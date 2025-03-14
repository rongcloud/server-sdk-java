package io.rong.methods.chatroom.ban;

import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonParseException;
import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ChatroomBanListResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.StatusResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * Chatroom Mute All Service
 */
public class BanAllMember {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "chatroom/ban";

    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public BanAllMember(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    /**
     * Add Chatroom Mute All Method
     *
     * @param chatroom : Id (required)
     * @return ResponseResult
     **/
    public ResponseResult add(ChatroomModel chatroom) throws Exception {
        String message = CommonUtil.checkFiled(chatroom, PATH, CheckMethod.ADD);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("chatroomId=").append(URLEncoder.encode(chatroom.getId(), UTF8));

        if(null != chatroom.getExtra() && chatroom.getExtra().length() != 0){
            sb.append("&extra=").append(URLEncoder.encode(chatroom.getExtra(), UTF8));
        }
        if(null != chatroom.getNeedNotify()){
            sb.append("&needNotify=").append(chatroom.getNeedNotify());
        }

        String body = sb.toString();
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/chatroom/ban/add.json", "application/x-www-form-urlencoded");
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
     * Remove chatroom mute all method
     *
     * @param chatroom : Id. (Required)
     * @return ResponseResult
     **/
    public ResponseResult remove(ChatroomModel chatroom) throws Exception {
        String message = CommonUtil.checkFiled(chatroom, PATH, CheckMethod.ADD);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("chatroomId=").append(URLEncoder.encode(chatroom.getId(), UTF8));
        if(null != chatroom.getExtra() && chatroom.getExtra().length() != 0){
            sb.append("&extra=").append(URLEncoder.encode(chatroom.getExtra(), UTF8));
        }
        if(null != chatroom.getNeedNotify()){
            sb.append("&needNotify=").append(chatroom.getNeedNotify());
        }
        String body = sb.toString();
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/chatroom/ban/rollback.json", "application/x-www-form-urlencoded");
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
     * Check the mute all chatrooms method
     *
     * @param chatroom : Id. (Required)
     * @return ResponseResult
     **/
    public StatusResult check(ChatroomModel chatroom) throws Exception {
        String message = CommonUtil.checkFiled(chatroom, PATH, CheckMethod.CHECK);
        if (null != message) {
            return (StatusResult) GsonUtil.fromJson(message, StatusResult.class);
        }
        String body = "chatroomId="+URLEncoder.encode(chatroom.getId(), UTF8);
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/chatroom/ban/check.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        StatusResult result;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (StatusResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.ADD, response), StatusResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new StatusResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

    /**
     * Query the list of users muted in chatrooms
     *
     * @return ListGagChatroomUserResult
     **/
    public ChatroomBanListResult getList(Integer size, Integer page) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (size != null) {
            sb.append("size=").append(URLEncoder.encode(size.toString(), UTF8));
        }
        if (page != null) {
            sb.append("&page=").append(URLEncoder.encode(page.toString(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1);
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/chatroom/ban/query.json", "application/x-www-form-urlencoded");

        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        ChatroomBanListResult result;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (ChatroomBanListResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GETLIST, response), ChatroomBanListResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new ChatroomBanListResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage(), null);
        }
        result.setReqBody(body);
        return result;
    }
}
