server-sdk-go
=============

Rong Cloud Server SDK in Go.

使用

先安装 httplib 库：
httplib 库主要用来模拟客户端发送 HTTP 请求，类似于 Curl 工具，支持 JQuery 类似的链式操作。使用起来相当的方便；通过如下方式进行安装：

go get github.com/astaxie/beego/httplib


json用法: rcServer, initError := RCServerSDK.InitRCServer("your_appKey", "your_appSecret", "json")
xml用法: rcServer, initError := RCServerSDK.InitRCServer("your_appKey", "your_appSecret", "xml")

byteData, rcError := rcServer.UserGetToken("your_user_id", "your_name", "your_portrait_uri")
println("result:", string(byteData))


测试结果

=== RUN Test_InitRCServer_xml
--- PASS: Test_InitRCServer_xml (0.00s)
RCServer_test.go:13: 初始化RCServe_xmlr：测试通过。

=== RUN Test_InitRCServer_json
--- PASS: Test_InitRCServer_json (0.00s)
RCServer_test.go:23: 初始化RCServer_json：测试通过。

=== RUN Test_InitRCServer_otherType
--- PASS: Test_InitRCServer_otherType (0.00s)
RCServer_test.go:30: 初始化RCServer_other：测试通过。

=== RUN Test_UserGetToken
--- PASS: Test_UserGetToken (0.27s)
RCServer_test.go:43: 获取 Token：测试通过。returnData: {"code":200,"userId":"testUserId","token":"40WpwW65OA8WGwsB13xB/qFHDKhjHfDr9ou3WnmJxatWdYmGx9CnaDMkEm74P2gH/bVidgmj91P7JI0Vo5arbJfoA9ZYTVQX"}

=== RUN Test_UserRefresh
--- PASS: Test_UserRefresh (0.21s)
RCServer_test.go:52: 刷新用户信息：测试通过。returnData: {"code":200}

=== RUN Test_UserCheckOnline
--- PASS: Test_UserCheckOnline (0.23s)
RCServer_test.go:62: 检查用户在线状态：测试通过。returnData: {"code":200,"status":"0"}

=== RUN Test_UserBlock
--- PASS: Test_UserBlock (0.22s)
RCServer_test.go:71: 封禁用户：测试通过。returnData: {"code":200}

=== RUN Test_UserUnblock
--- PASS: Test_UserUnblock (0.23s)
RCServer_test.go:80: 解除用户封禁：测试通过。returnData: {"code":200}

=== RUN Test_UserBlockQuery
--- PASS: Test_UserBlockQuery (0.20s)
RCServer_test.go:89: 获取被封禁用户：测试通过。returnData: {"url":"/user/block/query.json","code":1000,"errorMessage":"Internal logic error."}

=== RUN Test_MessagePrivatePublish
--- PASS: Test_MessagePrivatePublish (0.20s)
RCServer_test.go:99: 发送单聊消息：测试通过。returnData: {"code":200}

=== RUN Test_MessageSystemPublish
--- PASS: Test_MessageSystemPublish (0.20s)
RCServer_test.go:109: 发送系统消息：测试通过。returnData: {"url":"/message/system/publish","code":404,"errorMessage":"Not a valid API."}

=== RUN Test_GroupCreat
--- PASS: Test_GroupCreat (0.21s)
RCServer_test.go:119: 创建群组：测试通过。returnData: {"code":200}

=== RUN Test_MessageGroupPublish
--- PASS: Test_MessageGroupPublish (0.23s)
RCServer_test.go:129: 发送群组消息：测试通过。returnData: {"code":200}

=== RUN Test_ChatroomCreat
--- PASS: Test_ChatroomCreat (0.21s)
RCServer_test.go:138: 创建聊天室：测试通过。returnData: {"code":200}

=== RUN Test_MessageChatroomPublish
--- PASS: Test_MessageChatroomPublish (0.22s)
RCServer_test.go:148: 发送聊天室消息：测试通过。returnData: {"code":200}

=== RUN Test_MessageBroadcast
--- PASS: Test_MessageBroadcast (0.24s)
RCServer_test.go:159: 发送广播消息：测试通过。returnData: {"url":"/message/broadcast.json","code":404,"errorMessage":"Not a valid API."}

=== RUN Test_MessageHistory
--- PASS: Test_MessageHistory (0.21s)
RCServer_test.go:169: 消息历史记录下载地址获取：测试通过。returnData: {"code":200,"url":"","date":"2015010101"}

=== RUN Test_MessageHistoryDelete
--- PASS: Test_MessageHistoryDelete (0.21s)
RCServer_test.go:179: 消息历史记录删除：测试通过。returnData: {"url":"/message/history/delete.json","code":1002,"errorMessage":"ParamError:data is not exist."}

=== RUN Test_GroupJoin
--- PASS: Test_GroupJoin (0.21s)
RCServer_test.go:189: 加入群组：测试通过。returnData: {"code":200}

=== RUN Test_GroupQuit
--- PASS: Test_GroupQuit (0.23s)
RCServer_test.go:199: 退出群组：测试通过。returnData: {"code":200}

=== RUN Test_GroupDismiss
--- PASS: Test_GroupDismiss (0.20s)
RCServer_test.go:209: 解散群组：测试通过。returnData: {"code":200}

=== RUN Test_GroupRefresh
--- PASS: Test_GroupRefresh (0.22s)
RCServer_test.go:218: 刷新群组信息：测试通过。returnData: {"code":200}

=== RUN Test_ChatroomDestroy
--- PASS: Test_ChatroomDestroy (0.21s)
RCServer_test.go:227: 销毁聊天室 ：测试通过。returnData: {"code":200}

=== RUN Test_ChatroomQuery
--- PASS: Test_ChatroomQuery (0.21s)
RCServer_test.go:236: 查询聊天室信息 ：测试通过。returnData: {"code":200,"chatRooms":[]}

PASS

ok  	github.com/yesidi/RCServerSDK	4.610s
