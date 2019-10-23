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
	"strconv"
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

	resp, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return err
	}

	var code CodeResult
	if err := json.Unmarshal(resp, &code); err != nil {
		return err
	}

	if code.Code != 200 {
		return code
	}

	return nil
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

	resp, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return err
	}

	var code CodeResult
	if err := json.Unmarshal(resp, &code); err != nil {
		return err
	}

	if code.Code != 200 {
		return code
	}

	return nil
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

	resp, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return WhiteList{}, err
	}

	var whiteList WhiteList
	var code CodeResult

	if err := json.Unmarshal(resp, &whiteList); err != nil {
		return WhiteList{}, err
	}

	if err := json.Unmarshal(resp, &code); err != nil {
		return WhiteList{}, err
	}

	if code.Code != 200 {
		return WhiteList{}, code
	}

	return whiteList, nil
}

// UserRegister 注册用户，生成用户在融云的唯一身份标识 Token
/*
*@param  userID:用户 ID，最大长度 64 字节.是用户在 App 中的唯一标识码，必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。
*@param  name:用户名称，最大长度 128 字节.用来在 Push 推送时显示用户的名称.用户名称，最大长度 128 字节.用来在 Push 推送时显示用户的名称。
*@param  portraitURI:用户头像 URI，最大长度 1024 字节.用来在 Push 推送时显示用户的头像。
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
	if portraitURI == "" {
		return User{}, RCErrorNew(1002, "Paramer 'portraitUri' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/getToken." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userID)
	req.Param("name", name)
	req.Param("portraitUri", portraitURI)

	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return User{}, err
	}

	var code CodeResult
	var userResult User

	if err := json.Unmarshal(rep, &struct {
		*CodeResult
		*User
	}{&code, &userResult}); err != nil {
		return User{}, err
	}
	if code.Code != 200 {
		return User{}, code
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
	if name == "" {
		return RCErrorNew(1002, "Paramer 'name' is required")
	}
	if portraitURI == "" {
		return RCErrorNew(1002, "Paramer 'portraitURI' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/user/refresh." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userID)
	req.Param("name", name)
	req.Param("portraitUri", portraitURI)

	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return err
	}

	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}

	if code.Code != 200 {
		return code
	}
	return nil
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

	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return err
	}

	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}

	if code.Code != 200 {
		return code
	}
	return nil
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
	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return err
	}

	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return RCErrorNew(20100, err.Error())
	}

	if code.Code != 200 {
		return code
	}
	return nil
}

// BlockGetList 获取某用户的黑名单列表
/*
*@return QueryBlockUserResult error
 */
func (rc *RongCloud) BlockGetList() (BlockListResult, error) {
	req := httplib.Post(rc.rongCloudURI + "/user/block/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return BlockListResult{}, err
	}

	var dat BlockListResult
	var code CodeResult
	if err := json.Unmarshal(rep, &dat); err != nil {
		return BlockListResult{}, err
	}
	if err := json.Unmarshal(rep, &code); err != nil {
		return BlockListResult{}, err
	}
	if code.Code != 200 {
		return BlockListResult{}, code
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

	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return err
	}

	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}

	if code.Code != 200 {
		return code
	}

	return nil
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

	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return err
	}

	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}

	if code.Code != 200 {
		return code
	}
	return nil
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

	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return BlacklistResult{}, err
	}

	var listResult BlacklistResult
	var code CodeResult
	if err := json.Unmarshal(rep, &listResult); err != nil {
		return BlacklistResult{}, err
	}
	if err := json.Unmarshal(rep, &code); err != nil {
		return BlacklistResult{}, err
	}

	if code.Code != 200 {
		return BlacklistResult{}, code
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

	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return -1, err
	}

	var code CodeResult
	var userResult User
	if err := json.Unmarshal(rep, &struct {
		*CodeResult
		*User
	}{&code, &userResult}); err != nil {
		return -1, err
	}
	if code.Code != 200 {
		return -1, code
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
	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return err
	}
	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {

		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
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

	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return err
	}
	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {

		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
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
	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return TagResult{}, err
	}

	var tag TagResult
	if err := json.Unmarshal(rep, &tag); err != nil {
		return TagResult{}, err
	}
	if tag.Code != 200 {
		return TagResult{}, RCErrorNew(tag.Code, tag.ErrorMessage)
	}

	return tag, nil

}
