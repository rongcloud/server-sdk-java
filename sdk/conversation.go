// Conversation 会话

package sdk

import (
	"encoding/json"
	"fmt"
	"time"

	"github.com/astaxie/beego/httplib"
)

// ConversationType 会话类型
type ConversationType int

const (
	// PRIVATE 单聊
	PRIVATE ConversationType = iota + 1
	// DISCUSSION 讨论组
	DISCUSSION
	// GROUP 群聊
	GROUP
	// SYSTEM 系统
	SYSTEM
	// CUSTOMERSERVICE 客服
	CUSTOMERSERVICE
)

// ConversationMute 设置用户某个会话屏蔽 Push
/*
*@param  conversationType:会话类型 PRIVATE、GROUP、DISCUSSION、SYSTEM。
*@param  userID:设置用户 ID。
*@param  targetID:需要屏蔽的目标 ID。
*
*@return error
 */
func (rc *RongCloud) ConversationMute(conversationType ConversationType, userID, targetID string) error {

	if conversationType == 0 {
		return RCErrorNew(1002, "Paramer 'userId' is required")
	}

	if userID == "" {
		return RCErrorNew(1002, "Paramer 'userID' is required")
	}

	if targetID == "" {
		return RCErrorNew(1002, "Paramer 'targetID' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/conversation/notification/set." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("requestId", userID)
	req.Param("conversationType", fmt.Sprintf("%v", conversationType))
	req.Param("targetId", targetID)
	req.Param("isMuted", "1")

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
func (rc *RongCloud) ConversationUnmute(conversationType ConversationType, userID, targetID string) error {
	if conversationType == 0 {
		return RCErrorNew(1002, "Paramer 'conversationType' is required")
	}

	if userID == "" {
		return RCErrorNew(1002, "Paramer 'userID' is required")
	}

	if targetID == "" {
		return RCErrorNew(1002, "Paramer 'targetID' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/conversation/notification/set." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("requestId", userID)
	req.Param("conversationType", fmt.Sprintf("%v", conversationType))
	req.Param("targetId", targetID)
	req.Param("isMuted", "0")

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
func (rc *RongCloud) ConversationGet(conversationType ConversationType, userID, targetID string) (int, error) {
	if conversationType == 0 {
		return -1, RCErrorNew(1002, "Paramer 'conversationType' is required")
	}

	if userID == "" {
		return -1, RCErrorNew(1002, "Paramer 'userID' is required")
	}

	if targetID == "" {
		return -1, RCErrorNew(1002, "Paramer 'targetID' is required")
	}

	req := httplib.Post(rc.rongCloudURI + "/conversation/notification/get." + ReqType)
	req.SetTimeout(time.Second*rc.timeout, time.Second*rc.timeout)
	rc.fillHeader(req)
	req.Param("requestId", userID)
	req.Param("conversationType", fmt.Sprintf("%v", conversationType))
	req.Param("targetId", targetID)

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
