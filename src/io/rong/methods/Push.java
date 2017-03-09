package io.rong.methods;

import java.net.HttpURLConnection;
import java.net.Proxy;

import io.rong.models.*;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import io.rong.util.HostType;

public class Push {

	private String appKey;
	private String appSecret;
	private Proxy proxy;

	public Push(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}
	public Push(String appKey, String appSecret,Proxy proxy) {
		this.appKey = appKey;
		this.appSecret = appSecret;
        this.proxy = proxy;
	}
	
	
	/**
	 * 添加 Push 标签方法 
	 * 
	 * @param  userTag:用户标签。
	 *
	 * @return CodeSuccessReslut
	 **/
	public CodeSuccessReslut setUserPushTag(UserTag userTag) throws Exception {
		if (userTag == null) {
			throw new IllegalArgumentException("Paramer 'userTag' is required");
		}
		
	    HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/user/tag/set.json", "application/json",proxy);
	    HttpUtil.setBodyParameter(userTag.toString(), conn);
	    
	    return (CodeSuccessReslut) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessReslut.class);
	}
	
	/**
	 * 广播消息方法（fromuserid 和 message为null即为不落地的push） 
	 * 
	 * @param  pushMessage:json数据
	 *
	 * @return CodeSuccessReslut
	 **/
	public CodeSuccessReslut broadcastPush(PushMessage pushMessage) throws Exception {
		if (pushMessage == null) {
			throw new IllegalArgumentException("Paramer 'pushMessage' is required");
		}
		
	    HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/push.json", "application/json",proxy);
	    HttpUtil.setBodyParameter(pushMessage.toString(), conn);
	    
	    return (CodeSuccessReslut) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessReslut.class);
	}

	 
}