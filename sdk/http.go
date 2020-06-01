package sdk

import (
	"compress/gzip"
	"encoding/json"
	"github.com/astaxie/beego/httplib"
	"io/ioutil"
)

func (rc *RongCloud) do(b *httplib.BeegoHTTPRequest) (body []byte, err error) {
	return rc.httpRequest(b)
}

func (rc *RongCloud) httpRequest(b *httplib.BeegoHTTPRequest) (body []byte, err error) {
	resp, err := b.DoRequest()
	if err != nil {
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
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return code
	}
	return nil
}
