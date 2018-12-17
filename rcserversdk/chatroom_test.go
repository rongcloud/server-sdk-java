package rcserversdk

import (
	"testing"
	"fmt"
	"encoding/json"
)

func TestRongCloud_ChatRoomCreate(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.ChatRoomCreate(
		"chrm01",
		"rcchrm01",
	)
	if err != nil{
		panic(err)
	}
}

func TestRongCloud_ChatRoomGet(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	rep, err := rc.ChatRoomGet(
		"chrm01",
		500,
		1,
	)
	if err != nil{
		panic(err)
	}
	fmt.Println(rep)

}

func TestRongCloud_ChatRoomIsExist(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	rep, err := rc.ChatRoomIsExist(
		"chrm01",
		[]string{"u01"},
		)
	if err != nil{
		panic(err)
	}
	fmt.Println(rep)
}

func TestRongCloud_ChatRoomDestroy(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.ChatRoomDestroy(
		"chrm01",
		)
	if err != nil{
		panic(err)
	}
}

func TestRongCloud_ChatRoomBanAdd(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.ChatRoomBanAdd(
		[]string{"u01"},
		30)
	if err != nil{
		panic(err)
	}
}

func TestRongCloud_ChatRoomBanGetList(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	rep, err := rc.ChatRoomBanGetList()
	if err != nil{
		panic(err)
	}
	fmt.Println(rep)
}

func TestRongCloud_ChatRoomBanRemove(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.ChatRoomBanRemove(
		[]string{"u01"},
		)
	if err != nil{
		panic(err)
	}
}

func TestRongCloud_ChatRoomBlockAdd(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.ChatRoomBlockAdd(
		"chrm01",
		[]string{"u01"},
		30,
		)
	if err != nil{
		panic(err)
	}
}

func TestRongCloud_ChatRoomBlockGetList(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	rep ,err := rc.ChatRoomBlockGetList(
		"chrm01",
		)
	if err != nil{
		panic(err)
	}
	fmt.Println(rep)
}

func TestRongCloud_ChatRoomBlockRemove(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.ChatRoomBlockRemove(
		"chrm01",
		[]string{"u01"},
	)
	if err != nil{
		panic(err)
	}
}

func TestRongCloud_ChatRoomDemotionAdd(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	msg := MsgContent{
		Content: "{name}, 语文成绩 {score} 分",
		Extra:   "helloExtra",
	}
	msgBytes, err := json.Marshal(msg)
	if err != nil {
		panic("struct to json false" + err.Error())
	}
	err = rc.ChatRoomDemotionAdd(
		[]string{
			string(msgBytes),
		},
		)
	if err != nil{
		panic(err)
	}
}

func TestRongCloud_ChatRoomDemotionGetList(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	rep, err := rc.ChatRoomDemotionGetList()
	if err != nil{
		panic(err)
	}
	fmt.Println(rep)
}

func TestRongCloud_ChatRoomDemotionRemove(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	msg := MsgContent{
		Content: "{name}, 语文成绩 {score} 分",
		Extra:   "helloExtra",
	}
	msgBytes, err := json.Marshal(msg)
	if err != nil {
		panic("struct to json false" + err.Error())
	}
	err = rc.ChatRoomDemotionRemove(
		[]string{
			string(msgBytes),
		},
	)
	if err != nil{
		panic(err)
	}
}

func TestRongCloud_ChatRoomDistributionStop(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.ChatRoomDistributionStop(
		"chrm01",
		)
	if err != nil {
		panic(err)
	}

}

func TestRongCloud_ChatRoomDistributionResume(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.ChatRoomDistributionResume(
		"chrm01",
	)
	if err != nil {
		panic(err)
	}
}

func TestRongCloud_ChatRoomGagAdd(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.ChatRoomGagAdd(
		"chrm01",
		[]string{"u01"},
		30,
		)
	if err != nil{
		panic(err)
	}
}

func TestRongCloud_ChatRoomGagGetList(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	rep, err := rc.ChatRoomGagGetList(
		"chrm01",
		)
	if err != nil{
		panic(err)
	}
	fmt.Println(rep)
}

func TestRongCloud_ChatRoomGagRemove(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.ChatRoomGagRemove(
		"chrm01",
		[]string{"u01"},
	)
	if err != nil{
		panic(err)
	}
}

func TestRongCloud_ChatRoomKeepAliveAdd(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.ChatRoomKeepAliveAdd(
		"chrm01",
		)
	if err != nil{
		panic(err)
	}
}

func TestRongCloud_ChatRoomKeepAliveGetList(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	rep, err := rc.ChatRoomKeepAliveGetList(
		"chrm01",
		)
	if err != nil{
		panic(err)
	}
	fmt.Println(rep)
}

func TestRongCloud_ChatRoomKeepAliveRemove(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.ChatRoomKeepAliveRemove(
		"chrm01",
		)
	if err != nil{
		panic(err)
	}
}

func TestRongCloud_ChatRoomUserWhitelistAdd(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.ChatRoomUserWhitelistAdd(
		"chrm01",
		[]string{"u01"},
		)
	if err != nil{
		panic(err)
	}
}

func TestRongCloud_ChatRoomUserWhitelistGetList(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	rep, err := rc.ChatRoomUserWhitelistGetList(
		"chrm01",
		)
	if err != nil{
		panic(err)
	}
	fmt.Println(rep)
}

func TestRongCloud_ChatRoomUserWhitelistRemove(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	err := rc.ChatRoomUserWhitelistRemove(
		"chrm01",
		[]string{"u01"},
		)
	if err != nil{
		panic(err)
	}
}

func TestRongCloud_ChatRoomWhitelistAdd(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	msg := MsgContent{
		Content: "{name}, 语文成绩 {score} 分",
		Extra:   "helloExtra",
	}
	msgBytes, err := json.Marshal(msg)
	if err != nil {
		panic("struct to json false" + err.Error())
	}
	err = rc.ChatRoomWhitelistAdd(
		[]string{string(msgBytes)},
	)
	if err != nil{
		panic(err)
	}
}

func TestRongCloud_ChatRoomWhitelistGetList(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	rep, err := rc.ChatRoomWhitelistGetList()
	if err != nil{
		panic(err)
	}
	fmt.Println(rep)
}

func TestRongCloud_ChatRoomWhitelistRemove(t *testing.T) {
	rc := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	msg := MsgContent{
		Content: "{name}, 语文成绩 {score} 分",
		Extra:   "helloExtra",
	}
	msgBytes, err := json.Marshal(msg)
	if err != nil {
		panic("struct to json false" + err.Error())
	}
	err = rc.ChatRoomWhitelistRemove(
		[]string{string(msgBytes)},
	)
	if err != nil{
		panic(err)
	}
}