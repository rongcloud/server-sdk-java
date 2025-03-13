package io.rong.methods.message.group;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonParseException;
import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.message.GroupMessage;
import io.rong.models.message.GroupStatusMessage;
import io.rong.models.message.MentionMessage;
import io.rong.models.message.RecallMessage;
import io.rong.models.response.MessageResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * Group message sending methods
 * <p>
 * Docs: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
 *
 * @author RongCloud
 */
public class Group {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "message/group";
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

    public Group(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }

    /**
     * Sends a group message (a user sends a message to a group, with a maximum size of 128k per message).
     *
     * @param message The group message to be sent.
     * @return ResponseResult The result of the message sending operation.
     * @throws Exception If an error occurs during the message sending process.
     **/
    public MessageResult send(GroupMessage message) throws Exception {

        String code = CommonUtil.checkFiled(message, PATH, CheckMethod.PUBLISH);
        if (null != code) {
            return (MessageResult) GsonUtil.fromJson(code, MessageResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId().toString(), UTF8));

        for (int i = 0; i < message.getTargetId().length; i++) {
            String child = message.getTargetId()[i];
            if (null != child) {
                sb.append("&toGroupId=").append(URLEncoder.encode(child, UTF8));
            }
        }

        sb.append("&objectName=").append(URLEncoder.encode(message.getContent().getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(message.getContent().toString(), UTF8));

        if (message.getPushContent() != null) {
            sb.append("&pushContent=").append(URLEncoder.encode(message.getPushContent().toString(), UTF8));
        }

        if (message.getPushData() != null) {
            sb.append("&pushData=").append(URLEncoder.encode(message.getPushData(), UTF8));
        }

        if (message.getPushExt() != null) {
            sb.append("&pushExt=").append(URLEncoder.encode(message.getPushExt(), UTF8));
        }

        if (message.getIsPersisted() != null) {
            sb.append("&isPersisted=").append(URLEncoder.encode(message.getIsPersisted().toString(), UTF8));
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

        if (message.getDisableUpdateLastMsg() != null) {
            sb.append("&disableUpdateLastMsg=").append(message.getDisableUpdateLastMsg());
        }

        if (message.getMsgRandom() != null) {
            sb.append("&msgRandom=").append(message.getMsgRandom());
        }

        if (message.getIsMentioned() != null) {
            sb.append("&isMentioned=").append(message.getIsMentioned());
        }

        if (message.getToUserId() != null && message.getToUserId().length > 0) {
            for (int i = 0; i < message.getToUserId().length; i++) {
                String toId = message.getToUserId()[i];
                if (null != toId) {
                    sb.append("&toUserId=").append(URLEncoder.encode(toId, UTF8));
                }
            }
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/group/publish.json", "application/x-www-form-urlencoded");
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
     * Sends a group @mention message (A user sends a message to a group. The maximum size of a single message is 128k.)
     *
     * @param message
     * @return ResponseResult
     * @throws Exception
     **/
    public MessageResult sendMention(MentionMessage message) throws Exception {

        String code = CommonUtil.checkFiled(message, PATH, CheckMethod.SEND_MENTION);
        if (null != code) {
            return (MessageResult) GsonUtil.fromJson(code, MessageResult.class);
        }
        if (null == message.getContent().getContent()) {
            return new MessageResult(1002, "MentionMessageContent.content is a required parameter");
        }
        if (null == message.getContent().getContent().getMentionedInfo()) {
            return new MessageResult(1002, "mentionedInfo is a required parameter");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId().toString(), UTF8));
        String[] groupIds = message.getTargetId();
        for (int i = 0; i < groupIds.length; i++) {
            String child = groupIds[i];
            sb.append("&toGroupId=").append(URLEncoder.encode(child, UTF8));
        }

        if (message.getToUserId() != null && message.getToUserId().length > 0) {
            for (int i = 0; i < message.getToUserId().length; i++) {
                String toId = message.getToUserId()[i];
                if (null != toId) {
                    sb.append("&toUserId=").append(URLEncoder.encode(toId, UTF8));
                }
            }
        }

        sb.append("&objectName=").append(URLEncoder.encode(message.getContent().getContent().getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(message.getContent().toString(), UTF8));

        if (message.getPushContent() != null) {
            sb.append("&pushContent=").append(URLEncoder.encode(message.getPushContent().toString(), UTF8));
        }

        if (message.getPushData() != null) {
            sb.append("&pushData=").append(URLEncoder.encode(message.getPushData(), UTF8));
        }

        if (message.getPushExt() != null) {
            sb.append("&pushExt=").append(URLEncoder.encode(message.getPushExt(), UTF8));
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
        if (message.getExpansion() != null) {
            sb.append("&expansion=").append(URLEncoder.encode(message.getExpansion().toString(), UTF8));
            if (message.getExtraContent() != null) {
                sb.append("&extraContent=").append(URLEncoder.encode(JSON.toJSONString(message.getExtraContent()), UTF8));
            }
        }
        if (message.getMsgRandom() != null) {
            sb.append("&msgRandom=").append(message.getMsgRandom());
        }

        if (message.getDisableUpdateLastMsg() != null) {
            sb.append("&disableUpdateLastMsg=").append(message.getDisableUpdateLastMsg());
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/group/publish.json", "application/x-www-form-urlencoded");
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
     * The group targeted message feature allows sending messages to one or more specified users in a group, while other users in the group will not receive the message. This parameter is valid when toGroupId is a group. Note: If the "Cloud Storage for One-to-One and Group Messages" feature is enabled, group targeted messages will not be stored in the cloud. This feature can be used when sending read receipts to some users in a group.
     *
     * @param message
     * @return ResponseResult
     * @throws Exception
     **/
    public MessageResult sendDirection(GroupMessage message) throws Exception {

        String code = CommonUtil.checkFiled(message, PATH, CheckMethod.PUBLISH);
        if (null != code) {
            return (MessageResult) GsonUtil.fromJson(code, MessageResult.class);
        }
        if (message.getTargetId().length > 1) {
            return new MessageResult(20005, "Group targeted message is valid only when the group ID is one.");
        }
        if (null == message.getToUserId() && message.getToUserId().length < 1) {
            return new MessageResult(20005, "toUserId is required.");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId().toString(), UTF8));

        for (int i = 0; i < message.getTargetId().length; i++) {
            String child = message.getTargetId()[i];
            if (null != child) {
                sb.append("&toGroupId=").append(URLEncoder.encode(child, UTF8));
            }
        }
        for (int i = 0; i < message.getToUserId().length; i++) {
            String toId = message.getToUserId()[i];
            if (null != toId) {
                sb.append("&toUserId=").append(URLEncoder.encode(toId, UTF8));
            }
        }

        sb.append("&objectName=").append(URLEncoder.encode(message.getContent().getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(message.getContent().toString(), UTF8));

        if (message.getPushContent() != null) {
            sb.append("&pushContent=").append(URLEncoder.encode(message.getPushContent(), UTF8));
        }

        if (message.getPushData() != null) {
            sb.append("&pushData=").append(URLEncoder.encode(message.getPushData(), UTF8));
        }

        if (message.getPushExt() != null) {
            sb.append("&pushExt=").append(URLEncoder.encode(message.getPushExt(), UTF8));
        }

        if (message.getIsPersisted() != null) {
            sb.append("&isPersisted=").append(URLEncoder.encode(message.getIsPersisted().toString(), UTF8));
        }

/*if (message.getIsCounted() != null) {
    sb.append("&isCounted=").append(URLEncoder.encode(message.getIsCounted().toString(), UTF8));
}*/

        if (message.getIsIncludeSender() != null) {
            sb.append("&isIncludeSender=").append(URLEncoder.encode(message.getIsIncludeSender().toString(), UTF8));
        }
        if (message.getContentAvailable() != null) {
            sb.append("&contentAvailable=").append(URLEncoder.encode(message.getContentAvailable().toString(), UTF8));
        }
        if (message.getMsgRandom() != null){
            sb.append("&msgRandom=").append(message.getMsgRandom());
        }

        if(message.getDisableUpdateLastMsg() != null) {
            sb.append("&disableUpdateLastMsg=").append(message.getDisableUpdateLastMsg());
        }

        if(message.getIsMentioned() != null){
            sb.append("&isMentioned=").append(message.getIsMentioned());
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/group/publish.json", "application/x-www-form-urlencoded");
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
     * Recall a group message.
     *
     * @param message
     * @return ResponseResult
     * @throws Exception
     **/
    public ResponseResult recall(RecallMessage message) throws Exception {
        // Fields to validate
        String errMsg = CommonUtil.checkFiled(message, RECAL_PATH, CheckMethod.RECALL);
        if (null != errMsg) {
            return (ResponseResult) GsonUtil.fromJson(errMsg, Result.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode("3", UTF8));
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

        if (message.getDisableUpdateLastMsg() != null) {
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
     * Send group status message
     *
     * @param message
     * @return
     * @throws Exception
     */
    public MessageResult sendStatusMessage(GroupStatusMessage message) throws Exception {
        String errMsg = CommonUtil.checkFiled(message, PATH, CheckMethod.SENDGROUPSTATUS);
        if (null != errMsg) {
            return (MessageResult) GsonUtil.fromJson(errMsg, MessageResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId(), UTF8));

        for (int i = 0; i < message.getGroupId().length; i++) {
            String child = message.getGroupId()[i];
            if (null != child) {
                sb.append("&toGroupId=").append(URLEncoder.encode(child, UTF8));
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
        if (message.getMsgRandom() != null){
            sb.append("&msgRandom=").append(message.getMsgRandom());
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/statusmessage/group/publish.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        MessageResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (MessageResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.SENDGROUPSTATUS, response), MessageResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new MessageResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }
}
