import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.ApiHttpClient;
import io.rong.models.ChatroomInfo;
import io.rong.models.FormatType;
import io.rong.models.GroupInfo;
import io.rong.models.ImgMessage;
import io.rong.models.SdkHttpResult;
import io.rong.models.TxtMessage;
import io.rong.models.VoiceMessage;
import io.rong.models.*;
/**
 * 一些api的调用示例
 */
public class Example {

	/**
	 * 本地调用测试
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String key = "app-key";//替换成您的appkey
		String secret = "appsecrect";//替换成匹配上面key的secret

		SdkHttpResult result = null;

		//获取token
		result = ApiHttpClient.getToken(key, secret, "402880ef4a", "asdfa",
				"http://aa.com/a.png", FormatType.json);
		System.out.println("gettoken=" + result);
		
		//发消息(push内容为消息内容)
		List<String> toIds = new ArrayList<String>();
		toIds.add("id1");
		toIds.add("id2");
		toIds.add("id3");
		result = ApiHttpClient.publishMessage(key, secret, "fromUserId", toIds,
				new TxtMessage("txtMessagehaha"), FormatType.json);
		System.out.println("publishMessage=" + result);
		
		//发消息 语音类型
		result = ApiHttpClient.publishMessage(key, secret, "fromUserId", toIds,
				new VoiceMessage("txtMessagehaha", 100L), FormatType.json);
		System.out.println("publishMessage=" + result);
		
		//发消息 图片类型
		result = ApiHttpClient.publishMessage(key, secret, "fromUserId", toIds,
				new ImgMessage("txtMessagehaha", "http://aa.com/a.png"),
				FormatType.json);
		System.out.println("publishMessage=" + result);
		
		//发消息(push内容为填写内容)
		result = ApiHttpClient.publishMessage(key, secret, "fromUserId", toIds,
				new TxtMessage("txtMessagehaha"), "pushContent", "pushData",
				FormatType.json);
		System.out.println("publishMessageAddpush=" + result);
		
		//发送系统消息
		result = ApiHttpClient.publishSystemMessage(key, secret, "fromUserId",
				toIds, new TxtMessage("txtMessagehaha"), "pushContent",
				"pushData", FormatType.json);
		System.out.println("publishSystemMessage=" + result);
		
		//创建聊天室
		List<ChatroomInfo> chats = new ArrayList<ChatroomInfo>();
		chats.add(new ChatroomInfo("idtest", "name"));
		chats.add(new ChatroomInfo("id%s+-{}{#[]", "name12312"));
		result = ApiHttpClient.createChatroom(key, secret, chats,
				FormatType.json);
		System.out.println("createchatroom=" + result);
		List<String> chatIds = new ArrayList<String>();
		chatIds.add("id");
		chatIds.add("id%+-:{}{#[]");
		result = ApiHttpClient.queryChatroom(key, secret, chatIds,
				FormatType.json);
		System.out.println("queryChatroom=" + result);
		
		//发送聊天室消息
		result = ApiHttpClient.publishChatroomMessage(key, secret,
				"fromUserId", chatIds, new TxtMessage("txtMessagehaha"),
				FormatType.json);
		System.out.println("publishChatroomMessage=" + result);
		
		//销毁聊天室
		result = ApiHttpClient.destroyChatroom(key, secret, chatIds,
				FormatType.json);
		System.out.println("destroyChatroom=" + result);
		List<GroupInfo> groups = new ArrayList<GroupInfo>();
		groups.add(new GroupInfo("id1", "name1"));
		groups.add(new GroupInfo("id2", "name2"));
		groups.add(new GroupInfo("id3", "name3"));
		result = ApiHttpClient.syncGroup(key, secret, "userId1", groups,
				FormatType.json);
		System.out.println("syncGroup=" + result);
		
		//加入群
		result = ApiHttpClient.joinGroup(key, secret, "userId2", "id1",
				"name1", FormatType.json);
		System.out.println("joinGroup=" + result);
		
		//批量加入群
		List<String> list = new ArrayList<String>();
		list.add("userId3");
		list.add("userId1");
		list.add("userId3");
		list.add("userId2");
		list.add("userId3");
		list.add("userId3");
		result = ApiHttpClient.joinGroupBatch(key, secret, list, "id1",
				"name1", FormatType.json);
		System.out.println("joinGroupBatch=" + result);
		
		//发送群消息
		result = ApiHttpClient.publishGroupMessage(key, secret, "userId1",
				chatIds, new TxtMessage("txtMessagehaha"), "pushContent",
				"pushData", FormatType.json);
		System.out.println("publishGroupMessage=" + result);
		
		//退出群
		result = ApiHttpClient.quitGroup(key, secret, "userId1", "id1",
				FormatType.json);
		System.out.println("quitGroup=" + result);
		
		//批量退出群
		result = ApiHttpClient.quitGroupBatch(key, secret, list, "id1",
				FormatType.json);
		System.out.println("quitGroupBatch=" + result);
		
		//解散群
		result = ApiHttpClient.dismissGroup(key, secret, "userIddismiss",
				"id1", FormatType.json);
		
		//获取消息历史记录下载地址
		result = ApiHttpClient.getMessageHistoryUrl(key, secret, "2014112811",
				FormatType.json);
		System.out.println("getMessageHistoryUrl=" + result);
		
		//删除历史记录(只是删除了历史记录，不会删除消息)
		result = ApiHttpClient.deleteMessageHistory(key, secret, "2014122811",
		FormatType.json);
		System.out.println("deleteMessageHistory=" + result);
		
		//封禁用户相关操作**********begin**********封禁用户相关操作//
		result = ApiHttpClient.blockUser(key, secret, "2", 10,FormatType.json);
		System.out.println("blockUser=" + result);
		//封禁用户
		result = ApiHttpClient.blockUser(key, secret, "id2", 10,FormatType.json);
		System.out.println("blockUser=" + result);
		result = ApiHttpClient.blockUser(key, secret, "id3", 10,FormatType.json);
		System.out.println("blockUser=" + result);
		//查询封禁用户
		result = ApiHttpClient.queryBlockUsers(key, secret, FormatType.json);
		System.out.println("queryBlockUsers=" + result);
		//解除封禁
		result = ApiHttpClient.unblockUser(key, secret, "id1", FormatType.json);
		System.out.println("unblockUser=" + result);
		result = ApiHttpClient.queryBlockUsers(key, secret, FormatType.json);
		System.out.println("queryBlockUsers=" + result);
		result = ApiHttpClient.unblockUser(key, secret, "id2", FormatType.json);
		System.out.println("unblockUser=" + result);
		result = ApiHttpClient.unblockUser(key, secret, "id3", FormatType.json);
		System.out.println("unblockUser=" + result);
		result = ApiHttpClient.queryBlockUsers(key, secret, FormatType.json);
		System.out.println("queryBlockUsers=" + result);
		//封禁用户相关操作**********end**********封禁用户相关操作//
		
		//检查用户在线状态
		result = ApiHttpClient.checkOnline(key, secret, "143", FormatType.json);
		System.out.println("checkOnline=" + result);
		
		//添加黑名单
		List<String> toBlackIds = new ArrayList<String>();
		toBlackIds.add("22");
		toBlackIds.add("12");
		result = ApiHttpClient.blackUser(key, secret, "3706", toBlackIds,
				FormatType.json);
		System.out.println("blackUser=" + result);
		
		//查询黑名单
		result = ApiHttpClient.QueryblackUser(key, secret, "3706",FormatType.json);
		System.out.println("QueryblackUser=" + result);
		
		//解除黑名单
		result = ApiHttpClient.unblackUser(key, secret, "3706", toBlackIds,
				FormatType.json);
		System.out.println("unblackUser=" + result);
		
		result = ApiHttpClient.QueryblackUser(key, secret, "3706",FormatType.json);
		System.out.println("QueryblackUser=" + result);	
		
		//刷新群信息
		result = ApiHttpClient.refreshGroupInfo(key, secret, "id1", "name4",
				FormatType.json);
		System.out.println("refreshGroupInfo=" + result);
		
		PushMessage message = createPushMessage();//融云消息
		//PushMessage message = createPushMessage2();//不落地push
		result = ApiHttpClient.push(key, secret, message, FormatType.json);
		System.out.println("push=" + result);
		//给用户打标签
		UserTag tag = new UserTag();
		tag.setTags(new String[] { "a", "b" });
		tag.setUserId("userId11111");
		result = ApiHttpClient.setUserTag(key, secret, tag, FormatType.json);
		System.out.println("tag=" + result);

	}

	/**
	 * 创建发送落地消息的内容
	 */
	private static PushMessage createPushMessage()
			throws UnsupportedEncodingException {
		List<String> osList = new ArrayList<>();
		osList.add("ios");
		osList.add("android");

		TagObj tag = new TagObj();
		tag.setIs_to_all(false);//给全量用户发 设置为true则下面标签和userids将无效
		
		//给打标签的发则设置标签
		List<String> tagas = new ArrayList<String>();
		tagas.add("a");
		tagas.add("b");
		tagas.add("3");
		tag.setTag(tagas);
		
		//给特定用户ID发,优先级高于上面的标签
		List<String> tagus = new ArrayList<String>();
		tagus.add("123");
		tagus.add("456");
		tag.setUserid(tagus);
		
		PushMessage pmsg = new PushMessage();
		pmsg.setPlatform(osList);
		PushNotification noti = new PushNotification();
		noti.setAlert("ddd");
		noti.setAndroid(createPush());
		noti.setIos(createPush());
		pmsg.setNotification(noti);
		
		//下面两个参数的有无 控制发送的落地消息还是不落地的push
		//PushMessage实体中的 fromuserid 和 message为null即为不落地的push
		pmsg.setFromuserid("fromuseId1");
		MsgObj msg = new MsgObj();
		TxtMessage message = new TxtMessage("hello", "one extra");
		msg.setContent(message.toString());
		msg.setObjectName(message.getType());
		pmsg.setMessage(msg);
		//上面两个参数的有无 控制发送的落地消息还是不落地的push
		
		pmsg.setAudience(tag);
		System.out.println(pmsg.toString());
		return pmsg;
	}
	
	/**
	 * 创建发送不落地的push内容
	 */
	private static PushMessage createPushMessage2()
			throws UnsupportedEncodingException {
		List<String> osList = new ArrayList<>();
		osList.add("ios");
		osList.add("android");

		TagObj tag = new TagObj();
		tag.setIs_to_all(false);//给全量用户发 设置为true则下面标签和userids将无效
		
		//给打标签的发则设置标签
		List<String> tagas = new ArrayList<String>();
		tagas.add("a");
		tagas.add("b");
		tagas.add("3");
		tag.setTag(tagas);
		
		//给特定用户ID发,优先级高于上面的标签
		List<String> tagus = new ArrayList<String>();
		tagus.add("123");
		tagus.add("456");
		tag.setUserid(tagus);
		
		PushMessage pmsg = new PushMessage();
		pmsg.setPlatform(osList);
		PushNotification noti = new PushNotification();
		noti.setAlert("ddd");
		noti.setAndroid(createPush());
		noti.setIos(createPush());
		pmsg.setNotification(noti);
		
		pmsg.setAudience(tag);
		System.out.println(pmsg.toString());
		return pmsg;
	}

	private static PlatformNotification createPush() {
		PlatformNotification data = new PlatformNotification();
		data.setAlert("override alert");
		Map<String, String> map = new HashMap<>();
		map.put("id", "1");
		map.put("name", "2");
		data.setExtras(map);
		return data;
	}
}
