package io.rong.methods;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

import io.rong.models.*;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import io.rong.util.HostType;

public class Chatroom {

	private static final String UTF8 = "UTF-8";
	private String appKey;
	private String appSecret;
	
	public Chatroom(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}
	
	
	/**
	 * 创建聊天室方法 
	 * 
	 * @param  chatRoomInfo:id:要创建的聊天室的id；name:要创建的聊天室的name。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult create(ChatRoomInfo[] chatRoomInfo) throws Exception {
		if (chatRoomInfo == null) {
			throw new IllegalArgumentException("Paramer 'chatRoomInfo' is required");
		}
		
	   	StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i< chatRoomInfo.length; i++) {
			ChatRoomInfo child  = chatRoomInfo[i];
			sb.append("&chatroom["+child.getId()+"]=").append(URLEncoder.encode(child.getName(), UTF8));
		}
		
	   	String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
	   	HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/create.json", "application/x-www-form-urlencoded");
	   	HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 加入聊天室方法 
	 * 
	 * @param  userId:要加入聊天室的用户 Id，可提交多个，最多不超过 50 个。（必传）
	 * @param  chatroomId:要加入的聊天室 Id。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult join(String[] userId, String chatroomId) throws Exception {
		if (userId == null) {
			throw new IllegalArgumentException("Paramer 'userId' is required");
		}
		
		if (chatroomId == null) {
			throw new IllegalArgumentException("Paramer 'chatroomId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    
	    for (int i = 0 ; i< userId.length; i++) {
			String child  = userId[i];
			sb.append("&userId=").append(URLEncoder.encode(child, UTF8));
		}
		
	    sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/join.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 查询聊天室信息方法 
	 * 
	 * @param  chatroomId:要查询的聊天室id（必传）
	 *
	 * @return ChatroomQueryResult
	 **/
	public ChatroomQueryResult query(String[] chatroomId) throws Exception {
		if (chatroomId == null) {
			throw new IllegalArgumentException("Paramer 'chatroomId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    
	    for (int i = 0 ; i< chatroomId.length; i++) {
			String child  = chatroomId[i];
			sb.append("&chatroomId=").append(URLEncoder.encode(child, UTF8));
		}
		
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/query.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (ChatroomQueryResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), ChatroomQueryResult.class);
	}
	
	/**
	 * 查询聊天室内用户方法 
	 * 
	 * @param  chatroomId:要查询的聊天室 ID。（必传）
	 * @param  count:要获取的聊天室成员数，上限为 500 ，超过 500 时最多返回 500 个成员。（必传）
	 * @param  order:加入聊天室的先后顺序， 1 为加入时间正序， 2 为加入时间倒序。（必传）
	 *
	 * @return ChatroomUserQueryResult
	 **/
	public ChatroomUserQueryResult queryUser(String chatroomId, String count, String order) throws Exception {
		if (chatroomId == null) {
			throw new IllegalArgumentException("Paramer 'chatroomId' is required");
		}
		
		if (count == null) {
			throw new IllegalArgumentException("Paramer 'count' is required");
		}
		
		if (order == null) {
			throw new IllegalArgumentException("Paramer 'order' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
	    sb.append("&count=").append(URLEncoder.encode(count.toString(), UTF8));
	    sb.append("&order=").append(URLEncoder.encode(order.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/user/query.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (ChatroomUserQueryResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), ChatroomUserQueryResult.class);
	}
	
	/**
	 * 聊天室消息停止分发方法（可实现控制对聊天室中消息是否进行分发，停止分发后聊天室中用户发送的消息，融云服务端不会再将消息发送给聊天室中其他用户。） 
	 * 
	 * @param  chatroomId:聊天室 Id。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult stopDistributionMessage(String chatroomId) throws Exception {
		if (chatroomId == null) {
			throw new IllegalArgumentException("Paramer 'chatroomId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/message/stopDistribution.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 聊天室消息恢复分发方法 
	 * 
	 * @param  chatroomId:聊天室 Id。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult resumeDistributionMessage(String chatroomId) throws Exception {
		if (chatroomId == null) {
			throw new IllegalArgumentException("Paramer 'chatroomId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/message/resumeDistribution.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 添加禁言聊天室成员方法（在 App 中如果不想让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息.） 
	 * 
	 * @param  userId:用户 Id。（必传）
	 * @param  chatroomId:聊天室 Id。（必传）
	 * @param  minute:禁言时长，以分钟为单位，最大值为43200分钟。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult addGagUser(String userId, String chatroomId, String minute) throws Exception {
		if (userId == null) {
			throw new IllegalArgumentException("Paramer 'userId' is required");
		}
		
		if (chatroomId == null) {
			throw new IllegalArgumentException("Paramer 'chatroomId' is required");
		}
		
		if (minute == null) {
			throw new IllegalArgumentException("Paramer 'minute' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
	    sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
	    sb.append("&minute=").append(URLEncoder.encode(minute.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/user/gag/add.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 查询被禁言聊天室成员方法 
	 * 
	 * @param  chatroomId:聊天室 Id。（必传）
	 *
	 * @return ListGagChatroomUserResult
	 **/
	public ListGagChatroomUserResult ListGagUser(String chatroomId) throws Exception {
		if (chatroomId == null) {
			throw new IllegalArgumentException("Paramer 'chatroomId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/user/gag/list.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (ListGagChatroomUserResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), ListGagChatroomUserResult.class);
	}
	
	/**
	 * 移除禁言聊天室成员方法 
	 * 
	 * @param  userId:用户 Id。（必传）
	 * @param  chatroomId:聊天室Id。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult rollbackGagUser(String userId, String chatroomId) throws Exception {
		if (userId == null) {
			throw new IllegalArgumentException("Paramer 'userId' is required");
		}
		
		if (chatroomId == null) {
			throw new IllegalArgumentException("Paramer 'chatroomId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
	    sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/user/gag/rollback.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 添加封禁聊天室成员方法 
	 * 
	 * @param  userId:用户 Id。（必传）
	 * @param  chatroomId:聊天室 Id。（必传）
	 * @param  minute:封禁时长，以分钟为单位，最大值为43200分钟。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult addBlockUser(String userId, String chatroomId, String minute) throws Exception {
		if (userId == null) {
			throw new IllegalArgumentException("Paramer 'userId' is required");
		}
		
		if (chatroomId == null) {
			throw new IllegalArgumentException("Paramer 'chatroomId' is required");
		}
		
		if (minute == null) {
			throw new IllegalArgumentException("Paramer 'minute' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
	    sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
	    sb.append("&minute=").append(URLEncoder.encode(minute.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/user/block/add.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 查询被封禁聊天室成员方法 
	 * 
	 * @param  chatroomId:聊天室 Id。（必传）
	 *
	 * @return ListBlockChatroomUserResult
	 **/
	public ListBlockChatroomUserResult getListBlockUser(String chatroomId) throws Exception {
		if (chatroomId == null) {
			throw new IllegalArgumentException("Paramer 'chatroomId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/user/block/list.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (ListBlockChatroomUserResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), ListBlockChatroomUserResult.class);
	}
	
	/**
	 * 移除封禁聊天室成员方法 
	 * 
	 * @param  userId:用户 Id。（必传）
	 * @param  chatroomId:聊天室 Id。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult rollbackBlockUser(String userId, String chatroomId) throws Exception {
		if (userId == null) {
			throw new IllegalArgumentException("Paramer 'userId' is required");
		}
		
		if (chatroomId == null) {
			throw new IllegalArgumentException("Paramer 'chatroomId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
	    sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/user/block/rollback.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 添加聊天室消息优先级方法 
	 * 
	 * @param  objectName:低优先级的消息类型，每次最多提交 5 个，设置的消息类型最多不超过 20 个。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult addPriority(String[] objectName) throws Exception {
		if (objectName == null) {
			throw new IllegalArgumentException("Paramer 'objectName' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    
	    for (int i = 0 ; i< objectName.length; i++) {
			String child  = objectName[i];
			sb.append("&objectName=").append(URLEncoder.encode(child, UTF8));
		}
		
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/message/priority/add.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 销毁聊天室方法 
	 * 
	 * @param  chatroomId:要销毁的聊天室 Id。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult destroy(String[] chatroomId) throws Exception {
		if (chatroomId == null) {
			throw new IllegalArgumentException("Paramer 'chatroomId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    
	    for (int i = 0 ; i< chatroomId.length; i++) {
			String child  = chatroomId[i];
			sb.append("&chatroomId=").append(URLEncoder.encode(child, UTF8));
		}
		
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/destroy.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 添加聊天室白名单成员方法 
	 * 
	 * @param  chatroomId:聊天室中用户 Id，可提交多个，聊天室中白名单用户最多不超过 5 个。（必传）
	 * @param  userId:聊天室 Id。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult addWhiteListUser(String chatroomId, String[] userId) throws Exception {
		if (chatroomId == null) {
			throw new IllegalArgumentException("Paramer 'chatroomId' is required");
		}
		
		if (userId == null) {
			throw new IllegalArgumentException("Paramer 'userId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
	    
	    for (int i = 0 ; i< userId.length; i++) {
			String child  = userId[i];
			sb.append("&userId=").append(URLEncoder.encode(child, UTF8));
		}
		
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/user/whitelist/add.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}

	 
}