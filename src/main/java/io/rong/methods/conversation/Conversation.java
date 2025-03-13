package io.rong.methods.conversation;

import io.rong.methods.BaseMethod;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.conversation.ConversationModel;
import io.rong.models.conversation.ConversationSetTopModel;
import io.rong.models.response.ConversationNotificationResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 *
 * Conversation Do Not Disturb Service
 * docs: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
 *
 * @version
 * */
public class Conversation extends BaseMethod {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "conversation";

    @Override
    protected void initPath() {
        super.path = PATH;
    }

    public Conversation(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        initPath();
    }

    /**
     * Set whether to receive message notifications for a specific conversation.
     *
     * @param conversation Conversation information, where type is required
     * @return ResponseResult
     **/
    public ResponseResult mute(ConversationModel conversation) throws Exception {
        String message = CommonUtil.checkFiled(conversation, PATH, CheckMethod.MUTE);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode(conversation.type.toString(), UTF8));
        sb.append("&requestId=").append(URLEncoder.encode(conversation.userId.toString(), UTF8));
        sb.append("&targetId=").append(URLEncoder.encode(conversation.targetId.toString(), UTF8));
        sb.append("&isMuted=").append(URLEncoder.encode("1", UTF8));
        if (conversation.busChannel != null) {
            sb.append("&busChannel=").append(URLEncoder.encode(conversation.getBusChannel(), UTF8));
        }
        sb.append("&unpushLevel=").append(URLEncoder.encode(String.valueOf(conversation.getUnpushLevel()), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/conversation/notification/set.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.MUTE, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * Set whether to receive message notifications for a specific conversation.
     *
     * @param conversation Conversation information, where type is required.
     * @return ResponseResult
     **/
    public ResponseResult unMute(ConversationModel conversation) throws Exception {
        String message = CommonUtil.checkFiled(conversation, PATH, CheckMethod.UNMUTE);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode(conversation.type.toString(), UTF8));
        sb.append("&requestId=").append(URLEncoder.encode(conversation.userId.toString(), UTF8));
        sb.append("&targetId=").append(URLEncoder.encode(conversation.targetId.toString(), UTF8));
        sb.append("&isMuted=").append(URLEncoder.encode("0", UTF8));
        if (conversation.busChannel != null) {
            sb.append("&busChannel=").append(URLEncoder.encode(conversation.getBusChannel(), UTF8));
        }
        sb.append("&unpushLevel=").append(URLEncoder.encode("0", UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/conversation/notification/set.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.UNMUTE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }
    /**
     * Method to query the Do Not Disturb status of a conversation.
     *
     * @param conversation Conversation information, where type is required
     * @return ResponseResult
     **/
    public Result get(ConversationModel conversation) throws Exception {
        String message = CommonUtil.checkFiled(conversation,PATH,CheckMethod.GET);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode(conversation.type, UTF8));
        sb.append("&requestId=").append(URLEncoder.encode(conversation.userId, UTF8));
        sb.append("&targetId=").append(URLEncoder.encode(conversation.targetId, UTF8));
        if(conversation.busChannel != null){
            sb.append("&busChannel=").append(URLEncoder.encode(conversation.getBusChannel(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/conversation/notification/get.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ConversationNotificationResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GET,HttpUtil.returnResult(conn, rongCloud.getConfig())), ConversationNotificationResult.class);
    }


    /**
     * Set conversation top
     */
    public ResponseResult setTop(ConversationSetTopModel model) throws Exception {
        String method = CheckMethod.SET_TOP;
        ResponseResult result = checkFiled(model, method, ResponseResult.class);
        if (result != null) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", model.getUserId());
        addFormParam(sb, "&conversationType=", model.getConversationType());
        addFormParam(sb, "&targetId=", model.getTargetId());
        addFormParam(sb, "&setTop=", model.getSetTop());
        String body = sb.toString();

        return doRequest("/conversation/top/set.json", body, method, ResponseResult.class);
    }

}
