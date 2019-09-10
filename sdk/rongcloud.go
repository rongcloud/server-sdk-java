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
	"net/url"
	"reflect"
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
	USERAGENT = "rc-go-sdk/3.0.1"
	// DEFAULTTIMEOUT 默认超时时间
	DEFAULTTIMEOUT = 10
	// NUMTIMEOUT 默认超时次数切换 Api 地址
	NUMTIMEOUT = 3
)

var (
	defaultExtra = rongCloudExtra{
		rongCloudURI:    RONGCLOUDURI,
		rongCloudSMSURI: RONGCLOUDSMSURI,
		timeout:         DEFAULTTIMEOUT,
		numTimeout:      NUMTIMEOUT,
		count:           0,
	}
	rc   *RongCloud
	once sync.Once
)

// RongCloud appKey appSecret extra
type RongCloud struct {
	appKey    string
	appSecret string
	*rongCloudExtra
}

// rongCloudExtra rongCloud扩展增加自定义融云服务器地址,请求超时时间
type rongCloudExtra struct {
	rongCloudURI    string
	rongCloudSMSURI string
	timeout         time.Duration
	numTimeout      uint
	count           uint
}

// CodeResult 融云返回状态码和错误码
type CodeResult struct {
	Code         int    `json:"code"`         // 返回码，200 为正常。
	ErrorMessage string `json:"errorMessage"` // 错误信息
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
		rc = &RongCloud{
			appKey:         appKey,
			appSecret:      appSecret,
			rongCloudExtra: &defaultRongCloud,
		}
	},
	)
	for _, option := range options {
		option(rc)
	}
	return rc
}

// GetRongCloud 获取 RongCloud 对象
func GetRongCloud() *RongCloud {
	return rc
}

// changeURI 切换 Api 服务器地址
func (rc *RongCloud) changeURI() {
	switch rc.rongCloudURI {
	case RONGCLOUDURI:
		rc.rongCloudURI = RONGCLOUDURI2
	case RONGCLOUDURI2:
		rc.rongCloudURI = RONGCLOUDURI
	default:
	}
}

// PrivateURI 私有云设置 Api 地址
func (rc *RongCloud) PrivateURI(uri, sms string) {
	rc.rongCloudURI = uri
	rc.rongCloudSMSURI = sms
}

// urlError 判断是否为 url.Error
func (rc *RongCloud) urlError(err error) {
	if rc.numTimeout == 0 {
		return
	}
	if reflect.TypeOf(err) == reflect.TypeOf(&url.Error{}) {
		if err.(*url.Error).Timeout() {
			if rc.count >= rc.numTimeout {
				rc.changeURI()
				rc.count = 1
			} else {
				rc.count++
			}
		}
	}
}
