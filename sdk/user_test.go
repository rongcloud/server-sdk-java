package sdk

import (
	"testing"
)

func TestRongCloud_UserRegister(t *testing.T) {

	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
	)

	rep, err := rc.UserRegister(
		"7Szq13MKRVortoknTAk7W8",
		"7Szq13MKRVortoknTAk7W8",
		"http://rongcloud.cn/portrait.jpg",
	)
	if err == nil {
		t.Log(rep)
	}
	t.Log(err)
}

func TestRongCloud_UserUpdate(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
	)

	rep := rc.UserUpdate("7Szq13MKRVortoknTAk7W8", "7Szq13MKRVortoknTAk7W8", "http://rongcloud.cn/portrait.jpg")
	t.Log(rep)
}

func TestRongCloud_BlockAdd(t *testing.T) {

	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
	)

	err := rc.BlockAdd(
		"4kIvGJmETlYqDoVFgWdYdM",
		5,
	)
	t.Log(err)
}

func TestRongCloud_BlockGetList(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	rep, err := rc.BlockGetList()
	if err == nil {
		t.Log(rep)
	}
	t.Log(err)
}

func TestRongCloud_BlockRemove(t *testing.T) {

	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
	)
	err := rc.BlockRemove(
		"4kIvGJmETlYqDoVFgWdYdM",
	)

	t.Log(err)
}

func TestRongCloud_BlacklistAdd(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
	)
	err := rc.BlacklistAdd(
		"4kIvGJmETlYqDoVFgWdYdM",
		[]string{"u01"},
	)

	t.Log(err)
}

func TestRongCloud_BlacklistGetList(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
	)
	req, err := rc.BlacklistGet(
		"4kIvGJmETlYqDoVFgWdYdM",
	)
	t.Log(req)
	t.Log(err)
}

func TestRongCloud_BlacklistRemove(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
	)
	err := rc.BlacklistRemove(
		"4kIvGJmETlYqDoVFgWdYdM",
		[]string{"u01"},
	)
	t.Log(err)
}

func TestRongCloud_OnlineStatusCheck(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
	)
	status, err := rc.OnlineStatusCheck("4kIvGJmETlYqDoVFgWdYdM")
	t.Log(err)
	t.Log(status)
}
