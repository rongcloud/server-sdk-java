package sdk

import (
	"encoding/json"
	"strconv"
	"time"

	"github.com/astaxie/beego/httplib"
)

// Group 群组信息
type Group struct {
	ID    string      `json:"id"`
	Users []GroupUser `json:"users"`
	Name  string      `json:"name"`
	Stat  string      `json:"stat"`
}

// GroupUser 群组用户信息
type GroupUser struct {
	ID     string `json:"id"`
	UserID string `json:"userId"`
	Time   string `json:"time"`
}

// GroupInfo 群组信息
type GroupInfo struct {
	GroupInfo []Group `json:"groupinfo"`
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
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if name == "" {
		return RCErrorNew(1002, "Paramer 'name' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/create." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	for _, item := range members {
		req.Param("userId", item)
	}
	req.Param("groupId", id)
	req.Param("groupName", name)
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

// GroupSync 同步用户所属群组方法（当第一次连接融云服务器时，需要向融云服务器提交 ID 对应的用户当前所加入的所有群组，此接口主要为防止应用中用户群信息同融云已知的用户所属群信息不同步。）
/*
 *@param  id:被同步群信息的用户 ID。（必传）
 *@param  groups:用户所在群组列表。
 *
 *@return error
 */
func (rc *RongCloud) GroupSync(id string, groups []Group) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(groups) == 0 {
		return RCErrorNew(1002, "Paramer 'groups' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/sync." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", id)
	for _, item := range groups {
		req.Param("group["+item.ID+"]", item.Name)
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

// GroupUpdate 刷新群组信息方法
/*
*@param  id:群组 Id。
*@param  name:群名称。
*
*@return error
 */
func (rc *RongCloud) GroupUpdate(id, name string) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if name == "" {
		return RCErrorNew(1002, "Paramer 'name' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/refresh." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", id)
	req.Param("groupName", name)
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
		return RCErrorNew(1002, "Paramer 'member' is required")
	}

	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if name == "" {
		return RCErrorNew(1002, "Paramer 'name' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/join." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", member)
	req.Param("groupId", id)
	req.Param("groupName", name)
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

// GroupGet 查询群成员方法
/*
 *@param  id:群组Id。
 *
 *@return Group error
 */
func (rc *RongCloud) GroupGet(id string) (Group, error) {
	if id == "" {
		return Group{}, RCErrorNew(1002, "Paramer 'id' is required")
	}
	req := httplib.Post(rc.rongCloudURI + "/group/user/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", id)
	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return Group{}, err
	}
	var code CodeResult
	if err := json.Unmarshal(rep, &code); err != nil {
		return Group{}, err
	}
	if code.Code != 200 {
		return Group{}, code
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
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/quit." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", member)
	req.Param("groupId", id)
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
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/dismiss." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("userId", member)
	req.Param("groupId", id)
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
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	if minute == 0 {
		return RCErrorNew(1002, "Paramer 'minute' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/user/gag/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, item := range members {
		req.Param("userId", item)
	}
	req.Param("groupId", id)
	req.Param("minute", strconv.Itoa(minute))
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

// GroupMuteMembersAdd 添加禁言群成员方法（在 App 中如果不想让某一用户在群中发言时，可将此用户在群组中禁言，被禁言用户可以接收查看群组中用户聊天信息，但不能发送消息。）
/*
*@param  id:群组 ID。
*@param  members:禁言群成员列表。
*@param  minute:禁言时长，以分钟为单位，最大值为43200分钟。
*
*@return error
 */
func (rc *RongCloud) GroupMuteMembersAdd(id string, members []string, minute int) error {
	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	if minute == 0 {
		return RCErrorNew(1002, "Paramer 'minute' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/user/gag/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, item := range members {
		req.Param("userId", item)
	}
	req.Param("groupId", id)
	req.Param("minute", strconv.Itoa(minute))
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

// GroupGagList 查询被禁言群成员方法
/*
*@param  id:群组ID。
*
*@return Group error
 */
func (rc *RongCloud) GroupGagList(id string) (Group, error) {
	if id == "" {
		return Group{}, RCErrorNew(1002, "Paramer 'id' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/user/gag/list." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", id)
	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
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
		return Group{}, code
	}
	return dat, nil
}

// GroupMuteMembersGetList 查询被禁言群成员方法
/*
*@param  id:群组ID。
*
*@return Group error
 */
func (rc *RongCloud) GroupMuteMembersGetList(id string) (Group, error) {
	if id == "" {
		return Group{}, RCErrorNew(1002, "Paramer 'id' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/user/gag/list." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", id)
	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
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
		return Group{}, code
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
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/user/gag/rollback." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	for _, item := range members {
		req.Param("userId", item)
	}
	req.Param("groupId", id)
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

// GroupMuteMembersRemove 移除禁言群成员方法
/*
*@param  id:群组 Id。
*@param  members:解除禁言群成员列表	。
*
*@return error
 */
func (rc *RongCloud) GroupMuteMembersRemove(id string, members []string) error {
	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/user/gag/rollback." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	for _, item := range members {
		req.Param("userId", item)
	}
	req.Param("groupId", id)
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

// GroupMuteAllMembersAdd 设置某一群组禁言，禁言后群组中所有成员禁止发送消息，如需要某些用户可以发言时，可将此用户加入到群禁言用户白名单中。
/*
*@param  members:禁言群成员列表。
*
*@return error
 */
func (rc *RongCloud) GroupMuteAllMembersAdd(members []string) error {
	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/ban/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	for _, item := range members {
		req.Param("groupId", item)
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

// GroupMuteAllMembersRemove 解除群组禁言
/*
*@param  members:禁言群成员列表。
*
*@return error
 */
func (rc *RongCloud) GroupMuteAllMembersRemove(members []string) error {
	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/ban/rollback." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	for _, item := range members {
		req.Param("groupId", item)
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

// GroupMuteAllMembersGetList 查询全部群组禁言列表
/*
*@param  id:群组ID。
*
*@return Group error
 */
func (rc *RongCloud) GroupMuteAllMembersGetList(members []string) (GroupInfo, error) {

	req := httplib.Post(rc.rongCloudURI + "/group/ban/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	if len(members) > 0 {
		for _, item := range members {
			req.Param("groupId", item)
		}
	}

	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return GroupInfo{}, err
	}
	var code CodeResult
	var group GroupInfo
	if err := json.Unmarshal(rep, &struct {
		*CodeResult
		*GroupInfo
	}{
		&code,
		&group,
	}); err != nil {
		return GroupInfo{}, err
	}
	if code.Code != 200 {
		return GroupInfo{}, code
	}

	return group, nil
}

// GroupMuteWhiteListUserAdd 在群组被禁言状态下，如果需要某些用户可以发言时，可将此用户加入到群组禁言用户白名单中。群禁言用户白名单，只有群组被设置为全部禁言时才会生效。
/*
*@param  id:群组 ID。
*@param  members:禁言群成员列表。
*
*@return error
 */
func (rc *RongCloud) GroupMuteWhiteListUserAdd(id string, members []string) error {
	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/user/ban/whitelist/add." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	for _, item := range members {
		req.Param("userId", item)
	}
	req.Param("groupId", id)
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

// GroupMuteWhiteListUserRemove 移除群禁言白名单用户。
/*
*@param  id:群组 ID。
*@param  members:禁言群成员列表。
*
*@return error
 */
func (rc *RongCloud) GroupMuteWhiteListUserRemove(id string, members []string) error {
	if len(members) == 0 {
		return RCErrorNew(1002, "Paramer 'members' is required")
	}

	if id == "" {
		return RCErrorNew(1002, "Paramer 'id' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/user/ban/whitelist/rollback." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	for _, item := range members {
		req.Param("userId", item)
	}
	req.Param("groupId", id)
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

// GroupMuteWhiteListUserGetList 查询群禁言白名单用户列表。
/*
*@param  id:群组 ID。
*
*@return error
 */
func (rc *RongCloud) GroupMuteWhiteListUserGetList(id string) ([]string, error) {
	if id == "" {
		return []string{}, RCErrorNew(1002, "Paramer 'id' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/group/user/ban/whitelist/query." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)

	req.Param("groupId", id)
	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return []string{}, err
	}
	var code CodeResult
	var userIDs []string
	if err := json.Unmarshal(rep, &struct {
		*CodeResult
		UserIDs *[]string `json:"userids"`
	}{
		&code,
		&userIDs,
	}); err != nil {
		return []string{}, err
	}
	if code.Code != 200 {
		return []string{}, code
	}

	return userIDs, nil
}
