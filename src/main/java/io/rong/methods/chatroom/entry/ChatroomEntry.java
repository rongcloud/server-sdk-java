package io.rong.methods.chatroom.entry;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.chatroom.ChatroomEntryModel;
import io.rong.models.response.ChatroomEntryListResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

/**
 * Chatroom custom attributes
 *
 * 1. Set chatroom custom attributes
 *
 * 2. Delete chatroom custom attributes
 *
 * 3. Query chatroom custom attributes
 *
 * @author RongCloud
 *
 */
public class ChatroomEntry {

	private static final String UTF8 = "UTF-8";
	private static final String PATH = "chatroom/entry";
	private String appKey;
	private String appSecret;
	private RongCloud rongCloud;

	public RongCloud getRongCloud() {
		return rongCloud;
	}

	public void setRongCloud(RongCloud rongCloud) {
		this.rongCloud = rongCloud;
	}

	public ChatroomEntry(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}

	public ChatroomEntry(String appKey, String appSecret, RongCloud rongCloud) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.rongCloud = rongCloud;

	}

    /**
     * Set chatroom custom attributes
     *
     * @param model Required fields: chatroomId, userId, key, value
     * @return
     * @throws IOException
     * @throws ProtocolException
     * @throws MalformedURLException
     */
    public ResponseResult set(ChatroomEntryModel model) throws Exception {
        String message = CommonUtil.checkFiled(model, PATH, CheckMethod.SET);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

		StringBuilder sb = new StringBuilder();
		sb.append("&chatroomId=").append(URLEncoder.encode(model.chatroomId, UTF8));
		sb.append("&userId=").append(URLEncoder.encode(model.userId.toString(), UTF8));
		sb.append("&key=").append(URLEncoder.encode(model.key, UTF8));
		sb.append("&value=").append(URLEncoder.encode(model.value, UTF8));
		sb.append("&autoDelete=").append(model.autoDelete);

		if (StringUtils.isNotBlank(model.objectName)) {
			sb.append("&objectName=").append(URLEncoder.encode(model.objectName, UTF8));
		}
		if (StringUtils.isNotBlank(model.content)) {
			sb.append("&content=").append(URLEncoder.encode(model.content, UTF8));
		}

		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
				"/chatroom/entry/set.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

		return (ResponseResult) GsonUtil.fromJson(
				CommonUtil.getResponseByCode(PATH, CheckMethod.SET, HttpUtil.returnResult(conn, rongCloud.getConfig())),
				ResponseResult.class);
	}


    /**
     * Batch set chatroom attributes (KV)
     *
     * @param model Required fields: chatroomId, entryOwnerId, entryInfo
     * @return
     * @throws IOException
     * @throws ProtocolException
     * @throws MalformedURLException
     */
    public ResponseResult batchSet(ChatroomEntryModel model) throws Exception {
        String message = CommonUtil.checkFiled(model, PATH, CheckMethod.BATCHSET);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

		StringBuilder sb = new StringBuilder();
		sb.append("&chatroomId=").append(URLEncoder.encode(model.getChatroomId(), UTF8));
		sb.append("&autoDelete=").append(URLEncoder.encode(model.isAutoDelete().toString(), UTF8));
		sb.append("&entryOwnerId=").append(URLEncoder.encode(model.getEntryOwnerId(), UTF8));
		sb.append("&entryInfo=").append(URLEncoder.encode(GsonUtil.toJson(model.getEntryInfo()), UTF8));

		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
		"/chatroom/entry/batch/set.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

		ResponseResult result = (ResponseResult) GsonUtil.fromJson(
		CommonUtil.getResponseByCode(PATH, CheckMethod.BATCHSET, HttpUtil.returnResult(conn, rongCloud.getConfig())),
		ResponseResult.class);
		result.setReqBody(body);
		return result;
	}


    /**
     * Remove chatroom attributes
     *
     * @param model Required fields: chatroomId, userId, key
     * @return
     * @throws Exception
     */
    public ResponseResult remove(ChatroomEntryModel model) throws Exception {
        String message = CommonUtil.checkFiled(model, PATH, CheckMethod.REMOVE);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

		StringBuilder sb = new StringBuilder();
		sb.append("&chatroomId=").append(URLEncoder.encode(model.chatroomId, UTF8));
		sb.append("&userId=").append(URLEncoder.encode(model.userId, UTF8));
		sb.append("&key=").append(URLEncoder.encode(model.key, UTF8));

		if (!StringUtils.isBlank(model.objectName)) {
			sb.append("&objectName=").append(URLEncoder.encode(model.objectName, UTF8));
		}
		if (!StringUtils.isBlank(model.content)) {
			sb.append("&content=").append(URLEncoder.encode(model.content, UTF8));
		}

		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
				"/chatroom/entry/remove.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

		return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.REMOVE,
				HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
	}

    /**
     * Query chatroom attributes
     *
     * @param model Required fields: chatroomId
     * @return
     */
    public ChatroomEntryListResult query(ChatroomEntryModel model) throws Exception {
        String message = CommonUtil.checkFiled(model, PATH, CheckMethod.QUERY);
        if (null != message) {
            return (ChatroomEntryListResult) GsonUtil.fromJson(message, ChatroomEntryListResult.class);
        }

		StringBuilder sb = new StringBuilder();
		sb.append("&chatroomId=").append(URLEncoder.encode(model.chatroomId, UTF8));

		if (null != model.keys && model.keys.length > 0) {
			for (String key : model.keys) {
				sb.append("&keys=").append(URLEncoder.encode(key, UTF8));
			}
		}

		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
				"/chatroom/entry/query.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

		return (ChatroomEntryListResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.QUERY,
				HttpUtil.returnResult(conn, rongCloud.getConfig())), ChatroomEntryListResult.class);
	}
}
