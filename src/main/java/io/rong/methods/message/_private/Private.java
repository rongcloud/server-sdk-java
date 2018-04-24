package io.rong.methods.message._private;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.models.CheckMethod;
import io.rong.models.CommonConstrants;
import io.rong.models.Result;
import io.rong.models.message.RecallMessage;
import io.rong.models.message.TemplateMessage;
import io.rong.models.response.ResponseResult;
import io.rong.models.Templates;
import io.rong.models.message.PrivateMessage;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 发送单聊消息方法
 * docs : http://www.rongcloud.cn/docs/server.html#message_private_publish
 * @author RongCloud
 *
 */
public class Private {

	private static final String UTF8 = "UTF-8";
	private static final String PATH = "message/_private";
	private String appKey;
	private String appSecret;
	private RongCloud rongCloud;

	public RongCloud getRongCloud() {
		return rongCloud;
	}

	public void setRongCloud(RongCloud rongCloud) {
		this.rongCloud = rongCloud;
	}
	public Private(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}

	/**
	 * 发送单聊消息方法（一个用户向另外一个用户发送消息，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人，如：一次发送 1000 人时，示为 1000 条消息。） 
	 * 
	 * @param message 单聊消息
	 *
	 * @return ResponseResult
	 * @throws Exception
	 **/
	public ResponseResult send(PrivateMessage message) throws Exception {
		if(null == message){
			throw new ParamException(CommonConstrants.RCLOUD_PARAM_NULL,PATH, "Paramer 'message' is required");
		}
		String errMsg = CommonUtil.checkFiled(message,PATH, CheckMethod.SEND);
		if(null != errMsg){
			return (ResponseResult)GsonUtil.fromJson(errMsg,ResponseResult.class);
		}

	    StringBuilder sb = new StringBuilder();
	    sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId().toString(), UTF8));
	    
	    for (int i = 0 ; i< message.getTargetId().length; i++) {
			String child  = message.getTargetId()[i];
			sb.append("&toUserId=").append(URLEncoder.encode(child, UTF8));
		}
		
	    sb.append("&objectName=").append(URLEncoder.encode(message.getContent().getType(), UTF8));
   	    sb.append("&content=").append(URLEncoder.encode(message.getContent().toString(), UTF8));
	    
	    if (message.getPushContent() != null) {
	    	sb.append("&pushContent=").append(URLEncoder.encode(message.getPushContent().toString(), UTF8));
	    }
	    
	    if (message.getPushData() != null) {
	    	sb.append("&pushData=").append(URLEncoder.encode(message.getPushData().toString(), UTF8));
	    }
	    
	    if (message.getCount() != null) {
	    	sb.append("&count=").append(URLEncoder.encode(message.getCount().toString(), UTF8));
	    }
	    
	    if (message.getVerifyBlacklist() != null) {
	    	sb.append("&verifyBlacklist=").append(URLEncoder.encode(message.getVerifyBlacklist().toString(), UTF8));
	    }
	    
	    if (message.getIsPersisted() != null) {
	    	sb.append("&isPersisted=").append(URLEncoder.encode(message.getIsPersisted().toString(), UTF8));
	    }
	    
	    if (message.getIsCounted() != null) {
	    	sb.append("&isCounted=").append(URLEncoder.encode(message.getIsCounted().toString(), UTF8));
	    }
	    
	    if (message.getIsIncludeSender() != null) {
	    	sb.append("&isIncludeSender=").append(URLEncoder.encode(message.getIsIncludeSender().toString(), UTF8));
	    }
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/private/publish.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);

	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISH,HttpUtil.returnResult(conn)), ResponseResult.class);
	}
	
	/**
	 * 发送单聊模板消息方法（一个用户向多个用户发送不同消息内容，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人。） 
	 * 
	 * @param  message:单聊模版消息。
	 *
	 * @return ResponseResult
	 * @throws Exception
	 **/
	public ResponseResult sendTemplate(TemplateMessage message) throws Exception {

		String errMsg = CommonUtil.checkFiled(message,PATH, CheckMethod.SENDTEMPLATE);
		if(null != errMsg){
			return (ResponseResult)GsonUtil.fromJson(errMsg,ResponseResult.class);
		}

		Templates templateMessage = new Templates();

		ArrayList<String> toUserIds = new ArrayList<>();
		List<Map<String,String>> values = new ArrayList<>();
		List<String> push = new ArrayList<>();

		for(Map.Entry<String, TemplateMessage.Data> vo : message.getContent().entrySet()){
			toUserIds.add(vo.getKey());
			values.add(vo.getValue().getData());
			push.add(vo.getValue().getPush());
		}
		templateMessage.setFromUserId(message.getSenderId());
		templateMessage.setToUserId(toUserIds.toArray(new String[toUserIds.size()]));
		templateMessage.setObjectName(message.getObjectName());
		templateMessage.setContent(message.getTemplate().toString());
		templateMessage.setValues(values);
		templateMessage.setPushContent(push.toArray(new String[push.size()]));
		templateMessage.setPushData(message.getPushData());
		templateMessage.setVerifyBlacklist(message.getVerifyBlacklist());
		templateMessage.setContentAvailable(message.getContentAvailable());


		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/private/publish_template.json", "application/json");
	    HttpUtil.setBodyParameter(templateMessage.toString(), conn);
	    
	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISHTEMPLATE,HttpUtil.returnResult(conn)), ResponseResult.class);
	}

	/**
	 * 设置用户某会话接收新消息时是否进行消息提醒。
	 *
	 * @param message
	 *
	 * @return ResponseResult
	 * @throws Exception
	 **/
	public Result recall(RecallMessage message) throws Exception {

		String errMsg = CommonUtil.checkFiled(message,PATH,CheckMethod.RECALL);
		if(null != errMsg){
			return (ResponseResult)GsonUtil.fromJson(errMsg,ResponseResult.class);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("&conversationType=").append(URLEncoder.encode("1", UTF8));
		sb.append("&fromUserId=").append(URLEncoder.encode(message.senderId.toString(), UTF8));
		sb.append("&targetId=").append(URLEncoder.encode(message.targetId.toString(), UTF8));
		sb.append("&messageUID=").append(URLEncoder.encode(message.uId.toString(), UTF8));
		sb.append("&sentTime=").append(URLEncoder.encode(message.sentTime.toString(), UTF8));
		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/recall.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);

		return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.RECALL,HttpUtil.returnResult(conn)), ResponseResult.class);
	}
	 
}