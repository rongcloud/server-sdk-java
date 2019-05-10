package sdk

import (
	"testing"
)

func TestRongCloud_SensitiveAdd(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
	)
	err := rc.SensitiveAdd(
		"7Szq13MKRVortoknTAk7W8",
		"7Szq13MKRVortoknTAk7W8",
		1,
	)
	t.Log(err)
}

func TestRongCloud_SensitiveGetList(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
	)
	rep, err := rc.SensitiveGetList()
	if err == nil {
		t.Log(rep)
	}
	t.Log(err)
}

func TestRongCloud_SensitiveRemove(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
	)
	err := rc.SensitiveRemove(
		[]string{"7Szq13MKRVortoknTAk7W8"},
	)
	t.Log(err)
}
