package message

import (
	"testing"
	"fmt"
	"github.com/rongcloud/server-sdk-go/RCserverSDK"
)


func TestMessage_PrivateSend(t *testing.T) {

	fmt.Println("========TestMessage_PrivateSend========")
	private := Message{
		&RCserverSDK.RongCloud{
			AppKey: "ik1qhw016nsfp",	//appkey
			AppSecret: "S7eyGBASFSU42", //appsecret
		},
	}


	msg := MsgContent{
			Content:"hello",
			Extra: "helloExtra",
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

	fmt.Println(err)
	fmt.Println("========End========")
}

func TestMessage_PrivateRecall(t *testing.T) {

	fmt.Println("========TestPrivate_Recall========")
	private := Message{
		&RCserverSDK.RongCloud{
			AppKey: "ik1qhw016nsfp",	//appkey
			AppSecret: "S7eyGBASFSU42", //appsecret
		},
	}


	err := private.PrivateRecall(
		"u01",
		"u02",
		"B7CE-U880-31M6-D3EE",
		1543566558208,
		1,
	)

	fmt.Println(err)
	fmt.Println("========End========")
}

func TestMessage_PrivateSendTemplate(t *testing.T) {
	fmt.Println("========TestPrivate_SendTemplate========")
	private := Message{
		&RCserverSDK.RongCloud{
			AppKey: "ik1qhw016nsfp",	//appkey
			AppSecret: "S7eyGBASFSU42", //appsecret
		},
	}

	tpl1 := TemplateMsgContent{
		TargetID:"4kIvGJmETlYqDoVFgWdYdM",
		Data: map[string]string {
			"{name}": "小明",
			"{score}": "90",
		},
		PushContent:"{name} 你的成绩出来了",
	}


	tpl2 := TemplateMsgContent{
		TargetID:"GvYBoFJQTggripS_qoiVaA",
		Data: map[string]string {
			"{name}": "小红",
			"{score}": "95",
		},
		PushContent:"{name} 你的成绩出来了",
	}

	msg := MsgContent{
		Content:"{name}, 语文成绩 {score} 分",
		Extra: "helloExtra",
	}

	var tpl []TemplateMsgContent
	tpl = append(tpl, tpl1)
	tpl = append(tpl, tpl2)
	err := private.PrivateSendTemplate(
		"7Szq13MKRVortoknTAk7W8",
		"RC:TxtMsg",
		msg,
		tpl)

	fmt.Println(err)
	fmt.Println("========End========")
}