package RCServerSDK

import "testing"

var rcServer *RCServer

//初始化RCServer_xml 方式
func Test_InitRCServer_xml(t *testing.T) {
	if _rcServer, initError := InitRCServer("your_appKey", "your_appSecret", "xml"); initError != nil || _rcServer == nil {
		t.Error("初始化RCServer_xml：测试失败！！！")
	} else {
		rcServer = _rcServer
		t.Log("初始化RCServe_xmlr：测试通过。")
	}
}

//初始化RCServer_json 方式
func Test_InitRCServer_json(t *testing.T) {
	if _rcServer, initError := InitRCServer("your_appKey", "your_appSecret", "json"); initError != nil || _rcServer == nil {
		t.Error("初始化RCServer_json：测试失败！！！")
	} else {
		rcServer = _rcServer
		t.Log("初始化RCServer_json：测试通过。")
	}
}

//初始化RCServer_other 方式
func Test_InitRCServer_otherType(t *testing.T) {
	if _rcServer, initError := InitRCServer("your_appKey", "your_appSecret", "foo"); initError != nil || _rcServer == nil {
		t.Log("初始化RCServer_other：测试通过。")

	} else {
		t.Error("初始化RCServer_other：测试失败！！！")

	}
}

//获取 Token 方法
func Test_UserGetToken(t *testing.T) {
	if byteData, tokenError := rcServer.UserGetToken("testUserId", "testUserName", "http://www.testPortrait.com"); tokenError != nil || len(byteData) == 0 {
		t.Error("获取 Token：测试失败！！！")
	} else {
		t.Log("获取 Token：测试通过。returnData:", string(byteData))
	}
}

//刷新用户信息 方法
func Test_UserRefresh(t *testing.T) {
	if returnData, returnError := rcServer.UserRefresh("testUserId", "testUserName", "http://www.testPortrait.com"); returnError != nil || len(returnData) == 0 {
		t.Error("刷新用户信息：测试失败！！！")
	} else {
		t.Log("刷新用户信息：测试通过。returnData:", string(returnData))
	}
}

//检查用户在线状态 方法
//请不要频繁循环调用此接口，而是选择合适的频率和时机调用，此接口设置了一定的频率限制。
func Test_UserCheckOnline(t *testing.T) {
	if returnData, returnError := rcServer.UserCheckOnline("testUserId"); returnError != nil || len(returnData) == 0 {
		t.Error("检查用户在线状态：测试失败！！！")
	} else {
		t.Log("检查用户在线状态：测试通过。returnData:", string(returnData))
	}
}

//封禁用户 方法
func Test_UserBlock(t *testing.T) {
	if returnData, returnError := rcServer.UserBlock("testUserId", "10"); returnError != nil || len(returnData) == 0 {
		t.Error("封禁用户：测试失败！！！")
	} else {
		t.Log("封禁用户：测试通过。returnData:", string(returnData))
	}
}

//解除用户封禁 方法
func Test_UserUnblock(t *testing.T) {
	if returnData, returnError := rcServer.UserUnblock("testUserId"); returnError != nil || len(returnData) == 0 {
		t.Error("解除用户封禁：测试失败！！！")
	} else {
		t.Log("解除用户封禁：测试通过。returnData:", string(returnData))
	}
}

//获取被封禁用户 方法
func Test_UserBlockQuery(t *testing.T) {
	if returnData, returnError := rcServer.UserBlockQuery(); returnError != nil || len(returnData) == 0 {
		t.Error("获取被封禁用户：测试失败！！！")
	} else {
		t.Log("获取被封禁用户：测试通过。returnData:", string(returnData))
	}
}

//发送单聊消息 方法
//说明：一个用户向另外一个用户发送消息
func Test_MessagePrivatePublish(t *testing.T) {
	if returnData, returnError := rcServer.MessagePrivatePublish("testUserId", []string{"testUserId"}, "RC:TxtMsg", "{\"content\":\"hello\",\"extra\":\"helloExtra\"}", "这是个测试", ""); returnError != nil || len(returnData) == 0 {
		t.Error("发送单聊消息：测试失败！！！")
	} else {
		t.Log("发送单聊消息：测试通过。returnData:", string(returnData))
	}
}

//发送系统消息
//说明：一个用户向一个或多个用户发送系统消息
func Test_MessageSystemPublish(t *testing.T) {
	if returnData, returnError := rcServer.MessageSystemPublish("testUserId", []string{"testUserId"}, "RC:TxtMsg", "{\"content\":\"hello\",\"extra\":\"helloExtra\"}", "这是个测试", ""); returnError != nil || len(returnData) == 0 {
		t.Error("发送系统消息：测试失败！！！")
	} else {
		t.Log("发送系统消息：测试通过。returnData:", string(returnData))
	}
}

//创建群组 方法
//创建群组，并将用户加入该群组，用户将可以收到该群的消息。注：其实本方法是加入群组方法 /group/join 的别名。
func Test_GroupCreat(t *testing.T) {
	if returnData, returnError := rcServer.GroupCreat("testUserId", "testGroupId", "testGroupName"); returnError != nil || len(returnData) == 0 {
		t.Error("创建群组：测试失败！！！")
	} else {
		t.Log("创建群组：测试通过。returnData:", string(returnData))
	}
}

//发送群组消息 方法
//说明：以一个用户身份向群组发送消息
func Test_MessageGroupPublish(t *testing.T) {
	if returnData, returnError := rcServer.MessageGroupPublish("testUserId", []string{"testGroupId"}, "RC:TxtMsg", "{\"content\":\"hello\",\"extra\":\"helloExtra\"}", "这是个测试", ""); returnError != nil || len(returnData) == 0 {
		t.Error("发送群组消息：测试失败！！！")
	} else {
		t.Log("发送群组消息：测试通过。returnData:", string(returnData))
	}
}

//创建聊天室 方法
func Test_ChatroomCreat(t *testing.T) {
	if returnData, returnError := rcServer.ChatroomCreat("testChatroomId", "testChatroomName"); returnError != nil || len(returnData) == 0 {
		t.Error("创建聊天室：测试失败！！！")
	} else {
		t.Log("创建聊天室：测试通过。returnData:", string(returnData))
	}
}

//发送聊天室消息 方法
//说明：一个用户向聊天室发送消息
func Test_MessageChatroomPublish(t *testing.T) {
	if returnData, returnError := rcServer.MessageChatroomPublish("testUserId", []string{"testChatroomId"}, "RC:TxtMsg", "{\"content\":\"hello\",\"extra\":\"helloExtra\"}"); returnError != nil || len(returnData) == 0 {
		t.Error("发送聊天室消息：测试失败！！！")
	} else {
		t.Log("发送聊天室消息：测试通过。returnData:", string(returnData))
	}
}

//发送广播消息 方法
//说明：某发送消息给一个应用下的所有注册用户。
//此服务尚未公开提供。如您需要，请提交工单给我们登记。
func Test_MessageBroadcast(t *testing.T) {
	if returnData, returnError := rcServer.MessageBroadcast("testUserId", "RC:TxtMsg", "{\"content\":\"hello\",\"extra\":\"helloExtra\"}"); returnError != nil || len(returnData) == 0 {
		t.Error("发送广播消息：测试失败！！！")
	} else {
		t.Log("发送广播消息：测试通过。returnData:", string(returnData))
	}
}

//消息历史记录下载地址获取 方法
//说明：获取 APP 内指定某天某小时内的所有会话消息记录的下载地址
func Test_MessageHistory(t *testing.T) {
	if returnData, returnError := rcServer.MessageHistory("2015010101"); returnError != nil || len(returnData) == 0 {
		t.Error("消息历史记录下载地址获取：测试失败！！！")
	} else {
		t.Log("消息历史记录下载地址获取：测试通过。returnData:", string(returnData))
	}
}

//消息历史记录删除 方法
//说明：删除 APP 内指定某天某小时内的所有会话消息记录
func Test_MessageHistoryDelete(t *testing.T) {
	if returnData, returnError := rcServer.MessageHistoryDelete("2015010101"); returnError != nil || len(returnData) == 0 {
		t.Error("消息历史记录删除：测试失败！！！")
	} else {
		t.Log("消息历史记录删除：测试通过。returnData:", string(returnData))
	}
}

//加入群组 方法
//将用户加入指定群组，用户将可以收到该群的消息。
func Test_GroupJoin(t *testing.T) {
	if returnData, returnError := rcServer.GroupJoin("testUserId", "testGroupId", "testGroupName"); returnError != nil || len(returnData) == 0 {
		t.Error("加入群组：测试失败！！！")
	} else {
		t.Log("加入群组：测试通过。returnData:", string(returnData))
	}
}

//退出群组 方法
//将用户从群中移除，不再接收该群组的消息。
func Test_GroupQuit(t *testing.T) {
	if returnData, returnError := rcServer.GroupQuit("testUserId", "testGroupId"); returnError != nil || len(returnData) == 0 {
		t.Error("退出群组：测试失败！！！")
	} else {
		t.Log("退出群组：测试通过。returnData:", string(returnData))
	}
}

//解散群组 方法
//将该群解散，所有用户都无法再接收该群的消息。
func Test_GroupDismiss(t *testing.T) {
	if returnData, returnError := rcServer.GroupDismiss("testUserId", "testGroupId"); returnError != nil || len(returnData) == 0 {
		t.Error("解散群组：测试失败！！！")
	} else {
		t.Log("解散群组：测试通过。returnData:", string(returnData))
	}
}

//刷新群组信息 方法
func Test_GroupRefresh(t *testing.T) {
	if returnData, returnError := rcServer.GroupRefresh("testGroupId", "testGroupName"); returnError != nil || len(returnData) == 0 {
		t.Error("刷新群组信息：测试失败！！！")
	} else {
		t.Log("刷新群组信息：测试通过。returnData:", string(returnData))
	}
}

//销毁聊天室 方法
func Test_ChatroomDestroy(t *testing.T) {
	if returnData, returnError := rcServer.ChatroomDestroy("testChatroomId"); returnError != nil || len(returnData) == 0 {
		t.Error("销毁聊天室 ：测试失败！！！")
	} else {
		t.Log("销毁聊天室 ：测试通过。returnData:", string(returnData))
	}
}

//查询聊天室信息 方法
func Test_ChatroomQuery(t *testing.T) {
	if returnData, returnError := rcServer.ChatroomQuery("testChatroomId"); returnError != nil || len(returnData) == 0 {
		t.Error("查询聊天室信息 ：测试失败！！！")
	} else {
		t.Log("查询聊天室信息 ：测试通过。returnData:", string(returnData))
	}
}
