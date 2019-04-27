package io.rong.example;

import io.rong.RongCloud;
import io.rong.messages.TxtMessage;
import io.rong.messages.VoiceMessage;
import io.rong.models.Result;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.group.UserGroup;
import io.rong.models.message.*;
import io.rong.models.response.*;
import io.rong.models.sensitiveword.SensitiveWordModel;
import io.rong.models.user.UserModel;
import io.rong.util.GsonUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.Assert.assertEquals;

/**
 * 一些api的调用示例和测试
 */
public class Example {
	private RongCloud rongCloud ;
	private static final TxtMessage txtMessage = new TxtMessage("hello", "helloExtra");
	private static final VoiceMessage voiceMessage = new VoiceMessage("hello", "helloExtra", 20L);
	private static final String[] targetIds = {"LoDld8izA"};

	@Before
	public void setUp() throws Exception {
		String appKey = "appKey";
		String appSecret = "appSecret";
		rongCloud = RongCloud.getInstance(appKey, appSecret);
		//rongCloud = RongCloud.getInstance(appKey, appSecret,api);
		/**
		 * 自定义备用 API 地址用法
		 * List apiList = new ArrayList<>();
		 * apiList.add("http://api2-cn.ronghub.com");
		 * apiList.add("http://api-na02.ronghub.com");
		 * rongCloud = RongCloud.getInstance(appKey, appSecret,api,apiList);
		 * */
	}

	/**
	 *
	 * 检查用户在线状态 方法
	 */
	@Test
	public void testCheckOnline() throws Exception {
		UserModel user = new UserModel();
		user.setId("userId");

		CheckOnlineResult result = rongCloud.user.onlineStatus.check(user);
		System.out.println("checkOnline:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 *
	 * 用户注册测试
	 */
	@Test
	public void testRegister() throws Exception {

		UserModel user = new UserModel()
				.setId("userId1")
				.setName("username")
				.setPortrait("http://www.rongcloud.cn/images/logo.png");

		TokenResult result = rongCloud.user.register(user);

		System.out.println("getToken:  " + result.toString());
		assertEquals("200",result.getCode().toString());

	}

	/**
	 *  刷新用户信息测试
	 */
	@Test
	public void testUserRefresh() throws Exception {
		UserModel user = new UserModel()
				.setId("userId1")
				.setName("username")
				.setPortrait("http://www.rongcloud.cn/images/logo.png");

		Result result = (ResponseResult)rongCloud.user.update(user);
		System.out.println("refresh:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}
	/**
	 * 测试用户封禁方法（每秒钟限 100 次）
	 */
	@Test
	public void testUserAddBlock() throws Exception {
		UserModel user = new UserModel()
				.setId("hkjo09h")
				.setMinute(1000);
		Result result = (ResponseResult)rongCloud.user.block.add(user);
		System.out.println("userAddBlock:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 * 测试解除用户封禁方法（每秒钟限 100 次）
	 */
	@Test
	public void testUserUnBlock() throws Exception {
		ResponseResult result = (ResponseResult)rongCloud.user.block.remove("userId2");
		System.out.println("unBlock:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  获取被封禁用户方法（每秒钟限 100 次）
	 */
	@Test
	public void testUserQueryBlock() throws Exception {

		BlockUserResult result = (BlockUserResult)rongCloud.user.block.getList();
		System.out.println("queryBlock:  " + result.toString());

		assertEquals("200",result.getCode().toString());


	}
	/**
	 * 添加用户到黑名单方法（每秒钟限 100 次）
	 */
	@Test
	public void testAddBlacklist() throws Exception {

		UserModel blackUser = new UserModel().setId("hdsjGB89");
		UserModel[] blacklist = {blackUser};
		UserModel user = new UserModel()
				.setId("hdsjGB89")
				.setBlacklist(blacklist);

		Result result = (Result)rongCloud.user.blackList.add(user);
		System.out.println("addBlacklist:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  获取某用户的黑名单列表方法（每秒钟限 100 次）
	 */
	@Test
	public void testGetBlacklist() throws Exception {
		UserModel user = new UserModel().setId("hdsjGB89");

		BlackListResult result = rongCloud.user.blackList.getList(user);
		System.out.println("queryBlacklist:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *
	 *  从黑名单中移除用户方法（每秒钟限 100 次）
	 */
	@Test
	public void testRemoveBlacklist() throws Exception {
		UserModel blackUser = new UserModel().setId("hdsjGB89");
		UserModel[] blacklist = {blackUser};
		UserModel user = new UserModel()
				.setId("hdsjGB89")
				.setBlacklist(blacklist);
		Result result = (Result)rongCloud.user.blackList.remove(user);
		System.out.println("removeBlacklist:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 系统消息测试
	 */
	@Test
	public void testSendSystem() throws Exception {
		String[] targetIds = new String[100];

		for(int i=0;i<2;i++) {
			targetIds[i] = "test"+i;
		}
		SystemMessage systemMessage = new SystemMessage()
					.setSenderId("usetId")
					.setTargetId(targetIds)
					.setObjectName(txtMessage.getType())
					.setContent(txtMessage)
					.setPushContent("this is a push")
					.setPushData("{'pushData':'hello'}")
					.setIsPersisted(0)
					.setIsCounted(0)
					.setContentAvailable(0);
		ResponseResult result = rongCloud.message.system.send(systemMessage);
		System.out.println("publishSystem:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}
	/**
	 * 发送系统模板消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，
	 * 会话类型为 SYSTEM.每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：
	 * 一次发送 100 人时，示为 100 条消息。）
	 */
	@Test
	public void testSendSystemTemplate() throws Exception {
		Reader reader = null ;
		try {

			reader =new BufferedReader( new InputStreamReader(Example.class.getClassLoader().getResourceAsStream("jsonsource/message/TemplateMessage.json")));

			TemplateMessage template = (TemplateMessage)GsonUtil.fromJson(reader, TemplateMessage.class);

			ResponseResult result = rongCloud.message.system.sendTemplate(template);
			System.out.println("sendSystemTemplate:  " + result.toString());

			assertEquals("200",result.getCode().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != reader){
				reader.close();
			}
		}
	}
	/**
	 * 广播消息测试
	 */
	@Test
	public void testSendBroadcast() throws Exception {
		BroadcastMessage message = new BroadcastMessage()
				.setSenderId("OScHVP1tQ")
				.setObjectName(txtMessage.getType())
				.setContent(txtMessage)
				.setPushContent("this is a push")
				.setPushData("{'pushData':'hello'}")
				.setOs("Android");
		ResponseResult result = rongCloud.message.system.broadcast(message);
		System.out.println("send broadcast:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}
	/**
	 * 测试单聊模板消息
	 */
	@Test
	public void testSendPrivateTemplate() throws Exception {
		Reader reader = null ;
		// 发送单聊模板消息方法（一个用户向多个用户发送不同消息内容，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人。）
		try {
			reader =new BufferedReader( new InputStreamReader(Example.class.getClassLoader().getResourceAsStream("jsonsource/message/TemplateMessage.json")));
			TemplateMessage template  =  (TemplateMessage) GsonUtil.fromJson(reader, TemplateMessage.class);
			System.out.println(template.toString());
			ResponseResult messagePublishTemplateResult = rongCloud.message.msgPrivate.sendTemplate(template);
			System.out.println("sendPrivateTemplate:  " + messagePublishTemplateResult.toString());

			assertEquals("200",messagePublishTemplateResult.getCode().toString());

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != reader){
				reader.close();
			}
		}
	}
	/**
	 * 测试单聊消息
	 * */
	@Test
	public void testSendPrivate() throws Exception {
		Reader reader = null ;
		PrivateMessage  privateMessage = new PrivateMessage()
				.setSenderId("userId")
				.setTargetId(targetIds)
				.setObjectName(voiceMessage.getType())
				.setContent(voiceMessage)
				.setPushContent("")
				.setPushData("{\"pushData\":\"hello\"}")
				.setCount("4")
				.setVerifyBlacklist(0)
				.setIsPersisted(0)
				.setIsCounted(0)
				.setIsIncludeSender(0);

		//发送单聊方法
		ResponseResult publishPrivateResult = rongCloud.message.msgPrivate.send(privateMessage);
		System.out.println("sendPrivate:  " + publishPrivateResult.toString());

		assertEquals("200",publishPrivateResult.getCode().toString());
	}
	/**
	 * 测试撤回单聊消息
	 * */
	@Test
	public void testRecallPrivate() throws Exception {
		RecallMessage message = new RecallMessage()
				.setSenderId("OScHVP1tQ")
				.setTargetId("NomSvNyME")
				.setuId("B8FV-QAHO-I1E4-JLRI")
				.setSentTime("1548334967010");
		ResponseResult result = (ResponseResult)rongCloud.message.msgPrivate.recall(message);

		System.out.println("recall private message:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}
	/**
	 * 测试群组消息
	 * */
	@Test
	public void testSendGroup() throws Exception {
		//群组消息
		GroupMessage groupMessage = new GroupMessage()
				.setSenderId("Hji8yh76")
				.setTargetId(targetIds)
				.setObjectName(txtMessage.getType())
				.setContent(txtMessage)
				.setPushContent("this is a push")
				.setPushData("{\"pushData\":\"hello\"}")
				.setIsPersisted(0)
				.setIsIncludeSender(0)
				.setContentAvailable(0);
		ResponseResult result = rongCloud.message.group.send(groupMessage);

		System.out.println("send group:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 测试群组@消息
	 * */
	@Test
	public void testSendGroupMention() throws Exception {
		String[] targetId = {"HJjjd98k"};
		//要@的人
		String[] mentionIds = {"Hji8yh76","sea9901"};
		//@内容
		MentionedInfo mentionedInfo = new MentionedInfo(1,mentionIds,"push");
		//@消息的消息内容
		MentionMessageContent content = new MentionMessageContent(txtMessage,mentionedInfo);

		MentionMessage mentionMessage = new MentionMessage()
				.setSenderId("userId")
				.setTargetId(targetId)
				.setObjectName(txtMessage.getType())
				.setContent(content)
				.setPushContent("this is a push")
				.setPushData("{\"pushData\":\"hello\"}")
				.setIsPersisted(0)
				.setIsCounted(0)
				.setIsIncludeSender(0)
				.setContentAvailable(0);
		ResponseResult result = rongCloud.message.group.sendMention(mentionMessage);

		System.out.println("send group:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}
	/**
	 * 测试群组定向消息
	 * */
	@Test
	public void testSendGroupDirection() throws Exception {
		//群组消息
		String[] toUserIds= {"aRUuikSyp"};
		GroupMessage groupMessage = new GroupMessage()
				.setSenderId("XN3gZdUOB")
				.setTargetId(targetIds)
				.setToUserId(toUserIds)
				.setObjectName(txtMessage.getType())
				.setContent(txtMessage)
				.setPushContent("this is a push")
				.setPushData("{\"pushData\":\"hello\"}")
				.setIsPersisted(0)
				.setIsIncludeSender(0)
				.setContentAvailable(0);
		ResponseResult result = rongCloud.message.group.sendDirection(groupMessage);

		System.out.println("send group:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}
	/**
	 * 测试群组撤回消息
	 * */
	@Test
	public void testRecallGroup() throws Exception {
		RecallMessage message = new RecallMessage()
				.setSenderId("NomSvNyME")
				.setTargetId("BauMbEShY")
				.setuId("B8FV-UKT9-TJMD-KV6A")
				.setSentTime("1548335533735");
		ResponseResult result = (ResponseResult)rongCloud.message.group.recall(message);

		System.out.println("send recall message:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 测试聊天室消息
	 * */
	@Test
	public void testSendChatroom() throws Exception {
		//聊天室消息
		ChatroomMessage message = new ChatroomMessage()
				.setSenderId("targetIds")
				.setTargetId(targetIds)
				.setContent(txtMessage)
				.setObjectName(txtMessage.getType());
		ResponseResult result = rongCloud.message.chatroom.send(message);
		System.out.println("publishChatroomPrivate:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}
	/**
	 * 测试删除历史消息
	 * */
	@Test
	public void testRemoveHistory() throws Exception {
		ResponseResult result = rongCloud.message.history.remove("2018030210");
		System.out.println("remove history  message:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 测试获取历史消息
	 * */
	@Test
	public void testGetHistory() throws Exception {
		HistoryMessageResult result = (HistoryMessageResult)rongCloud.message.history.get("2018032810");
		System.out.println("get history  message:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 添加敏感词方法（设置敏感词后，App 中用户不会收到含有敏感词的消息内容，默认最多设置 50 个敏感词。）
	 * */
	@Test
	public void testAddSensitiveword() throws Exception {
		SensitiveWordModel sentiveWord = new SensitiveWordModel()
				.setType(0)
				.setKeyword("黄赌毒")
				.setReplace("***");
		ResponseResult result = rongCloud.sensitiveword.add(sentiveWord);
		System.out.println("add:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 查询敏感词列表方法
	 * */
	@Test
	public void testGetListSensitiveword() throws Exception {

		ListWordfilterResult result = rongCloud.sensitiveword.getList(1);
		System.out.println("getList:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 移除敏感词方法（从敏感词列表中，移除某一敏感词。）
	 * */
	@Test
	public void testDeleteSensitiveword() throws Exception {

		ResponseResult result = rongCloud.sensitiveword.remove(null);
		System.out.println("SensitivewordDelete:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 创建群组方法（创建群组，并将用户加入该群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，
	 * 每个群最大至 3000 人，App 内的群组数量没有限制.注：其实本方法是加入群组方法 /group/join 的别名。）
	 */
	@Test
	public void testGroupCreate() throws Exception {

		GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};
		GroupModel group = new GroupModel()
				.setId("ghJiu7H3")
				.setMembers(members)
				.setName("groupName");
		Result result = (Result)rongCloud.group.create(group);
		System.out.println("group create result:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 * 	同步用户所属群组方法测试（当第一次连接融云服务器时，需要向融云服务器提交 userId 对应的用户当前所加入的所有群组，
	 * 	此接口主要为防止应用中用户群信息同融云已知的用户所属群信息不同步。）
	 */
	@Test
	public  void testGroupSync() throws Exception {

		GroupModel group1 = new GroupModel().setId("groupId1").setName("groupName1");
		GroupModel group2 = new GroupModel().setId("groupId2").setName("groupName2");
		GroupModel[] groupSyncGroups = {group1,group2};

		UserGroup user = new UserGroup();
		user.setGroups(groupSyncGroups);
		user.setId("jhkoi90jj");

		Result result = (Result)rongCloud.group.sync(user);

		System.out.println("sync:  " + result.toString());
		assertEquals("200",result.getCode().toString());

	}

	/**
	 *  刷新群组信息方法测试
	 */
	@Test
	public void testGroupUpdate() throws Exception {

		GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

		GroupModel group = new GroupModel()
				.setId("hiuyr743k")
				.setMembers(members)
				.setName("RongCloud");
		Result result = (Result)rongCloud.group.update(group);
		System.out.println("refresh:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 将用户加入指定群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人。
	 */
	@Test
	public void testGroupJoin() throws Exception {
		GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};
		GroupModel group = new GroupModel()
				.setId("hgir769ll")
				.setMembers(members)
				.setName("RongClooud");
		Result result = (Result)rongCloud.group.join(group);
		System.out.println("join:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  查询群成员方法
	 */
	@Test
	public  void testGroupQueryUser() throws Exception {

		GroupModel group = new GroupModel().setId("figk97h");

		GroupUserQueryResult result = rongCloud.group.get(group);
		System.out.println("groupQueryUser:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  退出群组方法测试（将用户从群中移除，不再接收该群组的消息.）
	 */
	@Test
	public void testGroupQuit() throws Exception {

		GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};
		GroupModel group = new GroupModel()
				.setId("groupId")
				.setMembers(members)
				.setName("groupName");
		Result result = (Result)rongCloud.group.quit(group);
		System.out.println("quit:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  添加禁言群成员方法测试
	 */
	@Test
	public void testGroupAddGagUser() throws Exception {

		GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};
		GroupModel group = new GroupModel()
				.setId("appSecret")
				.setMembers(members)
				.setMinute(5);
		Result result = (Result)rongCloud.group.gag.add(group);
		System.out.println("group.gag.add:  " + result.toString());

		assertEquals("200",result.getCode().toString());


	}
	/**
	 *  查询被禁言群成员方法
	 */
	@Test
	public void testGroupLisGagUser() throws Exception {
		ListGagGroupUserResult result = rongCloud.group.gag.getList("25");
		System.out.println("group.gag.getList:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  移除禁言群成员方法
	 */
	@Test
	public void testGroupRollBackGagUser() throws Exception {
		GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};
		GroupModel group = new GroupModel()
				.setMembers(members)
				.setId("jhgui85hh");

		ResponseResult result = (ResponseResult)rongCloud.group.gag.remove(group);
		System.out.println("group.gag.remove:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  解散群组方法
	 */
	@Test
	public void testGroupDismissResult() throws Exception {
		GroupMember[] members = new GroupMember[]{new GroupMember().setId("ghJiu7H1")};
		GroupModel group = new GroupModel()
				.setId("groupId")
				.setMembers(members);

		Result result = (Result)rongCloud.group.dismiss(group);
		System.out.println("groupDismissResult:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  添加全局禁言群方法
	 */
	@Test
	public void testGroupBanUserResult() throws Exception {
		GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

		GroupModel groupModel = new GroupModel()
				.setMembers(members)
				.setMinute(1000000);
		Result result = rongCloud.group.ban.user.add(groupModel);
		System.out.println("group.ban.add:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}

	/**
	 *
	 * 获取全局禁言群方法
	 */
	@Test
	public void testGroupBanUserGetListResult() throws Exception {

		ListGagGroupUserResult result = (ListGagGroupUserResult) rongCloud.group.ban.user.getList();
		System.out.println("group.ban.getList:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}

	/**
	 *
	 * 移除用户全局禁言群方法
	 */
	@Test
	public void testGroupBanUserRemoveResult() throws Exception {

		GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

		GroupModel groupModel = new GroupModel()
				.setMembers(members);
		Result result =  rongCloud.group.ban.user.remove(groupModel);
		System.out.println("group.ban.remove:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}


	/**
	 * 添加禁言白名单用户方法
	 */
	@Test
	public void testGroupBanWhitelistAddResult() throws Exception {

		GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

		GroupModel groupModel = new GroupModel()
				.setId("groupId")
				.setMembers(members);
		Result result = rongCloud.group.ban.whitelist.user.add(groupModel);
		System.out.println("group.ban.add:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}

	/**
	 * API 文档:
	 * 查询禁言白名单用户方法
	 */
	@Test
	public void testGroupBanWhitelistGetListResult() throws Exception {

		GroupBanWhitelistResult result = (GroupBanWhitelistResult) rongCloud.group.ban.whitelist.user.getList("groupId");
		System.out.println("group.ban.getList:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}


	/**
	 * API 文档:
	 * 移除禁言白名单用户方法
	 */
	@Test
	public void testGroupBanWhitelistRemoveResult() throws Exception {

		GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

		GroupModel groupModel = new GroupModel()
				.setMembers(members)
				.setId("groupId");
		Result result =  rongCloud.group.ban.whitelist.user.remove(groupModel);
		System.out.println("group.ban.remove:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 * 添加禁言群方法
	 */
	@Test
	public void testGroupBanAddResult() throws Exception {
		String[] groupIds = {"ghJiu7H1","ghJiu7H2","ghJiu7H3","ghJiu7H4","ghJiu7H5","ghJiu7H6","ghJiu7H7","ghJiu7H8","ghJiu7H9","ghJiu7H10"};
		Result result = rongCloud.group.ban.add(groupIds);

		System.out.println("group.ban.remove:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}

	/**
	 * 查询禁言群方法
	 */
	@Test
	public void testGroupBanGetListResult() throws Exception {
		String[] groupIds = {"ghJiu7H1","ghJiu7H2","ghJiu7H3","ghJiu7H4","ghJiu7H5","ghJiu7H6","ghJiu7H7","ghJiu7H8"};

		GroupBanResult result = (GroupBanResult)rongCloud.group.ban.getList();
		System.out.println("group.ban.getList:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}

	/**
	 * 移除禁言群方法
	 */
	@Test
	public void testGroupBanRemoveResult() throws Exception {
		String[] groupIds = {"ghJiu7H1","ghJiu7H2","ghJiu7H3","ghJiu7H4","ghJiu7H5","ghJiu7H6","ghJiu7H7"};

		Result result = rongCloud.group.ban.remove(groupIds);
		System.out.println("group.ban.remove:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 * 创建聊天室
	 *
	 * */
	@Test
	public void testCreateChatroom() throws Exception {
		ChatroomModel[] chatrooms = {
				new ChatroomModel().setId("chatroomId1").setName("chatroomName1"),
				new ChatroomModel().setId("chatroomId2").setName("chatroomName2")
		};
		ResponseResult result =  rongCloud.chatroom.create(chatrooms);

		System.out.println("create:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}
	/**
	 *
	 * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/chatroom/chatroom.html#getMembers
	 * 查询聊天室成员demo
	 *
	 * */
	@Test
	public void testGetChatroom() throws Exception {
		ChatroomModel chatroomModel = new ChatroomModel()
				.setId("d7ec7a8b8d8546c98b0973417209a548");

		ResponseResult result =  rongCloud.chatroom.destroy(chatroomModel);
		System.out.println("destroy:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}
	/**
	 *
	 * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/chatroom/chatroom.html#isExist
	 * 查询聊天室成员是否存在
	 *
	 * */
	@Test
	public void testChatroomIsExist() throws Exception {
		ChatroomMember member = new ChatroomMember()
				.setId("76894")
				.setChatroomId("76891");

		CheckChatRoomUserResult result = rongCloud.chatroom.isExist(member);
		System.out.println("checkChatroomUserResult:  " + result.isInChrm);

		assertEquals("200",result.getCode().toString());
	}
	/**
	 *  添加聊天室消息白名单
	 */
	@Test
	public void addChatroomMsgWhiteList() throws Exception {
		String[] messageType = {"RC:VcMsg", "RC:ImgTextMsg", "RC:ImgMsg"};
		ResponseResult result = rongCloud.chatroom.whiteList.message.add(messageType);
		System.out.println("remove chatroon whitelist msg:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  删除聊天室消息白名单
	 */
	@Test
	public void testRemoveChatroomMsgWhiteList() throws Exception {
		String[] messageType = {"RC:VcMsg", "RC:ImgTextMsg", "RC:ImgMsg"};
		ResponseResult result = rongCloud.chatroom.whiteList.message.remove(messageType);
		System.out.println("remove chatroon whitelist msg::  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  获取聊天室消息白名单
	 */
	@Test
	public void testGetChatroomMsgWhiteList() throws Exception {
		ChatroomWhitelistMsgResult result = rongCloud.chatroom.whiteList.message.getList();
		System.out.println("get chatroon whitelist msg:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  添加聊天室用户白名单
	 */
	@Test
	public void addChatroomUserWhiteList() throws Exception {
		ChatroomMember[] members = {
				new ChatroomMember().setId("qawr34h"),new ChatroomMember().setId("qawr35h")
		};
		ChatroomModel chatroom = new ChatroomModel()
				.setId("d7ec7a8b8d8546c98b0973417209a548")
				.setMembers(members);
		ResponseResult result = rongCloud.chatroom.whiteList.user.add(chatroom);
		System.out.println("remove chatroon whitelist msg:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  删除聊天室用户白名单
	 */
	@Test
	public void testRemoveChatroomWhiteListMsg() throws Exception {
		ChatroomMember[] members = {
				new ChatroomMember().setId("qawr34h"),new ChatroomMember().setId("qawr35h")
		};
		ChatroomModel chatroom = new ChatroomModel()
				.setId("d7ec7a8b8d8546c98b0973417209a548")
				.setMembers(members);
		ResponseResult result = rongCloud.chatroom.whiteList.user.remove(chatroom);
		System.out.println("remove chatroom user whitelist:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  获取聊天室消息白名单
	 */
	@Test
	public void testGetChatroomWhiteListMsg() throws Exception {
		ChatroomModel chatroom = new ChatroomModel()
				.setId("d7ec7a8b8d8546c98b0973417209a548");
		Result result = rongCloud.chatroom.whiteList.user.getList(chatroom);
		System.out.println("get chatroom user whitelist:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 * 添加聊天室全局禁言
	 */
	@Test
	public void testChatroomBan() throws Exception {
		ChatroomMember[] members = {
				new ChatroomMember().setId("qawr34h"), new ChatroomMember().setId("qawr35h")
		};
		ChatroomModel chatroom = new ChatroomModel()
				.setMembers(members)
				.setMinute(5);
		ResponseResult result = rongCloud.chatroom.ban.add(chatroom);
		System.out.println("addGagUser:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 * 添加聊天室全局禁言
	 */
	@Test
	public void testAddChatroomBan() throws Exception {
		ChatroomMember[] members = {
				new ChatroomMember().setId("qawr34h"), new ChatroomMember().setId("qawr35h")
		};
		ChatroomModel chatroom = new ChatroomModel()
				.setMembers(members)
				.setMinute(5);
		ResponseResult result = rongCloud.chatroom.ban.add(chatroom);
		System.out.println("addGagUser:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 * 获取聊天时全局禁言列表
	 */
	@Test
	public void testGetChatroomBan() throws Exception {

		ListGagChatroomUserResult chatroomListGagUserResult = rongCloud.chatroom.ban.getList();
		System.out.println("ListGagUser:  " + chatroomListGagUserResult.toString());

		assertEquals("200",chatroomListGagUserResult.getCode().toString());

	}
	/**
	 * 删除聊天时全局禁言
	 */
	@Test
	public void testRemoveChatroomBan() throws Exception {
		ChatroomMember[] members = {
				new ChatroomMember().setId("qawr34h"), new ChatroomMember().setId("qawr35h")
		};
		ChatroomModel chatroom = new ChatroomModel()
				.setMembers(members)
				.setMinute(5);
		ResponseResult removeResult = rongCloud.chatroom.ban.remove(chatroom);
		System.out.println("removeBanUser:  " + removeResult.toString());

		assertEquals("200",removeResult.getCode().toString());

	}
	/**
	 * 添加封禁聊天室成员方法
	 */
	@Test
	public void testAddChatroomBlock() throws Exception {
		ChatroomMember[] members = {
				new ChatroomMember().setId("qawr34h"), new ChatroomMember().setId("qawr35h")
		};
		ChatroomModel chatroom = new ChatroomModel()
				.setId("d7ec7a8b8d8546c98b0973417209a548")
				.setMembers(members)
				.setMinute(5);
		ResponseResult result = rongCloud.chatroom.block.add(chatroom);
		System.out.println("addBlockUser:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 * 移除封禁聊天室成员方法
	 */
	@Test
	public void testRemoveChatroomBlock() throws Exception {
		ChatroomMember[] members = {
				new ChatroomMember().setId("qawr34h"), new ChatroomMember().setId("qawr35h")
		};
		ChatroomModel chatroom = new ChatroomModel()
				.setId("d7ec7a8b8d8546c98b0973417209a548")
				.setMembers(members);
		ResponseResult result = rongCloud.chatroom.block.remove(chatroom);
		System.out.println("removeBlockUser:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 * 获取封禁聊天室成员方法
	 */
	@Test
	public void testGetChatroomBlock() throws Exception {

		ListBlockChatroomUserResult result = rongCloud.chatroom.block.getList("d7ec7a8b8d8546c98b0973417209a548");
		System.out.println("getListBlockUser:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}
	/**
	 *
	 * 销毁聊天室
	 *
	 * */
	@Test
	public void testDestroyChatroom() throws Exception {
		ChatroomModel chatroomModel = new ChatroomModel()
				.setId("d7ec7a8b8d8546c98b0973417209a548");

		ResponseResult result =  rongCloud.chatroom.destroy(chatroomModel);
		System.out.println("destroy:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}
	/**
	 *
	 * 添加应用内聊天室降级消息
	 *
	 * */
	@Test
	public void testAddChatroomDemotion() throws Exception {
		String[] messageType = {"RC:VcMsg", "RC:ImgTextMsg", "RC:ImgMsg"};
		ResponseResult result = rongCloud.chatroom.demotion.add(messageType);
		System.out.println("add demotion:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}
	/**
	 *
	 * 移除应用内聊天室降级消息
	 *
	 * */
	@Test
	public void testRemoveChatroomDemotion() throws Exception {
		String[] messageType = {"RC:VcMsg", "RC:ImgTextMsg"};
		ResponseResult result = rongCloud.chatroom.demotion.remove(messageType);
		System.out.println("remove demotion:  " + result.toString());
		System.out.println("add demotion:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}

	/**
	 *
	 * 添加聊天室消息优先级demo
	 *
	 * */
	@Test
	public void testGetChatroomDemotion() throws Exception {
		ChatroomDemotionMsgResult result =rongCloud.chatroom.demotion.getList();
		System.out.println("get demotion:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}

	/**
	 *
	 * 聊天室消息停止分发
	 *
	 */
	@Test
	public void testStopChatroomDistributio() throws Exception {
		ChatroomModel chatroomModel = new ChatroomModel()
				.setId("d7ec7a8b8d8546c98b0973417209a548");
		ResponseResult result = rongCloud.chatroom.distribute.stop(chatroomModel);

		System.out.println("stopDistributionMessage:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}


	/**
	 * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/chatroom/distribute.html#resume
	 *
	 * 聊天室消息恢复分发方法（每秒钟限 100 次）
	 */
	@Test
	public void testResumeChatroomDistributio() throws Exception {

		ChatroomModel chatroomModel = new ChatroomModel()
				.setId("d7ec7a8b8d8546c98b0973417209a548");
		ResponseResult result = rongCloud.chatroom.distribute.resume(chatroomModel);
		System.out.println("resumeDistributionMessage:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}
	/**
	 *
	 * 添加禁言聊天室成员方法想（在 App 中如果不让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，
	 * 被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息.）获取某用户的黑名单列表方法（每秒钟限 100 次）
	 */
	@Test
	public void testAddChatroomGag() throws Exception {
		ChatroomMember[] members = {
				new ChatroomMember().setId("qawr34h"),new ChatroomMember().setId("qawr35h")
		};
		ChatroomModel chatroom = new ChatroomModel()
				.setId("hjhf07kk")
				.setMembers(members)
				.setMinute(5);
		ResponseResult result =  rongCloud.chatroom.gag.add(chatroom);
		System.out.println("addGagUser:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}

	/**
	 *
	 * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/chatroom/gag.html#remove
	 * 查询被禁言聊天室成员方法
	 */
	@Test
	public void testGetChatroomGag() throws Exception {
		ChatroomModel chatroom = new ChatroomModel()
						.setId("hjhf07kk");
		ListGagChatroomUserResult result =  rongCloud.chatroom.gag.getList(chatroom);
		System.out.println("ListGagUser:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}

	/**
	 *
	 * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/chatroom/gag.html#getList
	 *
	 * 移除禁言聊天室成员
	 */
	@Test
	public void testRemoveChatroomGag() throws Exception {
		ChatroomMember[] members = {
				new ChatroomMember().setId("qawr34h"),new ChatroomMember().setId("qawr35h")
		};
		ChatroomModel chatroom = new ChatroomModel()
						.setId("hjhf07kk")
						.setMembers(members);
		ResponseResult result =  rongCloud.chatroom.gag.remove(chatroom);
		System.out.println("rollbackGagUser:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}

	/**
	 * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/chatroom/keepalive.html#add
	 *
	 * 添加保活聊天室
	 *
	 **/
	@Test
	public void testAddChatroomKeepalive() throws Exception {
		ChatroomModel chatroom = new ChatroomModel()
				.setId("d7ec7a8b8d8546c98b0973417209a548");
		ResponseResult result =  rongCloud.chatroom.keepalive.add(chatroom);
		System.out.println("add keepalive result"+result.toString());
		assertEquals("200",result.getCode().toString());
	}

	/**
	 * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/chatroom/keepalive.html#remove
	 *
	 * 删除保活聊天室
	 *
	 **/
	@Test
	public void testRemoveChatroomKeepalive() throws Exception {

		ChatroomModel chatroom = new ChatroomModel()
				.setId("d7ec7a8b8d8546c98b0973417209a548");
		ResponseResult result =  rongCloud.chatroom.keepalive.remove(chatroom);
		System.out.println("keepalive remove"+result.toString());
		assertEquals("200",result.getCode().toString());
	}

	/**
	 *
	 * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/chatroom/keepalive.html#getList
	 *
	 * 获取保活聊天室
	 *
	 **/
	@Test
	public void testGetChatroomKeepalive() throws Exception {

		ChatroomKeepaliveResult result =  rongCloud.chatroom.keepalive.getList();

		System.out.println("keepalive getList"+result.toString());
		assertEquals("200",result.getCode().toString());
	}

	/**
	 *  新添加禁言群成员方法测试
	 */
	@Test
	public void testGroupMuteMembersAdd() throws Exception {

		GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};
		GroupModel group = new GroupModel()
				.setId("appSecret")
				.setMembers(members)
				.setMinute(5);
		Result result = (Result)rongCloud.group.muteMembers.add(group);
		System.out.println("group.gag.add:  " + result.toString());

		assertEquals("200",result.getCode().toString());


	}
	/**
	 *  新查询被禁言群成员方法
	 */
	@Test
	public void testGroupMuteMemberList() throws Exception {
		GroupMuteMembersListResult result = rongCloud.group.muteMembers.getList("25");
		System.out.println("group.gag.getList:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  新移除禁言群成员方法
	 */
	@Test
	public void testGroupRemoveMuteMemberr() throws Exception {
		GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};
		GroupModel group = new GroupModel()
				.setMembers(members)
				.setId("jhgui85hh");

		ResponseResult result = (ResponseResult)rongCloud.group.muteMembers.remove(group);
		System.out.println("group.gag.remove:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
}