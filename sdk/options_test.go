package sdk

import (
	"os"
	"testing"
)

func TestWithRongCloudSMSURI(t *testing.T) {
	rc := NewRongCloud("abc", "abc123", WithRongCloudSMSURI("sms.test.com"))

	if rc.rongCloudSMSURI != "sms.test.com" {
		t.Error("invalid RongCloud SMS URI")
	}

	t.Log("success")
}

func TestWithRongCloudURI(t *testing.T) {
	rc := NewRongCloud("abc", "abc123", WithRongCloudURI("api.test.com"))

	if rc.rongCloudURI != "api.test.com" {
		t.Error("invalid RongCloud URI")
	}

	t.Log("success")
}

func TestWithTimeout(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		// Set timeout to 20 seconds
		WithTimeout(20),
	)
	t.Log(rc)
}
