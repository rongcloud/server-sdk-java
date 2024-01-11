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

// User 用户信息 返回信息
type User struct {
	Token        string `json:"token"`
	UserID       string `json:"userId"`
	BlockEndTime string `json:"blockEndTime,omitempty"`
	Status       string `json:"status,omitempty"`
}

// BlockListResult 返回信息
type BlockListResult struct {
	Users []User `json:"users"`
}

// BlacklistResult 返回信息
type BlacklistResult struct {
	Users []string `json:"users"`
}

// Tag TagSet 参数
type Tag struct {
	UserID string   `json:"userId"` // 用户 Id。（必传）
	Tags   []string `json:"tags"`   // 用户标签，一个用户最多添加 20 个标签，每个 tag 最大不能超过 40 个字节，标签中不能包含特殊字符。（必传）
}

// TagBatch TagBatchSet 参数
type TagBatch struct {
	UserIDs []string `json:"userIds"` // 用户 Id，一次最多支持 1000 个用户。（必传）
	Tags    []string `json:"tags"`    // 用户标签，一个用户最多添加 20 个标签，每个 tag 最大不能超过 40 个字节，标签中不能包含特殊字符。（必传）
}

// TagResult TagGet 返回值
type TagResult struct {
	*CodeResult
	Result map[string][]string `json:"result"` // 用户所有的标签数组。
}

// WhiteList QueryWhiteList 返回数据
type WhiteList struct {
	Users []string `json:"users"`
}

// UserBlockPushPeriodDelete 删除用户免打扰时段 /user/blockPushPeriod/delete.json
// *
// @param: userId  用户id，必传
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

// UserBlockPushPeriodGet UserBlockPushPeriodGet:  查用户免打扰时段 /user/blockPushPeriod/get.json
// *
// @param: userId  用户id，必传
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

// UserBlockPushPeriodSet :添加户免打扰时段 /user/blockPushPeriod/set.json
// *
//
//	@param :userId 用户ID  必传
//	@param :startTime 开始时间（秒） 必传
//	@param :period  时段 (分钟)     必传
//	@param :level   免打扰级别  默认 1  不是必传
//	form表单
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

// UserTokenExpireObj ：的返回值定义
type UserTokenExpireObj struct {
	// 返回码，200 为正常
	Code int `json:"code"`
}

// UserTokenExpireResObj /user/token/expire.json Token 失效
// *
//
//	@param: userId: 必传 需要设置 Token 失效的用户 ID，支持设置多个最多不超过 20 个
//	@param: time: 必传 过期时间戳精确到毫秒，该时间戳前用户获取的 Token 全部失效，使用时间戳之前的 Token 已经在连接中的用户不会立即失效，断开后无法进行连接。
//	response: UserTokenExpireObj
//	文档： https://doc.rongcloud.cn/imserver/server/v1/user/expire
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

// UserTokenExpire /user/token/expire.json Token 失效
// *
//
//	@param: userId: 必传 需要设置 Token 失效的用户 ID，支持设置多个最多不超过 20 个
//	@param: time: 必传 过期时间戳精确到毫秒，该时间戳前用户获取的 Token 全部失效，使用时间戳之前的 Token 已经在连接中的用户不会立即失效，断开后无法进行连接。
//	response: byte数组
//	文档 ：https://doc.rongcloud.cn/imserver/server/v1/user/expire
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

// UserRemarksGetObj :UserRemarksGetResObj 的返回值
type UserRemarksGetObj struct {
	// 返回码，200 为正常。
	Code int `json:"code"`

	// 用户的备注名总数。
	Total int `json:"total"`

	// JSON 对象数组，包含用户 ID（id）和对应的备注名（remark）。单次最多返回 50 个用户备注名。
	Users []UserRemarksUsers `json:"users"`
}

type UserRemarksUsers struct {
	// 用户id
	Id string `json:"id"`

	// 备注名字
	Remark string `json:"remark"`
}

// UserRemarksGetResObj /user/remarks/get.json  查询用户级送备注名
// *
//
//	@param: userId :用户ID。
//	@param: page :页数，默认为第一页。
//	@param: size :每页条数，默认每页 50 条
//	response： UserRemarksGetObj
//	文档：https://doc.rongcloud.cn/imserver/server/v1/user/get-remark-for-push
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

// UserRemarksGet /user/remarks/get.json  查询用户级送备注名
// *
//
//	@param: userId :用户ID。
//	@param: page :页数，默认为第一页。
//	@param: size :每页条数，默认每页 50 条
//
// response ：byte数组
// 文档：https://doc.rongcloud.cn/imserver/server/v1/user/get-remark-for-push
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

// UserRemarksDel /user/remarks/del.json  删除用户级送备注名
// *
//
//	@param: userId :操作者用户ID。
//	@param: targetId:需要删除推送备注名的用户 ID
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

// UserRemark :UserRemarksSet方法接收的参数
type UserRemark struct {
	// 目标用户 ID。单次最多设置 100 个
	Id string

	// 收到目标用户推送时显示的备注名。
	Remark string
}

// UserRemarksSet /user/remarks/set.json
// *
// @param: userId:用户 ID。
// @param: remarks:设置的目标用户推送备注名 JSON 字符串
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

// UserChatFbQueryListObj ： 的返回结果
type UserChatFbQueryListObj struct {
	// 返回码，200 为正常。
	Code int `json:"code"`

	// 被禁言用户总数。
	Total int `json:"total"`

	// 被禁言用户数组。
	Users []string `json:"users"`
}

// UserChatFbQueryListResObj * /user/chat/fb/querylist
// 查询禁言用户列表
// @param: num :获取行数，默认为 100，最大支持 200 个
// @param: offset :查询开始位置，默认为 0。
// @param: t  :会话类型，目前支持单聊会话 PERSON。
// response: UserChatFbQueryListObj
// 文档： https://doc.rongcloud.cn/imserver/server/v1/user/ban
// */
func (rc *RongCloud) UserChatFbQueryListResObj(num, offset int, t string) (UserChatFbQueryListObj, error) {
	var (
		result = UserChatFbQueryListObj{}
	)
	if num == 0 {
		num = 100
	}
	if len(t) == 0 {
		return result, RCErrorNew(1002, "Paramer 'type' is required")
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
// 查询禁言用户列表
// @param: num :获取行数，默认为 100，最大支持 200 个
// @param: offset :查询开始位置，默认为 0。
// @param: t  :会话类型，目前支持单聊会话 PERSON。
// response： 返回byte数组
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
// 用户单聊禁言
// @param: userId :被禁言用户 ID，支持批量设置，最多不超过 1000 个
// @param: state :禁言状态，0 解除禁言、1 添加禁言
// @param: type  :会话类型，目前支持单聊会话 PERSON
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
 * @msg: 添加用户到白名单（每秒限 100 次）
 * @param string userId
 * @param []string whiteList
 * @return: error
 */
func (rc *RongCloud) AddWhiteList(userId string, whiteList []string) error {
	if userId == "" {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}

	if len(whiteList) == 0 {
		return RCErrorNew(1002, "Paramer 'whiteList' cannot empty")
	}

	if len(whiteList) > 20 {
		return RCErrorNew(1002, "Length of paramer 'whiteList' must less than 20")
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
 * @msg: 移除白名单中用户（每秒限 100 次）
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
 * @msg: 获取某用户白名单列表（每秒限100次）
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

// UserRegister 注册用户，生成用户在融云的唯一身份标识 Token
/*
*@param  userID:用户 ID，最大长度 64 字节.是用户在 App 中的唯一标识码，必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。
*@param  name:用户名称，最大长度 128 字节.用来在 Push 推送时显示用户的名称.用户名称，最大长度 128 字节.用来在 Push 推送时显示用户的名称。
*@param  portraitURI:用户头像 URI，最大长度 1024 字节.用来在 Push 推送时显示用户的头像。可以为空
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

// UserUpdate 修改用户信息
/*
*@param  userID:用户 ID，最大长度 64 字节.是用户在 App 中的唯一标识码，必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。
*@param  name:用户名称，最大长度 128 字节。用来在 Push 推送时，显示用户的名称，刷新用户名称后 5 分钟内生效。（可选，提供即刷新，不提供忽略）
*@param  portraitURI:用户头像 URI，最大长度 1024 字节。用来在 Push 推送时显示。（可选，提供即刷新，不提供忽略）
*
*@return error
 */
func (rc *RongCloud) UserUpdate(userID, name, portraitURI string) error {
	if userID == "" {
		return RCErrorNew(1002, "Paramer 'userID' is required")
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

// BlockAdd 添加用户到黑名单
/*
*@param  id:用户 ID。
*@param  minute:封禁时长 1 - 1 * 30 * 24 * 60 分钟，最大值为 43200 分钟
*
*@return error
 */
func (rc *RongCloud) BlockAdd(id string, minute uint64) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}
	if minute > 43200 {
		return RCErrorNew(20004, "封禁时间不正确, 当前传入为 , 正确范围 1 - 1 * 30 * 24 * 60 分钟")
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

// BlockRemove 从黑名单中移除用户
/*
*@param  id:用户 ID。
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

// BlockGetList 获取某用户的黑名单列表
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

// BlacklistAdd 添加用户到黑名单方法（每秒钟限 100 次）
/*
*@param  id:用户 ID。
*@param  blacklist:被设置为黑名单的用户列表。
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

// BlacklistRemove 从黑名单中移除用户方法（每秒钟限 100 次）
/*
*@param  id:用户 ID。
*@param  blacklist:被移除黑名单列表。
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

// BlacklistGet 获取某用户的黑名单列表方法（每秒钟限 100 次）
/*
*@param  id:用户 ID。
*
*@return BlacklistResult error
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

// OnlineStatusCheck 检查用户在线状态
/*
*@param  userID:用户 ID，最大长度 64 字节.是用户在 App 中的唯一标识码，必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。
*
*@return int, error
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

// TagSet 为应用中的用户添加标签，如果某用户已经添加了标签，再次对用户添加标签时将覆盖之前设置的标签内容。
/*
*@param  tag :标签 Tag 构造体。
*
*@return error
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

// TagBatchSet 为应用中的用户批量添加标签，如果某用户已经添加了标签，再次对用户添加标签时将覆盖之前设置的标签内容。
/*
*@param  t :标签 TagBatch 构造体。
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

// TagGet 查询用户所有标签功能，支持批量查询每次最多查询 50 个用户。
/*
*@param  userIds: 用户 ID。
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
	OperateId string `json:"operateId"` // 操作 ID，为当前操作的唯一标识。开通用户注销与激活状态回调后，回调请求正文中会携带此参数。
}

// UserDeactivate 注销用户
// @param userIds []string 被注销用户 ID，最多一次 100 个
// @return string, error
// official doc https://doc.rongcloud.cn/imserver/server/v1/user/deactivate
// 发起注销后，服务端会在 15 分钟内通过回调通知注销结果。 https://doc.rongcloud.cn/imserver/server/v1/user/callback-deactivation
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
	Code  int      `json:"code"`  // 返回码，200 为正常
	Users []string `json:"users"` // 已注销的用户 ID 列表
}

// UserDeactivateQuery 查询已注销用户
// @param pageNo 分页获取注销用户列表时的当前页数，默认 1，最小 1。
// @param pageSize 分页获取注销用户列表时的每页行数，默认 50，最小 1，最大 50。
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
	Code      int    `json:"code"`      // 返回码，200 为正常。每个用户 ID 操作结果通过用户注销与激活状态回调传递。
	OperateId string `json:"operateId"` // 操作 ID，为当前操作的唯一标识。开通用户注销与激活状态回调后，回调请求正文中会携带此参数。
}

// UserReactivate 重新激活注销用户
// @param userIds []string 激活用户 ID，单次请求最多传入 100 个用户 ID。
// @return string, error
// official doc https://doc.rongcloud.cn/imserver/server/v1/user/reactivate
// 重新激活用户请通过(https://doc.rongcloud.cn/imserver/server/v1/user/callback-deactivation)接口获取重新激活结果。重复调用此接口不会报错。
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
