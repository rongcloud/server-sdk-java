/*
 * 融云 Server API go 客户端
 * create by RongCloud
 * create datetime : 2018-11-28
 *
 * v3.0.0
 */

package sdk

import (
	"crypto/sha1"
	"fmt"
	"github.com/astaxie/beego/httplib"
	"io"
	"math/rand"
	"strconv"
	"time"
)

const (
	// RONGCLOUDSMSURI 容云默认 SMS API 地址
	RONGCLOUDSMSURI = "http://172.29.202.3:18082"
	// RONGCLOUDURI 容云默认 API 地址
	RONGCLOUDURI = "http://172.29.202.3:18081"
	// ReqType body类型
	ReqType = "json"
	// USERAGENT sdk 名称
	USERAGENT = "rc-go-sdk/3.0"
	// DEFAULTTIMEOUT 默认超时时间
	DEFAULTTIMEOUT = 30
)

// RongCloud ak sk
type RongCloud struct {
	appKey    string
	appSecret string
	*RongCloudExtra
}

// RongCloudExtra RongCloud扩展增加自定义容云服务器地址,请求超时时间
type RongCloudExtra struct {
	RongCloudURI    string
	RongCloudSMSURI string
	TimeOut         time.Duration
}

// CodeReslut 容云返回状态码和错误码
type CodeReslut struct {
	Code         int    `json:"code"`
	ErrorMessage string `json:"errorMessage"`
}

// getSignature 本地生成签名
// Signature (数据签名)计算方法：将系统分配的 App Secret、Nonce (随机数)、
// Timestamp (时间戳)三个字符串按先后顺序拼接成一个字符串并进行 SHA1 哈希计算。如果调用的数据签名验证失败，接口调用会返回 HTTP 状态码 401。
func (rc *RongCloud) getSignature() (nonce, timestamp, signature string) {
	nonceInt := rand.Int()
	nonce = strconv.Itoa(nonceInt)
	timeInt64 := time.Now().Unix()
	timestamp = strconv.FormatInt(timeInt64, 10)
	h := sha1.New()
	io.WriteString(h, rc.appSecret+nonce+timestamp)
	signature = fmt.Sprintf("%x", h.Sum(nil))
	return
}

// FillHeader 在http header 增加API签名
func (rc *RongCloud) FillHeader(req *httplib.BeegoHTTPRequest) {
	nonce, timestamp, signature := rc.getSignature()
	req.Header("App-Key", rc.appKey)
	req.Header("Nonce", nonce)
	req.Header("Timestamp", timestamp)
	req.Header("Signature", signature)
	req.Header("Content-Type", "application/x-www-form-urlencoded")
	req.Header("User-Agent", USERAGENT)
}

// FillJSONHeader 在http header Content-Type 设置为josn格式
func FillJSONHeader(req *httplib.BeegoHTTPRequest) {
	req.Header("Content-Type", "application/json")
}

// NewRongCloud 创建RongCloud对象
func NewRongCloud(appKey, appSecret string, extra *RongCloudExtra) *RongCloud {
	// 默认扩展配置
	defaultExtra := RongCloudExtra{
		RongCloudURI:    RONGCLOUDURI,
		RongCloudSMSURI: RONGCLOUDSMSURI,
		TimeOut:         DEFAULTTIMEOUT,
	}
	// 使用默认服务器地址
	if extra == nil {
		rc := RongCloud{
			appKey:         appKey,    //app key
			appSecret:      appSecret, //app secret
			RongCloudExtra: &defaultExtra,
		}
		return &rc
	}
	if extra.TimeOut == 0 {
		extra.TimeOut = DEFAULTTIMEOUT
	}
	// RongCloudSMSURI RongCloudURI 必须同时修改
	if extra.RongCloudSMSURI == "" || extra.RongCloudURI == "" {
		extra.RongCloudURI = RONGCLOUDURI
		extra.RongCloudSMSURI = RONGCLOUDSMSURI
	}
	// 使用扩展配置地址
	rc := RongCloud{
		appKey:         appKey,    //app key
		appSecret:      appSecret, //app secret
		RongCloudExtra: extra,
	}
	return &rc
}
