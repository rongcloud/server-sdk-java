package RCserverSDK

import (
	"testing"
	"fmt"
	)

func TestRongCloud_UserRegister(t *testing.T) {

	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
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
	if err != nil{
		t.Fatal(err)
	}
}

func TestRongCloud_UserUpdate(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)

	rep := rc.UserUpdate("7Szq13MKRVortoknTAk7W8","7Szq13MKRVortoknTAk7W8","http://rongcloud.cn/portrait.jpg")
	fmt.Println(rep)
}



func TestRongCloud_BlockAdd(t *testing.T) {

	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
		)

	err := rc.BlockAdd(
		"4kIvGJmETlYqDoVFgWdYdM",
		5,
	)
	if err != nil{
		t.Fatal(err)
	}
}

func TestRongCloud_BlockGetList(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	rep, err := rc.BlockGetList()
	if err == nil{
		fmt.Println(rep)
	}
	if err != nil{
		t.Fatal(err)
	}
}

func TestRongCloud_BlockRemove(t *testing.T) {

	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.BlockRemove(
		"4kIvGJmETlYqDoVFgWdYdM",
	)

	if err != nil{
		t.Fatal(err)
	}
}

func TestRongCloud_BlacklistAdd(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.BlacklistAdd(
		"4kIvGJmETlYqDoVFgWdYdM",
		[]string{"u01"},
	)

	if err != nil{

		t.Fatal(err)
	}
}

func TestRongCloud_BlacklistGetList(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	req, err := rc.BlacklistGet(
		"4kIvGJmETlYqDoVFgWdYdM",
	)
	fmt.Println(req)
	if err != nil{
		fmt.Println(err)
		t.Fatal(err)
	}
}

func TestRongCloud_BlacklistRemove(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.BlacklistRemove(
		"4kIvGJmETlYqDoVFgWdYdM",
		[]string{"u01"},
	)
	if err != nil{
		t.Fatal(err)
	}
}
