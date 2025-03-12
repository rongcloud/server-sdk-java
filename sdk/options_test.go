package sdk

import (
	"os"
	"testing"
)

func TestWithRongCloudURI(t *testing.T) {
	rc := NewRongCloud("abc", "abc123", REGION_BJ)

	if rc.rongCloudURI != "https://api.rong-api.com" {
		t.Error("invalid RongCloud URI")
	}

	t.Log("success")
}

func TestWithTimeout(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
		// Set timeout to 20 seconds
		WithTimeout(20),
	)
	t.Log(rc)
}
