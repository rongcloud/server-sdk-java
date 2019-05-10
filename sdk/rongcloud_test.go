package sdk

import (
	"os"
	"testing"
	"time"
)

func TestNewRongCloud(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("RC_APP_ID"),
		os.Getenv("RC_APP_SECRET"),
		WithTimeout(3*time.Second),
		WithNumTimeout(2),
	)
	t.Log(rc)
	t.Log(rc.timeout)
	t.Log(rc.numTimeout)
}
