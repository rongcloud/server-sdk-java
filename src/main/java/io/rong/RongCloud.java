/**
 * 融云 Server API java 客户端
 * create by kitName
 * create datetime : 2017-06-05
 * <p>
 * v2.0.1
 */
package io.rong;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.rong.methods.chatroom.*;
import io.rong.methods.conversation.Conversation;
import io.rong.methods.group.Group;
import io.rong.methods.message.Message;
import io.rong.methods.sensitive.SensitiveWord;
import io.rong.methods.sensitive.Wordfilter;
import io.rong.methods.user.User;
import io.rong.util.HostType;
import io.rong.util.HttpUtil;

public class RongCloud {

	private static ConcurrentHashMap<String, RongCloud> rongCloud = new ConcurrentHashMap<String, RongCloud>();

	public User user;
	public Message message;
	public Wordfilter wordfilter;
	public SensitiveWord sensitiveword;
	public Group group;
	public Chatroom chatroom;
	public Conversation conversation;
	private HostType apiHostType = new HostType("http://api-cn.ronghub.com");
	private HostType smsHostType = new HostType("http://api.sms.ronghub.com");
	private static List<HostType> apiHostListBackUp = new ArrayList();

	public HostType getApiHostType() {
		if(HttpUtil.timeoutNum.get() >= 3){
			for(HostType host : apiHostListBackUp){
				if(!apiHostType.getStrType().equals(host.getStrType())){
					HttpUtil.timeoutNum.set(0);
					this.setApiHostType(host);
					return host;
				}
			}
		}
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

	public static List<HostType> getApiHostListBackUp() {
		return apiHostListBackUp;
	}

	public static void setApiHostListBackUp(List<HostType> apiHostListBackUp) {
		RongCloud.apiHostListBackUp = apiHostListBackUp;
	}

	private RongCloud(String appKey, String appSecret) {
		user = new User(appKey, appSecret,this);
		message = new Message(appKey, appSecret);
		message.setRongCloud(this);
		wordfilter = new Wordfilter(appKey, appSecret);
		wordfilter.setRongCloud(this);
		sensitiveword = new SensitiveWord(appKey, appSecret);
		sensitiveword.setRongCloud(this);
		group = new Group(appKey, appSecret, this);
		chatroom = new Chatroom(appKey, appSecret,this);
		chatroom.setRongCloud(this);
		conversation = new Conversation(appKey,appSecret);
		conversation.setRongCloud(this);

	}

	public static RongCloud getInstance(String appKey, String appSecret) {
		if (null == rongCloud.get(appKey)) {
			rongCloud.putIfAbsent(appKey, new RongCloud(appKey, appSecret));
			apiHostListBackUp.add(new HostType("http://api-cn.ronghub.com"));
			apiHostListBackUp.add(new HostType("http://api2-cn.ronghub.com"));
		}
		return rongCloud.get(appKey);
	}
	/**
	 * 自定义 API 地址
	 * */
	public static RongCloud getInstance(String appKey, String appSecret,String api) {
		if (null == rongCloud.get(appKey)) {
			RongCloud rc =  new RongCloud(appKey, appSecret);
			if(api!=null && api.trim().length()>0){
				rc.setApiHostType(new HostType(api));
			}
			rongCloud.putIfAbsent(appKey,rc );
		}
		return rongCloud.get(appKey);
	}
	/**
	 * 自定义 api 支持备用域名
	 * @param appKey
	 * @param appSecret
	 * @param api 主 API 地址
	 * @param apiBackUp 备用 API 地址列表
	 * */
	public static RongCloud getInstance(String appKey, String appSecret,String api,List<String> apiBackUp) {
		if (null == rongCloud.get(appKey)) {
			RongCloud rc =  new RongCloud(appKey, appSecret);
			if(api!=null && api.trim().length()>0){
				rc.setApiHostType(new HostType(api));
				rc.apiHostListBackUp.add(new HostType(api));
			}
			for(String apiHost : apiBackUp){
				rc.apiHostListBackUp.add(new HostType(apiHost));
			}
			rongCloud.putIfAbsent(appKey,rc );
		}
		return rongCloud.get(appKey);
	}

}