package io.rong.methods.conversation;

import io.rong.RongCloud;
import io.rong.models.*;
import io.rong.models.conversation.ConversationModel;
import io.rong.models.response.ConversationNotificationResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 *
 * 会话消息免打扰服务
 * docs: "http://www.rongcloud.cn/docs/server.html#conversation_notification"
 *
 * @version
 * */
public class Conversation {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "conversation";
    private static String method = "";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Conversation(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * 设置用户某会话接收新消息时是否进行消息提醒。
     *
     * @param conversation 会话信息 其中type(必传)
     * @return ResponseResult
     **/
    public ResponseResult mute(ConversationModel conversation) throws Exception {
        String message = CommonUtil.checkFiled(conversation,PATH,CheckMethod.MUTE);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode(conversation.type.toString(), UTF8));
        sb.append("&requestId=").append(URLEncoder.encode(conversation.userId .toString(), UTF8));
        sb.append("&targetId=").append(URLEncoder.encode(conversation.targetId.toString(), UTF8));
        sb.append("&isMuted=").append(URLEncoder.encode("1", UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/conversation/notification/set.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.MUTE,HttpUtil.returnResult(conn)), ResponseResult.class);
    }

    /**
     * 设置用户某会话接收新消息时是否进行消息提醒。
     *
     * @param conversation 会话信息 其中type(必传)
     * @return ResponseResult
     **/
    public ResponseResult unMute(ConversationModel conversation) throws Exception {
        String message = CommonUtil.checkFiled(conversation,PATH,CheckMethod.UNMUTE);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode(conversation.type.toString(), UTF8));
        sb.append("&requestId=").append(URLEncoder.encode(conversation.userId .toString(), UTF8));
        sb.append("&targetId=").append(URLEncoder.encode(conversation.targetId.toString(), UTF8));
        sb.append("&isMuted=").append(URLEncoder.encode("0", UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/conversation/notification/set.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.UNMUTE,HttpUtil.returnResult(conn)), ResponseResult.class);
    }
}
