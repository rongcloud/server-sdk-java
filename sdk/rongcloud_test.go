package sdk

import (
	"os"
	"testing"
)

func TestNewRongCloud(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("RC_APP_ID"),
		os.Getenv("RC_APP_SECRET"),
	)
	t.Log(rc)
}

func TestGetRongCloud(t *testing.T) {
	NewRongCloud(
		os.Getenv("RC_APP_ID"),
		os.Getenv("RC_APP_SECRET"),
	)
	rc := GetRongCloud()
	t.Log(rc)
}
