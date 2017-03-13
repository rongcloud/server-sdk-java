package io.rong.methods;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

import io.rong.models.*;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import io.rong.util.HostType;

public class Group {

	private static final String UTF8 = "UTF-8";
	private String appKey;
	private String appSecret;
	
	public Group(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}
	
	
	/**
	 * 创建群组方法（创建群组，并将用户加入该群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人，App 内的群组数量没有限制.注：其实本方法是加入群组方法 /group/join 的别名。） 
	 * 
	 * @param  userId:要加入群的用户 Id。（必传）
	 * @param  groupId:创建群组 Id。（必传）
	 * @param  groupName:群组 Id 对应的名称。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult create(String[] userId, String groupId, String groupName) throws Exception {
		if (userId == null) {
			throw new IllegalArgumentException("Paramer 'userId' is required");
		}
		
		if (groupId == null) {
			throw new IllegalArgumentException("Paramer 'groupId' is required");
		}
		
		if (groupName == null) {
			throw new IllegalArgumentException("Paramer 'groupName' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    
	    for (int i = 0 ; i< userId.length; i++) {
			String child  = userId[i];
			sb.append("&userId=").append(URLEncoder.encode(child, UTF8));
		}
		
	    sb.append("&groupId=").append(URLEncoder.encode(groupId.toString(), UTF8));
	    sb.append("&groupName=").append(URLEncoder.encode(groupName.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/group/create.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 同步用户所属群组方法（当第一次连接融云服务器时，需要向融云服务器提交 userId 对应的用户当前所加入的所有群组，此接口主要为防止应用中用户群信息同融云已知的用户所属群信息不同步。） 
	 * 
	 * @param  userId:被同步群信息的用户 Id。（必传）
	 * @param  groupInfo:该用户的群信息，如群 Id 已经存在，则不会刷新对应群组名称，如果想刷新群组名称请调用刷新群组信息方法。
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult sync(String userId, GroupInfo[] groupInfo) throws Exception {
		if (userId == null) {
			throw new IllegalArgumentException("Paramer 'userId' is required");
		}
		
		if (groupInfo == null) {
			throw new IllegalArgumentException("Paramer 'groupInfo' is required");
		}
		
	   	StringBuilder sb = new StringBuilder();
		sb.append("&userId=").append(URLEncoder.encode(userId, UTF8));
		
		for (int i = 0 ; i< groupInfo.length; i++) {
		    GroupInfo child  = groupInfo[i];
			sb.append("&group["+child.getId()+"]=").append(URLEncoder.encode(child.getName(), UTF8));
		}
		
	   	String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
	   	HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/group/sync.json", "application/x-www-form-urlencoded");
	   	HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 刷新群组信息方法 
	 * 
	 * @param  groupId:群组 Id。（必传）
	 * @param  groupName:群名称。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult refresh(String groupId, String groupName) throws Exception {
		if (groupId == null) {
			throw new IllegalArgumentException("Paramer 'groupId' is required");
		}
		
		if (groupName == null) {
			throw new IllegalArgumentException("Paramer 'groupName' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&groupId=").append(URLEncoder.encode(groupId.toString(), UTF8));
	    sb.append("&groupName=").append(URLEncoder.encode(groupName.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/group/refresh.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 将用户加入指定群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人。 
	 * 
	 * @param  userId:要加入群的用户 Id，可提交多个，最多不超过 1000 个。（必传）
	 * @param  groupId:要加入的群 Id。（必传）
	 * @param  groupName:要加入的群 Id 对应的名称。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult join(String[] userId, String groupId, String groupName) throws Exception {
		if (userId == null) {
			throw new IllegalArgumentException("Paramer 'userId' is required");
		}
		
		if (groupId == null) {
			throw new IllegalArgumentException("Paramer 'groupId' is required");
		}
		
		if (groupName == null) {
			throw new IllegalArgumentException("Paramer 'groupName' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    
	    for (int i = 0 ; i< userId.length; i++) {
			String child  = userId[i];
			sb.append("&userId=").append(URLEncoder.encode(child, UTF8));
		}
		
	    sb.append("&groupId=").append(URLEncoder.encode(groupId.toString(), UTF8));
	    sb.append("&groupName=").append(URLEncoder.encode(groupName.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/group/join.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 查询群成员方法 
	 * 
	 * @param  groupId:群组Id。（必传）
	 *
	 * @return GroupUserQueryResult
	 **/
	public GroupUserQueryResult queryUser(String groupId) throws Exception {
		if (groupId == null) {
			throw new IllegalArgumentException("Paramer 'groupId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&groupId=").append(URLEncoder.encode(groupId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/group/user/query.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (GroupUserQueryResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), GroupUserQueryResult.class);
	}
	
	/**
	 * 退出群组方法（将用户从群中移除，不再接收该群组的消息.） 
	 * 
	 * @param  userId:要退出群的用户 Id.（必传）
	 * @param  groupId:要退出的群 Id.（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult quit(String[] userId, String groupId) throws Exception {
		if (userId == null) {
			throw new IllegalArgumentException("Paramer 'userId' is required");
		}
		
		if (groupId == null) {
			throw new IllegalArgumentException("Paramer 'groupId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    
	    for (int i = 0 ; i< userId.length; i++) {
			String child  = userId[i];
			sb.append("&userId=").append(URLEncoder.encode(child, UTF8));
		}
		
	    sb.append("&groupId=").append(URLEncoder.encode(groupId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/group/quit.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 添加禁言群成员方法（在 App 中如果不想让某一用户在群中发言时，可将此用户在群组中禁言，被禁言用户可以接收查看群组中用户聊天信息，但不能发送消息。） 
	 * 
	 * @param  userId:用户 Id。（必传）
	 * @param  groupId:群组 Id。（必传）
	 * @param  minute:禁言时长，以分钟为单位，最大值为43200分钟。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult addGagUser(String userId, String groupId, String minute) throws Exception {
		if (userId == null) {
			throw new IllegalArgumentException("Paramer 'userId' is required");
		}
		
		if (groupId == null) {
			throw new IllegalArgumentException("Paramer 'groupId' is required");
		}
		
		if (minute == null) {
			throw new IllegalArgumentException("Paramer 'minute' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
	    sb.append("&groupId=").append(URLEncoder.encode(groupId.toString(), UTF8));
	    sb.append("&minute=").append(URLEncoder.encode(minute.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/group/user/gag/add.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 查询被禁言群成员方法 
	 * 
	 * @param  groupId:群组Id。（必传）
	 *
	 * @return ListGagGroupUserResult
	 **/
	public ListGagGroupUserResult lisGagUser(String groupId) throws Exception {
		if (groupId == null) {
			throw new IllegalArgumentException("Paramer 'groupId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&groupId=").append(URLEncoder.encode(groupId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/group/user/gag/list.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (ListGagGroupUserResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), ListGagGroupUserResult.class);
	}
	
	/**
	 * 移除禁言群成员方法 
	 * 
	 * @param  userId:用户Id。支持同时移除多个群成员（必传）
	 * @param  groupId:群组Id。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult rollBackGagUser(String[] userId, String groupId) throws Exception {
		if (userId == null) {
			throw new IllegalArgumentException("Paramer 'userId' is required");
		}
		
		if (groupId == null) {
			throw new IllegalArgumentException("Paramer 'groupId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    
	    for (int i = 0 ; i< userId.length; i++) {
			String child  = userId[i];
			sb.append("&userId=").append(URLEncoder.encode(child, UTF8));
		}
		
	    sb.append("&groupId=").append(URLEncoder.encode(groupId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/group/user/gag/rollback.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 解散群组方法。（将该群解散，所有用户都无法再接收该群的消息。） 
	 * 
	 * @param  userId:操作解散群的用户 Id。（必传）
	 * @param  groupId:要解散的群 Id。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult dismiss(String userId, String groupId) throws Exception {
		if (userId == null) {
			throw new IllegalArgumentException("Paramer 'userId' is required");
		}
		
		if (groupId == null) {
			throw new IllegalArgumentException("Paramer 'groupId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
	    sb.append("&groupId=").append(URLEncoder.encode(groupId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/group/dismiss.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}

	 
}