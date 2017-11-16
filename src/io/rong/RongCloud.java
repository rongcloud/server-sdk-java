/**
 * 融云 Server API java 客户端
 * create by kitName
 * create datetime : 2017-06-05
 * <p>
 * v2.0.1
 */
package io.rong;

import java.util.concurrent.ConcurrentHashMap;

import io.rong.methods.*;
import io.rong.util.HostType;

public class RongCloud {

	private static ConcurrentHashMap<String, RongCloud> rongCloud = new ConcurrentHashMap<String, RongCloud>();

	public User user;
	public Message message;
	public Wordfilter wordfilter;
	public Sensitiveword sensitiveword;
	public Group group;
	public Chatroom chatroom;
	public Push push;
	public SMS sms;
	private HostType apiHostType = new HostType("http://api.cn.ronghub.com");
	private HostType smsHostType = new HostType("http://api.sms.ronghub.com");

	public HostType getApiHostType() {
		return apiHostType;
	}

	public void setApiHostType(HostType apiHostType) {
		this.apiHostType = apiHostType;
	}

	public HostType getSmsHostType() {
		return smsHostType;
	}

	public void setSmsHostType(HostType smsHostType) {
		this.smsHostType = smsHostType;
	}

	private RongCloud(String appKey, String appSecret) {
		user = new User(appKey, appSecret);
		user.setRongCloud(this);
		message = new Message(appKey, appSecret);
		message.setRongCloud(this);
		wordfilter = new Wordfilter(appKey, appSecret);
		wordfilter.setRongCloud(this);
		sensitiveword = new Sensitiveword(appKey, appSecret);
		sensitiveword.setRongCloud(this);
		group = new Group(appKey, appSecret);
		group.setRongCloud(this);
		chatroom = new Chatroom(appKey, appSecret);
		chatroom.setRongCloud(this);
		push = new Push(appKey, appSecret);
		push.setRongCloud(this);
		sms = new SMS(appKey, appSecret);
		sms.setRongCloud(this);

	}

	public static RongCloud getInstance(String appKey, String appSecret) {
		if (null == rongCloud.get(appKey)) {
			rongCloud.putIfAbsent(appKey, new RongCloud(appKey, appSecret));
		}
		return rongCloud.get(appKey);
	}

	public static RongCloud getInstance(String appKey, String appSecret,String api) {
		if (null == rongCloud.get(appKey)) {
			RongCloud rc =  	new RongCloud(appKey, appSecret);
			if(api!=null && api.trim().length()>0)
			rc.setApiHostType(new HostType(api));
			rongCloud.putIfAbsent(appKey,rc );
		}
		return rongCloud.get(appKey);
	}

}