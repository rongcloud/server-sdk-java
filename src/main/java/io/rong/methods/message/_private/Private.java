package io.rong.methods.message._private;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.Templates;
import io.rong.models.message.PrivateMessage;
import io.rong.models.message.RecallMessage;
import io.rong.models.message.PrivateStatusMessage;
import io.rong.models.message.TemplateMessage;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

/**
 * 发送单聊消息方法
 * docs : http://www.rongcloud.cn/docs/server.html#message_private_publish
 * @author RongCloud
 *
 */
public class Private {

	private static final String UTF8 = "UTF-8";
	private static final String PATH = "message/_private";
	private static final String RECAL_PATH = "message/recall";
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
		String errMsg = CommonUtil.checkFiled(message, PATH, CheckMethod.SEND);
		if (null != errMsg) {
			return (ResponseResult) GsonUtil.fromJson(errMsg, ResponseResult.class);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId().toString(), UTF8));

		for (int i = 0; i < message.getTargetId().length; i++) {
			String child = message.getTargetId()[i];
			if (null != child) {
				sb.append("&toUserId=").append(URLEncoder.encode(child, UTF8));
			}
		}

		sb.append("&objectName=").append(URLEncoder.encode(message.getContent().getType(), UTF8));
		sb.append("&content=").append(URLEncoder.encode(message.getContent().toString(), UTF8));

		if (message.getPushContent() != null) {
			sb.append("&pushContent=").append(URLEncoder.encode(message.getPushContent().toString(), UTF8));
		}

		if (message.getPushData() != null) {
			sb.append("&pushData=").append(URLEncoder.encode(message.getPushData().toString(), UTF8));
		}

		if (message.getPushExt() != null) {
			sb.append("&pushExt=").append(URLEncoder.encode(message.getPushExt().toString(), UTF8));
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
		if (message.getContentAvailable() != null) {
			sb.append("&contentAvailable=").append(URLEncoder.encode(message.getContentAvailable().toString(), UTF8));
		}
		if (message.getDisablePush() != null) {
			sb.append("&disablePush=").append(URLEncoder.encode(message.getDisablePush().toString(), UTF8));
		}
		if (message.getExpansion() != null) {
			sb.append("&expansion=").append(URLEncoder.encode(message.getExpansion().toString(), UTF8));
		}
		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/private/publish.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

		ResponseResult result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISH,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
		result.setReqBody(body);

		return result;
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

		String errMsg = CommonUtil.checkFiled(message, PATH, CheckMethod.SENDTEMPLATE);
		if (null != errMsg) {
			return (ResponseResult) GsonUtil.fromJson(errMsg, ResponseResult.class);
		}

		Templates templateMessage = new Templates();

		ArrayList<String> toUserIds = new ArrayList<>();
		List<Map<String, String>> values = new ArrayList<>();
		List<String> push = new ArrayList<>();

		for (Map.Entry<String, TemplateMessage.Data> vo : message.getContent().entrySet()) {
			toUserIds.add(vo.getKey());
			values.add(vo.getValue().getData());
			push.add(vo.getValue().getPush());
		}
		templateMessage.setFromUserId(message.getSenderId());
		templateMessage.setToUserId(toUserIds.toArray(new String[toUserIds.size()]));
		templateMessage.setObjectName(message.getObjectName());
		templateMessage.setContent(GsonUtil.toJson(message.getTemplate(), Map.class));
		templateMessage.setValues(values);
		templateMessage.setPushContent(push.toArray(new String[push.size()]));
		templateMessage.setPushData(message.getPushData());
		templateMessage.setPushData(message.getPushExt());
		templateMessage.setVerifyBlacklist(message.getVerifyBlacklist());
		templateMessage.setContentAvailable(message.getContentAvailable());
		if (message.getDisablePush() != null) {
			templateMessage.setDisablePush(message.getDisablePush());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/private/publish_template.json", "application/json");
	    HttpUtil.setBodyParameter(templateMessage.toString(), conn, rongCloud.getConfig());

		ResponseResult result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.PUBLISHTEMPLATE, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
		result.setReqBody(templateMessage.toString());
	    return result;
	}

	/**
	 * 消息单聊撤回。
	 *
	 * @param message
	 *
	 * @return ResponseResult
	 * @throws Exception
	 **/
	public Result recall(RecallMessage message) throws Exception {

		String errMsg = CommonUtil.checkFiled(message, RECAL_PATH, CheckMethod.RECALL);
		if (null != errMsg) {
			return (ResponseResult) GsonUtil.fromJson(errMsg, ResponseResult.class);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("&conversationType=").append(URLEncoder.encode("1", UTF8));
		sb.append("&fromUserId=").append(URLEncoder.encode(message.senderId.toString(), UTF8));
		sb.append("&targetId=").append(URLEncoder.encode(message.targetId.toString(), UTF8));
		sb.append("&messageUID=").append(URLEncoder.encode(message.uId.toString(), UTF8));
		sb.append("&sentTime=").append(URLEncoder.encode(message.sentTime.toString(), UTF8));
		if (message.getDisablePush() != null) {
			sb.append("&disablePush=").append(URLEncoder.encode(message.getDisablePush().toString(), UTF8));
		}

		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/recall.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

		ResponseResult result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.RECALL, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
		result.setReqBody(body);
		return result;
	}

	/**
	 * 发送单聊状态消息
	 *
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public ResponseResult sendStatusMessage(PrivateStatusMessage message) throws Exception{
		String errMsg = CommonUtil.checkFiled(message, PATH, CheckMethod.SENDSTATUS);
		if (null != errMsg) {
			return (ResponseResult) GsonUtil.fromJson(errMsg, ResponseResult.class);
		}

		StringBuilder sb = new StringBuilder();
	    sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId(), UTF8));

	    for (int i = 0 ; i< message.getTargetId().length; i++) {
			String child  = message.getTargetId()[i];
			if(null != child){
				sb.append("&toUserId=").append(URLEncoder.encode(child, UTF8));
			}
		}

		if (!StringUtils.isBlank(message.getObjectName())) {
			sb.append("&objectName=").append(URLEncoder.encode(message.getObjectName(), UTF8));
		} else {
			sb.append("&objectName=").append(URLEncoder.encode(message.getContent().getType(), UTF8));
		}

   	    sb.append("&content=").append(URLEncoder.encode(message.getContent().toString(), UTF8));
   	    sb.append("&verifyBlacklist=").append(URLEncoder.encode(String.valueOf(message.getVerifyBlacklist()), UTF8));
   	    sb.append("&isIncludeSender=").append(URLEncoder.encode(String.valueOf(message.getIsIncludeSender()), UTF8));
       	sb.append("&isPersisted=").append(URLEncoder.encode(String.valueOf("0"), UTF8));
       	sb.append("&isCounted=").append(URLEncoder.encode(String.valueOf("0"), UTF8));

		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/statusmessage/private/publish.json", "application/x-www-form-urlencoded");
	    HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.SENDSTATUS, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
	}

	/**
	 * 发送正在输入状态信息
	 *
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public ResponseResult sendTypingStatusMessage(PrivateMessage message) throws Exception {
		String errMsg = CommonUtil.checkFiled(message, PATH, CheckMethod.SEND);
		if (null != errMsg) {
			return (ResponseResult) GsonUtil.fromJson(errMsg, ResponseResult.class);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId().toString(), UTF8));

		for (int i = 0; i < message.getTargetId().length; i++) {
			String child = message.getTargetId()[i];
			if (null != child) {
				sb.append("&toUserId=").append(URLEncoder.encode(child, UTF8));
			}
		}

		sb.append("&objectName=").append(URLEncoder.encode(message.getObjectName(), UTF8));
		sb.append("&content=").append(URLEncoder.encode("{\"typingContentType\":\"RC:TxtMsg\"}", UTF8));
		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/private/publish.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISH,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
	}
}