package RCServerSDK

import (
	"fmt"
	"testing"
)

func TestMessage_PrivateSend(t *testing.T) {

	private := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)

	msg := MsgContent{
		Content: "hello",
		Extra:   "helloExtra",
	}

	err := private.PrivateSend(
		"7Szq13MKRVortoknTAk7W8",
		"4kIvGJmETlYqDoVFgWdYdM",
		"RC:TxtMsg",
		msg,
		"",
		"",
		1,
		0,
		1,
		1,
		0,
	)

	if err != nil {
		t.Fatal(err)
	}
}

func TestMessage_PrivateRecall(t *testing.T) {

	private := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)

	err := private.PrivateRecall(
		"7Szq13MKRVortoknTAk7W8",
		"4kIvGJmETlYqDoVFgWdYdM",
		"B7CE-U880-31M6-D3EE",
		1543566558208,
		1,
	)

	if err != nil {
		t.Fatal(err)
	}
}

func TestMessage_PrivateSendTemplate(t *testing.T) {

	private := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)
	fmt.Println(private.RongCloudURI)
	tpl1 := TemplateMsgContent{
		TargetID: "4kIvGJmETlYqDoVFgWdYdM",
		Data: map[string]string{
			"{name}":  "小明",
			"{score}": "90",
		},
		PushContent: "{name} 你的成绩出来了",
	}

	tpl2 := TemplateMsgContent{
		TargetID: "GvYBoFJQTggripS_qoiVaA",
		Data: map[string]string{
			"{name}":  "小红",
			"{score}": "95",
		},
		PushContent: "{name} 你的成绩出来了",
	}

	msg := MsgContent{
		Content: "{name}, 语文成绩 {score} 分",
		Extra:   "helloExtra",
	}

	var tpl []TemplateMsgContent
	tpl = append(tpl, tpl1)
	tpl = append(tpl, tpl2)
	err := private.PrivateSendTemplate(
		"7Szq13MKRVortoknTAk7W8",
		"RC:TxtMsg",
		msg,
		tpl)

	if err != nil {
		t.Fatal(err)
	}
}

func TestRongCloud_GroupSend(t *testing.T) {

	private := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)

	msg := MsgContent{
		Content: "hello",
		Extra:   "helloExtra",
	}

	err := private.GroupSend(
		"7Szq13MKRVortoknTAk7W8",
		"CFtiYbXNQNYtSr7rzUfHco",
		"RC:TxtMsg",
		msg,
		"",
		"",
		1,
		0,
		1,
	)

	if err != nil {
		t.Fatal(err)
	}
}

func TestRongCloud_PrivateRecall(t *testing.T) {

	private := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)

	err := private.GroupRecall(
		"7Szq13MKRVortoknTAk7W8",
		"CFtiYbXNQNYtSr7rzUfHco",
		"B7CE-U880-31M6-D3EE",
		1543566558208,
		1,
	)

	if err != nil {
		t.Fatal(err)
	}
}

func TestRongCloud_GroupSendMention(t *testing.T) {

	private := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)

	msg := MentionMsgContent{
		Content:       "@user_2 hello",
		MentionedInfo: MentionedInfo{Type: 2, UserIDs: []string{"4kIvGJmETlYqDoVFgWdYdM"}, PushContent: "有人@你"},
	}
	err := private.GroupSendMention(
		"7Szq13MKRVortoknTAk7W8",
		"cYgiKZzRSUsrfrx6C3u_GI",
		"RC:TxtMsg",
		msg,
		"",
		"",
		1,
		0,
		1,
		1,
		0,
	)

	if err != nil {
		t.Fatal(err)
	}
}

func TestRongCloud_ChatroomSend(t *testing.T) {

	private := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)

	msg := MsgContent{
		Content: "hello",
		Extra:   "helloExtra",
	}

	err := private.ChatroomSend(
		"7Szq13MKRVortoknTAk7W8",
		"4kIvGJmETlYqDoVFgWdYdM",
		"RC:TxtMsg",
		msg,
	)

	if err != nil {
		t.Fatal(err)
	}

}

func TestRongCloud_ChatroomBroadcast(t *testing.T) {

	private := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)

	msg := MsgContent{
		Content: "hello",
		Extra:   "helloExtra",
	}

	err := private.ChatroomBroadcast(
		"7Szq13MKRVortoknTAk7W8",
		"RC:TxtMsg",
		msg,
	)
	if err != nil {
		t.Fatal(err)
	}
}

func TestRongCloud_SystemSend(t *testing.T) {

	private := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)

	msg := MsgContent{
		Content: "hello",
		Extra:   "helloExtra",
	}

	err := private.SystemSend(
		"7Szq13MKRVortoknTAk7W8",
		"4kIvGJmETlYqDoVFgWdYdM",
		"RC:TxtMsg",
		msg,
		"",
		"",
		0,
		1,
		1,
	)

	if err != nil {
		t.Fatal(err)
	}
}

func TestRongCloud_SystemBroadcast(t *testing.T) {

	private := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)

	msg := MsgContent{
		Content: "hello",
		Extra:   "helloExtra",
	}

	err := private.SystemBroadcast(
		"7Szq13MKRVortoknTAk7W8",
		"RC:TxtMsg",
		msg,
	)

	if err != nil {
		t.Fatal(err)
	}
}

func TestRongCloud_SystemSendTemplate(t *testing.T) {

	private := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)

	tpl1 := TemplateMsgContent{
		TargetID: "4kIvGJmETlYqDoVFgWdYdM",
		Data: map[string]string{
			"{name}":  "小明",
			"{score}": "90",
		},
		PushContent: "{name} 你的成绩出来了",
	}

	tpl2 := TemplateMsgContent{
		TargetID: "GvYBoFJQTggripS_qoiVaA",
		Data: map[string]string{
			"{name}":  "小红",
			"{score}": "95",
		},
		PushContent: "{name} 你的成绩出来了",
	}

	msg := MsgContent{
		Content: "{name}, 语文成绩 {score} 分",
		Extra:   "helloExtra",
	}

	var tpl []TemplateMsgContent
	tpl = append(tpl, tpl1)
	tpl = append(tpl, tpl2)
	err := private.SystemSendTemplate(
		"7Szq13MKRVortoknTAk7W8",
		"RC:TxtMsg",
		msg,
		tpl)

	if err != nil {
		t.Fatal(err)
	}
}

func TestRongCloud_HistoryGet(t *testing.T) {

	private := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)

	history, err := private.HistoryGet(
		"2018030210",
	)
	fmt.Println(history)
	if err != nil {
		t.Fatal(err)
	}
}

func TestRongCloud_HistoryRemove(t *testing.T) {

	private := NewRongCloud(
		"ik1qhw016nsfp",
		"S7eyGBASFSU42",
		nil,
	)

	err := private.HistoryRemove(
		"2018030210",
	)

	if err != nil {
		t.Fatal(err)
	}
}
