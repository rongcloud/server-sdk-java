package io.rong.methods.message.group;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.message.MentionMessage;
import io.rong.models.message.RecallMessage;
import io.rong.models.response.ResponseResult;
import io.rong.models.message.GroupMessage;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 * 发送群组消息方法
 *
 * docs : http://www.rongcloud.cn/docs/server.html#message_group_publish
 * @author RongCloud
 *
 */
public class Group {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "message/group";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Group(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * 发送群组消息方法（以一个用户身份向群组发送消息，单条消息最大 128k.每秒钟最多发送 20 条消息，每次最多向 3 个群组发送，如：一次向 3 个群组发送消息，示为 3 条消息。）
     *
     * @param message
     *
     * @return ResponseResult
     * @throws Exception
     **/
    public ResponseResult send(GroupMessage message) throws Exception {

        String code = CommonUtil.checkFiled(message,PATH, CheckMethod.PUBLISH);
        if(null != code){
            return (ResponseResult)GsonUtil.fromJson(code,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId().toString(), UTF8));

        for (int i = 0 ; i< message.getTargetId().length; i++) {
            String child  =message.getTargetId()[i];
            sb.append("&toGroupId=").append(URLEncoder.encode(child, UTF8));
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

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/group/publish.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISH,HttpUtil.returnResult(conn)), ResponseResult.class);
    }

    /**
     * 发送群组@消息方法（以一个用户身份向群组发送消息，单条消息最大 128k.每秒钟最多发送 20 条消息，每次最多向 3 个群组发送，如：一次向 3 个群组发送消息，示为 3 条消息。）
     *
     * @param message
     *
     * @return ResponseResult
     * @throws Exception
     **/
    public ResponseResult sendMention(MentionMessage message) throws Exception {

        String code = CommonUtil.checkFiled(message,PATH, CheckMethod.PUBLISH);
        if(null != code){
            return (ResponseResult)GsonUtil.fromJson(code,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId().toString(), UTF8));
        String[] groupIds = message.getTargetId();
        for (int i = 0 ; i< groupIds.length; i++) {
            String child  = groupIds[i];
            sb.append("&toGroupId=").append(URLEncoder.encode(child, UTF8));
        }

        sb.append("&objectName=").append(URLEncoder.encode(message.getContent().getContent().getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(message.getContent().toString(), UTF8));

        if (message.getPushContent() != null) {
            sb.append("&pushContent=").append(URLEncoder.encode(message.getPushContent().toString(), UTF8));
        }

        if (message.getPushContent() != null) {
            sb.append("&pushData=").append(URLEncoder.encode(message.getPushContent().toString(), UTF8));
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

        sb.append("&isMentioned=").append(URLEncoder.encode("1", UTF8));

        if (message.getContentAvailable() != null) {
            sb.append("&contentAvailable=").append(URLEncoder.encode(message.getContentAvailable().toString(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/group/publish.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISH,HttpUtil.returnResult(conn)), ResponseResult.class);
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
        //需要校验的字段
        String errMsg = CommonUtil.checkFiled(message,PATH,CheckMethod.RECALL);
        if(null != errMsg){
            return (Result)GsonUtil.fromJson(errMsg,Result.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode("3", UTF8));
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
