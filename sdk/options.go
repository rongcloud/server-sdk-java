package sdk

import "time"

type rongCloudOption func(*rongCloud)

// WithTimeout 设置超时时间
func WithTimeout(t time.Duration) rongCloudOption {
	return func(o *rongCloud) {
		o.timeout = t
	}
}

// WithNumTimeout 设置切换 Api 超时次数
func WithNumTimeout(n uint) rongCloudOption {
	return func(o *rongCloud) {
		o.numTimeout = n
	}
}
