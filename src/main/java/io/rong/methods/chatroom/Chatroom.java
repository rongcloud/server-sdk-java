package io.rong.methods.chatroom;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.methods.chatroom.ban.Ban;
import io.rong.methods.chatroom.distribute.Distribute;
import io.rong.methods.chatroom.gag.Gag;
import io.rong.methods.chatroom.keepalive.Keepalive;
import io.rong.methods.chatroom.demotion.Demotion;
import io.rong.methods.chatroom.whitelist.Whitelist;
import io.rong.methods.chatroom.block.Block;
import io.rong.models.*;
import io.rong.models.chatroom.*;
import io.rong.models.response.ChatroomQueryResult;
import io.rong.models.response.ChatroomUserQueryResult;
import io.rong.models.response.CheckChatRoomUserResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 *
 * 聊天室服务
 * docs: "http://www.rongcloud.cn/docs/server.html#chatroom"
 *
 * */
public class Chatroom {

	private static final String UTF8 = "UTF-8";
	private static final String PATH = "chatroom";
	private String appKey;
	private String appSecret;
	public 	 Block block;
	public Gag gag;
	public Ban ban;
	public Keepalive keepalive;
	public Demotion demotion;
	public Whitelist whiteList;
	public Distribute distribute;
	private RongCloud rongCloud;


	public RongCloud getRongCloud() {
		return rongCloud;
	}

	public void setRongCloud(RongCloud rongCloud) {
		this.rongCloud = rongCloud;
		gag.setRongCloud(rongCloud);
		keepalive.setRongCloud(rongCloud);
		demotion.setRongCloud(rongCloud);
		whiteList.setRongCloud(rongCloud);
		block.setRongCloud(rongCloud);
		demotion.setRongCloud(rongCloud);
		distribute.setRongCloud(rongCloud);
		ban.setRongCloud(rongCloud);

	}
	public Chatroom(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.gag = new Gag(appKey,appSecret);
		this.keepalive = new Keepalive(appKey,appSecret);
		this.demotion = new Demotion(appKey,appSecret);
		this.whiteList = new Whitelist(appKey,appSecret);
		this.block = new Block(appKey,appSecret);
		this.distribute = new Distribute(appKey,appSecret);
		this.ban = new Ban(appKey,appSecret);

	}
	/**
	 * 创建聊天室方法 
	 * 
	 * @param  chatrooms:chatroom.id,name（必传）
	 *
	 * @return ResponseResult
	 **/
	public ResponseResult create(ChatroomModel[] chatrooms) throws Exception {
		if (chatrooms == null) {
			throw new ParamException(CommonConstrants.RCLOUD_PARAM_NULL, "/chatroom/create","Paramer 'chatrooms' is required");
		}
		
	   	StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i< chatrooms.length; i++) {
			ChatroomModel chatroom  = chatrooms[i];
			sb.append("&chatroom["+chatroom.getId()+"]=").append(URLEncoder.encode(chatroom.getName(), UTF8));
		}
		
	   	String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
	   	HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/create.json", "application/x-www-form-urlencoded");
	   	HttpUtil.setBodyParameter(body, conn);
	    
	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.CREATE,HttpUtil.returnResult(conn)), ResponseResult.class);
	}
	/**
	 * 销毁聊天室方法
	 *
	 * @param  chatroom:要销毁的聊天室 Id。（必传）
	 *
	 * @return ResponseResult
	 **/
	public ResponseResult destroy(ChatroomModel chatroom) throws Exception {
		if (chatroom == null) {
			throw new ParamException(CommonConstrants.RCLOUD_PARAM_NULL, "/chatroom/destroy", "Paramer 'chatroomId' is required");
		}
		String message = CommonUtil.checkFiled(chatroom,PATH,CheckMethod.DESTORY);
		if(null != message){
			return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
		}
		StringBuilder sb = new StringBuilder();

		sb.append("&chatroomId=").append(URLEncoder.encode(chatroom.getId(), UTF8));

		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/destroy.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);


		return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.DESTORY,HttpUtil.returnResult(conn)), ResponseResult.class);
	}
	/**
	 * 查询聊天室内用户方法
	 *
	 * @param  chatroom:聊天室.id,count,order（必传）
	 *
	 * @return ChatroomUserQueryResult
	 **/
	public ChatroomUserQueryResult get(ChatroomModel chatroom) throws Exception {
		String message = CommonUtil.checkFiled(chatroom,PATH,CheckMethod.GET);
		if(null != message){
			return (ChatroomUserQueryResult)GsonUtil.fromJson(message,ChatroomUserQueryResult.class);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("&chatroomId=").append(URLEncoder.encode(chatroom.getId().toString(), UTF8));
		sb.append("&count=").append(URLEncoder.encode(chatroom.getCount().toString(), UTF8));
		sb.append("&order=").append(URLEncoder.encode(chatroom.getOrder().toString(), UTF8));
		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/user/query.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);

		return (ChatroomUserQueryResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GET,HttpUtil.returnResult(conn)), ChatroomUserQueryResult.class);
	}
	/**
	 * 查询用户是否存在聊天室
	 *
	 * @param  member:聊天室成员。（必传）
	 *
	 * @return ResponseResult
	 **/
	public CheckChatRoomUserResult isExist(ChatroomMember member) throws Exception {
		String message = CommonUtil.checkFiled(member,PATH,CheckMethod.ISEXIST);
		if(null != message){
			return (CheckChatRoomUserResult)GsonUtil.fromJson(message,ResponseResult.class);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("&chatroomId=").append(URLEncoder.encode(member.chatroomId.toString(), UTF8));
		sb.append("&userId=").append(URLEncoder.encode(member.id.toString(), UTF8));
		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/user/exist.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);

		return (CheckChatRoomUserResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.ISEXIST,HttpUtil.returnResult(conn)), CheckChatRoomUserResult.class);
	}
}