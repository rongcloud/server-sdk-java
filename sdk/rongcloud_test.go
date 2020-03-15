package sdk

import (
    "os"
    "testing"
)

func TestNewRC(t *testing.T) {
    rc := NewRC("abc", "abc123", "api.test.com", "sms.test.com")

    if rc.appKey != "abc" {
        t.Error("invalid app key")
    }

    if rc.appSecret != "abc123" {
        t.Error("invalid app secret")
    }

    if rc.rongCloudExtra.rongCloudURI != "api.test.com" {
        t.Error("invalid rc uri")
    }

    if rc.rongCloudExtra.rongCloudSMSURI != "sms.test.com" {
        t.Error("invalid rc sms uri")
    }

    t.Log("success")
}

func TestNewRongCloud(t *testing.T) {
    rc := NewRongCloud(
        os.Getenv("APP_KEY"),
        os.Getenv("APP_SECRET"),
    )
    t.Log(rc)
}

func TestGetRongCloud(t *testing.T) {
    NewRongCloud(
        os.Getenv("APP_KEY"),
        os.Getenv("APP_SECRET"),
    )
    rc := GetRongCloud()
    t.Log(rc)
}
