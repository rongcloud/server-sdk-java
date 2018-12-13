package RCserverSDK

import (
	"github.com/astaxie/beego/httplib"
	"fmt"
	"encoding/json"
)

type ConversationType int

const (
	PRIVATE ConversationType = 1
	DISCUSSION ConversationType = 2
	GROUP ConversationType = 3
	CUSTOMERSERVICE	ConversationType = 4
	SYSTEM ConversationType = 4
)


func (rc *RongCloud) ConversationMute (conversationType ConversationType, userID, targetID string) error{

	if(conversationType == 0) {
		return RCErrorNew(20005,"Paramer 'userId' is required")
	}

	if(userID == "") {
		return RCErrorNew(20005,"Paramer 'userID' is required")
	}

	if(targetID == "") {
		return RCErrorNew(20005,"Paramer 'targetID' is required")
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


func (rc *RongCloud) ConversationUnmute (conversationType ConversationType, userID, targetID string) error{

	if(conversationType == 0) {
		return RCErrorNew(20005,"Paramer 'conversationType' is required")
	}

	if(userID == "") {
		return RCErrorNew(20005,"Paramer 'userID' is required")
	}

	if(targetID == "") {
		return RCErrorNew(20005,"Paramer 'targetID' is required")
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

