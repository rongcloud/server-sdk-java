package sdk

import (
	"os"
	"testing"
)

func TestRongCloud_GroupRemarksGetResObj(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	if res, err := rc.GroupRemarksGetResObj(
		"u02",
		"1",
	); err != nil {
		t.Error(err)
		return
	} else {
		t.Logf(" suc :%+v", res)
	}
}

func TestRongCloud_GroupRemarksGet(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	if res, err := rc.GroupRemarksGet(
		"u02",
		"1",
	); err != nil {
		t.Error(err)
		return
	} else {
		t.Log(" suc", string(res))
	}
}

func TestRongCloud_GroupRemarksDel(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	if err := rc.GroupRemarksDel(
		"u02",
		"1",
	); err != nil {
		t.Error(err)
		return
	}
	t.Log(" suc")
}

func TestRongCloud_GroupRemarksSet(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	if err := rc.GroupRemarksSet(
		"u02",
		"1",
		"1",
	); err != nil {
		t.Error(err)
		return
	}
	t.Log(" suc")
}

func TestRongCloud_GroupUserGagAdd(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	if err := rc.GroupUserGagAdd(
		"u02",
		"",
		"1",
	); err != nil {
		t.Error(err)
		return
	}
	t.Log(" suc")

}

func TestRongCloud_GroupUserQueryResObj(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	if res, err := rc.GroupUserQueryResObj(
		"u02",
	); err != nil {
		t.Error(err)
		return
	} else {
		t.Logf("group user query suc,res is: %+v", res)
	}
}

func TestRongCloud_GroupUserQuery(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	if res, err := rc.GroupUserQuery(
		"u02",
	); err != nil {
		t.Error(err)
		return
	} else {
		t.Log("group user query suc,res is:", string(res))
	}
}

func TestRongCloud_GroupCreate(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	resp, err := rc.GroupCreate(
		"u02",
		"rongcloud_group01",
		[]string{"u01", "u02"},
	)

	t.Log(err)
	t.Log(resp)
}

func TestRongCloud_GroupCreateWithMessageOptions(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	resp, err := rc.GroupCreate(
		"u02",
		"rongcloud_group01",
		[]string{"u01", "u02"},
		MessageOptions{
			BindNotifyMsg: true,
			FromUserId:    "u02",
			ObjectName:    "RC:TxtMsg",
			Content:       "{\"content\":\"test\"}",
			MaxMember:     10,
		},
	)

	t.Log(err)
	t.Log(resp)
}

func TestRongCloud_GroupGet(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	rep, err := rc.GroupGet(
		"u02",
	)
	t.Log(err)
	t.Log(rep)
}

func TestRongCloud_GroupJoin(t *testing.T) {
	t.Log(os.Getenv("AppKey"))
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	resp, err := rc.GroupJoin(
		"u01",
		"rongcloud_group01",
		[]string{"u03", "u04", "u05"},
	)
	t.Log(err)
	t.Log(resp)
	resp, err = rc.GroupJoin(
		"u01",
		"",
		[]string{"u03", "u04", "u05"},
	)
	t.Log(err)
	t.Log(resp)
}

func TestRongCloud_GroupJoinWithMessageOptions(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	resp, err := rc.GroupJoin(
		"u01",
		"",
		[]string{"u03", "u04", "u05"},
		MessageOptions{
			BindNotifyMsg: true,
			FromUserId:    "u02",
			ObjectName:    "RC:TxtMsg",
			Content:       "{\"content\":\"test\"}",
			MaxMember:     10,
		},
	)
	t.Log(err)
	t.Log(resp)
}

func TestRongCloud_GroupUpdate(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	resp, err := rc.GroupUpdate(
		"u01",
		"rongcloud_group02",
	)
	t.Log(err)
	t.Log(resp)
}

func TestRongCloud_GroupUpdateWithMessageOptions(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	resp, err := rc.GroupUpdate(
		"u01",
		"rongcloud_group02",
		MessageOptions{
			BindNotifyMsg: true,
			FromUserId:    "u02",
			ObjectName:    "RC:TxtMsg",
			Content:       "{\"content\":\"test\"}",
		},
	)
	t.Log(err)
	t.Log(resp)
}

func TestRongCloud_GroupQuit(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	resp, err := rc.GroupQuit(
		[]string{"u03", "u04"},
		"u01",
	)
	t.Log(err)
	t.Log(resp)
}

func TestRongCloud_GroupQuitWithMessageOptions(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	resp, err := rc.GroupQuit(
		[]string{"u03", "u04"},
		"u01",
		MessageOptions{
			BindNotifyMsg: true,
			FromUserId:    "u02",
			ObjectName:    "RC:TxtMsg",
			Content:       "{\"content\":\"test\"}",
		},
	)
	t.Log(err)
	t.Log(resp)
}

func TestRongCloud_GroupSync(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	group := Group{ID: "u02", Name: "rongcloud_group02"}
	groups := []Group{}
	groups = append(groups, group)
	err := rc.GroupSync(
		"u04",
		groups,
	)
	t.Log(err)
}

func TestRongCloud_GroupGagAdd(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	err := rc.GroupGagAdd(
		"u01",
		[]string{"u02"},
		300,
	)
	t.Log(err)
}

func TestRongCloud_GROUPGagList(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	rep, err := rc.GroupGagList(
		"u01",
	)
	t.Log(err)
	t.Log(rep)
}

func TestRongCloud_GroupGagremove(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	err := rc.GroupGagRemove(
		"u01",
		[]string{"u02"},
	)
	t.Log(err)
}

func TestRongCloud_GroupDismiss(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)

	resp, err := rc.GroupDismiss(
		"u01",
		"u01",
	)
	t.Log(err)
	t.Log(resp)
}

func TestRongCloud_GroupDismissWithMessageOptions(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	resp, err := rc.GroupDismiss(
		"u01",
		"u01",
		MessageOptions{
			BindNotifyMsg: true,
			FromUserId:    "u02",
			ObjectName:    "RC:TxtMsg",
			Content:       "{\"content\":\"test\"}",
		},
	)
	t.Log(err)
	t.Log(resp)
}

func TestRongCloud_GroupMuteAllMembersAdd(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	err := rc.GroupMuteAllMembersAdd(
		[]string{
			"group01",
			"group02",
		})
	t.Log(err)
}

func TestRongCloud_GroupMuteAllMembersList(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	group, err := rc.GroupMuteAllMembersGetList(
		[]string{
			"group01",
			"group02",
		}, 0, 0)
	t.Log(err)
	t.Log(group)
}

func TestRongCloud_GroupMuteAllMembersRemove(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	err := rc.GroupMuteAllMembersRemove(
		[]string{
			"group01",
			"group02",
		})
	t.Log(err)
}

func TestRongCloud_GroupGMuteMembersAdd(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	err := rc.GroupMuteMembersAdd(
		"gourp01",
		[]string{
			"u01",
			"u02",
		},
		30,
	)
	t.Log(err)
}

func TestRongCloud_GroupMuteMembersGetList(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	rep, err := rc.GroupMuteMembersGetList(
		"gourp01",
	)
	if err != nil {
		t.Error(err)
	}
	t.Log(rep)
}

func TestRongCloud_GroupMuteMembersRemove(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	err := rc.GroupMuteMembersRemove(
		"gourp01",
		[]string{
			"u01",
			"u02",
		},
	)
	t.Log(err)
}

func TestRongCloud_GroupMuteWhiteListUserAdd(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	err := rc.GroupMuteWhiteListUserAdd(
		"gourp01",
		[]string{
			"u01",
			"u02",
		},
	)
	t.Log(err)
}

func TestRongCloud_GroupMuteWhiteListUserGetList(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	rep, err := rc.GroupMuteWhiteListUserGetList(
		"gourp01",
	)
	t.Log(err)
	t.Log(rep)
}

func TestRongCloud_GroupMuteWhiteListUserRemove(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
		REGION_BJ,
	)
	err := rc.GroupMuteWhiteListUserRemove(
		"gourp01",
		[]string{
			"u01",
			"u02",
		},
	)
	t.Log(err)
}
