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

public class Example {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String key = "app-key";
		String secret = "appsecrect";

		SdkHttpResult result = null;

		result = ApiHttpClient.getToken(key, secret, "402880ef4a", "asdfa",
				"http://aa.com/a.png", FormatType.json);
		System.out.println("gettoken=" + result);
		List<String> toIds = new ArrayList<String>();
		toIds.add("id1");
		toIds.add("id2");
		toIds.add("id3");
		result = ApiHttpClient.publishMessage(key, secret, "fromUserId", toIds,
				new TxtMessage("txtMessagehaha"), FormatType.json);
		System.out.println("publishMessage=" + result);
		result = ApiHttpClient.publishMessage(key, secret, "fromUserId", toIds,
				new VoiceMessage("txtMessagehaha", 100L), FormatType.json);
		System.out.println("publishMessage=" + result);
		result = ApiHttpClient.publishMessage(key, secret, "fromUserId", toIds,
				new ImgMessage("txtMessagehaha", "http://aa.com/a.png"),
				FormatType.json);
		System.out.println("publishMessage=" + result);

		result = ApiHttpClient.publishMessage(key, secret, "fromUserId", toIds,
				new TxtMessage("txtMessagehaha"), "pushContent", "pushData",
				FormatType.json);
		System.out.println("publishMessageAddpush=" + result);

		result = ApiHttpClient.publishSystemMessage(key, secret, "fromUserId",
				toIds, new TxtMessage("txtMessagehaha"), "pushContent",
				"pushData", FormatType.json);
		System.out.println("publishSystemMessage=" + result);

		result = ApiHttpClient.publishSystemMessage(key, secret, "fromUserId",
				toIds, new TxtMessage("txtMessagehaha"), "pushContent",
				"pushData", FormatType.json);
		System.out.println("publishSystemMessage=" + result);

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

		result = ApiHttpClient.publishChatroomMessage(key, secret,
				"fromUserId", chatIds, new TxtMessage("txtMessagehaha"),
				FormatType.json);
		System.out.println("publishChatroomMessage=" + result);

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
		result = ApiHttpClient.joinGroup(key, secret, "userId2", "id1",
				"name1", FormatType.json);
		System.out.println("joinGroup=" + result);
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

		result = ApiHttpClient.publishGroupMessage(key, secret, "userId1",
				chatIds, new TxtMessage("txtMessagehaha"), "pushContent",
				"pushData", FormatType.json);
		System.out.println("publishGroupMessage=" + result);

		result = ApiHttpClient.quitGroup(key, secret, "userId1", "id1",
				FormatType.json);
		System.out.println("quitGroup=" + result);
		result = ApiHttpClient.quitGroupBatch(key, secret, list, "id1",
				FormatType.json);
		System.out.println("quitGroupBatch=" + result);
		result = ApiHttpClient.dismissGroup(key, secret, "userIddismiss",
				"id1", FormatType.json);
		result = ApiHttpClient.getMessageHistoryUrl(key, secret, "2014112811",
				FormatType.json);
		System.out.println("getMessageHistoryUrl=" + result);

		result = ApiHttpClient.blockUser(key, secret, "2", 10,FormatType.json);
		System.out.println("blockUser=" + result);

		result = ApiHttpClient.blockUser(key, secret, "id2", 10,FormatType.json);
		System.out.println("blockUser=" + result);

		result = ApiHttpClient.blockUser(key, secret, "id3", 10,FormatType.json);
		System.out.println("blockUser=" + result);

		result = ApiHttpClient.queryBlockUsers(key, secret, FormatType.json);
		System.out.println("queryBlockUsers=" + result);

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

		result = ApiHttpClient.checkOnline(key, secret, "143", FormatType.json);
		System.out.println("checkOnline=" + result);
		
		List<String> toBlackIds = new ArrayList<String>();
		toBlackIds.add("22");
		toBlackIds.add("12");

		result = ApiHttpClient.blackUser(key, secret, "3706", toBlackIds,
				FormatType.json);
		System.out.println("blackUser=" + result);
		
		result = ApiHttpClient.QueryblackUser(key, secret, "3706",FormatType.json);
		System.out.println("QueryblackUser=" + result);
		
		result = ApiHttpClient.unblackUser(key, secret, "3706", toBlackIds,
				FormatType.json);
		System.out.println("unblackUser=" + result);
		
		result = ApiHttpClient.QueryblackUser(key, secret, "3706",FormatType.json);
		System.out.println("QueryblackUser=" + result);	

		result = ApiHttpClient.deleteMessageHistory(key, secret, "2014122811",
				FormatType.json);
		System.out.println("deleteMessageHistory=" + result);
		
		result = ApiHttpClient.refreshGroupInfo(key, secret, "id1", "name4",
				FormatType.json);
		System.out.println("refreshGroupInfo=" + result);
		PushMessage message = createPushMessage();
		result = ApiHttpClient.push(key, secret, message, FormatType.json);
		System.out.println("push=" + result);
		UserTag tag = new UserTag();
		tag.setTags(new String[] { "a", "b" });
		tag.setUserId("userId11111");
		result = ApiHttpClient.setUserTag(key, secret, tag, FormatType.json);
		System.out.println("tag=" + result);

	}

	private static PushMessage createPushMessage()
			throws UnsupportedEncodingException {
		List<String> osList = new ArrayList<>();
		osList.add("ios");
		osList.add("android");

		TagObj tag = new TagObj();
		tag.setIs_to_all(false);
		List<String> tagas = new ArrayList<String>();

		tagas.add("a");
		tagas.add("b");
		tagas.add("3");
		tag.setTag(tagas);
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
		pmsg.setFromuserid("fromuseId1");

		MsgObj msg = new MsgObj();
		TxtMessage message = new TxtMessage("hello", "one extra");
		msg.setContent(message.toString());
		msg.setObjectName(message.getType());
		pmsg.setMessage(msg);
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
