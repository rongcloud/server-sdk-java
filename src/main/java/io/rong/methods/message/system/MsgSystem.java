package io.rong.methods.message.system;

import com.google.gson.JsonParseException;
import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.message.*;
import io.rong.models.response.BroadcastResult;
import io.rong.models.response.MessageResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.Templates;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONException;



/**
 * Methods for sending system messages
 * <p>
 * docs : https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
 *
 * @author RongCloud
 */
public class MsgSystem {
    private static final String UTF8 = "UTF-8";
    private String appKey;
    private String appSecret;
    private static final String PATH = "message/system";
    private static final String RECAL_PATH = "message/recall";
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public MsgSystem(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }

    /**
     * Sends a system message (a user sends a system message to one or more users, with a maximum message size of 128k. The conversation type is SYSTEM.
     *
     * @param message The message body
     * @return ResponseResult
     * @throws Exception
     **/
    public MessageResult send(MessageModel message) throws Exception {
        SystemMessage systemMessage = (SystemMessage) message;
        String code = CommonUtil.checkFiled(systemMessage, PATH, CheckMethod.PUBLISH);
        if (null != code) {
            return (MessageResult) GsonUtil.fromJson(code, MessageResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(systemMessage.getSenderId().toString(), UTF8));

        for (int i = 0; i < systemMessage.getTargetId().length; i++) {
            String child = systemMessage.getTargetId()[i];
            if (null != child) {
                sb.append("&toUserId=").append(URLEncoder.encode(child, UTF8));
            }
        }

        sb.append("&objectName=").append(URLEncoder.encode(systemMessage.getObjectName(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(systemMessage.getContent().toString(), UTF8));

        if (systemMessage.getPushContent() != null) {
            sb.append("&pushContent=").append(URLEncoder.encode(systemMessage.getPushContent().toString(), UTF8));
        }

        if (systemMessage.getPushData() != null) {
            sb.append("&pushData=").append(URLEncoder.encode(systemMessage.getPushData().toString(), UTF8));
        }

        if (message.getPushExt() != null) {
            sb.append("&pushExt=").append(URLEncoder.encode(message.getPushExt(), UTF8));
        }

        if (systemMessage.getIsPersisted() != null) {
            sb.append("&isPersisted=").append(URLEncoder.encode(systemMessage.getIsPersisted().toString(), UTF8));
        }

        if (systemMessage.getIsCounted() != null) {
            sb.append("&isCounted=").append(URLEncoder.encode(systemMessage.getIsCounted().toString(), UTF8));
        }

        if (systemMessage.getContentAvailable() != null) {
            sb.append("&contentAvailable=").append(URLEncoder.encode(systemMessage.getContentAvailable().toString(), UTF8));
        }

        if (systemMessage.getDisablePush() != null) {
            sb.append("&disablePush=").append(URLEncoder.encode(systemMessage.getDisablePush().toString(), UTF8));
        }
        if (message.getMsgRandom() != null){
            sb.append("&msgRandom=").append(message.getMsgRandom());
        }

        if(message.getDisableUpdateLastMsg() != null) {
            sb.append("&disableUpdateLastMsg=").append(message.getDisableUpdateLastMsg());
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/system/publish.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        MessageResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (MessageResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.PUBLISH, response), MessageResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new MessageResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;

    }

    /**
     * Recall a system message.
     *
     * @param message
     * @return ResponseResult
     * @throws Exception
     **/
    public Result recall(RecallMessage message) throws Exception {

        String errMsg = CommonUtil.checkFiled(message, RECAL_PATH, CheckMethod.RECALL);
        if (null != errMsg) {
            return (ResponseResult) GsonUtil.fromJson(errMsg, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode("6", UTF8));
        sb.append("&fromUserId=").append(URLEncoder.encode(message.senderId.toString(), UTF8));
        sb.append("&targetId=").append(URLEncoder.encode(message.targetId.toString(), UTF8));
        sb.append("&messageUID=").append(URLEncoder.encode(message.uId.toString(), UTF8));
        if(message.sentTime  != null){
            sb.append("&sentTime=").append(URLEncoder.encode(message.sentTime.toString(), UTF8));
        }
        if (message.getDisablePush() != null) {
            sb.append("&disablePush=").append(URLEncoder.encode(message.getDisablePush().toString(), UTF8));
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

        if(message.getDisableUpdateLastMsg() != null) {
            sb.append("&disableUpdateLastMsg=").append(message.getDisableUpdateLastMsg());
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/recall.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        ResponseResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.RECALL, response), ResponseResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new ResponseResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }


    /**
     * Send system template message method (A user sends a system message to one or more users, with a maximum message size of 128k, and the conversation type is SYSTEM.)
     *
     * @param template: System template message.
     * @return ResponseResult
     * @throws Exception
     **/
    public MessageResult sendTemplate(TemplateMessage template) throws Exception {

        String code = CommonUtil.checkFiled(template, PATH, CheckMethod.PUBLISHTEMPLATE);
        if (null != code) {
            return (MessageResult) GsonUtil.fromJson(code, MessageResult.class);
        }
        Templates templateMessage = new Templates();

        ArrayList<String> toUserIds = new ArrayList<>();
        List<Map<String, String>> values = new ArrayList<>();
        List<String> push = new ArrayList<>();

        for (Map.Entry<String, TemplateMessage.Data> vo : template.getContent().entrySet()) {
            toUserIds.add(vo.getKey());
            values.add(vo.getValue().getData());
            push.add(vo.getValue().getPush());
        }
        templateMessage.setFromUserId(template.getSenderId());
        templateMessage.setToUserId(toUserIds.toArray(new String[toUserIds.size()]));
        templateMessage.setObjectName(template.getObjectName());
        templateMessage.setContent(GsonUtil.toJson(template.getTemplate(), Map.class));
        templateMessage.setValues(values);
        templateMessage.setPushContent(push.toArray(new String[push.size()]));
        templateMessage.setPushData(template.getPushData());
        templateMessage.setPushExt(template.getPushExt());
        templateMessage.setContentAvailable(template.getContentAvailable());
        templateMessage.setDisableUpdateLastMsg(template.getDisableUpdateLastMsg());
        if (template.getMsgRandom() != null){
            templateMessage.setMsgRandom(template.getMsgRandom());
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/system/publish_template.json", "application/json");
        HttpUtil.setBodyParameter(templateMessage.toString(), conn, rongCloud.getConfig());

        MessageResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (MessageResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.PUBLISHTEMPLATE, response), MessageResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new MessageResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(templateMessage.toString());
        return result;

    }

    /**
     * Sends a broadcast message to all registered users of an application. If users are offline, Push notifications will be sent to those who meet the conditions (e.g., bound to a mobile device). The maximum size of a single message is 128k, and the conversation type is SYSTEM. This method can be called up to 2 times per hour and 3 times per day.
     * This feature is available for free in the development environment. In the production environment, you need to log in to the developer Console, enable the public cloud professional edition under "Application/IM Service/Advanced Features," and then activate it under "Broadcast Messages and Push Notifications" to use this feature.
     *
     * @param message The message body
     * @return ResponseResult
     * @throws Exception
     **/
    public BroadcastResult broadcast(BroadcastMessage message) throws Exception {

        String errMsg = CommonUtil.checkFiled(message, PATH, CheckMethod.BROADCAST);
        if (null != errMsg) {
            return (BroadcastResult) GsonUtil.fromJson(errMsg, BroadcastResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId().toString(), UTF8));
        sb.append("&objectName=").append(URLEncoder.encode(message.getContent().getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(message.getContent().toString(), UTF8));

        if (message.getPushContent() != null) {
            sb.append("&pushContent=").append(URLEncoder.encode(message.getPushContent().toString(), UTF8));
        }

        if (message.getPushData() != null) {
            sb.append("&pushData=").append(URLEncoder.encode(message.getPushData().toString(), UTF8));
        }

        if (message.getPushExt() != null) {
            sb.append("&pushExt=").append(URLEncoder.encode(message.getPushExt(), UTF8));
        }

        if (message.getOs() != null) {
            sb.append("&os=").append(URLEncoder.encode(message.getOs().toString(), UTF8));
        }

        if (message.getContentAvailable() != null) {
            sb.append("&contentAvailable=").append(URLEncoder.encode(message.getContentAvailable().toString(), UTF8));
        }

//        if (message.getDisablePush() != null) {
//            sb.append("&disablePush=").append(URLEncoder.encode(message.getDisablePush().toString(), UTF8));
//        }
        if (message.getMsgRandom() != null){
            sb.append("&msgRandom=").append(message.getMsgRandom());
        }

        if(message.getDisableUpdateLastMsg() != null) {
            sb.append("&disableUpdateLastMsg=").append(message.getDisableUpdateLastMsg());
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/broadcast.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        BroadcastResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (BroadcastResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.BROADCAST, response), BroadcastResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new BroadcastResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

    /**
     * Broadcast to online users
     * This refers to the action of sending a message to all online users in the App. When users are actively using the App, the message will be displayed in the chat UI and conversation list, with the conversation type set to SYSTEM.
     * System notification messages can only be sent via the Server API. End users who receive system messages cannot reply to them.
     * The online broadcast message is implemented using the server-side notification SDK pull mechanism. In extreme cases, users may receive the message within 5 minutes after it is sent.
     *
     * @param message The message body
     * @return ResponseResult
     * @throws Exception
     **/
    public BroadcastResult onlineBroadcast(BroadcastMessage message) throws Exception {

        String errMsg = CommonUtil.checkFiled(message, PATH, CheckMethod.ONLINE);
        if (null != errMsg) {
            return (BroadcastResult) GsonUtil.fromJson(errMsg, BroadcastResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId().toString(), UTF8));
        sb.append("&objectName=").append(URLEncoder.encode(message.getContent().getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(message.getContent().toString(), UTF8));
        if (message.getMsgRandom() != null){
            sb.append("&msgRandom=").append(message.getMsgRandom());
        }

        if(message.getDisableUpdateLastMsg() != null) {
            sb.append("&disableUpdateLastMsg=").append(message.getDisableUpdateLastMsg());
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/online/broadcast.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        BroadcastResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (BroadcastResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.ONLINE, response), BroadcastResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new BroadcastResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }


    /**
     * Recall a broadcast message with Message-bearing Notification
     *
     * @param message
     * @return ResponseResult
     * @throws Exception
     **/
    public ResponseResult recallBroadcast(RecallMessage message) throws Exception {

        String errMsg = CommonUtil.checkFiled(message, RECAL_PATH, CheckMethod.BROADCAST);
        if (null != errMsg) {
            return (ResponseResult) GsonUtil.fromJson(errMsg, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.senderId.toString(), UTF8));
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

        if(message.getDisableUpdateLastMsg() != null) {
            sb.append("&disableUpdateLastMsg=").append(message.getDisableUpdateLastMsg());
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/broadcast/recall.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        ResponseResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.BROADCAST, response),
                    ResponseResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new ResponseResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

    /**
     *  Push-only Notification
     *  Sends a push-only notification to specified users in the application. This notification will be sent regardless of whether the user is actively using the app.
     *  The notification will only be displayed in the notification bar and will not contain any message content. After logging into the app, the user will not see this content in the chat UI, and it will not be stored in the local database.
     */
    public ResponseResult sendUser(PushUserMessage pushUser) throws Exception {
        String code = CommonUtil.checkFiled(pushUser, PATH, CheckMethod.SEND_USER);
        if (null != code) {
            return (ResponseResult) GsonUtil.fromJson(code, ResponseResult.class);
        }

        String body = GsonUtil.toJson(pushUser);
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/push/user.json", "application/json");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        System.out.println("bbbbb"+body);
        ResponseResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.SEND_USER, response), ResponseResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new ResponseResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;

    }



}
