package io.rong.methods.user.chat;

import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonParseException;
import io.rong.RongCloud;
import io.rong.models.response.BanListResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.Result;
import io.rong.models.CheckMethod;
import io.rong.models.user.BanListModel;
import io.rong.models.user.BanModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * User one-to-one chat mute
 * docs: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
 *
 * @author RongCloud
 */
public class Ban {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "user/chat";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public Ban(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;
    }

    /**
     * Mute a user
     * url  "/user/chat/fb/set"
     * docs https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
     * @param model userId (required) state (required) type (required)
     * @return ResponseResult
     */
    public Result set(BanModel model) throws Exception {

        String message = CommonUtil.checkFiled(model, PATH, CheckMethod.SET);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        for (String userId : model.getUserId()) {
            sb.append("&userId=").append(URLEncoder.encode(userId, UTF8));
        }
        sb.append("&state=").append(URLEncoder.encode(model.getState().toString(), UTF8));
        sb.append("&type=").append(URLEncoder.encode(model.getType(), UTF8));

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/chat/fb/set.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        String response = "";
        ResponseResult result;
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
     * Query the list of banned users
     *
     * @param model:num offset type (required)
     * @return QueryBlacklistUserResult
     **/
    public BanListResult getList(BanListModel model) throws Exception {
        String message = CommonUtil.checkFiled(model, PATH, CheckMethod.GETLIST);
        if (null != message) {
            return (BanListResult) GsonUtil.fromJson(message, BanListResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&type=").append(URLEncoder.encode(model.getType(), UTF8));
        if (model.getNum() != null) {
            sb.append("&num=").append(URLEncoder.encode(model.getNum().toString(), UTF8));
        }
        if (model.getOffset() != null) {
            sb.append("&offset=").append(URLEncoder.encode(model.getOffset().toString(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/chat/fb/querylist.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
//        String str = CommonUtil.getResponseByCode(PATH, CheckMethod.GETLIST, HttpUtil.returnResult(conn, rongCloud.getConfig()));
//
//        return null;
        return (BanListResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GETLIST, HttpUtil.returnResult(conn, rongCloud.getConfig())), BanListResult.class);
    }

}
