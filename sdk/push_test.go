package sdk

import (
	"encoding/json"
	"os"
	"testing"
)

type selfExtras struct {
	ID int `json:"id"`
}

// ToJSON 实现 Extras Interface
func (s selfExtras) ToJSON() ([]byte, error) {
	return json.Marshal(s)
}

func TestRongCloud_PushCustomResObj(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)
	str := `{
  "platform":["ios","android"],
  "audience":{
    "tag":["女","年轻"],
    "tag_or":["北京","上海"],
    "is_to_all":false
  },
  "notification":{
    "title":"标题",
    "alert":"this is a push",
    "ios":
      {
        "thread-id":"223",
        "apns-collapse-id":"111",
        "extras": {"id": "1","name": "2"}
      },
    "android": {
        "hw":{
            "channelId":"NotificationKanong",
            "importance": "NORMAL",
            "image":"https://example.com/image.png"
        },
        "mi":{
            "channelId":"rongcloud_kanong",
            "large_icon_uri":"https://example.com/image.png"
        },
        "oppo":{
            "channelId":"rc_notification_id"
        },
        "vivo":{
            "classification":"0"
        },
        "extras": {"id": "1","name": "2"}
      }
  }
}`
	res, err := rc.PushCustomResObj([]byte(str))
	if err != nil {
		t.Errorf("push custom err:%v", err)
		return
	}
	t.Log("push suc res is:", res)
}

func TestRongCloud_PushCustom(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)
	str := `{
  "platform":["ios","android"],
  "audience":{
    "tag":["女","年轻"],
    "tag_or":["北京","上海"],
    "is_to_all":false
  },
  "notification":{
    "title":"标题",
    "alert":"this is a push",
    "ios":
      {
        "thread-id":"223",
        "apns-collapse-id":"111",
        "extras": {"id": "1","name": "2"}
      },
    "android": {
        "hw":{
            "channelId":"NotificationKanong",
            "importance": "NORMAL",
            "image":"https://example.com/image.png"
        },
        "mi":{
            "channelId":"rongcloud_kanong",
            "large_icon_uri":"https://example.com/image.png"
        },
        "oppo":{
            "channelId":"rc_notification_id"
        },
        "vivo":{
            "classification":"0"
        },
        "extras": {"id": "1","name": "2"}
      }
  }
}`
	res, err := rc.PushCustom([]byte(str))
	if err != nil {
		t.Errorf("push custom err:%v", err)
		return
	}
	t.Log("push suc res is:", res)
}

func TestRongCloud_PushSend(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)
	push := Push{
		PlatForm: []PlatForm{
			IOSPlatForm,
			AndroidPlatForm,
		},
		Audience: Audience{
			IsToAll: true,
		},
		Notification: Notification{
			Alert: "this is a push",
			IOS: IOSPush{
				Title: "iOS 平台显示标题",
				Alert: "iOS 平台显示内容",
				Extras: selfExtras{
					ID: 1,
				},
			},
			Android: AndroidPush{
				Alert: "Android 平台显示内容",
				Extras: selfExtras{
					ID: 1,
				},
			},
		},
	}
	p, err := rc.PushSend(push)
	if err != nil {
		t.Log(err)
	} else {
		t.Log(p.Code)
		t.Log(p.ID)
	}

	msg := TXTMsg{
		Content: "hello",
		Extra:   "helloExtra",
	}
	msgr, err := msg.ToString()
	if err != nil {
		t.Fatal(err)
	}
	broadcast := Broadcast{
		PlatForm: []PlatForm{
			IOSPlatForm,
			AndroidPlatForm,
		},
		FromUserID: "u01",
		Audience: Audience{
			IsToAll: true,
		},
		Message: Message{
			Content:    msgr,
			ObjectName: "RC:TxtMsg",
		},
	}
	p, err = rc.PushSend(broadcast)
	if err != nil {
		t.Log(err)
	} else {
		t.Log(p.Code)
		t.Log(p.ID)
	}
}
