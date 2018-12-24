package sdk

import (
	"fmt"
	"testing"
)

func TestRongCloud_UserRegister(t *testing.T) {

	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)

	rep, err := rc.UserRegister(
		"7Szq13MKRVortoknTAk7W8",
		"7Szq13MKRVortoknTAk7W8",
		"http://rongcloud.cn/portrait.jpg",
	)
	if err == nil {
		fmt.Println(rep)
	}
	fmt.Println(err.Error())
}

func TestRongCloud_UserUpdate(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)

	rep := rc.UserUpdate("7Szq13MKRVortoknTAk7W8", "7Szq13MKRVortoknTAk7W8", "http://rongcloud.cn/portrait.jpg")
	fmt.Println(rep)
}

func TestRongCloud_BlockAdd(t *testing.T) {

	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)

	err := rc.BlockAdd(
		"4kIvGJmETlYqDoVFgWdYdM",
		5,
	)
	fmt.Println(err)
}

func TestRongCloud_BlockGetList(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	rep, err := rc.BlockGetList()
	if err == nil {
		fmt.Println(rep)
	}
	fmt.Println(err)
}

func TestRongCloud_BlockRemove(t *testing.T) {

	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := rc.BlockRemove(
		"4kIvGJmETlYqDoVFgWdYdM",
	)

	fmt.Println(err)
}

func TestRongCloud_BlacklistAdd(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := rc.BlacklistAdd(
		"4kIvGJmETlYqDoVFgWdYdM",
		[]string{"u01"},
	)

	fmt.Println(err)
}

func TestRongCloud_BlacklistGetList(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	req, err := rc.BlacklistGet(
		"4kIvGJmETlYqDoVFgWdYdM",
	)
	fmt.Println(req)
	fmt.Println(err)
}

func TestRongCloud_BlacklistRemove(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := rc.BlacklistRemove(
		"4kIvGJmETlYqDoVFgWdYdM",
		[]string{"u01"},
	)
	fmt.Println(err)
}
