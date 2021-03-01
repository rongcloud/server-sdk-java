package sdk

import (
	"compress/gzip"
	"encoding/json"
	"github.com/astaxie/beego/httplib"
	"io/ioutil"
	"net"
	"net/url"
	"os"
	"syscall"
)

func (rc *RongCloud) do(b *httplib.BeegoHTTPRequest) (body []byte, err error) {
	return rc.httpRequest(b)
}

// 需要切换域名的网络错误
func isNetError(err error) bool {
	netErr, ok := err.(net.Error)
	if !ok {
		return false
	}
	// 超时
	if netErr.Timeout() {
		return true
	}

	var opErr *net.OpError
	opErr, ok = netErr.(*net.OpError)
	if !ok {
		//  url 错误
		urlErr, ok := netErr.(*url.Error)
		if !ok {
			return false
		}
		opErr, ok = urlErr.Err.(*net.OpError)
		if !ok {
			return false
		}
	}

	switch t := opErr.Err.(type) {
	case *net.DNSError:
		return true
	case *os.SyscallError:
		if errno, ok := t.Err.(syscall.Errno); ok {
			switch errno {
			case syscall.ECONNREFUSED:
				return true
			case syscall.ETIMEDOUT:
				return true
			}
		}
	}

	return false
}

func (rc *RongCloud) httpRequest(b *httplib.BeegoHTTPRequest) (body []byte, err error) {
	// 使用全局 httpClient，解决 http 打开端口过多问题
	b.SetTransport(rc.globalTransport)

	resp, err := b.DoRequest()
	if err != nil {
		if isNetError(err) {
			rc.ChangeURI()
		}
		return nil, err
	}
	if resp.Body == nil {
		return nil, nil
	}
	defer resp.Body.Close()
	rc.checkStatusCode(resp)
	if resp.Header.Get("Content-Encoding") == "gzip" {
		reader, err := gzip.NewReader(resp.Body)
		if err != nil {
			return nil, err
		}
		body, err = ioutil.ReadAll(reader)
	} else {
		body, err = ioutil.ReadAll(resp.Body)
	}
	if err = checkHTTPResponseCode(body); err != nil {
		return nil, err
	}
	return body, err
}

func checkHTTPResponseCode(rep []byte) error {
	code := codePool.Get().(CodeResult)
	defer codePool.Put(code)
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return code
	}
	return nil
}
