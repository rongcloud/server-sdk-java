package io.rong.methods.message.ultragroup;

import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonParseException;
import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.message.*;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 发送超级群消息方法
 * <p>
 * docs :
 *
 * @author RongCloud
 */
public class UltraGroup {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "message/ultragroup";
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
     * 发送超级群消息方法（以一个用户身份向群组发送消息，单条消息最大 128k.每秒钟最多发送 20 条消息，每次最多向 3 个群组发送，如：一次向 3 个群组发送消息，示为 3 条消息。）
     *
     * @param message
     * @return ResponseResult
     * @throws Exception
     **/
    public ResponseResult send(UltraGroupMessage message) throws Exception {

        String code = CommonUtil.checkFiled(message, PATH, CheckMethod.PUBLISH);
        if (null != code) {
            return (ResponseResult) GsonUtil.fromJson(code, ResponseResult.class);
        }
        ConcurrentHashMap<String, Object> params = new ConcurrentHashMap<String, Object>();
        params.put("fromUserId", message.getSenderId());

        params.put("toGroupIds", message.getTargetId());
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
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/message/ultragroup/publish.json", "application/json");
        HttpUtil.setBodyParameter(GsonUtil.toJson(params), conn, rongCloud.getConfig());

        ResponseResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            System.out.println(response);
            result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.PUBLISH, response), ResponseResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new ResponseResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        return result;
    }

}
