/*
 * @Descripttion:
 * @version:
 * @Author: ran.ding
 * @Date: 2019-09-02 18:29:55
 * @LastEditors: ran.ding
 * @LastEditTime: 2019-09-10 11:37:14
 */
package sdk

import (
	"encoding/json"
	"strconv"
	"time"

	"github.com/astaxie/beego/httplib"
)

// rcMsg rcMsg接口
type rcMsg interface {
	ToString() (string, error)
}

// MsgUserInfo 融云内置消息用户信息
type MsgUserInfo struct {
	ID    string `json:"id"`
	Name  string `json:"name"`
	Icon  string `json:"icon"`
	Extra string `json:"extra"`
}

// TXTMsg 消息
type TXTMsg struct {
	Content string      `json:"content"`
	User    MsgUserInfo `json:"user"`
	Extra   string      `json:"extra"`
}

// ImgMsg 消息
type ImgMsg struct {
	Content  string      `json:"content"`
	User     MsgUserInfo `json:"user"`
	ImageURI string      `json:"imageUri"`
	Extra    string      `json:"extra"`
}

// InfoNtf 消息
type InfoNtf struct {
	Message string      `json:"message"`
	User    MsgUserInfo `json:"user"`
	Extra   string      `json:"extra"`
}

// VCMsg 消息
type VCMsg struct {
	Content  string      `json:"content"`
	User     MsgUserInfo `json:"user"`
	Extra    string      `json:"extra"`
	Duration interface{} `json:"duration"`
}

// IMGTextMsg 消息
type IMGTextMsg struct {
	Title    string      `json:"title"`
	Content  string      `json:"content"`
	User     MsgUserInfo `json:"user"`
	Extra    string      `json:"extra"`
	Duration interface{} `json:"duration"`
	URL      string      `json:"url"`
}

// FileMsg 消息
type FileMsg struct {
	Name    string      `json:"name"`
	Size    string      `json:"size"`
	Type    string      `json:"type"`
	FileURL string      `json:"fileUrl"`
	User    MsgUserInfo `json:"user"`
}

// LBSMsg 消息
type LBSMsg struct {
	Content   string      `json:"content"`
	Extra     string      `json:"extra"`
	POI       string      `json:"poi"`
	Latitude  float64     `json:"latitude"`
	Longitude float64     `json:"longitude"`
	User      MsgUserInfo `json:"user"`
}

// ProfileNtf 消息
type ProfileNtf struct {
	Operation string      `json:"operation"`
	Data      string      `json:"data"`
	User      MsgUserInfo `json:"user"`
	Extra     string      `json:"extra"`
}

// CMDNtf 消息
type CMDNtf struct {
	Name string      `json:"operation"`
	Data string      `json:"data"`
	User MsgUserInfo `json:"user"`
}

// CMDMsg 消息
type CMDMsg struct {
	Name string      `json:"operation"`
	Data string      `json:"data"`
	User MsgUserInfo `json:"user"`
}

// ContactNtf 消息
type ContactNtf struct {
	Operation    string      `json:"operation"`
	SourceUserID string      `json:"sourceUserId"`
	TargetUserID string      `json:"targetUserId"`
	Message      string      `json:"message"`
	Extra        string      `json:"extra"`
	User         MsgUserInfo `json:"user"`
}

// GrpNtf 消息
type GrpNtf struct {
	OperatorUserID string      `json:"operatorUserId"`
	Operation      string      `json:"operation"`
	Data           string      `json:"data"`
	Message        string      `json:"message"`
	Extra          string      `json:"extra"`
	User           MsgUserInfo `json:"user"`
}

// DizNtf 消息
type DizNtf struct {
	Type      int         `json:"type"`
	Extension string      `json:"extension"`
	Operation string      `json:"operation"`
	User      MsgUserInfo `json:"user"`
}

// TemplateMsgContent 消息模版
type TemplateMsgContent struct {
	TargetID    string
	Data        map[string]string
	PushContent string
	PushData    string
}

// MentionedInfo Mentioned
type MentionedInfo struct {
	Type        int      `json:"type"`
	UserIDs     []string `json:"userIdList"`
	PushContent string   `json:"mentionedContent"`
}

// MentionMsgContent MentionMsgContent
type MentionMsgContent struct {
	Content       string        `json:"content"`
	MentionedInfo MentionedInfo `json:"mentionedinfo"`
}

// History History
type History struct {
	URL string `json:"url"`
}

// BroadcastRecallContent content of message broadcast recall
type BroadcastRecallContent struct {
	MessageId        string `json:"messageUId"`
	ConversationType int    `json:"conversationType"`
	IsAdmin          int    `json:"isAdmin"`
	IsDelete         int    `json:"isDelete"`
}

// ChatRoomKVNotiMessage 聊天室属性通知消息
type ChatRoomKVNotiMessage struct {
	Type  int    `json:"type"`
	Key   string `json:"string"`
	Value string `json:"string"`
	Extra string `json:"string"`
}

// ToString ChatRoomKVNotiMessage
func (msg *ChatRoomKVNotiMessage) ToString() (string, error) {
	bytes, err := json.Marshal(msg)
	if err != nil {
		return "", err
	}

	return string(bytes), nil
}

/**
 * @name: ToString
 * @test:
 * @msg: 将广播消息撤回接口需要的 content 结构体参数转换为 json
 * @param {type}
 * @return: string error
 */
func (content *BroadcastRecallContent) ToString() (string, error) {
	bytes, err := json.Marshal(content)
	if err != nil {
		return "", err
	}

	return string(bytes), nil
}

// ToString TXTMsg
func (msg *TXTMsg) ToString() (string, error) {
	bytes, err := json.Marshal(msg)
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// ToString ImgMsg
func (msg *ImgMsg) ToString() (string, error) {
	bytes, err := json.Marshal(msg)
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// ToString InfoNtf
func (msg *InfoNtf) ToString() (string, error) {
	bytes, err := json.Marshal(msg)
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// ToString VCMsg
func (msg *VCMsg) ToString() (string, error) {
	bytes, err := json.Marshal(msg)
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// ToString IMGTextMsg
func (msg *IMGTextMsg) ToString() (string, error) {
	bytes, err := json.Marshal(msg)
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// ToString FileMsg
func (msg *FileMsg) ToString() (string, error) {
	bytes, err := json.Marshal(msg)
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// ToString LBSMsg
func (msg *LBSMsg) ToString() (string, error) {
	bytes, err := json.Marshal(msg)
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// ToString ProfileNtf
func (msg *ProfileNtf) ToString() (string, error) {
	bytes, err := json.Marshal(msg)
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// ToString CMDNtf
func (msg *CMDNtf) ToString() (string, error) {
	bytes, err := json.Marshal(msg)
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// ToString CMDMsg
func (msg *CMDMsg) ToString() (string, error) {
	bytes, err := json.Marshal(msg)
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// ToString ContactNtf
func (msg *ContactNtf) ToString() (string, error) {
	bytes, err := json.Marshal(msg)
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// ToString GrpNtf
func (msg *GrpNtf) ToString() (string, error) {
	bytes, err := json.Marshal(msg)
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// ToString DizNtf
func (msg *DizNtf) ToString() (string, error) {
	bytes, err := json.Marshal(msg)
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// msgOptions is extra options for sending messages
type msgOptions struct {
	isMentioned      int
	contentAvailable int
	verifyBlacklist  int
	expansion        bool
	disablePush      bool
	pushExt          string
	pushContent      string
	pushData         string
}

// MsgOption 接口函数
type MsgOption func(*msgOptions)

// 是否为 @消息，0 表示为普通消息，1 表示为 @消息，默认为 0
func WithMsgMentioned(isMentioned int) MsgOption {
	return func(options *msgOptions) {
		options.isMentioned = isMentioned
	}
}

// 针对 iOS 平台，对 SDK 处于后台暂停状态时为静默推送
// iOS7 之后推出的一种推送方式。 允许应用在收到通知后在后台运行一段代码，且能够马上执行
// 1 表示为开启，0 表示为关闭，默认为 0
func WithMsgContentAvailable(contentAvailable int) MsgOption {
	return func(options *msgOptions) {
		options.contentAvailable = contentAvailable
	}
}

// 是否过滤发送人黑名单列表，0 为不过滤、 1 为过滤，默认为 0 不过滤。
func WithMsgVerifyBlacklist(verifyBlacklist int) MsgOption {
	return func(options *msgOptions) {
		options.verifyBlacklist = verifyBlacklist
	}
}

// 是否为可扩展消息，默认为 false，设为 true 时终端在收到该条消息后，可对该条消息设置扩展信息。暂不支持海外数据中心
func WithMsgExpansion(isExpansion bool) MsgOption {
	return func(options *msgOptions) {
		options.expansion = isExpansion
	}
}

// disablePush Boolean 是否为静默消息，默认为 false，设为 true 时终端用户离线情况下不会收到通知提醒。暂不支持海外数据中心
func WithMsgDisablePush(isDisablePush bool) MsgOption {
	return func(options *msgOptions) {
		options.disablePush = isDisablePush
	}
}

// pushExt String 推送通知属性设置，详细查看 pushExt 结构说明，pushExt 为 JSON 结构请求时需要做转义处理。
// disablePush 为 true 时此属性无效。暂不支持海外数据中心
func WithMsgPushExt(pushExt string) MsgOption {
	return func(options *msgOptions) {
		options.pushExt = pushExt
	}
}

// pushContent String 定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息。
// 如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。
func WithMsgPushContent(pushContent string) MsgOption {
	return func(options *msgOptions) {
		options.pushContent = pushContent
	}
}

// pushData String 针对 iOS 平台为 Push 通知时附加到 payload 中，客户端获取远程推送内容时为 appData，
// 同时融云默认携带了消息基本信息，客户端可通过 'rc' 属性获取，Android 客户端收到推送消息时对应字段名为 pushData。
func WithMsgPushData(pushData string) MsgOption {
	return func(options *msgOptions) {
		options.pushData = pushData
	}
}

// 修改默认值
func modifyMsgOptions(options []MsgOption) msgOptions {
	// 默认值
	defaultMsgOptions := msgOptions{
		isMentioned:      0,
		contentAvailable: 0,
		verifyBlacklist:  0,
		expansion:        false,
		disablePush:      false,
		pushExt:          "",
		pushContent:      "",
		pushData:         "",
	}

	// 修改默认值
	for _, ext := range options {
		ext(&defaultMsgOptions)
	}

	return defaultMsgOptions
}

/**
 * @name: MessageBroadcastRecall
 * @test:
 * @msg:广播消息撤回
 * @param string userId
 * @param string objectName
 * @param BroadcastRecallContent content
 * @return: error
 */
func (rc *RongCloud) MessageBroadcastRecall(userId string, objectName string, content BroadcastRecallContent) error {
	if userId == "" {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}

	if objectName == "" {
		return RCErrorNew(1002, "Paramer 'objectName' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/message/broadcast." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", userId)
	req.Param("objectName", objectName)

	msg, err := content.ToString()
	if err != nil {
		return err
	}
	req.Param("content", msg)

	_, err = rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

/**
 * @name: ChatRoomRecall
 * @test:
 * @msg: 消息撤回 - 聊天室
 * @param string userId
 * @param string targetId
 * @param string messageId
 * @param int sentTime
 * @return: error
 */
func (rc *RongCloud) ChatRoomRecall(userId string, targetId string, messageId string, sentTime int) error {
	if userId == "" {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}

	if targetId == "" {
		return RCErrorNew(1002, "Paramer 'targetId' is required")
	}

	if messageId == "" {
		return RCErrorNew(1002, "Paramer 'messageId' is required")
	}

	if sentTime == 0 {
		return RCErrorNew(1002, "Paramer 'sentTime' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/message/recall." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("fromUserId", userId)
	req.Param("conversationType", strconv.Itoa(4))
	req.Param("targetId", targetId)
	req.Param("messageUID", messageId)
	req.Param("sentTime", strconv.Itoa(sentTime))

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// PrivateSend 发送单聊消息方法（一个用户向多个用户发送消息，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人，如：一次发送 1000 人时，示为 1000 条消息。）
/*
 *@param  senderID:发送人用户 ID。
 *@param  targetID:接收用户 ID。可以实现向多人发送消息，每次上限为 1000 人。
 *@param  objectName:发送的消息类型。
 *@param  msg:消息内容。
 *@param  pushContent:定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息。如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。
 *@param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。
 *@param  count:针对 iOS 平台，Push 时用来控制未读消息显示数，只有在 toUserId 为一个用户 Id 的时候有效。
 *@param  verifyBlacklist:是否过滤发送人黑名单列表，0 表示为不过滤、 1 表示为过滤，默认为 0 不过滤。
 *@param  isPersisted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息。
 *@param  isIncludeSender:发送用户自已是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。
 *@param  contentAvailable:针对 iOS 平台，对 SDK 处于后台暂停状态时为静默推送，是 iOS7 之后推出的一种推送方式。 允许应用在收到通知后在后台运行一段代码，且能够马上执行，查看详细。1 表示为开启，0 表示为关闭，默认为 0。
 *@param  options 发送消息需要用的其他扩展参数
 *
 *@return error
 */
func (rc *RongCloud) PrivateSend(senderID string, targetID []string, objectName string, msg rcMsg,
	pushContent, pushData string, count, verifyBlacklist, isPersisted, isIncludeSender, contentAvailable int,
	options ...MsgOption) error {
	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if len(targetID) == 0 {
		return RCErrorNew(1002, "Paramer 'targetID' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/private/publish." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", senderID)
	for _, v := range targetID {
		req.Param("toUserId", v)
	}
	req.Param("objectName", objectName)

	msgr, err := msg.ToString()
	if err != nil {
		return err
	}
	req.Param("content", msgr)
	req.Param("pushData", pushData)
	req.Param("pushContent", pushContent)
	req.Param("count", strconv.Itoa(count))
	req.Param("verifyBlacklist", strconv.Itoa(verifyBlacklist))
	req.Param("isPersisted", strconv.Itoa(isPersisted))
	req.Param("contentAvailable", strconv.Itoa(contentAvailable))
	req.Param("isIncludeSender", strconv.Itoa(isIncludeSender))
	req.Param("expansion", strconv.FormatBool(extraOptins.expansion))
	req.Param("disablePush", strconv.FormatBool(extraOptins.disablePush))
	if !extraOptins.disablePush && extraOptins.pushExt != "" {
		req.Param("pushExt", extraOptins.pushExt)
	}

	_, err = rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// PrivateRecall 撤回单聊消息方法
/*
*
*@param  senderID:发送人用户 ID。
*@param  targetID:接收用户 ID。
*@param  uID:消息的唯一标识，各端 SDK 发送消息成功后会返回 uID。
*@param  sentTime:消息的发送时间，各端 SDK 发送消息成功后会返回 sentTime。
*
*@return error
 */
func (rc *RongCloud) PrivateRecall(senderID, targetID, uID string, sentTime int) error {
	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if targetID == "" {
		return RCErrorNew(1002, "Paramer 'targetID' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/message/recall." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", senderID)
	req.Param("targetId", targetID)
	req.Param("messageUID", uID)
	req.Param("sentTime", strconv.Itoa(sentTime))
	req.Param("conversationType", strconv.Itoa(1))

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// PrivateSendTemplate 向多个用户发送不同内容消息
/*
 *@param  senderID:发送人用户 ID。
 *@param  objectName:发送的消息类型。
 *@param  template:消息模版。
 *@param  content:数据内容，包含消息内容和接收者。
 *@param  options 发送消息需要用的其他扩展参数
 *
 *@return error
 */
func (rc *RongCloud) PrivateSendTemplate(senderID, objectName string, template TXTMsg, content []TemplateMsgContent,
	options ...MsgOption) error {
	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/private/publish_template." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	var toUserIDs, push, pushData []string
	var values []map[string]string

	for _, v := range content {
		if v.TargetID == "" {
			return RCErrorNew(1002, "Paramer 'TargetID' is required")
		}
		toUserIDs = append(toUserIDs, v.TargetID)
		values = append(values, v.Data)
		push = append(push, v.PushContent)
		pushData = append(pushData, v.PushData)
	}

	bytes, err := json.Marshal(template)
	if err != nil {
		return err
	}

	param := map[string]interface{}{}
	param["fromUserId"] = senderID
	param["objectName"] = objectName
	param["content"] = string(bytes)
	param["toUserId"] = toUserIDs
	param["values"] = values
	param["pushContent"] = push
	param["pushData"] = pushData
	param["verifyBlacklist"] = extraOptins.verifyBlacklist
	param["contentAvailable"] = extraOptins.contentAvailable
	param["disablePush"] = extraOptins.disablePush
	req, err = req.JSONBody(param)
	if err != nil {
		return err
	}

	_, err = rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// GroupSend 发送群组消息方法（以一个用户身份向群组发送消息，单条消息最大 128k.每秒钟最多发送 20 条消息，每次最多向 3 个群组发送，如：一次向 3 个群组发送消息，示为 3 条消息。）
/*
 *@param  senderID:发送人用户 ID 。
 *@param  targetID:接收群ID.
 *@param  objectName:消息类型。
 *@param  userID:群定向消群定向消息功能，向群中指定的一个或多个用户发送消息，群中其他用户无法收到该消息，当 targetID 为一个群组时此参数有效。注：如果开通了“单群聊消息云存储”功能，群定向消息不会存储到云端，向群中部分用户发送消息阅读状态回执时可使用此功能。（可选）
 *@param  msg:发送消息内容
 *@param  pushContent:定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息. 如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。
 *@param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。
 *@param  isPersisted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息。
 *@param  isIncludeSender:发送用户自已是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。
 *@param  options 发送消息需要用的其他扩展参数
 *
 *@return error
 */
func (rc *RongCloud) GroupSend(senderID string, targetID, userID []string, objectName string, msg rcMsg,
	pushContent string, pushData string, isPersisted, isIncludeSender int,
	options ...MsgOption) error {
	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if len(targetID) == 0 {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/group/publish." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", senderID)
	for _, v := range targetID {
		req.Param("toGroupId", v)
	}
	req.Param("objectName", objectName)
	msgr, err := msg.ToString()
	if err != nil {
		rc.urlError(err)
		return err
	}
	req.Param("content", msgr)
	req.Param("pushContent", pushContent)
	req.Param("pushData", pushData)
	req.Param("isPersisted", strconv.Itoa(isPersisted))
	req.Param("isIncludeSender", strconv.Itoa(isIncludeSender))
	req.Param("isMentioned", strconv.Itoa(extraOptins.isMentioned))
	req.Param("contentAvailable", strconv.Itoa(extraOptins.contentAvailable))
	req.Param("expansion", strconv.FormatBool(extraOptins.expansion))
	req.Param("disablePush", strconv.FormatBool(extraOptins.disablePush))
	if !extraOptins.disablePush && extraOptins.pushExt != "" {
		req.Param("pushExt", extraOptins.pushExt)
	}
	if len(userID) > 0 {
		for _, v := range userID {
			req.Param("toUserId", v)
		}
	}

	_, err = rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// GroupRecall 撤回群聊消息
/*
*@param  senderID:发送人用户 ID。
*@param  targetID:接收用户 ID。
*@param  uID:消息的唯一标识，各端 SDK 发送消息成功后会返回 uID。
*@param  sentTime:消息的发送时间，各端 SDK 发送消息成功后会返回 sentTime。
*
*@return error
 */
func (rc *RongCloud) GroupRecall(senderID, targetID, uID string, sentTime int) error {
	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if targetID == "" {
		return RCErrorNew(1002, "Paramer 'targetID' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/message/recall." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", senderID)
	req.Param("targetId", targetID)
	req.Param("messageUID", uID)
	req.Param("sentTime", strconv.Itoa(sentTime))
	req.Param("conversationType", strconv.Itoa(3))

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// GroupSendMention 发送群组 @ 消息
/*
*@param  senderID:发送人用户 ID 。
*@param  targetID:接收群ID,最多不超过 3 个群组。
*@param  objectName:消息类型。
*@param  msg:发送消息内容。
*@param  pushContent:定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息. 如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。
*@param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。
*@param  isPersisted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息。
*@param  isIncludeSender:发送用户自已是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。
*@param  isMentioned:是否为 @消息，0 表示为普通消息，1 表示为 @消息，默认为 0。当为 1 时 content 参数中必须携带 mentionedInfo @消息的详细内容。为 0 时则不需要携带 mentionedInfo。当指定了 toUserId 时，则 @ 的用户必须为 toUserId 中的用户。
*@param  contentAvailable:针对 iOS 平台，对 SDK 处于后台暂停状态时为静默推送，是 iOS7 之后推出的一种推送方式。 允许应用在收到通知后在后台运行一段代码，且能够马上执行，查看详细。1 表示为开启，0 表示为关闭，默认为 0
*
*@return error
 */
func (rc *RongCloud) GroupSendMention(senderID string, targetID []string, objectName string, msg MentionMsgContent,
	pushContent, pushData string, isPersisted, isIncludeSender, isMentioned, contentAvailable int) error {
	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if len(targetID) == 0 && len(targetID) > 3 {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/message/group/publish." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", senderID)
	for _, v := range targetID {
		req.Param("toGroupId", v)
	}
	req.Param("objectName", objectName)

	bytes, err := json.Marshal(msg)
	if err != nil {
		return err
	}
	req.Param("content", string(bytes))
	req.Param("pushContent", pushContent)
	req.Param("pushData", pushData)
	req.Param("isPersisted", strconv.Itoa(isPersisted))
	req.Param("isIncludeSender", strconv.Itoa(isIncludeSender))
	req.Param("isMentioned", strconv.Itoa(isMentioned))
	req.Param("contentAvailable", strconv.Itoa(contentAvailable))

	_, err = rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomSend 发送聊天室消息方法。（以一个用户身份向群组发送消息，单条消息最大 128k.每秒钟最多发送 20 条消息，每次最多向 3 个群组发送，如：一次向 3 个群组发送消息，示为 3 条消息。）
/*
*@param  senderID:发送人用户 ID 。
*@param  targetID:接收聊天室ID, 建议最多不超过 10 个聊天室。
*@param  objectName:消息类型
*@param  msg:发送消息内容
*
*@return error
 */
func (rc *RongCloud) ChatRoomSend(senderID string, targetID []string, objectName string, msg rcMsg) error {
	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if len(targetID) == 0 {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/message/chatroom/publish." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", senderID)
	for _, v := range targetID {
		req.Param("toChatroomId", v)
	}
	req.Param("objectName", objectName)
	msgr, err := msg.ToString()
	if err != nil {
		return err
	}
	req.Param("content", msgr)

	_, err = rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomBroadcast 向应用内所有聊天室广播消息方法，此功能需开通 专属服务（以一个用户身份向群组发送消息，单条消息最大 128k.每秒钟最多发送 20 条消息。）
/*
*@param  senderID:发送人用户 ID 。
*@param  objectName:消息类型
*@param  msg:发送消息内容
*
*@return error
 */
func (rc *RongCloud) ChatRoomBroadcast(senderID, objectName string, msg rcMsg) error {
	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/message/chatroom/broadcast." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", senderID)
	req.Param("objectName", objectName)
	msgr, err := msg.ToString()
	if err != nil {
		return err
	}
	req.Param("content", msgr)

	_, err = rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// SystemSend 一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM。
/*
*@param  senderID:发送人用户 ID。
*@param  targetID:接收用户 ID, 上限为 100 人。
*@param  objectName:发送的消息类型。
*@param  msg:消息。
*@param  pushContent:定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息。如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。
*@param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。
*@param  count:针对 iOS 平台，Push 时用来控制未读消息显示数，只有在 toUserId 为一个用户 Id 的时候有效。
*@param  isPersisted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息。
*@param  options 发送消息需要用的其他扩展参数

*
*@return error
 */
func (rc *RongCloud) SystemSend(senderID string, targetID []string, objectName string, msg rcMsg,
	pushContent, pushData string, count, isPersisted int,
	options ...MsgOption) error {

	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if len(targetID) == 0 {
		return RCErrorNew(1002, "Paramer 'targetID' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/system/publish." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", senderID)
	for _, v := range targetID {
		req.Param("toUserId", v)
	}
	req.Param("objectName", objectName)
	msgr, err := msg.ToString()
	if err != nil {
		return err
	}

	req.Param("content", msgr)
	req.Param("pushData", pushData)
	req.Param("pushContent", pushContent)
	req.Param("count", strconv.Itoa(count))
	req.Param("isPersisted", strconv.Itoa(isPersisted))
	req.Param("contentAvailable", strconv.Itoa(extraOptins.contentAvailable))
	req.Param("disablePush", strconv.FormatBool(extraOptins.disablePush))
	if !extraOptins.disablePush && extraOptins.pushExt != "" {
		req.Param("pushExt", extraOptins.pushExt)
	}

	_, err = rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// SystemBroadcast 给应用内所有用户发送消息方法，每小时最多发 2 次，每天最多发送 3 次（以一个用户身份向群组发送消息，单条消息最大 128k.每秒钟最多发送 20 条消息。）
/*
*@param  senderID:发送人用户 ID 。
*@param  objectName:消息类型
*@param  msg:发送消息内容
*
*@return error
 */
func (rc *RongCloud) SystemBroadcast(senderID, objectName string, msg rcMsg,
	options ...MsgOption) error {
	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/broadcast." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", senderID)
	req.Param("objectName", objectName)
	msgr, err := msg.ToString()
	if err != nil {
		return err
	}
	req.Param("content", msgr)

	if extraOptins.pushContent != "" {
		req.Param("pushContent", extraOptins.pushContent)
	}
	if extraOptins.pushData != "" {
		req.Param("pushData", extraOptins.pushData)
	}
	req.Param("contentAvailable", strconv.Itoa(extraOptins.contentAvailable))
	if extraOptins.pushExt != "" {
		req.Param("pushExt", extraOptins.pushExt)
	}

	_, err = rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// SystemSendTemplate 一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM
/*
*@param  senderID:发送人用户 ID。
*@param  objectName:发送的消息类型。
*@param  template:消息模版。
*@param  content:数据内容，包含消息内容和接收者。
*
*@return error
 */
func (rc *RongCloud) SystemSendTemplate(senderID, objectName string, template TXTMsg, content []TemplateMsgContent) error {
	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/message/system/publish_template." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	var toUserIDs, push []string
	var values []map[string]string

	for _, v := range content {
		if v.TargetID == "" {
			return RCErrorNew(1002, "Paramer 'TargetID' is required")
		}
		toUserIDs = append(toUserIDs, v.TargetID)
		values = append(values, v.Data)
		push = append(push, v.PushContent)
	}

	bytes, err := json.Marshal(template)
	if err != nil {
		return err
	}

	param := map[string]interface{}{}
	param["fromUserId"] = senderID
	param["objectName"] = objectName
	param["content"] = string(bytes)
	param["toUserId"] = toUserIDs
	param["values"] = values
	param["pushContent"] = push
	param["verifyBlacklist"] = 0

	_, _ = req.JSONBody(param)

	_, err = rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// HistoryGet 按小时获取历史消息日志文件 URL，包含小时内应用产生的所有消息，消息日志文件无论是否已下载，3 天后将从融云服务器删除
/*
*@param date:精确到小时，例如: 2018030210 表示获取 2018 年 3 月 2 日 10 点至 11 点产生的数据
*
*@return History error
 */
func (rc *RongCloud) HistoryGet(date string) (History, error) {
	req := httplib.Post(rc.rongCloudURI + "/message/history." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("date", date)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return History{}, err
	}
	var history History
	if err := json.Unmarshal(resp, &history); err != nil {
		return History{}, err
	}
	return history, nil
}

// HistoryRemove 删除历史消息日志文件
/*
*@param date:精确到小时，例如: 2018030210 表示获取 2018 年 3 月 2 日 10 点至 11 点产生的数据
*
*@return error
 */
func (rc *RongCloud) HistoryRemove(date string) error {
	if date == "" {
		return RCErrorNew(1002, "Paramer 'date' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/message/history/delete." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("date", date)

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err

}
