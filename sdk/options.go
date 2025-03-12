package sdk

import (
	"net/http"
	"time"
)

type rongCloudOption func(*RongCloud)

// WithRongCloudSMSURI sets the RongCloud SMS URI
func WithRongCloudSMSURI(rongCloudSMSURI string) rongCloudOption {
	return func(o *RongCloud) {
		o.rongCloudSMSURI = rongCloudSMSURI
	}
}

// WithRongCloudURI sets the RongCloud URI
func WithRongCloudURI(rongCloudURI string) rongCloudOption {
	return func(o *RongCloud) {
		o.rongCloudURI = rongCloudURI
	}
}

// WithTimeout sets the timeout duration, with the minimum unit in seconds
func WithTimeout(t time.Duration) rongCloudOption {
	return func(o *RongCloud) {
		o.timeout = t
	}
}

// WithKeepAlive sets the connection keep-alive duration, with the minimum unit in seconds
func WithKeepAlive(t time.Duration) rongCloudOption {
	return func(o *RongCloud) {
		o.keepAlive = t
	}
}

// WithMaxIdleConnsPerHost sets the maximum number of connections per host
func WithMaxIdleConnsPerHost(n int) rongCloudOption {
	return func(o *RongCloud) {
		o.maxIdleConnsPerHost = n
	}
}

func WithTransport(transport http.RoundTripper) rongCloudOption {
	return func(o *RongCloud) {
		o.globalTransport = transport
	}
}
