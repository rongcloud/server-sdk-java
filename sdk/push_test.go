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

func TestRongCloud_Send(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("RC_APP_ID"),
		os.Getenv("RC_APP_SECRET"),
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
	p, err := rc.Send(push)
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
	msgr, err := msg.toString()
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
	p, err = rc.Send(broadcast)
	if err != nil {
		t.Log(err)
	} else {
		t.Log(p.Code)
		t.Log(p.ID)
	}
}
