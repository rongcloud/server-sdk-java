Server SDK 功能支持的版本清单

| 模块   | 方法名    | 说明           | 支持版本（Tags）   |
| :-----| :-----  | :------ | :----- |
| [用户信息](./src/main/java/io/rong/methods/user/User.java) | register | 注册， 获取 token| 3.0.1 |
|  | update | 更新用户信息 | 3.0.1 |
|  | get | 查询用户信息 | 3.0.8 |
| [检查用户在线状态](./src/main/java/io/rong/methods/user/onlinestatus/OnlineStatus.java) | onlinestatus.check | 检查用户在线状态| 3.0.1 |
| [黑名单](./src/main/java/io/rong/methods/user/blacklist/Blacklist.java) | blacklist.add | 添加黑名单 | 3.0.1 |
|  | blacklist.getList | 获取黑名单列表| 3.0.1 |
|  | blacklist.remove | 移除黑名单 | 3.0.1 |
| [白名单](./src/main/java/io/rong/methods/user/whitelist/Whitelist.java) | whitelist.add | 添加白名单 | 3.0.7 |
|  | whitelist.getList | 获取白名单列表| 3.0.7 |
|  | whitelist.remove | 移除白名单 | 3.0.7 |
| [用户封禁](./src/main/java/io/rong/methods/user/block/Block.java) | block.add |添加用户封禁 | 3.0.1 |
|  | block.getList| 获取用户封禁列表| 3.0.1 |
|  | block.remove| 移除用户封禁| 3.0.1 |
| [用户标签](./src/main/java/io/rong/methods/user/tag/Tag.java) | tag.set | 添加用户标签 |  |
|  | tag.batchset | 批量添加用户标签 | 3.0.4 |
|  | tag.get | 获取用户标签 | 3.0.4 |
| [用户全局群禁言](./src/main/java/io/rong/methods/user/mute/MuteGroups.java) | muteGroups.add | 添加全局群组禁言用户，添加后用户在应用下的所有群组中都不能发送消息 | 3.0.2 |
|  | muteGroups.remove | 移除全局群组禁言用户 | 3.0.2 |
|  | muteGroups.getList | 获取全局群组禁言用户列表 | 3.0.2 |
|  [用户全局聊天室禁言](./src/main/java/io/rong/methods/user/mute/MuteChatrooms.java) | muteChatrooms.add | 添加全局聊天室禁言用户，添加后用户在应用下的所有聊天室中都不能发送消息 | 3.0.2 |
|  |muteChatrooms.remove |移除全局聊天室禁言用户| 3.0.2 |
|  |muteChatrooms.getList |获取全局聊天室禁言用户列表| 3.0.2 |
| [敏感词](./src/main/java/io/rong/methods/sensitive/SensitiveWord.java) | add | 添加敏感词，添加后默认 2 小时生效| 3.0.1 |
|  | getList | 获取敏感词列表 | 3.0.1 |
|  | remove | 移除敏感词，支持批量移除功能，移除后默认 2 小时生效 | 3.0.1 |
| [消息发送](./src/main/java/io/rong/methods/message/) | private.send | 发送单聊消息 | 3.0.1 |
|  | private.sendTemplate | 发送单聊模板消息 | 3.0.1 |
|  | private.recall | 消息单聊撤回 | 3.0.1 |
|  | chatroom.send | 发送聊天室消息 | 3.0.1 |
|  | chatroom.recall | 撤回聊天室消息 | 3.0.7 |
|  | chatroom.broadcast| 发送聊天室广播消息 | 3.0.1 |
|  | group.send | 发送群组消息 | 3.0.1 |
|  | group.sendMention | 发送群组 @ 消息 | 3.1.6 |
|  | group.recall | 撤回群组消息 | 3.0.1 |
|  | system.send | 发送系统消息 | 3.0.1 |
|  | system.sendTemplate | 发送系统模板消息 | 3.0.1 |
|  | system.broadcast | 发送广播消息，单个应用每小时只能发送 2 次，每天最多发送 3 次。 | 3.0.1 |
|  | system.broadcast | 支持将通过 /push 发送的广播消息撤回功能，撤回广播消息也占用广播消息每天的使用配额。 | 3.0.7 |
| [消息历史记录](./src/main/java/io/rong/methods/message/history/History.java) | message.history.get | 消息历史记录下载地址获取 | 3.0.1 |
|  | message.history.remove | 消息历史记录删除方法 | 3.0.1 |
| [广播推送](./src/main/java/io/rong/methods/push/Push.java) | Push.push | 发送推送，推送和广播消息合计，单个应用每小时只能发送 2 次，每天最多发送 3 次。 | 3.0.4 |
|  | Push.message | 发送广播消息，推送和广播消息合计，单个应用每小时只能发送 2 次，每天最多发送 3 次。 | 3.0.4 |
| [群组](./src/main/java/io/rong/methods/group/Group.java) | create | 创建群组 | 3.0.1 |
|  | sync | 同步群关系 | 3.0.1 |
|  | update | 更新群信息 | 3.0.1 |
|  | get | 获取群信息 | 3.0.1 |
|  | invite | 邀请人加入群组 | 3.0.1 |
|  | quit | 退出群组 | 3.0.1 |
|  | dismiss | 解散群组 | 3.0.1 |
| [用户指定群禁言](./src/main/java/io/rong/methods/group/mute/MuteMembers.java) | muteMembers.add | 添加指定群组禁言用户，该用户在指定群组中不能发送消息 | 3.0.2 |
|  | muteMembers.remove | 移除指定群组禁言用户 | 3.0.2 |
|  | muteMembers.getList | 获取指定群组禁言用户列表 | 3.0.2 |
| [指定群组全部禁言](./src/main/java/io/rong/methods/group/mute/MuteAllMembers.java) | muteAllMembers.add | 添加指定群组全部成员禁言，添加后该群组中所有用户不能在此群组中发送消息 | 3.0.2 |
|  | muteAllMembers.remove | 移除指定群组全部成员禁言 | 3.0.2 |
|  | muteAllMembers.getList | 获取群组禁言列表 | 3.0.2 |
| [群全部禁言白名单](./src/main/java/io/rong/methods/group/mute/whitelist/User.java) | muteWhiteList.user.add |添加群组禁言白名单用户，群组被禁言后，该群白名单中用户可以在群组中发送消息 | 3.0.2 |
|  | muteWhiteList.user.remove | 移除群组禁言白名单用户 | 3.0.2 |
|  | muteWhiteList.user.getList | 获取群组禁言白名单用户列表 | 3.0.2 |
| [会话免打扰(Conversation)](./src/main/java/io/rong/methods/conversation/Conversation.java) | mute | 添加免打扰会话 | 3.0.1 |
|  | unMute | 移除免打扰会话 | 3.0.1 |
|  | get | 免打扰会话状态获取 | 3.0.1 |
| [聊天室](./src/main/java/io/rong/methods/chatroom/Chatroom.java) | create | 创建聊天室 | 3.0.1 |
|  | destroy | 销毁聊天室 | 3.0.1 |
|  | get | 查询聊天室信息 | 3.0.1 |
|  | isExist | 检查用户是否在聊天室 | 3.0.1 |
| [聊天室封禁](./src/main/java/io/rong/methods/chatroom/block/Block.java) | block.add | 添加聊天室封禁用户，被封禁后用户无法加入该聊天室，如用户正在聊天室中将被踢出聊天室 | 3.0.1 |
|  | block.getList | 获取聊天室封禁用户列表 | 3.0.1 |
|  | block.remove | 移除聊天室封禁用户 | 3.0.1 |
| [聊天室禁言](./src/main/java/io/rong/methods/chatroom/mute/MuteMembers.java) | muteMembers.add | 添加聊天室禁言用户，用户无法在该聊天室中发送消息 | 3.0.2 |
|  | muteMembers.getList | 获取聊天室禁言用户列表 | 3.0.2 |
|  | muteMembers.remove | 移除聊天室禁言用户 | 3.0.2 |
| [聊天室消息优先级](./src/main/java/io/rong/methods/chatroom/demotion/Demotion.java) | demotion.add | 添加聊天室低优先级消息，添加后因消息量激增导致服务器压力较大时，默认丢弃低级别的消息 | 3.0.1 |
|  |demotion.getList|查询聊天室低优先级消息列表 | 3.0.1 |
|  |demotion.remove|移除聊天室低优先级消息 | 3.0.1 |
| [聊天室消息分发控制](./src/main/java/io/rong/methods/chatroom/distribute/Distribute.java) | distribute.stop | 停止聊天室消息分发，服务端收到上行消息后不进行下行发送 | 3.0.1 |
|  |distribute.resume|恢复聊天室消息分发| 3.0.1 |
| [聊天室保活](./src/main/java/io/rong/methods/chatroom/keepalive/Keepalive.java) | keepalive.add | 添加保活聊天室，保活中的聊天室不会被自动销毁 | 3.0.1 |
|  | keepalive.remove | 移除保活聊天室 | 3.0.1 |
|  | keepalive.getList | 获取保活聊天室列表 | 3.0.1 |
| [聊天室消息白名单](./src/main/java/io/rong/methods/chatroom/whitelist/Messages.java) | whiteList.message.add | 添加白名单消息类型，白名单中的消息类型，在消息量激增导致服务器压力较大时不会被丢弃，确保消息到达 | 3.0.1 |
|  | whiteList.message.remove | 移除白名单消息类型 | 3.0.1 |
|  | whiteList.message.getList | 获取白名单消息类型列表 | 3.0.1 |
| [聊天室用户白名单](./src/main/java/io/rong/methods/chatroom/whitelist/User.java) | whiteList.user.add | 添加白名单用户，白名单中用户发送的消息，在消息量激增导致服务器压力较大时不会被丢弃，确保消息到达 | 3.0.1 |
|  | whiteList.user.remove | 移除白名单用户 | 3.0.1 |
|  | whiteList.user.getList | 获取白名单用户列表 | 3.0.1 |