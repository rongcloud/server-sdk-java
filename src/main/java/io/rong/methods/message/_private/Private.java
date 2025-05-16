package io.rong.methods.message._private;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonParseException;
import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.Templates;
import io.rong.models.message.PrivateMessage;
import io.rong.models.message.PrivateStatusMessage;
import io.rong.models.message.RecallMessage;
import io.rong.models.message.TemplateMessage;
import io.rong.models.response.MessageResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Methods for sending one-to-one chat messages
 *
 * @author RongCloud
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
     * Sends a one-to-one chat message (a message from one user to another, with a maximum size of 128k per message).
     *
     * @param message The one-to-one chat message
     * @return ResponseResult
     * @throws Exception
     **/
    public MessageResult send(PrivateMessage message) throws Exception {
        String errMsg = CommonUtil.checkFiled(message, PATH, CheckMethod.SEND);
        if (null != errMsg) {
            return (MessageResult) GsonUtil.fromJson(errMsg, MessageResult.class);
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
            if (message.getExtraContent() != null) {
                sb.append("&extraContent=").append(URLEncoder.encode(JSON.toJSONString(message.getExtraContent()), UTF8));
            }
        }

        if (message.getMsgRandom() != null){
            sb.append("&msgRandom=").append(message.getMsgRandom());
        }

        if(message.getDisableUpdateLastMsg() != null){
            sb.append("&disableUpdateLastMsg=").append(message.getDisableUpdateLastMsg());
        }
        if (message.getNeedReadReceipt() != null) {
            sb.append("&needReadReceipt=").append(message.getNeedReadReceipt());
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/private/publish.json", "application/x-www-form-urlencoded");
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
     * Sends a one-to-one chat template message (one user sends different message contents to multiple users, with a maximum message size of 128k).
     *
     * @param message: The one-to-one chat template message.
     * @return ResponseResult
     * @throws Exception
     **/
    public MessageResult sendTemplate(TemplateMessage message) throws Exception {

        String errMsg = CommonUtil.checkFiled(message, PATH, CheckMethod.SENDTEMPLATE);
        if (null != errMsg) {
            return (MessageResult) GsonUtil.fromJson(errMsg, MessageResult.class);
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
        templateMessage.setPushExt(message.getPushExt());
        templateMessage.setVerifyBlacklist(message.getVerifyBlacklist());
        templateMessage.setContentAvailable(message.getContentAvailable());
        templateMessage.setDisableUpdateLastMsg(message.getDisableUpdateLastMsg());
        templateMessage.setNeedReadReceipt(message.getNeedReadReceipt());
        if (message.getDisablePush() != null) {
            templateMessage.setDisablePush(message.getDisablePush());
        }

        if (message.getMsgRandom() != null){
            templateMessage.setMsgRandom(message.getMsgRandom());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/private/publish_template.json", "application/json");
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
     * Recall a one-to-one chat message.
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
        sb.append("&conversationType=").append(URLEncoder.encode("1", UTF8));
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
            result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.RECALL, response),
                    ResponseResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new ResponseResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

    /**
     * Send one-to-one status message
     *
     * @param message
     * @return
     * @throws Exception
     */
    public MessageResult sendStatusMessage(PrivateStatusMessage message) throws Exception {
        String errMsg = CommonUtil.checkFiled(message, PATH, CheckMethod.SENDSTATUS);
        if (null != errMsg) {
            return (MessageResult) GsonUtil.fromJson(errMsg, MessageResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId(), UTF8));

        for (int i = 0; i < message.getTargetId().length; i++) {
            String child = message.getTargetId()[i];
            if (null != child) {
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
        if (message.getMsgRandom() != null) {
            sb.append("&msgRandom=").append(message.getMsgRandom());
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/statusmessage/private/publish.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        MessageResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (MessageResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.SENDSTATUS, response), MessageResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new MessageResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

    /**
     * Send typing status message
     *
     * @param message The private message to be sent
     * @return MessageResult
     * @throws Exception
     */
    public MessageResult sendTypingStatusMessage(PrivateMessage message) throws Exception {
        String errMsg = CommonUtil.checkFiled(message, PATH, CheckMethod.SEND);
        if (null != errMsg) {
            return (MessageResult) GsonUtil.fromJson(errMsg, MessageResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId().toString(), UTF8));

        for (int i = 0; i < message.getTargetId().length; i++) {
            String child = message.getTargetId()[i];
            if (null != child) {
                sb.append("&toUserId=").append(URLEncoder.encode(child, UTF8));
            }
        }

        if (message.getMsgRandom() != null){
            sb.append("&msgRandom=").append(message.getMsgRandom());
        }

        sb.append("&objectName=").append(URLEncoder.encode(message.getObjectName(), UTF8));
        sb.append("&content=").append(URLEncoder.encode("{\"typingContentType\":\"RC:TxtMsg\"}", UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/private/publish.json", "application/x-www-form-urlencoded");
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
}