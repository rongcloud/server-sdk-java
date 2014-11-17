import java.util.ArrayList;
import java.util.List;

import com.rongcloud.api.model.ChatroomInfo;
import com.rongcloud.api.model.FormatType;
import com.rongcloud.api.model.GroupInfo;
import com.rongcloud.api.model.ImgMessage;
import com.rongcloud.api.model.SdkHttpResult;
import com.rongcloud.api.model.TxtMessage;
import com.rongcloud.api.model.VoiceMessage;
import com.rongcloud.api.sdk.SdkHttpClient;


public class test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String key ="uwd1c0sxdlx2";
		String secret = "SECRET";
		SdkHttpResult result = SdkHttpClient.getToken(key,secret,"user1","lala","http://aa.com/a.png",FormatType.json);
		System.out.println(result);
		List<String> toIds = new ArrayList<String>();
		toIds.add("id1");
		toIds.add("id2");
		toIds.add("id3");
		result = SdkHttpClient.publishMessage(key, secret, "fromUserId",toIds , new TxtMessage("txtMessagehaha"), FormatType.json);
		System.out.println(result);
		result = SdkHttpClient.publishMessage(key, secret, "fromUserId", toIds, new VoiceMessage("txtMessagehaha",100L), FormatType.json);
		System.out.println(result);
		result = SdkHttpClient.publishMessage(key, secret, "fromUserId", toIds, new ImgMessage("txtMessagehaha","http://aa.com/a.png"), FormatType.json);
		System.out.println(result);
		List<ChatroomInfo> chats = new ArrayList<ChatroomInfo>();
		chats.add(new ChatroomInfo("id","name"));
		result = SdkHttpClient.createChatroom(key, secret, chats, FormatType.json);
		System.out.println(result);
		List<String> chatIds = new ArrayList<String>();
		chatIds.add("id");
		result = SdkHttpClient.queryChatroom(key, secret, chatIds, FormatType.json);
		System.out.println(result);
		result = SdkHttpClient.destroyChatroom(key, secret, chatIds, FormatType.json);
		System.out.println(result);
		List<GroupInfo> groups = new ArrayList<GroupInfo>();
		groups.add(new GroupInfo("id1", "name1"));
		groups.add(new GroupInfo("id2", "name2"));
		groups.add(new GroupInfo("id3", "name3"));
		result = SdkHttpClient.syncGroup(key, secret, "userId1",groups, FormatType.json);
		System.out.println(result);
		result = SdkHttpClient.joinGroup(key, secret, "userId2", "id1", "name1", FormatType.json);
		System.out.println(result);
		result = SdkHttpClient.dismissGroup(key, secret, "userIddismiss", "id1", FormatType.json);
		System.out.println(result);
		result = SdkHttpClient.quitGroup(key, secret, "userId1", "id1", FormatType.json);
		System.out.println(result);
	}
}
