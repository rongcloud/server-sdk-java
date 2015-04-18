server-sdk-go
=============

Rong Cloud Server SDK in Go.


##如何使用

先安装 httplib 库：
httplib 库主要用来模拟客户端发送 HTTP 请求，类似于 Curl 工具，支持 JQuery 类似的链式操作。使用起来相当的方便；通过如下方式进行安装：

`go get github.com/astaxie/beego/httplib`

再安装 server-sdk-go：

`go get github.com/rongcloud/server-sdk-go`

####json用法: 

`rcServer, rcError := RCServerSDK.NewRCServer("your_appKey", "your_appSecret", "json")`

####xml用法: 

`rcServer, rcError := RCServerSDK.NewRCServer("your_appKey", "your_appSecret", "xml")`

####例如“获取token”: 

`byteData, rcError := rcServer.UserGetToken("your_user_id", "your_name", "your_portrait_uri")`


####如果需要解析得到的json：

在你自己的go文件里导入包 

`import "encoding/json"`

解析json

`var foo interface{}`

`json.Unmarshal(byteData, &foo)`

解析出来的数据就在 foo 变量里了，通过断言即可得到你需要的数据，也可以解析到自定义的struct里面，这里就不详述了。

####如果想得到字符串：

`println("jsonString:", string(byteData))`


##运行测试结果（成功通过！）
```javascript
=== RUN Test_NewRCServer_xml
--- PASS: Test_NewRCServer_xml (0.00 seconds)
RCServer_test.go:17: 初始化RCServe_xml：测试通过。

=== RUN Test_NewRCServer_json
--- PASS: Test_NewRCServer_json (0.00 seconds)
RCServer_test.go:27: 初始化RCServer_json：测试通过。

=== RUN Test_NewRCServer_otherType
--- PASS: Test_NewRCServer_otherType (0.00 seconds)
RCServer_test.go:34: 初始化RCServer_other：测试通过。

=== RUN Test_UserGetToken
--- PASS: Test_UserGetToken (0.55 seconds)
RCServer_test.go:47: 获取 Token：测试通过。returnData: {"code":200,"userId":"testUserId","token":"SdD6hDH8x7ugwhlVIFlO8OV77ez9s2QiXVQymUC50BNrG+9T1CrSL3MNUQeonp+HA01PYg1uT51eSMvkQGF/mM1OxX883sBI"}

=== RUN Test_UserRefresh
--- PASS: Test_UserRefresh (0.20 seconds)
RCServer_test.go:56: 刷新用户信息：测试通过。returnData: {"code":200}

=== RUN Test_UserCheckOnline
--- PASS: Test_UserCheckOnline (0.21 seconds)
RCServer_test.go:66: 检查用户在线状态：测试通过。returnData: {"code":200,"status":"0"}

=== RUN Test_UserBlock
--- PASS: Test_UserBlock (0.25 seconds)
RCServer_test.go:75: 封禁用户：测试通过。returnData: {"code":200}

=== RUN Test_UserUnblock
--- PASS: Test_UserUnblock (0.22 seconds)
RCServer_test.go:84: 解除用户封禁：测试通过。returnData: {"code":200}

=== RUN Test_UserBlockQuery
--- PASS: Test_UserBlockQuery (0.21 seconds)
RCServer_test.go:93: 获取被封禁用户：测试通过。returnData: {"code":200,"users":[]}

=== RUN Test_UserBlackAdd
--- PASS: Test_UserBlackAdd (0.28 seconds)
RCServer_test.go:102: 添加用户到黑名单：测试通过。returnData: {"code":200}

=== RUN Test_UserBlackRemove
--- PASS: Test_UserBlackRemove (0.23 seconds)
RCServer_test.go:111: 从黑名单中移除用户：测试通过。returnData: {"code":200}

=== RUN Test_UserBlackQuery
--- PASS: Test_UserBlackQuery (0.21 seconds)
RCServer_test.go:120: 获取某用户的黑名单列表：测试通过。returnData: {"code":200,"users":[]}

=== RUN Test_MessagePrivatePublish
--- PASS: Test_MessagePrivatePublish (0.20 seconds)
RCServer_test.go:130: 发送单聊消息：测试通过。returnData: {"code":200}

=== RUN Test_MessageSystemPublish
--- PASS: Test_MessageSystemPublish (0.20 seconds)
RCServer_test.go:140: 发送系统消息：测试通过。returnData: {"code":200}

=== RUN Test_GroupCreat
--- PASS: Test_GroupCreat (0.21 seconds)
RCServer_test.go:150: 创建群组：测试通过。returnData: {"code":200}

=== RUN Test_MessageGroupPublish
--- PASS: Test_MessageGroupPublish (0.24 seconds)
RCServer_test.go:160: 发送群组消息：测试通过。returnData: {"code":200}

=== RUN Test_ChatroomCreat
--- PASS: Test_ChatroomCreat (0.21 seconds)
RCServer_test.go:169: 创建聊天室：测试通过。returnData: {"code":200}

=== RUN Test_MessageChatroomPublish
--- PASS: Test_MessageChatroomPublish (0.20 seconds)
RCServer_test.go:179: 发送聊天室消息：测试通过。returnData: {"code":200}

=== RUN Test_MessageBroadcast
--- PASS: Test_MessageBroadcast (0.23 seconds)
RCServer_test.go:190: 发送广播消息：测试通过。returnData: {"code":200}

=== RUN Test_MessageReceive
--- PASS: Test_MessageReceive (0.20 seconds)
RCServer_test.go:204: 同步消息：测试通过。returnData: /receive_message.php

=== RUN Test_MessageHistory
--- PASS: Test_MessageHistory (0.20 seconds)
RCServer_test.go:214: 消息历史记录下载地址获取：测试通过。returnData: {"code":200,"url":"","date":"2015010101"}

=== RUN Test_MessageHistoryDelete
--- PASS: Test_MessageHistoryDelete (0.21 seconds)
RCServer_test.go:224: 消息历史记录删除：测试通过。returnData: {"url":"/message/history/delete.json","code":1002,"errorMessage":"ParamError:data is not exist."}

=== RUN Test_GroupSync
--- PASS: Test_GroupSync (0.20 seconds)
RCServer_test.go:234: 同步用户所属群组：测试通过。returnData: {"code":200}

=== RUN Test_GroupJoin
--- PASS: Test_GroupJoin (0.21 seconds)
RCServer_test.go:244: 加入群组：测试通过。returnData: {"code":200}

=== RUN Test_GroupQuit
--- PASS: Test_GroupQuit (0.22 seconds)
RCServer_test.go:254: 退出群组：测试通过。returnData: {"code":200}

=== RUN Test_GroupDismiss
--- PASS: Test_GroupDismiss (0.20 seconds)
RCServer_test.go:264: 解散群组：测试通过。returnData: {"code":200}

=== RUN Test_GroupRefresh
--- PASS: Test_GroupRefresh (0.20 seconds)
RCServer_test.go:273: 刷新群组信息：测试通过。returnData: {"code":200}

=== RUN Test_ChatroomDestroy
--- PASS: Test_ChatroomDestroy (0.27 seconds)
RCServer_test.go:282: 销毁聊天室 ：测试通过。returnData: {"code":200}

=== RUN Test_ChatroomQuery
--- PASS: Test_ChatroomQuery (0.21 seconds)
RCServer_test.go:291: 查询聊天室信息 ：测试通过。returnData: {"code":200,"chatRooms":[]}

PASS
ok  	_/Users/Ye/Documents/github/server-sdk-go/RCServerSDK       5.970s

```