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
		FromUserID: "",
		Audience: Audience{
			IsToAll: true,
		},
		Notification: Notification{
			Alert: "this is a push",
			IOS: IOSBroadcast{
				Title: "iOS 平台显示标题",
				Alert: "iOS 平台显示内容",
				Extras: selfExtras{
					ID: 1,
				},
			},
			Android: AndroidBroadcast{
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

}
