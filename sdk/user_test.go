package sdk

import (
	"os"
	"testing"
)

func TestRongCloud_UserRegister(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("RC_APP_ID"),
		os.Getenv("RC_APP_SECRET"),
	)

	rep, err := rc.UserRegister(
		"7Szq13MKRVortoknTAk7W8",
		"7Szq13MKRVortoknTAk7W8",
		"http://rongcloud.cn/portrait.jpg",
	)
	t.Log(err)
	t.Log(rep)
}

func TestRongCloud_UserUpdate(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("RC_APP_ID"),
		os.Getenv("RC_APP_SECRET"),
	)

	rep := rc.UserUpdate("7Szq13MKRVortoknTAk7W8", "7Szq13MKRVortoknTAk7W8", "http://rongcloud.cn/portrait.jpg")
	t.Log(rep)
}

func TestRongCloud_BlockAdd(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("RC_APP_ID"),
		os.Getenv("RC_APP_SECRET"),
	)

	err := rc.BlockAdd(
		"4kIvGJmETlYqDoVFgWdYdM",
		5,
	)
	t.Log(err)
}

func TestRongCloud_BlockGetList(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("RC_APP_ID"),
		os.Getenv("RC_APP_SECRET"),
		nil,
	)
	rep, err := rc.BlockGetList()
	t.Log(err)
	t.Log(rep)
}

func TestRongCloud_BlockRemove(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("RC_APP_ID"),
		os.Getenv("RC_APP_SECRET"),
	)
	err := rc.BlockRemove(
		"4kIvGJmETlYqDoVFgWdYdM",
	)
	t.Log(err)
}

func TestRongCloud_BlacklistAdd(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("RC_APP_ID"),
		os.Getenv("RC_APP_SECRET"),
	)
	err := rc.BlacklistAdd(
		"4kIvGJmETlYqDoVFgWdYdM",
		[]string{"u01"},
	)

	t.Log(err)
}

func TestRongCloud_BlacklistGetList(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("RC_APP_ID"),
		os.Getenv("RC_APP_SECRET"),
	)
	req, err := rc.BlacklistGet(
		"4kIvGJmETlYqDoVFgWdYdM",
	)
	t.Log(err)
	t.Log(req)
}

func TestRongCloud_BlacklistRemove(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("RC_APP_ID"),
		os.Getenv("RC_APP_SECRET"),
	)
	err := rc.BlacklistRemove(
		"4kIvGJmETlYqDoVFgWdYdM",
		[]string{"u01"},
	)
	t.Log(err)
}

func TestRongCloud_OnlineStatusCheck(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("RC_APP_ID"),
		os.Getenv("RC_APP_SECRET"),
	)
	status, err := rc.OnlineStatusCheck("4kIvGJmETlYqDoVFgWdYdM")
	t.Log(err)
	t.Log(status)
}
