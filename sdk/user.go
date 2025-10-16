/*
 * @Descripttion:
 * @version:
 * @Author: ran.ding
 * @Date: 2019-09-02 18:29:55
 * @LastEditors: ran.ding
 * @LastEditTime: 2019-09-10 11:22:38
 */
package sdk

import (
	"encoding/json"
	"fmt"
	"strconv"
	"strings"
	"time"

	"github.com/astaxie/beego/httplib"
)

// User User information response
type User struct {
	Token        string `json:"token"`
	UserID       string `json:"userId"`
	BlockEndTime string `json:"blockEndTime,omitempty"`
	Status       string `json:"status,omitempty"`
}

type UserInfoResult struct {
	UserName     string `json:"userName"`
	UserPortrait string `json:"userPortrait"`
	CreateTime   string `json:"createTime"`
}

// BlockListResult Response information
type BlockListResult struct {
	Users []User `json:"users"`
}

// BlacklistResult Response information
type BlacklistResult struct {
	Users []string `json:"users"`
}

// Tag TagSet parameters
type Tag struct {
	UserID string   `json:"userId"` // User ID (required)
	Tags   []string `json:"tags"`   // User tags. A user can have up to 20 tags, each tag cannot exceed 40 bytes, and special characters are not allowed in tags. (required)
}

// TagBatch TagBatchSet parameters
type TagBatch struct {
	UserIDs []string `json:"userIds"` // User IDs, up to 1000 users per request. (required)
	Tags    []string `json:"tags"`    // User tags. A user can have up to 20 tags, each tag cannot exceed 40 bytes, and special characters are not allowed in tags. (required)
}

// TagResult TagGet return value
type TagResult struct {
	*CodeResult
	Result map[string][]string `json:"result"` // Array of all tags for the user.
}

// WhiteList QueryWhiteList return data
type WhiteList struct {
	Users []string `json:"users"`
}

// UserBlockPushPeriodDelete Delete user's Do Not Disturb period /user/blockPushPeriod/delete.json
// *
// @param: userId  User ID, required
//
// *//
func (rc *RongCloud) UserBlockPushPeriodDelete(userId string) error {
	if len(userId) == 0 {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/user/blockPushPeriod/delete.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	_, err := rc.do(req)
	if err != nil {
		return err
	}
	return nil
}

type PushPeriodGet struct {
	Code int `json:"code"`
	Data struct {
		StartTime string `json:"startTime"`
		Period    int    `json:"period"`
		Level     int    `json:"unPushLevel"`
	}
}

// UserBlockPushPeriodGet UserBlockPushPeriodGet: Get user's Do Not Disturb period /user/blockPushPeriod/get.json
// *
// @param: userId  User ID, required
//
//	response : PushPeriodGet
//
// *//
func (rc *RongCloud) UserBlockPushPeriodGet(userId string) (PushPeriodGet, error) {
	data := PushPeriodGet{}
	if len(userId) == 0 {
		return data, RCErrorNew(1002, "Paramer 'userId' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/user/blockPushPeriod/get.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	res, err := rc.do(req)
	if err != nil {
		return data, err
	}
	if err := json.Unmarshal(res, &data); err != nil {
		return data, err
	}
	return data, nil
}

// UserInfoGet retrieves user information.
//
// Parameters:
//
//	userId - User ID used to query user information. It cannot be empty.
//
// Return Values:
//
//	UserInfoResult type containing user information.
//	error type if an error occurs during execution.
//
// This function sends a POST request to the RongCloud server to retrieve
// user information based on the provided userId.
// If the userId is empty, an error message will be returned.
func (rc *RongCloud) UserInfoGet(userId string) (UserInfoResult, error) {
	data := UserInfoResult{}
	if len(userId) == 0 {
		return data, RCErrorNew(1002, "Parameter 'userId' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/user/info.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	res, err := rc.do(req)
	if err != nil {
		return data, err
	}
	if err := json.Unmarshal(res, &data); err != nil {
		return data, err
	}
	return data, nil
}

// UserBlockPushPeriodSet : Set user's Do Not Disturb period /user/blockPushPeriod/set.json
// *
//
//	@param :userId User ID (required)
//	@param :startTime Start time (in seconds) (required)
//	@param :period Duration (in minutes) (required)
//	@param :level Do Not Disturb Level (default is 1, optional)
//	form data
//
// *//
func (rc *RongCloud) UserBlockPushPeriodSet(userId, startTime, period, level string) error {
	if len(userId) <= 0 {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}
	if len(startTime) <= 0 {
		return RCErrorNew(1002, "Paramer 'startTime' is required")
	}
	if len(period) <= 0 {
		return RCErrorNew(1002, "Paramer 'period' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/blockPushPeriod/set.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	req.Param("startTime", startTime)
	req.Param("period", period)
	if len(level) > 0 {
		req.Param("level", level)
	} else {
		req.Param("level", strconv.Itoa(1))
	}
	_, err := rc.do(req)
	if err != nil {
		return err
	}
	return nil
}

// UserTokenExpireObj: Return value definition
type UserTokenExpireObj struct {
	// Return code, 200 indicates success
	Code int `json:"code"`
}

// UserTokenExpireResObj /user/token/expire.json Token expiration
// *
//
//	@param: userId: Required The user ID(s) for which the Token needs to be invalidated. Supports multiple users, up to 20.
//	@param: time: Required The expiration timestamp in milliseconds. All Tokens obtained before this timestamp will be invalidated. Users already connected with Tokens obtained before this timestamp will not be immediately disconnected but will be unable to reconnect after disconnection.
//	response: UserTokenExpireObj
//	Documentation: https://doc.rongcloud.cn/imserver/server/v1/user/expire
//
// *//
func (rc *RongCloud) UserTokenExpireResObj(userId string, t int64) (UserTokenExpireObj, error) {
	var result = UserTokenExpireObj{}
	if len(userId) == 0 {
		return result, RCErrorNew(1002, "Paramer 'userId' is required")
	}
	if t <= 0 {
		return result, RCErrorNew(1002, "Paramer 'time' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/token/expire.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	req.Param("time", fmt.Sprintf("%v", t))
	res, err := rc.do(req)
	if err != nil {
		return result, err
	}
	if err := json.Unmarshal(res, &result); err != nil {
		return result, err
	}
	return result, err
}

// UserTokenExpire /user/token/expire.json Token expiration
// *
//
//	@param: userId: Required The user ID(s) for which the Token needs to be invalidated. Supports multiple users, up to 20.
//	@param: time: Required The expiration timestamp in milliseconds. All Tokens obtained before this timestamp will be invalidated. Users already connected with Tokens obtained before this timestamp will not be immediately disconnected but will be unable to reconnect after disconnection.
//	response: byte array

// Documentation: https://doc.rongcloud.cn/imserver/server/v1/user/expire
//
// *//
func (rc *RongCloud) UserTokenExpire(userId string, t int64) ([]byte, error) {
	if len(userId) == 0 {
		return nil, RCErrorNew(1002, "Paramer 'userId' is required")
	}
	if t <= 0 {
		return nil, RCErrorNew(1002, "Paramer 'time' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/token/expire.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	req.Param("time", fmt.Sprintf("%v", t))
	res, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return res, err
}

// UserRemarksGetObj : The return value of UserRemarksGetResObj
type UserRemarksGetObj struct {
	// Return code, 200 indicates success.
	Code int `json:"code"`

	// Total number of user remarks.
	Total int `json:"total"`

	// JSON object array containing user IDs (id) and corresponding remarks (remark). A maximum of 50 user remarks can be returned per request.
	Users []UserRemarksUsers `json:"users"`
}

type UserRemarksUsers struct {
	// User ID
	Id string `json:"id"`

	// Remark name
	Remark string `json:"remark"`
}

// UserRemarksGetResObj /user/remarks/get.json  Query user remarks
// *
//
//	@param: userId : User ID.
//	@param: page : Page number, defaults to the first page.
//	@param: size : Number of items per page, defaults to 50 items per page.
//	response： UserRemarksGetObj

// Documentation: https://doc.rongcloud.cn/imserver/server/v1/user/get-remark-for-push
//
// */
func (rc *RongCloud) UserRemarksGetResObj(userId string, page, size int) (UserRemarksGetObj, error) {
	var (
		result = UserRemarksGetObj{}
	)
	if len(userId) == 0 {
		return result, RCErrorNew(1002, "Paramer 'userId' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/user/remarks/get.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	req.Param("page", strconv.Itoa(page))
	req.Param("size", strconv.Itoa(size))
	res, err := rc.do(req)
	if err != nil {
		return result, err
	}
	if err := json.Unmarshal(res, &result); err != nil {
		return result, err
	}
	return result, err
}

// UserRemarksGet /user/remarks/get.json  Query user push remark name
// *
//
//	@param: userId : User ID.
//	@param: page : Page number, defaults to the first page.
//	@param: size : Number of items per page, defaults to 50 items per page.
//
// response : Byte array
// Documentation: https://doc.rongcloud.cn/imserver/server/v1/user/get-remark-for-push
// */
func (rc *RongCloud) UserRemarksGet(userId string, page, size int) ([]byte, error) {
	if len(userId) == 0 {
		return nil, RCErrorNew(1002, "Paramer 'userId' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/user/remarks/get.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	req.Param("page", strconv.Itoa(page))
	req.Param("size", strconv.Itoa(size))
	res, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return res, err
}

// UserRemarksDel /user/remarks/del.json  Delete user-level push remark
// *
//
//	@param: userId :Operator user ID.
//	@param: targetId: User ID for which the push remark needs to be deleted
//
// */
func (rc *RongCloud) UserRemarksDel(userId, targetId string) error {
	if len(userId) == 0 {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}
	if len(targetId) == 0 {
		return RCErrorNew(1002, "Paramer 'targetId' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/user/remarks/del.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	req.Param("targetId", targetId)
	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// UserRemark :Parameters received by the UserRemarksSet method
type UserRemark struct {
	// Target user ID. A maximum of 100 can be set at a time.
	Id string

	// The remark name displayed when receiving a push from the target user.
	Remark string
}

// UserRemarksSet /user/remarks/set.json
// *
// @param: userId: User ID.
// @param: remarks: JSON string of the target user push remarks to be set
//
// */
func (rc *RongCloud) UserRemarksSet(userId string, remarks []UserRemark) error {
	if len(userId) == 0 {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}
	if len(remarks) == 0 {
		return RCErrorNew(1002, "Paramer 'remarks' is required")
	}
	remarkList, err := json.Marshal(remarks)
	if err != nil {
		return RCErrorNew(1002, "Marshal 'remarks' err")
	}
	req := httplib.Post(rc.rongCloudURI + "/user/remarks/set.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	req.Param("remarks", string(remarkList))
	_, err = rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// UserChatFbQueryListObj: Response object for querying the list of muted users
type UserChatFbQueryListObj struct {
	// Response code, 200 indicates success.
	Code int `json:"code"`

	// Total number of muted users.
	Total int `json:"total"`

	// Array of muted users.
	Users []string `json:"users"`
}

// UserChatFbQueryListResObj * /user/chat/fb/querylist
// Query the list of muted users
// @param: num: Number of rows to fetch, default is 100, maximum supported is 200
// @param: offset: Starting position for the query, default is 0
// @param: t: Conversation type, currently supports one-to-one chat PERSON
// response: UserChatFbQueryListObj
// Documentation: https://doc.rongcloud.cn/imserver/server/v1/user/ban
// */
func (rc *RongCloud) UserChatFbQueryListResObj(num, offset int, t string) (UserChatFbQueryListObj, error) {
	var (
		result = UserChatFbQueryListObj{}
	)
	if num == 0 {
		num = 100
	}
	if len(t) == 0 {
		return result, RCErrorNew(1002, "Parameter 'type' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/chat/fb/querylist.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("num", strconv.Itoa(num))
	req.Param("offset", strconv.Itoa(offset))
	req.Param("type", t)
	res, err := rc.do(req)
	if err != nil {
		return result, err
	}
	if err := json.Unmarshal(res, &result); err != nil {
		return result, err
	}
	return result, err
}

// UserChatFbQueryList * /user/chat/fb/querylist
// Query the list of muted users
// @param: num : Number of rows to fetch, defaults to 100, maximum supported is 200
// @param: offset : Starting position for the query, defaults to 0.
// @param: t  : Conversation type, currently supports one-to-one chat PERSON.
// response： Returns a byte array
// https://doc.rongcloud.cn/imserver/server/v1/user/ban
// */
func (rc *RongCloud) UserChatFbQueryList(num, offset int, t string) ([]byte, error) {
	if num == 0 {
		num = 100
	}
	if len(t) == 0 {
		return nil, RCErrorNew(1002, "Paramer 'type' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/chat/fb/querylist.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("num", strconv.Itoa(num))
	req.Param("offset", strconv.Itoa(offset))
	req.Param("type", t)
	res, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return res, err
}

// UserChatFbSet *
// Mute a user in one-to-one chat
// @param: userId : User ID to be muted, supports batch setting, maximum of 1000 users
// @param: state : Mute state, 0 to unmute, 1 to mute
// @param: type  : Conversation type, currently supports one-to-one chat PERSON
// */
func (rc *RongCloud) UserChatFbSet(userId string, state int, t string) error {
	if len(userId) == 0 {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}
	if len(t) == 0 {
		return RCErrorNew(1002, "Paramer 'type' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/chat/fb/set.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	req.Param("state", fmt.Sprintf("%v", state))
	req.Param("type", t)
	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

/**
 * @name: AddWhiteList
 * @test:
 * @msg: Add a user to the allowlist (limited to 100 requests per second)
 * @param string userId
 * @param []string whiteList
 * @return: error
 */
func (rc *RongCloud) AddWhiteList(userId string, whiteList []string) error {
	if userId == "" {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}

	if len(whiteList) == 0 {
		return RCErrorNew(1002, "Paramer 'whiteList' cannot be empty")
	}

	if len(whiteList) > 20 {
		return RCErrorNew(1002, "Length of paramer 'whiteList' must be less than 20")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/whitelist/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	for _, v := range whiteList {
		req.Param("whiteUserId", v)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

/**
 * @name: RemoveWhiteList
 * @test:
 * @msg: Remove users from the allowlist (limit: 100 times per second)
 * @param string userId
 * @param []string whiteList
 * @return: error
 */
func (rc *RongCloud) RemoveWhiteList(userId string, whiteList []string) error {
	if userId == "" {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}

	if len(whiteList) == 0 {
		return RCErrorNew(1002, "Paramer 'whiteList' is required")
	}

	if len(whiteList) > 20 {
		return RCErrorNew(1002, "Length of paramer 'whiteList' must less than 20")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/whitelist/remove." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	for _, v := range whiteList {
		req.Param("whiteUserId", v)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

/**
 * @name: QueryWhiteList
 * @test:
 * @msg: Get the allowlist of a user (limit: 100 times per second)
 * @param string userId
 * @return: WhiteList error
 */
func (rc *RongCloud) QueryWhiteList(userId string) (WhiteList, error) {
	if userId == "" {
		return WhiteList{}, RCErrorNew(1002, "Paramer 'userId' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/whitelist/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return WhiteList{}, err
	}

	var whiteList WhiteList
	if err := json.Unmarshal(resp, &whiteList); err != nil {
		return WhiteList{}, err
	}
	return whiteList, nil
}

// UserRegister Registers a user and generates a unique Token for the user in RongCloud.
/*
*@param  userID: User ID, maximum length of 64 bytes. It is the unique identifier for the user within the App. It must be unique within the same App, and duplicate user IDs will be treated as the same user.
*@param  name: User name, maximum length of 128 bytes. Used to display the user's name in push notifications.
*@param  portraitURI: User avatar URI, maximum length of 1024 bytes. Used to display the user's avatar in push notifications. Can be empty.
*
*@return User, error
 */
func (rc *RongCloud) UserRegister(userID, name, portraitURI string) (User, error) {
	if userID == "" {
		return User{}, RCErrorNew(1002, "Paramer 'userID' is required")
	}
	if name == "" {
		return User{}, RCErrorNew(1002, "Paramer 'name' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/getToken." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userID)
	req.Param("name", name)
	if portraitURI != "" {
		req.Param("portraitUri", portraitURI)
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return User{}, err
	}

	var userResult User
	if err := json.Unmarshal(resp, &userResult); err != nil {
		return User{}, err
	}
	return userResult, nil
}

// UserUpdate Updates user information
/*
*@param  userID: User ID, maximum length 64 bytes. It is the unique identifier of the user within the App. It must be ensured that it is not duplicated within the same App. Duplicate user IDs will be treated as the same user.
*@param  name: User name, maximum length 128 bytes. It is used to display the user's name in push notifications. The user name will take effect within 5 minutes after refreshing. (Optional, refresh if provided, ignore if not provided)
*@param  portraitURI: User avatar URI, maximum length 1024 bytes. It is used to display the user's avatar in push notifications. (Optional, refresh if provided, ignore if not provided)
*
*@return error
 */
func (rc *RongCloud) UserUpdate(userID, name, portraitURI string) error {
	if userID == "" {
		return RCErrorNew(1002, "Parameter 'userID' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/refresh." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userID)
	req.Param("name", name)
	req.Param("portraitUri", portraitURI)

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// BlockAdd Adds a user to the blocklist
/*
*@param  id: User ID.
*@param  minute: Ban duration, 1 - 1 * 30 * 24 * 60 minutes, maximum value is 43200 minutes
*
*@return error
 */
func (rc *RongCloud) BlockAdd(id string, minute uint64) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}
	if minute > 43200 {
		return RCErrorNew(20004, "Invalid ban duration, current input is , valid range is 1 - 1 * 30 * 24 * 60 minutes")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/block." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", id)
	req.Param("minute", strconv.FormatUint(minute, 10))

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// BlockRemove Remove a user from the blocklist
/*
*@param  id: User ID.
*
*@return error
 */
func (rc *RongCloud) BlockRemove(id string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/user/unblock." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", id)

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// BlockGetList Get the blocklist of a user
/*
*@return QueryBlockUserResult error
 */
func (rc *RongCloud) BlockGetList() (BlockListResult, error) {
	req := httplib.Post(rc.rongCloudURI + "/user/block/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return BlockListResult{}, err
	}

	var dat BlockListResult
	if err := json.Unmarshal(resp, &dat); err != nil {
		return BlockListResult{}, err
	}

	return dat, nil
}

// BlacklistAdd Adds users to the blocklist (limited to 100 times per second)
/*
*@param  id: User ID.
*@param  blacklist: List of users to be added to the blocklist.
*
*@return error
 */
func (rc *RongCloud) BlacklistAdd(id string, blacklist []string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}
	if len(blacklist) == 0 {
		return RCErrorNew(1002, "Paramer 'blacklist' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/blacklist/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", id)
	for _, v := range blacklist {
		req.Param("blackUserId", v)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// BlacklistRemove Removes users from the blocklist (limited to 100 times per second)
/*
*@param  id: User ID.
*@param  blacklist: List of users to be removed from the blocklist.
*
*@return error
 */
func (rc *RongCloud) BlacklistRemove(id string, blacklist []string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}
	if len(blacklist) == 0 {
		return RCErrorNew(1002, "Paramer 'blacklist' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/blacklist/remove." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", id)
	for _, v := range blacklist {
		req.Param("blackUserId", v)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// BlocklistGet Retrieves the blocklist of a user (limited to 100 requests per second)
/*
*@param  id: User ID.
*
*@return BlocklistResult error
 */
func (rc *RongCloud) BlacklistGet(id string) (BlacklistResult, error) {
	if id == "" {
		return BlacklistResult{}, RCErrorNew(1002, "Paramer 'id' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/blacklist/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", id)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return BlacklistResult{}, err
	}

	var listResult BlacklistResult
	if err := json.Unmarshal(resp, &listResult); err != nil {
		return BlacklistResult{}, err
	}
	return listResult, nil
}

// OnlineStatusCheck Checks the online status of a user

/*
* @param userID: User ID, maximum length of 64 bytes. It is the unique identifier of the user within the App, and must be unique within the same App. Duplicate user IDs will be treated as the same user.
*
* @return int, error
 */
func (rc *RongCloud) OnlineStatusCheck(userID string) (int, error) {
	if userID == "" {
		return -1, RCErrorNew(1002, "Paramer 'userID' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/checkOnline." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userID)

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return -1, err
	}
	var userResult User
	if err := json.Unmarshal(resp, &userResult); err != nil {
		return -1, err
	}
	status, _ := strconv.Atoi(userResult.Status)
	return status, nil
}

// TagSet Adds tags to a user in the App. If a user already has tags, adding new tags will overwrite the previous tag content.
/*
* @param tag: The Tag struct for the tag.
*
* @return error
 */
func (rc *RongCloud) TagSet(tag Tag) error {
	req := httplib.Post(rc.rongCloudURI + "/user/tag/set." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req, err := req.JSONBody(tag)
	if err != nil {
		rc.urlError(err)
		return err
	}

	_, err = rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// TagBatchSet Adds tags to users in batch for the application. If a user already has tags, adding new tags will overwrite the previous ones.
/*
*@param  t : The TagBatch struct.
*
*@return error
 */
func (rc *RongCloud) TagBatchSet(tagBatch TagBatch) error {
	req := httplib.Post(rc.rongCloudURI + "/user/tag/batch/set." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req, err := req.JSONBody(tagBatch)
	if err != nil {
		rc.urlError(err)
		return err
	}

	_, err = rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// TagGet Queries all tags for users, supporting batch queries with a maximum of 50 users per query.
/*
*@param  userIds: User IDs.
*
*@return error
 */
func (rc *RongCloud) TagGet(userIds []string) (TagResult, error) {
	req := httplib.Post(rc.rongCloudURI + "/user/tags/get." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range userIds {
		req.Param("userIds", v)
	}

	resp, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
		return TagResult{}, err
	}

	var tag TagResult
	if err := json.Unmarshal(resp, &tag); err != nil {
		return TagResult{}, err
	}
	return tag, nil

}

type UserDeactivateResponse struct {
	Code      int    `json:"code"`
	OperateId string `json:"operateId"` // Operation ID, the unique identifier for the current operation. This parameter is included in the callback request body after enabling the User Deactivation and Activation Status Callback.
}

// UserDeactivate Deactivates users
// @param userIds []string User IDs to be deactivated, up to 100 at a time
// @return string, error
// official doc https://doc.rongcloud.cn/imserver/server/v1/user/deactivate
// After initiating deactivation, the server will notify the deactivation result via callback within 15 minutes. https://doc.rongcloud.cn/imserver/server/v1/user/callback-deactivation
func (rc *RongCloud) UserDeactivate(userIds []string) (*UserDeactivateResponse, error) {
	req := httplib.Post(fmt.Sprintf("%s%s", rc.rongCloudURI, "/user/deactivate.json"))
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", strings.Join(userIds, ","))
	body, err := rc.doV2(req)
	if err != nil {
		return nil, err
	}
	var userDeactivateResp UserDeactivateResponse
	err = json.Unmarshal(body, &userDeactivateResp)
	if err != nil {
		return nil, err
	}
	return &userDeactivateResp, nil
}

type UserDeactivateQueryResponse struct {
	Code  int      `json:"code"`  // Return code, 200 indicates success
	Users []string `json:"users"` // List of deactivated user IDs
}

// UserDeactivateQuery Queries deactivated users
// @param pageNo Current page number for paginated retrieval of deactivated user list, default is 1, minimum is 1.
// @param pageSize Number of rows per page for paginated retrieval of deactivated user list, default is 50, minimum is 1, maximum is 50.
// @return string, error
// official doc https://doc.rongcloud.cn/imserver/server/v1/user/query-deactivated-list
func (rc *RongCloud) UserDeactivateQuery(pageNo, pageSize int) (*UserDeactivateQueryResponse, error) {
	req := httplib.Post(fmt.Sprintf("%s/%s", rc.rongCloudURI, "/user/deactivate/query.json"))
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("pageNo", strconv.Itoa(pageNo))
	req.Param("pageSize", strconv.Itoa(pageSize))
	body, err := rc.doV2(req)
	if err != nil {
		return nil, err
	}
	var userDeactivateQueryResp UserDeactivateQueryResponse
	err = json.Unmarshal(body, &userDeactivateQueryResp)
	if err != nil {
		return nil, err
	}
	return &userDeactivateQueryResp, nil
}

type UserReactivateResponse struct {
	Code      int    `json:"code"`      // Response code, 200 indicates success. The result of each user ID operation is passed through the User Deactivation and Activation Status Callback.
	OperateId string `json:"operateId"` // Operation ID, the unique identifier for the current operation. This parameter is included in the callback request body after enabling the User Deactivation and Activation Status Callback.
}

// UserReactivate Reactivate deactivated users
// @param userIds []string User IDs to reactivate, with a maximum of 100 user IDs per request.
// @return string, error
// Official documentation: https://doc.rongcloud.cn/imserver/server/v1/user/reactivate
// To get the reactivation result, use the (https://doc.rongcloud.cn/imserver/server/v1/user/callback-deactivation) interface. Repeated calls to this interface will not result in errors.
func (rc *RongCloud) UserReactivate(userIds []string) (*UserReactivateResponse, error) {
	req := httplib.Post(fmt.Sprintf("%s%s", rc.rongCloudURI, "/user/reactivate.json"))
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", strings.Join(userIds, ","))
	body, err := rc.doV2(req)
	if err != nil {
		return nil, err
	}
	var userReactivateResp UserReactivateResponse
	err = json.Unmarshal(body, &userReactivateResp)
	if err != nil {
		return nil, err
	}
	return &userReactivateResp, nil
}

// UserProfileSet /user/profile/set.json User profile settings
// *
//
//	@param: userId: Required The user ID to be set
//	@param: userProfile: Optional Basic user information
//	@param: userExtProfile: Optional Extended user information
//	response: err
//
// *//
func (rc *RongCloud) UserProfileSet(userId string, userProfile string, userExtProfile string) error {
	if len(userId) == 0 {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/profile/set.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	req.Param("userProfile", userProfile)
	req.Param("userExtProfile", userExtProfile)
	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// UserProfileClean /user/profile/clean.json Clear User Profile Hosting
// *
//
//	@param: userId: Required The user ID to be set
//	response: err
//
// *//
func (rc *RongCloud) UserProfileClean(userId string) error {
	if len(userId) == 0 {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/profile/clean.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

type UserProfileResponse struct {
	UserId         string `json:"userId"`
	Version        int    `json:"version"`
	UserProfile    string `json:"userProfile"`
	UserExtProfile string `json:"userExtProfile"`
}

type UserProfileQueryResponse struct {
	Code         int                   `json:"code"` // Response code, 200 indicates success
	UserProfiles []UserProfileResponse `json:"userList"`
}

// UserProfilQuery /user/profile/query.json Paginate and Retrieve All User Profiles in the Application
// *
//
//	@param: page: Optional Page number

//	@param: size: Optional. Specifies the number of items per page.
//	@param: order: Optional. Defines the sorting mechanism based on registration time. Default is ascending order. 0 for ascending, 1 for descending.
//	response: UserProfileQueryResponse, err
//
// *//
func (rc *RongCloud) UserProfilQuery(page int, size int, order int) (*UserProfileQueryResponse, error) {
	req := httplib.Post(rc.rongCloudURI + "/user/profile/query.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("page", strconv.Itoa(page))
	req.Param("size", strconv.Itoa(size))
	req.Param("order", strconv.Itoa(order))
	body, err := rc.doV2(req)
	if err != nil {
		return nil, err
	}

	var userProfileQueryResponse UserProfileQueryResponse
	err = json.Unmarshal(body, &userProfileQueryResponse)
	if err != nil {
		return nil, err
	}
	return &userProfileQueryResponse, nil
}

// UserProfilBatchQuery /user/profile/batch/query.json Batch query user profiles
// *
//
//	@param: userId: Required. Specifies the user ID to be queried.
//	response: UserProfileQueryResponse, err
//
// *//
func (rc *RongCloud) UserProfilBatchQuery(userId string) (*UserProfileQueryResponse, error) {
	if len(userId) == 0 {
		return nil, RCErrorNew(1002, "Paramer 'userId' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/profile/batch/query.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	body, err := rc.doV2(req)
	if err != nil {
		return nil, err
	}

	var userProfileQueryResponse UserProfileQueryResponse
	err = json.Unmarshal(body, &userProfileQueryResponse)
	if err != nil {
		return nil, err
	}
	return &userProfileQueryResponse, nil
}

// UserQueryItem represents a single user record returned by /user/query.json
type UserQueryItem struct {
	UserId       string `json:"userId"`
	UserName     string `json:"userName"`
	UserPortrait string `json:"userPortrait"`
	RegTime      string `json:"regTime"`
}

// UserQueryResponse is the response of /user/query.json (non-200 codes are raised by rc.do)
type UserQueryResponse struct {
	Total int             `json:"total"`
	Users []UserQueryItem `json:"users"`
}

// UserQueryOrder represents sort order for user query.
type UserQueryOrder int

const (
	UserQueryOrderAsc  UserQueryOrder = 0
	UserQueryOrderDesc UserQueryOrder = 1
)

// userQueryParams holds query params with defaults applied.
type userQueryParams struct {
	page     int
	pageSize int
	order    UserQueryOrder
}

// UserQueryOption is a functional option to customize user query parameters.
type UserQueryOption func(*userQueryParams)

// WithPage sets the page number (must be > 0; otherwise default is used).
func WithPage(page int) UserQueryOption {
	return func(p *userQueryParams) {
		if page > 0 {
			p.page = page
		}
	}
}

// WithPageSize sets page size (clamped to [1, 100]; default 20).
func WithPageSize(size int) UserQueryOption {
	return func(p *userQueryParams) {
		if size <= 0 {
			return
		}
		if size > 100 {
			size = 100
		}
		p.pageSize = size
	}
}

// WithOrder sets sort order (0 asc, 1 desc; invalid values fallback to asc).
func WithOrder(order UserQueryOrder) UserQueryOption {
	return func(p *userQueryParams) {
		if order != UserQueryOrderAsc && order != UserQueryOrderDesc {
			order = UserQueryOrderAsc
		}
		p.order = order
	}
}

// UserQuery queries users with functional options.
// Defaults: page=1, pageSize=20, order=asc.
func (rc *RongCloud) UserQuery(opts ...UserQueryOption) (UserQueryResponse, error) {
	params := userQueryParams{
		page:     1,
		pageSize: 20,
		order:    UserQueryOrderAsc,
	}
	for _, opt := range opts {
		opt(&params)
	}

	req := httplib.Post(rc.rongCloudURI + "/user/query.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("page", strconv.Itoa(params.page))
	req.Param("pageSize", strconv.Itoa(params.pageSize))
	req.Param("order", strconv.Itoa(int(params.order)))

	res, err := rc.do(req)
	var result = UserQueryResponse{}
	if err != nil {
		return result, err
	}
	if err := json.Unmarshal(res, &result); err != nil {
		return result, err
	}
	return result, nil
}

// UserDelUsers deletes users (up to 100 per request).
// @param userIds User IDs to delete, length 1-100
// @return error
func (rc *RongCloud) UserDelUsers(userIds []string) error {
	if len(userIds) == 0 {
		return RCErrorNew(1002, "Paramer 'userIds' is required")
	}
	if len(userIds) > 100 {
		return RCErrorNew(1002, "Length of paramer 'userIds' must be less than or equal to 100")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/delusers.json")
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, id := range userIds {
		req.Param("userId", id)
	}
	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}
