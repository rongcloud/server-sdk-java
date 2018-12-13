package message

import (
	"github.com/astaxie/beego/httplib"
	"strconv"
	"encoding/json"
	"strings"
	"time"
	"io/ioutil"
	"github.com/rongcloud/server-sdk-go/RCserverSDK"

	"fmt"
	)


// User user模块
type Message struct {
	*RCserverSDK.RongCloud
}

type TemplateMsgContent struct {
	TargetID string
	Data map[string]string
	PushContent string
}


type MsgContent struct {
	Content string `json:"content"`
	Extra string `json:"extra"`
}




/*
	 *发送单聊消息方法（一个用户向另外一个用户发送消息，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人，如：一次发送 1000 人时，示为 1000 条消息。）
	 *
	 *@param  senderId:发送人用户 Id。
	 *@param  targetId:接收用户 Id，可以实现向多人发送消息，每次上限为 1000 人。
	 *@param  objectName:发送的消息类型。
	 *@param  msg:消息。
	 *@param  pushContent:定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息。如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。
	 *@param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。
	 *@param  count:针对 iOS 平台，Push 时用来控制未读消息显示数，只有在 toUserId 为一个用户 Id 的时候有效。
	 *@param  verifyBlacklist:是否过滤发送人黑名单列表，0 表示为不过滤、 1 表示为过滤，默认为 0 不过滤。
	 *@param  isPersisted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息。
	 *@param  isCounted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数，0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。
	 *@param  isIncludeSender:发送用户自已是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。
	 *
	 *@return RCError
*/

// Send 一对一发消息
func (m *Message)PrivateSend(senderID, targetID, objectName string, msg MsgContent,
	pushContent, pushData string, count, verifyBlacklist, isPersisted, isCounted, isIncludeSender int) *RCserverSDK.RCError {
	if( senderID == "") {
		return RCserverSDK.RCErrorNew(20005,"Paramer 'senderID' is required");
	}

	if(targetID == "") {
		return RCserverSDK.RCErrorNew(20005,"Paramer 'targetID' is required");
	}

	req := httplib.Post(RCserverSDK.RONGCLOUDURI + "/message/private/publish." + RCserverSDK.ReqType)
	req.SetTimeout(time.Second * 5, time.Second * 5)
	m.FillHeader(req)
	req.Param("fromUserId", senderID)
	req.Param("toUserId", targetID)
	req.Param("objectName", objectName)

	bytes, err := json.Marshal(msg)
	if err != nil {
		return RCserverSDK.RCErrorNew(20013,err.Error())
	}

	req.Param("content",string(bytes))
	req.Param("pushContent", pushContent)
	req.Param("pushData", pushData)
	req.Param("count", strconv.Itoa(count))
	req.Param("verifyBlacklist", strconv.Itoa(verifyBlacklist))
	req.Param("isPersisted", strconv.Itoa(isPersisted))
	req.Param("isCounted", strconv.Itoa(isCounted))
	req.Param("isIncludeSender", strconv.Itoa(isIncludeSender))

	rep, err := req.DoRequest()
	if err != nil {
		return RCserverSDK.RCErrorNew(20013,err.Error());
	}
	body, err := ioutil.ReadAll(rep.Body)
	if err != nil {
		return RCserverSDK.RCErrorNew(20013,err.Error())
	}else{
		strData := string(body)
		var ret = RCserverSDK.RCError{}

		dec := json.NewDecoder(strings.NewReader(strData))
		err := dec.Decode(&ret)
		fmt.Println(ret)
		if err != nil {
			return RCserverSDK.RCErrorNew(20013,err.Error())
		}

		return nil
	}
}

/*
	 *撤回单聊消息方法
	 *
	 *@param  senderId:发送人用户 Id。
	 *@param  targetId:接收用户 Id，可以实现向多人发送消息，每次上限为 1000 人。
	 *@param  uId:消息的唯一标识，各端 SDK 发送消息成功后会返回 uId。
	 *@param  sentTime:消息的发送时间，各端 SDK 发送消息成功后会返回 sentTime。
	 *@param  conversationType:会话类型，二人会话是 1 、群组会话是 3 。
	 *@return RCError
*/

// recall 一对一撤回消息
func (m *Message)PrivateRecall(senderID, targetID, uId string, sentTime ,conversationType int) *RCserverSDK.RCError {
	if (senderID == "") {
		return RCserverSDK.RCErrorNew(20005, "Paramer 'senderID' is required");
	}

	if (targetID == "") {
		return RCserverSDK.RCErrorNew(20005, "Paramer 'targetID' is required");
	}

	req := httplib.Post(RCserverSDK.RONGCLOUDURI + "/message/recall." + RCserverSDK.ReqType)
	req.SetTimeout(time.Second*5, time.Second*5)
	m.FillHeader(req)
	req.Param("fromUserId", senderID)
	req.Param("targetId", targetID)
	req.Param("messageUID", uId)
	req.Param("sentTime", strconv.Itoa(sentTime))
	req.Param("conversationType",strconv.Itoa(conversationType))

	rep, err := req.DoRequest()
	if err != nil {
		return RCserverSDK.RCErrorNew(20013, err.Error());
	}
	body, err := ioutil.ReadAll(rep.Body)
	if err != nil {
		return RCserverSDK.RCErrorNew(20013, err.Error())
	} else {
		strData := string(body)
		var ret= RCserverSDK.RCError{}

		dec := json.NewDecoder(strings.NewReader(strData))
		err := dec.Decode(&ret)
		fmt.Println(ret)
		if err != nil {
			return RCserverSDK.RCErrorNew(20013, err.Error())
		}

		return nil
	}
}

/*
	 *向多个用户发送不同内容消息
	 *
	 *@param  senderID:发送人用户 Id。
	 *@param  objectName:发送的消息类型。
	 *@param  template:消息模版。
	 *@param  content:数据内容，包含消息内容和接收者。
	 *
	 *@return RCError
*/

// SendTemplate 向多个用户发送不同内容消息
func (m *Message)PrivateSendTemplate(senderID, objectName string, template MsgContent, content []TemplateMsgContent) *RCserverSDK.RCError {
	if (senderID == "") {
		return RCserverSDK.RCErrorNew(20005, "Paramer 'senderID' is required");
	}

	req := httplib.Post(RCserverSDK.RONGCLOUDURI + "/message/private/publish_template." + RCserverSDK.ReqType)
	req.SetTimeout(time.Second*5, time.Second*5)
	m.FillHeader(req)

	var toUserIDs, push []string
	var values []map[string]string

	for _, v := range content{
		if (v.TargetID ==  "") {
			return RCserverSDK.RCErrorNew(20005, "Paramer 'TargetID' is required");
		}
		toUserIDs = append(toUserIDs, v.TargetID)
		values = append(values, v.Data)
		push = append(push,v.PushContent)
	}

	bytes, err := json.Marshal(template)
	if err != nil {
		return RCserverSDK.RCErrorNew(20013,err.Error())
	}
	fmt.Println(string(bytes))

	param := map[string]interface{}{}
	param["fromUserId"] = senderID
	param["objectName" ] = objectName
	param["content"] = string(bytes)
	param["toUserId"] = toUserIDs
	param["values"] = values
	//param["pushData"] = push
	param["pushContent"] = push
	param["verifyBlacklist" ] = 0

	byts, err := json.Marshal(param)
	if err != nil {
		return RCserverSDK.RCErrorNew(20013,err.Error())
	}
	fmt.Println(string(byts))
	req.JSONBody(param)

	fmt.Println()

	rep, err := req.DoRequest()

	if err != nil {
		return RCserverSDK.RCErrorNew(20013, err.Error());
	}
	body, err := ioutil.ReadAll(rep.Body)
	if err != nil {
		return RCserverSDK.RCErrorNew(20014, err.Error())
	} else {
		strData := string(body)
		var ret= RCserverSDK.RCError{}

		dec := json.NewDecoder(strings.NewReader(strData))
		fmt.Println(strData)
		err := dec.Decode(&ret)
		fmt.Println(ret)
		if err != nil {
			return RCserverSDK.RCErrorNew(20015, err.Error())
		}

		return nil
	}
	return nil
}



