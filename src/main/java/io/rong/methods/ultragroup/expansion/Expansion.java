package io.rong.methods.ultragroup.expansion;

import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonParseException;
import io.rong.RongCloud;
import io.rong.methods.ultragroup.ban.User;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.message.ExpansionModel;
import io.rong.models.response.ExpansionResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class Expansion {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "ultragroup/expansion";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public Expansion(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;
    }


    /**
     * Set message expansion
     *
     * @param msg : Message expansion parameters
     * @return Result
     **/
    public ResponseResult set(ExpansionModel msg) throws Exception {
        String message = CommonUtil.checkFiled(msg, PATH, CheckMethod.SET);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&msgUID=").append(URLEncoder.encode(msg.getMsgUID(), UTF8));
        sb.append("&userId=").append(URLEncoder.encode(msg.getUserId(), UTF8));
        sb.append("&groupId=").append(URLEncoder.encode(msg.getTargetId(), UTF8));
        sb.append("&extraKeyVal=").append(URLEncoder.encode(GsonUtil.toJson(msg.getExtraKeyVal()), UTF8));
        if(msg.getBusChannel() != null){
            sb.append("&busChannel=").append(URLEncoder.encode(msg.getBusChannel(), UTF8));
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1);
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/ultragroup/message/expansion/set.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        ResponseResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.SET, response), ResponseResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new ResponseResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

    /**
     * Remove message extension
     *
     * @param msg : Message extension parameters
     * @return ResponseResult
     **/
    public ResponseResult remove(ExpansionModel msg) throws Exception {
        // Parameter validation
        String message = CommonUtil.checkFiled(msg, PATH, CheckMethod.REMOVE);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&msgUID=").append(URLEncoder.encode(msg.getMsgUID(), UTF8));
        sb.append("&userId=").append(URLEncoder.encode(msg.getUserId(), UTF8));
        sb.append("&groupId=").append(URLEncoder.encode(msg.getTargetId(), UTF8));
        sb.append("&extraKey=").append(URLEncoder.encode(GsonUtil.toJson(msg.getExtraKey()), UTF8));
        if(msg.getBusChannel() != null){
            sb.append("&busChannel=").append(URLEncoder.encode(msg.getBusChannel(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/ultragroup/message/expansion/delete.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        ResponseResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.REMOVE, response), ResponseResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new ResponseResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

    /**
     * Get the extension information
     *
     * @return ExpansionResult
     **/
    public Result getList(String msgUID, String targetId, String busChannel, String pageNo) throws Exception {
        // Parameter validation
        String message = CommonUtil.checkParam("msgUID", msgUID, PATH, CheckMethod.GETLIST);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&msgUID=").append(URLEncoder.encode(msgUID, UTF8));
        if(busChannel != null){
            sb.append("&busChannel=").append(URLEncoder.encode(busChannel, UTF8));
        }

        sb.append("&groupId=").append(URLEncoder.encode(targetId, UTF8));
        if (pageNo != null) {
            sb.append("&pageNo=").append(URLEncoder.encode(pageNo, UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/ultragroup/message/expansion/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        ExpansionResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (ExpansionResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GETLIST, response), ExpansionResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new ExpansionResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

    public Result getList(String msgUID, String targetId, String busChannel) throws Exception {
        return getList(msgUID, targetId, busChannel, null);
    }
    public Result getList(String msgUID, String targetId) throws Exception {
        return getList(msgUID, targetId, null, null);
    }
}
