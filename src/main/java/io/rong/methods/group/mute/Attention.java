package io.rong.methods.group.mute;

import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonParseException;
import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.group.AttentionModel;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.group.*;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * 特别关注群成员设置
 */
public class Attention {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "group/mute/attention";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public Attention(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;
    }

    /**
     * 设置用户指定群特别关注用户
     *
     * @param model 参数实体(userId,groupId,attentionUserId)
     * @return
     * @throws Exception
     */
    public Result set(AttentionModel model) throws Exception {
        String message = CommonUtil.checkFiled(model, PATH, CheckMethod.ADD);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(model.getUserId(), UTF8));
        sb.append("&groupId=").append(URLEncoder.encode(model.getGroupId(), UTF8));
        String[] attentionUserIds = model.getAttentionUserIds();
        for (String attentionUserId : attentionUserIds) {
            sb.append("&attentionUserId=").append(URLEncoder.encode(attentionUserId, UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/attention/set.json",
          "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        ResponseResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.ADD, response), ResponseResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new ResponseResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }


    /**
     * 删除用户指定群组中的特别关注用户
     *
     * @param model 参数实体(userId,groupId,attentionUserId)
     * @return
     * @throws Exception
     */
    public Result del(AttentionModel model) throws Exception {
        String message = CommonUtil.checkFiled(model, PATH, CheckMethod.DEL);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(model.getUserId(), UTF8));
        sb.append("&groupId=").append(URLEncoder.encode(model.getGroupId(), UTF8));
        String[] attentionUserIds = model.getAttentionUserIds();
        for (String attentionUserId : attentionUserIds) {
            sb.append("&attentionUserId=").append(URLEncoder.encode(attentionUserId, UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/attention/del.json",
          "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        ResponseResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.DEL, response), ResponseResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new ResponseResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

    /**
     * 清理多个群成员特别关注的同一个人
     * @param model
     * @return
     * @throws Exception
     */
    public Result reverseDel(AttentionModel model) throws Exception {
        String message = CommonUtil.checkFiled(model, PATH, CheckMethod.REVERSE_DEL);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        String[] userIds = model.getUserIds();
        for (String userId : userIds) {
            sb.append("&userId=").append(URLEncoder.encode(userId, UTF8));
        }
        sb.append("&groupId=").append(URLEncoder.encode(model.getGroupId(), UTF8));
        sb.append("&attentionUserId=").append(URLEncoder.encode(model.getAttentionUserId(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/attention/users/del.json",
          "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        ResponseResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.REVERSE_DEL, response), ResponseResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new ResponseResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

    /**
     * 同步群特别关注信息
     * @param model
     * @return
     * @throws Exception
     */
    public Result sync(AttentionModel model) throws Exception {
        String message = CommonUtil.checkFiled(model, PATH, CheckMethod.SYNC);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(model.getUserId(), UTF8));
        sb.append("&groupId=").append(URLEncoder.encode(model.getGroupId(), UTF8));
        if (model.getAttentionUserIds() != null) {
            String[] attentionUserIds = model.getAttentionUserIds();
            for (String attentionUserId : attentionUserIds) {
                sb.append("&attentionUserId=").append(URLEncoder.encode(attentionUserId, UTF8));
            }
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/attention/sync.json",
          "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        ResponseResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.SYNC, response), ResponseResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new ResponseResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

    /**
     * 查询用户指定群组特别关注成员列表
     *
     * @param model 参数实体(userId,groupId)
     * @return
     * @throws Exception
     */
    public Result query(AttentionModel model) throws Exception {
        String message = CommonUtil.checkFiled(model, PATH, CheckMethod.QUERY);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(model.getUserId(), UTF8));
        sb.append("&groupId=").append(URLEncoder.encode(model.getGroupId(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/attention/query.json",
          "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        AttentionResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (AttentionResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.QUERY, response), AttentionResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new AttentionResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }





}
