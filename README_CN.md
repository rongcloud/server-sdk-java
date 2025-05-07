server-sdk-go
=============

Rong Cloud Server SDK in Go.

# 版本说明

- 为方便开发者的接入使用，更好的对融云 Server SDK 进行维护管理，融云 Server SDK v3 统一规范了命名及调用方式，结构更加清晰。老版本的 Server SDK 已经切入 v1 v2.0.1 分支，仍然可以使用，但不会再做新的功能更新。
- 如果您是新接入的开发者，建议您使用 Server SDK v3 版本。 对已集成使用老版本 Server SDK 的开发者，不能直接升级使用，强烈建议您重新测试后使用

# API文档
- [官方文档](https://doc.rongcloud.cn/imserver/server/v1/overview)

## 如何使用

### 1. go mod 使用旧版本

- go mod 文件： `require github.com/rongcloud/server-sdk-go`
- go 文件引入 ： `import "github.com/rongcloud/server-sdk-go/sdk"`

### 2. go mode 方式使用 v3

- v3 版本 : `require github.com/rongcloud/server-sdk-go/v3`
- go 文件引入： `import "github.com/rongcloud/server-sdk-go/v3/sdk"`

### 3. 非 go mod 直接使用

- 直接下载/更新包到 GOPATH：`go get -u github.com/rongcloud/server-sdk-go`
- go 文件引入： `import "github.com/rongcloud/server-sdk-go/sdk"`

## 方法调用

* 请参考 rongcloud_test.go 上面提供了所有的 API 接口的调用用例。

```go
package main

import "fmt"

//旧版本引入 或者 非go mod方式使用
import "github.com/rongcloud/server-sdk-go/sdk"

// go mod v3 版本引入
//import "github.com/rongcloud/server-sdk-go/v3/sdk"

func main() {
	//初始化必须指定数据中心
	rc := sdk.NewRongCloud("appKey", "appSecret", REGION_BJ)
	msg := sdk.TXTMsg{
		Content: "hello",
		Extra:   "helloExtra",
	}

	err := rc.PrivateSend(
		"userId",
		[]string{"toUserId"},
		"RC:TxtMsg",
		&msg,
		"",
		"",
		1,
		0,
		1,
		0,
		0,
	)
	
	fmt.Println(err)
}
```

### http 参数优化

- http连接相关的性能优化
- `sdk.WithMaxIdleConnsPerHost` : 每个域名最大活跃连接数，默认 100
- `sdk.WithTimeout` : 连接超时设置，默认 10 秒；最小单位为秒， `sdk.WithTimeout(30)` 表示设置为30秒
- `sdk.WithKeepAlive` : 连接保活时间，默认 30 秒；最小单位为秒， `sdk.WithKeepAlive(30)` 表示设置保活时间为30秒
- `rc.SetHttpTransport` : 手动设置 http client
- `rc.GetHttpTransport` : 获得当前全局 http client

```go
package main

import "fmt"
import "time"
import "net"
import "net/http"
import "github.com/rongcloud/server-sdk-go/sdk"

func main() {
	// 方法1： 创建对象时设置
	rc := sdk.NewRongCloud("appKey",
		"appSecret",
		//初始化必须指定数据中心
		REGION_BJ,
		// 每个域名最大活跃连接数
		sdk.WithMaxIdleConnsPerHost(100),
		)
	
	// 方法2： 自定义 http client， 调用 set 方法设置
	dialer := &net.Dialer{
        Timeout:   10 * time.Second,
        KeepAlive: 30 * time.Second,
    }
    globalTransport := &http.Transport{
        DialContext:         dialer.DialContext,
        MaxIdleConnsPerHost: 100,
    }
    rc.SetHttpTransport(globalTransport)
	
}
```

### GO SDK 功能支持的版本清单

| 模块                                                                                       | 方法名                           | 说明                                               | master |
|:-----------------------------------------------------------------------------------------|:------------------------------|:-------------------------------------------------|:-------| 
| [用户信息](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/user_test.go)          | UserRegister                  | 注册， 获取 token                                     | √      |
|                                                                                          | UserUpdate                    | 更新用户信息                                           | √      |
|                                                                                          | OnlineStatusCheck             | 检查用户在线状态                                         | √      |
|                                                                                          | BlacklistAdd                  | 添加黑名单                                            | √      |
|                                                                                          | BlacklistGet                  | 获取黑名单列表                                          | √      |
|                                                                                          | BlacklistRemove               | 移除黑名单                                            | √      |
|                                                                                          | BlockAdd                      | 添加用户封禁                                           | √      |
|                                                                                          | BlockGetList                  | 获取用户封禁列表                                         | √      |
|                                                                                          | BlockRemove                   | 移除用户封禁                                           | √      |
|                                                                                          | TagSet                        | 添加用户标签                                           | √      |
|                                                                                          | TagBatchSet                   | 批量添加用户标签                                         | √      |
|                                                                                          | TagGet                        | 获取用户标签                                           | √      |
|                                                                                          | GroupMuteAdd                  | 添加全局群组禁言用户，添加后用户在应用下的所有群组中都不能发送消息                |        |
|                                                                                          | GroupMuteRemove               | 移除全局群组禁言用户                                       |        |
|                                                                                          | GroupMuteGetList              | 获取全局群组禁言用户列表                                     |        |
|                                                                                          | ChatRoomMuteAdd               | 添加全局聊天室禁言用户，添加后用户在应用下的所有聊天室中都不能发送消息              |        |
|                                                                                          | ChatRoomMuteRemove            | 移除全局聊天室禁言用户                                      |        |
|                                                                                          | ChatRoomMuteGetList           | 获取全局聊天室禁言用户列表                                    |        |
|                                                                                          | UserDeactivate                | 注销用户                                             |    √    |
|                                                                                          | UserDeactivateQuery           | 查询已注销用户                                                 |    √    |
|                                                                                          | UserReactivate                | 重新激活注销用户                                                 |    √    |
| [敏感词](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/sensitive_test.go)      | SensitiveAdd                  | 添加敏感词，添加后默认 2 小时生效                               | √      |
|                                                                                          | SensitiveGetList              | 获取敏感词列表                                          | √      |
|                                                                                          | SensitiveRemove               | 移除敏感词，支持批量移除功能，移除后默认 2 小时生效                      | √      |
| [消息发送](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/message_test.go)       | PrivateSend                   | 发送单聊消息                                           | √      |
|                                                                                          | PrivateSendTemplate           | 发送单聊模板消息                                         | √      |
|                                                                                          | PrivateRecall                 | 消息单聊撤回                                           | √      |
|                                                                                          | ChatRoomSend                  | 发送聊天室消息                                          | √      |
|                                                                                          | ChatRoomBroadcast             | 发送聊天室广播消息                                        | √      |
|                                                                                          | GroupSend                     | 发送群组消息                                           | √      |
|                                                                                          | GroupSendMention              | 发送群组 @ 消息                                        | √      |
|                                                                                          | GroupRecall                   | 撤回群组消息                                           | √      |
|                                                                                          | SystemSend                    | 发送系统消息                                           | √      |
|                                                                                          | SystemSendTemplate            | 发送系统模板消息                                         | √      |
|                                                                                          | SystemBroadcast               | 发送广播消息，单个应用每小时只能发送 2 次，每天最多发送 3 次。               | √      |
| [消息历史记录](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/message_test.go)     | HistoryGet                    | 消息历史记录下载地址获取                                     | √      |
|                                                                                          | HistoryRemove                 | 消息历史记录删除方法                                       | √      |
|                                                                                          | GetPrivateHistoryMessage                 | 获取单聊历史消息                                      | √      |
|                                                                                          | GetGroupHistoryMessage                 | 获取群聊历史消息                                      | √      |
|                                                                                          | GetUltraGroupHistoryMessage                 | 获取超级群历史消息                                      | √      |
|                                                                                          | GetChatroomHistoryMessage                 | 获取聊天室历史消息                                      | √      |
| [广播推送](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/push_test.go)          | PushSend                      | 发送推送，推送和广播消息合计，单个应用每小时只能发送 2 次，每天最多发送 3 次。       | √      |
| [群组](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/group_test.go)           | GroupCreate                   | 创建群组                                             | √      |
|                                                                                          | GroupSync                     | 同步群关系                                            | √      |
|                                                                                          | GroupUpdate                   | 更新群信息                                            | √      |
|                                                                                          | GroupGet                      | 获取群信息                                            | √      |
|                                                                                          | GroupJoin                     | 邀请人加入群组                                          | √      |
|                                                                                          | GroupQuit                     | 退出群组                                             | √      |
|                                                                                          | GroupDismiss                  | 解散群组                                             | √      |
|                                                                                          | GroupMuteMembersAdd           | 添加指定群组禁言用户，该用户在指定群组中不能发送消息                       | √      |
|                                                                                          | GroupMuteMembersRemove        | 移除指定群组禁言用户                                       | √      |
|                                                                                          | GroupMuteMembersGetList       | 获取指定群组禁言用户列表                                     | √      |
|                                                                                          | GroupMuteAllMembersAdd        | 添加指定群组全部成员禁言，添加后该群组中所有用户不能在此群组中发送消息              | √      |
|                                                                                          | GroupMuteAllMembersRemove     | 移除指定群组全部成员禁言                                     | √      |
|                                                                                          | GroupMuteAllMembersGetList    | 获取群组禁言列表                                         | √      |
|                                                                                          | GroupMuteWhiteListUserAdd     | 添加群组禁言白名单用户，群组被禁言后，该群白名单中用户可以在群组中发送消息            | √      |
|                                                                                          | GroupMuteWhiteListUserRemove  | 移除群组禁言白名单用户                                      | √      |
|                                                                                          | GroupMuteWhiteListUserGetList | 获取群组禁言白名单用户列表                                    | √      |
| [会话免打扰](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/conversation_test.go) | ConversationMute              | 添加免打扰会话                                          | √      |
|                                                                                          | ConversationUnmute            | 移除免打扰会话                                          | √      |
|                                                                                          | ConversationGet               | 免打扰会话状态获取                                        | √      |
| [聊天室](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/chatroom_test.go)       | ChatRoomCreate                | 创建聊天室                                            | √      |
|                                                                                          | ChatRoomDestroy               | 销毁聊天室                                            | √      |
|                                                                                          | ChatRoomGet                   | 查询聊天室信息                                          | √      |
|                                                                                          | ChatRoomIsExist               | 检查用户是否在聊天室                                       | √      |
|                                                                                          | ChatRoomBlockAdd              | 添加聊天室封禁用户，被封禁后用户无法加入该聊天室，如用户正在聊天室中将被踢出聊天室        | √      |
|                                                                                          | ChatRoomBlockGetList          | 获取聊天室封禁用户列表                                      | √      |
|                                                                                          | ChatRoomBlockRemove           | 移除聊天室封禁用户                                        | √      |
|                                                                                          | ChatRoomMuteMembersAdd        | 添加聊天室禁言用户，用户无法在该聊天室中发送消息                         | √      |
|                                                                                          | ChatRoomMuteMembersGetList    | 获取聊天室禁言用户列表                                      | √      |
|                                                                                          | ChatRoomMuteMembersRemove     | 移除聊天室禁言用户                                        | √      |
|                                                                                          | ChatRoomDemotionAdd           | 添加聊天室低优先级消息，添加后因消息量激增导致服务器压力较大时，默认丢弃低级别的消息       | √      |
|                                                                                          | ChatRoomDemotionGetList       | 查询聊天室低优先级消息列表                                    | √      |
|                                                                                          | ChatRoomDemotionRemove        | 移除聊天室低优先级消息                                      | √      |
|                                                                                          | ChatRoomDistributionStop      | 停止聊天室消息分发，服务端收到上行消息后不进行下行发送                      | √      |
|                                                                                          | ChatRoomDistributionResume    | 恢复聊天室消息分发                                        | √      |
|                                                                                          | ChatRoomKeepAliveAdd          | 添加保活聊天室，保活中的聊天室不会被自动销毁                           | √      |
|                                                                                          | ChatRoomKeepAliveRemove       | 移除保活聊天室                                          | √      |
|                                                                                          | ChatRoomKeepAliveGetList      | 获取保活聊天室列表                                        | √      |
|                                                                                          | ChatRoomWhitelistAdd          | 添加白名单消息类型，白名单中的消息类型，在消息量激增导致服务器压力较大时不会被丢弃，确保消息到达 | √      |
|                                                                                          | ChatRoomWhitelistRemove       | 移除白名单消息类型                                        | √      |
|                                                                                          | ChatRoomWhitelistGetList      | 获取白名单消息类型列表                                      | √      |
|                                                                                          | ChatRoomUserWhitelistAdd      | 添加白名单用户，白名单中用户发送的消息，在消息量激增导致服务器压力较大时不会被丢弃，确保消息到达 | √      |
|                                                                                          | ChatRoomUserWhitelistRemove   | 移除白名单用户                                          | √      |
|                                                                                          | ChatRoomUserWhitelistGetList  | 获取白名单用户列表                                        | √      |
