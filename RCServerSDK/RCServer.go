// @APIVersion 1.0.0
// @Title 融云服务器SDK
// @Description 融云服务器SDK的go版本.
// @Contact yesidi@me.com
/*
//使用
先安装 httplib 库：
httplib 库主要用来模拟客户端发送 HTTP 请求，类似于 Curl 工具，支持 JQuery 类似的链式操作。使用起来相当的方便；通过如下方式进行安装：

go get github.com/astaxie/beego/httplib

json用法: rcServer, initError := RCServerSDK.NewRCServer("your_appKey", "your_appSecret", "json")
xml用法: rcServer, initError := RCServerSDK.NewRCServer("your_appKey", "your_appSecret", "xml")

	byteData, rcError := rcServer.UserGetToken("your_user_id", "your_name", "your_portrait_uri")
	println("result:", string(byteData))
*/

package RCServerSDK

import (
	"bytes"
	"crypto/sha1"
	"errors"
	"fmt"
	"io"
	"io/ioutil"
	"math/rand"
	"net/http"
	"net/url"
	"strconv"
	"time"

	"github.com/astaxie/beego/httplib"
)

const (
	RC_SERVER_API_URL    = "http://api.cn.ronghub.com"
	RC_SMS_API_URL       = "http://api.sms.ronghub.com"
	RC_USER_GET_TOKEN    = "/user/getToken"
	RC_USER_REFRESH      = "/user/refresh"
	RC_USER_CHECK_ONLINE = "/user/checkOnline"
	RC_USER_BLOCK        = "/user/block"
	RC_USER_UNBLOCK      = "/user/unblock"
	RC_USER_BLOCK_QUERY  = "/user/block/query"
	RC_USER_BLACK_ADD    = "/user/blacklist/add"
	RC_USER_BLACK_REMOVE = "/user/blacklist/remove"
	RC_USER_BLACK_QUERY  = "/user/blacklist/query"

	RC_MESSAGE_PRIVATE_PUBLISH          = "/message/private/publish"
	RC_MESSAGE_PRIVATE_PUBLISH_TEMPLATE = "/message/private/publish_template"
	RC_MESSAGE_SYSTEM_PUBLISH           = "/message/system/publish"
	RC_MESSAGE_SYSTEM_PUBLISH_TEMPLATE  = "/message/system/publish_template"
	RC_MESSAGE_GROUP_PUBLISH            = "/message/group/publish"
	RC_MESSAGE_DISCUSSION_PUBLISH       = "/message/discussion/publish"
	RC_MESSAGE_CHATROOM_PUBLISH         = "/message/chatroom/publish"
	RC_MESSAGE_BROADCAST                = "/message/broadcast"
	RC_MESSAGE_HISTORY                  = "/message/history"
	RC_MESSAGE_HISTORY_DELETE           = "/message/history/delete"

	RC_GROUP_CREATE  = "/group/create"
	RC_GROUP_JOIN    = "/group/join"
	RC_GROUP_QUIT    = "/group/quit"
	RC_GROUP_DISMISS = "/group/dismiss"
	RC_GROUP_REFRESH = "/group/refresh"
	RC_GROUP_SYNC    = "/group/sync"

	RC_CHATROOM_CREATE  = "/chatroom/create"
	RC_CHATROOM_DESTROY = "/chatroom/destroy"
	RC_CHATROOM_QUERY   = "/chatroom/query"

	RC_WORDFILTER_ADD    = "/wordfilter/add"
	RC_WORDFILTER_DELETE = "/wordfilter/delete"
	RC_WORDFILTER_QUERY  = "/wordfilter/list"
	RC_GROUP_USER_QUERY  = "/group/user/query"

	RC_GROUP_USER_GAG_ADD         = "/group/user/gag/add"
	RC_GROUP_USER_GAG_ROLLBACK    = "/group/user/gag/rollback"
	RC_GROUP_USER_GAG_QUERY       = "/group/user/gag/list"
	RC_CHATROOM_JOIN              = "/chatroom/join"
	RC_CHATROOM_USER_QUERY        = "/chatroom/user/query"
	RC_CHATROOM_USER_GAG_ADD      = "/chatroom/user/gag/add"
	RC_CHATROOM_USER_GAG_ROLLBACK = "/chatroom/user/gag/rollback"
	RC_CHATROOM_USER_GAG_QUERY    = "/chatroom/user/gag/list"

	RC_CHATROOM_USER_BLOCK_ADD             = "/chatroom/user/block/add"
	RC_CHATROOM_USER_BLOCK_ROLLBACK        = "/chatroom/user/block/rollback"
	RC_CHATROOM_USER_BLOCK_QUERY           = "/chatroom/user/block/list"
	RC_CHATROOM_MESSAGE_STOPDISTRIBUTION   = "/chatroom/message/stopDistribution"
	RC_CHATROOM_MESSAGE_RESUMEDISTRIBUTION = "/chatroom/message/resumeDistribution"

	RC_SMS_IMAGECODE_GET = "/getImgCode"
	RC_SMS_SENDCODE      = "/sendCode"
	RC_SMS_VERIFYCODE    = "/verifyCode"
)

type RCServer struct {
	apiUrl    string
	appKey    string
	appSecret string
	//json/xml
	format string
}

//初始化RCServer
func NewRCServer(appKey, appSecret, format string) (rcServer *RCServer, initError error) {
	if len(appKey) == 0 {
		return nil, errors.New("appKey不能为空！")
	} else if len(appSecret) == 0 {
		return nil, errors.New("appSecret不能为空！")
	} else if !(format == "json" || format == "xml") {
		return nil, errors.New("format格式只能为\"json\"或\"xml\"！")
	}
	server := &RCServer{
		appKey:    appKey,
		appSecret: appSecret,
		format:    "." + format,
		apiUrl:    RC_SERVER_API_URL,
	}
	return server, nil
}

//本地生成签名
//Signature (数据签名)计算方法：将系统分配的 App Secret、Nonce (随机数)、
//Timestamp (时间戳)三个字符串按先后顺序拼接成一个字符串并进行 SHA1 哈希计算。如果调用的数据签名验证失败，接口调用会返回 HTTP 状态码 401。
func getSignature(rcServer *RCServer) (nonce, timestamp, signature string) {
	nonceInt := rand.Int()
	nonce = strconv.Itoa(nonceInt)
	timeInt64 := time.Now().Unix()
	timestamp = strconv.FormatInt(timeInt64, 10)
	h := sha1.New()
	io.WriteString(h, rcServer.appSecret+nonce+timestamp)
	signature = fmt.Sprintf("%x", h.Sum(nil))
	return
}

//API签名
func fillHeader(req *httplib.BeegoHTTPRequest, rcServer *RCServer) {
	nonce, timestamp, signature := getSignature(rcServer)
	req.Header("App-Key", rcServer.appKey)
	req.Header("Nonce", nonce)
	req.Header("Timestamp", timestamp)
	req.Header("Signature", signature)
	req.Header("Content-Type", "application/x-www-form-urlencoded")
}

func fillJsonHeader(req *httplib.BeegoHTTPRequest) {
	req.Header("Content-Type", "application/json")
}

//获取 Token 方法
func (rcServer *RCServer) UserGetToken(userId, name, portraitUri string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_USER_GET_TOKEN + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	req.Param("name", name)
	req.Param("portraitUri", portraitUri)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//刷新用户信息 方法
func (rcServer *RCServer) UserRefresh(userId, name, portraitUri string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_USER_REFRESH + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	req.Param("name", name)
	req.Param("portraitUri", portraitUri)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//检查用户在线状态 方法
//请不要频繁循环调用此接口，而是选择合适的频率和时机调用，此接口设置了一定的频率限制。
func (rcServer *RCServer) UserCheckOnline(userId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_USER_CHECK_ONLINE + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//封禁用户 方法
func (rcServer *RCServer) UserBlock(userId, minute string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_USER_BLOCK + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	req.Param("minute", minute)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//解除用户封禁 方法
func (rcServer *RCServer) UserUnblock(userId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_USER_UNBLOCK + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//获取被封禁用户 方法
func (rcServer *RCServer) UserBlockQuery() ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_USER_BLOCK_QUERY + rcServer.format
	req := httplib.Post(destinationUrl)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//添加用户到黑名单 方法
func (rcServer *RCServer) UserBlackAdd(userId, blackUserId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_USER_BLACK_ADD + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	req.Param("blackUserId", blackUserId)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//从黑名单中移除用户 方法
func (rcServer *RCServer) UserBlackRemove(userId, blackUserId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_USER_BLACK_REMOVE + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	req.Param("blackUserId", blackUserId)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//获取某用户的黑名单列表 方法
func (rcServer *RCServer) UserBlackQuery(userId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_USER_BLACK_QUERY + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//发送单聊消息 方法
//说明：一个用户向另外一个用户发送消息
func (rcServer *RCServer) MessagePrivatePublish(fromUserId string, toUserIds []string, objectName, content, pushContent, pushData string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_MESSAGE_PRIVATE_PUBLISH + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("fromUserId", fromUserId)
	for _, toUserId := range toUserIds {
		req.Param("toUserId", toUserId)
	}
	req.Param("objectName", objectName)
	req.Param("content", content)
	req.Param("pushContent", pushContent)
	req.Param("pushData", pushData)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//发送单聊模板消息
//说明：一个用户向多个用户发送不同消息内容，单条消息最大 128k。
func (rcServer *RCServer) MessageTemplatePublish(fromUserId, objectName, content string, toUserIds []string, values, pushContent, pushData string, verifyBlacklist int) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_MESSAGE_PRIVATE_PUBLISH_TEMPLATE + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("fromUserId", fromUserId)
	for _, toUserId := range toUserIds {
		req.Param("toUserId", toUserId)
	}
	req.Param("objectName", objectName)
	req.Param("values", values)
	req.Param("content", content)
	req.Param("pushContent", pushContent)
	req.Param("pushData", pushData)
	req.Param("verifyBlacklist", strconv.Itoa(verifyBlacklist))
	fillHeader(req, rcServer)
	return req.Bytes()
}

//发送系统消息
//说明：一个用户向一个或多个用户发送系统消息
func (rcServer *RCServer) MessageSystemPublish(fromUserId string, toUserIds []string, objectName, content, pushContent, pushData string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_MESSAGE_SYSTEM_PUBLISH + rcServer.format

	// 由于 "github.com/astaxie/beego/httplib" 不支持发送多个 key 一样的参数(toUserId),所以这里用 "net/http"
	u := url.Values{}
	u.Add("fromUserId", fromUserId)
	for _, toUserId := range toUserIds {
		u.Add("toUserId", toUserId)
	}
	u.Add("objectName", objectName)
	u.Add("content", content)
	u.Add("pushContent", pushContent)
	u.Add("pushData", pushData)

	req, err := http.NewRequest("POST", destinationUrl, bytes.NewBufferString(u.Encode()))

	nonce, timestamp, signature := getSignature(rcServer)
	req.Header.Set("App-Key", rcServer.appKey)
	req.Header.Set("Nonce", nonce)
	req.Header.Set("Timestamp", timestamp)
	req.Header.Set("Signature", signature)
	req.Header.Set("Content-Type", "application/x-www-form-urlencoded")

	client := &http.Client{}
	res, _ := client.Do(req)
	defer res.Body.Close()
	body, err := ioutil.ReadAll(res.Body)
	return body, err
}

//发送系统模板消息 方法
//说明：一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM。
func (rcServer *RCServer) MessageSystemTemplatePublish(fromUserId string, toUserIds []string, objectName, values, content, pushContent, pushData string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_MESSAGE_SYSTEM_PUBLISH_TEMPLATE + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("fromUserId", fromUserId)
	for _, toUserId := range toUserIds {
		req.Param("toUserId", toUserId)
	}
	req.Param("objectName", objectName)
	req.Param("values", values)
	req.Param("content", content)
	req.Param("pushContent", pushContent)
	req.Param("pushData", pushData)
	fillHeader(req, rcServer)
	return req.Bytes()
}

//发送群组消息 方法
//说明：以一个用户身份向群组发送消息
func (rcServer *RCServer) MessageGroupPublish(fromUserId string, toGroupIds []string, objectName, content, pushContent, pushData string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_MESSAGE_GROUP_PUBLISH + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("fromUserId", fromUserId)
	for _, toGroupId := range toGroupIds {
		req.Param("toGroupId", toGroupId)
	}
	req.Param("objectName", objectName)
	req.Param("content", content)
	req.Param("pushContent", pushContent)
	req.Param("pushData", pushData)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//发送讨论组消息 方法
//说明：以一个用户身份向讨论组发送消息，单条消息最大 128k，每秒钟最多发送 20 条消息。
func (rcServer *RCServer) MessageDiscussionPublish(fromUserId string, toDiscussionIds []string, objectName, content, pushContent, pushData string, isPersisted, isCounted int) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_MESSAGE_DISCUSSION_PUBLISH + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("fromUserId", fromUserId)
	for _, toDiscussionId := range toDiscussionIds {
		req.Param("toDiscussionId", toDiscussionId)
	}
	req.Param("objectName", objectName)
	req.Param("content", content)
	req.Param("pushContent", pushContent)
	req.Param("pushData", pushData)
	req.Param("isPersisted", strconv.Itoa(isPersisted))
	req.Param("isCounted", strconv.Itoa(isCounted))
	fillHeader(req, rcServer)
	return req.Bytes()
}

//发送聊天室消息 方法
//说明：一个用户向聊天室发送消息
func (rcServer *RCServer) MessageChatroomPublish(fromUserId string, toChatroomIds []string, objectName, content string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_MESSAGE_CHATROOM_PUBLISH + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("fromUserId", fromUserId)
	for _, toChatroomId := range toChatroomIds {
		req.Param("toChatroomId", toChatroomId)
	}
	req.Param("objectName", objectName)
	req.Param("content", content)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//发送广播消息 方法
//说明：某发送消息给一个应用下的所有注册用户。
//此服务尚未公开提供。如您需要，请提交工单给我们登记。
func (rcServer *RCServer) MessageBroadcast(fromUserId, objectName, content string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_MESSAGE_BROADCAST + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("fromUserId", fromUserId)
	req.Param("objectName", objectName)
	req.Param("content", content)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//敏感词服务
//设置敏感词后，App 中用户不会收到含有敏感词的消息内容，默认最多设置 50 个敏感词。
//
//添加敏感词 方法
func (rcServer *RCServer) WordfilterAdd(word string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_WORDFILTER_ADD + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("word", word)
	fillHeader(req, rcServer)
	return req.Bytes()
}

// 移除敏感词 方法
// 说明：从敏感词列表中，移除某一敏感词。
func (rcServer *RCServer) WordfilterDelete(word string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_WORDFILTER_DELETE + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("word", word)
	fillHeader(req, rcServer)
	return req.Bytes()
}

// 查询敏感词列表 方法
func (rcServer *RCServer) WordfilterQuery() ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_WORDFILTER_DELETE + rcServer.format
	req := httplib.Post(destinationUrl)
	fillHeader(req, rcServer)
	return req.Bytes()
}

//同步消息 方法
//同步消息时都需要你的服务提供应答，只要有应答，就表示消息已经同步，如果无应答或者应答超时（10秒），融云会再尝试推送2次，如果仍然失败，融云将不再推送此消息。
//说明：
//1、融云服务器可以将消息数据同步给开发者的应用服务器，开发者应用服务器接收所有在你的 App 下聊天的实时数据(目前支持二人会话数据、群聊数据)，接收数据前需要在开发者后台注册接收地址（目前通过工单提交）。
//2、为了验证数据有效性并确保调用者为融云 Server，我们会在每个请求前添加数据签名，详细签名方法参见“API 调用签名规则”,签名信息参数在接收地址的 URL 上提供。
//3、调用 Server API 接口发送的消息，不会通过消息路由服务。
func (rcServer *RCServer) MessageReceive(messageReceiveURL, fromUserId, toUserId, objectName, content, timestamp string) ([]byte, error) {
	destinationUrl := messageReceiveURL
	req := httplib.Post(destinationUrl)
	req.Param("fromUserId", fromUserId)
	req.Param("toUserId", toUserId)
	req.Param("objectName", objectName)
	req.Param("content", content)
	req.Param("timestamp", timestamp)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//消息历史记录下载地址获取 方法
//说明：获取 APP 内指定某天某小时内的所有会话消息记录的下载地址
func (rcServer *RCServer) MessageHistory(date string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_MESSAGE_HISTORY + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("date", date)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//消息历史记录删除 方法
//说明：删除 APP 内指定某天某小时内的所有会话消息记录
func (rcServer *RCServer) MessageHistoryDelete(date string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_MESSAGE_HISTORY_DELETE + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("date", date)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//同步用户所属群组 方法
//向融云服务器提交 userId 对应的用户当前所加入的所有群组。
func (rcServer *RCServer) GroupSync(userId string, groupIdAndNameArray []map[string]string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_GROUP_SYNC + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	for _, groupIdAndNameDic := range groupIdAndNameArray {
		for groupId, name := range groupIdAndNameDic {
			req.Param("groupId["+groupId+"]", name)
		}
	}
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//创建群组 方法
//创建群组，并将用户加入该群组，用户将可以收到该群的消息。注：其实本方法是加入群组方法 /group/join 的别名。
func (rcServer *RCServer) GroupCreat(userId, groupId, groupName string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_GROUP_CREATE + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	req.Param("groupId", groupId)
	req.Param("groupName", groupName)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//加入群组 方法
//将用户加入指定群组，用户将可以收到该群的消息。
func (rcServer *RCServer) GroupJoin(userId, groupId, groupName string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_GROUP_JOIN + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	req.Param("groupId", groupId)
	req.Param("groupName", groupName)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//退出群组 方法
//将用户从群中移除，不再接收该群组的消息。
func (rcServer *RCServer) GroupQuit(userId, groupId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_GROUP_QUIT + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	req.Param("groupId", groupId)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//解散群组 方法
//将该群解散，所有用户都无法再接收该群的消息。
func (rcServer *RCServer) GroupDismiss(userId, groupId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_GROUP_DISMISS + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	req.Param("groupId", groupId)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//刷新群组信息 方法
func (rcServer *RCServer) GroupRefresh(groupId, groupName string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_GROUP_REFRESH + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("groupId", groupId)
	req.Param("groupName", groupName)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

// 查询群成员 方法
func (rcServer *RCServer) GroupUserQuery(groupId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_GROUP_USER_QUERY + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("groupId", groupId)
	fillHeader(req, rcServer)
	return req.Bytes()
}

//群组成员禁言服务
//在 App 中如果不想让某一用户在群中发言时，可将此用户在群组中禁言，被禁言用户可以接收查看群组中用户聊天信息，但不能发送消息。
//
//添加禁言群成员 方法
func (rcServer *RCServer) GroupUserGagAdd(userId, groupId, minute string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_GROUP_USER_GAG_ADD + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	req.Param("groupId", groupId)
	req.Param("minute", minute)
	fillHeader(req, rcServer)
	return req.Bytes()
}

//移除禁言群成员 方法
func (rcServer *RCServer) GroupUserGagRollback(userId, groupId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_GROUP_USER_GAG_ROLLBACK + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	req.Param("groupId", groupId)
	fillHeader(req, rcServer)
	return req.Bytes()
}

//查询被禁言群成员 方法
func (rcServer *RCServer) GroupUserGagQuery(groupId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_GROUP_USER_GAG_QUERY + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("groupId", groupId)
	fillHeader(req, rcServer)
	return req.Bytes()
}

//创建聊天室 方法
func (rcServer *RCServer) ChatroomCreat(chatroomId, chatroomName string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_CHATROOM_CREATE + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("chatroom["+chatroomId+"]", chatroomName)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//加入聊天室 方法
func (rcServer *RCServer) ChatroomJoin(userId, chatroomId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_CHATROOM_JOIN + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	req.Param("chatroomId", chatroomId)
	fillHeader(req, rcServer)
	return req.Bytes()
}

//销毁聊天室 方法
func (rcServer *RCServer) ChatroomDestroy(chatroomId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_CHATROOM_DESTROY + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("chatroomId", chatroomId)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

//查询聊天室信息 方法
func (rcServer *RCServer) ChatroomQuery(chatroomId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_CHATROOM_QUERY + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("chatroomId", chatroomId)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
}

// 查询聊天室内用户 方法
func (rcServer *RCServer) ChatroomUserQuery(chatroomId, count, order string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_CHATROOM_USER_QUERY + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("chatroomId", chatroomId)
	req.Param("count", count)
	req.Param("order", order)
	fillHeader(req, rcServer)
	return req.Bytes()
}

//聊天室成员禁言服务
//在 App 中如果不想让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息。

//添加禁言聊天室成员 方法
func (rcServer *RCServer) ChatroomUserGagAdd(userId, chatroomId, minute string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_CHATROOM_USER_GAG_ADD + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	req.Param("chatroomId", chatroomId)
	req.Param("minute", minute)
	fillHeader(req, rcServer)
	return req.Bytes()
}

// 移除禁言聊天室成员 方法
func (rcServer *RCServer) ChatroomUserGagRollback(userId, chatroomId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_CHATROOM_USER_GAG_ROLLBACK + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	req.Param("chatroomId", chatroomId)
	fillHeader(req, rcServer)
	return req.Bytes()
}

//查询被禁言聊天室成员 方法
func (rcServer *RCServer) ChatroomUserGagQuery(chatroomId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_CHATROOM_USER_GAG_QUERY + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("chatroomId", chatroomId)
	fillHeader(req, rcServer)
	return req.Bytes()
}

//聊天室封禁服务
//在 App 中如果想将某一用户踢出聊天室并在一段时间内不允许再进入聊天室时，可实现将用户对指定的聊天室做封禁处理，被封禁用户将被踢出聊天室，并在设定的时间内不能再进入聊天室中。
//
//添加封禁聊天室成员 方法

func (rcServer *RCServer) ChatroomUserBlockAdd(userId, chatroomId, minute string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_CHATROOM_USER_BLOCK_ADD + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	req.Param("chatroomId", chatroomId)
	req.Param("minute", minute)
	fillHeader(req, rcServer)
	return req.Bytes()
}

//移除封禁聊天室成员 方法
func (rcServer *RCServer) ChatroomUserBlockRollback(userId, chatroomId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_CHATROOM_USER_BLOCK_ROLLBACK + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("userId", userId)
	req.Param("chatroomId", chatroomId)
	fillHeader(req, rcServer)
	return req.Bytes()
}

//查询被封禁聊天室成员 方法
func (rcServer *RCServer) ChatroomUserBlockQuery(chatroomId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_CHATROOM_USER_BLOCK_QUERY + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("chatroomId", chatroomId)
	fillHeader(req, rcServer)
	return req.Bytes()
}

//聊天室消息分发服务
//可实现控制对聊天室中消息是否进行分发，停止分发后聊天室中用户发送的消息，融云服务端不会再将消息发送给聊天室中其他用户。
//
//聊天室消息停止分发 方法
func (rcServer *RCServer) ChatroomMessageStopDis(chatroomId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_CHATROOM_MESSAGE_STOPDISTRIBUTION + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("chatroomId", chatroomId)
	fillHeader(req, rcServer)
	return req.Bytes()
}

//聊天室消息恢复分发 方法
func (rcServer *RCServer) ChatroomMessageResumeDis(chatroomId string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_CHATROOM_MESSAGE_RESUMEDISTRIBUTION + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("chatroomId", chatroomId)
	fillHeader(req, rcServer)
	return req.Bytes()
}

//短信服务
// 获取图片验证码 方法
func (rcServer *RCServer) SmsGetImgCode(appKey string) ([]byte, error) {
	destinationUrl := RC_SMS_API_URL + RC_SMS_IMAGECODE_GET + rcServer.format + "?appKey=" + appKey
	req := httplib.Get(destinationUrl)
	return req.Bytes()
}

// 发送短信验证码 方法
func (rcServer *RCServer) SmsSendCode(mobile, verifyId, verifyCode, templateId, region string) ([]byte, error) {
	destinationUrl := RC_SMS_API_URL + RC_SMS_SENDCODE + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("mobile", mobile)
	req.Param("verifyId", verifyId)
	req.Param("verifyCode", verifyCode)
	req.Param("templateId", templateId)
	req.Param("region", region)
	fillHeader(req, rcServer)
	return req.Bytes()
}

// 验证码验证 方法
func (rcServer *RCServer) SmsVerifyCode(sessionId, code string) ([]byte, error) {
	destinationUrl := RC_SMS_API_URL + RC_SMS_VERIFYCODE + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("sessionId", sessionId)
	req.Param("code", code)
	fillHeader(req, rcServer)
	return req.Bytes()
}
