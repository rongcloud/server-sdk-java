package sdk

import (
	"os"
	"testing"
	"time"
)

func TestWithNumTimeout(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		WithTimeout(3*time.Second),
		WithNumTimeout(2),
	)
	t.Log(rc)
	t.Log(rc.numTimeout)
}

func TestWithTimeout(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		WithTimeout(3*time.Second),
	)
	t.Log(rc)
	t.Log(rc.timeout)
}
