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
/**
 * 发送讨论组消息方法
 *
 * docs : http://www.rongcloud.cn/docs/server.html#message_discussion_publish
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
     * 发送讨论组消息方法（以一个用户身份向讨论组发送消息，单条消息最大 128k，每秒钟最多发送 20 条消息.）
     *
     *
     * @param  message:发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。（必传）
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
        sb.append("&toDiscussionId=").append(URLEncoder.encode(message.getTargetId().toString(), UTF8));
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
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/discussion/publish.json", "application/x-www-form-urlencoded");
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
        String msgErr = CommonUtil.checkFiled(message,PATH,CheckMethod.RECALL);
        if(null != msgErr){
            return (ResponseResult)GsonUtil.fromJson(msgErr,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode("2", UTF8));
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
