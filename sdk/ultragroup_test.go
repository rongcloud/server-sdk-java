package sdk

import (
	"os"
	"testing"
)

func TestRongCloud_UltraGroupChannelGet(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	res, err := rc.UltraGroupChannelGet(
		"ug_m_gid_lw_1",
		1,
		1,
	)
	if err != nil {
		t.Errorf("err:%v", err)
		return
	}
	t.Logf("res is:%+v", res)
}

func TestRongCloud_UGGroupChannelGet(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	res, err := rc.UGGroupChannelGet(
		"ug_m_gid_lw_1",
		001,
		1,
	)
	if err != nil {
		t.Errorf("err:%v", err)
		return
	}
	t.Logf("res is:%v", string(res))
}

func TestRongCloud_UGChannelPrivateUserGetResObj(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	res, err := rc.UGChannelPrivateUserGetResObj(
		"ug_m_gid_lw_1",
		"channel001",
		"1",
		"1",
	)
	if err != nil {
		t.Errorf("err:%v", err)
		return
	}
	t.Logf("res is:%+v", res)
}

func TestRongCloud_UGChannelPrivateUserGet(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	res, err := rc.UGChannelPrivateUserGet(
		"ug_m_gid_lw_1",
		"channel001",
		"1",
		"1",
	)
	if err != nil {
		t.Errorf("err:%v", err)
		return
	}
	t.Logf("res is:%v", string(res))
}

func TestRongCloud_UGChannelPrivateUserDelResObj(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	res, err := rc.UGChannelPrivateUserDelResObj(
		"ug_m_gid_lw_1",
		"channel001",
		"0",
	)
	if err != nil {
		t.Errorf("err:%v", err)
		return
	}
	t.Logf("res is:%+v", res)
}

func TestRongCloud_UGChannelPrivateUserDel(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	res, err := rc.UGChannelPrivateUserDel(
		"ug_m_gid_lw_1",
		"channel001",
		"0",
	)
	if err != nil {
		t.Errorf("err:%v", err)
		return
	}
	t.Logf("res is:%v", string(res))
}

func TestRongCloud_UGChannelPrivateUserAddResObj(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	res, err := rc.UGChannelPrivateUserAddResObj(
		"ug_m_gid_lw_1",
		"channel001",
		"0",
	)
	if err != nil {
		t.Errorf("err:%v", err)
		return
	}
	t.Logf("res is:%+v", res)
}

func TestRongCloud_UGChannelPrivateUserAdd(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	res, err := rc.UGChannelPrivateUserAdd(
		"ug_m_gid_lw_1",
		"channel001",
		"0",
	)
	if err != nil {
		t.Errorf("err:%v", err)
		return
	}
	t.Logf("res is:%v", string(res))
}

func TestRongCloud_UGGroupChannelCreate(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)
	if err := rc.UltraGroupCreate("1", "ug_m_gid_lw_1", "ug_m_gid_lw_1"); err != nil {
		t.Errorf("UltraGroupCreate err:%v", err)
		return
	}

	res, err := rc.UGGroupChannelCreate(
		"ug_m_gid_lw_1",
		"channel001",
		"0",
	)
	if err != nil {
		t.Errorf("err:%v", err)
		return
	}
	t.Logf("res is:%v", string(res))
}

func TestRongCloud_UGGroupChannelChangeResObj(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)
	res, err := rc.UGGroupChannelChangeResObj(
		"ug_m_gid_lw_1",
		"channel001",
		"1",
	)
	if err != nil {
		t.Errorf("err:%v", err)
		return
	}
	t.Logf("res is:%+v", res)
}

func TestRongCloud_UGGroupChannelChange(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)
	res, err := rc.UGGroupChannelChange(
		"ug_m_gid_lw_1",
		"channel001",
		"1",
	)
	if err != nil {
		t.Errorf("err:%v", err)
		return
	}
	t.Logf("res is:%v", string(res))
}

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

func Test_UGMessageExpansionSet(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	m := map[string]string{}
	m["k1"] = "v1"
	m["k2"] = "v1"
	m["k3"] = "v1"

	err := rc.UGMessageExpansionSet(
		"testExp0309",
		"ltZ1InfrF",
		"BVET-SJMK-AT5B-ADFN",
		"cc",
		m,
	)

	t.Log(err)

	err = rc.UGMessageExpansionSet(
		"",
		"ltZ1InfrF",
		"BVET-SJMK-AT5B-ADFN",
		"cc",
		m,
	)

	t.Log(err)

	err = rc.UGMessageExpansionSet(
		"testExp0309",
		"",
		"BVET-SJMK-AT5B-ADFN",
		"cc",
		m,
	)

	t.Log(err)

	err = rc.UGMessageExpansionSet(
		"testExp0309",
		"ltZ1InfrF",
		"",
		"cc",
		m,
	)

	t.Log(err)

	err = rc.UGMessageExpansionSet(
		"testExp0309",
		"ltZ1InfrF",
		"BVET-SJMK-AT5B-ADFN",
		"cc",
		nil,
	)

	t.Log(err)

	err = rc.UGMessageExpansionSet(
		"testExp0309",
		"ltZ1InfrF",
		"BVET-SJMK-AT5B-ADFN",
		"",
		m,
	)

	t.Log(err)
}

func Test_UGMessageExpansionDelete(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	err := rc.UGMessageExpansionDelete(
		"testExp0309",
		"ltZ1InfrF",
		"BVET-SJMK-AT5B-ADFN",
		"cc",
		"k1", "k2",
	)
	t.Log(err)

	err = rc.UGMessageExpansionDelete(
		"",
		"ltZ1InfrF",
		"BVET-SJMK-AT5B-ADFN",
		"cc",
		"k1", "k2",
	)
	t.Log(err)

	err = rc.UGMessageExpansionDelete(
		"testExp0309",
		"",
		"BVET-SJMK-AT5B-ADFN",
		"cc",
		"k1", "k2",
	)
	t.Log(err)

	err = rc.UGMessageExpansionDelete(
		"testExp0309",
		"ltZ1InfrF",
		"",
		"cc",
		"k1", "k2",
	)
	t.Log(err)

	err = rc.UGMessageExpansionDelete(
		"testExp0309",
		"ltZ1InfrF",
		"BVET-SJMK-AT5B-ADFN",
		"cc",
		"",
	)
	t.Log(err)

	err = rc.UGMessageExpansionDelete(
		"testExp0309",
		"ltZ1InfrF",
		"BVET-SJMK-AT5B-ADFN",
		"",
		"k1", "k2",
	)
	t.Log(err)

}

func Test_UGMessageExpansionQuery(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	data, requestId := rc.UGMessageExpansionQuery(
		"testExp0309",
		"BVET-SJMK-AT5B-ADFN",
		"cc",
	)

	t.Log(data)
	t.Log(requestId)
}

func Test_UGMessagePublish(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	err := rc.UGMessagePublish("aa", "RC:TxtMsg", "{\"content\":\"1234455667788-0309-1-test\"}",
		"", "", "1", "", "0", "0", "", "{\"key1\":\"key1\"}",
		false, false, &PushExt{
			Title:                "you have a new message.",
			TemplateId:           "123456",
			ForceShowPushContent: 0,
			PushConfigs: []map[string]map[string]string{
				{
					"HW": {
						"channelId": "NotificationKanong",
					},
				},
				{
					"MI": {
						"channelId": "rongcloud_kanong",
					},
				},
				{
					"OPPO": {
						"channelId": "rc_notification_id",
					},
				},
				{
					"VIVO": {
						"classification": "0",
					},
				},
				{
					"APNs": {
						"thread-id":        "1",
						"apns-collapse-id": "1",
					},
				},
			},
		}, "testExp0309")

	if err != nil {
		t.Error(err)
		return
	}
	t.Log("success")
}

func Test_UGMemberExists(t *testing.T) {
	key := os.Getenv("APP_KEY")
	secret := os.Getenv("APP_SECRET")

	rc := NewRongCloud(key, secret)
	exists, err := rc.UGMemberExists("rongcloud_group01", "u01")
	if err != nil {
		t.Errorf("Failed to query member exists. err: %v", err)
		return
	}

	t.Logf("Exists: %t", exists)
}
