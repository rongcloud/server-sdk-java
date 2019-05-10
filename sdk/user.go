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

// UserRegister 注册用户，生成用户在融云的唯一身份标识 Token
/*
*@param  userID:用户 ID，最大长度 64 字节.是用户在 App 中的唯一标识码，必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。
*@param  name:用户名称，最大长度 128 字节.用来在 Push 推送时显示用户的名称.用户名称，最大长度 128 字节.用来在 Push 推送时显示用户的名称。
*@param  portraitURI:用户头像 URI，最大长度 1024 字节.用来在 Push 推送时显示用户的头像。
*
*@return User, error
 */
func (rc *rongCloud) UserRegister(userID, name, portraitURI string) (User, error) {
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
func (rc *rongCloud) UserUpdate(userID, name, portraitURI string) error {
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
func (rc *rongCloud) BlockAdd(id string, minute uint64) error {
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
func (rc *rongCloud) BlockRemove(id string) error {
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
func (rc *rongCloud) BlockGetList() (BlockListResult, error) {
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
func (rc *rongCloud) BlacklistAdd(id string, blacklist []string) error {
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
func (rc *rongCloud) BlacklistRemove(id string, blacklist []string) error {
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
func (rc *rongCloud) BlacklistGet(id string) (BlacklistResult, error) {
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
func (rc *rongCloud) OnlineStatusCheck(userID string) (int, error) {
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
