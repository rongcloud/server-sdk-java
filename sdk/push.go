package sdk

import (
	"encoding/json"
	"errors"
	"fmt"
	"net/http"
	"strings"
	"time"

	"github.com/astaxie/beego/httplib"
)

// PlatForm Broadcast type
type PlatForm string

const (
	// IOSPlatForm Broadcast
	IOSPlatForm PlatForm = "ios"
	// AndroidPlatForm Broadcast
	AndroidPlatForm PlatForm = "android"
)

// Extras Implement the Extras interface in IOSBroadcast and AndroidBroadcast
type Extras interface {
	ToJSON() ([]byte, error)
}

// Sender Broadcast push interface
type Sender interface {
	sender()
}

// PushResult Return value of the Send function
type PushResult struct {
	*CodeResult
	ID         string `json:"id"`
	MessageUID string `json:"messageUID,omitempty"`
}

// Broadcast Broadcast message
type Broadcast struct {
	PlatForm     []PlatForm   `json:"platform"`               // Target operating system, at least one of iOS or Android must be specified. If you need to push messages to both systems, both must be filled in. (Required)
	FromUserID   string       `json:"fromuserid"`             // Sender's user ID. (Required)
	Message      Message      `json:"message"`                // Broadcast message. (Required)
	Audience     Audience     `json:"audience"`               // Push conditions, including: tag, userid, packageName, is_to_all. (Required)
	Notification Notification `json:"notification,omitempty"` // Push content by operating system type. If both iOS and Android are set in platform, but only iOS content is set in notification, Android will use the initial alert content. (Optional)
}

// Push Broadcast push
type Push struct {
	PlatForm     []PlatForm   `json:"platform"`     // Target operating systems. At least one of iOS or Android must be specified. If you need to push messages to both systems, both must be filled. (Required)
	Audience     Audience     `json:"audience"`     // Push conditions, including: tag, userid, packageName, is_to_all. (Required)
	Notification Notification `json:"notification"` // Push message content by operating system type. If platform is set to push messages to both iOS and Android, but notification only sets iOS push content, the Android push content will default to the initial alert setting. (Required)
}

// Message Broadcast message content
type Message struct {
	Content              string `json:"content"`              // RongCloud's built-in messages. Please refer to the RCMsg Interface. For custom messages, implement the RCMsg Interface and convert it to a String for passing.
	ObjectName           string `json:"objectName"`           // RongCloud's built-in messages or custom messages.
	DisableUpdateLastMsg bool   `json:"disableUpdateLastMsg"` // Indicates whether to disable updating the last message. Default is false.
}

// IOSPush Settings for iOS platform push and additional information.
type IOSPush struct {
	Title            string `json:"title,omitempty"`            // The push title displayed in the notification bar, specific to the iOS platform, supported on iOS 8.2 and above. Parameters are set under the ios node. Refer to the "Set iOS Push Title Request Example" for details. (Optional)
	ContentAvailable int    `json:"contentAvailable,omitempty"` // For the iOS platform, silent push is a push method introduced after iOS7. It allows the app to run a piece of code in the background upon receiving the notification and execute it immediately. 1 indicates enabled, 0 indicates disabled, default is 0. (Optional)
	Alert            string `json:"alert,omitempty"`            // Push message content for iOS or Android platforms. If specified, the default push message content is overridden and cannot be empty. (Optional)
	Extras           Extras `json:"extras,omitempty"`           // Extras should implement the Extras Interface. Additional information for iOS or Android platforms. Developers can parse this in the App as needed. (Optional)
	Badge            int    `json:"badge,omitempty"`            // App badge number, specific to the iOS platform. If not specified, the badge number remains unchanged. If 0 or negative, the badge number is cleared. Otherwise, the specified number sets the badge number, with a maximum of 9999. Parameters are set under the ios node. Refer to the "Set iOS Badge Number HTTP Request Example" for details. (Optional)
	Category         string `json:"category,omitempty"`         // iOS rich push type defined by the developer, parsed in the App, used with richMediaUri. (Optional)
	RichMediaURI     string `json:"richMediaUri,omitempty"`     // URL for iOS rich push content, used with category. (Optional)
	ThreadId         string `json:"threadId,omitempty"`         // ThreadId for iOS platform notification grouping. Notifications with the same thread-id are grouped together, and groups with more than 5 notifications are collapsed. (Optional)
	ApnsCollapseId   string `json:"apns-collapse-id,omitempty"` // ApnsCollapseId for the iOS platform, supported from iOS10. Messages with the same ID are merged into one. (Optional)
}

// AndroidPush Settings for Android platform push and additional information.
type AndroidPush struct {
	Alert          string `json:"alert,omitempty"`          // Push message content for iOS or Android platforms. If specified, the default push message content is overridden and cannot be empty. (Optional)
	Extras         Extras `json:"extras,omitempty"`         // Extras should implement the Extras Interface. Additional information for iOS or Android platforms. Developers can parse this in the App as needed. PushNotification does not have this field. (Optional)
	ChannelId      string `json:"channelId,omitempty"`      // ChannelId for vendor-specific push channels. Currently supported vendors include: "MI" for Xiaomi, "HW" for Huawei, "OPPO". (Optional)
	Importance     string `json:"importance,omitempty"`     // Importance for Huawei notification priority, values: NORMAL, LOW, default is NORMAL for important messages. (Optional)
	Image          string `json:"image,omitempty"`          // Image URL for custom notification bar icon on the right for Huawei push. If not set, the icon is not displayed. URL must use HTTPS protocol, e.g., https://example.com/image.png. Icon file must be less than 512KB, recommended size: 40dp x 40dp, corner radius: 8dp. Icons larger than the recommended size may be compressed or not fully displayed. (Optional)
	LargeIconUri   string `json:"large_icon_uri,omitempty"` // LargeIconUri for custom notification bar icon on the right for Xiaomi push. If not set, the icon is not displayed. Domestic version only supports MIUI12 and above; international version supports. Image requirements: 120 * 120px, png or jpg format. (Optional)
	Classification string `json:"classification,omitempty"` // Classification for vivo push channel type. 0 for operational messages, 1 for system messages, default is the push channel type set in the developer backend for vivo push. (Optional)
}

// Notification Push message content by operating system type. If platform is set to push messages to both iOS and Android, but notification only sets iOS push content, the Android push content will default to the initial alert setting. (Required)
type Notification struct {
	Alert   string      `json:"alert"`             // Default push message content. If alert is specified for iOS or Android, the push content will follow the alert for the respective platform. (Required)
	IOS     IOSPush     `json:"ios,omitempty"`     // Settings for iOS platform push and additional information.
	Android AndroidPush `json:"android,omitempty"` // Settings for Android platform push and additional information.
}

// Audience Push conditions.
type Audience struct {
	Tag         []string `json:"tag,omitempty"`    // User tags. Up to 20 tags can be sent at a time, with an AND relationship between tags. Invalid if is_to_all is true. (Optional)
	TagOr       []string `json:"tag_or,omitempty"` // User tags. Up to 20 tags can be sent at a time, with an OR relationship between tags. Invalid if is_to_all is true. tag_or can coexist with tag. (Optional)
	UserID      []string `json:"userid,omitempty"` // User IDs. Up to 1000 users can be sent at a time. If both tag and userid conditions exist, userid takes precedence. If userid is specified, the platform parameter is invalid. Invalid if is_to_all is true. (Optional)
	IsToAll     bool     `json:"is_to_all"`        // Whether to push to all users. false means push based on tag, tag_or, or userid conditions. true means push to all users, and tag, tag_or, and userid conditions are invalid. (Required)
	PackageName string   `json:"packageName"`      // Application package name. Invalid if is_to_all is true. If coexisting with tag or tag_or, it has an AND relationship, pushing to users who meet all conditions. If coexisting with userid, userid takes precedence. (Optional)
}

type PushNotification struct {
	Title       string                 `json:"title,omitempty"`   // Title The title displayed in the notification bar, with a maximum of 50 characters.
	PushContent string                 `json:"pushContent"`       // PushContent The content of the push notification.
	IOS         IOSPush                `json:"ios,omitempty"`     // IOS Settings for push notifications and additional information on the iOS platform. For details, refer to the ios structure description.
	Android     map[string]interface{} `json:"android,omitempty"` // Android Settings for push notifications and additional information on the Android platform. For details, refer to the android structure description.
}

type PushCustomData struct {
	Platform []string `json:"platform"`
	Audience struct {
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
	} `json:"audience"`
	Notification struct {
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
	} `json:"notification"`
}

// PushCustomObj : The return value of the PushCustomResObj method
type PushCustomObj struct {
	// Return code, 200 indicates success.
	Code int `json:"code"`

	// Unique identifier for the push notification.
	Id string `json:"id"`
}

// PushCustomObj : Push-only Notification for all users /push/custom.json
// *
//
//	@param: p：Refer to the parameters passed in the TestRongCloud_PushCustom unit test
//	@param: platform []string Target operating systems, at least one of iOS or Android must be specified. If you need to send push notifications to both systems, all must be filled in.
//	@param: audience  string Push conditions, including: tag, tag_or, packageName, is_to_all.
//	@param: notification string Push notification content by operating system type. If both iOS and Android are set in the platform, but only iOS push content is set in the notification, the Android push content will be the initial alert setting. For details, refer to the notification structure description.
//
// Can be constructed as the above map or struct and serialized into JSON before calling PushCustom
// response: Return structure
// Documentation: https://doc.rongcloud.cn/imserver/server/v1/push-plus#push_custom
// *//
func (rc *RongCloud) PushCustomObj(data PushCustomData) (PushCustomObj, error) {
	var (
		err    error
		result = PushCustomObj{}
	)
	body, err := json.Marshal(data)
	if err != nil {
		return result, err
	}
	req := httplib.Post(rc.rongCloudURI + "/push/custom.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Body(body)
	req.Header("Content-Type", "application/json")
	code, err := rc.do(req)
	if err != nil {
		fmt.Println("do err", err)
		return result, err
	}
	if err := json.Unmarshal(code, &result); err != nil {
		fmt.Println("unmarshal err", err)
		return result, err
	}
	return result, nil
}

// PushCustomResObj: Broadcast to Users with Push-only Notification /push/custom.json
/*
  @param: p: Refer to the parameters passed in the TestRongCloud_PushCustom unit test
  @param: platform []string Target operating systems, at least one of iOS or Android must be specified. If messages need to be pushed to both systems, both must be filled.
  @param: audience string Push conditions, including: tag, tag_or, packageName, is_to_all.
  @param: notification string Push notification content by operating system type. If both iOS and Android are set in platform but only iOS content is set in notification, the Android push content will default to the initial alert setting. Refer to the notification structure for details.
  You can construct the above as a map or struct for JSON serialization before calling PushCustom.
  Request follows the PushCustomData struct:
  Response: Returns the struct
  Documentation: https://doc.rongcloud.cn/imserver/server/v1/push-plus#push_custom
*/
func (rc *RongCloud) PushCustomResObj(p []byte) (PushCustomObj, error) {
	var (
		err    error
		result = PushCustomObj{}
	)
	url := rc.rongCloudURI + "/push/custom.json"
	fmt.Println(url)
	req := httplib.Post(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Body(p)
	req.Header("Content-Type", "application/json")
	code, err := rc.do(req)
	if err != nil {
		return result, err
	}
	if err := json.Unmarshal(code, &result); err != nil {
		return result, err
	}
	return result, err
}

// PushCustom: Broadcast to Users with Push-only Notification /push/custom.json
/*
  @param: p: Refer to the parameters passed in the TestRongCloud_PushCustom unit test
  @param: platform []string Target operating systems, at least one of iOS or Android must be specified. If messages need to be pushed to both systems, both must be filled.
  @param: audience string Push conditions, including: tag, tag_or, packageName, is_to_all.
  @param: notification string Push notification content by operating system type. If both iOS and Android are set in platform but only iOS content is set in notification, the Android push content will default to the initial alert setting. Refer to the notification structure for details.
  Request follows the PushCustomData struct:
  You can construct the above as a map or struct for JSON serialization before calling PushCustom.
  Response: Returns a byte array
  Documentation: https://doc.rongcloud.cn/imserver/server/v1/push-plus#push_custom
*/
func (rc *RongCloud) PushCustom(p []byte) ([]byte, error) {
	var err error
	req := httplib.Post(rc.rongCloudURI + "/push/custom.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Body(p)
	req.Header("Content-Type", "application/json")
	code, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return code, err
}

// PushUser Sends a Push-only Notification to specified users in the app. A Push-only Notification will be delivered to the user regardless of whether they are using the app. The notification will only appear in the notification bar and will not carry message content. After logging into the app, the user will not see this content in the chat UI, and it will not be stored in the local database.
func (rc *RongCloud) PushUser(notification *PushNotification, users ...string) error {
	if notification == nil {
		return errors.New("Invalid notification")
	}

	if userLens := len(users); userLens > 100 || userLens <= 0 {
		return errors.New("Invalid users")
	}

	if notification.Android != nil {
		android := make(map[string]interface{})
		for key, val := range notification.Android {
			k := strings.ToLower(key)
			android[k] = val
		}
		notification.Android = android
	}

	var err error

	req := httplib.Post(rc.rongCloudURI + "/push/user." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req, err = req.JSONBody(map[string]interface{}{
		"userIds":      users,
		"notification": notification,
	})
	if err != nil {
		return err
	}

	resp, err := rc.do(req)
	if err != nil {
		return err
	}

	data := make(map[string]interface{})
	if err = json.Unmarshal(resp, &data); err != nil {
		return err
	}

	code, ok := data["code"]
	if !ok {
		return errors.New("Failed to request")
	}

	if int(code.(float64)) != http.StatusOK {
		return fmt.Errorf("Response error. code: %d", int(code.(float64)))
	}

	return nil
}

// PushSend This method has the same sending mechanism as the /message/broadcast broadcast message method, but allows for more sending conditions. This feature is free to use in the development environment. In the production environment, you need to enable the IM Enterprise Plan in the advanced feature settings of the developer console and then enable it in the "Broadcast Messages and Push Notifications" section before you can use it.
// The combined frequency of push notifications and broadcast messages is limited to 2 times per hour and 3 times per day. If you need to adjust the sending frequency, please contact us.
/*
*@param  Push: The push notification body.
*
*@return PushResult, error
 */
func (rc *RongCloud) PushSend(sender Sender) (PushResult, error) {
	req := httplib.Post(rc.rongCloudURI + "/push." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req, err := req.JSONBody(sender)
	if err != nil {
		rc.urlError(err)
		return PushResult{}, err
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return PushResult{}, err
	}
	var pushResult PushResult
	if err := json.Unmarshal(resp, &pushResult); err != nil {
		return PushResult{}, err
	}
	return pushResult, nil
}

func (p Push) sender() {

}

func (b Broadcast) sender() {

}
