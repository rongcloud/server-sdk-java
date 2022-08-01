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
	"fmt"
	"strconv"
	"time"

	"github.com/astaxie/beego/httplib"
)

const (
	MessagePrivateType = 1 // MessagePrivateType 二人会话
	MessageGroupType   = 3 // MessageGroupType 群组会话
)

// rcMsg rcMsg接口
type rcMsg interface {
	ToString() (string, error)
}

// MsgUserInfo 融云内置消息用户信息
type MsgUserInfo struct {
	ID       string `json:"id"`
	Name     string `json:"name"`
	Icon     string `json:"icon"`
	Portrait string `json:"portrait"`
	Extra    string `json:"extra"`
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

// 高清语音消息 RC:HQVCMsg
type HQVCMsg struct {
	LocalPath string      `json:"localPath"`
	RemoteUrl string      `json:"remoteUrl"`
	Duration  interface{} `json:"duration"`
	User      MsgUserInfo `json:"user"`
	Extra     string      `json:"extra"`
}

// IMGTextMsg 消息
type IMGTextMsg struct {
	Title    string      `json:"title"`
	Content  string      `json:"content"`
	User     MsgUserInfo `json:"user"`
	Extra    string      `json:"extra"`
	ImageUri string      `json:"imageUri"`
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
	Name string      `json:"name"`
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
	MentionedInfo MentionedInfo `json:"mentionedInfo"`
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
	Value string `json:"value"`
	Extra string `json:"extra"`
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

// ToString HQVCMsg
func (msg *HQVCMsg) ToString() (string, error) {
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
	busChannel       string
	isAdmin          int
	isDelete         int
	extraContent     string
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

// 是否为可扩展消息，默认为 false，设为 true 时终端在收到该条消息后，可对该条消息设置扩展信息。
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

// busChannel 创建子会话
func WithMsgBusChannel(busChannel string) MsgOption {
	return func(options *msgOptions) {
		options.busChannel = busChannel
	}
}

// 是否为管理员，默认为 0，设为 1 时，IMKit 收到此条消息后，小灰条默认显示为“管理员 撤回了一条消息”。
func WithIsAdmin(isAdmin int) MsgOption {
	return func(options *msgOptions) {
		options.isAdmin = isAdmin
	}
}

// 默认为 0 撤回该条消息同时，用户端将该条消息删除并替换为一条小灰条撤回提示消息；为 1 时，该条消息删除后，不替换为小灰条提示消息。
func WithIsDelete(isDelete int) MsgOption {
	return func(options *msgOptions) {
		options.isDelete = isDelete
	}
}

// WithExtraContent 自定义的消息扩展信息，该字段接受 JSON 字符串格式的键值对（key-value pairs）。
func WithExtraContent(extraContent string) MsgOption {
	return func(options *msgOptions) {
		options.extraContent = extraContent
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
		busChannel:       "",
		isAdmin:          0,
		isDelete:         0,
		extraContent:     "",
	}

	// 修改默认值
	for _, ext := range options {
		ext(&defaultMsgOptions)
	}

	return defaultMsgOptions
}

// UgMessageExtension :根据消息 ID 批量获取超级群消息的扩展消息，可选填
type UgMessageExtension struct {
	// 频道 Id，支持英文字母、数字组合，最长为 20 个字符
	BusChannel string

	// 请求唯一标识,，保证一分钟之内的请求幂等
	MsgRandom int64
}

// MessageExpansionSet : 设置消息扩展  /message/expansion/set.json
//*
// @param  msgUID:消息唯一标识 ID，可通过全量消息路由功能获取。详见全量消息路由。
// @param  userId:操作者用户 ID，即需要为指定消息（msgUID）设置扩展信息的用户 ID。
// @param  conversationType:会话类型。支持的会话类型包括：1（二人会话）、3（群组会话）。
// @param  targetId:目标 ID，根据不同的 conversationType，可能是用户 ID 或群组 ID。
// @param  extraKeyVal:消息自定义扩展内容，JSON 结构，以 Key、Value 的方式进行设置，如：{"type":"3"}，单条消息可设置 300 个扩展信息，一次最多可以设置 100 个。
// @param  isSyncSender:设置操作会生成“扩展操作消息”。该字段指定“扩展操作消息”的发送者是否可在客户端接收该消息。https://doc.rongcloud.cn/imserver/server/v1/message/expansion#set
//*/
func (rc *RongCloud) MessageExpansionSet(msgUID, userId, conversationType, targetId, extraKeyVal string, isSyncSender int) error {
	if len(msgUID) == 0 {
		return RCErrorNew(1002, "Paramer 'msgUID' is required")
	}

	if len(userId) == 0 {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}

	if len(conversationType) == 0 {
		return RCErrorNew(1002, "Paramer 'conversationType' is required")
	}

	if len(targetId) == 0 {
		return RCErrorNew(1002, "Paramer 'targetId' is required")
	}

	if len(extraKeyVal) == 0 {
		return RCErrorNew(1002, "Paramer 'extraKeyVal' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/message/expansion/set.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("msgUID", msgUID)
	req.Param("userId", userId)
	req.Param("conversationType", conversationType)
	req.Param("targetId", targetId)
	req.Param("extraKeyVal", extraKeyVal)
	req.Param("isSyncSender", strconv.Itoa(isSyncSender))
	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// MessageExpansionDel : 删除消息扩展  /message/expansion/delete.json
//*
// @param  msgUID:消息唯一标识 ID，可通过全量消息路由功能获取。详见全量消息路由。
// @param  userId:操作者用户 ID，即需要为指定消息（msgUID）删除扩展信息的用户 ID。
// @param  conversationType:会话类型。支持的会话类型包括：1（二人会话）、3（群组会话）。
// @param  targetId:目标 ID，根据不同的 conversationType，可能是用户 ID 或群组 ID。
// @param  extraKeyVal:消息自定义扩展内容，JSON 结构，以 Key、Value 的方式进行设置，如：{"type":"3"}，单条消息可设置 300 个扩展信息，一次最多可以设置 100 个。
// @param  isSyncSender:设置操作会生成“扩展操作消息”。该字段指定“扩展操作消息”的发送者是否可在客户端接收该消息。具体请看。https://doc.rongcloud.cn/imserver/server/v1/message/expansion#delete
//*/
func (rc *RongCloud) MessageExpansionDel(msgUID, userId, conversationType, targetId, extraKey string, isSyncSender int) error {
	if len(msgUID) == 0 {
		return RCErrorNew(1002, "Paramer 'msgUID' is required")
	}

	if len(userId) == 0 {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}

	if len(conversationType) == 0 {
		return RCErrorNew(1002, "Paramer 'conversationType' is required")
	}

	if len(targetId) == 0 {
		return RCErrorNew(1002, "Paramer 'targetId' is required")
	}

	if len(extraKey) == 0 {
		return RCErrorNew(1002, "Paramer 'extraKey' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/message/expansion/delete.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("msgUID", msgUID)
	req.Param("userId", userId)
	req.Param("conversationType", conversationType)
	req.Param("targetId", targetId)
	req.Param("extraKey", extraKey)
	req.Param("isSyncSender", strconv.Itoa(isSyncSender))
	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// 超级群消息修改

// UGMessageModify : 超级群消息修改 /ultragroup/msg/modify.json
//*
// @param  groupId:超级群 ID
// @param  fromUserId:消息发送者
// @param  msgUID:消息唯一标识
// @param  content:消息所发送内容 最大128k
// @param  busChannel:频道 Id，支持英文字母、数字组合，最长为 20 个字符
// @param  msgRandom:请求唯一标识,，保证一分钟之内的请求幂等
//*/
func (rc *RongCloud) UGMessageModify(groupId, fromUserId, msgUID, content string, options ...UgMessageExtension) ([]byte, error) {
	if len(groupId) == 0 {
		return nil, RCErrorNew(1002, "Paramer 'groupId' is required")
	}

	if len(fromUserId) == 0 {
		return nil, RCErrorNew(1002, "Paramer 'fromUserId' is required")
	}

	if len(msgUID) == 0 {
		return nil, RCErrorNew(1002, "Paramer 'msgUID' is required")
	}

	if len(content) == 0 {
		return nil, RCErrorNew(1002, "Paramer 'content' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/ultragroup/msg/modify.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", groupId)
	req.Param("fromUserId", fromUserId)
	req.Param("msgUID", msgUID)
	req.Param("content", content)

	if len(options) == 1 {
		req.Param("busChannel", options[0].BusChannel)
		req.Param("msgRandom", fmt.Sprintf("%v", options[0].MsgRandom))
	}
	res, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return res, err
}

// UGMessageData ：UGMessageGet方法中的消息参数数组
type UGMessageData struct {
	// 消息唯一标识 ID
	MsgUid string `json:"msgUID"`

	// 频道 Id，支持英文字母、数字组合，最长为 20 个字符
	BusChannel string `json:"busChannel,omitempty"`
}

type UGMessageGetData struct {
	Code int                    `json:"code"`
	Data []UGMessageGetDataList `json:"data"`
}

type UGMessageGetDataList struct {
	FromUserId   string `json:"fromUserId"`
	GroupId      string `json:"groupId"`
	SentTime     uint64 `json:"sentTime"`
	BusChannel   string `json:"busChannel"`
	MsgUid       string `json:"msgUID"`
	ObjectName   string `json:"objectName"`
	Content      string `json:"content"`
	Expansion    bool   `json:"expansion"`
	ExtraContent string `json:"extraContent"`
}

// UGMessageGetObj  : 根据消息 ID 批量获取超级群消息 /ultragroup/msg/get
//*
// @param  groupId:超级群 ID
// @param  msgList:消息参数数组   每个元素是UGMessageData
// response： 返回结构体
//*/
func (rc *RongCloud) UGMessageGetObj(groupId string, msgList []UGMessageData, options ...MsgOption) (UGMessageGetData, error) {
	respData := UGMessageGetData{}
	if len(groupId) == 0 {
		return respData, RCErrorNew(1002, "Paramer 'groupId' is required")
	}

	if len(msgList) == 0 {
		return respData, RCErrorNew(1002, "Paramer 'msgList' is required")
	}

	msg, err := json.Marshal(msgList)
	if err != nil {
		return respData, err
	}
	extOptions := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/ultragroup/msg/get.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", groupId)
	req.Param("msgs", string(msg))

	if extOptions.busChannel != "" {
		req.Param("busChannel", extOptions.busChannel)
	}
	res, err := rc.do(req)
	if err != nil {
		return respData, err
	}
	if err := json.Unmarshal(res, &respData); err != nil {
		return respData, err
	}
	return respData, err
}

// UGMessageGet : 根据消息 ID 批量获取超级群消息 /ultragroup/msg/get
//*
// @param  groupId:超级群 ID
// @param  msgList:消息参数数组   每个元素是UGMessageData
// response： 返回byte数组
//*/
func (rc *RongCloud) UGMessageGet(groupId string, msgList []UGMessageData, options ...MsgOption) ([]byte, error) {
	if len(groupId) == 0 {
		return nil, RCErrorNew(1002, "Paramer 'groupId' is required")
	}

	if len(msgList) == 0 {
		return nil, RCErrorNew(1002, "Paramer 'msgList' is required")
	}

	msg, err := json.Marshal(msgList)
	if err != nil {
		return nil, err
	}
	extOptions := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/ultragroup/msg/get.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", groupId)
	req.Param("msgs", string(msg))

	if extOptions.busChannel != "" {
		req.Param("busChannel", extOptions.busChannel)
	}
	res, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return res, err
}

// UGMessageRecall 超级群消息撤回
func (rc *RongCloud) UGMessageRecall(userId, targetId, messageId string, sentTime int, options ...MsgOption) error {
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

	extOptions := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/recall." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("fromUserId", userId)
	req.Param("conversationType", strconv.Itoa(10))
	req.Param("targetId", targetId)
	req.Param("messageUID", messageId)
	req.Param("sentTime", strconv.Itoa(sentTime))
	req.Param("disablePush", strconv.FormatBool(extOptions.disablePush))
	req.Param("isAdmin", strconv.Itoa(extOptions.isAdmin))
	req.Param("isDelete", strconv.Itoa(extOptions.isDelete))

	if extOptions.busChannel != "" {
		req.Param("busChannel", extOptions.busChannel)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
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
 * @param int isAdmin
 * @param int isDelete
 * @param bool disablePush
 * @return: error
 */
func (rc *RongCloud) ChatRoomRecall(userId string, targetId string, messageId string, sentTime int,
	options ...MsgOption) error {
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

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/recall." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("fromUserId", userId)
	req.Param("conversationType", strconv.Itoa(4))
	req.Param("targetId", targetId)
	req.Param("messageUID", messageId)
	req.Param("sentTime", strconv.Itoa(sentTime))
	req.Param("disablePush", strconv.FormatBool(extraOptins.disablePush))
	req.Param("isAdmin", strconv.Itoa(extraOptins.isAdmin))
	req.Param("isDelete", strconv.Itoa(extraOptins.isDelete))

	if extraOptins.busChannel != "" {
		req.Param("busChannel", extraOptins.busChannel)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

/**
 * @name: SystemRecall
 * @test:
 * @msg: 消息撤回 - 系统会话
 * @param string userId
 * @param string targetId
 * @param string messageId
 * @param int sentTime
 * @param int isAdmin
 * @param int isDelete
 * @param bool disablePush
 * @return: error
 */
func (rc *RongCloud) SystemRecall(userId string, targetId string, messageId string, sentTime int,
	options ...MsgOption) error {
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

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/recall." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("fromUserId", userId)
	req.Param("conversationType", strconv.Itoa(6))
	req.Param("targetId", targetId)
	req.Param("messageUID", messageId)
	req.Param("sentTime", strconv.Itoa(sentTime))
	req.Param("disablePush", strconv.FormatBool(extraOptins.disablePush))
	req.Param("isAdmin", strconv.Itoa(extraOptins.isAdmin))
	req.Param("isDelete", strconv.Itoa(extraOptins.isDelete))

	if extraOptins.busChannel != "" {
		req.Param("busChannel", extraOptins.busChannel)
	}

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

	if extraOptins.busChannel != "" {
		req.Param("busChannel", extraOptins.busChannel)
	}

	if extraOptins.expansion && extraOptins.extraContent != "" {
		req.Param("extraContent", extraOptins.extraContent)
	}

	_, err = rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// 私聊状态消息发送
// senderID: 发送人用户 ID。
// targetID: 接收用户 ID，支持向多人发送消息，每次上限为 1000 人。
// objectName: 消息类型
// msg: 所发送消息的内容
// verifyBlacklist: 是否过滤发送人黑名单列表，0 表示为不过滤、 1 表示为过滤，默认为 0 不过滤。
// isIncludeSender: 发送用户自己是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。
func (rc *RongCloud) PrivateStatusSend(senderID string, targetID []string, objectName string, msg rcMsg,
	verifyBlacklist int, isIncludeSender int, options ...MsgOption) error {

	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if len(targetID) == 0 {
		return RCErrorNew(1002, "Paramer 'targetID' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/statusmessage/private/publish." + ReqType)
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
	req.Param("verifyBlacklist", strconv.Itoa(verifyBlacklist))
	req.Param("isIncludeSender", strconv.Itoa(isIncludeSender))
	if extraOptins.busChannel != "" {
		req.Param("busChannel", extraOptins.busChannel)
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
* @param int isAdmin
* @param int isDelete
* @param bool disablePush
*@return error
 */
func (rc *RongCloud) PrivateRecall(senderID, targetID, uID string, sentTime int,
	options ...MsgOption) error {
	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if targetID == "" {
		return RCErrorNew(1002, "Paramer 'targetID' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/recall." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", senderID)
	req.Param("targetId", targetID)
	req.Param("messageUID", uID)
	req.Param("sentTime", strconv.Itoa(sentTime))
	req.Param("conversationType", strconv.Itoa(1))
	req.Param("disablePush", strconv.FormatBool(extraOptins.disablePush))
	req.Param("isAdmin", strconv.Itoa(extraOptins.isAdmin))
	req.Param("isDelete", strconv.Itoa(extraOptins.isDelete))

	if extraOptins.busChannel != "" {
		req.Param("busChannel", extraOptins.busChannel)
	}

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

	if extraOptins.busChannel != "" {
		param["busChannel"] = extraOptins.busChannel
	}

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
	pushContent string, pushData string, isPersisted, isIncludeSender int, options ...MsgOption) error {
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

	if extraOptins.busChannel != "" {
		req.Param("busChannel", extraOptins.busChannel)
	}

	if extraOptins.expansion && extraOptins.extraContent != "" {
		req.Param("extraContent", extraOptins.extraContent)
	}

	_, err = rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// 群聊状态消息发送
// senderID: 发送人用户 ID。
// toGroupIds: 接收群ID，提供多个本参数可以实现向多群发送消息，最多不超过 3 个群组。
// objectName: 消息类型
// msg: 所发送消息的内容
// verifyBlacklist: 是否过滤发送人黑名单列表，0 表示为不过滤、 1 表示为过滤，默认为 0 不过滤。
// isIncludeSender: 发送用户自己是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。
func (rc *RongCloud) GroupStatusSend(senderID string, toGroupIds []string, objectName string, msg rcMsg,
	verifyBlacklist int, isIncludeSender int, options ...MsgOption) error {

	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if len(toGroupIds) == 0 {
		return RCErrorNew(1002, "Paramer 'toGroupIds' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/statusmessage/group/publish." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", senderID)
	for _, v := range toGroupIds {
		req.Param("toGroupId", v)
	}
	req.Param("objectName", objectName)

	msgr, err := msg.ToString()
	if err != nil {
		return err
	}
	req.Param("content", msgr)
	req.Param("verifyBlacklist", strconv.Itoa(verifyBlacklist))
	req.Param("isIncludeSender", strconv.Itoa(isIncludeSender))
	if extraOptins.busChannel != "" {
		req.Param("busChannel", extraOptins.busChannel)
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
* @param int isAdmin
* @param int isDelete
* @param bool disablePush
*@return error
 */
func (rc *RongCloud) GroupRecall(senderID, targetID, uID string, sentTime int,
	options ...MsgOption) error {
	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if targetID == "" {
		return RCErrorNew(1002, "Paramer 'targetID' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/recall." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", senderID)
	req.Param("targetId", targetID)
	req.Param("messageUID", uID)
	req.Param("sentTime", strconv.Itoa(sentTime))
	req.Param("conversationType", strconv.Itoa(3))
	req.Param("disablePush", strconv.FormatBool(extraOptins.disablePush))
	req.Param("isAdmin", strconv.Itoa(extraOptins.isAdmin))
	req.Param("isDelete", strconv.Itoa(extraOptins.isDelete))

	if extraOptins.busChannel != "" {
		req.Param("busChannel", extraOptins.busChannel)
	}

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
	pushContent, pushData string, isPersisted, isIncludeSender, isMentioned, contentAvailable int, options ...MsgOption) error {
	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if len(targetID) == 0 && len(targetID) > 3 {
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
	req.Param("expansion", strconv.FormatBool(extraOptins.expansion))
	req.Param("disablePush", strconv.FormatBool(extraOptins.disablePush))
	if !extraOptins.disablePush && extraOptins.pushExt != "" {
		req.Param("pushExt", extraOptins.pushExt)
	}
	if extraOptins.busChannel != "" {
		req.Param("busChannel", extraOptins.busChannel)
	}

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
func (rc *RongCloud) ChatRoomSend(senderID string, targetID []string, objectName string, msg rcMsg, isPersisted, isIncludeSender int) error {
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
	req.Param("isPersisted", fmt.Sprint(isPersisted))

	if isIncludeSender > 0 {
		req.Param("isIncludeSender", fmt.Sprint(isIncludeSender))
	}

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
* @param isIncludeSender:0或者1
*@return error
 */
func (rc *RongCloud) ChatRoomBroadcast(senderID, objectName string, msg rcMsg, isIncludeSender ...string) error {
	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/message/chatroom/broadcast." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", senderID)
	req.Param("objectName", objectName)
	if len(isIncludeSender) > 0 {
		req.Param("isIncludeSender", isIncludeSender[0])
	}
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

// 在线广播消息
// 是指系统向 App 中所有在线用户发送消息的行为。当用户正在使用 App 时，消息会展示在聊天界面和会话列表界面，会话类型为 SYSTEM。
// @param fromUserId  发送人用户 Id
// @param objectName  消息类型，
// @param content  发送消息内容
func (rc *RongCloud) OnlineBroadcast(fromUserId string, objectName string, content string) ([]byte, error) {

	if fromUserId == "" {
		return nil, RCErrorNew(1002, "Paramer 'fromUserId' is required")
	}
	if objectName == "" {
		return nil, RCErrorNew(1002, "Paramer 'objectName' is required")
	}
	if content == "" {
		return nil, RCErrorNew(1002, "Paramer 'content' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/message/online/broadcast." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", fromUserId)
	req.Param("objectName", objectName)
	req.Param("content", content)

	code, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}

	return code, err
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
	pushContent, pushData string, count, isPersisted int, options ...MsgOption) error {

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
	if extraOptins.busChannel != "" {
		req.Param("busChannel", extraOptins.busChannel)
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
func (rc *RongCloud) SystemBroadcast(senderID, objectName string, msg rcMsg, options ...MsgOption) error {
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
func (rc *RongCloud) SystemSendTemplate(senderID, objectName string, template TXTMsg, content []TemplateMsgContent,
	options ...MsgOption) error {
	if senderID == "" {
		return RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/system/publish_template." + ReqType)
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
	param["verifyBlacklist"] = 0
	param["pushData"] = pushData
	param["contentAvailable"] = extraOptins.contentAvailable
	param["disablePush"] = extraOptins.disablePush
	if extraOptins.busChannel != "" {
		param["busChannel"] = extraOptins.busChannel
	}

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

// SetMessageExpansion 设置消息扩展
// 发送消息时，如设置了 expansion 为 true，可对该条消息进行扩展信息设置，每次最多可以设置 100 个扩展属性信息，最多可设置 300 个。
func (rc *RongCloud) SetMessageExpansion(msgUID, userId, conversationType, targetId string, extra map[string]string, isSyncSender int) error {
	if msgUID == "" {
		return RCErrorNew(1002, "Paramer 'msgUID' is required")
	}

	if userId == "" {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}

	if conversationType == "" {
		return RCErrorNew(1002, "Paramer 'conversationType' is required")
	}

	if conversationType != strconv.Itoa(MessagePrivateType) && conversationType != strconv.Itoa(MessageGroupType) {
		return RCErrorNew(1002, "Paramer 'conversationType' must be 1 or 3 to string")
	}

	if isSyncSender != 0 && isSyncSender != 1 {
		return RCErrorNew(1002, "Paramer 'isSyncSender' is error")
	}

	if targetId == "" {
		return RCErrorNew(1002, "Paramer 'targetId' is required")
	}

	if extra == nil {
		return RCErrorNew(1002, "Paramer 'extra' is required")
	}

	encExtra, err := json.Marshal(extra)
	if err != nil {
		return err
	}

	req := httplib.Post(rc.rongCloudURI + "/message/expansion/set." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("msgUID", msgUID)
	req.Param("userId", userId)
	req.Param("conversationType", conversationType)
	req.Param("targetId", targetId)
	req.Param("extraKeyVal", string(encExtra))
	req.Param("isSyncSender", strconv.Itoa(isSyncSender))

	if _, err = rc.do(req); err != nil {
		return err
	}

	return nil
}

// DeleteMessageExpansion 删除消息扩展
func (rc *RongCloud) DeleteMessageExpansion(msgUID, userId, conversationType, targetId string, isSyncSender int, keys ...string) error {
	if msgUID == "" {
		return RCErrorNew(1002, "Paramer 'msgUID' is required")
	}

	if userId == "" {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}

	if conversationType == "" {
		return RCErrorNew(1002, "Paramer 'conversationType' is required")
	}

	if conversationType != strconv.Itoa(MessagePrivateType) && conversationType != strconv.Itoa(MessageGroupType) {
		return RCErrorNew(1002, "Paramer 'conversationType' must be 1 or 3 to string")
	}

	if targetId == "" {
		return RCErrorNew(1002, "Paramer 'targetId' is required")
	}

	if len(keys) <= 0 {
		return RCErrorNew(1002, "Paramer 'keys' is required")
	}

	if isSyncSender != 0 && isSyncSender != 1 {
		return RCErrorNew(1002, "Paramer 'isSyncSender' is error")
	}

	encKeys, err := json.Marshal(keys)
	if err != nil {
		return err
	}

	req := httplib.Post(rc.rongCloudURI + "/message/expansion/delete." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("msgUID", msgUID)
	req.Param("userId", userId)
	req.Param("conversationType", conversationType)
	req.Param("targetId", targetId)
	req.Param("extraKey", string(encKeys))
	req.Param("isSyncSender", strconv.Itoa(isSyncSender))

	if _, err = rc.do(req); err != nil {
		return err
	}

	return nil
}

type MessageExpansionItem struct {
	Key       string `json:"key"`
	Value     string `json:"value"`
	Timestamp int64  `json:"timestamp"`
}

// QueryMessageExpansion 获取扩展信息
// 根据消息 ID 获取指定消息扩展信息
func (rc *RongCloud) QueryMessageExpansion(msgUID string, page int) ([]MessageExpansionItem, error) {
	if msgUID == "" {
		return nil, RCErrorNew(1002, "Paramer 'msgUID' is required")
	}

	if page <= 0 {
		page = 1
	}

	req := httplib.Post(rc.rongCloudURI + "/message/expansion/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("msgUID", msgUID)
	req.Param("pageNo", strconv.Itoa(page))

	body, err := rc.do(req)
	if err != nil {
		return nil, err
	}

	resp := struct {
		Code         int                               `json:"code"`
		ExtraContent map[string]map[string]interface{} `json:"extraContent"`
	}{}

	if err = json.Unmarshal(body, &resp); err != nil {
		return nil, err
	}

	if resp.Code != 200 {
		return nil, fmt.Errorf("response error: %d", resp.Code)
	}

	var data []MessageExpansionItem
	for key, val := range resp.ExtraContent {
		item := MessageExpansionItem{
			Key: key,
		}

		if v, ok := val["v"]; ok {
			item.Value = v.(string)
		}

		if ts, ok := val["ts"]; ok {
			item.Timestamp = int64(ts.(float64))
		}

		data = append(data, item)
	}

	return data, nil
}
