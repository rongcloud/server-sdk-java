// ChatRoom 聊天室

package sdk

import (
	"encoding/json"
	"strconv"
	"time"

	"github.com/astaxie/beego/httplib"
)

// ChatRoomInfo 聊天室信息
type ChatRoomInfo struct {
	ID   string `json:"id"`
	Name string `json:"name"`
}

// ChatRoom 聊天室信息
type ChatRoom struct {
	ChatRoomID string `json:"chrmId"`
	Name       string `json:"name"`
	Time       string `json:"time"`
}

// ChatRoomResult ChatRoom 返回结果
type ChatRoomResult struct {
	Total            int            `json:"total"`
	Users            []ChatRoomUser `json:"users"`
	Result           []ChatRoomUser `json:"result"`
	ObjectNames      []string       `json:"objectNames"`
	ChatRoomIDs      []string       `json:"chatroomids"`
	WhitelistMsgType []string       `json:"whitlistMsgType"`
}

// ChatRoomUser 聊天室用户信息
type ChatRoomUser struct {
	ID       string `json:"id"`
	UserID   string `json:"userId"`
	Time     string `json:"time"`
	IsInChrm int    `json:"isInChrm"`
}

// ChatRoomCreate 创建聊天室方法
/*
 *@param  id:要创建的聊天室的ID；
 *@param  name:要创建的聊天室的name。
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

// ChatRoomDestroy 销毁聊天室方法
/**
 *
 *@param  id:要销毁的聊天室 ID。
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

// ChatRoomGet 查询聊天室内用户方法
/*
 *@param  id:要查询的聊天室 ID。
 *@param  count:要获取的聊天室成员数，上限为 500 ，超过 500 时最多返回 500 个成员。
 *@param  order:加入聊天室的先后顺序， 1 为加入时间正序， 2 为加入时间倒序。
 *
 *@return ChatRoomResult error
 */
func (rc *RongCloud) ChatRoomGet(id string, count, order int) (ChatRoomResult, error) {
	if id == "" {
		return ChatRoomResult{}, RCErrorNew(1002, "Paramer 'id' is required")
	}

	if count <= 0 {
		return ChatRoomResult{}, RCErrorNew(1002, "Paramer 'count' is required")
	}

	if order <= 0 {
		return ChatRoomResult{}, RCErrorNew(1002, "Paramer 'order' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)
	req.Param("count", strconv.Itoa(count))
	req.Param("order", strconv.Itoa(order))
	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return ChatRoomResult{}, err
	}
	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return ChatRoomResult{}, err
	}
	if code.Code != 200 {
		return ChatRoomResult{}, code
	}

	var dat ChatRoomResult
	if err := json.Unmarshal(rep, &dat); err != nil {
		return ChatRoomResult{}, err
	}
	return dat, nil
}

// ChatRoomIsExist 检查用户是否在聊天室
/*
 *@param  id:要查询的聊天室 ID。
 *@param  members:每次最多 1000 个成员。
 *
 *@return ChatRoomResult error
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
	var dat ChatRoomResult
	if err := json.Unmarshal(rep, &dat); err != nil {
		return []ChatRoomUser{}, err
	}
	return dat.Result, nil
}

// ChatRoomBlockAdd 添加封禁聊天室成员方法
/**
 *
 *@param  id:聊天室 Id。
 *@param  members:封禁列表。
 *@param  minute:封禁时长，以分钟为单位，最大值为43200分钟。
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomBlockAdd(id string, members []string, minute uint) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	if minute == 0 {
		return RCErrorNew(1002, "Paramer 'minute' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/block/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)
	for _, v := range members {
		req.Param("userId", v)
	}

	req.Param("minute", strconv.Itoa(int(minute)))
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

// ChatRoomBlockRemove 移除封禁聊天室成员方法
/*
 *
 *@param  id:聊天室 ID。
 *@param  members: 用户列表。
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomBlockRemove(id string, members []string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/block/rollback." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	req.Param("chatroomId", id)
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

// ChatRoomBlockGetList 查询被封禁聊天室成员方法
/*
 *@param  id:聊天室 ID。
 *
 *@return ChatRoomResult error
 */
func (rc *RongCloud) ChatRoomBlockGetList(id string) (ChatRoomResult, error) {
	var dat ChatRoomResult
	var code CodeResult
	if id == "" {
		return dat, RCErrorNew(1002, "Paramer 'id' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/block/list." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return dat, err
	}

	if err := json.Unmarshal(rep, &code); err != nil {
		return dat, err
	}
	if code.Code != 200 {
		return dat, code
	}

	if err := json.Unmarshal(rep, &dat); err != nil {
		return dat, err
	}

	return dat, nil
}

// ChatRoomBanAdd 添加聊天室全局禁言
/*
 *@param  members:成员列表，最多不超过 20 个。
 *@param  minute:禁言时长，范围: 1 - 1 * 30 * 24 * 60 分钟。
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomBanAdd(members []string, minute uint) error {

	var code CodeResult
	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}
	if minute == 0 {
		return RCErrorNew(1002, "Paramer 'minute' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/ban/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	req.Param("minute", strconv.Itoa(int(minute)))
	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return err
	}

	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return code
	}

	return nil
}

// ChatRoomBanRemove 解除聊天室全局禁言
/*
 *@param  members:成员列表，最多不超过 20 个。
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomBanRemove(members []string) error {

	var code CodeResult
	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/ban/remove." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return err
	}

	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return code
	}

	return nil
}

// ChatRoomBanGetList 获取聊天室全局禁言列表
/*
 *@return []ChatRoomUser error
 */
func (rc *RongCloud) ChatRoomBanGetList() ([]ChatRoomUser, error) {
	var code CodeResult
	var dat ChatRoomResult
	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/ban/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return []ChatRoomUser{}, err
	}
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

// ChatRoomGagAdd 添加禁言聊天室成员方法（在 App 中如果不想让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息.）
/*
 *
 *@param  id:聊天室 ID。
 *@param  members:禁言列表。
 *@param  minute:禁言时长，以分钟为单位，最大值为43200分钟。（必传）
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomGagAdd(id string, members []string, minute uint) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	if minute == 0 {
		return RCErrorNew(1002, "Paramer 'minute' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/gag/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}

	req.Param("chatroomId", id)
	req.Param("minute", strconv.Itoa(int(minute)))
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

// ChatRoomGagRemove 移除禁言聊天室成员方法
/*
 *@param  id:聊天室Id。
 *@param  members:解除禁言列表
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomGagRemove(id string, members []string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/gag/rollback." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	req.Param("chatroomId", id)
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

// ChatRoomGagGetList 查询被禁言聊天室成员方法
/*
 *@param  id:聊天室 ID。（必传）
 *
 *@return []ChatRoomUser error
 */
func (rc *RongCloud) ChatRoomGagGetList(id string) ([]ChatRoomUser, error) {
	var dat ChatRoomResult
	if id == "" {
		return []ChatRoomUser{}, RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}
	req := httplib.Post(RONGCLOUDURI + "/chatroom/user/gag/list." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)
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

// ChatRoomDemotionAdd 添加聊天室消息优先级方法
/*
 *@param  objectName:消息类型列表，最多 20 个。
 *@return err
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

// ChatRoomDemotionRemove 移除应用内聊天室降级消息
/*
 *@param  objectName:消息类型列表。
 *@return err
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

// ChatRoomDemotionGetList 获取应用内聊天室降级消息
/*
 *@return []string error
 */
func (rc *RongCloud) ChatRoomDemotionGetList() ([]string, error) {
	var dat ChatRoomResult

	req := httplib.Post(rc.rongCloudURI + "/chatroom/message/priority/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
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
	return dat.ObjectNames, nil
}

// ChatRoomDistributionStop 聊天室消息停止分发方法（可实现控制对聊天室中消息是否进行分发，停止分发后聊天室中用户发送的消息，融云服务端不会再将消息发送给聊天室中其他用户。）
/*
 *@param  id:聊天室 ID。
 *@return error
 */
func (rc *RongCloud) ChatRoomDistributionStop(id string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/message/stopDistribution." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)
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

// ChatRoomDistributionResume 聊天室消息恢复分发方法
/*
 *@param  id:聊天室 ID。
 *@return error
 */
func (rc *RongCloud) ChatRoomDistributionResume(id string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/chatroom/message/resumeDistribution." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)
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

// ChatRoomKeepAliveAdd 添加保活聊天室
/*
 *@param  id:聊天室 ID。
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

// ChatRoomKeepAliveRemove 删除保活聊天室
/*
 *@param  id:聊天室 ID。
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

// ChatRoomKeepAliveGetList 获取保活聊天室
/*
 *@param  id:聊天室 ID。
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
	return dat.ChatRoomIDs, nil
}

// ChatRoomWhitelistAdd 添加聊天室消息白名单
/*
 *@param  objectNames:消息类型列表。
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

// ChatRoomWhitelistRemove 删除聊天室消息白名单
/*
 *@param  objectNames:消息类型列表。
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

// ChatRoomWhitelistGetList 获取聊天室消息白名单
/*
 *@return []string error
 */
func (rc *RongCloud) ChatRoomWhitelistGetList() ([]string, error) {
	var dat ChatRoomResult

	req := httplib.Post(rc.rongCloudURI + "/chatroom/whitelist/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

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

	return dat.WhitelistMsgType, nil
}

// ChatRoomUserWhitelistAdd 添加聊天室白名单成员方法
/*
 *@param  id:聊天室 ID。
 *@param  members:白名单列表，最多不超过 5 个。
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

// ChatRoomUserWhitelistRemove 将用户从白名单中移除
/*
 *@param  id:聊天室 ID。
 *@param  members:白名单列表，最多不超过 5 个。
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

// ChatRoomUserWhitelistGetList 获取聊天室用户白名单
/*
 *@param  id:聊天室 ID。
 *@return []string error
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

// ChatRoomMuteMembersAdd 添加禁言聊天室成员方法（在 App 中如果不想让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息.）
/*
 *
 *@param  id:聊天室 ID。
 *@param  members:禁言列表。
 *@param  minute:禁言时长，以分钟为单位，最大值为43200分钟。（必传）
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomMuteMembersAdd(id string, members []string, minute uint) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	if minute == 0 {
		return RCErrorNew(1002, "Paramer 'minute' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/gag/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}

	req.Param("chatroomId", id)
	req.Param("minute", strconv.Itoa(int(minute)))
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

// ChatRoomMuteMembersGetList 查询被禁言聊天室成员方法
/*
 *@param  id:聊天室 ID。（必传）
 *
 *@return []ChatRoomUser error
 */
func (rc *RongCloud) ChatRoomMuteMembersGetList(id string) ([]ChatRoomUser, error) {
	var dat ChatRoomResult
	if id == "" {
		return []ChatRoomUser{}, RCErrorNew(1002, "Paramer 'chatroomId' is required")
	}
	req := httplib.Post(RONGCLOUDURI + "/chatroom/user/gag/list." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("chatroomId", id)
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

// ChatRoomMuteMembersRemove 移除禁言聊天室成员方法
/*
 *@param  id:聊天室Id。
 *@param  members:解除禁言列表
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomMuteMembersRemove(id string, members []string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/chatroom/user/gag/rollback." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	req.Param("chatroomId", id)
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
