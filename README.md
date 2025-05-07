# Server SDK in Go

RongCloud Server SDK in Go.

## Version notes

- To simplify integration and improve maintenance, Server SDK v3 standardizes naming and methods with a clearer structure. Older versions (v1 and v2.0.1) are still available but won’t receive new features.
- New developers should use Server SDK v3. If you’re using an older version, retest before switching—–direct upgrades aren’t supported.

## API documentation
- [Official docs](https://docs.rongcloud.io/platform-chat-api)

## How to use

### 1. Using older versions with Go Module

- Go Module file: `require github.com/rongcloud/server-sdk-go`
- Import: `import "github.com/rongcloud/server-sdk-go/sdk"`

### 2. Using v3 with Go Module

- v3 version: `require github.com/rongcloud/server-sdk-go/v3`
- Import: `import "github.com/rongcloud/server-sdk-go/v3/sdk"`

### 3. Using without Go Module

- Download/update to GOPATH: `go get -u github.com/rongcloud/server-sdk-go`
- Import: `import "github.com/rongcloud/server-sdk-go/sdk"`

## Method invocation

* See `rongcloud_test.go` for API call examples.

```go
package main

import "fmt"

// Older version or non-Go Module usage
import "github.com/rongcloud/server-sdk-go/sdk"

// Go Module v3 usage
//import "github.com/rongcloud/server-sdk-go/v3/sdk"

func main() {
	//Initialization must specify a data center
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

### HTTP parameter optimization

- Optimize HTTP connections for better performance.
- `sdk.WithMaxIdleConnsPerHost`: Max active connections per host, default 100.
- `sdk.WithTimeout`: Connection timeout, default 10 seconds; minimum unit is seconds, e.g., `sdk.WithTimeout(30)` sets it to 30 seconds.
- `sdk.WithKeepAlive`: Connection keepalive time, default 30 seconds; minimum unit is seconds, e.g., `sdk.WithKeepAlive(30)` sets it to 30 seconds.
- `rc.SetHttpTransport`: Manually set the HTTP client.
- `rc.GetHttpTransport`: Get the current global HTTP client.

```go
package main

import "fmt"
import "time"
import "net"
import "net/http"
import "github.com/rongcloud/server-sdk-go/sdk"

func main() {
	// Method 1: Set during object creation
	rc := sdk.NewRongCloud("appKey",
		"appSecret",
		//Initialization must specify a data center
		REGION_BJ,
		// Max active connections per host
		sdk.WithMaxIdleConnsPerHost(100),
		)
	
	// Method 2: Custom HTTP client, set via the set method
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

### GO SDK feature support version list

| Module                                                                                       | Method name                           | Description                                               | master |
|:-----------------------------------------------------------------------------------------|:------------------------------|:-------------------------------------------------|:-------| 
| [User information](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/user_test.go)          | UserRegister                  | Register, get token                                     | √      |
|                                                                                          | UserUpdate                    | Update user info                                           | √      |
|                                                                                          | OnlineStatusCheck             | Check user online status                                         | √      |
|                                                                                          | BlacklistAdd                  | Add to blocklist                                            | √      |
|                                                                                          | BlacklistGet                  | Get blocklist                                          | √      |
|                                                                                          | BlacklistRemove               | Remove from blocklist                                            | √      |
|                                                                                          | BlockAdd                      | Ban user                                           | √      |
|                                                                                          | BlockGetList                  | Get banned user list                                         | √      |
|                                                                                          | BlockRemove                   | Unban user                                           | √      |
|                                                                                          | TagSet                        | Add user tag                                           | √      |
|                                                                                          | TagBatchSet                   | Batch add user tags                                         | √      |
|                                                                                          | TagGet                        | Get user tags                                           | √      |
|                                                                                          | GroupMuteAdd                  | Add global group mute, user can’t send messages in any group                |        |
|                                                                                          | GroupMuteRemove               | Remove global group mute                                       |        |
|                                                                                          | GroupMuteGetList              | Get global group mute list                                     |        |
|                                                                                          | ChatRoomMuteAdd               | Add global chatroom mute, user can’t send messages in any chatroom              |        |
|                                                                                          | ChatRoomMuteRemove            | Remove global chatroom mute                                      |        |
|                                                                                          | ChatRoomMuteGetList           | Get global chatroom mute list                                    |        |
|                                                                                          | UserDeactivate                | Deactivate user                                             |    √    |
|                                                                                          | UserDeactivateQuery           | Query deactivated users                                                 |    √    |
|                                                                                          | UserReactivate                | Reactivate deactivated users                                                 |    √    |
| [Sensitive words](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/sensitive_test.go)      | SensitiveAdd                  | Add sensitive word, takes effect in 2 hours                               | √      |
|                                                                                          | SensitiveGetList              | Get sensitive word list                                          | √      |
|                                                                                          | SensitiveRemove               | Remove sensitive word, supports batch removal, takes effect in 2 hours                      | √      |
| [Message sending](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/message_test.go)       | PrivateSend                   | Send private message                                           | √      |
|                                                                                          | PrivateSendTemplate           | Send private template message                                         | √      |
|                                                                                          | PrivateRecall                 | Recall private message                                           | √      |
|                                                                                          | ChatRoomSend                  | Send chatroom message                                          | √      |
|                                                                                          | ChatRoomBroadcast             | Send chatroom broadcast message                                        | √      |
|                                                                                          | GroupSend                     | Send group message                                           | √      |
|                                                                                          | GroupSendMention              | Send group @ message                                        | √      |
|                                                                                          | GroupRecall                   | Recall group message                                           | √      |
|                                                                                          | SystemSend                    | Send system message                                           | √      |
|                                                                                          | SystemSendTemplate            | Send system template message                                         | √      |
|                                                                                          | SystemBroadcast               | Send broadcast message, max 2 per hour, 3 per day.               | √      |
| [Message history](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/message_test.go)     | HistoryGet                    | Get message history download URL                                     | √      |
|                                                                                          | HistoryRemove                 | Delete message history                                       | √      |
| [Broadcast push](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/push_test.go)          | PushSend                      | Send push, max 2 per hour, 3 per day.       | √      |
| [Group](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/group_test.go)           | GroupCreate                   | Create group                                             | √      |
|                                                                                          | GroupSync                     | Sync group relationships                                            | √      |
|                                                                                          | GroupUpdate                   | Update group info                                            | √      |
|                                                                                          | GroupGet                      | Get group info                                            | √      |
|                                                                                          | GroupJoin                     | Invite users to group                                          | √      |
|                                                                                          | GroupQuit                     | Quit group                                             | √      |
|                                                                                          | GroupDismiss                  | Dismiss group                                             | √      |
|                                                                                          | GroupMuteMembersAdd           | Mute users in group                       | √      |
|                                                                                          | GroupMuteMembersRemove        | Unmute users in group                                       | √      |
|                                                                                          | GroupMuteMembersGetList       | Get muted users in group                                     | √      |
|                                                                                          | GroupMuteAllMembersAdd        | Mute all group members              | √      |
|                                                                                          | GroupMuteAllMembersRemove     | Unmute all group members                                     | √      |
|                                                                                          | GroupMuteAllMembersGetList    | Get group mute list                                         | √      |
|                                                                                          | GroupMuteWhiteListUserAdd     | Add users to group mute allowlist            | √      |
|                                                                                          | GroupMuteWhiteListUserRemove  | Remove users from group mute allowlist                                      | √      |
|                                                                                          | GroupMuteWhiteListUserGetList | Get group mute allowlist                                    | √      |
| [Conversation do not disturb](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/conversation_test.go) | ConversationMute              | Mute conversation                                          | √      |
|                                                                                          | ConversationUnmute            | Unmute conversation                                          | √      |
|                                                                                          | ConversationGet               | Get conversation mute status                                        | √      |
| [Chatroom](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/chatroom_test.go)       | ChatRoomCreate                | Create chatroom                                            | √      |
|                                                                                          | ChatRoomDestroy               | Destroy chatroom                                            | √      |
|                                                                                          | ChatRoomGet                   | Get chatroom info                                          | √      |
|                                                                                          | ChatRoomIsExist               | Check if user is in chatroom                                       | √      |
|                                                                                          | ChatRoomBlockAdd              | Ban user from chatroom        | √      |
|                                                                                          | ChatRoomBlockGetList          | Get banned users in chatroom                                      | √      |
|                                                                                          | ChatRoomBlockRemove           | Unban user from chatroom                                        | √      |
|                                                                                          | ChatRoomMuteMembersAdd        | Mute users in chatroom                         | √      |
|                                                                                          | ChatRoomMuteMembersGetList    | Get muted users in chatroom                                      | √      |
|                                                                                          | ChatRoomMuteMembersRemove     | Unmute users in chatroom                                        | √      |
|                                                                                          | ChatRoomDemotionAdd           | Add low-priority messages in chatroom       | √      |
|                                                                                          | ChatRoomDemotionGetList       | Get low-priority messages in chatroom                                    | √      |
|                                                                                          | ChatRoomDemotionRemove        | Remove low-priority messages in chatroom                                      | √      |
|                                                                                          | ChatRoomDistributionStop      | Stop chatroom message distribution                      | √      |
|                                                                                          | ChatRoomDistributionResume    | Resume chatroom message distribution                                        | √      |
|                                                                                          | ChatRoomKeepAliveAdd          | Keep chatroom alive                           | √      |
|                                                                                          | ChatRoomKeepAliveRemove       | Remove chatroom keepalive                                          | √      |
|                                                                                          | ChatRoomKeepAliveGetList      | Get keepalive chatrooms                                        | √      |
|                                                                                          | ChatRoomWhitelistAdd          | Add whitelist message types in chatroom | √      |
|                                                                                          | ChatRoomWhitelistRemove       | Remove whitelist message types                                        | √      |
|                                                                                          | ChatRoomWhitelistGetList      | Get whitelist message types                                      | √      |
|                                                                                          | ChatRoomUserWhitelistAdd      | Add whitelist users in chatroom | √      |
|                                                                                          | ChatRoomUserWhitelistRemove   | Remove whitelist users                                          | √      |
|                                                                                          | ChatRoomUserWhitelistGetList  | Get whitelist users                                        | √      | | ChatRoomBroadcast             | Broadcast a message to chat room                                        | √      |
|                                                                                          | GroupSend                     | Send a group message                                           | √      |
|                                                                                          | GroupSendMention              | Send a group @ message                                        | √      |
|                                                                                          | GroupRecall                   | Recall a group message                                           | √      |
|                                                                                          | SystemSend                    | Send a system message                                           | √      |
|                                                                                          | SystemSendTemplate            | Send a system template message                                         | √      |
|                                                                                          | SystemBroadcast               | Broadcast a message, limited to 2 times per hour and 3 times per day per app.               | √      |
| [Message history](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/message_test.go)     | HistoryGet                    | Get message history download URL                                     | √      |
|                                                                                          | HistoryRemove                 | Delete message history                                       | √      |
|                                                                                          | GetPrivateHistoryMessage                 | Get one-to-one historical messages                    | √      |
|                                                                                          | GetGroupHistoryMessage                 | Get group historical messages                                 | √      |
|                                                                                          | GetUltraGroupHistoryMessage                 | Get ultra group historical messages                                     | √      |
|                                                                                          | GetChatroomHistoryMessage                 | Get chatroom historical messages                              | √      |
| [Broadcast push](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/push_test.go)          | PushSend                      | Send a push, combined with broadcast messages, limited to 2 times per hour and 3 times per day per app.       | √      |
| [Group](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/group_test.go)           | GroupCreate                   | Create a group                                             | √      |
|                                                                                          | GroupSync                     | Sync group relationships                                            | √      |
|                                                                                          | GroupUpdate                   | Update group info                                            | √      |
|                                                                                          | GroupGet                      | Get group info                                            | √      |
|                                                                                          | GroupJoin                     | Invite users to join a group                                          | √      |
|                                                                                          | GroupQuit                     | Quit a group                                             | √      |
|                                                                                          | GroupDismiss                  | Dismiss a group                                             | √      |
|                                                                                          | GroupMuteMembersAdd           | Mute users in a group, preventing them from sending messages                       | √      |
|                                                                                          | GroupMuteMembersRemove        | Unmute users in a group                                       | √      |
|                                                                                          | GroupMuteMembersGetList       | Get list of muted users in a group                                     | √      |
|                                                                                          | GroupMuteAllMembersAdd        | Mute all members in a group, preventing them from sending messages              | √      |
|                                                                                          | GroupMuteAllMembersRemove     | Unmute all members in a group                                     | √      |
|                                                                                          | GroupMuteAllMembersGetList    | Get list of muted groups                                         | √      |
|                                                                                          | GroupMuteWhiteListUserAdd     | Add users to group mute whitelist, allowing them to send messages even if the group is muted            | √      |
|                                                                                          | GroupMuteWhiteListUserRemove  | Remove users from group mute whitelist                                      | √      |
|                                                                                          | GroupMuteWhiteListUserGetList | Get list of users in group mute whitelist                                    | √      |
| [Conversation mute](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/conversation_test.go) | ConversationMute              | Mute a conversation                                          | √      |
|                                                                                          | ConversationUnmute            | Unmute a conversation                                          | √      |
|                                                                                          | ConversationGet               | Get conversation mute status                                        | √      |
| [Chat room](https://github.com/rongcloud/server-sdk-go/blob/master/sdk/chatroom_test.go)       | ChatRoomCreate                | Create a chat room                                            | √      |
|                                                                                          | ChatRoomDestroy               | Destroy a chat room                                            | √      |
|                                                                                          | ChatRoomGet                   | Query chat room info                                          | √      |
|                                                                                          | ChatRoomIsExist               | Check if a user is in a chat room                                       | √      |
|                                                                                          | ChatRoomBlockAdd              | Block a user from a chat room, preventing them from joining or kicking them if already in the chat room        | √      |
|                                                                                          | ChatRoomBlockGetList          | Get list of blocked users in a chat room                                      | √      |
|                                                                                          | ChatRoomBlockRemove           | Unblock a user from a chat room                                        | √      |
|                                                                                          | ChatRoomMuteMembersAdd        | Mute a user in a chat room, preventing them from sending messages                         | √      |
|                                                                                          | ChatRoomMuteMembersGetList    | Get list of muted users in a chat room                                      | √      |
|                                                                                          | ChatRoomMuteMembersRemove     | Unmute a user in a chat room                                        | √      |
|                                                                                          | ChatRoomDemotionAdd           | Add low-priority messages to a chat room, which may be discarded when the server is under heavy load       | √      |
|                                                                                          | ChatRoomDemotionGetList       | Get list of low-priority messages in a chat room                                    | √      |
|                                                                                          | ChatRoomDemotionRemove        | Remove low-priority messages from a chat room                                      | √      |
|                                                                                          | ChatRoomDistributionStop      | Stop chat room message distribution, preventing the server from sending messages after receiving them                      | √      | | ChatRoomDistributionResume    | Resume chatroom message distribution                                        | √      |
|                                                                                          | ChatRoomKeepAliveAdd          | Add keepalive chatroom, keepalive chatrooms won’t be automatically destroyed                           | √      |
|                                                                                          | ChatRoomKeepAliveRemove       | Remove keepalive chatroom                                          | √      |
|                                                                                          | ChatRoomKeepAliveGetList      | Get keepalive chatroom list                                        | √      |
|                                                                                          | ChatRoomWhitelistAdd          | Add allowlist message type, allowlist message types won’t be discarded when server pressure is high due to a surge in message volume, ensuring message delivery | √      |
|                                                                                          | ChatRoomWhitelistRemove       | Remove allowlist message type                                        | √      |
|                                                                                          | ChatRoomWhitelistGetList      | Get allowlist message type list                                      | √      |
|                                                                                          | ChatRoomUserWhitelistAdd      | Add allowlist user, messages sent by allowlist users won’t be discarded when server pressure is high due to a surge in message volume, ensuring message delivery | √      |
|                                                                                          | ChatRoomUserWhitelistRemove   | Remove allowlist user                                          | √      |
|                                                                                          | ChatRoomUserWhitelistGetList  | Get allowlist user list                                        | √      |
