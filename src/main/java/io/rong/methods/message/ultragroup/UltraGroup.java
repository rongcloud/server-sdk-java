package io.rong.methods.message.ultragroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonParseException;
import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.message.*;
import io.rong.models.response.MessageResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Methods for sending ultra group messages
 * <p>
 * docs :
 *
 * @author RongCloud
 */
public class UltraGroup {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "message/ultragroup";
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

    public UltraGroup(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }

    /**
     * Method for sending ultra group messages (Send messages to a group as a single user, with a maximum message size of 128k.)
     * @param message
     * @return ResponseResult
     * @throws Exception
     **/
    public MessageResult send(UltraGroupMessage message) throws Exception {

        String code = CommonUtil.checkFiled(message, PATH, CheckMethod.PUBLISH);
        if (null != code) {
            return (MessageResult) GsonUtil.fromJson(code, MessageResult.class);
        }
        ConcurrentHashMap<String, Object> params = new ConcurrentHashMap<String, Object>();
        params.put("fromUserId", message.getSenderId());

        params.put("toGroupIds", message.getTargetId());
        params.put("toUserIds", message.getToUserIds());
        params.put("objectName", message.getObjectName());
        params.put("content", message.getContent().toString());

        if (message.getPushContent() != null) {
            params.put("pushContent", message.getPushContent().toString());
        }

        if (message.getPushData() != null) {
            params.put("pushData", message.getPushData());
        }

        if (message.getIsMentioned() != null) {
            params.put("isMentioned", message.getIsMentioned());
        }

        if (message.getPushExt() != null) {
            params.put("pushExt", message.getPushExt());
        }

        if (message.getIsPersisted() != null) {
            params.put("isPersisted", message.getIsPersisted());
        }

        if (message.getBusChannel() != null) {
            params.put("busChannel", message.getBusChannel());
        }

        if (message.getContentAvailable() != null) {
            params.put("contentAvailable", message.getContentAvailable());
        }

        if (message.getIsCounted() != null) {
            params.put("isCounted", message.getIsCounted());
        }

        if (message.getExpansion() != null && message.getExpansion()) {
            params.put("expansion", message.getExpansion());
            if (message.getExtraContent() != null) {
                params.put("extraContent", JSON.toJSONString(message.getExtraContent()));
            }
        }

        if (message.getMsgRandom() != null){
            params.put("msgRandom", message.getMsgRandom());
        }

        if(message.getDisableUpdateLastMsg() != null) {
            params.put("disableUpdateLastMsg", message.getDisableUpdateLastMsg());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/ultragroup/publish.json", "application/json");
        HttpUtil.setBodyParameter(GsonUtil.toJson(params), conn, rongCloud.getConfig());

        MessageResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (MessageResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.PUBLISH, response), MessageResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new MessageResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(GsonUtil.toJson(params));
        return result;
    }

    /**
     * Recall a message in an ultra group.
     *
     * @param message
     * @return ResponseResult
     * @throws Exception
     **/
    public ResponseResult recall(RecallMessage message) throws Exception {
        // Fields to validate
        String errMsg = CommonUtil.checkFiled(message, RECAL_PATH, CheckMethod.RECALL);
        if (null != errMsg) {
            return (ResponseResult) GsonUtil.fromJson(errMsg, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode("10", UTF8));
        sb.append("&fromUserId=").append(URLEncoder.encode(message.senderId.toString(), UTF8));
        sb.append("&targetId=").append(URLEncoder.encode(message.targetId.toString(), UTF8));
        sb.append("&messageUID=").append(URLEncoder.encode(message.uId.toString(), UTF8));
        if(message.sentTime  != null){
            sb.append("&sentTime=").append(URLEncoder.encode(message.sentTime.toString(), UTF8));
        }
        if (message.getIsAdmin() != null) {
            sb.append("&isAdmin=").append(URLEncoder.encode(message.getIsAdmin().toString(), UTF8));
        }

        if (message.getDisablePush() != null) {
            sb.append("&disablePush=").append(message.getDisablePush().toString());
        }
        if (message.getIsDelete() != null) {
            sb.append("&isDelete=").append(URLEncoder.encode(message.getIsDelete().toString(), UTF8));
        }
        if (message.getExtra() != null) {
            sb.append("&extra=").append(URLEncoder.encode(message.getExtra().toString(), UTF8));
        }
        if (message.getBusChannel() != null) {
            sb.append("&busChannel=").append(URLEncoder.encode(message.getBusChannel().toString(), UTF8));
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
            result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(RECAL_PATH, CheckMethod.RECALL, response), ResponseResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new ResponseResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

}
