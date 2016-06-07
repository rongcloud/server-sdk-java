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
	RC_SERVER_API_URL    = "https://api.cn.ronghub.com"
	RC_USER_GET_TOKEN    = "/user/getToken"
	RC_USER_REFRESH      = "/user/refresh"
	RC_USER_CHECK_ONLINE = "/user/checkOnline"
	RC_USER_BLOCK        = "/user/block"
	RC_USER_UNBLOCK      = "/user/unblock"
	RC_USER_BLOCK_QUERY  = "/user/block/query"
	RC_USER_BLACK_ADD    = "/user/blacklist/add"
	RC_USER_BLACK_REMOVE = "/user/blacklist/remove"
	RC_USER_BLACK_QUERY  = "/user/blacklist/query"

	RC_MESSAGE_PRIVATE_PUBLISH  = "/message/private/publish"
	RC_MESSAGE_SYSTEM_PUBLISH   = "/message/system/publish"
	RC_MESSAGE_GROUP_PUBLISH    = "/message/group/publish"
	RC_MESSAGE_CHATROOM_PUBLISH = "/message/chatroom/publish"
	RC_MESSAGE_BROADCAST        = "/message/broadcast"
	RC_MESSAGE_HISTORY          = "/message/history"
	RC_MESSAGE_HISTORY_DELETE   = "/message/history/delete"

	RC_GROUP_CREATE  = "/group/create"
	RC_GROUP_JOIN    = "/group/join"
	RC_GROUP_QUIT    = "/group/quit"
	RC_GROUP_DISMISS = "/group/dismiss"
	RC_GROUP_REFRESH = "/group/refresh"
	RC_GROUP_SYNC    = "/group/sync"

	RC_CHATROOM_CREATE  = "/chatroom/create"
	RC_CHATROOM_DESTROY = "/chatroom/destroy"
	RC_CHATROOM_QUERY   = "/chatroom/query"
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
func fillHeader(req *httplib.BeegoHttpRequest, rcServer *RCServer) {
	nonce, timestamp, signature := getSignature(rcServer)
	req.Header("App-Key", rcServer.appKey)
	req.Header("Nonce", nonce)
	req.Header("Timestamp", timestamp)
	req.Header("Signature", signature)
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

//创建聊天室 方法
func (rcServer *RCServer) ChatroomCreat(chatroomId, chatroomName string) ([]byte, error) {
	destinationUrl := rcServer.apiUrl + RC_CHATROOM_CREATE + rcServer.format
	req := httplib.Post(destinationUrl)
	req.Param("chatroom["+chatroomId+"]", chatroomName)
	fillHeader(req, rcServer)
	byteData, err := req.Bytes()
	return byteData, err
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
