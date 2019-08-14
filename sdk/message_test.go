package sdk

import (
	"os"
	"testing"
)

func TestMessage_PrivateSend(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	msg := TXTMsg{
		Content: "hello",
		Extra:   "helloExtra",
	}

	err := rc.PrivateSend(
		"7Szq13MKRVortoknTAk7W8",
		[]string{"4kIvGJmETlYqDoVFgWdYdM"},
		"RC:TxtMsg",
		&msg,
		"",
		"",
		1,
		0,
		1,
		0,
		0,
	)
	t.Log(err)
}

func TestMessage_PrivateRecall(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	err := rc.PrivateRecall(
		"7Szq13MKRVortoknTAk7W8",
		"4kIvGJmETlYqDoVFgWdYdM",
		"B7CE-U880-31M6-D3EE",
		1543566558208,
	)
	t.Log(err)
}

func TestMessage_PrivateSendTemplate(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
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

	msg := TXTMsg{
		Content: "{name}, 语文成绩 {score} 分",
		Extra:   "helloExtra",
	}

	var tpl []TemplateMsgContent
	tpl = append(tpl, tpl1)
	tpl = append(tpl, tpl2)
	err := rc.PrivateSendTemplate(
		"7Szq13MKRVortoknTAk7W8",
		"RC:TxtMsg",
		msg,
		tpl)
	t.Log(err)
}

func TestRongCloud_GroupSend(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	msg := TXTMsg{
		Content: "hello",
		Extra:   "helloExtra",
	}

	err := rc.GroupSend(
		"7Szq13MKRVortoknTAk7W8",
		[]string{"CFtiYbXNQNYtSr7rzUfHco"},
		[]string{},
		"RC:TxtMsg",
		&msg,
		"",
		"",
		1,
		0,
		1,
	)
	t.Log(err)
}

func TestRongCloud_PrivateRecall(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	err := rc.GroupRecall(
		"7Szq13MKRVortoknTAk7W8",
		"CFtiYbXNQNYtSr7rzUfHco",
		"B7CE-U880-31M6-D3EE",
		1543566558208,
	)

	t.Log(err)
}

func TestRongCloud_GroupSendMention(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	msg := MentionMsgContent{
		Content:       "@user_2 hello",
		MentionedInfo: MentionedInfo{Type: 2, UserIDs: []string{"4kIvGJmETlYqDoVFgWdYdM"}, PushContent: "有人@你"},
	}
	err := rc.GroupSendMention(
		"7Szq13MKRVortoknTAk7W8",
		[]string{"cYgiKZzRSUsrfrx6C3u_GI"},
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
	t.Log(err)
}

func TestRongCloud_ChatRoomSend(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	msg := TXTMsg{
		Content: "hello",
		Extra:   "helloExtra",
	}

	err := rc.ChatRoomSend(
		"7Szq13MKRVortoknTAk7W8",
		[]string{"4kIvGJmETlYqDoVFgWdYdM"},
		"RC:TxtMsg",
		&msg,
	)
	t.Log(err)

}

func TestRongCloud_ChatroomBroadcast(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	msg := TXTMsg{
		Content: "hello",
		Extra:   "helloExtra",
	}

	err := rc.ChatRoomBroadcast(
		"7Szq13MKRVortoknTAk7W8",
		"RC:TxtMsg",
		&msg,
	)
	t.Log(err)
}

func TestRongCloud_SystemSend(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	msg := TXTMsg{
		Content: "hello",
		Extra:   "helloExtra",
	}

	err := rc.SystemSend(
		"7Szq13MKRVortoknTAk7W8",
		[]string{"4kIvGJmETlYqDoVFgWdYdM"},
		"RC:TxtMsg",
		&msg,
		"",
		"",
		0,
		1,
		1,
	)
	t.Log(err)
}

func TestRongCloud_SystemBroadcast(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	msg := TXTMsg{
		Content: "hello",
		Extra:   "helloExtra",
	}

	err := rc.SystemBroadcast(
		"7Szq13MKRVortoknTAk7W8",
		"RC:TxtMsg",
		&msg,
	)
	t.Log(err)
}

func TestRongCloud_SystemSendTemplate(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
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

	msg := TXTMsg{
		Content: "{name}, 语文成绩 {score} 分",
		Extra:   "helloExtra",
	}

	var tpl []TemplateMsgContent
	tpl = append(tpl, tpl1)
	tpl = append(tpl, tpl2)
	err := rc.SystemSendTemplate(
		"7Szq13MKRVortoknTAk7W8",
		"RC:TxtMsg",
		msg,
		tpl)
	t.Log(err)
}

func TestRongCloud_HistoryGet(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	history, err := rc.HistoryGet(
		"2018030210",
	)
	t.Log(err)
	t.Log(history)
}

func TestRongCloud_HistoryRemove(t *testing.T) {

	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)

	err := rc.HistoryRemove(
		"2018030210",
	)
	t.Log(err)
}
