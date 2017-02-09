package rcserversdk

import (
  "testing"
  "os"
  "io"
)


var rongcloud *RongCloud

//初始化RCServer_json 方式
func Test_NewRCServer_json(t *testing.T) {
  _rongcloud := CreateRongCloud("your_appKey", "your_appSecret")
  rongcloud = _rongcloud
  t.Log("初始化RCServer_json：测试通过。")
}

func readFile(path string) string {
	fi, err := os.Open(path)
	if err != nil {
		panic(err)
	}
	defer fi.Close()


	chunks := []byte{}
	buf := make([]byte, 1024)
	for {
		n, err := fi.Read(buf)
		if err != nil && err != io.EOF {
			panic(err)
		}
		if 0 == n {
			break
		}
		chunks = append(chunks, buf[:n]...)
		// fmt.Println(string(buf[:n]))
	}
	return string(chunks)
}


var jsonStr string
/**************** User ****************/
func Test_User_GetToken(t *testing.T){
    userId := "userId1"
    name := "username"
    portraitUri := "http://www.rongcloud.cn/images/logo.png"
    
    if result, tokenError := rongcloud.User.GetToken( userId,name,portraitUri  );tokenError != nil || result==nil{
		t.Error("GetToken: fail.")
    } else {
        t.Log("GetToken: pass.", result)
    }
}
   
func Test_User_Refresh(t *testing.T){
    userId := "userId1"
    name := "username"
    portraitUri := "http://www.rongcloud.cn/images/logo.png"
    
    if result, tokenError := rongcloud.User.Refresh( userId,name,portraitUri  );tokenError != nil || result==nil{
		t.Error("Refresh: fail.")
    } else {
        t.Log("Refresh: pass.", result)
    }
}
   
func Test_User_CheckOnline(t *testing.T){
    userId := "userId1"
    
    if result, tokenError := rongcloud.User.CheckOnline( userId  );tokenError != nil || result==nil{
		t.Error("CheckOnline: fail.")
    } else {
        t.Log("CheckOnline: pass.", result)
    }
}
   
func Test_User_Block(t *testing.T){
    userId := "userId4"
    minute := 10
    
    if result, tokenError := rongcloud.User.Block( userId,minute  );tokenError != nil || result==nil{
		t.Error("Block: fail.")
    } else {
        t.Log("Block: pass.", result)
    }
}
   
func Test_User_UnBlock(t *testing.T){
    userId := "userId2"
    
    if result, tokenError := rongcloud.User.UnBlock( userId  );tokenError != nil || result==nil{
		t.Error("UnBlock: fail.")
    } else {
        t.Log("UnBlock: pass.", result)
    }
}
   
func Test_User_QueryBlock(t *testing.T){
    
    if result, tokenError := rongcloud.User.QueryBlock(   );tokenError != nil || result==nil{
		t.Error("QueryBlock: fail.")
    } else {
        t.Log("QueryBlock: pass.", result)
    }
}
   
func Test_User_AddBlacklist(t *testing.T){
    userId := "userId1"
    blackUserId := "userId2"
    
    if result, tokenError := rongcloud.User.AddBlacklist( userId,blackUserId  );tokenError != nil || result==nil{
		t.Error("AddBlacklist: fail.")
    } else {
        t.Log("AddBlacklist: pass.", result)
    }
}
   
func Test_User_QueryBlacklist(t *testing.T){
    userId := "userId1"
    
    if result, tokenError := rongcloud.User.QueryBlacklist( userId  );tokenError != nil || result==nil{
		t.Error("QueryBlacklist: fail.")
    } else {
        t.Log("QueryBlacklist: pass.", result)
    }
}
   
func Test_User_RemoveBlacklist(t *testing.T){
    userId := "userId1"
    blackUserId := "userId2"
    
    if result, tokenError := rongcloud.User.RemoveBlacklist( userId,blackUserId  );tokenError != nil || result==nil{
		t.Error("RemoveBlacklist: fail.")
    } else {
        t.Log("RemoveBlacklist: pass.", result)
    }
}
   
/**************** Message ****************/
func Test_Message_PublishPrivate(t *testing.T){
    fromUserId := "userId1"
    toUserId := []string{"userId2","userid3","userId4"} 
    
    var voiceMessage VoiceMessage
    jsonStr =  "{\"content\":\"hello\",\"extra\":\"helloExtra\",\"duration\":20}"
    JsonParse(jsonStr,&voiceMessage)
    
    voiceMessage.SetType("RC:VcMsg")
    pushContent := "thisisapush"
    pushData := "{\"pushData\":\"hello\"}"
    count := "4"
    verifyBlacklist := 0
    isPersisted := 0
    isCounted := 0
    isIncludeSender := 0
    
    if result, tokenError := rongcloud.Message.PublishPrivate( fromUserId,toUserId,voiceMessage,pushContent,pushData,count,verifyBlacklist,isPersisted,isCounted,isIncludeSender  );tokenError != nil || result==nil{
		t.Error("PublishPrivate: fail.")
    } else {
        t.Log("PublishPrivate: pass.", result)
    }
}
   
func Test_Message_PublishTemplate(t *testing.T){
	jsonStr =  readFile("../jsonsource/TemplateMessage.json")
    var templateMessage TemplateMessage
    JsonParse(jsonStr, &templateMessage)	
    
    if result, tokenError := rongcloud.Message.PublishTemplate( templateMessage  );tokenError != nil || result==nil{
		t.Error("PublishTemplate: fail.")
    } else {
        t.Log("PublishTemplate: pass.", result)
    }
}
   
func Test_Message_PublishSystem(t *testing.T){
    fromUserId := "userId1"
    toUserId := []string{"userId2","userid3","userId4"} 
    
    var txtMessage TxtMessage
    jsonStr =  "{\"content\":\"hello\",\"extra\":\"helloExtra\"}"
    JsonParse(jsonStr,&txtMessage)
    
    txtMessage.SetType("RC:TxtMsg")
    pushContent := "thisisapush"
    pushData := "{\"pushData\":\"hello\"}"
    isPersisted := 0
    isCounted := 0
    
    if result, tokenError := rongcloud.Message.PublishSystem( fromUserId,toUserId,txtMessage,pushContent,pushData,isPersisted,isCounted  );tokenError != nil || result==nil{
		t.Error("PublishSystem: fail.")
    } else {
        t.Log("PublishSystem: pass.", result)
    }
}
   
func Test_Message_PublishSystemTemplate(t *testing.T){
	jsonStr =  readFile("../jsonsource/TemplateMessage.json")
    var templateMessage TemplateMessage
    JsonParse(jsonStr, &templateMessage)	
    
    if result, tokenError := rongcloud.Message.PublishSystemTemplate( templateMessage  );tokenError != nil || result==nil{
		t.Error("PublishSystemTemplate: fail.")
    } else {
        t.Log("PublishSystemTemplate: pass.", result)
    }
}
   
func Test_Message_PublishGroup(t *testing.T){
    fromUserId := "userId"
    toGroupId := []string{"groupId1","groupId2","groupId3"} 
    
    var txtMessage TxtMessage
    jsonStr =  "{\"content\":\"hello\",\"extra\":\"helloExtra\"}"
    JsonParse(jsonStr,&txtMessage)
    
    txtMessage.SetType("RC:TxtMsg")
    pushContent := "thisisapush"
    pushData := "{\"pushData\":\"hello\"}"
    isPersisted := 1
    isCounted := 1
    isIncludeSender := 0
    
    if result, tokenError := rongcloud.Message.PublishGroup( fromUserId,toGroupId,txtMessage,pushContent,pushData,isPersisted,isCounted,isIncludeSender  );tokenError != nil || result==nil{
		t.Error("PublishGroup: fail.")
    } else {
        t.Log("PublishGroup: pass.", result)
    }
}
   
func Test_Message_PublishDiscussion(t *testing.T){
    fromUserId := "userId1"
    toDiscussionId := "discussionId1"
    
    var txtMessage TxtMessage
    jsonStr =  "{\"content\":\"hello\",\"extra\":\"helloExtra\"}"
    JsonParse(jsonStr,&txtMessage)
    
    txtMessage.SetType("RC:TxtMsg")
    pushContent := "thisisapush"
    pushData := "{\"pushData\":\"hello\"}"
    isPersisted := 1
    isCounted := 1
    isIncludeSender := 0
    
    if result, tokenError := rongcloud.Message.PublishDiscussion( fromUserId,toDiscussionId,txtMessage,pushContent,pushData,isPersisted,isCounted,isIncludeSender  );tokenError != nil || result==nil{
		t.Error("PublishDiscussion: fail.")
    } else {
        t.Log("PublishDiscussion: pass.", result)
    }
}
   
func Test_Message_PublishChatroom(t *testing.T){
    fromUserId := "userId1"
    toChatroomId := []string{"ChatroomId1","ChatroomId2","ChatroomId3"} 
    
    var txtMessage TxtMessage
    jsonStr =  "{\"content\":\"hello\",\"extra\":\"helloExtra\"}"
    JsonParse(jsonStr,&txtMessage)
    
    txtMessage.SetType("RC:TxtMsg")
    
    if result, tokenError := rongcloud.Message.PublishChatroom( fromUserId,toChatroomId,txtMessage  );tokenError != nil || result==nil{
		t.Error("PublishChatroom: fail.")
    } else {
        t.Log("PublishChatroom: pass.", result)
    }
}
   
func Test_Message_Broadcast(t *testing.T){
    fromUserId := "userId1"
    
    var txtMessage TxtMessage
    jsonStr =  "{\"content\":\"哈哈\",\"extra\":\"hello ex\"}"
    JsonParse(jsonStr,&txtMessage)
    
    txtMessage.SetType("RC:TxtMsg")
    pushContent := "thisisapush"
    pushData := "{\"pushData\":\"hello\"}"
    os := "iOS"
    
    if result, tokenError := rongcloud.Message.Broadcast( fromUserId,txtMessage,pushContent,pushData,os  );tokenError != nil || result==nil{
		t.Error("Broadcast: fail.")
    } else {
        t.Log("Broadcast: pass.", result)
    }
}
   
func Test_Message_GetHistory(t *testing.T){
    date := "2014010101"
    
    if result, tokenError := rongcloud.Message.GetHistory( date  );tokenError != nil || result==nil{
		t.Error("GetHistory: fail.")
    } else {
        t.Log("GetHistory: pass.", result)
    }
}
   
func Test_Message_DeleteMessage(t *testing.T){
    date := "2014010101"
    
    if result, tokenError := rongcloud.Message.DeleteMessage( date  );tokenError != nil || result==nil{
		t.Error("DeleteMessage: fail.")
    } else {
        t.Log("DeleteMessage: pass.", result)
    }
}
   
/**************** Wordfilter ****************/
func Test_Wordfilter_Add(t *testing.T){
    word := "money"
    
    if result, tokenError := rongcloud.Wordfilter.Add( word  );tokenError != nil || result==nil{
		t.Error("Add: fail.")
    } else {
        t.Log("Add: pass.", result)
    }
}
   
func Test_Wordfilter_GetList(t *testing.T){
    
    if result, tokenError := rongcloud.Wordfilter.GetList(   );tokenError != nil || result==nil{
		t.Error("GetList: fail.")
    } else {
        t.Log("GetList: pass.", result)
    }
}
   
func Test_Wordfilter_Delete(t *testing.T){
    word := "money"
    
    if result, tokenError := rongcloud.Wordfilter.Delete( word  );tokenError != nil || result==nil{
		t.Error("Delete: fail.")
    } else {
        t.Log("Delete: pass.", result)
    }
}
   
/**************** Group ****************/
func Test_Group_Create(t *testing.T){
    userId := []string{"userId1","userid2","userId3"} 
    groupId := "groupId1"
    groupName := "groupName1"
    
    if result, tokenError := rongcloud.Group.Create( userId,groupId,groupName  );tokenError != nil || result==nil{
		t.Error("Create: fail.")
    } else {
        t.Log("Create: pass.", result)
    }
}
   
func Test_Group_Sync(t *testing.T){
    userId := "userId1"
    
    var groupInfo [] GroupInfo
    jsonStr =  "[{\"id\":\"groupId1\",\"name\" :\"groupName1\"},{\"id\":\"groupId2\",\"name\":\"groupName2\"},{\"id\":\"groupId3\",\"name\" :\"groupName3\"}]"
    JsonParse(jsonStr,&groupInfo)
    
    
    if result, tokenError := rongcloud.Group.Sync( userId,groupInfo  );tokenError != nil || result==nil{
		t.Error("Sync: fail.")
    } else {
        t.Log("Sync: pass.", result)
    }
}
   
func Test_Group_Refresh(t *testing.T){
    groupId := "groupId1"
    groupName := "newGroupName"
    
    if result, tokenError := rongcloud.Group.Refresh( groupId,groupName  );tokenError != nil || result==nil{
		t.Error("Refresh: fail.")
    } else {
        t.Log("Refresh: pass.", result)
    }
}
   
func Test_Group_Join(t *testing.T){
    userId := []string{"userId2","userid3","userId4"} 
    groupId := "groupId1"
    groupName := "TestGroup"
    
    if result, tokenError := rongcloud.Group.Join( userId,groupId,groupName  );tokenError != nil || result==nil{
		t.Error("Join: fail.")
    } else {
        t.Log("Join: pass.", result)
    }
}
   
func Test_Group_QueryUser(t *testing.T){
    groupId := "groupId1"
    
    if result, tokenError := rongcloud.Group.QueryUser( groupId  );tokenError != nil || result==nil{
		t.Error("QueryUser: fail.")
    } else {
        t.Log("QueryUser: pass.", result)
    }
}
   
func Test_Group_Quit(t *testing.T){
    userId := []string{"userId2","userid3","userId4"} 
    groupId := "TestGroup"
    
    if result, tokenError := rongcloud.Group.Quit( userId,groupId  );tokenError != nil || result==nil{
		t.Error("Quit: fail.")
    } else {
        t.Log("Quit: pass.", result)
    }
}
   
func Test_Group_AddGagUser(t *testing.T){
    userId := "userId1"
    groupId := "groupId1"
    minute := "1"
    
    if result, tokenError := rongcloud.Group.AddGagUser( userId,groupId,minute  );tokenError != nil || result==nil{
		t.Error("AddGagUser: fail.")
    } else {
        t.Log("AddGagUser: pass.", result)
    }
}
   
func Test_Group_LisGagUser(t *testing.T){
    groupId := "groupId1"
    
    if result, tokenError := rongcloud.Group.LisGagUser( groupId  );tokenError != nil || result==nil{
		t.Error("LisGagUser: fail.")
    } else {
        t.Log("LisGagUser: pass.", result)
    }
}
   
func Test_Group_RollBackGagUser(t *testing.T){
    userId := []string{"userId2","userid3","userId4"} 
    groupId := "groupId1"
    
    if result, tokenError := rongcloud.Group.RollBackGagUser( userId,groupId  );tokenError != nil || result==nil{
		t.Error("RollBackGagUser: fail.")
    } else {
        t.Log("RollBackGagUser: pass.", result)
    }
}
   
func Test_Group_Dismiss(t *testing.T){
    userId := "userId1"
    groupId := "groupId1"
    
    if result, tokenError := rongcloud.Group.Dismiss( userId,groupId  );tokenError != nil || result==nil{
		t.Error("Dismiss: fail.")
    } else {
        t.Log("Dismiss: pass.", result)
    }
}
   
/**************** Chatroom ****************/
func Test_Chatroom_Create(t *testing.T){
    
    var chatRoomInfo [] ChatRoomInfo
    jsonStr =  "[{\"id\":\"chatroomId\",\"name\":\"TestChatRoom\"},{\"id\":\"chatroomId1\",\"name\":\"TestChatRoom1\"},{\"id\":\"chatroomId2\",\"name\" :\"TestChatRoom2\"}]"
    JsonParse(jsonStr,&chatRoomInfo)
    
    
    if result, tokenError := rongcloud.Chatroom.Create( chatRoomInfo  );tokenError != nil || result==nil{
		t.Error("Create: fail.")
    } else {
        t.Log("Create: pass.", result)
    }
}
   
func Test_Chatroom_Join(t *testing.T){
    userId := []string{"userId2","userid3","userId4"} 
    chatroomId := "chatroomId1"
    
    if result, tokenError := rongcloud.Chatroom.Join( userId,chatroomId  );tokenError != nil || result==nil{
		t.Error("Join: fail.")
    } else {
        t.Log("Join: pass.", result)
    }
}
   
func Test_Chatroom_Query(t *testing.T){
    chatroomId := []string{"chatroomId1","chatroomId2","chatroomId3"} 
    
    if result, tokenError := rongcloud.Chatroom.Query( chatroomId  );tokenError != nil || result==nil{
		t.Error("Query: fail.")
    } else {
        t.Log("Query: pass.", result)
    }
}
   
func Test_Chatroom_QueryUser(t *testing.T){
    chatroomId := "chatroomId1"
    count := "500"
    order := "2"
    
    if result, tokenError := rongcloud.Chatroom.QueryUser( chatroomId,count,order  );tokenError != nil || result==nil{
		t.Error("QueryUser: fail.")
    } else {
        t.Log("QueryUser: pass.", result)
    }
}
   
func Test_Chatroom_StopDistributionMessage(t *testing.T){
    chatroomId := "chatroomId1"
    
    if result, tokenError := rongcloud.Chatroom.StopDistributionMessage( chatroomId  );tokenError != nil || result==nil{
		t.Error("StopDistributionMessage: fail.")
    } else {
        t.Log("StopDistributionMessage: pass.", result)
    }
}
   
func Test_Chatroom_ResumeDistributionMessage(t *testing.T){
    chatroomId := "chatroomId1"
    
    if result, tokenError := rongcloud.Chatroom.ResumeDistributionMessage( chatroomId  );tokenError != nil || result==nil{
		t.Error("ResumeDistributionMessage: fail.")
    } else {
        t.Log("ResumeDistributionMessage: pass.", result)
    }
}
   
func Test_Chatroom_AddGagUser(t *testing.T){
    userId := "userId1"
    chatroomId := "chatroomId1"
    minute := "1"
    
    if result, tokenError := rongcloud.Chatroom.AddGagUser( userId,chatroomId,minute  );tokenError != nil || result==nil{
		t.Error("AddGagUser: fail.")
    } else {
        t.Log("AddGagUser: pass.", result)
    }
}
   
func Test_Chatroom_ListGagUser(t *testing.T){
    chatroomId := "chatroomId1"
    
    if result, tokenError := rongcloud.Chatroom.ListGagUser( chatroomId  );tokenError != nil || result==nil{
		t.Error("ListGagUser: fail.")
    } else {
        t.Log("ListGagUser: pass.", result)
    }
}
   
func Test_Chatroom_RollbackGagUser(t *testing.T){
    userId := "userId1"
    chatroomId := "chatroomId1"
    
    if result, tokenError := rongcloud.Chatroom.RollbackGagUser( userId,chatroomId  );tokenError != nil || result==nil{
		t.Error("RollbackGagUser: fail.")
    } else {
        t.Log("RollbackGagUser: pass.", result)
    }
}
   
func Test_Chatroom_AddBlockUser(t *testing.T){
    userId := "userId1"
    chatroomId := "chatroomId1"
    minute := "1"
    
    if result, tokenError := rongcloud.Chatroom.AddBlockUser( userId,chatroomId,minute  );tokenError != nil || result==nil{
		t.Error("AddBlockUser: fail.")
    } else {
        t.Log("AddBlockUser: pass.", result)
    }
}
   
func Test_Chatroom_GetListBlockUser(t *testing.T){
    chatroomId := "chatroomId1"
    
    if result, tokenError := rongcloud.Chatroom.GetListBlockUser( chatroomId  );tokenError != nil || result==nil{
		t.Error("GetListBlockUser: fail.")
    } else {
        t.Log("GetListBlockUser: pass.", result)
    }
}
   
func Test_Chatroom_RollbackBlockUser(t *testing.T){
    userId := "userId1"
    chatroomId := "chatroomId1"
    
    if result, tokenError := rongcloud.Chatroom.RollbackBlockUser( userId,chatroomId  );tokenError != nil || result==nil{
		t.Error("RollbackBlockUser: fail.")
    } else {
        t.Log("RollbackBlockUser: pass.", result)
    }
}
   
func Test_Chatroom_AddPriority(t *testing.T){
    objectName := []string{"RC:VcMsg","RC:ImgTextMsg","RC:ImgMsg"} 
    
    if result, tokenError := rongcloud.Chatroom.AddPriority( objectName  );tokenError != nil || result==nil{
		t.Error("AddPriority: fail.")
    } else {
        t.Log("AddPriority: pass.", result)
    }
}
   
func Test_Chatroom_Destroy(t *testing.T){
    chatroomId := []string{"chatroomId","chatroomId1","chatroomId2"} 
    
    if result, tokenError := rongcloud.Chatroom.Destroy( chatroomId  );tokenError != nil || result==nil{
		t.Error("Destroy: fail.")
    } else {
        t.Log("Destroy: pass.", result)
    }
}
   
func Test_Chatroom_AddWhiteListUser(t *testing.T){
    chatroomId := "chatroomId"
    userId := []string{"userId1","userId2","userId3","userId4","userId5"} 
    
    if result, tokenError := rongcloud.Chatroom.AddWhiteListUser( chatroomId,userId  );tokenError != nil || result==nil{
		t.Error("AddWhiteListUser: fail.")
    } else {
        t.Log("AddWhiteListUser: pass.", result)
    }
}
   
/**************** Push ****************/
func Test_Push_SetUserPushTag(t *testing.T){
	jsonStr =  readFile("../jsonsource/UserTag.json")
    var userTag UserTag
    JsonParse(jsonStr, &userTag)	
    
    if result, tokenError := rongcloud.Push.SetUserPushTag( userTag  );tokenError != nil || result==nil{
		t.Error("SetUserPushTag: fail.")
    } else {
        t.Log("SetUserPushTag: pass.", result)
    }
}
   
func Test_Push_BroadcastPush(t *testing.T){
	jsonStr =  readFile("../jsonsource/PushMessage.json")
    var pushMessage PushMessage
    JsonParse(jsonStr, &pushMessage)	
    
    if result, tokenError := rongcloud.Push.BroadcastPush( pushMessage  );tokenError != nil || result==nil{
		t.Error("BroadcastPush: fail.")
    } else {
        t.Log("BroadcastPush: pass.", result)
    }
}
   
/**************** SMS ****************/
func Test_SMS_GetImageCode(t *testing.T){
    appKey := "app-key"
    
    if result, tokenError := rongcloud.SMS.GetImageCode( appKey  );tokenError != nil || result==nil{
		t.Error("GetImageCode: fail.")
    } else {
        t.Log("GetImageCode: pass.", result)
    }
}
   
func Test_SMS_SendCode(t *testing.T){
    mobile := "13500000000"
    templateId := "dsfdsfd"
    region := "86"
    verifyId := "1408706337"
    verifyCode := "1408706337"
    
    if result, tokenError := rongcloud.SMS.SendCode( mobile,templateId,region,verifyId,verifyCode  );tokenError != nil || result==nil{
		t.Error("SendCode: fail.")
    } else {
        t.Log("SendCode: pass.", result)
    }
}
   
func Test_SMS_VerifyCode(t *testing.T){
    sessionId := "2312312"
    code := "2312312"
    
    if result, tokenError := rongcloud.SMS.VerifyCode( sessionId,code  );tokenError != nil || result==nil{
		t.Error("VerifyCode: fail.")
    } else {
        t.Log("VerifyCode: pass.", result)
    }
}
   
