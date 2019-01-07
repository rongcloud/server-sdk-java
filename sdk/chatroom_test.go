package sdk

import (
	"fmt"
	"testing"
)

func TestRongCloud_ChatRoomCreate(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := rc.ChatRoomCreate(
		"chrm01",
		"rcchrm01",
	)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomGet(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	rep, err := rc.ChatRoomGet(
		"chrm01",
		500,
		1,
	)
	fmt.Println(err)
	fmt.Println(rep)

}

func TestRongCloud_ChatRoomIsExist(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	rep, err := rc.ChatRoomIsExist(
		"chrm01",
		[]string{"u01"},
	)
	fmt.Println(err)
	fmt.Println(rep)
}

func TestRongCloud_ChatRoomDestroy(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := rc.ChatRoomDestroy(
		"chrm01",
	)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomBanAdd(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := rc.ChatRoomBanAdd(
		[]string{"u01"},
		30)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomBanGetList(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	rep, err := rc.ChatRoomBanGetList()
	fmt.Println(err)
	fmt.Println(rep)
}

func TestRongCloud_ChatRoomBanRemove(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := rc.ChatRoomBanRemove(
		[]string{"u01"},
	)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomBlockAdd(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := rc.ChatRoomBlockAdd(
		"chrm01",
		[]string{"u01"},
		30,
	)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomBlockGetList(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	rep, err := rc.ChatRoomBlockGetList(
		"chrm01",
	)
	fmt.Println(err)
	fmt.Println(rep)
}

func TestRongCloud_ChatRoomBlockRemove(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := rc.ChatRoomBlockRemove(
		"chrm01",
		[]string{"u01"},
	)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomDemotionAdd(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	txtmsg := "RC:TxtMsg"
	err := rc.ChatRoomDemotionAdd(
		[]string{
			txtmsg,
		},
	)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomDemotionGetList(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	rep, err := rc.ChatRoomDemotionGetList()
	fmt.Println(err)
	fmt.Println(rep)
}

func TestRongCloud_ChatRoomDemotionRemove(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	txtmsg := "RC:TxtMsg"
	err := rc.ChatRoomDemotionRemove(
		[]string{
			txtmsg,
		},
	)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomDistributionStop(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := rc.ChatRoomDistributionStop(
		"chrm01",
	)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomDistributionResume(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := rc.ChatRoomDistributionResume(
		"chrm01",
	)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomGagAdd(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := rc.ChatRoomGagAdd(
		"chrm01",
		[]string{"u01"},
		30,
	)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomGagGetList(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	rep, err := rc.ChatRoomGagGetList(
		"chrm01",
	)
	fmt.Println(err)
	fmt.Println(rep)
}

func TestRongCloud_ChatRoomGagRemove(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := rc.ChatRoomGagRemove(
		"chrm01",
		[]string{"u01"},
	)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomKeepAliveAdd(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := rc.ChatRoomKeepAliveAdd(
		"chrm01",
	)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomKeepAliveGetList(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	rep, err := rc.ChatRoomKeepAliveGetList(
		"chrm01",
	)
	fmt.Println(err)
	fmt.Println(rep)
}

func TestRongCloud_ChatRoomKeepAliveRemove(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := rc.ChatRoomKeepAliveRemove(
		"chrm01",
	)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomUserWhitelistAdd(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)

	err := rc.ChatRoomUserWhitelistAdd(
		"chrm01",
		[]string{"u01"},
	)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomUserWhitelistGetList(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	rep, err := rc.ChatRoomUserWhitelistGetList(
		"chrm01",
	)
	fmt.Println(err)
	fmt.Println(rep)
}

func TestRongCloud_ChatRoomUserWhitelistRemove(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	err := rc.ChatRoomUserWhitelistRemove(
		"chrm01",
		[]string{"u01"},
	)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomWhitelistAdd(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	txtmsg := "RC:TxtMsg"

	err := rc.ChatRoomWhitelistAdd(
		[]string{txtmsg},
	)
	fmt.Println(err)
}

func TestRongCloud_ChatRoomWhitelistGetList(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	rep, err := rc.ChatRoomWhitelistGetList()
	fmt.Println(err)
	fmt.Println(rep)
}

func TestRongCloud_ChatRoomWhitelistRemove(t *testing.T) {
	rc := NewRongCloud(
		"输入用户app key",
		"输入用户app secret",
		nil,
	)
	txtmsg := "RC:TxtMsg"
	err := rc.ChatRoomWhitelistRemove(
		[]string{txtmsg},
	)
	fmt.Println(err)
}
