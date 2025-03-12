package sdk

import (
	"os"
	"testing"
)

func TestNewRongCloud(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	t.Log(rc)
}

func TestGetRongCloud(t *testing.T) {
	NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	rc := GetRongCloud()
	t.Log(rc)
}
