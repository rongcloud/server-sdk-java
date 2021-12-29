package sdk

import (
	"os"
	"testing"
)

func Test_UGGroupUpdate(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	err, requestId := rc.UGGroupCreate(
		"u02",
		"rongcloud_group01",
		"super",
	)

	t.Log(err)
	t.Log(requestId)
}

func Test_UGQueryUserGroups(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	groups, err, requestId := rc.UGQueryUserGroups("user001", 1, 20)
	t.Log(requestId)
	if err == nil {
		t.Log(len(groups))
		for _, group := range groups {
			t.Log(group.GroupId, " - ", group.GroupName)
		}
	} else {
		t.Log(err)
	}
}

func Test_UGGroupSend(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	rcmsg := TXTMsg{
		Content: "hello",
		Extra:   "helloExtra",
	}

	content, err := rcmsg.ToString()
	if err != nil {
		t.Log(err)
		return
	}

	var ugmsg UGMessage
	ugmsg.FromUserId = "test1"
	ugmsg.ToGroupIds = []string{"group1"}
	ugmsg.ObjectName = "RC:TxtMsg"
	ugmsg.Content = content
	ugmsg.StoreFlag = true

	err, requestId := rc.UGGroupSend(ugmsg)

	t.Log(err)
	t.Log(requestId)

}
