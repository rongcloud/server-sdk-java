package sdk

import (
	"encoding/json"
	"strconv"

	"github.com/astaxie/beego/httplib"
)

// Group 群组信息
type Group struct {
	ID    string      `json:"id"`
	Users []GroupUser `json:"users"`
	Name  string      `json:"name"`
}

// GroupUser 群组用户信息
type GroupUser struct {
	ID     string `json:"id"`
	UserID string `json:"userId"`
	Time   string `json:"time"`
}

// GroupCreate 创建群组方法（创建群组，并将用户加入该群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人，App 内的群组数量没有限制.注：其实本方法是加入群组方法 /group/join 的别名。）
/*
 *@param  id:群组 Id，最大长度 30 个字符，建议使用 英文字母、数字 混排
 *@param  name:群组名称，最大长度 60 个字符
 *@param  members:加入群组的用户列表
 *
 *@return error
 */
func (rc *RongCloud) GroupCreate(id, name string, members []string) error {
	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}

	if id == "" {
		return RCErrorNew(1002, "Paramer 'groupId' is required")
	}

	if name == "" {
		return RCErrorNew(1002, "Paramer 'groupName' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/group/create." + ReqType)
	rc.FillHeader(req)
	for _, item := range members {
		req.Param("userId", item)
	}
	req.Param("groupId", id)
	req.Param("groupName", name)
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

// GroupSync 同步用户所属群组方法（当第一次连接融云服务器时，需要向融云服务器提交 ID 对应的用户当前所加入的所有群组，此接口主要为防止应用中用户群信息同融云已知的用户所属群信息不同步。）
/*
 *@param  id:被同步群信息的用户 ID。（必传）
 *@param  groups:用户所在群组列表。
 *
 *@return error
 */
func (rc *RongCloud) GroupSync(id string, groups []Group) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}

	if len(groups) == 0 {
		return RCErrorNew(1002, "Paramer 'groups' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/group/sync." + ReqType)
	rc.FillHeader(req)
	req.Param("userId", id)
	for _, item := range groups {
		req.Param("group["+item.ID+"]", item.Name)
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

// GroupUpdate 刷新群组信息方法
/*
*@param  id:群组 Id。
*@param  name:群名称。
*
*@return error
 */
func (rc *RongCloud) GroupUpdate(id, name string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'groupId' is required")
	}

	if name == "" {
		return RCErrorNew(1002, "Paramer 'groupName' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/group/refresh." + ReqType)
	rc.FillHeader(req)
	req.Param("groupId", id)
	req.Param("groupName", name)
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

// GroupJoin 将用户加入指定群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人。
/*
 *@param  id:加入的群组 Id。
 *@param  name:群组名称，最大长度 60 个字符。
 *@param  member:要加入群组的用户。
 *
 *@return error
 */
func (rc *RongCloud) GroupJoin(id, name, member string) error {
	if member == "" {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}

	if id == "" {
		return RCErrorNew(1002, "Paramer 'groupId' is required")
	}

	if name == "" {
		return RCErrorNew(1002, "Paramer 'groupName' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/group/join." + ReqType)
	rc.FillHeader(req)

	req.Param("userId", member)
	req.Param("groupId", id)
	req.Param("groupName", name)
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

// GroupGet 查询群成员方法
/*
 *@param  id:群组Id。
 *
 *@return Group error
 */
func (rc *RongCloud) GroupGet(id string) (Group, error) {
	if id == "" {
		return Group{}, RCErrorNew(1002, "Paramer 'ID' is required")
	}
	req := httplib.Post(rc.RongCloudURI + "/group/user/query." + ReqType)
	rc.FillHeader(req)
	req.Param("groupId", id)
	rep, err := req.Bytes()
	if err != nil {
		return Group{}, err
	}
	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return Group{}, err
	}
	if code.Code != 200 {
		return Group{}, RCErrorNew(code.Code, code.ErrorMessage)
	}
	var dat Group
	if err := json.Unmarshal(rep, &dat); err != nil {
		return Group{}, err
	}
	return dat, nil

}

// GroupQuit 退出群组方法（将用户从群中移除，不再接收该群组的消息.）
/*
 *@param  id:要退出的群组 Id。
 *@param  member:要退出群组的群成员。
 *
 *@return error
 */
func (rc *RongCloud) GroupQuit(member, id string) error {
	if member == "" {
		return RCErrorNew(1002, "Paramer 'member' is required")
	}

	if id == "" {
		return RCErrorNew(1002, "Paramer 'ID' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/group/quit." + ReqType)
	rc.FillHeader(req)

	req.Param("userId", member)
	req.Param("groupId", id)
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

// GroupDismiss 解散群组方法
/*
 *@param  id:群组 ID，最大长度 30 个字符，建议使用 英文字母、数字 混排
 *@param  member:群主或群管理.
 *
 *@return error
 */
func (rc *RongCloud) GroupDismiss(id, member string) error {
	if member == "" {
		return RCErrorNew(1002, "Paramer 'member' is required")
	}

	if id == "" {
		return RCErrorNew(1002, "Paramer 'ID' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/group/dismiss." + ReqType)
	rc.FillHeader(req)

	req.Param("userId", member)
	req.Param("groupId", id)
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

// GroupGagAdd 添加禁言群成员方法（在 App 中如果不想让某一用户在群中发言时，可将此用户在群组中禁言，被禁言用户可以接收查看群组中用户聊天信息，但不能发送消息。）
/*
*@param  id:群组 ID。
*@param  members:禁言群成员列表。
*@param  minute:禁言时长，以分钟为单位，最大值为43200分钟。
*
*@return error
 */
func (rc *RongCloud) GroupGagAdd(id string, members []string, minute int) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'ID' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	if minute == 0 {
		return RCErrorNew(1002, "Paramer 'minute' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/group/user/gag/add." + ReqType)
	rc.FillHeader(req)
	for _, item := range members {
		req.Param("userId", item)
	}
	req.Param("groupId", id)
	req.Param("minute", strconv.Itoa(minute))
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

// GroupGagList 查询被禁言群成员方法
/*
*@param  id:群组ID。
*
*@return Group error
 */
func (rc *RongCloud) GroupGagList(id string) (Group, error) {
	if id == "" {
		return Group{}, RCErrorNew(1002, "Paramer 'groupId' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/group/user/gag/list." + ReqType)
	rc.FillHeader(req)
	req.Param("groupId", id)
	rep, err := req.Bytes()
	if err != nil {
		return Group{}, err
	}
	var code CodeResult
	var dat Group
	if err := json.Unmarshal(rep, &code); err != nil {
		return Group{}, err
	}
	if err := json.Unmarshal(rep, &dat); err != nil {
		return Group{}, err
	}
	if code.Code != 200 {
		return Group{}, RCErrorNew(code.Code, code.ErrorMessage)
	}
	return dat, nil
}

// GroupGagRemove 移除禁言群成员方法
/*
*@param  id:群组 Id。
*@param  members:解除禁言群成员列表	。
*
*@return error
 */
func (rc *RongCloud) GroupGagRemove(id string, members []string) error {
	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	if id == "" {
		return RCErrorNew(1002, "Paramer 'groupId' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/group/user/gag/rollback.json")
	rc.FillHeader(req)
	for _, item := range members {
		req.Param("userId", item)
	}
	req.Param("groupId", id)
	rep, err := req.Bytes()
	if err != nil {
		return RCErrorNew(20013, err.Error())
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
