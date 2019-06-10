package sdk

import (
	"encoding/json"
	"fmt"
	"time"

	"github.com/astaxie/beego/httplib"
)

// PlatForm 广播类型
type PlatForm string

const (
	// IOSPlatForm 广播
	IOSPlatForm PlatForm = "ios"
	// AndroidPlatForm 广播
	AndroidPlatForm PlatForm = "android"
)

// Extras 请自行实现 IOSBroadcast AndroidBroadcast 中 Extras 接口
type Extras interface {
	ToJSON() ([]byte, error)
}

// Sender 广播推送接口
type Sender interface {
	sender()
}

// PushResult Send 函数返回
type PushResult struct {
	*CodeResult
	ID string `json:"id"`
}

// Broadcast 广播消息
type Broadcast struct {
	PlatForm     []PlatForm   `json:"platform"`               // 目标操作系统，iOS、Android 最少传递一个。如果需要给两个系统推送消息时，则需要全部填写。（必传）
	FromUserID   string       `json:"fromuserid"`             // 发送人用户 Id。 （必传）
	Message      Message      `json:"message"`                // 广播消息。（必传）
	Audience     Audience     `json:"audience"`               // 推送条件，包括：tag 、userid 、packageName 、 is_to_all。（必传）
	Notification Notification `json:"notification,omitempty"` // 按操作系统类型推送消息内容，如 platform 中设置了给 iOS 和 Android 系统推送消息，而在 notification 中只设置了 iOS 的推送内容，则 Android 的推送内容为最初 alert 设置的内容。（非必传）
}

// Push 广播推送
type Push struct {
	PlatForm     []PlatForm   `json:"platform"`     // 目标操作系统，iOS、Android 最少传递一个。如果需要给两个系统推送消息时，则需要全部填写。（必传）
	Audience     Audience     `json:"audience"`     // 推送条件，包括：tag 、userid 、packageName 、 is_to_all。（必传）
	Notification Notification `json:"notification"` // 按操作系统类型推送消息内容，如 platform 中设置了给 iOS 和 Android 系统推送消息，而在 notification 中只设置了 iOS 的推送内容，则 Android 的推送内容为最初 alert 设置的内容。（必传）
}

// Message 广播消息内容
type Message struct {
	Content    string `json:"content"`    // 融云的内置消息，请务必参考 RCMsg Interface 。自定义消息请，自行实现 RCMsg Interface 转换成 String 传入
	ObjectName string `json:"objectName"` // 融云的内置消息，自定义消息
}

// IOSPush 设置 iOS 平台下的推送及附加信息。
type IOSPush struct {
	Title            string `json:"title,omitempty"`            // 通知栏显示的推送标题，仅针对 iOS 平台，支持 iOS 8.2 及以上版本，参数在 ios 节点下设置，详细可参考“设置 iOS 推送标题请求示例”。（非必传）
	ContentAvailable int    `json:"contentAvailable,omitempty"` // 针对 iOS 平台，静默推送是 iOS7 之后推出的一种推送方式。 允许应用在收到通知后在后台运行一段代码，且能够马上执行。1 表示为开启，0 表示为关闭，默认为 0（非必传）
	Alert            string `json:"alert,omitempty"`            // iOS 或 Android 不同平台下的推送消息内容，传入后默认的推送消息内容失效，不能为空。（非必传）
	// Extras 请自行实现 Extras Interface
	Extras       Extras `json:"extras,omitempty"`       // iOS 或 Android 不同平台下的附加信息，如果开发者自己需要，可以自己在 App 端进行解析。（非必传）
	Badge        int    `json:"badge,omitempty"`        // 应用角标，仅针对 iOS 平台；不填时，表示不改变角标数；为 0 或负数时，表示 App 角标上的数字清零；否则传相应数字表示把角标数改为指定的数字，最大不超过 9999，参数在 ios 节点下设置，详细可参考“设置 iOS 角标数 HTTP 请求示例”。（非必传）
	Category     string `json:"category,omitempty"`     // iOS 富文本推送的类型开发者自已定义，自已在 App 端进行解析判断，与 richMediaUri 一起使用。（非必传）
	RichMediaURI string `json:"richMediaUri,omitempty"` // iOS 富文本推送内容的 URL，与 category 一起使用。（非必传）
}

// AndroidPush 设置 Android 平台下的推送及附加信息。
type AndroidPush struct {
	Alert string `json:"alert,omitempty"` // iOS 或 Android 不同平台下的推送消息内容，传入后默认的推送消息内容失效，不能为空。（非必传）
	// Extras 请自行实现 Extras Interface
	Extras Extras `json:"extras,omitempty"` // iOS 或 Android 不同平台下的附加信息，如果开发者自己需要，可以自己在 App 端进行解析。（非必传）
}

// Notification 按操作系统类型推送消息内容，如 platform 中设置了给 iOS 和 Android 系统推送消息，而在 notification 中只设置了 iOS 的推送内容，则 Android 的推送内容为最初 alert 设置的内容。（必传）
type Notification struct {
	Alert   string      `json:"alert"`             // 默认推送消息内容，如填写了 iOS 或 Android 下的 alert 时，则推送内容以对应平台系统的 alert 为准。（必传）
	IOS     IOSPush     `json:"ios,omitempty"`     // 设置 iOS 平台下的推送及附加信息。
	Android AndroidPush `json:"android,omitempty"` // 设置 Android 平台下的推送及附加信息。
}

// Audience 推送条件。
type Audience struct {
	Tag         []string `json:"tag,omitempty"`    // 用户标签，每次发送时最多发送 20 个标签，标签之间为 AND 的关系，is_to_all 为 true 时参数无效。（非必传）
	TagOr       []string `json:"tag_or,omitempty"` // 用户标签，每次发送时最多发送 20 个标签，标签之间为 OR 的关系，is_to_all 为 true 时参数无效，tag_or 同 tag 参数可以同时存在。（非必传）
	UserID      []string `json:"userid,omitempty"` // 用户 Id，每次发送时最多发送 1000 个用户，如果 tag 和 userid 两个条件同时存在时，则以 userid 为准，如果 userid 有值时，则 platform 参数无效，is_to_all 为 true 时参数无效。（非必传）
	IsToAll     bool     `json:"is_to_all"`        // 是否全部推送，false 表示按 tag 、tag_or 或 userid 条件推送，true 表示向所有用户推送，tag、tag_or 和 userid 条件无效。（必传）
	PackageName string   `json:"packageName"`      // 应用包名，is_to_all 为 true 时，此参数无效。与 tag、tag_or 同时存在时为 And 的关系，向同时满足条件的用户推送。与 userid 条件同时存在时，以 userid 为准进行推送。（非必传）
}

// PushSend 此方法与 /message/broadcast 广播消息方法发送机制一样，可选择更多发送条件。 该功能开发环境下可免费使用。生产环境下，您需要在开发者后台高级功能设置中开通 IM 商用版后，在“广播消息和推送”中，开启后才能使用。
// 推送和广播消息合计每小时只能发送 2 次，每天最多发送 3 次。如需要调整发送频率.
/*
*@param  Push: 广播消息体 push。
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
	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return PushResult{}, err
	}
	var pushResult PushResult
	if err := json.Unmarshal(rep, &pushResult); err != nil {
		return PushResult{}, err
	}
	if pushResult.Code != 200 {
		fmt.Println(pushResult.ErrorMessage)
		return PushResult{}, RCErrorNew(pushResult.Code, pushResult.ErrorMessage)
	}
	return pushResult, nil
}

func (p Push) sender() {

}

func (b Broadcast) sender() {

}
