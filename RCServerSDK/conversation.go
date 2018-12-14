// Conversation 会话

package rcserversdk

import (
	"encoding/json"
	"fmt"

	"github.com/astaxie/beego/httplib"
)

// ConversationType 会话类型
type ConversationType int

const (
	// PRIVATE 单聊
	PRIVATE ConversationType = 1
	// DISCUSSION 讨论组
	DISCUSSION ConversationType = 2
	// GROUP 群聊
	GROUP ConversationType = 3
	// SYSTEM 系统
	SYSTEM ConversationType = 4
	// CUSTOMERSERVICE 客服
	CUSTOMERSERVICE ConversationType = 5
)

// ConversationMute 设置用户某个会话屏蔽 Push
func (rc *RongCloud) ConversationMute(conversationType ConversationType, userID, targetID string) error {

	if conversationType == 0 {
		return RCErrorNew(20005, "Paramer 'userId' is required")
	}

	if userID == "" {
		return RCErrorNew(20005, "Paramer 'userID' is required")
	}

	if targetID == "" {
		return RCErrorNew(20005, "Paramer 'targetID' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/conversation/notification/set." + ReqType)
	rc.FillHeader(req)
	req.Param("requestId", userID)
	req.Param("conversationType", fmt.Sprintf("%v", conversationType))
	req.Param("targetId", targetID)
	req.Param("isMuted", "1")

	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}

// ConversationUnmute 设置用户某个会话接收 Push
func (rc *RongCloud) ConversationUnmute(conversationType ConversationType, userID, targetID string) error {
	if conversationType == 0 {
		return RCErrorNew(20005, "Paramer 'conversationType' is required")
	}

	if userID == "" {
		return RCErrorNew(20005, "Paramer 'userID' is required")
	}

	if targetID == "" {
		return RCErrorNew(20005, "Paramer 'targetID' is required")
	}

	req := httplib.Post(rc.RongCloudURI + "/conversation/notification/set." + ReqType)
	rc.FillHeader(req)
	req.Param("requestId", userID)
	req.Param("conversationType", fmt.Sprintf("%v", conversationType))
	req.Param("targetId", targetID)
	req.Param("isMuted", "0")

	rep, err := req.Bytes()
	if err != nil {
		return err
	}
	var code CodeReslut
	if err := json.Unmarshal(rep, &code); err != nil {
		return err
	}
	if code.Code != 200 {
		return RCErrorNew(code.Code, code.ErrorMessage)
	}
	return nil
}
