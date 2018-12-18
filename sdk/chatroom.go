// ChatRoom 聊天室

package sdk

import (
	"encoding/json"
	"errors"
	"strconv"

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
 *
 *@param  chatRoomInfo:id:要创建的聊天室的id；name:要创建的聊天室的name。（必传）
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomCreate(id, name string) error {
	if id == "" {
		return RCErrorNew(20005, "Paramer 'id' is required")
	}
	if name == "" {
		return RCErrorNew(20005, "Paramer 'name' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/create." + ReqType)
	rc.FillHeader(req)

	req.Param("chatroom["+id+"]", name)

	rep, err := req.Bytes()
	if err != nil {
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

// ChatRoomDestroy 销毁聊天室方法
/**
 *
 *@param  id:要销毁的聊天室 ID。
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomDestroy(id string) error {
	if id == "" {
		return RCErrorNew(20005, "Paramer 'id' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/destroy." + ReqType)
	rc.FillHeader(req)

	req.Param("chatroomId", id)

	rep, err := req.Bytes()
	if err != nil {
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

// ChatRoomGet 查询聊天室内用户方法
/*
 *
 *@param  chatroomId:要查询的聊天室 ID。（必传）
 *@param  count:要获取的聊天室成员数，上限为 500 ，超过 500 时最多返回 500 个成员。（必传）
 *@param  order:加入聊天室的先后顺序， 1 为加入时间正序， 2 为加入时间倒序。（必传）
 *
 *@return ChatRoomResult error
 */
func (rc *RongCloud) ChatRoomGet(id string, count, order int) (ChatRoomResult, error) {
	if id == "" {
		return ChatRoomResult{}, errors.New("Paramer 'chatroomId' is required")
	}

	if count <= 0 {
		return ChatRoomResult{}, errors.New("Paramer 'count' is required")
	}

	if order <= 0 {
		return ChatRoomResult{}, errors.New("Paramer 'order' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/query." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	req.Param("count", strconv.Itoa(count))
	req.Param("order", strconv.Itoa(order))
	rep, err := req.Bytes()
	if err != nil {
		return ChatRoomResult{}, err
	}
	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return ChatRoomResult{}, err
	}
	if code.Code != 200 {
		return ChatRoomResult{}, RCErrorNew(code.Code, code.ErrorMessage)
	}

	var dat ChatRoomResult
	if err := json.Unmarshal(rep, &dat); err != nil {
		return ChatRoomResult{}, err
	}
	return dat, nil
}

// ChatRoomIsExist 检查用户是否在聊天室
func (rc *RongCloud) ChatRoomIsExist(id string, members []string) ([]ChatRoomUser, error) {
	if id == "" {
		return []ChatRoomUser{}, errors.New("Paramer 'chatroomId' is required")
	}

	if len(members) == 0 {
		return []ChatRoomUser{}, errors.New("Paramer 'count' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/users/exist." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	for _, v := range members {
		req.Param("userId", v)
	}

	rep, err := req.Bytes()
	if err != nil {
		return []ChatRoomUser{}, err
	}
	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return []ChatRoomUser{}, err
	}
	if code.Code != 200 {
		return []ChatRoomUser{}, RCErrorNew(code.Code, code.ErrorMessage)
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
 *@param  userId:用户 Id。（必传）
 *@param  chatroomId:聊天室 Id。（必传）
 *@param  minute:封禁时长，以分钟为单位，最大值为43200分钟。（必传）
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomBlockAdd(id string, members []string, minute uint) error {
	if id == "" {
		return errors.New("Paramer 'id' is required")
	}

	if len(members) == 0 {
		return errors.New("Paramer 'members' is required")
	}

	if minute == 0 {
		return errors.New("Paramer 'minute' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/block/add." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	for _, v := range members {
		req.Param("userId", v)
	}

	req.Param("minute", strconv.Itoa(int(minute)))
	rep, err := req.Bytes()
	if err != nil {
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

// ChatRoomBlockRemove 移除封禁聊天室成员方法
/*
 *
 *@param  id:聊天室 ID。（必传）
 *@param  members: 用户列表。（必传）
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomBlockRemove(id string, members []string) error {
	if id == "" {
		return errors.New("Paramer 'id' is required")
	}

	if len(members) == 0 {
		return errors.New("Paramer 'members' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/block/rollback." + ReqType)
	rc.FillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
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

// ChatRoomBlockGetList 查询被封禁聊天室成员方法
/*
 *
 *@param  chatroomId:聊天室 Id。（必传）
 *
 *@return ListBlockChatRoomUserResult
 */
func (rc *RongCloud) ChatRoomBlockGetList(id string) (ChatRoomResult, error) {
	var dat ChatRoomResult
	var code CodeResult
	if id == "" {
		return dat, errors.New("Paramer 'chatroomId' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/block/list." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
		return dat, err
	}

	if err := json.Unmarshal(rep, &code); err != nil {
		return dat, err
	}
	if code.Code != 200 {
		return dat, RCErrorNew(code.Code, code.ErrorMessage)
	}

	if err := json.Unmarshal(rep, &dat); err != nil {
		return dat, err
	}

	return dat, nil
}

// ChatRoomBanAdd 添加聊天室全局禁言
func (rc *RongCloud) ChatRoomBanAdd(members []string, minute uint) error {

	var code CodeResult
	if len(members) == 0 {
		return errors.New("Paramer 'members' is required")
	}
	if minute == 0 {
		return errors.New("Paramer 'minute' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/ban/add." + ReqType)
	rc.FillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	req.Param("minute", strconv.Itoa(int(minute)))
	rep, err := req.Bytes()
	if err != nil {
		return err
	}

	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}

	return nil
}

// ChatRoomBanRemove 解除聊天室全局禁言
func (rc *RongCloud) ChatRoomBanRemove(members []string) error {

	var code CodeResult
	if len(members) == 0 {
		return errors.New("Paramer 'members' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/ban/remove." + ReqType)
	rc.FillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	rep, err := req.Bytes()
	if err != nil {
		return err
	}

	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}

	return nil
}

// ChatRoomBanGetList 获取聊天室全局禁言列表
func (rc *RongCloud) ChatRoomBanGetList() ([]ChatRoomUser, error) {
	var code CodeResult
	var dat ChatRoomResult
	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/ban/query." + ReqType)
	rc.FillHeader(req)

	rep, err := req.Bytes()
	if err != nil {
		return []ChatRoomUser{}, err
	}
	if err := json.Unmarshal(rep, &code); err != nil {
		return []ChatRoomUser{}, err
	}
	if code.Code != 200 {
		return []ChatRoomUser{}, RCErrorNew(code.Code, code.ErrorMessage)
	}
	if err := json.Unmarshal(rep, &dat); err != nil {
		return []ChatRoomUser{}, err
	}
	return dat.Users, nil
}

// ChatRoomGagAdd 添加禁言聊天室成员方法（在 App 中如果不想让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息.）
/*
 *
 *@param  userId:用户 Id。（必传）
 *@param  chatroomId:聊天室 Id。（必传）
 *@param  minute:禁言时长，以分钟为单位，最大值为43200分钟。（必传）
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomGagAdd(id string, members []string, minute uint) error {
	if id == "" {
		return errors.New("Paramer 'userId' is required")
	}

	if len(members) == 0 {
		return errors.New("Paramer 'members' is required")
	}

	if minute == 0 {
		return errors.New("Paramer 'minute' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/gag/add." + ReqType)
	rc.FillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}

	req.Param("chatroomId", id)
	req.Param("minute", strconv.Itoa(int(minute)))
	rep, err := req.Bytes()
	if err != nil {
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

// ChatRoomGagRemove 移除禁言聊天室成员方法
/*
 *
 *@param  userId:用户 Id。（必传）
 *@param  chatroomId:聊天室Id。（必传）
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomGagRemove(id string, members []string) error {
	if id == "" {
		return errors.New("Paramer 'id' is required")
	}

	if len(members) == 0 {
		return errors.New("Paramer 'members' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/gag/rollback." + ReqType)
	rc.FillHeader(req)
	for _, v := range members {
		req.Param("userId", v)
	}
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
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

// ChatRoomGagGetList 查询被禁言聊天室成员方法
/*
 *
 *@param  id:聊天室 ID。（必传）
 *
 *@return []ChatRoomUser error
 */
func (rc *RongCloud) ChatRoomGagGetList(chatroomID string) ([]ChatRoomUser, error) {
	var dat ChatRoomResult
	if chatroomID == "" {
		return []ChatRoomUser{}, errors.New("Paramer 'chatroomId' is required")
	}
	req := httplib.Post(RONGCLOUDURI + "/chatroom/user/gag/list." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", chatroomID)
	rep, err := req.Bytes()
	if err != nil {
		return []ChatRoomUser{}, err
	}
	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return []ChatRoomUser{}, err
	}
	if code.Code != 200 {
		return []ChatRoomUser{}, RCErrorNew(code.Code, code.ErrorMessage)
	}
	if err := json.Unmarshal(rep, &dat); err != nil {
		return []ChatRoomUser{}, err
	}
	return dat.Users, nil
}

// ChatRoomDemotionAdd 添加聊天室消息优先级方法
/*
 *
 *@param  objectName:低优先级的消息类型，每次最多提交 5 个，设置的消息类型最多不超过 20 个。（必传）
 *
 *@return err
 */
func (rc *RongCloud) ChatRoomDemotionAdd(objectNames []string) error {
	if len(objectNames) == 0 {
		return errors.New("Paramer 'objectName' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/message/priority/add." + ReqType)
	rc.FillHeader(req)
	for _, v := range objectNames {
		req.Param("objectName", v)
	}
	rep, err := req.Bytes()
	if err != nil {
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

// ChatRoomDemotionRemove 添加应用内聊天室降级消息
func (rc *RongCloud) ChatRoomDemotionRemove(objectNames []string) error {
	if len(objectNames) == 0 {
		return errors.New("Paramer 'objectName' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/message/priority/remove." + ReqType)
	rc.FillHeader(req)
	for _, v := range objectNames {
		req.Param("objectName", v)
	}
	rep, err := req.Bytes()
	if err != nil {
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

// ChatRoomDemotionGetList 移除应用内聊天室降级消息
func (rc *RongCloud) ChatRoomDemotionGetList() ([]string, error) {
	var dat ChatRoomResult

	req := httplib.Post(rc.RongCloudURI + "/chatroom/message/priority/query." + ReqType)
	rc.FillHeader(req)
	rep, err := req.Bytes()
	if err != nil {
		return []string{}, err
	}
	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return []string{}, err
	}
	if code.Code != 200 {
		return []string{}, RCErrorNew(code.Code, code.ErrorMessage)
	}
	if err := json.Unmarshal(rep, &dat); err != nil {
		return []string{}, err
	}
	return dat.ObjectNames, nil
}

// ChatRoomDistributionStop 聊天室消息停止分发方法（可实现控制对聊天室中消息是否进行分发，停止分发后聊天室中用户发送的消息，融云服务端不会再将消息发送给聊天室中其他用户。）
/*
 *
 *@param  chatroomId:聊天室 Id。（必传）
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomDistributionStop(id string) error {
	if id == "" {
		return errors.New("Paramer 'chatroomId' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/message/stopDistribution." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
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

// ChatRoomDistributionResume 聊天室消息恢复分发方法
/*
 *
 *@param  chatroomId:聊天室 Id。（必传）
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomDistributionResume(id string) error {
	if id == "" {
		return errors.New("Paramer 'chatroomId' is required")
	}
	req := httplib.Post(rc.RongCloudURI + "/chatroom/message/resumeDistribution." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
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

// ChatRoomKeepAliveAdd 添加保活聊天室
func (rc *RongCloud) ChatRoomKeepAliveAdd(id string) error {
	if id == "" {
		return errors.New("Paramer 'chatroomId' is required")
	}
	req := httplib.Post(rc.RongCloudURI + "/chatroom/keepalive/add." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
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

// ChatRoomKeepAliveRemove 删除保活聊天室
func (rc *RongCloud) ChatRoomKeepAliveRemove(id string) error {
	if id == "" {
		return errors.New("Paramer 'chatroomId' is required")
	}
	req := httplib.Post(rc.RongCloudURI + "/chatroom/keepalive/remove." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
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

// ChatRoomKeepAliveGetList 获取保活聊天室
func (rc *RongCloud) ChatRoomKeepAliveGetList(id string) ([]string, error) {
	var dat ChatRoomResult
	if id == "" {
		return []string{}, errors.New("Paramer 'chatroomId' is required")
	}
	req := httplib.Post(rc.RongCloudURI + "/chatroom/keepalive/query." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
		return []string{}, err
	}
	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return []string{}, err
	}
	if code.Code != 200 {
		return []string{}, RCErrorNew(code.Code, code.ErrorMessage)
	}
	if err := json.Unmarshal(rep, &dat); err != nil {
		return []string{}, err
	}
	return dat.ChatRoomIDs, nil
}

// ChatRoomWhitelistAdd 添加聊天室消息白名单
func (rc *RongCloud) ChatRoomWhitelistAdd(objectNames []string) error {

	if len(objectNames) == 0 {
		return errors.New("Paramer 'objectNames' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/whitelist/add." + ReqType)
	rc.FillHeader(req)
	for _, v := range objectNames {
		req.Param("objectnames", v)
	}
	rep, err := req.Bytes()
	if err != nil {
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

// ChatRoomWhitelistRemove 删除聊天室消息白名单
func (rc *RongCloud) ChatRoomWhitelistRemove(objectNames []string) error {

	if len(objectNames) == 0 {
		return errors.New("Paramer 'members' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/whitelist/delete." + ReqType)
	rc.FillHeader(req)

	for _, v := range objectNames {
		req.Param("objectnames", v)
	}
	rep, err := req.Bytes()
	if err != nil {
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

// ChatRoomWhitelistGetList 获取聊天室消息白名单
func (rc *RongCloud) ChatRoomWhitelistGetList() ([]string, error) {
	var dat ChatRoomResult

	req := httplib.Post(rc.RongCloudURI + "/chatroom/whitelist/query." + ReqType)
	rc.FillHeader(req)

	rep, err := req.Bytes()
	if err != nil {
		return []string{}, err
	}

	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return []string{}, err
	}
	if code.Code != 200 {
		return []string{}, RCErrorNew(code.Code, code.ErrorMessage)
	}
	if err := json.Unmarshal(rep, &dat); err != nil {
		return []string{}, err
	}

	return dat.WhitelistMsgType, nil
}

// ChatRoomUserWhitelistAdd 添加聊天室白名单成员方法
/*
 *
 *@param  chatroomId:聊天室中用户 Id，可提交多个，聊天室中白名单用户最多不超过 5 个。（必传）
 *@param  userId:聊天室 Id。（必传）
 *
 *@return error
 */
func (rc *RongCloud) ChatRoomUserWhitelistAdd(id string, members []string) error {
	if id == "" {
		return errors.New("Paramer 'id' is required")
	}

	if len(members) == 0 {
		return errors.New("Paramer 'members' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/whitelist/add." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	for _, v := range members {
		req.Param("userId", v)
	}
	rep, err := req.Bytes()
	if err != nil {
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

// ChatRoomUserWhitelistRemove 将用户从白名单中移除
func (rc *RongCloud) ChatRoomUserWhitelistRemove(id string, members []string) error {
	if id == "" {
		return errors.New("Paramer 'id' is required")
	}

	if len(members) == 0 {
		return errors.New("Paramer 'members' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/whitelist/remove." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	for _, v := range members {
		req.Param("userId", v)
	}
	rep, err := req.Bytes()
	if err != nil {
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

// ChatRoomUserWhitelistGetList 获取聊天室用户白名单
func (rc *RongCloud) ChatRoomUserWhitelistGetList(id string) ([]string, error) {
	var dat map[string]interface{}
	if id == "" {
		return []string{}, errors.New("Paramer 'id' is required")
	}
	req := httplib.Post(rc.RongCloudURI + "/chatroom/user/whitelist/query." + ReqType)
	rc.FillHeader(req)
	req.Param("chatroomId", id)
	rep, err := req.Bytes()
	if err != nil {
		return []string{}, err
	}
	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return []string{}, err
	}
	if code.Code != 200 {
		return []string{}, RCErrorNew(code.Code, code.ErrorMessage)
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
