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

func TestRongCloud_PushCustomObj(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)
	res, err := rc.PushCustomObj(PushCustomData{
		Platform: []string{"ios", "android"},
		Audience: struct {
			Tag      []string `json:"tag"`
			TagOr    []string `json:"tag_or"`
			Packages string   `json:"packageName"`
			TagItems []struct {
				Tags          []string `json:"tags"`
				IsNot         bool     `json:"isNot"`
				TagsOperator  string   `json:"tagsOperator"`
				ItemsOperator string   `json:"itemsOperator"`
			} `json:"tagItems,omitempty"`
			IsToAll bool `json:"is_to_all"`
		}{
			Tag:   []string{"女", "年轻"},
			TagOr: []string{"北京", "上海"},
			TagItems: []struct {
				Tags          []string `json:"tags"`
				IsNot         bool     `json:"isNot"`
				TagsOperator  string   `json:"tagsOperator"`
				ItemsOperator string   `json:"itemsOperator"`
			}{
				{
					Tags:          []string{"guangdong", "hunan"},
					IsNot:         false,
					TagsOperator:  "OR",
					ItemsOperator: "OR",
				},
				{
					Tags:          []string{"20200408"},
					IsNot:         true,
					TagsOperator:  "OR",
					ItemsOperator: "AND",
				},
				{
					Tags:          []string{"male", "female"},
					IsNot:         false,
					TagsOperator:  "OR",
					ItemsOperator: "AND",
				},
			},
			IsToAll: false,
		},
		Notification: struct {
			Title string `json:"title"`
			Alert string `json:"alert"`
			Ios   struct {
				Title            string      `json:"title,omitempty"`
				ContentAvailable int         `json:"contentAvailable"`
				Badge            int         `json:"badge,omitempty"`
				ThreadId         string      `json:"thread-id"`
				ApnsCollapseId   string      `json:"apns-collapse-id"`
				Category         string      `json:"category,omitempty"`
				RichMediaUri     string      `json:"richMediaUri,omitempty"`
				Extras           interface{} `json:"extras"`
			} `json:"ios"`
			Android struct {
				Hw struct {
					ChannelId  string `json:"channelId"`
					Importance string `json:"importance"`
					Image      string `json:"image"`
				} `json:"hw"`
				Mi struct {
					ChannelId    string `json:"channelId"`
					LargeIconUri string `json:"large_icon_uri"`
				} `json:"mi"`
				Oppo struct {
					ChannelId string `json:"channelId"`
				} `json:"oppo"`
				Vivo struct {
					Classification string `json:"classification"`
				} `json:"vivo"`
				Extras struct {
					Id   string `json:"id"`
					Name string `json:"name"`
				} `json:"extras"`
			} `json:"android"`
		}{
			Title: "标题",
			Alert: "this is a push",
			Ios: struct {
				Title            string      `json:"title,omitempty"`
				ContentAvailable int         `json:"contentAvailable"`
				Badge            int         `json:"badge,omitempty"`
				ThreadId         string      `json:"thread-id"`
				ApnsCollapseId   string      `json:"apns-collapse-id"`
				Category         string      `json:"category,omitempty"`
				RichMediaUri     string      `json:"richMediaUri,omitempty"`
				Extras           interface{} `json:"extras"`
			}{
				ThreadId:       "223",
				ApnsCollapseId: "111",
				Extras: struct {
					Id   string `json:"id"`
					Name string `json:"name"`
				}{
					Id:   "1",
					Name: "2",
				},
			},
			Android: struct {
				Hw struct {
					ChannelId  string `json:"channelId"`
					Importance string `json:"importance"`
					Image      string `json:"image"`
				} `json:"hw"`
				Mi struct {
					ChannelId    string `json:"channelId"`
					LargeIconUri string `json:"large_icon_uri"`
				} `json:"mi"`
				Oppo struct {
					ChannelId string `json:"channelId"`
				} `json:"oppo"`
				Vivo struct {
					Classification string `json:"classification"`
				} `json:"vivo"`
				Extras struct {
					Id   string `json:"id"`
					Name string `json:"name"`
				} `json:"extras"`
			}{
				Hw: struct {
					ChannelId  string `json:"channelId"`
					Importance string `json:"importance"`
					Image      string `json:"image"`
				}{
					ChannelId:  "NotificationKanong",
					Importance: "NORMAL",
					Image:      "https://example.com/image.png",
				},
				Mi: struct {
					ChannelId    string `json:"channelId"`
					LargeIconUri string `json:"large_icon_uri"`
				}{
					ChannelId:    "rongcloud_kanong",
					LargeIconUri: "https://example.com/image.png",
				},
				Oppo: struct {
					ChannelId string `json:"channelId"`
				}{
					ChannelId: "rc_notification_id",
				},
				Vivo: struct {
					Classification string `json:"classification"`
				}{
					Classification: "0",
				},
				Extras: struct {
					Id   string `json:"id"`
					Name string `json:"name"`
				}{
					Id:   "1",
					Name: "2",
				},
			},
		},
	})
	if err != nil {
		t.Errorf("push custom err:%v", err)
		return
	}
	t.Log("push suc res is:", res)
}

func TestRongCloud_PushCustomResObj(t *testing.T) {
	rc := NewRongCloud(
		os.Getenv("APP_KEY"),
		os.Getenv("APP_SECRET"),
	)
	str := `{
	"platform": ["ios", "android"],
	"audience": {
		"tag": [
			"女",
			"年轻"
		],
		"tag_or": [
			"北京",
			"上海"
		],
		"tagItems": [{
				"tags": [
					"guangdong",
					"hunan"
				],
				"isNot": false,
				"tagsOperator": "OR",
				"itemsOperator": "OR"
			},
			{
				"tags": [
					"20200408"
				],
				"isNot": true,
				"tagsOperator": "OR",
				"itemsOperator": "AND"
			},
			{
				"tags": [
					"male",
					"female"
				],
				"isNot": false,
				"tagsOperator": "OR",
				"itemsOperator": "OR"
			}
		],
		"userid": ["123","456"],
		"is_to_all": false
	},
	"notification": {
		"title": "标题",
		"alert": "this is a push",
		"ios": {
			"thread-id": "223",
			"apns-collapse-id": "111",
			"extras": {
				"id": "1",
				"name": "2"
			}
		},
		"android": {
			"hw": {
				"channelId": "NotificationKanong",
				"importance": "NORMAL",
				"image": "https://example.com/image.png"
			},
			"mi": {
				"channelId": "rongcloud_kanong",
				"large_icon_uri": "https://example.com/image.png"
			},
			"oppo": {
				"channelId": "rc_notification_id"
			},
			"vivo": {
				"classification": "0"
			},
			"extras": {
				"id": "1",
				"name": "2"
			}
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
	 "tagItems":[
				  {
					  "tags":[
						  "guangdong",
						  "hunan"
					  ],
					  "isNot":false,
					  "tagsOperator":"OR",
					  "itemsOperator":"OR"
				  },
				  {
					  "tags":[
						  "20200408"
					  ],
					  "isNot":true,
					  "tagsOperator":"OR",
					  "itemsOperator":"AND"
				  },
				  {
					  "tags":[
						  "male",
						  "female"
					  ],
					  "isNot":false,
					  "tagsOperator":"OR",
					  "itemsOperator":"OR"
				  }
			  ],
		  "userid":[
			  "123",
			  "456"
		 ],
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
