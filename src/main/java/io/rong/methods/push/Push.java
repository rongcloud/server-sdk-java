package io.rong.methods.push;

import java.net.HttpURLConnection;

import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonParseException;
import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.push.BroadcastModel;
import io.rong.models.push.PushModel;
import io.rong.models.response.PushResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

/**
 * Push notification service
 *
 * @author lang
 */
public class Push {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "push";

    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public Push(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    /**
     * Broadcast
     *
     * @param broadcast Broadcast data
     * @return PushResult
     **/


public PushResult message(BroadcastModel broadcast) throws Exception {
    // Fields to be validated
    String message = CommonUtil.checkFiled(broadcast, PATH, CheckMethod.BROADCAST);
    if (null != message) {
        return (PushResult) GsonUtil.fromJson(message, PushResult.class);
    }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/push.json",
                "application/json");
        HttpUtil.setBodyParameter(broadcast.toString(), conn, rongCloud.getConfig());

        return (PushResult) GsonUtil.fromJson(
                CommonUtil.getResponseByCode(PATH, CheckMethod.BROADCAST, HttpUtil.returnResult(conn, rongCloud.getConfig())),
                PushResult.class);
    }

/**
 * Push notification
 *
 * @param push Push data
 * @return PushResult
 **/
public PushResult push(PushModel push) throws Exception {
    // Fields to be validated
    String message = CommonUtil.checkFiled(push, PATH, CheckMethod.PUSH);
    if (null != message) {
        return (PushResult) GsonUtil.fromJson(message, PushResult.class);
    }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/push.json",
                "application/json");

        HttpUtil.setBodyParameter(push.toString(), conn, rongCloud.getConfig());

        PushResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (PushResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.PUSH, response), PushResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new PushResult(500, "");
            result.setErrorMessage("request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(push.toString());
        return result;
    }

}