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

	private static final String RONGCLOUDURI = "http://api.cn.ronghub.com";
	
	private static final String UTF8 = "UTF-8";

	// 获取token
	public static SdkHttpResult getToken(String appKey, String appSecret,
			String userId, String userName, String portraitUri,
			FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil
				.CreatePostHttpConnection(appKey, appSecret, RONGCLOUDURI
						+ "/user/getToken." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&name=").append(URLEncoder.encode(userName, UTF8));
		sb.append("&portraitUri=").append(URLEncoder.encode(portraitUri, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 检查用户在线状态
	public static SdkHttpResult checkOnline(String appKey, String appSecret,
			String userId, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret,
				RONGCLOUDURI + "/user/checkOnline." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 刷新用户信息
	public static SdkHttpResult refreshUser(String appKey, String appSecret,
			String userId, String userName, String portraitUri,
			FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, RONGCLOUDURI + "/user/refresh." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&name=").append(URLEncoder.encode(userName, UTF8));
		sb.append("&portraitUri=").append(URLEncoder.encode(portraitUri, UTF8));

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 封禁用户
	public static SdkHttpResult blockUser(String appKey, String appSecret,
			String userId, int minute, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, RONGCLOUDURI + "/user/block." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&minute=").append(
				URLEncoder.encode(String.valueOf(minute), UTF8));

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 解禁用户
	public static SdkHttpResult unblockUser(String appKey, String appSecret,
			String userId, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, RONGCLOUDURI + "/user/unblock." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 获取被封禁用户
	public static SdkHttpResult queryBlockUsers(String appKey,
			String appSecret, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret,
				RONGCLOUDURI + "/user/block/query." + format.toString());

		return HttpUtil.returnResult(conn);
	}

	// 添加用户到黑名单
	public static SdkHttpResult blackUser(String appKey, String appSecret,
			String userId, List<String> blackUserIds, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret,
				RONGCLOUDURI + "/user/blacklist/add." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		if (blackUserIds != null) {
			for (String blackId : blackUserIds) {
				sb.append("&blackUserId=").append(
						URLEncoder.encode(blackId, UTF8));
			}
		}

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 从黑名单移除用户
	public static SdkHttpResult unblackUser(String appKey, String appSecret,
			String userId, List<String> blackUserIds, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret,
				RONGCLOUDURI + "/user/blacklist/remove." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		if (blackUserIds != null) {
			for (String blackId : blackUserIds) {
				sb.append("&blackUserId=").append(
						URLEncoder.encode(blackId, UTF8));
			}
		}

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 获取黑名单用户
	public static SdkHttpResult QueryblackUser(String appKey, String appSecret,
			String userId, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret,
				RONGCLOUDURI + "/user/blacklist/query." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 创建群
	public static SdkHttpResult createGroup(String appKey, String appSecret,
			List<String> userIds, String groupId, String groupName,
			FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, RONGCLOUDURI + "/group/create." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
		sb.append("&groupName=").append(URLEncoder.encode(groupName, UTF8));
		if (userIds != null) {
			for (String id : userIds) {
				sb.append("&userId=").append(URLEncoder.encode(id, UTF8));
			}
		}
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 加入群
	public static SdkHttpResult joinGroup(String appKey, String appSecret,
			String userId, String groupId, String groupName, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, RONGCLOUDURI + "/group/join." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&groupId=").append(URLEncoder.encode(groupId, UTF8));
		sb.append("&groupName=").append(URLEncoder.encode(groupName, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 批量加入群
	public static SdkHttpResult joinGroupBatch(String appKey, String appSecret,
			List<String> userIds, String groupId, String groupName,
			FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, RONGCLOUDURI + "/group/join." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
		sb.append("&groupName=").append(URLEncoder.encode(groupName, UTF8));
		if (userIds != null) {
			for (String id : userIds) {
				sb.append("&userId=").append(URLEncoder.encode(id, UTF8));
			}
		}
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 退出群
	public static SdkHttpResult quitGroup(String appKey, String appSecret,
			String userId, String groupId, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, RONGCLOUDURI + "/group/quit." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&groupId=").append(URLEncoder.encode(groupId, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 批量退出群
	public static SdkHttpResult quitGroupBatch(String appKey, String appSecret,
			List<String> userIds, String groupId, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, RONGCLOUDURI + "/group/quit." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
		if (userIds != null) {
			for (String id : userIds) {
				sb.append("&userId=").append(URLEncoder.encode(id, UTF8));
			}
		}

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 解散群
	public static SdkHttpResult dismissGroup(String appKey, String appSecret,
			String userId, String groupId, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil
				.CreatePostHttpConnection(appKey, appSecret, RONGCLOUDURI
						+ "/group/dismiss." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&groupId=").append(URLEncoder.encode(groupId, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 同步用户群信息
	public static SdkHttpResult syncGroup(String appKey, String appSecret,
			String userId, List<GroupInfo> groups, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, RONGCLOUDURI + "/group/sync." + format.toString());

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

	// 刷新群信息
	public static SdkHttpResult refreshGroupInfo(String appKey,
			String appSecret, String groupId, String groupName,
			FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil
				.CreatePostHttpConnection(appKey, appSecret, RONGCLOUDURI
						+ "/group/refresh." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
		sb.append("&groupName=").append(URLEncoder.encode(groupName, UTF8));

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 刷新群信息
	public static SdkHttpResult refreshGroupInfo(String appKey,
			String appSecret, GroupInfo group, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil
				.CreatePostHttpConnection(appKey, appSecret, RONGCLOUDURI
						+ "/group/refresh." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("groupId=").append(URLEncoder.encode(group.getId(), UTF8));
		sb.append("&groupName=").append(
				URLEncoder.encode(group.getName(), UTF8));

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 发送消息
	public static SdkHttpResult publishMessage(String appKey, String appSecret,
			String fromUserId, List<String> toUserIds, Message msg,
			FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret,
				RONGCLOUDURI + "/message/private/publish." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
		if (toUserIds != null) {
			for (int i = 0; i < toUserIds.size(); i++) {
				sb.append("&toUserId=").append(
						URLEncoder.encode(toUserIds.get(i), UTF8));
			}
		}
		sb.append("&objectName=")
				.append(URLEncoder.encode(msg.getType(), UTF8));
		sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 发送消息
	public static SdkHttpResult publishMessage(String appKey, String appSecret,
			String fromUserId, List<String> toUserIds, Message msg,
			String pushContent, String pushData, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret,
				RONGCLOUDURI + "/message/publish." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
		if (toUserIds != null) {
			for (int i = 0; i < toUserIds.size(); i++) {
				sb.append("&toUserId=").append(
						URLEncoder.encode(toUserIds.get(i), UTF8));
			}
		}
		sb.append("&objectName=")
				.append(URLEncoder.encode(msg.getType(), UTF8));
		sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

		if (pushContent != null) {
			sb.append("&pushContent=").append(
					URLEncoder.encode(pushContent, UTF8));
		}

		if (pushData != null) {
			sb.append("&pushData=").append(URLEncoder.encode(pushData, UTF8));
		}

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 发送系统消息
	public static SdkHttpResult publishSystemMessage(String appKey,
			String appSecret, String fromUserId, List<String> toUserIds,
			Message msg, String pushContent, String pushData, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret,
				RONGCLOUDURI + "/message/system/publish." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
		if (toUserIds != null) {
			for (int i = 0; i < toUserIds.size(); i++) {
				sb.append("&toUserId=").append(
						URLEncoder.encode(toUserIds.get(i), UTF8));
			}
		}
		sb.append("&objectName=")
				.append(URLEncoder.encode(msg.getType(), UTF8));
		sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

		if (pushContent != null) {
			sb.append("&pushContent=").append(
					URLEncoder.encode(pushContent, UTF8));
		}

		if (pushData != null) {
			sb.append("&pushData=").append(URLEncoder.encode(pushData, UTF8));
		}

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 发送群消息
	public static SdkHttpResult publishGroupMessage(String appKey,
			String appSecret, String fromUserId, List<String> toGroupIds,
			Message msg, String pushContent, String pushData, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret,
				RONGCLOUDURI + "/message/group/publish." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
		if (toGroupIds != null) {
			for (int i = 0; i < toGroupIds.size(); i++) {
				sb.append("&toGroupId=").append(
						URLEncoder.encode(toGroupIds.get(i), UTF8));
			}
		}
		sb.append("&objectName=")
				.append(URLEncoder.encode(msg.getType(), UTF8));
		sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

		if (pushContent != null) {
			sb.append("&pushContent=").append(
					URLEncoder.encode(pushContent, UTF8));
		}

		if (pushData != null) {
			sb.append("&pushData=").append(URLEncoder.encode(pushData, UTF8));
		}

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 发送聊天室消息
	public static SdkHttpResult publishChatroomMessage(String appKey,
			String appSecret, String fromUserId, List<String> toChatroomIds,
			Message msg, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil
				.CreatePostHttpConnection(appKey, appSecret, RONGCLOUDURI
						+ "/message/chatroom/publish." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
		if (toChatroomIds != null) {
			for (int i = 0; i < toChatroomIds.size(); i++) {
				sb.append("&toChatroomId=").append(
						URLEncoder.encode(toChatroomIds.get(i), UTF8));
			}
		}
		sb.append("&objectName=")
				.append(URLEncoder.encode(msg.getType(), UTF8));
		sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}
	
	public static SdkHttpResult broadcastMessage(String appKey, String appSecret,
			String fromUserId, Message msg,String pushContent, String pushData, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret,
				RONGCLOUDURI + "/message/broadcast." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));		
		sb.append("&objectName=")
				.append(URLEncoder.encode(msg.getType(), UTF8));
		sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));
		if (pushContent != null) {
			sb.append("&pushContent=").append(
					URLEncoder.encode(pushContent, UTF8));
		}

		if (pushData != null) {
			sb.append("&pushData=").append(URLEncoder.encode(pushData, UTF8));
		}

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 创建聊天室
	public static SdkHttpResult createChatroom(String appKey, String appSecret,
			List<ChatroomInfo> chatrooms, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret,
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

	// 销毁聊天室
	public static SdkHttpResult destroyChatroom(String appKey,
			String appSecret, List<String> chatroomIds, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret,
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

	// 查询聊天室信息
	public static SdkHttpResult queryChatroom(String appKey, String appSecret,
			List<String> chatroomIds, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret,
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

	// 获取消息历史记录下载地址
	public static SdkHttpResult getMessageHistoryUrl(String appKey,
			String appSecret, String date, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret,
				RONGCLOUDURI + "/message/history." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("date=").append(URLEncoder.encode(date, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 删除消息历史记录
	public static SdkHttpResult deleteMessageHistory(String appKey,
			String appSecret, String date, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret,
				RONGCLOUDURI + "/message/history/delete." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("date=").append(URLEncoder.encode(date, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}
}
