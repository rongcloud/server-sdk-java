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
