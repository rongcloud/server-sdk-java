// ChatRoom chatroom

package sdk

import (
	"encoding/json"
	"fmt"
	"strconv"
	"time"

	"github.com/astaxie/beego/httplib"
)

// ChatRoomInfo chatroom information
type ChatRoomInfo struct {
	ID   string `json:"id"`
	Name string `json:"name"`
}

// ChatRoom chatroom information
type ChatRoom struct {
	ChatRoomID string `json:"chrmId"`
	Name       string `json:"name"`
	Time       string `json:"time"`
}

// ChatRoomQueryResult chatroom query interface return data
type ChatRoomQueryResult struct {
	ChatRooms []ChatRoom `json:"chatRooms"`
}

// ChatRoomResult ChatRoom return result
type ChatRoomResult struct {
	Total            int            `json:"total"`
	Users            []ChatRoomUser `json:"users"`
	Result           []ChatRoomUser `json:"result"`
	ObjectNames      []string       `json:"objectNames"`
	ChatRoomIDs      []string       `json:"chatroomids"`
	WhitelistMsgType []string       `json:"whitlistMsgType"`
}

// Query chatroom information return result
type ChatRoomGetResult struct {
	Code        int    `json:"code"`
	ChatroomId  string `json:"chatroomId"`
	CreateTime  int64  `json:"createTime"`
	MemberCount int    `json:"memberCount"`
	DestroyType int    `json:"destroyType"`
	DestroyTime int    `json:"destroyTime"`
	IsBan       bool   `json:"ban"`
}

// ChatRoomUser Chatroom user information
type ChatRoomUser struct {
	ID       string `json:"id"`
	UserID   string `json:"userId"`
	Time     string `json:"time"`
	IsInChrm int    `json:"isInChrm"`
}

// ChatRoomAttr Chatroom custom attributes structure
type ChatRoomAttr struct {
	Key         string `json:"key"`
	Value       string `json:"value"`
	UserID      string `json:"userID"`
	AutoDelete  string `json:"autoDelete"`
	LastSetTime string `json:"lastSetTime"`
}

// ChatRoomAttrResult Chatroom custom attributes return result
type ChatRoomAttrResult struct {
	Keys []ChatRoomAttr `json:"keys"`
}

// ChatUserExistObj : Return value of ChatUserExistResObj
type ChatUserExistObj struct {
	// 200: Success.
	Code int `json:"code"`

	// Indicates whether the user is in the chatroom, true means in the chatroom, false means not in the chatroom.
	IsInChrm bool `json:"isInChrm"`
}

// chatroomOptions is extra options for chatroom
type chatroomOptions struct {
	needNotify   bool
	extra        string
	destroyType  int
	destroyTime  int
	isBan        bool
	whiteUserIds []string
	entryOwnerId string
	entryInfo    map[string]interface{}
}

// ChatroomOption Interface function
type ChatroomOption func(*chatroomOptions)

// Whether to notify members. Default is false (no notification)
func WithChatroomNeedNotify(isNeedNotify bool) ChatroomOption {
	return func(options *chatroomOptions) {
		options.needNotify = isNeedNotify
	}
}

// The extra information in JSON format carried by the notification, valid only when needNotify is true.
func WithChatroomExtra(extra string) ChatroomOption {
	return func(options *chatroomOptions) {
		options.extra = extra
	}
}

// Specifies the chatroom destruction type: 0: Default value, means destruction when inactive, 1: Fixed time destruction
func WithChatroomDestroyType(destroyType int) ChatroomOption {
	return func(options *chatroomOptions) {
		options.destroyType = destroyType
	}
}

// Sets the chatroom destruction time, effective when destroyType=1, unit is minutes, minimum 60 minutes, maximum 10080 minutes (7 days). If not set, defaults to 60 minutes.
func WithChatroomDestroyTime(destroyTime int) ChatroomOption {
	return func(options *chatroomOptions) {
		options.destroyTime = destroyTime
	}
}

// Whether to mute all members of the chatroom, default is false
func WithChatroomIsBan(isBan bool) ChatroomOption {
	return func(options *chatroomOptions) {
		options.isBan = isBan
	}
}

// The allowlist of user IDs for muting, supports batch setting, maximum of 20 users
func WithChatroomWhiteUserIds(whiteUserIds []string) ChatroomOption {
	return func(options *chatroomOptions) {
		options.whiteUserIds = whiteUserIds
	}
}

// The user ID to whom the chatroom custom attributes belong
func WithChatroomEntryOwnerId(entryOwnerId string) ChatroomOption {
	return func(options *chatroomOptions) {
		options.entryOwnerId = entryOwnerId
	}
}

// The KV pairs of chatroom custom attributes, in JSON structure
func WithChatroomEntryInfo(entryInfo map[string]interface{}) ChatroomOption {
	return func(options *chatroomOptions) {
		options.entryInfo = entryInfo
	}
}

// Modify default values
func modifyChatroomOptions(options []ChatroomOption) chatroomOptions {
	// Default values
	defaultOptions := chatroomOptions{
		needNotify:   false,
		extra:        "",
		destroyType:  0,
		destroyTime:  0,
		isBan:        false,
		whiteUserIds: []string{},
		entryOwnerId: "",
		entryInfo:    map[string]interface{}{},
	}

	// Modify default values
	for _, ext := range options {
		ext(&defaultOptions)
	}

	return defaultOptions
}

// ChatUserExistResObj :/chatroom/user/exist.json Check if a user has joined a chatroom
//*
//  @param: chatroomId, the ID of the chatroom to query
//  @param: userId, the ID of the user to query
//  response: ChatUserExistObj
//*//
func (rc *RongCloud) ChatUserExistResObj(chatroomId, userId string) (ChatUserExistObj, error) {
	var (
		result = ChatUserExistObj{}
	)
	if len(chatroomId) == 0 {
		return result, RCErrorNew(1002, "Parameter 'chatroomId' is required")
	}
	if len(userId) == 0 {
		return result, RCErrorNew(1002, "Parameter 'userId' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/exist.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("chatroomId", chatroomId)
	req.Param("userId", userId)

	res, err := rc.do(req)
	if err != nil {
		return result, err
	}
	if err := json.Unmarshal(res, &result); err != nil {
		return result, err
	}
	return result, err
}

// ChatUserExist :/chatroom/user/exist.json Check if a user has joined a chatroom
//*
//  @param: chatroomId, the ID of the chatroom to query
//  @param: userId, the ID of the user to query
//  response: byte array
//*//
func (rc *RongCloud) ChatUserExist(chatroomId, userId string) ([]byte, error) {
	if len(chatroomId) == 0 {
		return nil, RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}
	if len(userId) == 0 {
		return nil, RCErrorNew(1002, "Paramer 'userId' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/exist.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("chatroomId", chatroomId)
	req.Param("userId", userId)

	res, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return res, err
}

// ChatRoomCreate Create a chatroom
/*
 *@param  id: the ID of the chatroom to create;
 *@param  name: the name of the chatroom to create.
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomCreate(id, name string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}
	if name == "" {
		return RCErrorNew(1002, "Paramer 'name' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/create." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("chatroom["+id+"]", name)

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// Create a chatroom
func (rc *RongCloud) ChatRoomCreateNew(chatroomId string, options ...ChatroomOption) error {
	if chatroomId == "" {
		return RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}

	extOptions := modifyChatroomOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/chatroom/create_new." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("chatroomId", chatroomId)
	req.Param("destroyType", strconv.Itoa(extOptions.destroyType))
	req.Param("destroyTime", strconv.Itoa(extOptions.destroyTime))
	req.Param("isBan", strconv.FormatBool(extOptions.isBan))

	fmt.Println(strconv.FormatBool(extOptions.isBan))

	for _, v := range extOptions.whiteUserIds {
		req.Param("whiteUserIds", v)
	}

	if "" != extOptions.entryOwnerId {
		req.Param("entryOwnerId", extOptions.entryOwnerId)
	}

	if len(extOptions.entryInfo) > 0 {
		entryInfo, err := json.Marshal(extOptions.entryInfo)
		if err != nil {
			return err
		}
		req.Param("entryInfo", string(entryInfo))
	}

	_, err := rc.do(req)

	if err != nil {
		rc.urlError(err)
	}
	return err
}

// Set the chatroom destruction type
func (rc *RongCloud) ChatRoomDestroySet(chatroomId string, destroyType, destroyTime int) error {
	if chatroomId == "" {
		return RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/destroy/set." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("chatroomId", chatroomId)
	req.Param("destroyType", strconv.Itoa(destroyType))
	req.Param("destroyTime", strconv.Itoa(destroyTime))

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// Query chatroom information
func (rc *RongCloud) ChatRoomGetNew(chatroomId string) (ChatRoomGetResult, error) {
	if chatroomId == "" {
		return ChatRoomGetResult{}, RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/get." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", chatroomId)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return ChatRoomGetResult{}, err
	}
	fmt.Println(string(resp))
	var dat ChatRoomGetResult
	if err := json.Unmarshal(resp, &dat); err != nil {
		return ChatRoomGetResult{}, err
	}
	return dat, nil
}

// Batch set chatroom custom attributes (KV)
func (rc *RongCloud) ChatRoomEntryBatchSet(chatroomId string, autoDelete int, entryOwnerId string, entryInfo map[string]interface{}) error {
	if chatroomId == "" {
		return RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}

	if entryOwnerId == "" {
		return RCErrorNew(1002, "Paramer 'entryOwnerId' is required")
	}

	if len(entryInfo) < 1 {
		return RCErrorNew(1002, "Paramer 'entryInfo' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/entry/batch/set." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("chatroomId", chatroomId)
	req.Param("autoDelete", strconv.Itoa(autoDelete))

	req.Param("entryOwnerId", entryOwnerId)

	entryInfoJson, e := json.Marshal(entryInfo)
	if e != nil {
		return e
	}
	req.Param("entryInfo", string(entryInfoJson))

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomDestroy Destroys a chatroom
/**
 *
 *@param  id: The ID of the chatroom to be destroyed.
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomDestroy(id string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/destroy." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("chatroomId", id)

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomGet Query chatroom members
/*
 *@param  id: The ID of the chatroom to query.
 *@param  count: The number of chatroom members to retrieve, with a maximum limit of 500. If exceeded, only 500 members will be returned.
 *@param  order: The order in which members joined the chatroom. 1 for ascending order by join time, 2 for descending order by join time.
 *
 *@return ChatRoomResult error
 */
func (rc *RongCloud) ChatRoomGet(id string, count, order int) (ChatRoomResult, error) {
	if id == "" {
		return ChatRoomResult{}, RCErrorNew(1002, "Parameter 'id' is required")
	}

	if count <= 0 {
		return ChatRoomResult{}, RCErrorNew(1002, "Parameter 'count' is required")
	}

	if order <= 0 {
		return ChatRoomResult{}, RCErrorNew(1002, "Parameter 'order' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)
	req.Param("count", strconv.Itoa(count))
	req.Param("order", strconv.Itoa(order))

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return ChatRoomResult{}, err
	}
	var dat ChatRoomResult
	if err := json.Unmarshal(resp, &dat); err != nil {
		return ChatRoomResult{}, err
	}
	return dat, nil
}

// ChatRoomIsExist checks if users exist in a chatroom
/*
 * @param id: The ID of the chatroom to query.
 * @param members: A list of members to check, with a maximum of 1000 members per request.
 *
 * @return ChatRoomResult error
 */
func (rc *RongCloud) ChatRoomIsExist(id string, members []string) ([]ChatRoomUser, error) {
	if id == "" {
		return []ChatRoomUser{}, RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return []ChatRoomUser{}, RCErrorNew(1002, "Paramer 'count' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/users/exist." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)
	for _, v := range members {
		req.Param("userId", v)
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return []ChatRoomUser{}, err
	}
	var dat ChatRoomResult
	if err := json.Unmarshal(resp, &dat); err != nil {
		return []ChatRoomUser{}, err
	}
	return dat.Result, nil
}

// ChatRoomBlockAdd adds members to the chatroom blocklist
/**
 *
 * @param id: The ID of the chatroom.
 * @param members: A list of members to be blocked.
 * @param minute: The duration of the block in minutes, with a maximum value of 43200 minutes.
 *
 * @return error
 */
func (rc *RongCloud) ChatRoomBlockAdd(id string, members []string, minute uint, options ...ChatroomOption) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	if minute == 0 {
		return RCErrorNew(1002, "Paramer 'minute' is required")
	}
	extOptions := modifyChatroomOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/block/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)
	for _, v := range members {
		req.Param("userId", v)
	}
	req.Param("minute", strconv.Itoa(int(minute)))

	if extOptions.needNotify {
		req.Param("needNotify", strconv.FormatBool(extOptions.needNotify))
		req.Param("extra", extOptions.extra)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomBlockRemove Remove banned users from the chatroom
/*
 *
 *@param  id: Chatroom ID.
 *@param  members: List of users.
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomBlockRemove(id string, members []string, options ...ChatroomOption) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}
	extOptions := modifyChatroomOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/block/rollback." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	req.Param("chatroomId", id)
	if extOptions.needNotify {
		req.Param("needNotify", strconv.FormatBool(extOptions.needNotify))
		req.Param("extra", extOptions.extra)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomBlockGetList Query the list of banned chatroom members
/*
 *@param  id: The ID of the chatroom.
 *
 *@return ChatRoomResult error
 */
func (rc *RongCloud) ChatRoomBlockGetList(id string) (ChatRoomResult, error) {
	var dat ChatRoomResult
	if id == "" {
		return dat, RCErrorNew(1002, "Paramer 'id' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/block/list." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return dat, err
	}
	if err := json.Unmarshal(resp, &dat); err != nil {
		return dat, err
	}
	return dat, nil
}

// ChatRoomBanAdd Add a global mute for the chatroom
/*
 *@param  members: The list of members, with a maximum of 20.

 *@param  minute: Mute duration, range: 1 - 1 * 30 * 24 * 60 minutes.
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomBanAdd(members []string, minute uint, options ...ChatroomOption) error {

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}
	if minute == 0 {
		return RCErrorNew(1002, "Paramer 'minute' is required")
	}
	extOptions := modifyChatroomOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/ban/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	req.Param("minute", strconv.Itoa(int(minute)))
	if extOptions.needNotify {
		req.Param("needNotify", strconv.FormatBool(extOptions.needNotify))
		req.Param("extra", extOptions.extra)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomBanRemove Unmute all chatrooms
/*
 *@param  members: Member list, up to 20 members.
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomBanRemove(members []string, options ...ChatroomOption) error {

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}
	extOptions := modifyChatroomOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/ban/remove." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	if extOptions.needNotify {
		req.Param("needNotify", strconv.FormatBool(extOptions.needNotify))
		req.Param("extra", extOptions.extra)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomBanGetList Get the list of globally muted users in the chatroom
/*
 *@return []ChatRoomUser error
 */
func (rc *RongCloud) ChatRoomBanGetList() ([]ChatRoomUser, error) {
	var dat ChatRoomResult
	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/ban/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return []ChatRoomUser{}, err
	}
	if err := json.Unmarshal(resp, &dat); err != nil {
		return []ChatRoomUser{}, err
	}
	return dat.Users, nil
}

// ChatRoomGagAdd Mute a user in the chatroom (In the App, if you do not want a specific user to send messages in the chatroom, you can mute the user. The muted user can still receive and view messages in the chatroom but cannot send messages.)
/*
 *
 *@param  id: Chatroom ID.
 *@param  members: List of users to be muted.
 *@param  minute: Duration of the mute in minutes, with a maximum value of 43200 minutes. (Required)
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomGagAdd(id string, members []string, minute uint, options ...ChatroomOption) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	if minute == 0 {
		return RCErrorNew(1002, "Paramer 'minute' is required")
	}
	extOptions := modifyChatroomOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/gag/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	req.Param("chatroomId", id)
	req.Param("minute", strconv.Itoa(int(minute)))
	if extOptions.needNotify {
		req.Param("needNotify", strconv.FormatBool(extOptions.needNotify))
		req.Param("extra", extOptions.extra)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomGagRemove Remove the gag from chatroom members
/*
 * @param id: The ID of the chatroom.
 * @param members: The list of members to remove the gag from.
 *
 * @return error
 */
func (rc *RongCloud) ChatRoomGagRemove(id string, members []string, options ...ChatroomOption) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}
	extOptions := modifyChatroomOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/gag/rollback." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	req.Param("chatroomId", id)

	if extOptions.needNotify {
		req.Param("needNotify", strconv.FormatBool(extOptions.needNotify))
		req.Param("extra", extOptions.extra)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomGagGetList Query the list of muted chatroom members
/*
 * @param  id: Chatroom ID. (Required)
 *
 * @return []ChatRoomUser error
 */
func (rc *RongCloud) ChatRoomGagGetList(id string) ([]ChatRoomUser, error) {
	var dat ChatRoomResult
	if id == "" {
		return []ChatRoomUser{}, RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/gag/list." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return []ChatRoomUser{}, err
	}
	if err := json.Unmarshal(resp, &dat); err != nil {
		return []ChatRoomUser{}, err
	}
	return dat.Users, nil
}

// ChatRoomDemotionAdd Add chatroom message priority
/*
 * @param  objectName: List of message types, up to 20.
 * @return err
 */
func (rc *RongCloud) ChatRoomDemotionAdd(objectNames []string) error {
	if len(objectNames) == 0 {
		return RCErrorNew(1002, "Paramer 'objectName' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/message/priority/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range objectNames {
		req.Param("objectName", v)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomDemotionRemove Remove the downgraded messages in the chatroom
/*
 * @param objectName: List of message types.
 * @return err
 */
func (rc *RongCloud) ChatRoomDemotionRemove(objectNames []string) error {
	if len(objectNames) == 0 {
		return RCErrorNew(1002, "Paramer 'objectName' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/message/priority/remove." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range objectNames {
		req.Param("objectName", v)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomDemotionGetList Get the list of downgraded messages in the chatroom
/*
 * @return []string error
 */
func (rc *RongCloud) ChatRoomDemotionGetList() ([]string, error) {
	var dat ChatRoomResult

	req := httplib.Post(rc.rongCloudURI + "/chatroom/message/priority/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return []string{}, err
	}
	if err := json.Unmarshal(resp, &dat); err != nil {
		return []string{}, err
	}
	return dat.ObjectNames, nil
}

// ChatRoomDistributionStop Stops message distribution in a chatroom (This method allows you to control whether messages in a chatroom are distributed. Once distribution is stopped, messages sent by users in the chatroom will not be forwarded to other users by the RongCloud server.)
/*
 * @param  id: The chatroom ID.
 * @return error
 */
func (rc *RongCloud) ChatRoomDistributionStop(id string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/message/stopDistribution." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomDistributionResume Resumes message distribution in a chatroom
/*
 * @param  id: The chatroom ID.
 * @return error
 */
func (rc *RongCloud) ChatRoomDistributionResume(id string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/chatroom/message/resumeDistribution." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomKeepAliveAdd Add a chatroom to keepalive list
/*
 *@param  id: Chatroom ID.
 *@return error
 */
func (rc *RongCloud) ChatRoomKeepAliveAdd(id string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/chatroom/keepalive/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomKeepAliveRemove Remove a chatroom from keepalive list
/*
 *@param  id: Chatroom ID.
 *@return error
 */
func (rc *RongCloud) ChatRoomKeepAliveRemove(id string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/chatroom/keepalive/remove." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomKeepAliveGetList Get the list of keepalive chatrooms
/*
 *@param  id: Chatroom ID.

 *@return []string error
 */
func (rc *RongCloud) ChatRoomKeepAliveGetList() ([]string, error) {
	var dat ChatRoomResult
	// if id == "" {
	// 	return []string{}, RCErrorNew(1002, "Paramer 'chatroomId' is required")
	// }
	req := httplib.Post(rc.rongCloudURI + "/chatroom/keepalive/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	// req.Param("chatroomId", id)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return []string{}, err
	}
	if err := json.Unmarshal(resp, &dat); err != nil {
		return []string{}, err
	}
	return dat.ChatRoomIDs, nil
}

// ChatRoomWhitelistAdd Add a message to the chatroom allowlist
/*
 *@param  objectNames: List of message types.
 *@return error
 */
func (rc *RongCloud) ChatRoomWhitelistAdd(objectNames []string) error {

	if len(objectNames) == 0 {
		return RCErrorNew(1002, "Paramer 'objectNames' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/whitelist/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range objectNames {
		req.Param("objectnames", v)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomWhitelistRemove Remove a message from the chatroom allowlist
/*
 *@param  objectNames: List of message types.
 *@return error
 */
func (rc *RongCloud) ChatRoomWhitelistRemove(objectNames []string) error {

	if len(objectNames) == 0 {
		return RCErrorNew(1002, "Paramer 'objectNames' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/whitelist/delete." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	for _, v := range objectNames {
		req.Param("objectnames", v)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomWhitelistGetList Get the chatroom message allowlist
/*
 *@return []string error
 */
func (rc *RongCloud) ChatRoomWhitelistGetList() ([]string, error) {
	var dat ChatRoomResult

	req := httplib.Post(rc.rongCloudURI + "/chatroom/whitelist/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return []string{}, err
	}
	if err := json.Unmarshal(resp, &dat); err != nil {
		return []string{}, err
	}

	return dat.WhitelistMsgType, nil
}

// ChatRoomUserWhitelistAdd Add a user to the chatroom allowlist
/*
 *@param  id: Chatroom ID.
 *@param  members: The allowlist, with a maximum of 5 members.
 *@return error
 */
func (rc *RongCloud) ChatRoomUserWhitelistAdd(id string, members []string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/whitelist/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)
	for _, v := range members {
		req.Param("userId", v)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomUserWhitelistRemove Removes users from the allowlist
/*
 *@param  id: The chatroom ID.
 *@param  members: The allowlist, with a maximum of 5 members.
 *@return error
 */
func (rc *RongCloud) ChatRoomUserWhitelistRemove(id string, members []string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/whitelist/remove." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)
	for _, v := range members {
		req.Param("userId", v)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomUserWhitelistGetList Get the allowlist of users in a chatroom
/*
 * @param id: The ID of the chatroom.
 * @return []string error
 */
func (rc *RongCloud) ChatRoomUserWhitelistGetList(id string) ([]string, error) {
	var dat map[string]interface{}
	if id == "" {
		return []string{}, RCErrorNew(1002, "Paramer 'id' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/whitelist/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)

	response, err := req.Response()
	if err != nil {
		return []string{}, err
	}

	rc.checkStatusCode(response)

	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return []string{}, err
	}
	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return []string{}, err
	}
	if code.Code != 200 {
		return []string{}, code
	}
	if err := json.Unmarshal(rep, &dat); err != nil {
		return []string{}, err
	}
	if dat["users"] == nil {
		return []string{}, nil
	}
	var users []string
	for _, v := range dat["users"].([]interface{}) {
		users = append(users, v.(string))
	}
	return users, nil
}

// ChatRoomMuteMembersAdd Adds muted members to a chatroom (In the app, if you want to prevent a user from sending messages in a chatroom, you can mute the user. Muted users can still receive and view messages in the chatroom but cannot send messages.)
/*
 *
 *@param  id: The chatroom ID.
 *@param  members: The list of users to be muted.
 *@param  minute: The duration of the mute in minutes, with a maximum value of 43200 minutes. (Required)
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomMuteMembersAdd(id string, members []string, minute uint, options ...ChatroomOption) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	if minute == 0 {
		return RCErrorNew(1002, "Paramer 'minute' is required")
	}
	extOptions := modifyChatroomOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/gag/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}

	req.Param("chatroomId", id)
	req.Param("minute", strconv.Itoa(int(minute)))
	if extOptions.needNotify {
		req.Param("needNotify", strconv.FormatBool(extOptions.needNotify))
		req.Param("extra", extOptions.extra)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomMuteMembersGetList Retrieves the list of muted members in a chatroom.
/*
*@param  id: The ID of the chatroom. (Required)
*
*@return []ChatRoomUser error
 */
func (rc *RongCloud) ChatRoomMuteMembersGetList(id string) ([]ChatRoomUser, error) {
	var dat ChatRoomResult
	if id == "" {
		return []ChatRoomUser{}, RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/gag/list." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)

	response, err := req.Response()
	if err != nil {
		return []ChatRoomUser{}, err
	}

	rc.checkStatusCode(response)

	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return []ChatRoomUser{}, err
	}
	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return []ChatRoomUser{}, err
	}
	if code.Code != 200 {
		return []ChatRoomUser{}, code
	}
	if err := json.Unmarshal(rep, &dat); err != nil {
		return []ChatRoomUser{}, err
	}
	return dat.Users, nil
}

// ChatRoomMuteMembersRemove Removes muted members from the chatroom
/*
 *@param  id: The ID of the chatroom.
 *@param  members: List of members to unmute
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomMuteMembersRemove(id string, members []string, options ...ChatroomOption) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}
	extOptions := modifyChatroomOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/gag/rollback." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	req.Param("chatroomId", id)

	if extOptions.needNotify {
		req.Param("needNotify", strconv.FormatBool(extOptions.needNotify))
		req.Param("extra", extOptions.extra)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ChatRoomEntrySet Sets custom attributes for a chatroom
/**
 * @param	chatRoomID	The ID of the chatroom
 * @param	userID		The ID of the user performing the operation. Can be set via Server API by non-chatroom users.
 * @param	key			The name of the chatroom attribute. Supports uppercase and lowercase letters, numbers, and special characters + = - _. Case sensitive. Maximum length: 128 characters
 * @param	value		The value of the chatroom attribute. Maximum length: 4096 characters
 * @param	autoDelete	Whether to delete this key after the user exits the chatroom. true: delete this key; false: do not delete this key after the user exits
 *
 * @retrun error
 */
func (rc *RongCloud) ChatRoomEntrySet(chatRoomID, userID, key, value string, autoDelete int) error {
	if chatRoomID == "" {
		return RCErrorNew(1002, "Paramer 'chatRoomID' is required")
	}

	if userID == "" {
		return RCErrorNew(1002, "Paramer 'userID' is required")
	}

	if key == "" {
		return RCErrorNew(1002, "Paramer 'key' is required")
	}

	if value == "" {
		return RCErrorNew(1002, "Paramer 'value' is required")
	}

	if autoDelete != 0 && autoDelete != 1 {
		return RCErrorNew(1002, "Parameter 'autoDelete' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/entry/set." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("chatroomId", chatRoomID)
	req.Param("userId", userID)
	req.Param("key", key)
	req.Param("value", value)
	req.Param("autoDelete", strconv.Itoa(autoDelete))

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}

	return err
}

// ChatRoomEntryRemove Remove custom attributes of a chatroom
/**
 * @param	chatRoomID	The ID of the chatroom
 * @param	userID		The ID of the user performing the operation. It can be set by a non-chatroom user via Server API.
 * @param	key			The name of the chatroom attribute. The key supports a combination of uppercase and lowercase letters, numbers, and special characters + = - _. It is case-sensitive with a maximum length of 128 characters.
 *
 * @return error
 */
func (rc *RongCloud) ChatRoomEntryRemove(chatRoomID, userID, key string) error {
	if chatRoomID == "" {
		return RCErrorNew(1002, "Paramer 'chatRoomID' is required")
	}

	if userID == "" {
		return RCErrorNew(1002, "Paramer 'userID' is required")
	}

	if key == "" {
		return RCErrorNew(1002, "Paramer 'key' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/entry/remove." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("chatroomId", chatRoomID)
	req.Param("userId", userID)
	req.Param("key", key)

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}

	return err
}

// ChatRoomEntryQuery Query custom attributes of a chatroom
/**
 * @param ChatRoomID	The ID of the chatroom
 * @param keys			Specify the keys to retrieve from the chatroom. Up to 100 keys can be specified. If empty, all keys will be retrieved.
 *
 * @return []ChatRoomAttr	List of attributes
 * @return error 			Error
 */
func (rc *RongCloud) ChatRoomEntryQuery(chatRoomID string, keys ...string) ([]ChatRoomAttr, error) {
	if chatRoomID == "" {
		return nil, RCErrorNew(1002, "Paramer 'chatRoomID' is required")
	}
	if len(keys) > 100 {
		return nil, RCErrorNew(1002, "Paramer 'keys' more than 100")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/entry/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("chatroomId", chatRoomID)
	if len(keys) != 0 {
		for k := range keys {
			req.Param("keys", keys[k])
		}
	}
	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return nil, err
	}
	var data ChatRoomAttrResult
	if err := json.Unmarshal(resp, &data); err != nil {
		return nil, err
	}
	return data.Keys, nil
}

// ChatRoomQuery Query basic information of a chatroom
/**
 * @param chatRoomID	The ID of the chatroom to query

 * @return []ChatRoom	Array of chatroom information
 * @return error 		Error message
 *
 */
func (rc *RongCloud) ChatRoomQuery(chatRoomID []string) ([]ChatRoom, error) {
	if len(chatRoomID) <= 0 {
		return nil, RCErrorNew(1002, "Paramer 'chatRoomID' is required")
	}

	url := fmt.Sprintf(`%s/chatroom/query.%s`, rc.rongCloudURI, ReqType)
	req := httplib.Post(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	for _, v := range chatRoomID {
		req.Param("chatroomId", v)
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return nil, err
	}

	var data ChatRoomQueryResult
	if err := json.Unmarshal(resp, &data); err != nil {
		return nil, err
	}

	return data.ChatRooms, nil
}

// Mute all users in the chatroom
func (rc *RongCloud) ChatRoomBan(chatroomId string, options ...ChatroomOption) error {
	if chatroomId == "" {
		return RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}

	extOptions := modifyChatroomOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/chatroom/ban/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", chatroomId)
	if extOptions.needNotify {
		req.Param("needNotify", strconv.FormatBool(extOptions.needNotify))
		req.Param("extra", extOptions.extra)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// Rollback mute all chatrooms
func (rc *RongCloud) ChatRoomBanRollback(chatroomId string, options ...ChatroomOption) error {
	if chatroomId == "" {
		return RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}

	extOptions := modifyChatroomOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/chatroom/ban/rollback." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", chatroomId)
	if extOptions.needNotify {
		req.Param("needNotify", strconv.FormatBool(extOptions.needNotify))
		req.Param("extra", extOptions.extra)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// Query the list of muted chatrooms
func (rc *RongCloud) ChatRoomBanQuery(size, page int) ([]string, error) {
	req := httplib.Post(rc.rongCloudURI + "/chatroom/ban/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("page", strconv.Itoa(page))
	req.Param("size", strconv.Itoa(size))

	resp, err := rc.do(req)
	if err != nil {
		return []string{}, err
	}
	var dat ChatRoomResult
	if err := json.Unmarshal(resp, &dat); err != nil {
		return []string{}, err
	}

	return dat.ChatRoomIDs, nil
}

// Check the mute all status of a chatroom
func (rc *RongCloud) ChatRoomBanCheck(chatroomId string) (bool, error) {
	if chatroomId == "" {
		return false, RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/ban/check." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", chatroomId)

	resp, err := rc.do(req)
	if err != nil {
		return false, err
	}

	data := struct {
		Code   int `json:"code"`
		Status int `json:"status"`
	}{}

	if err := json.Unmarshal(resp, &data); err != nil {
		return false, err
	}

	if data.Status == 1 {
		return true, nil
	} else {
		return false, nil
	}
}

// Add users to the chatroom mute exceptions list
func (rc *RongCloud) ChatRoomUserBanWhitelistAdd(chatroomId string, members []string, options ...ChatroomOption) error {
	if chatroomId == "" {
		return RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	extOptions := modifyChatroomOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/ban/whitelist/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	req.Param("chatroomId", chatroomId)
	if extOptions.needNotify {
		req.Param("needNotify", strconv.FormatBool(extOptions.needNotify))
		req.Param("extra", extOptions.extra)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// Remove users from the chatroom mute exceptions list
func (rc *RongCloud) ChatRoomUserBanWhitelistRollback(chatroomId string, members []string, options ...ChatroomOption) error {
	if chatroomId == "" {
		return RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	extOptions := modifyChatroomOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/ban/whitelist/rollback." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	req.Param("chatroomId", chatroomId)
	if extOptions.needNotify {
		req.Param("needNotify", strconv.FormatBool(extOptions.needNotify))
		req.Param("extra", extOptions.extra)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// Query the chatroom mute exceptions list
func (rc *RongCloud) ChatRoomUserBanWhitelistQuery(chatroomId string) ([]string, error) {
	if chatroomId == "" {
		return []string{}, RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/ban/whitelist/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", chatroomId)

	resp, err := rc.do(req)
	if err != nil {
		return []string{}, err
	}
	data := struct {
		Code    int      `json:"code"`
		UserIds []string `json:"userIds"`
	}{}

	if err := json.Unmarshal(resp, &data); err != nil {
		return []string{}, err
	}

	return data.UserIds, nil
}
