server-sdk-go
=============

Rong Cloud Server SDK in Go.

# 版本说明
为方便开发者的接入使用，更好的对融云 Server SDK 进行维护管理，融云 Server SDK 2.0 统一规范了命名及调用方式，结构更加清晰。老版本的 Server SDK 已经切入v1分支，仍然可以使用，但不会再做新的功能更新。
如果您是新接入的开发者，建议您使用 Server SDK 2.0 版本。 对已集成使用老版本 Server SDK 的开发者，不能直接升级使用，强烈建议您重新测试后使用
# API文档
- 官方文档(http://www.rongcloud.cn/docs/server.html)

##如何使用

先安装 httplib 库：
httplib 库主要用来模拟客户端发送 HTTP 请求，类似于 Curl 工具，支持 JQuery 类似的链式操作。使用起来相当的方便；通过如下方式进行安装：

`go get github.com/astaxie/beego/httplib`

再安装 server-sdk-go：

`go get github.com/rongcloud/server-sdk-go`

###方法调用
* 请参考 rongcloud_test.go 上面提供了所有的 API 接口的调用用例。
 
## 高级API接口
### User
- getToken  获取 Token 
- refresh  刷新用户信息
- checkOnline  检查用户在线状态 
- block  封禁用户
- unBlock  解除用户封禁
- queryBlock  获取被封禁用户
- addBlacklist  添加用户到黑名单
- queryBlacklist  获取某用户的黑名单列表
- removeBlacklist  从黑名单中移除用户

### Message
- publishPrivate  发送单聊消息
- publishTemplate  发送单聊模板消息
- PublishSystem  发送系统消息
- publishSystemTemplate  发送系统模板消息
- publishGroup  发送群组消息
- publishDiscussion  发送讨论组消息
- publishChatroom  发送聊天室消息
- broadcast  发送广播消息
- getHistory  消息历史记录下载地址获取 消息历史记录下载地址获取。获取 APP 内指定某天某小时内的所有会话消息记录的下载地址
- deleteMessage  消息历史记录删除

### Wordfilter
- add  添加敏感词
- delete  移除敏感词
- getList  查询敏感词列表

### Group
- create  创建群组
- sync  同步用户所属群组
- refresh  刷新群组信息
- join  将用户加入指定群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人
- queryUser  查询群成员
- quit  退出群组
- addGagUser  添加禁言群成员
- lisGagUser  查询被禁言群成员
- rollBackGagUser  移除禁言群成员
- dismiss  解散群组。

### Chatroom
- create  创建聊天室
- join  加入聊天室
- query  查询聊天室信息
- queryUser  查询聊天室内用户
- stopDistributionMessage  聊天室消息停止分发
- resumeDistributionMessage  聊天室消息恢复分发
- addGagUser  添加禁言聊天室成员
- ListGagUser  查询被禁言聊天室成员
- rollbackGagUser  移除禁言聊天室成员
- addBlockUser  添加封禁聊天室成员
- getListBlockUser  查询被封禁聊天室成员
- rollbackBlockUser  移除封禁聊天室成员
- destroy  销毁聊天室
- addWhiteListUser  添加聊天室白名单成员

### Push
- setUserPushTag  添加 Push 标签
- broadcastPush  广播消息

### SMS
- getImageCode  获取图片验证码
- sendCode  发送短信验证码
- verifyCode  验证码验证
