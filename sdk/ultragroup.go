package sdk

import (
	"encoding/json"
	"fmt"
	"github.com/astaxie/beego/httplib"
	"strconv"
	"time"
)

// api 返回结果, data 数组
type RespDataArray struct {
	Code int                                 `json:"code"`
	Data map[string][]map[string]interface{} `json:"data"`
}

// api 返回结果, data
type RespDataKV struct {
	Code int                    `json:"code"`
	Data map[string]interface{} `json:"data"`
}

// 超级群 群组信息
type UGGroupInfo struct {
	GroupId   string `json:"group_id"`
	GroupName string `json:"group_name"`
}

// 超级群 用户信息
type UGUserInfo struct {
	Id        string `json:"id"`
	MutedTime string `json:"time"`
}

// 超级群 频道信息
type UGChannelInfo struct {
	ChannelId  string `json:"channel_id"`
	CreateTime string `json:"create_time"`
}

// 超级群 消息结构
type UGMessage struct {
	FromUserId          string   `json:"from_user_id"`
	ToGroupIds          []string `json:"to_group_ids"`
	ToUserIds           []string `json:"to_user_ids,omitempty"`
	ObjectName          string   `json:"object_name"`
	Content             string   `json:"content"`
	PushContent         string   `json:"push_content,omitempty"`
	PushData            string   `json:"push_data,omitempty"`
	IncludeSenderEnable bool     `json:"include_sender_enable,omitempty"`
	StoreFlag           bool     `json:"store_flag"`
	MentionedFlag       bool     `json:"mentioned_flag,omitempty"`
	SilencePush         bool     `json:"silence_push,omitempty"`
	PushExt             string   `json:"push_ext,omitempty"`
	BusChannel          string   `json:"bus_channel,omitempty"`
}

// 创建群组
func (rc *RongCloud) UGGroupCreate(userId, groupId, groupName string) (err error, requestId string) {
	if userId == "" {
		return RCErrorNewV2(1002, "param 'userId' is required"), ""
	}

	if groupId == "" {
		return RCErrorNewV2(1002, "param 'groupId' is required"), ""
	}

	if groupName == "" {
		return RCErrorNewV2(1002, "param 'groupName' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups", rc.rongCloudURI)
	req := httplib.Post(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	// json body
	postBody := map[string]interface{}{"user_id": userId,
		"group_id":   groupId,
		"group_name": groupName,
	}
	_, err = req.JSONBody(postBody)
	if err != nil {
		return err, ""
	}

	// http
	_, err = rc.doV2(req)

	return err, requestId
}

// 解散群组
func (rc *RongCloud) UGGroupDismiss(groupId string) (err error, requestId string) {

	if groupId == "" {
		return RCErrorNewV2(1002, "param 'groupId' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/%s", rc.rongCloudURI, groupId)
	req := httplib.Delete(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	// http
	_, err = rc.doV2(req)

	return err, requestId
}

// 加入群组
func (rc *RongCloud) UGGroupJoin(userId, groupId string) (err error, requestId string) {
	if userId == "" {
		return RCErrorNewV2(1002, "param 'userId' is required"), ""
	}

	if groupId == "" {
		return RCErrorNewV2(1002, "param 'groupId' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/%s/users/%s", rc.rongCloudURI, groupId, userId)
	req := httplib.Post(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	// http
	_, err = rc.doV2(req)

	return err, requestId
}

// 退出群组
func (rc *RongCloud) UGGroupQuit(userId, groupId string) (err error, requestId string) {
	if userId == "" {
		return RCErrorNewV2(1002, "param 'userId' is required"), ""
	}

	if groupId == "" {
		return RCErrorNewV2(1002, "param 'groupId' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/%s/users/%s", rc.rongCloudURI, groupId, userId)
	req := httplib.Delete(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	// http
	_, err = rc.doV2(req)

	return err, requestId
}

// 刷新群组信息
func (rc *RongCloud) UGGroupUpdate(groupId, groupName string) (err error, requestId string) {
	if groupId == "" {
		return RCErrorNewV2(1002, "param 'groupId' is required"), ""
	}
	if groupName == "" {
		return RCErrorNewV2(1002, "param 'groupName' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/%s", rc.rongCloudURI, groupId)
	req := httplib.Put(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	// json body
	postBody := map[string]interface{}{
		"group_name": groupName,
	}
	_, err = req.JSONBody(postBody)
	if err != nil {
		return err, ""
	}

	// http
	_, err = rc.doV2(req)

	return err, requestId
}

// 查询用户所在群组(P1)
func (rc *RongCloud) UGQueryUserGroups(userId string, page, size int) (groups []UGGroupInfo, err error, requestId string) {
	if userId == "" {
		return nil, RCErrorNewV2(1002, "param 'userId' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/users/%s/groups", rc.rongCloudURI, userId)
	req := httplib.Get(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	req.Param("page", strconv.Itoa(page))
	req.Param("size", strconv.Itoa(size))

	// http
	respBody, err := rc.doV2(req)
	if err != nil {
		return groups, err, requestId
	}

	// 处理返回结果
	var respJson RespDataArray
	if err := json.Unmarshal(respBody, &respJson); err != nil {
		return groups, err, requestId
	}

	if respJson.Data != nil {
		if gs, ok := respJson.Data["groups"]; ok {
			if gs != nil {
				for _, v := range gs {
					t := UGGroupInfo{GroupId: fmt.Sprint(v["group_id"]),
						GroupName: fmt.Sprint(v["group_name"])}
					groups = append(groups, t)
				}
			}
		}
	}

	return groups, err, requestId
}

// 查询群成员(P1)
func (rc *RongCloud) UGQueryGroupUsers(groupId string, page, size int) (users []UGUserInfo, err error, requestId string) {
	if groupId == "" {
		return nil, RCErrorNewV2(1002, "param 'groupId' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/%s/users", rc.rongCloudURI, groupId)
	req := httplib.Get(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	req.Param("page", strconv.Itoa(page))
	req.Param("size", strconv.Itoa(size))

	// http
	respBody, err := rc.doV2(req)
	if err != nil {
		return users, err, requestId
	}

	// 处理返回结果
	var respJson RespDataArray
	if err := json.Unmarshal(respBody, &respJson); err != nil {
		return users, err, requestId
	}

	if respJson.Data != nil {
		if gs, ok := respJson.Data["users"]; ok {
			if gs != nil {
				for _, v := range gs {
					t := UGUserInfo{Id: fmt.Sprint(v["id"])}
					users = append(users, t)
				}
			}
		}
	}

	return users, err, requestId
}

// 消息发送 普通消息
func (rc *RongCloud) UGGroupSend(msg UGMessage) (err error, requestId string) {
	if msg.FromUserId == "" {
		return RCErrorNewV2(1002, "Paramer 'FromUserId' is required"), ""
	}

	if len(msg.ToGroupIds) == 0 {
		return RCErrorNewV2(1002, "Paramer 'ToGroupIds' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/message/ultragroup/send", rc.rongCloudURI)
	req := httplib.Post(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	// json body
	_, err = req.JSONBody(msg)
	if err != nil {
		return err, ""
	}

	// http
	_, err = rc.doV2(req)

	return err, requestId
}

// 添加禁言成员
func (rc *RongCloud) UGGroupMuteMembersAdd(groupId string, userIds []string) (err error, requestId string) {
	if groupId == "" {
		return RCErrorNewV2(1002, "Paramer 'groupId' is required"), ""
	}

	if len(userIds) == 0 {
		return RCErrorNewV2(1002, "Paramer 'userIds' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/%s/muted-users", rc.rongCloudURI, groupId)
	req := httplib.Post(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	// json body
	postBody := map[string]interface{}{
		"user_ids": userIds,
	}
	_, err = req.JSONBody(postBody)
	if err != nil {
		return err, ""
	}

	// http
	_, err = rc.doV2(req)

	return err, requestId
}

// 移除禁言成员
func (rc *RongCloud) UGGroupMuteMembersRemove(groupId string, userIds []string) (err error, requestId string) {
	if groupId == "" {
		return RCErrorNewV2(1002, "Paramer 'groupId' is required"), ""
	}

	if len(userIds) == 0 {
		return RCErrorNewV2(1002, "Paramer 'userIds' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/%s/muted-users", rc.rongCloudURI, groupId)
	req := httplib.Delete(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	// json body
	postBody := map[string]interface{}{
		"user_ids": userIds,
	}
	_, err = req.JSONBody(postBody)
	if err != nil {
		return err, ""
	}

	// http
	_, err = rc.doV2(req)

	return err, requestId
}

// 获取禁言成员
func (rc *RongCloud) UGGroupMuteMembersGetList(groupId string) (users []UGUserInfo, err error, requestId string) {
	if groupId == "" {
		return nil, RCErrorNewV2(1002, "param 'groupId' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/%s/muted-users", rc.rongCloudURI, groupId)
	req := httplib.Get(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	// http
	respBody, err := rc.doV2(req)
	if err != nil {
		return users, err, requestId
	}

	// 处理返回结果
	var respJson RespDataArray
	if err := json.Unmarshal(respBody, &respJson); err != nil {
		return users, err, requestId
	}

	if respJson.Data != nil {
		if gs, ok := respJson.Data["users"]; ok {
			if gs != nil {
				for _, v := range gs {
					t := UGUserInfo{Id: fmt.Sprint(v["id"]),
						MutedTime: fmt.Sprint(v["time"])}
					users = append(users, t)
				}
			}
		}
	}

	return users, err, requestId
}

// 设置全体成员禁言
func (rc *RongCloud) UGGroupMuted(groupId string, status bool) (err error, requestId string) {
	if groupId == "" {
		return RCErrorNewV2(1002, "Paramer 'groupId' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/%s/muted-status", rc.rongCloudURI, groupId)
	req := httplib.Put(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	// json body
	postBody := map[string]interface{}{
		"status": status,
	}
	_, err = req.JSONBody(postBody)
	if err != nil {
		return err, ""
	}

	// http
	_, err = rc.doV2(req)

	return err, requestId
}

// 获取群全体成员禁言状态
func (rc *RongCloud) UGGroupMutedQuery(groupId string) (status bool, err error, requestId string) {
	if groupId == "" {
		return status, RCErrorNewV2(1002, "Paramer 'groupId' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/%s/muted-status", rc.rongCloudURI, groupId)
	req := httplib.Get(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	// http
	respBody, err := rc.doV2(req)
	if err != nil {
		return status, err, requestId
	}

	// 处理返回结果
	var respJson RespDataKV
	if err := json.Unmarshal(respBody, &respJson); err != nil {
		return status, err, requestId
	}

	if respJson.Data != nil {
		if s, ok := respJson.Data["status"]; ok {
			status, err = strconv.ParseBool(fmt.Sprint(s))
		}
	}

	return status, err, requestId
}

// 添加禁言白名单
func (rc *RongCloud) UGGroupMutedWhitelistAdd(groupId string, userIds []string) (err error, requestId string) {
	if groupId == "" {
		return RCErrorNewV2(1002, "Paramer 'groupId' is required"), ""
	}

	if len(userIds) == 0 {
		return RCErrorNewV2(1002, "Paramer 'userIds' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/%s/allowed-users", rc.rongCloudURI, groupId)
	req := httplib.Post(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	// json body
	postBody := map[string]interface{}{
		"user_ids": userIds,
	}
	_, err = req.JSONBody(postBody)
	if err != nil {
		return err, ""
	}

	// http
	_, err = rc.doV2(req)

	return err, requestId
}

// 移除禁言白名单
func (rc *RongCloud) UGGroupMutedWhitelistRemove(groupId string, userIds []string) (err error, requestId string) {
	if groupId == "" {
		return RCErrorNewV2(1002, "Paramer 'groupId' is required"), ""
	}

	if len(userIds) == 0 {
		return RCErrorNewV2(1002, "Paramer 'userIds' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/%s/allowed-users", rc.rongCloudURI, groupId)
	req := httplib.Delete(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	// json body
	postBody := map[string]interface{}{
		"user_ids": userIds,
	}
	_, err = req.JSONBody(postBody)
	if err != nil {
		return err, ""
	}

	// http
	_, err = rc.doV2(req)

	return err, requestId
}

// 获取禁言白名单
func (rc *RongCloud) UGGroupMutedWhitelistQuery(groupId string) (users []UGUserInfo, err error, requestId string) {
	if groupId == "" {
		return nil, RCErrorNewV2(1002, "param 'groupId' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/%s/allowed-users", rc.rongCloudURI, groupId)
	req := httplib.Get(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	// http
	respBody, err := rc.doV2(req)
	if err != nil {
		return users, err, requestId
	}

	// 处理返回结果
	var respJson RespDataArray
	if err := json.Unmarshal(respBody, &respJson); err != nil {
		return users, err, requestId
	}

	if respJson.Data != nil {
		if gs, ok := respJson.Data["users"]; ok {
			if gs != nil {
				for _, v := range gs {
					t := UGUserInfo{Id: fmt.Sprint(v["id"])}
					users = append(users, t)
				}
			}
		}
	}

	return users, err, requestId
}

// 创建群频道
func (rc *RongCloud) UGChannelCreate(groupId, channelId string) (err error, requestId string) {
	if groupId == "" {
		return RCErrorNewV2(1002, "param 'groupId' is required"), ""
	}

	if channelId == "" {
		return RCErrorNewV2(1002, "param 'channelId' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/%s/channels/%s", rc.rongCloudURI, groupId, channelId)
	req := httplib.Get(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	// http
	_, err = rc.doV2(req)

	return err, requestId
}

// 删除群频道
func (rc *RongCloud) UGChannelDelete(groupId, channelId string) (err error, requestId string) {
	if groupId == "" {
		return RCErrorNewV2(1002, "param 'groupId' is required"), ""
	}

	if channelId == "" {
		return RCErrorNewV2(1002, "param 'channelId' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/%s/channels/%s", rc.rongCloudURI, groupId, channelId)
	req := httplib.Delete(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	// http
	_, err = rc.doV2(req)

	return err, requestId
}

// 查询群频道列表
func (rc *RongCloud) UGChannelQuery(groupId string, page, size int) (channels []UGChannelInfo, err error, requestId string) {
	if groupId == "" {
		return nil, RCErrorNewV2(1002, "param 'groupId' is required"), ""
	}

	url := fmt.Sprintf("%s/v2/ultragroups/%s/channels", rc.rongCloudURI, groupId)
	req := httplib.Get(url)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	requestId = rc.fillHeaderV2(req)

	req.Param("page", strconv.Itoa(page))
	req.Param("limit", strconv.Itoa(size))

	// http
	respBody, err := rc.doV2(req)
	if err != nil {
		return channels, err, requestId
	}

	// 处理返回结果
	var respJson RespDataArray
	if err := json.Unmarshal(respBody, &respJson); err != nil {
		return channels, err, requestId
	}

	if respJson.Data != nil {
		if gs, ok := respJson.Data["channel_list"]; ok {
			if gs != nil {
				for _, v := range gs {
					t := UGChannelInfo{ChannelId: fmt.Sprint(v["channel_id"]),
						CreateTime: fmt.Sprint(v["create_time"])}
					channels = append(channels, t)
				}
			}
		}
	}

	return channels, err, requestId
}
