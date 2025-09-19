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
	MessagePrivateType = 1 // MessagePrivateType one-to-one chat
	MessageGroupType   = 3 // MessageGroupType group chat
)

// rcMsg rcMsg interface
type rcMsg interface {
	ToString() (string, error)
}

// MsgUserInfo RongCloud built-in message user information
type MsgUserInfo struct {
	ID       string `json:"id"`
	Name     string `json:"name"`
	Icon     string `json:"icon"`
	Portrait string `json:"portrait"`
	Extra    string `json:"extra"`
}

// TXTMsg message
type TXTMsg struct {
	Content string      `json:"content"`
	User    MsgUserInfo `json:"user"`
	Extra   string      `json:"extra"`
}

// ImgMsg message
type ImgMsg struct {
	Content  string      `json:"content"`
	User     MsgUserInfo `json:"user"`
	ImageURI string      `json:"imageUri"`
	Extra    string      `json:"extra"`
}

// InfoNtf Message
type InfoNtf struct {
	Message string      `json:"message"`
	User    MsgUserInfo `json:"user"`
	Extra   string      `json:"extra"`
}

// VCMsg Message
type VCMsg struct {
	Content  string      `json:"content"`
	User     MsgUserInfo `json:"user"`
	Extra    string      `json:"extra"`
	Duration interface{} `json:"duration"`
}

// HQVCMsg High-Quality Voice Message RC:HQVCMsg
type HQVCMsg struct {
	LocalPath string      `json:"localPath"`
	RemoteUrl string      `json:"remoteUrl"`
	Duration  interface{} `json:"duration"`
	User      MsgUserInfo `json:"user"`
	Extra     string      `json:"extra"`
}

// IMGTextMsg Message
type IMGTextMsg struct {
	Title    string      `json:"title"`
	Content  string      `json:"content"`
	User     MsgUserInfo `json:"user"`
	Extra    string      `json:"extra"`
	ImageUri string      `json:"imageUri"`
	URL      string      `json:"url"`
}

type SightMsg struct {
	Content  string      `json:"content"`
	User     MsgUserInfo `json:"user"`
	Extra    string      `json:"extra"`
	SightURL string      `json:"sightUrl"`
	Duration int         `json:"duration"`
	Size     int         `json:"size"`
	Name     string      `json:"name"`
}

// FileMsg Message
type FileMsg struct {
	Name    string      `json:"name"`
	Size    string      `json:"size"`
	Type    string      `json:"type"`
	FileURL string      `json:"fileUrl"`
	User    MsgUserInfo `json:"user"`
}

// LBSMsg Message
type LBSMsg struct {
	Content   string      `json:"content"`
	Extra     string      `json:"extra"`
	POI       string      `json:"poi"`
	Latitude  float64     `json:"latitude"`
	Longitude float64     `json:"longitude"`
	User      MsgUserInfo `json:"user"`
}

// ProfileNtf message
type ProfileNtf struct {
	Operation string      `json:"operation"`
	Data      string      `json:"data"`
	User      MsgUserInfo `json:"user"`
	Extra     string      `json:"extra"`
}

// CMDNtf message
type CMDNtf struct {
	Name string      `json:"operation"`
	Data string      `json:"data"`
	User MsgUserInfo `json:"user"`
}

// CMDMsg message
type CMDMsg struct {
	Name string      `json:"name"`
	Data string      `json:"data"`
	User MsgUserInfo `json:"user"`
}

// ContactNtf message
type ContactNtf struct {
	Operation    string      `json:"operation"`
	SourceUserID string      `json:"sourceUserId"`
	TargetUserID string      `json:"targetUserId"`
	Message      string      `json:"message"`
	Extra        string      `json:"extra"`
	User         MsgUserInfo `json:"user"`
}

// GrpNtf message
type GrpNtf struct {
	OperatorUserID string      `json:"operatorUserId"`
	Operation      string      `json:"operation"`
	Data           string      `json:"data"`
	Message        string      `json:"message"`
	Extra          string      `json:"extra"`
	User           MsgUserInfo `json:"user"`
}

// DizNtf message
type DizNtf struct {
	Type      int         `json:"type"`
	Extension string      `json:"extension"`
	Operation string      `json:"operation"`
	User      MsgUserInfo `json:"user"`
}

// TemplateMsgContent Message template
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

// BroadcastRecallContent Content of message broadcast recall
type BroadcastRecallContent struct {
	MessageId        string `json:"messageUId"`
	ConversationType int    `json:"conversationType"`
	IsAdmin          int    `json:"isAdmin"`
	IsDelete         int    `json:"isDelete"`
}

// ChatRoomKVNotiMessage Chatroom custom attributes notification message
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
 * @msg: Converts the content structure parameter required for the broadcast message recall API to JSON
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

// ToString SightMsg
func (msg *SightMsg) ToString() (string, error) {
	bytes, err := json.Marshal(msg)
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// msgOptions is extra options for sending messages
type msgOptions struct {
	isMentioned          int
	contentAvailable     int
	verifyBlacklist      int
	expansion            bool
	disablePush          bool
	pushExt              string
	pushContent          string
	pushData             string
	busChannel           string
	isAdmin              int
	isDelete             int
	extraContent         string
	isCounted            int
	disableUpdateLastMsg bool
}

// MsgOption interface functions
type MsgOption func(*msgOptions)

// Indicates whether the message is a mention message. 0 represents a normal message, 1 represents a mention message. Default is 0.
func WithMsgMentioned(isMentioned int) MsgOption {
	return func(options *msgOptions) {
		options.isMentioned = isMentioned
	}
}

// Indicates whether to disable updating the last message. Default is false.
func WithDisableUpdateLastMsg(disableUpdateLastMsg bool) MsgOption {
	return func(options *msgOptions) {
		options.disableUpdateLastMsg = disableUpdateLastMsg
	}
}

// For iOS platform, enables silent push when the SDK is in the background.
// A push method introduced after iOS7. Allows the app to run a piece of code in the background upon receiving a notification and execute it immediately.
// 1 represents enabled, 0 represents disabled. Default is 0.
func WithMsgContentAvailable(contentAvailable int) MsgOption {
	return func(options *msgOptions) {
		options.contentAvailable = contentAvailable
	}
}

// Specifies whether to filter the sender's blocklist. 0 represents no filtering, 1 represents filtering. Default is 0 (no filtering).
func WithMsgVerifyBlacklist(verifyBlacklist int) MsgOption {
	return func(options *msgOptions) {
		options.verifyBlacklist = verifyBlacklist
	}
}

// Indicates whether the message is extensible. Default is false. When set to true, the client can set extended information upon receiving the message.
func WithMsgExpansion(isExpansion bool) MsgOption {
	return func(options *msgOptions) {
		options.expansion = isExpansion
	}
}

// disablePush Boolean Indicates whether it is a silent message. Default is false. When set to true, the end user will not receive a notification reminder when offline.
func WithMsgDisablePush(isDisablePush bool) MsgOption {
	return func(options *msgOptions) {
		options.disablePush = isDisablePush
	}
}

// pushExt String Specifies the push notification attributes. For detailed information, refer to the pushExt structure description. pushExt is in JSON format and requires escaping when making requests.
// This attribute is invalid when disablePush is true.
func WithMsgPushExt(pushExt string) MsgOption {
	return func(options *msgOptions) {
		options.pushExt = pushExt
	}
}

// pushContent String Defines the displayed push content. If objectName is a built-in message type of RongCloud, the user will definitely receive push information after sending.
// For custom messages, pushContent is the displayed push content of the custom message. If not provided, the user will not receive a push notification.
func WithMsgPushContent(pushContent string) MsgOption {
	return func(options *msgOptions) {
		options.pushContent = pushContent
	}
}

// pushData String For iOS platform, it is appended to the payload when sending a push notification. The client retrieves the remote push content as appData.
// RongCloud by default carries the basic message information, which the client can obtain through the 'rc' attribute. For Android clients, the corresponding field name is pushData when receiving push messages.
func WithMsgPushData(pushData string) MsgOption {
	return func(options *msgOptions) {
		options.pushData = pushData
	}
}

// busChannel Creates a sub-conversation
func WithMsgBusChannel(busChannel string) MsgOption {
	return func(options *msgOptions) {
		options.busChannel = busChannel
	}
}

// Indicates whether the user is an administrator. Default is 0. When set to 1, IMKit will display a gray bar message as "Admin recalled a message" upon receiving this message.
func WithIsAdmin(isAdmin int) MsgOption {
	return func(options *msgOptions) {
		options.isAdmin = isAdmin
	}
}

// Default is 0. When recalling the message, the client will delete the message and replace it with a gray bar recall prompt. When set to 1, the message will be deleted without being replaced by a gray bar prompt.
func WithIsDelete(isDelete int) MsgOption {
	return func(options *msgOptions) {
		options.isDelete = isDelete
	}
}

// WithExtraContent Custom message extension information, which accepts key-value pairs in JSON string format.
func WithExtraContent(extraContent string) MsgOption {
	return func(options *msgOptions) {
		options.extraContent = extraContent
	}
}

// WithMsgIsCounted Specifies whether to count the message as unread when the user is offline. 0 means not counted, 1 means counted. Default is 1.
func WithMsgIsCounted(isCounted int) MsgOption {
	return func(options *msgOptions) {
		options.isCounted = isCounted
	}
}

// Modify default values
func modifyMsgOptions(options []MsgOption) msgOptions {
	// Default values
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
		isCounted:        1,
	}

	// Modify default values
	for _, ext := range options {
		ext(&defaultMsgOptions)
	}

	return defaultMsgOptions
}

// UgMessageExtension : Batch retrieves extended messages for ultra group messages based on message ID, optional.
type UgMessageExtension struct {
	// Channel ID, supports a combination of letters and numbers, up to 20 characters.
	BusChannel string

	// Unique request identifier, ensures idempotency within one minute.
	MsgRandom int64
}

type MessageResult struct {
	Code        int               `json:"code"`
	MessageUID  string            `json:"messageUID,omitempty"`
	MessageUIDs []MessageUIDEntry `json:"messageUIDs,omitempty"`
}

type MessageUIDEntry struct {
	UserId     string `json:"userId,omitempty"`     // Indicates the value when sending a one-to-one chat message.
	GroupId    string `json:"groupId,omitempty"`    // Indicates the value when sending a group or ultra group message.
	ChatroomId string `json:"chatroomId,omitempty"` // Indicates the value when sending a chatroom message.
	MessageUID string `json:"messageUID,omitempty"` // Specifies the Message ID.
}

// MessageExpansionSet : Set message expansion /message/expansion/set.json
// *
// @param  msgUID: The unique identifier of the message, which can be obtained through the full message routing feature. See full message routing for details.
// @param  userId: The user ID of the operator, i.e., the user ID that needs to set the extended information for the specified message (msgUID).
// @param  conversationType: The conversation type. Supported conversation types include: 1 (one-to-one chat), 3 (group chat).
// @param  targetId: The target ID, which can be a user ID or group ID depending on the conversationType.
// @param  extraKeyVal: The custom extended content of the message, in JSON format, set in Key-Value pairs, e.g., {"type":"3"}. A single message can have up to 300 extended information items, and a maximum of 100 can be set at once.
// @param  isSyncSender: Specifies whether the sender of the "expansion operation message" can receive this message on the client. https://doc.rongcloud.cn/imserver/server/v1/message/expansion#set
// */
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

// MessageExpansionDel : Delete message expansion /message/expansion/delete.json
// *
// @param  msgUID: The unique identifier of the message, which can be obtained through the full message routing feature. For details, see Post-messaging Callback.
// @param  userId: The user ID of the operator, i.e., the user ID that needs to delete the extended information for the specified message (msgUID).
// @param  conversationType: The type of conversation. Supported conversation types include: 1 (one-to-one chat), 3 (group chat).
// @param  targetId: The target ID, which could be a user ID or group ID depending on the conversationType.
// @param  extraKeyVal: The custom extended content of the message, in JSON format, set as Key-Value pairs, e.g., {"type":"3"}. A single message can have up to 300 extended information items, and a maximum of 100 can be set at once.
// @param  isSyncSender: The operation will generate an "extension operation message". This field specifies whether the sender of the "extension operation message" can receive this message on the client. For details, see https://doc.rongcloud.cn/imserver/server/v1/message/expansion#delete
// */
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

// Ultra Group Message Modification

// UGMessageModify : Ultra Group Message Modification /ultragroup/msg/modify.json
// *
// @param  groupId: Ultra group ID
// @param  fromUserId: Message sender
// @param  msgUID: Unique identifier of the message
// @param  content: Message content, up to 128k
// @param  busChannel: Channel ID, supports alphanumeric combinations, up to 20 characters
// @param  msgRandom: Unique request identifier, ensures idempotency within one minute
// */
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

// UGMessageData: Message parameter array in the UGMessageGet method
type UGMessageData struct {
	// Unique identifier of the message
	MsgUid string `json:"msgUID"`

	// Channel ID, supports alphanumeric combinations, up to 20 characters
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

// UGMessageGetObj : Batch retrieve ultra group messages by message ID /ultragroup/msg/get
// *
// @param  groupId: Ultra group ID
// @param  msgList: Message parameter array, each element is UGMessageData
// response: Response structure
// */
func (rc *RongCloud) UGMessageGetObj(groupId string, msgList []UGMessageData, options ...MsgOption) (UGMessageGetData, error) {
	respData := UGMessageGetData{}
	if len(groupId) == 0 {
		return respData, RCErrorNew(1002, "Parameter 'groupId' is required")
	}

	if len(msgList) == 0 {
		return respData, RCErrorNew(1002, "Parameter 'msgList' is required")
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

// UGMessageGet : Retrieve Ultra Group Messages by Message ID /ultragroup/msg/get
// *
// @param  groupId: Ultra Group ID
// @param  msgList: Message parameter array, each element is UGMessageData
// response： Returns byte array
// */
func (rc *RongCloud) UGMessageGet(groupId string, msgList []UGMessageData, options ...MsgOption) ([]byte, error) {
	if len(groupId) == 0 {
		return nil, RCErrorNew(1002, "Parameter 'groupId' is required")
	}

	if len(msgList) == 0 {
		return nil, RCErrorNew(1002, "Parameter 'msgList' is required")
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

// UGMessageRecall Ultra group message recall
func (rc *RongCloud) UGMessageRecall(userId, targetId, messageId string, sentTime int, options ...MsgOption) error {
	if userId == "" {
		return RCErrorNew(1002, "Parameter 'userId' is required")
	}

	if targetId == "" {
		return RCErrorNew(1002, "Parameter 'targetId' is required")
	}

	if messageId == "" {
		return RCErrorNew(1002, "Parameter 'messageId' is required")
	}

	if sentTime == 0 {
		return RCErrorNew(1002, "Parameter 'sentTime' is required")
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

	if extOptions.disableUpdateLastMsg {
		req.Param("disableUpdateLastMsg", strconv.FormatBool(extOptions.disableUpdateLastMsg))
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
 * @msg: Broadcast message recall
 * @param string userId
 * @param string objectName
 * @param BroadcastRecallContent content
 * @return: error
 */
func (rc *RongCloud) MessageBroadcastRecall(userId string, objectName string, content BroadcastRecallContent, options ...MsgOption) (MessageResult, error) {
	result := MessageResult{}
	if userId == "" {
		return result, RCErrorNew(1002, "Paramer 'userId' is required")
	}

	if objectName == "" {
		return result, RCErrorNew(1002, "Paramer 'objectName' is required")
	}
	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/broadcast." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", userId)
	req.Param("objectName", objectName)

	if extraOptins.disableUpdateLastMsg {
		req.Param("disableUpdateLastMsg", strconv.FormatBool(extraOptins.disableUpdateLastMsg))
	}

	msg, err := content.ToString()
	if err != nil {
		return result, err
	}
	req.Param("content", msg)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}
	return result, nil
}

// MessageBroadcastRecallByMessageUID Recall a message - Broadcast
// @param string fromUserId The sender's user ID.
// @param string messageUID The unique identifier of the message. Currently can only be obtained through historical message logs, corresponding to the field name msgUID.
// @param int sentTime The timestamp when the message was sent. Currently can only be obtained through historical message logs, corresponding to the field name dateTime.
// @param int isAdmin Whether the user is an administrator. Default is 0. When set to 1, IMKit will display a gray bar message as "Admin recalled a message" upon receiving this message.
// @param int isDelete Default is 0. When recalling the message, the client will delete the message and replace it with a gray bar recall prompt. When set to 1, the message will be deleted without being replaced by a gray bar prompt.
// @param string extra Extension information, can contain arbitrary data content.
// @param MsgOption options Additional message options, such as disableUpdateLastMsg Boolean No Prohibits updating the last message in the conversation. When this parameter is false, the sent message will appear in the conversation list; when true, the message content will not be updated in the conversation list.
func (rc *RongCloud) MessageBroadcastRecallByMessageUID(fromUserId string, messageUID string, sentTime int, isAdmin int, isDelete int, extra string, options ...MsgOption) (MessageResult, error) {
	result := MessageResult{}
	if fromUserId == "" {
		return result, RCErrorNew(1002, "Parameter 'fromUserId' is required")
	}

	if messageUID == "" {
		return result, RCErrorNew(1002, "Parameter 'messageUID' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/broadcast/recall.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", fromUserId)
	req.Param("messageUID", messageUID)
	req.Param("sentTime", strconv.Itoa(sentTime))

	if isAdmin != 0 {
		req.Param("isAdmin", strconv.Itoa(isAdmin))
	}

	if isDelete != 0 {
		req.Param("isDelete", strconv.Itoa(isDelete))
	}

	if extra != "" {
		req.Param("extra", extra)
	}

	if extraOptins.disableUpdateLastMsg {
		req.Param("disableUpdateLastMsg", strconv.FormatBool(extraOptins.disableUpdateLastMsg))
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}
	return result, nil
}

/**
 * @name: ChatRoomRecall
 * @test:
 * @msg: Message Recall - Chatroom
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

	if extraOptins.disableUpdateLastMsg {
		req.Param("disableUpdateLastMsg", strconv.FormatBool(extraOptins.disableUpdateLastMsg))
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
 * @msg: Recall a message - System Conversation
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

	if extraOptins.disableUpdateLastMsg {
		req.Param("disableUpdateLastMsg", strconv.FormatBool(extraOptins.disableUpdateLastMsg))
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// PrivateSend Sends a one-to-one chat message (a single user can send a message to multiple users, with a maximum message size of 128k. The maximum number of messages that can be sent per minute is 6000, and the maximum number of users that can be sent to in a single request is 1000. For example, sending to 1000 users counts as 1000 messages.)
/*
*@param  senderID: The sender's user ID.
*@param  targetID: The recipient user ID. This allows sending messages to multiple users, with a maximum of 1000 users per request.
*@param  objectName: The type of message being sent.
*@param  msg: Message content.
*@param  pushContent: Defines the displayed push notification content.If the objectName is a built-in message
type of RongCloud, the user will definitely receive a push notification after sending.For custom messages, pushContent is the displayed push notification content of the custom message.If not provided, the user will not receive a push notification.
*@param  pushData: For iOS platforms, this is the additional payload attached to the push notification. For Android clients, the corresponding field name is pushData.
*@param  count: For iOS platforms, this controls the display count of unread messages in push notifications.It is only effective when toUserId is a single user ID.
*@param  verifyBlacklist: Whether to filter the sender's blocklist. 0 means no filtering, 1 means filtering. Default is 0 (no filtering).
*@param  isPersisted: When the current version has a new custom message and the older version does not, whether the older version client should store the message after receiving it. 0 means no storage, 1 means storage.Default is 1 (store the message).
*@param  isIncludeSender: Whether the sending user should receive the message.0 means no reception, 1 means reception.Default is 0 (no reception).
*@param  contentAvailable: For iOS platforms, this enables silent push notifications when the SDK is in the background suspended state.This is a push notification method introduced after iOS7.It allows the app to run a piece of code in the background immediately after receiving the notification.1 means enabled, 0 means disabled.Default is 0.
*@param  options: Other extended parameters needed for sending messages.
*@return MessageResult
*@return error
*/
func (rc *RongCloud) PrivateSend(senderID string, targetID []string, objectName string, msg rcMsg,
	pushContent, pushData string, count, verifyBlacklist, isPersisted, isIncludeSender, contentAvailable int,
	options ...MsgOption) (MessageResult, error) {
	result := MessageResult{}
	if senderID == "" {
		return result, RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if len(targetID) == 0 {
		return result, RCErrorNew(1002, "Paramer 'targetID' is required")
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
		return result, err
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
	req.Param("isCounted", strconv.Itoa(extraOptins.isCounted))

	if !extraOptins.disablePush && extraOptins.pushExt != "" {
		req.Param("pushExt", extraOptins.pushExt)
	}

	if extraOptins.busChannel != "" {
		req.Param("busChannel", extraOptins.busChannel)
	}

	if extraOptins.expansion && extraOptins.extraContent != "" {
		req.Param("extraContent", extraOptins.extraContent)
	}

	if extraOptins.disableUpdateLastMsg {
		req.Param("disableUpdateLastMsg", strconv.FormatBool(extraOptins.disableUpdateLastMsg))
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}
	return result, nil
}

// Send private status message
// senderID: The sender's user ID.
// targetID: The recipient user ID. Supports sending messages to multiple users, with a maximum of 1000 users per request.
// objectName: The message type.
// msg: The content of the message to be sent.
// verifyBlacklist: Whether to filter the sender's blocklist. 0 means no filtering, 1 means filtering. Default is 0 (no filtering).
// isIncludeSender: Whether the sender should receive the message. 0 means no, 1 means yes. Default is 0 (no).
func (rc *RongCloud) PrivateStatusSend(senderID string, targetID []string, objectName string, msg rcMsg,
	verifyBlacklist int, isIncludeSender int, options ...MsgOption) (MessageResult, error) {
	result := MessageResult{}
	if senderID == "" {
		return result, RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if len(targetID) == 0 {
		return result, RCErrorNew(1002, "Paramer 'targetID' is required")
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
		return result, err
	}
	req.Param("content", msgr)
	req.Param("verifyBlacklist", strconv.Itoa(verifyBlacklist))
	req.Param("isIncludeSender", strconv.Itoa(isIncludeSender))
	req.Param("isCounted", strconv.Itoa(extraOptins.isCounted))

	if extraOptins.busChannel != "" {
		req.Param("busChannel", extraOptins.busChannel)
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}
	return result, nil
}

// PrivateRecall is used to recall a one-to-one chat message.
/*
*
*@param  senderID: The sender's user ID.
*@param  targetID: The receiver's user ID.
*@param  uID: The unique identifier of the message, returned by the SDK after a successful message send.
*@param  sentTime: The timestamp when the message was sent, returned by the SDK after a successful message send.
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

	if extraOptins.disableUpdateLastMsg {
		req.Param("disableUpdateLastMsg", strconv.FormatBool(extraOptins.disableUpdateLastMsg))
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// PrivateSendTemplate Sends different content messages to multiple users
/*
 * @param senderID: The sender's user ID.
 * @param objectName: The type of message to be sent.
 * @param template: The message template.
 * @param content: The data content, including the message content and recipients.
 * @param options: Additional extended parameters required for sending the message.
 *
 * @return error
 */
func (rc *RongCloud) PrivateSendTemplate(senderID, objectName string, template TXTMsg, content []TemplateMsgContent,
	options ...MsgOption) (MessageResult, error) {
	result := MessageResult{}
	if senderID == "" {
		return result, RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/private/publish_template." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	var toUserIDs, push, pushData []string
	var values []map[string]string

	for _, v := range content {
		if v.TargetID == "" {
			return result, RCErrorNew(1002, "Paramer 'TargetID' is required")
		}
		toUserIDs = append(toUserIDs, v.TargetID)
		values = append(values, v.Data)
		push = append(push, v.PushContent)
		pushData = append(pushData, v.PushData)
	}

	bytes, err := json.Marshal(template)
	if err != nil {
		return result, err
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
	param["isCounted"] = extraOptins.isCounted

	if extraOptins.busChannel != "" {
		param["busChannel"] = extraOptins.busChannel
	}

	if extraOptins.disableUpdateLastMsg {
		param["disableUpdateLastMsg"] = strconv.FormatBool(extraOptins.disableUpdateLastMsg)
	}

	req, err = req.JSONBody(param)
	if err != nil {
		return result, err
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}
	return result, nil
}

// GroupSend Sends a group message (sends a message to a group as a user. The maximum size of a single message is 128k. The maximum number of messages that can be sent per second is 20. The maximum number of groups that can be sent to at a time is 3. For example, sending a message to 3 groups counts as 3 messages.)
/*
 * @param  senderID: The sender's user ID.
 * @param  targetID: The target group ID.
 * @param  objectName: The message type.
 * @param  userID: The targeted message feature allows sending messages to one or more specified users in the group, while other users in the group will not receive the message. This parameter is valid when targetID is a group. Note: If the "Cloud Storage for One-to-One and Group Messages" feature is enabled, targeted messages will not be stored in the cloud. This feature can be used when sending read receipts to some users in the group. (Optional)
 * @param  msg: The message content to be sent.
 * @param  pushContent: Defines the displayed Push content. If the objectName is a built-in RongCloud message type, the user will definitely receive a Push notification after sending. If it is a custom message, pushContent is the displayed Push content of the custom message. If this parameter is not provided, the user will not receive a Push notification.
 * @param  pushData: For iOS platforms, this is the additional data attached to the Push notification payload. For Android clients, the corresponding field name is pushData.
 * @param  isPersisted: When the current version has a new custom message and the old version does not have this custom message, this parameter determines whether the old version client will store the message after receiving it. 0 means not to store, 1 means to store. The default is 1 (store the message).
 * @param  isIncludeSender: Whether the sender will receive the message. 0 means not to receive, 1 means to receive. The default is 0 (not to receive).
 * @param  options: Other extended parameters needed for sending the message.
 *
 * @return error
 */
func (rc *RongCloud) GroupSend(senderID string, targetID, userID []string, objectName string, msg rcMsg,
	pushContent string, pushData string, isPersisted, isIncludeSender int, options ...MsgOption) (MessageResult, error) {
	result := MessageResult{}
	if senderID == "" {
		return result, RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if len(targetID) == 0 {
		return result, RCErrorNew(1002, "Paramer 'senderID' is required")
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
		return result, err
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

	if extraOptins.disableUpdateLastMsg {
		req.Param("disableUpdateLastMsg", strconv.FormatBool(extraOptins.disableUpdateLastMsg))
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

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}
	return result, nil
}

// Send group status message
// senderID: The sender's user ID.
// toGroupIds: The recipient group IDs. Providing multiple parameters allows sending messages to multiple groups, with a maximum of 3 groups.
// objectName: The message type.
// msg: The content of the message to be sent.
// verifyBlacklist: Whether to filter the sender's blocklist. 0 indicates no filtering, 1 indicates filtering. Default is 0 (no filtering).
// isIncludeSender: Whether the sender should receive the message. 0 indicates not receiving, 1 indicates receiving. Default is 0 (not receiving).
func (rc *RongCloud) GroupStatusSend(senderID string, toGroupIds []string, objectName string, msg rcMsg,
	verifyBlacklist int, isIncludeSender int, options ...MsgOption) (MessageResult, error) {
	result := MessageResult{}
	if senderID == "" {
		return result, RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if len(toGroupIds) == 0 {
		return result, RCErrorNew(1002, "Paramer 'toGroupIds' is required")
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
		return result, err
	}
	req.Param("content", msgr)
	req.Param("verifyBlacklist", strconv.Itoa(verifyBlacklist))
	req.Param("isIncludeSender", strconv.Itoa(isIncludeSender))
	if extraOptins.busChannel != "" {
		req.Param("busChannel", extraOptins.busChannel)
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}
	return result, nil
}

// GroupRecall Recall a group chat message
/*
* @param senderID: The sender's user ID.
* @param targetID: The recipient's user ID.
* @param uID: The unique identifier of the message, returned by the SDK after the message is sent successfully.
* @param sentTime: The timestamp when the message was sent, returned by the SDK after the message is sent successfully.
* @param int isAdmin
* @param int isDelete
* @param bool disablePush
* @return error
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

	if extraOptins.disableUpdateLastMsg {
		req.Param("disableUpdateLastMsg", strconv.FormatBool(extraOptins.disableUpdateLastMsg))
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// GroupSendMention Sends a group @ message
/*
 * @param senderID: The sender's user ID.
 * @param targetID: The recipient group ID, with a maximum of 3 groups.
 * @param objectName: The message type.
 * @param msg: The message content to be sent.
 * @param pushContent: Defines the push notification content to be displayed. If the objectName is a built-in RongCloud message type, the user will always receive a push notification. For custom messages, pushContent defines the push notification content. If not provided, the user will not receive a push notification.
 * @param pushData: For iOS platforms, this is additional data attached to the push notification payload. For Android clients, the corresponding field name is pushData.
 * @param isPersisted: Determines whether older versions of the client store the message when a new custom message type is introduced. 0 means do not store, 1 means store. Default is 1.
 * @param isIncludeSender: Determines whether the sender also receives the message. 0 means do not receive, 1 means receive. Default is 0.
 * @param isMentioned: Indicates whether this is an @ message. 0 means a regular message, 1 means an @ message. Default is 0. When set to 1, the content parameter must include mentionedInfo with the detailed content of the @ message. When set to 0, mentionedInfo is not required. If toUserId is specified, the @ user must be included in toUserId.
 * @param contentAvailable: For iOS platforms, this enables silent push notifications when the SDK is in the background. This is a push method introduced in iOS7 that allows the app to execute code in the background upon receiving the notification. 1 means enabled, 0 means disabled. Default is 0.
 *
 * @return error
 */
func (rc *RongCloud) GroupSendMention(senderID string, targetID []string, objectName string, msg MentionMsgContent,
	pushContent, pushData string, isPersisted, isIncludeSender, isMentioned, contentAvailable int, options ...MsgOption) (MessageResult, error) {
	result := MessageResult{}
	if senderID == "" {
		return result, RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if len(targetID) == 0 && len(targetID) > 3 {
		return result, RCErrorNew(1002, "Paramer 'senderID' is required")
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
		return result, err
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
	if extraOptins.disableUpdateLastMsg {
		req.Param("disableUpdateLastMsg", strconv.FormatBool(extraOptins.disableUpdateLastMsg))
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}
	return result, nil
}

// ChatRoomSend sends a message to a chatroom. (A user can send a message to a group. The maximum size of a single message is 128k. A user can send up to 20 messages per second and up to 3 groups at a time. For example, sending a message to 3 groups counts as 3 messages.)
/*
 *@param  senderID: The sender's user ID.
 *@param  targetID: The target chatroom IDs. It is recommended not to exceed 10 chatrooms.
 *@param  objectName: The message type.
 *@param  msg: The message content.
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomSend(senderID string, targetID []string, objectName string, msg rcMsg, isPersisted, isIncludeSender int) (MessageResult, error) {
	result := MessageResult{}
	if senderID == "" {
		return result, RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if len(targetID) == 0 {
		return result, RCErrorNew(1002, "Paramer 'senderID' is required")
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
		return result, err
	}
	req.Param("content", msgr)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}
	return result, nil
}

// ChatRoomBroadcast Broadcasts a message to all chatrooms in the application. This feature requires the Dedicated Cloud service. (Sends a message as a user to a group, with a maximum message size of 128k. The maximum sending rate is 20 messages per second.)
/*
*@param  senderID: The sender's user ID.
*@param  objectName: The message type.
*@param  msg: The message content.
* @param isIncludeSender: 0 or 1.
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

// OnlineBroadcast Sends a message to all online users in the application. When users are actively using the app, the message will be displayed in the chat UI and conversation list, with the conversation type set to SYSTEM.
// @param fromUserId The sender's user ID.
// @param objectName The message type.
// @param content The message content.
func (rc *RongCloud) OnlineBroadcast(fromUserId string, objectName string, content string, options ...MsgOption) (MessageResult, error) {
	result := MessageResult{}

	if fromUserId == "" {
		return result, RCErrorNew(1002, "Paramer 'fromUserId' is required")
	}
	if objectName == "" {
		return result, RCErrorNew(1002, "Paramer 'objectName' is required")
	}
	if content == "" {
		return result, RCErrorNew(1002, "Paramer 'content' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/online/broadcast." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", fromUserId)
	req.Param("objectName", objectName)
	req.Param("content", content)

	if extraOptins.disableUpdateLastMsg {
		req.Param("disableUpdateLastMsg", strconv.FormatBool(extraOptins.disableUpdateLastMsg))
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}

	return result, nil
}

// SystemSend sends a system message from one user to one or multiple users. The maximum size of a single message is 128k, and the conversation type is SYSTEM.
/*
*@param  senderID: The sender's user ID.
*@param  targetID: The recipient user ID(s), with a maximum of 100 users.
*@param  objectName: The type of message being sent.
*@param  msg: The message content.
*@param  pushContent: Defines the displayed push notification content. If the objectName is a RongCloud built-in message type, the user will always receive a push notification. If it's a custom message, pushContent defines the push notification content to be displayed. If not provided, the user will not receive a push notification.
*@param  pushData: For iOS platforms, this is the additional data attached to the push notification payload. For Android clients, the corresponding field name is pushData.
*@param  count: For iOS platforms, this controls the number of unread messages displayed in the push notification. It is only effective when toUserId is a single user ID.
*@param  isPersisted: When a new version introduces a custom message that older versions do not support, this parameter determines whether the older client will store the message. 0 means not stored, 1 means stored. Default is 1 (store the message).
*@param  options: Additional extended parameters required for sending the message.

*
*@return error
 */
func (rc *RongCloud) SystemSend(senderID string, targetID []string, objectName string, msg rcMsg,
	pushContent, pushData string, count, isPersisted int, options ...MsgOption) (MessageResult, error) {
	result := MessageResult{}

	if senderID == "" {
		return result, RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	if len(targetID) == 0 {
		return result, RCErrorNew(1002, "Paramer 'targetID' is required")
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
		return result, err
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
	if extraOptins.disableUpdateLastMsg {
		req.Param("disableUpdateLastMsg", strconv.FormatBool(extraOptins.disableUpdateLastMsg))
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}
	return result, nil
}

// SystemBroadcast Sends a message to all users in the application. It can be sent up to 2 times per hour and 3 times per day. (Sends a message to a group as a user, with a maximum message size of 128k. A maximum of 20 messages can be sent per second.)
/*
*@param  senderID: The sender's user ID.
*@param  objectName: The message type.
*@param  msg: The message content to be sent.
*
*@return error
 */
func (rc *RongCloud) SystemBroadcast(senderID, objectName string, msg rcMsg, options ...MsgOption) (MessageResult, error) {
	result := MessageResult{}
	if senderID == "" {
		return result, RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/broadcast." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("fromUserId", senderID)
	req.Param("objectName", objectName)
	msgr, err := msg.ToString()
	if err != nil {
		return result, err
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
	if extraOptins.disableUpdateLastMsg {
		req.Param("disableUpdateLastMsg", strconv.FormatBool(extraOptins.disableUpdateLastMsg))
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}
	return result, nil
}

// SystemSendTemplate Sends a system message from one user to one or more users. The maximum size of a single message is 128k, and the conversation type is SYSTEM.
/*
*@param  senderID: The sender's user ID.
*@param  objectName: The type of message to be sent.
*@param  template: The message template.
*@param  content: The data content, including the message content and recipients.
*
*@return error
 */
func (rc *RongCloud) SystemSendTemplate(senderID, objectName string, template TXTMsg, content []TemplateMsgContent,
	options ...MsgOption) (MessageResult, error) {
	result := MessageResult{}
	if senderID == "" {
		return result, RCErrorNew(1002, "Paramer 'senderID' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/message/system/publish_template." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	var toUserIDs, push, pushData []string
	var values []map[string]string

	for _, v := range content {
		if v.TargetID == "" {
			return result, RCErrorNew(1002, "Paramer 'TargetID' is required")
		}
		toUserIDs = append(toUserIDs, v.TargetID)
		values = append(values, v.Data)
		push = append(push, v.PushContent)
		pushData = append(pushData, v.PushData)
	}

	bytes, err := json.Marshal(template)
	if err != nil {
		return result, err
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

	if extraOptins.disableUpdateLastMsg {
		param["disableUpdateLastMsg"] = strconv.FormatBool(extraOptins.disableUpdateLastMsg)
	}

	_, _ = req.JSONBody(param)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return result, err
	}

	if err := json.Unmarshal(resp, &result); err != nil {
		return result, err
	}
	return result, nil
}

// HistoryGet retrieves the URL of the historical message log file by hour, which includes all messages generated by the application within that hour. The message log file will be deleted from RongCloud servers after 3 days, regardless of whether it has been downloaded.
/*
* @param date: Specifies the hour, e.g., 2018030210 represents the data generated from 10:00 to 11:00 on March 2, 2018.
*
* @return History error
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

// HistoryRemove Deletes historical message log files
/*
*@param date: Specifies the hour, e.g., 2018030210 represents the data generated from 10:00 to 11:00 on March 2, 2018
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

// SetMessageExpansion Sets message extension
// When sending a message, if expansion is set to true, you can set extended information for this message. Up to 100 custom attributes can be set at a time, with a maximum of 300 in total.
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

// DeleteMessageExpansion Deletes message expansion
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

// QueryMessageExpansion Retrieves message extension information
// Retrieves the specified message extension information based on the Message UID
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

type QueryHistoryMessageModel struct {
	UserID       string `json:"userId"`
	TargetID     string `json:"targetId"`
	BusChannel   string `json:"busChannel,omitempty"`
	StartTime    int64  `json:"startTime"`
	EndTime      int64  `json:"endTime"`
	PageSize     int    `json:"pageSize,omitempty"`
	IncludeStart bool   `json:"includeStart"`
}

type HistoryMessageResponse struct {
	Code int              `json:"code"`
	Data []HistoryMessage `json:"data"`
}

// HistoryMessage represents a historical message
type HistoryMessage struct {
	TargetID     string `json:"targetId"`             // Conversation ID
	FromUserID   string `json:"fromUserId"`           // Message sender ID
	MsgUID       string `json:"messageUID"`           // Message ID
	MsgTime      int64  `json:"msgTime"`              // Message timestamp
	ObjectName   string `json:"objectName"`           // Message type
	Content      string `json:"content"`              // Message content
	Expansion    bool   `json:"expansion"`            // Whether the message is extensible
	ExtraContent string `json:"extraContent"`         // Message extension content, JSON structure
	BusChannel   string `json:"busChannel,omitempty"` // Business channel, optional
}

// GetPrivateHistoryMessage
/*
 * @param model: QueryHistoryMessageModel
 * @return HistoryMessageResponse, error
 */
func (rc *RongCloud) GetPrivateHistoryMessage(model QueryHistoryMessageModel) (HistoryMessageResponse, error) {
	var result HistoryMessageResponse

	if err := validateHistoryMessageModel(model); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/v3/message/private/query.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeaderV2(req)

	params := buildQueryHistoryMessageBody(model)

	var err error
	req, err = req.JSONBody(params)
	if err != nil {
		return result, err
	}

	res, err := rc.do(req)
	if err != nil {
		return result, fmt.Errorf("request failed: %w", err)
	}

	if err := json.Unmarshal(res, &result); err != nil {
		return result, fmt.Errorf("unmarshal response failed: %w", err)
	}

	return result, nil
}

// validateHistoryMessageModel
func validateHistoryMessageModel(model QueryHistoryMessageModel) error {
	if model.UserID == "" {
		return RCErrorNew(1002, "Parameter 'userId' is required")
	}
	if model.TargetID == "" {
		return RCErrorNew(1002, "Parameter 'targetId' is required")
	}
	if model.StartTime == 0 {
		return RCErrorNew(1002, "Parameter 'startTime' is required")
	}
	if model.EndTime == 0 {
		return RCErrorNew(1002, "Parameter 'endTime' is required")
	}
	return nil
}

// buildQueryHistoryMessageBody
func buildQueryHistoryMessageBody(model QueryHistoryMessageModel) map[string]string {
	jsonBody := map[string]string{
		"userId":       model.UserID,
		"targetId":     model.TargetID,
		"startTime":    strconv.FormatInt(model.StartTime, 10),
		"endTime":      strconv.FormatInt(model.EndTime, 10),
		"includeStart": strconv.FormatBool(model.IncludeStart),
	}
	if model.PageSize > 0 {
		jsonBody["pageSize"] = strconv.Itoa(model.PageSize)
	}
	if model.BusChannel != "" {
		jsonBody["busChannel"] = model.BusChannel
	}

	return jsonBody
}

// GetGroupHistoryMessage
/*
 * @param model: QueryHistoryMessageModel
 * @return HistoryMessageResponse, error
 */
func (rc *RongCloud) GetGroupHistoryMessage(model QueryHistoryMessageModel) (HistoryMessageResponse, error) {
	var result HistoryMessageResponse

	if err := validateHistoryMessageModel(model); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/v3/message/group/query.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeaderV2(req)

	params := buildQueryHistoryMessageBody(model)

	var err error
	req, err = req.JSONBody(params)
	if err != nil {
		return result, err
	}

	res, err := rc.do(req)
	if err != nil {
		return result, err
	}

	if err := json.Unmarshal(res, &result); err != nil {
		return result, err
	}

	return result, nil
}

// GetUltraGroupHistoryMessage
/*
 * @param model: QueryHistoryMessageModel
 * @return HistoryMessageResponse, error
 */
func (rc *RongCloud) GetUltraGroupHistoryMessage(model QueryHistoryMessageModel) (HistoryMessageResponse, error) {
	var result HistoryMessageResponse
	if err := validateHistoryMessageModel(model); err != nil {
		return result, err
	}
	req := httplib.Post(rc.rongCloudURI + "/v3/message/ultragroup/query.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeaderV2(req)

	params := buildQueryHistoryMessageBody(model)

	var err error
	req, err = req.JSONBody(params)
	if err != nil {
		return result, err
	}

	res, err := rc.do(req)
	if err != nil {
		return result, err
	}

	if err := json.Unmarshal(res, &result); err != nil {
		return result, err
	}

	return result, nil
}

// GetChatroomHistoryMessage
/*
 * @param model: QueryHistoryMessageModel
 * @return HistoryMessageResponse, error
 */
func (rc *RongCloud) GetChatroomHistoryMessage(model QueryHistoryMessageModel) (HistoryMessageResponse, error) {
	var result HistoryMessageResponse

	if err := validateHistoryMessageModel(model); err != nil {
		return result, err
	}

	req := httplib.Post(rc.rongCloudURI + "/v3/message/chatroom/query.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeaderV2(req)

	params := buildQueryHistoryMessageBody(model)

	var err error
	req, err = req.JSONBody(params)
	if err != nil {
		return result, err
	}

	res, err := rc.do(req)
	if err != nil {
		return result, err
	}

	if err := json.Unmarshal(res, &result); err != nil {
		return result, err
	}

	return result, nil
}
