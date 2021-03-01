package sdk

import (
	"time"
)

type rongCloudOption func(*RongCloud)

// WithRongCloudSMSURI 设置融云 SMS URI
func WithRongCloudSMSURI(rongCloudSMSURI string) rongCloudOption {
	return func(o *RongCloud) {
		o.rongCloudSMSURI = rongCloudSMSURI
	}
}

// WithRongCloudURI 设置融云 URI
func WithRongCloudURI(rongCloudURI string) rongCloudOption {
	return func(o *RongCloud) {
		o.rongCloudURI = rongCloudURI
	}
}

// WithTimeout 设置超时时间
func WithTimeout(t time.Duration) rongCloudOption {
	return func(o *RongCloud) {
		o.timeout = t
	}
}

// WithKeepAlive 连接保活时间
func WithKeepAlive(t time.Duration) rongCloudOption {
	return func(o *RongCloud) {
		o.keepAlive = t
	}
}

// WithMaxIdleConnsPerHost 设置每个域名最大连接数
func WithMaxIdleConnsPerHost(n int) rongCloudOption {
	return func(o *RongCloud) {
		o.maxIdleConnsPerHost = n
	}
}
