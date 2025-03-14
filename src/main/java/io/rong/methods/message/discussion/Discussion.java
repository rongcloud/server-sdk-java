package io.rong.methods.message.discussion;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.message.RecallMessage;
import io.rong.models.response.ResponseResult;
import io.rong.models.message.DiscussionMessage;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * Methods for sending discussion group messages
 *
 * @author RongCloud
 *
 */
public class Discussion {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "message/discussion";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Discussion(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * Send a discussion group message (Send a message to a discussion group as a user, with a maximum message size of 128k.)
     *
     *
     * @param  message: The message content to be sent. Refer to the RongCloud message type table for examples. If the objectName is a custom message type, this parameter can be customized. (Required)
     *
     * @return ResponseResult
     * @throws Exception
     **/
    public ResponseResult send(DiscussionMessage message) throws Exception {


        String code = CommonUtil.checkFiled(message,PATH, CheckMethod.PUBLISH);
        if(null != code){
            return (ResponseResult)GsonUtil.fromJson(code,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId().toString(), UTF8));
        for (int i = 0 ; i< message.getTargetId().length; i++) {
            String child  = message.getTargetId()[i];
            if(null != child){
                sb.append("&toDiscussionId=").append(URLEncoder.encode(child, UTF8));
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
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/discussion/publish.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISH,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * Recall a message in a discussion group.
     *
     * @param message
     *
     * @return ResponseResult
     * @throws Exception
     **/
    public Result recall(RecallMessage message) throws Exception {
        // Fields to be validated
        String msgErr = CommonUtil.checkFiled(message,PATH,CheckMethod.RECALL);
        if(null != msgErr){
            return (ResponseResult)GsonUtil.fromJson(msgErr,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode("2", UTF8));
        sb.append("&fromUserId=").append(URLEncoder.encode(message.senderId.toString(), UTF8));
        sb.append("&targetId=").append(URLEncoder.encode(message.targetId.toString(), UTF8));
        sb.append("&messageUID=").append(URLEncoder.encode(message.uId.toString(), UTF8));
        if(message.sentTime  != null){
            sb.append("&sentTime=").append(URLEncoder.encode(message.sentTime.toString(), UTF8));
        }
        if (message.getIsAdmin() != null) {
            sb.append("&isAdmin=").append(URLEncoder.encode(message.getIsAdmin().toString(), UTF8));
        }
        if (message.getIsDelete() != null) {
            sb.append("&isDelete=").append(URLEncoder.encode(message.getIsDelete().toString(), UTF8));
        }
        if (message.getExtra() != null) {
            sb.append("&extra=").append(URLEncoder.encode(message.getExtra().toString(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/recall.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.RECALL,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }
}
