package io.rong;

import io.rong.messages.*;
import io.rong.models.*;
import io.rong.util.GsonUtil;

import java.io.Reader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 一些api的调用示例
 */
public class Example {
	private static final String JSONFILE = Example.class.getClassLoader().getResource("jsonsource").getPath()+"/";
	/**
	 * 本地调用测试
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String appKey = "appkey";//替换成您的appkey
		String appSecret = "secret";//替换成匹配上面key的secret
		
		Reader reader = null ;
		RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
				
		
		System.out.println("************************User********************");
		// 获取 Token 方法 
		TokenReslut userGetTokenResult = rongCloud.user.getToken("userId1", "username", "http://www.rongcloud.cn/images/logo.png");
		System.out.println("getToken:  " + userGetTokenResult.toString());
		
		// 刷新用户信息方法 
		CodeSuccessReslut userRefreshResult = rongCloud.user.refresh("userId1", "username", "http://www.rongcloud.cn/images/logo.png");
		System.out.println("refresh:  " + userRefreshResult.toString());
		
		// 检查用户在线状态 方法 
		CheckOnlineReslut userCheckOnlineResult = rongCloud.user.checkOnline("userId1");
		System.out.println("checkOnline:  " + userCheckOnlineResult.toString());
		
		// 封禁用户方法（每秒钟限 100 次） 
		CodeSuccessReslut userBlockResult = rongCloud.user.block("userId4", 10);
		System.out.println("block:  " + userBlockResult.toString());
		
		// 解除用户封禁方法（每秒钟限 100 次） 
		CodeSuccessReslut userUnBlockResult = rongCloud.user.unBlock("userId2");
		System.out.println("unBlock:  " + userUnBlockResult.toString());
		
		// 获取被封禁用户方法（每秒钟限 100 次） 
		QueryBlockUserReslut userQueryBlockResult = rongCloud.user.queryBlock();
		System.out.println("queryBlock:  " + userQueryBlockResult.toString());
		
		// 添加用户到黑名单方法（每秒钟限 100 次） 
		CodeSuccessReslut userAddBlacklistResult = rongCloud.user.addBlacklist("userId1", "userId2");
		System.out.println("addBlacklist:  " + userAddBlacklistResult.toString());
		
		// 获取某用户的黑名单列表方法（每秒钟限 100 次） 
		QueryBlacklistUserReslut userQueryBlacklistResult = rongCloud.user.queryBlacklist("userId1");
		System.out.println("queryBlacklist:  " + userQueryBlacklistResult.toString());
		
		// 从黑名单中移除用户方法（每秒钟限 100 次） 
		CodeSuccessReslut userRemoveBlacklistResult = rongCloud.user.removeBlacklist("userId1", "userId2");
		System.out.println("removeBlacklist:  " + userRemoveBlacklistResult.toString());
		
		
		
		System.out.println("************************Message********************");
		// 发送单聊消息方法（一个用户向另外一个用户发送消息，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人，如：一次发送 1000 人时，示为 1000 条消息。） 
		String[] messagePublishPrivateToUserId = {"userId2","userid3","userId4"};
		VoiceMessage messagePublishPrivateVoiceMessage = new VoiceMessage("hello", "helloExtra", 20L);
		CodeSuccessReslut messagePublishPrivateResult = rongCloud.message.publishPrivate("userId1", messagePublishPrivateToUserId, messagePublishPrivateVoiceMessage, "thisisapush", "{\"pushData\":\"hello\"}", "4", 0, 0, 0);
		System.out.println("publishPrivate:  " + messagePublishPrivateResult.toString());
		
		// 发送单聊模板消息方法（一个用户向多个用户发送不同消息内容，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人。） 
		try {
			reader = new InputStreamReader(new FileInputStream(JSONFILE+"TemplateMessage.json"));
			TemplateMessage publishTemplateTemplateMessage  =  (TemplateMessage)GsonUtil.fromJson(reader, TemplateMessage.class);
			CodeSuccessReslut messagePublishTemplateResult = rongCloud.message.publishTemplate(publishTemplateTemplateMessage);
			System.out.println("publishTemplate:  " + messagePublishTemplateResult.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(null != reader){
				reader.close();
			}
		} 
		
		// 发送系统消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM。每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。） 
		String[] messagePublishSystemToUserId = {"userId2","userid3","userId4"};
		TxtMessage messagePublishSystemTxtMessage = new TxtMessage("hello", "helloExtra");
		CodeSuccessReslut messagePublishSystemResult = rongCloud.message.PublishSystem("userId1", messagePublishSystemToUserId, messagePublishSystemTxtMessage, "thisisapush", "{\"pushData\":\"hello\"}", 0, 0);
		System.out.println("PublishSystem:  " + messagePublishSystemResult.toString());
		
		// 发送系统模板消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM.每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。） 
		try {
			reader = new InputStreamReader(new FileInputStream(JSONFILE+"TemplateMessage.json"));
			TemplateMessage publishSystemTemplateTemplateMessage  =  (TemplateMessage)GsonUtil.fromJson(reader, TemplateMessage.class);
			CodeSuccessReslut messagePublishSystemTemplateResult = rongCloud.message.publishSystemTemplate(publishSystemTemplateTemplateMessage);
			System.out.println("publishSystemTemplate:  " + messagePublishSystemTemplateResult.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(null != reader){
				reader.close();
			}
		} 
		
		// 发送群组消息方法（以一个用户身份向群组发送消息，单条消息最大 128k.每秒钟最多发送 20 条消息，每次最多向 3 个群组发送，如：一次向 3 个群组发送消息，示为 3 条消息。） 
		String[] messagePublishGroupToGroupId = {"groupId1","groupId2","groupId3"};
		TxtMessage messagePublishGroupTxtMessage = new TxtMessage("hello", "helloExtra");
		CodeSuccessReslut messagePublishGroupResult = rongCloud.message.publishGroup("userId", messagePublishGroupToGroupId, messagePublishGroupTxtMessage, "thisisapush", "{\"pushData\":\"hello\"}", 1, 1);
		System.out.println("publishGroup:  " + messagePublishGroupResult.toString());
		
		// 发送讨论组消息方法（以一个用户身份向讨论组发送消息，单条消息最大 128k，每秒钟最多发送 20 条消息.） 
		TxtMessage messagePublishDiscussionTxtMessage = new TxtMessage("hello", "helloExtra");
		CodeSuccessReslut messagePublishDiscussionResult = rongCloud.message.publishDiscussion("userId1", "discussionId1", messagePublishDiscussionTxtMessage, "thisisapush", "{\"pushData\":\"hello\"}", 1, 1);
		System.out.println("publishDiscussion:  " + messagePublishDiscussionResult.toString());
		
		// 发送聊天室消息方法（一个用户向聊天室发送消息，单条消息最大 128k。每秒钟限 100 次。） 
		String[] messagePublishChatroomToChatroomId = {"ChatroomId1","ChatroomId2","ChatroomId3"};
		TxtMessage messagePublishChatroomTxtMessage = new TxtMessage("hello", "helloExtra");
		CodeSuccessReslut messagePublishChatroomResult = rongCloud.message.publishChatroom("userId1", messagePublishChatroomToChatroomId, messagePublishChatroomTxtMessage);
		System.out.println("publishChatroom:  " + messagePublishChatroomResult.toString());
		
		// 发送广播消息方法（发送消息给一个应用下的所有注册用户，如用户未在线会对满足条件（绑定手机终端）的用户发送 Push 信息，单条消息最大 128k，会话类型为 SYSTEM。每小时只能发送 1 次，每天最多发送 3 次。） 
		TxtMessage messageBroadcastTxtMessage = new TxtMessage("hello", "helloExtra");
		CodeSuccessReslut messageBroadcastResult = rongCloud.message.broadcast("userId1", messageBroadcastTxtMessage, "thisisapush", "{\"pushData\":\"hello\"}", "iOS");
		System.out.println("broadcast:  " + messageBroadcastResult.toString());
		
		// 消息历史记录下载地址获取 方法消息历史记录下载地址获取方法。获取 APP 内指定某天某小时内的所有会话消息记录的下载地址。（目前支持二人会话、讨论组、群组、聊天室、客服、系统通知消息历史记录下载） 
		HistoryMessageReslut messageGetHistoryResult = rongCloud.message.getHistory("2014010101");
		System.out.println("getHistory:  " + messageGetHistoryResult.toString());
		
		// 消息历史记录删除方法（删除 APP 内指定某天某小时内的所有会话消息记录。调用该接口返回成功后，date参数指定的某小时的消息记录文件将在随后的5-10分钟内被永久删除。） 
		CodeSuccessReslut messageDeleteMessageResult = rongCloud.message.deleteMessage("2014010101");
		System.out.println("deleteMessage:  " + messageDeleteMessageResult.toString());
		
		
		
		System.out.println("************************Wordfilter********************");
		// 添加敏感词方法（设置敏感词后，App 中用户不会收到含有敏感词的消息内容，默认最多设置 50 个敏感词。） 
		CodeSuccessReslut wordfilterAddResult = rongCloud.wordfilter.add("money");
		System.out.println("add:  " + wordfilterAddResult.toString());
		
		// 查询敏感词列表方法 
		ListWordfilterReslut wordfilterGetListResult = rongCloud.wordfilter.getList();
		System.out.println("getList:  " + wordfilterGetListResult.toString());
		
		// 移除敏感词方法（从敏感词列表中，移除某一敏感词。） 
		CodeSuccessReslut wordfilterDeleteResult = rongCloud.wordfilter.delete("money");
		System.out.println("delete:  " + wordfilterDeleteResult.toString());
		
		
		
		System.out.println("************************Group********************");
		// 创建群组方法（创建群组，并将用户加入该群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人，App 内的群组数量没有限制.注：其实本方法是加入群组方法 /group/join 的别名。） 
		String[] groupCreateUserId = {"userId1","userid2","userId3"};
		CodeSuccessReslut groupCreateResult = rongCloud.group.create(groupCreateUserId, "groupId1", "groupName1");
		System.out.println("create:  " + groupCreateResult.toString());
		
		// 同步用户所属群组方法（当第一次连接融云服务器时，需要向融云服务器提交 userId 对应的用户当前所加入的所有群组，此接口主要为防止应用中用户群信息同融云已知的用户所属群信息不同步。） 
		GroupInfo[] groupSyncGroupInfo = {new GroupInfo("groupId1","groupName1" ), new GroupInfo("groupId2","groupName2" ), new GroupInfo("groupId3","groupName3" )};
		CodeSuccessReslut groupSyncResult = rongCloud.group.sync("userId1", groupSyncGroupInfo);
		System.out.println("sync:  " + groupSyncResult.toString());
		
		// 刷新群组信息方法 
		CodeSuccessReslut groupRefreshResult = rongCloud.group.refresh("groupId1", "newGroupName");
		System.out.println("refresh:  " + groupRefreshResult.toString());
		
		// 将用户加入指定群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人。 
		String[] groupJoinUserId = {"userId2","userid3","userId4"};
		CodeSuccessReslut groupJoinResult = rongCloud.group.join(groupJoinUserId, "groupId1", "TestGroup");
		System.out.println("join:  " + groupJoinResult.toString());
		
		// 查询群成员方法 
		GroupUserQueryReslut groupQueryUserResult = rongCloud.group.queryUser("groupId1");
		System.out.println("queryUser:  " + groupQueryUserResult.toString());
		
		// 退出群组方法（将用户从群中移除，不再接收该群组的消息.） 
		String[] groupQuitUserId = {"userId2","userid3","userId4"};
		CodeSuccessReslut groupQuitResult = rongCloud.group.quit(groupQuitUserId, "TestGroup");
		System.out.println("quit:  " + groupQuitResult.toString());
		
		// 添加禁言群成员方法（在 App 中如果不想让某一用户在群中发言时，可将此用户在群组中禁言，被禁言用户可以接收查看群组中用户聊天信息，但不能发送消息。） 
		CodeSuccessReslut groupAddGagUserResult = rongCloud.group.addGagUser("userId1", "groupId1", "1");
		System.out.println("addGagUser:  " + groupAddGagUserResult.toString());
		
		// 查询被禁言群成员方法 
		ListGagGroupUserReslut groupLisGagUserResult = rongCloud.group.lisGagUser("groupId1");
		System.out.println("lisGagUser:  " + groupLisGagUserResult.toString());
		
		// 移除禁言群成员方法 
		String[] groupRollBackGagUserUserId = {"userId2","userid3","userId4"};
		CodeSuccessReslut groupRollBackGagUserResult = rongCloud.group.rollBackGagUser(groupRollBackGagUserUserId, "groupId1");
		System.out.println("rollBackGagUser:  " + groupRollBackGagUserResult.toString());
		
		// 解散群组方法。（将该群解散，所有用户都无法再接收该群的消息。） 
		CodeSuccessReslut groupDismissResult = rongCloud.group.dismiss("userId1", "groupId1");
		System.out.println("dismiss:  " + groupDismissResult.toString());
		
		
		
		System.out.println("************************Chatroom********************");
		// 创建聊天室方法 
		ChatRoomInfo[] chatroomCreateChatRoomInfo = {new ChatRoomInfo("chatroomId1","chatroomName1" ), new ChatRoomInfo("chatroomId2","chatroomName2" ), new ChatRoomInfo("chatroomId3","chatroomName3" )};
		CodeSuccessReslut chatroomCreateResult = rongCloud.chatroom.create(chatroomCreateChatRoomInfo);
		System.out.println("create:  " + chatroomCreateResult.toString());
		
		// 加入聊天室方法 
		String[] chatroomJoinUserId = {"userId2","userid3","userId4"};
		CodeSuccessReslut chatroomJoinResult = rongCloud.chatroom.join(chatroomJoinUserId, "chatroomId1");
		System.out.println("join:  " + chatroomJoinResult.toString());
		
		// 查询聊天室信息方法 
		String[] chatroomQueryChatroomId = {"chatroomId1","chatroomId2","chatroomId3"};
		ChatroomQueryReslut chatroomQueryResult = rongCloud.chatroom.query(chatroomQueryChatroomId);
		System.out.println("query:  " + chatroomQueryResult.toString());
		
		// 查询聊天室内用户方法 
		ChatroomUserQueryReslut chatroomQueryUserResult = rongCloud.chatroom.queryUser("chatroomId1", "500", "2");
		System.out.println("queryUser:  " + chatroomQueryUserResult.toString());
		
		// 聊天室消息停止分发方法（可实现控制对聊天室中消息是否进行分发，停止分发后聊天室中用户发送的消息，融云服务端不会再将消息发送给聊天室中其他用户。） 
		CodeSuccessReslut chatroomStopDistributionMessageResult = rongCloud.chatroom.stopDistributionMessage("chatroomId1");
		System.out.println("stopDistributionMessage:  " + chatroomStopDistributionMessageResult.toString());
		
		// 聊天室消息恢复分发方法 
		CodeSuccessReslut chatroomResumeDistributionMessageResult = rongCloud.chatroom.resumeDistributionMessage("chatroomId1");
		System.out.println("resumeDistributionMessage:  " + chatroomResumeDistributionMessageResult.toString());
		
		// 添加禁言聊天室成员方法（在 App 中如果不想让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息.） 
		CodeSuccessReslut chatroomAddGagUserResult = rongCloud.chatroom.addGagUser("userId1", "chatroomId1", "1");
		System.out.println("addGagUser:  " + chatroomAddGagUserResult.toString());
		
		// 查询被禁言聊天室成员方法 
		ListGagChatroomUserReslut chatroomListGagUserResult = rongCloud.chatroom.ListGagUser("chatroomId1");
		System.out.println("ListGagUser:  " + chatroomListGagUserResult.toString());
		
		// 移除禁言聊天室成员方法 
		CodeSuccessReslut chatroomRollbackGagUserResult = rongCloud.chatroom.rollbackGagUser("userId1", "chatroomId1");
		System.out.println("rollbackGagUser:  " + chatroomRollbackGagUserResult.toString());
		
		// 添加封禁聊天室成员方法 
		CodeSuccessReslut chatroomAddBlockUserResult = rongCloud.chatroom.addBlockUser("userId1", "chatroomId1", "1");
		System.out.println("addBlockUser:  " + chatroomAddBlockUserResult.toString());
		
		// 查询被封禁聊天室成员方法 
		ListBlockChatroomUserReslut chatroomGetListBlockUserResult = rongCloud.chatroom.getListBlockUser("chatroomId1");
		System.out.println("getListBlockUser:  " + chatroomGetListBlockUserResult.toString());
		
		// 移除封禁聊天室成员方法 
		CodeSuccessReslut chatroomRollbackBlockUserResult = rongCloud.chatroom.rollbackBlockUser("userId1", "chatroomId1");
		System.out.println("rollbackBlockUser:  " + chatroomRollbackBlockUserResult.toString());
		
		// 添加聊天室消息优先级方法 
		String[] chatroomAddPriorityObjectName = {"RC:VcMsg","RC:ImgTextMsg","RC:ImgMsg"};
		CodeSuccessReslut chatroomAddPriorityResult = rongCloud.chatroom.addPriority(chatroomAddPriorityObjectName);
		System.out.println("addPriority:  " + chatroomAddPriorityResult.toString());
		
		// 销毁聊天室方法 
		String[] chatroomDestroyChatroomId = {"chatroomId","chatroomId1","chatroomId2"};
		CodeSuccessReslut chatroomDestroyResult = rongCloud.chatroom.destroy(chatroomDestroyChatroomId);
		System.out.println("destroy:  " + chatroomDestroyResult.toString());
		
		// 添加聊天室白名单成员方法 
		String[] chatroomAddWhiteListUserUserId = {"userId1","userId2","userId3","userId4","userId5"};
		CodeSuccessReslut chatroomAddWhiteListUserResult = rongCloud.chatroom.addWhiteListUser("chatroomId", chatroomAddWhiteListUserUserId);
		System.out.println("addWhiteListUser:  " + chatroomAddWhiteListUserResult.toString());
		
		
		
		System.out.println("************************Push********************");
		// 添加 Push 标签方法 
		try {
			reader = new InputStreamReader(new FileInputStream(JSONFILE+"UserTag.json"));
			UserTag setUserPushTagUserTag  =  (UserTag)GsonUtil.fromJson(reader, UserTag.class);
			CodeSuccessReslut pushSetUserPushTagResult = rongCloud.push.setUserPushTag(setUserPushTagUserTag);
			System.out.println("setUserPushTag:  " + pushSetUserPushTagResult.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(null != reader){
				reader.close();
			}
		} 
		
		// 广播消息方法（fromuserid 和 message为null即为不落地的push） 
		try {
			reader = new InputStreamReader(new FileInputStream(JSONFILE+"PushMessage.json"));
			PushMessage broadcastPushPushMessage  =  (PushMessage)GsonUtil.fromJson(reader, PushMessage.class);
			CodeSuccessReslut pushBroadcastPushResult = rongCloud.push.broadcastPush(broadcastPushPushMessage);
			System.out.println("broadcastPush:  " + pushBroadcastPushResult.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(null != reader){
				reader.close();
			}
		} 
		
		
		
		System.out.println("************************SMS********************");
		// 获取图片验证码方法 
		SMSImageCodeReslut sMSGetImageCodeResult = rongCloud.sms.getImageCode("app-key");
		System.out.println("getImageCode:  " + sMSGetImageCodeResult.toString());
		
		// 发送短信验证码方法。 
		SMSSendCodeReslut sMSSendCodeResult = rongCloud.sms.sendCode("13500000000", "dsfdsfd", "86", "1408706337", "1408706337");
		System.out.println("sendCode:  " + sMSSendCodeResult.toString());
		
		// 验证码验证方法 
		CodeSuccessReslut sMSVerifyCodeResult = rongCloud.sms.verifyCode("2312312", "2312312");
		System.out.println("verifyCode:  " + sMSVerifyCodeResult.toString());
		
	 }
}