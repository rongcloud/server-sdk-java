// Conversation Session

package sdk

import (
	"encoding/json"
	"fmt"
	"net/http"
	"strconv"
	"time"

	"github.com/astaxie/beego/httplib"
)

// ConversationType Conversation type
type ConversationType int

const (
	PRIVATE            ConversationType = iota + 1 // PRIVATE one-to-one chat
	DISCUSSION                                     // DISCUSSION discussion group chat
	GROUP                                          // GROUP group chat
	CHATROOM                                       // CHATROOM chatroom chat
	CUSTOM                                         // CUSTOM customer service chat
	SYSTEM                                         // SYSTEM system notification
	APP_PUBLIC_SERVICE                             // APP_PUBLIC_SERVICE app public service
	PUBLIC_SERVICE                                 // PUBLIC_SERVICE public service
	ULTRA_GROUP                                    // ULTRA_GROUP ultra group service

	ConversationTypePrivate ConversationType = 1  // ConversationTypePrivate one-to-one chat
	ConversationTypeGroup   ConversationType = 3  // ConversationTypeGroup group chat
	ConversationTypeSystem  ConversationType = 6  // ConversationTypeSystem system
	ConversationTypeUG      ConversationType = 10 // ConversationTypeUG ultra group

	ConversationUnPushLevelAllMessage        = -1 // ConversationUnPushLevelAllMessage all message notifications
	ConversationUnPushLevelNotSet            = 0  // ConversationUnPushLevelNotSet not set
	ConversationUnPushLevelAtMessage         = 1  // ConversationUnPushLevelAtMessage only @message notifications
	ConversationUnPushLevelAtUser            = 2  // ConversationUnPushLevelAtUser @specific user notifications
	ConversationUnPushLevelAtAllGroupMembers = 4  // ConversationUnPushLevelAtAllGroupMembers @all group members notifications
	ConversationUnPushLevelNotRecv           = 5  // ConversationUnPushLevelNotRecv do not receive notifications
)

// ConversationTop : Pin a conversation
//*
//@param userId	: Required	            User ID, the user to whom the conversation belongs
//@param conversationType: Optional	    Conversation type. Supported conversation types include: 1 (one-to-one chat), 3 (group chat), 6 (system conversation).
//@param targetId: Required	            Target ID to be set, which varies depending on the conversation type (user ID for one-to-one chat, group ID for group chat, system target ID for system conversation).
//@param setTop	: Required	            true to pin, false to unpin.
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

// ConversationMute Mutes push notifications for a specific conversation of a user.
/*
*@param  conversationType: Specifies the conversation type: PRIVATE, GROUP, DISCUSSION, SYSTEM.
*@param  userID: The user ID for whom the conversation is being muted.
*@param  targetID: The target ID of the conversation to be muted.
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

// ConversationUnmute Sets the user to receive push notifications for a specific conversation
/*
*@param  conversationType: Conversation type, PRIVATE, GROUP, DISCUSSION, SYSTEM.
*@param  userID: The user ID to set.
*@param  targetID: The target ID to unmute.
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

// ConversationGet Get Conversation Do Not Disturb Status
/*
* @param conversationType: Conversation type, can be PRIVATE, GROUP, DISCUSSION, or SYSTEM.
* @param userID: User ID to set.
* @param targetID: Target ID to be muted.
*
* @return int error
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

// ConversationTypeNotificationSet Sets the Do Not Disturb status for a specific conversation type (one-to-one chat, group chat, ultra group, system message).
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

// ConversationTypeNotificationGet Queries the Do Not Disturb status for a specified conversation type (one-to-one chat, group chat, ultra group, system message) of a user.
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

// ConversationNotificationSet Sets the Do Not Disturb status for a specified conversation
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

// ConversationNotificationGet Retrieves the Do Not Disturb status for a specified conversation
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
