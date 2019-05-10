package sdk

import (
	"testing"
	"time"
)

func TestNewRongCloud(t *testing.T) {

	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		WithTimeout(3*time.Second),
		WithNumTimeout(2),
	)
	t.Log(rc)
	t.Log(rc.timeout)
	t.Log(rc.numTimeout)
}
