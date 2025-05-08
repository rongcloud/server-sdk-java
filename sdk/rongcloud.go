/*
 * @Descripttion:
 * @version:
 * @Author: ran.ding
 * @Date: 2019-09-02 18:29:55
 * @LastEditors: ran.ding
 * @LastEditTime: 2019-09-10 15:39:14
 */

// The MIT License (MIT)

// Copyright (c) 2014 RongCloud Rong Cloud

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
 * RongCloud Server API Go Client
 * Created by RongCloud
 * Creation date: 2018-11-28
 * Version: v3
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
	"github.com/google/uuid"
)

const (
	// ReqType Body type
	ReqType = "json"
	// USERAGENT SDK name
	USERAGENT = "rc-go-sdk/4.0.3"
	// DEFAULTTIMEOUT Default timeout, 10 seconds
	DEFAULTTIMEOUT = 10
	// DEFAULT_KEEPALIVE Default HTTP keepalive time, 30 seconds
	DEFAULT_KEEPALIVE = 30
	// DEFAULT_MAXIDLECONNSPERHOST Default maximum idle connections per host, 100
	DEFAULT_MAXIDLECONNSPERHOST = 100
	// DEFAULT_CHANGE_URI_DURATION Interval for automatic API URL switching, in seconds
	DEFAULT_CHANGE_URI_DURATION = 30
)

var (
	defaultExtra = rongCloudExtra{
		timeout:             DEFAULTTIMEOUT,
		keepAlive:           DEFAULT_KEEPALIVE,
		maxIdleConnsPerHost: DEFAULT_MAXIDLECONNSPERHOST,
		count:               0,
		changeUriDuration:   DEFAULT_CHANGE_URI_DURATION,
		lastChageUriTime:    0,
	}
	rc   *RongCloud
	once sync.Once

	REGION_BJ   = Region{"https://api.rong-api.com", "https://api-b.rong-api.com"}
	REGION_SG   = Region{"https://api.sg-light-api.com", "https://api-b.sg-light-api.com"}
	REGION_SG_B = Region{"https://api.sg-b-light-api.com", "https://api-b.sg-b-light-api.com"}
	REGION_NA   = Region{"https://api.us-light-api.com", "https://api-b.us-light-api.com"}
	REGION_SAU  = Region{"https://api.sau-light-api.com", "https://api-b.sau-light-api.com"}
)

type Region struct {
	primaryDomain string
	backupDomain  string
}

// RongCloud appKey appSecret extra
type RongCloud struct {
	appKey        string
	appSecret     string
	primaryDomain string
	backupDomain  string
	rongCloudURI  string
	*rongCloudExtra
	uriLock         sync.Mutex
	globalTransport http.RoundTripper
}

// rongCloudExtra extends RongCloud with custom RongCloud server address and request timeout
type rongCloudExtra struct {
	timeout             time.Duration
	keepAlive           time.Duration
	maxIdleConnsPerHost int
	count               uint
	changeUriDuration   int64
	lastChageUriTime    int64
}

// getSignature generates a local signature
// Signature calculation method: Concatenate the App Secret, Nonce (random number),
// and Timestamp (Unix timestamp) in order, then compute the SHA1 hash. If the signature verification fails, the API call will return HTTP status code 401.
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

// fillHeader adds API signature to the Http Header
func (rc RongCloud) fillHeader(req *httplib.BeegoHTTPRequest) {
	requestId := uuid.New().String()
	req.Header("Content-Type", "application/x-www-form-urlencoded")
	req.Header("User-Agent", USERAGENT)
	req.Header("X-Request-Id", requestId)
	nonce, timestamp, signature := rc.getSignature()
	req.Header("App-Key", rc.appKey)
	req.Header("Nonce", nonce)
	req.Header("Timestamp", timestamp)
	req.Header("Signature", signature)
}

// v2 sdk header
func (rc RongCloud) fillHeaderV2(req *httplib.BeegoHTTPRequest) string {
	requestId := uuid.New().String()
	req.Header("User-Agent", USERAGENT)
	req.Header("X-Request-Id", requestId)
	req.Header("Content-Type", "application/json")
	nonce, timestamp, signature := rc.getSignature()
	req.Header("App-Key", rc.appKey)
	req.Header("Timestamp", timestamp)
	req.Header("Nonce", nonce)
	req.Header("Signature", signature)
	return requestId
}

// fillJSONHeader sets the Http Header Content-Type to JSON format
func fillJSONHeader(req *httplib.BeegoHTTPRequest) {
	req.Header("Content-Type", "application/json")
}

// NewRongCloud creates a RongCloud object
func NewRongCloud(appKey, appSecret string, region Region, options ...rongCloudOption) *RongCloud {
	once.Do(func() {
		// Default extended configuration
		defaultRongCloud := defaultExtra
		defaultRongCloud.lastChageUriTime = 0
		rc = &RongCloud{
			appKey:         appKey,
			appSecret:      appSecret,
			rongCloudURI:   region.primaryDomain,
			primaryDomain:  region.primaryDomain,
			backupDomain:   region.backupDomain,
			rongCloudExtra: &defaultRongCloud,
		}

		for _, option := range options {
			option(rc)
		}

		if rc.globalTransport == nil {
			rc.globalTransport = &http.Transport{
				DialContext: (&net.Dialer{
					Timeout:   rc.timeout * time.Second,
					KeepAlive: rc.keepAlive * time.Second,
				}).DialContext,
				MaxIdleConnsPerHost: rc.maxIdleConnsPerHost,
			}
		}
	})

	return rc
}

// GetRongCloud retrieves the RongCloud object
func GetRongCloud() *RongCloud {
	return rc
}

// Customizes HTTP parameters
func (rc *RongCloud) SetHttpTransport(httpTransport http.RoundTripper) {
	rc.globalTransport = httpTransport
}

func (rc *RongCloud) GetHttpTransport() http.RoundTripper {
	return rc.globalTransport
}

// changeURI automatically switches the API server address
func (rc *RongCloud) ChangeURI() {
	nowUnix := time.Now().Unix()
	// Check the time interval since the last URI change
	rc.uriLock.Lock()
	if (nowUnix - rc.lastChageUriTime) >= rc.changeUriDuration {
		switch rc.rongCloudURI {
		case rc.primaryDomain:
			rc.rongCloudURI = rc.backupDomain
		case rc.backupDomain:
			rc.rongCloudURI = rc.primaryDomain
		default:
		}
		rc.lastChageUriTime = nowUnix
	}
	rc.uriLock.Unlock()
}

// PrivateURI sets the API address for private cloud
func (rc *RongCloud) PrivateURI(uri string) {
	rc.rongCloudURI = uri
}

// urlError checks if the error is a url.Error
func (rc *RongCloud) urlError(err error) {
	// This method is deprecated
}

/*
*
Check the HTTP status code, and switch the domain once if it's greater than or equal to 500
*/
func (rc *RongCloud) checkStatusCode(resp *http.Response) {
	if resp.StatusCode >= 500 && resp.StatusCode < 600 {
		rc.ChangeURI()
	}

	return
}
