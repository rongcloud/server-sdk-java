/*
 * @Descripttion:
 * @version:
 * @Author: ran.ding
 * @Date: 2019-09-02 18:29:55
 * @LastEditors: ran.ding
 * @LastEditTime: 2019-09-10 15:39:14
 */

// The MIT License (MIT)

// Copyright (c) 2014 融云 Rong Cloud

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

/*
 * 融云 Server API go 客户端
 * create by RongCloud
 * create datetime : 2018-11-28
 * v3
 */

package sdk

import (
	"crypto/sha1"
	"fmt"
	"io"
	"math/rand"
	"net"
	"net/http"
	"strconv"
	"sync"
	"time"

	"github.com/astaxie/beego/httplib"
)

const (
	// RONGCLOUDSMSURI 融云默认 SMS API 地址
	RONGCLOUDSMSURI = "http://api.sms.ronghub.com"
	// RONGCLOUDURI 融云默认 API 地址
	RONGCLOUDURI = "http://api-cn.ronghub.com"
	// RONGCLOUDURI2 融云备用 API 地址
	RONGCLOUDURI2 = "http://api2-cn.ronghub.com"
	// ReqType body类型
	ReqType = "json"
	// USERAGENT sdk 名称
	USERAGENT = "rc-go-sdk/3.2.0"
	// DEFAULTTIMEOUT 默认超时时间，10秒
	DEFAULTTIMEOUT = 10 * time.Second
	// DEFAULT_KEEPALIVE http 默认保活时间，30秒
	DEFAULT_KEEPALIVE = 30 * time.Second
	// DEFAULT_MAXIDLECONNSPERHOST http 默认每个域名连接数，100
	DEFAULT_MAXIDLECONNSPERHOST = 100
	// 自动切换 api 地址时间间隔，秒
	DEFAULT_CHANGE_URI_DURATION = 30
)

var (
	defaultExtra = rongCloudExtra{
		rongCloudURI:        RONGCLOUDURI,
		rongCloudSMSURI:     RONGCLOUDSMSURI,
		timeout:             DEFAULTTIMEOUT,
		keepAlive:           DEFAULT_KEEPALIVE,
		maxIdleConnsPerHost: DEFAULT_MAXIDLECONNSPERHOST,
		count:               0,
		changeUriDuration:   DEFAULT_CHANGE_URI_DURATION,
		lastChageUriTime:    0,
	}
	rc   *RongCloud
	once sync.Once
)

// RongCloud appKey appSecret extra
type RongCloud struct {
	appKey    string
	appSecret string
	*rongCloudExtra
	uriLock         sync.Mutex
	globalTransport *http.Transport
}

// rongCloudExtra rongCloud扩展增加自定义融云服务器地址,请求超时时间
type rongCloudExtra struct {
	rongCloudURI        string
	rongCloudSMSURI     string
	timeout             time.Duration
	keepAlive           time.Duration
	maxIdleConnsPerHost int
	count               uint
	changeUriDuration   int64
	lastChageUriTime    int64
}

// getSignature 本地生成签名
// Signature (数据签名)计算方法：将系统分配的 App Secret、Nonce (随机数)、
// Timestamp (时间戳)三个字符串按先后顺序拼接成一个字符串并进行 SHA1 哈希计算。如果调用的数据签名验证失败，接口调用会返回 HTTP 状态码 401。
func (rc RongCloud) getSignature() (nonce, timestamp, signature string) {
	nonceInt := rand.Int()
	nonce = strconv.Itoa(nonceInt)
	timeInt64 := time.Now().Unix()
	timestamp = strconv.FormatInt(timeInt64, 10)
	h := sha1.New()
	_, _ = io.WriteString(h, rc.appSecret+nonce+timestamp)
	signature = fmt.Sprintf("%x", h.Sum(nil))
	return
}

// fillHeader 在 Http Header 增加API签名
func (rc RongCloud) fillHeader(req *httplib.BeegoHTTPRequest) {
	nonce, timestamp, signature := rc.getSignature()
	req.Header("App-Key", rc.appKey)
	req.Header("Nonce", nonce)
	req.Header("Timestamp", timestamp)
	req.Header("Signature", signature)
	req.Header("Content-Type", "application/x-www-form-urlencoded")
	req.Header("User-Agent", USERAGENT)
}

// fillJSONHeader 在 Http Header Content-Type 设置为josn格式
func fillJSONHeader(req *httplib.BeegoHTTPRequest) {
	req.Header("Content-Type", "application/json")
}

// NewRongCloud 创建 RongCloud 对象
func NewRongCloud(appKey, appSecret string, options ...rongCloudOption) *RongCloud {
	once.Do(func() {
		// 默认扩展配置
		defaultRongCloud := defaultExtra
		defaultRongCloud.lastChageUriTime = 0
		rc = &RongCloud{
			appKey:         appKey,
			appSecret:      appSecret,
			rongCloudExtra: &defaultRongCloud,
		}

		for _, option := range options {
			option(rc)
		}
		// 全局 httpClient，解决 http 打开端口过多问题
		dialer := &net.Dialer{
			Timeout:   rc.timeout,
			KeepAlive: rc.keepAlive,
		}

		rc.globalTransport = &http.Transport{
			DialContext:         dialer.DialContext,
			MaxIdleConnsPerHost: rc.maxIdleConnsPerHost,
		}
	},
	)

	return rc
}

// GetRongCloud 获取 RongCloud 对象
func GetRongCloud() *RongCloud {
	return rc
}

// 自定义 http 参数
func (rc *RongCloud) SetHttpTransport(httpTransport *http.Transport) {
	rc.globalTransport = httpTransport
}

func (rc *RongCloud) GetHttpTransport() *http.Transport {
	return rc.globalTransport
}

// changeURI 自动切换 Api 服务器地址
// 在 api、api2之间自动切换。无法切换其他域名。其他请使用 PrivateURI 设置
func (rc *RongCloud) ChangeURI() {
	nowUnix := time.Now().Unix()
	// 检查距离上次更换uri的时间间隔
	rc.uriLock.Lock()
	if (nowUnix - rc.lastChageUriTime) >= rc.changeUriDuration {
		switch rc.rongCloudURI {
		case RONGCLOUDURI:
			rc.rongCloudURI = RONGCLOUDURI2
		case RONGCLOUDURI2:
			rc.rongCloudURI = RONGCLOUDURI
		default:
		}
		rc.lastChageUriTime = nowUnix
	}
	rc.uriLock.Unlock()
}

// PrivateURI 私有云设置 Api 地址
func (rc *RongCloud) PrivateURI(uri, sms string) {
	rc.rongCloudURI = uri
	rc.rongCloudSMSURI = sms
}

// urlError 判断是否为 url.Error
func (rc *RongCloud) urlError(err error) {
	// 方法已废弃
}

/**
判断 http status code, 如果大于 500 就切换一次域名
*/
func (rc *RongCloud) checkStatusCode(resp *http.Response) {
	if resp.StatusCode >= 500 && resp.StatusCode < 600 {
		rc.ChangeURI()
	}

	return
}
