package io.rong;

import io.rong.models.ChatroomInfo;
import io.rong.models.FormatType;
import io.rong.models.GroupInfo;
import io.rong.models.Message;
import io.rong.models.SdkHttpResult;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.List;

public class ApiHttpClient {

	private static final String RONGCLOUDURI = "https://api.cn.rong.io";
	private static final String UTF8 = "UTF-8";
	
	//获取token
	public static SdkHttpResult getToken(String appKey, String appSecret,
			String userId, String userName, String portraitUri,
			FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, appSecret,
				RONGCLOUDURI + "/user/getToken." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&name=").append(URLEncoder.encode(userName, UTF8));
		sb.append("&portraitUri=").append(URLEncoder.encode(portraitUri, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}	
	//加入群
	public static SdkHttpResult joinGroup(String appKey, String appSecret,
			String userId, String groupId, String groupName, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, appSecret,
				RONGCLOUDURI + "/group/join." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&groupId=").append(URLEncoder.encode(groupId, UTF8));
		sb.append("&groupName=").append(URLEncoder.encode(groupName, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}
	//退出群
	public static SdkHttpResult quitGroup(String appKey, String appSecret,
			String userId, String groupId, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, appSecret,
				RONGCLOUDURI + "/group/quit." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&groupId=").append(URLEncoder.encode(groupId, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}
	//解散群
	public static SdkHttpResult dismissGroup(String appKey, String appSecret,
			String userId, String groupId, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, appSecret,
				RONGCLOUDURI + "/group/dismiss." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&groupId=").append(URLEncoder.encode(groupId, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}
	//同步用户群信息
	public static SdkHttpResult syncGroup(String appKey, String appSecret,
			String userId, List<GroupInfo> groups, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, appSecret,
				RONGCLOUDURI + "/group/sync." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		if (groups != null) {
			for (GroupInfo info : groups) {
				if (info != null) {
					sb.append(
							String.format("&group[%s]=",
									URLEncoder.encode(info.getId(), UTF8)))
							.append(URLEncoder.encode(info.getName(), UTF8));
				}
			}
		}
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}
	//发送消息
	public static SdkHttpResult publishMessage(String appKey, String appSecret,
			String fromUserId, List<String> toUserIds, Message msg, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, appSecret,
				RONGCLOUDURI + "/message/publish." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
		if(toUserIds != null){
			for(int i=0;i<toUserIds.size();i++){
				sb.append("&toUserId=").append(URLEncoder.encode(toUserIds.get(i), UTF8));
			}
		}		
		sb.append("&objectName=").append(URLEncoder.encode(msg.getType(), UTF8));
		sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}
	//创建聊天室
	public static SdkHttpResult createChatroom(String appKey, String appSecret,
			List<ChatroomInfo> chatrooms, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, appSecret,
				RONGCLOUDURI + "/chatroom/create." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("1=1");
		if (chatrooms != null) {
			for (ChatroomInfo info : chatrooms) {
				if (info != null) {
					sb.append(
							String.format("&chatroom[%s]=",
									URLEncoder.encode(info.getId(), UTF8)))
							.append(URLEncoder.encode(info.getName(), UTF8));
				}
			}
		}
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}
	//销毁聊天室
	public static SdkHttpResult destroyChatroom(String appKey, String appSecret,
			List<String> chatroomIds, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, appSecret,
				RONGCLOUDURI + "/chatroom/destroy." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("1=1");
		if (chatroomIds != null) {
			for (String id : chatroomIds) {
				sb.append("&chatroomId=").append(URLEncoder.encode(id, UTF8));
			}
		}

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}
	//查询聊天室信息
	public static SdkHttpResult queryChatroom(String appKey, String appSecret,
			List<String> chatroomIds, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, appSecret,
				RONGCLOUDURI + "/chatroom/query." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("1=1");
		if (chatroomIds != null) {
			for (String id : chatroomIds) {
				sb.append("&chatroomId=").append(URLEncoder.encode(id, UTF8));
			}
		}

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}
}
