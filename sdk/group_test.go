package sdk

import (
	"fmt"
	"testing"
)

func TestRongCloud_GroupCreate(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)

	err := rc.GroupCreate(
		"u01",
		"rongcloud_group01",
		[]string{"u01", "u02"},
	)

	fmt.Println(err)
}

func TestRongCloud_GroupGet(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)

	rep, err := rc.GroupGet(
		"u01",
	)
	if err == nil {
		fmt.Println(rep)
	}
	fmt.Println(err)
}

func TestRongCloud_GroupJoin(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)

	err := rc.GroupJoin(
		"u01",
		"rongcloud_group01",
		"u03",
	)

	fmt.Println(err)
}

func TestRongCloud_GroupUpdate(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)

	err := rc.GroupUpdate(
		"u01",
		"rongcloud_group02",
	)

	fmt.Println(err)
}

func TestRongCloud_GroupQuit(t *testing.T) {

	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)

	err := rc.GroupQuit(
		"u03",
		"u01",
	)

	fmt.Println(err)
}

func TestRongCloud_GroupSync(t *testing.T) {

	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	group := Group{ID: "u02", Name: "rongcloud_group02"}
	groups := []Group{}
	groups = append(groups, group)
	err := rc.GroupSync(
		"u04",
		groups,
	)

	fmt.Println(err)
}

func TestRongCloud_GroupGagAdd(t *testing.T) {

	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)

	err := rc.GroupGagAdd(
		"u01",
		[]string{"u02"},
		300,
	)
	fmt.Println(err)
}

func TestRongCloud_GROUPGagList(t *testing.T) {

	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)

	rep, err := rc.GroupGagList(
		"u01",
	)
	if err == nil {
		fmt.Println(rep)
	}
	fmt.Println(err)
}

func TestRongCloud_GroupGagremove(t *testing.T) {

	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)

	err := rc.GroupGagRemove(
		"u01",
		[]string{"u02"},
	)

	fmt.Println(err)
}

func TestRongCloud_GroupDismiss(t *testing.T) {

	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)

	err := rc.GroupDismiss(
		"u01",
		"u01",
	)

	fmt.Println(err)
}
