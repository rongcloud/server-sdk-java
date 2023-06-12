package io.rong.methods.chatroom.ban;

import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonParseException;
import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.GroupBanWhitelistResult;
import io.rong.models.response.ListBlockChatroomUserResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * 聊天室全体成员禁言白名单
 * docs:https://docs.rongcloud.cn/v4/views/im/server/chatroom/ban.html#ban-whitelist
 */
public class BanAllMemberWhitelist {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "chatroom/ban-whitelist";

    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public BanAllMemberWhitelist(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    /**
     * 添加聊天室全体禁言白名单方法
     *
     * @param chatroom:聊天室信息，memberIds（必传支持多个最多20个）。（必传）
     * @return ResponseResult
     **/
    public ResponseResult add(ChatroomModel chatroom) throws Exception {
        String message = CommonUtil.checkFiled(chatroom, PATH, CheckMethod.ADD);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        ChatroomMember[] members = chatroom.getMembers();
        sb.append("chatroomId=").append(URLEncoder.encode(chatroom.getId(), UTF8));
        for (ChatroomMember member : members) {
            sb.append("&userId=").append(URLEncoder.encode(member.getId(), UTF8));
        }
        if(null != chatroom.getExtra() && chatroom.getExtra().length() != 0){
            sb.append("&extra=").append(URLEncoder.encode(chatroom.getExtra(), UTF8));
        }
        if(null != chatroom.getNeedNotify()){
            sb.append("&needNotify=").append(chatroom.getNeedNotify());
        }
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/chatroom/user/ban/whitelist/add.json", "application/x-www-form-urlencoded");
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
     * 查询聊天室全体禁言白名单方法
     *
     * @param chatroomId:聊天室 Id。（必传）
     * @return ListBlockChatroomUserResult
     **/
    public GroupBanWhitelistResult getList(String chatroomId) throws Exception {
        String message = CommonUtil.checkParam("id", chatroomId, PATH, CheckMethod.GETLIST);
        if (null != message) {
            return (GroupBanWhitelistResult) GsonUtil.fromJson(message, GroupBanWhitelistResult.class);
        }
        String body = "chatroomId="+URLEncoder.encode(chatroomId, UTF8);
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/chatroom/user/ban/whitelist/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        String response = "";
        GroupBanWhitelistResult result = null;
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (GroupBanWhitelistResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GETLIST, response),
                    GroupBanWhitelistResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new GroupBanWhitelistResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
            result.setReqBody(body);
        }
        return result;
    }

    /**
     * 移除聊天室全体禁言白名单方法
     *
     * @param chatroom: 封禁的聊天室信息 其中聊天室 Id。（必传）,用户 Id。（必传）
     * @return ResponseResult
     **/
    public ResponseResult remove(ChatroomModel chatroom) throws Exception {
        String message = CommonUtil.checkFiled(chatroom, PATH, CheckMethod.REMOVE);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        ChatroomMember[] members = chatroom.getMembers();
        sb.append("chatroomId=").append(URLEncoder.encode(chatroom.getId(), UTF8));
        for (ChatroomMember member : members) {
            sb.append("&userId=").append(URLEncoder.encode(member.getId(), UTF8));
        }
        if(null != chatroom.getExtra() && chatroom.getExtra().length() != 0){
            sb.append("&extra=").append(URLEncoder.encode(chatroom.getExtra(), UTF8));
        }
        if(null != chatroom.getNeedNotify()){
            sb.append("&needNotify=").append(chatroom.getNeedNotify());
        }
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/chatroom/user/ban/whitelist/rollback.json", "application/x-www-form-urlencoded");
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
}
