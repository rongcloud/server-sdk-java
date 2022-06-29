// Conversation 会话

package sdk

import (
	"encoding/json"
	"fmt"
	"net/http"
	"strconv"
	"time"

	"github.com/astaxie/beego/httplib"
)

// ConversationType 会话类型
type ConversationType int

const (
	PRIVATE            ConversationType = iota + 1 // PRIVATE 二人会话
	DISCUSSION                                     // DISCUSSION 讨论组会话
	GROUP                                          // GROUP 群组会话
	CHATROOM                                       // CHATROOM 聊天室会话
	CUSTOM                                         // CUSTOM 客服会话
	SYSTEM                                         // SYSTEM 系统通知
	APP_PUBLIC_SERVICE                             // APP_PUBLIC_SERVICE 应用公众服务
	PUBLIC_SERVICE                                 // PUBLIC_SERVICE 公众服务
	ULTRA_GROUP                                    // ULTRA_GROUP 超级群服务

	ConversationTypePrivate ConversationType = 1  // ConversationTypePrivate 二人会话
	ConversationTypeGroup   ConversationType = 3  // ConversationTypeGroup 群组会话
	ConversationTypeSystem  ConversationType = 6  // ConversationTypeSystem 系统
	ConversationTypeUG      ConversationType = 10 // ConversationTypeUG 超级群

	ConversationUnPushLevelAllMessage        = -1 // ConversationUnPushLevelAllMessage 全部消息通知
	ConversationUnPushLevelNotSet            = 0  // ConversationUnPushLevelNotSet 未设置
	ConversationUnPushLevelAtMessage         = 1  // ConversationUnPushLevelAtMessage 仅@消息通知
	ConversationUnPushLevelAtUser            = 2  // ConversationUnPushLevelAtUser @指定用户通知
	ConversationUnPushLevelAtAllGroupMembers = 4  // ConversationUnPushLevelAtAllGroupMembers @群全员通知
	ConversationUnPushLevelNotRecv           = 5  // ConversationUnPushLevelNotRecv 不接收通知
)

// ConversationTop :会话置顶
//*
//@param userId	：必传	            用户ID，会话所属的用户
//@param conversationType：不必传	会话类型。支持的会话类型包括：1（二人会话）、3（群组会话）、6（系统会话）。
//@param targetId:	必传	            需要设置的目标 ID，根据会话类型不同为单聊用户 ID、群聊 ID、系统目标 ID
//@param setTop	:	必传		            true 表示置顶，false 表示取消置顶。
//
//@return error
//*/
func (rc *RongCloud) ConversationTop(conversationType ConversationType, userId, targetId, setTop string) error {
	if len(userId) == 0 {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}
	if len(targetId) == 0 {
		return RCErrorNew(1002, "Paramer 'targetId' is required")
	}
	if len(setTop) == 0 {
		return RCErrorNew(1002, "Paramer 'setTop' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/conversation/top/set." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("userId", userId)
	req.Param("conversationType", fmt.Sprintf("%v", conversationType))
	req.Param("targetId", targetId)
	req.Param("setTop", setTop)
	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ConversationMute 设置用户某个会话屏蔽 Push
/*
*@param  conversationType:会话类型 PRIVATE、GROUP、DISCUSSION、SYSTEM。
*@param  userID:设置用户 ID。
*@param  targetID:需要屏蔽的目标 ID。
*
*@return error
 */
func (rc *RongCloud) ConversationMute(conversationType ConversationType, userID, targetID string,
	options ...MsgOption) error {

	if conversationType == 0 {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}

	if userID == "" {
		return RCErrorNew(1002, "Paramer 'userID' is required")
	}

	if targetID == "" {
		return RCErrorNew(1002, "Paramer 'targetID' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/conversation/notification/set." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("requestId", userID)
	req.Param("conversationType", fmt.Sprintf("%v", conversationType))
	req.Param("targetId", targetID)
	req.Param("isMuted", "1")

	if extraOptins.busChannel != "" {
		req.Param("busChannel", extraOptins.busChannel)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ConversationUnmute 设置用户某个会话接收 Push
/*
*@param  conversationType:会话类型 PRIVATE、GROUP、DISCUSSION、SYSTEM。
*@param  userID:设置用户 ID。
*@param  targetID:需要屏蔽的目标 ID。
*
*@return error
 */
func (rc *RongCloud) ConversationUnmute(conversationType ConversationType, userID, targetID string,
	options ...MsgOption) error {
	if conversationType == 0 {
		return RCErrorNew(1002, "Paramer 'conversationType' is required")
	}

	if userID == "" {
		return RCErrorNew(1002, "Paramer 'userID' is required")
	}

	if targetID == "" {
		return RCErrorNew(1002, "Paramer 'targetID' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/conversation/notification/set." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("requestId", userID)
	req.Param("conversationType", fmt.Sprintf("%v", conversationType))
	req.Param("targetId", targetID)
	req.Param("isMuted", "0")

	if extraOptins.busChannel != "" {
		req.Param("busChannel", extraOptins.busChannel)
	}

	_, err := rc.do(req)
	if err != nil {
		rc.urlError(err)
	}
	return err
}

// ConversationGet 免打扰会话状态获取
/*
*@param  conversationType:会话类型 PRIVATE、GROUP、DISCUSSION、SYSTEM。
*@param  userID:设置用户 ID。
*@param  targetID:需要屏蔽的目标 ID。
*
*@return int error
 */
func (rc *RongCloud) ConversationGet(conversationType ConversationType, userID, targetID string,
	options ...MsgOption) (int, error) {
	if conversationType == 0 {
		return -1, RCErrorNew(1002, "Paramer 'conversationType' is required")
	}

	if userID == "" {
		return -1, RCErrorNew(1002, "Paramer 'userID' is required")
	}

	if targetID == "" {
		return -1, RCErrorNew(1002, "Paramer 'targetID' is required")
	}

	extraOptins := modifyMsgOptions(options)

	req := httplib.Post(rc.rongCloudURI + "/conversation/notification/get." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("requestId", userID)
	req.Param("conversationType", fmt.Sprintf("%v", conversationType))
	req.Param("targetId", targetID)

	if extraOptins.busChannel != "" {
		req.Param("busChannel", extraOptins.busChannel)
	}

	response, err := req.Response()
	if err != nil {
		return -1, err
	}

	rc.checkStatusCode(response)

	rep, err := req.Bytes()
	if err != nil {
		rc.urlError(err)
		return -1, err
	}
	var code CodeResult
	var isMuted int
	_ = json.Unmarshal(rep, &struct {
		*CodeResult
		IsMuted *int `json:"isMuted"`
	}{
		&code,
		&isMuted,
	})
	if code.Code != 200 {
		return -1, code
	}
	return isMuted, nil
}

// ConversationTypeNotificationSet 按会话类型设置免打扰, 用户设置指定会话类型（单聊、群聊、超级群、系统消息）的免打扰状态。
func (rc *RongCloud) ConversationTypeNotificationSet(ct ConversationType, requestId string, unPushLevel int) error {
	if ct != ConversationTypePrivate && ct != ConversationTypeGroup && ct != ConversationTypeSystem && ct != ConversationTypeUG {
		return RCErrorNew(1002, "Paramer 'conversationType' was wrong")
	}

	if requestId == "" {
		return RCErrorNew(1002, "Paramer 'requestId' was wrong")
	}

	if unPushLevel != ConversationUnPushLevelAllMessage && unPushLevel != ConversationUnPushLevelNotSet && unPushLevel != ConversationUnPushLevelAtMessage &&
		unPushLevel != ConversationUnPushLevelAtUser && unPushLevel != ConversationUnPushLevelAtAllGroupMembers && unPushLevel != ConversationUnPushLevelNotRecv {
		return RCErrorNew(1002, "Paramer 'unPushLevel' was wrong")
	}

	req := httplib.Post(rc.rongCloudURI + "/conversation/type/notification/set.json")

	req.Param("conversationType", strconv.Itoa(int(ct)))
	req.Param("requestId", requestId)
	req.Param("unpushLevel", strconv.Itoa(unPushLevel))
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)

	rc.fillHeader(req)

	body, err := rc.do(req)
	if err != nil {
		return err
	}

	resp := struct {
		Code int `json:"code"`
	}{}
	if err = json.Unmarshal(body, &resp); err != nil {
		return err
	}

	if resp.Code != http.StatusOK {
		return fmt.Errorf("Response error. code: %d", resp.Code)
	}

	return nil
}

// ConversationTypeNotificationGet 查询用户指定会话类型（单聊、群聊、超级群、系统消息）的免打扰状态。
func (rc *RongCloud) ConversationTypeNotificationGet(ct ConversationType, requestId string) (int, error) {
	if ct != ConversationTypePrivate && ct != ConversationTypeGroup && ct != ConversationTypeSystem && ct != ConversationTypeUG {
		return 0, RCErrorNew(1002, "Paramer 'conversationType' was wrong")
	}

	if requestId == "" {
		return 0, RCErrorNew(1002, "Paramer 'requestId' was wrong")
	}

	req := httplib.Post(rc.rongCloudURI + "/conversation/type/notification/get.json")

	req.Param("conversationType", strconv.Itoa(int(ct)))
	req.Param("requestId", requestId)

	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)

	rc.fillHeader(req)

	body, err := rc.do(req)
	if err != nil {
		return 0, err
	}

	resp := struct {
		Code    int `json:"code"`
		IsMuted int `json:"isMuted"`
	}{}
	if err = json.Unmarshal(body, &resp); err != nil {
		return 0, err
	}

	if resp.Code != http.StatusOK {
		return 0, fmt.Errorf("Response error. code: %d", resp.Code)
	}

	return resp.IsMuted, nil
}

// ConversationNotificationSet 设置指定会话消息免打扰接口, 设置用户指定会话消息提醒状态
func (rc *RongCloud) ConversationNotificationSet(ct ConversationType, requestId, targetId, busChannel string, isMuted, unPushLevel int) error {
	if ct != ConversationTypePrivate && ct != ConversationTypeGroup && ct != ConversationTypeSystem && ct != ConversationTypeUG {
		return RCErrorNew(1002, "Paramer 'conversationType' was wrong")
	}

	if requestId == "" {
		return RCErrorNew(1002, "Paramer 'requestId' was wrong")
	}

	if targetId == "" {
		return RCErrorNew(1002, "Paramer 'targetId' was wrong")
	}

	if isMuted != 0 && isMuted != 1 {
		return RCErrorNew(1002, "Paramer 'isMuted' was wrong")
	}

	if unPushLevel != ConversationUnPushLevelAllMessage && unPushLevel != ConversationUnPushLevelNotSet && unPushLevel != ConversationUnPushLevelAtMessage &&
		unPushLevel != ConversationUnPushLevelAtUser && unPushLevel != ConversationUnPushLevelAtAllGroupMembers && unPushLevel != ConversationUnPushLevelNotRecv {
		return RCErrorNew(1002, "Paramer 'unPushLevel' was wrong")
	}

	req := httplib.Post(rc.rongCloudURI + "/conversation/notification/set.json")

	req.Param("conversationType", strconv.Itoa(int(ct)))
	req.Param("requestId", requestId)
	req.Param("targetId", targetId)
	req.Param("isMuted", strconv.Itoa(isMuted))
	req.Param("unpushLevel", strconv.Itoa(unPushLevel))

	if busChannel != "" {
		req.Param("busChannel", busChannel)
	}

	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)

	rc.fillHeader(req)

	body, err := rc.do(req)
	if err != nil {
		return err
	}

	resp := struct {
		Code int `json:"code"`
	}{}
	if err = json.Unmarshal(body, &resp); err != nil {
		return err
	}

	if resp.Code != http.StatusOK {
		return fmt.Errorf("Response error. code: %d", resp.Code)
	}

	return nil
}

// ConversationNotificationGet 查询指定会话消息免打扰接口
func (rc *RongCloud) ConversationNotificationGet(ct ConversationType, requestId, targetId, busChannel string) (int, error) {
	if ct != ConversationTypePrivate && ct != ConversationTypeGroup && ct != ConversationTypeSystem && ct != ConversationTypeUG {
		return 0, RCErrorNew(1002, "Paramer 'conversationType' was wrong")
	}

	if requestId == "" {
		return 0, RCErrorNew(1002, "Paramer 'requestId' was wrong")
	}

	if targetId == "" {
		return 0, RCErrorNew(1002, "Paramer 'targetId' was wrong")
	}

	req := httplib.Post(rc.rongCloudURI + "/conversation/notification/get.json")

	req.Param("conversationType", strconv.Itoa(int(ct)))
	req.Param("requestId", requestId)
	req.Param("targetId", targetId)

	if busChannel != "" {
		req.Param("busChannel", busChannel)
	}

	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)

	rc.fillHeader(req)

	body, err := rc.do(req)
	if err != nil {
		return 0, err
	}

	resp := struct {
		Code    int `json:"code"`
		IsMuted int `json:"isMuted"`
	}{}
	if err = json.Unmarshal(body, &resp); err != nil {
		return 0, err
	}

	if resp.Code != http.StatusOK {
		return 0, fmt.Errorf("Response error. code: %d", resp.Code)
	}

	return resp.IsMuted, nil
}
