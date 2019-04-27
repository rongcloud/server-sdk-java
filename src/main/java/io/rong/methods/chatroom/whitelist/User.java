package io.rong.methods.chatroom.whitelist;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.WhiteListResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class User {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "chatroom/whitelist/user";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public User(String appKey, String appSecret,RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;

    }
    /**
     * 添加聊天室白名单成员方法
     *
     * @param  chatroom:聊天室.Id，memberIds 聊天室中白名单成员最多不超过 5 个。（必传）
     *
     * @return ResponseResult
     **/
    public ResponseResult add(ChatroomModel chatroom) throws Exception {
        if (chatroom == null) {
            return new ResponseResult(1002,"Paramer chatroom is required");
        }
        String message = CommonUtil.checkFiled(chatroom,PATH, CheckMethod.ADD);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&chatroomId=").append(URLEncoder.encode(chatroom.getId().toString(), UTF8));

        ChatroomMember[] members = chatroom.getMembers();
        for(ChatroomMember member : members){
            sb.append("&userId=").append(URLEncoder.encode(member.getId(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/user/whitelist/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.ADD,HttpUtil.returnResult(conn)), ResponseResult.class);
    }
    /**
     * 添加聊天室白名单成员方法
     *
     * @param  chatroom:聊天室.Id，memberIds 聊天室中白名单成员最多不超过 5 个。（必传）
     *
     * @return ResponseResult
     **/
    public ResponseResult remove(ChatroomModel chatroom) throws Exception {
        if (chatroom == null) {
            return new ResponseResult(1002,"Paramer chatroom is required");
        }

        String message = CommonUtil.checkFiled(chatroom,PATH, CheckMethod.REMOVE);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&chatroomId=").append(URLEncoder.encode(chatroom.getId(), UTF8));

        ChatroomMember[] members = chatroom.getMembers();
        for(ChatroomMember member : members){
            sb.append("&userId=").append(URLEncoder.encode(member.getId(), UTF8));
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/user/whitelist/remove.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn)), ResponseResult.class);
    }

    /**
     * 添加聊天室白名单成员方法
     *
     *
     * @return WhiteListResult
     **/
    public Result getList(ChatroomModel chatroom) throws Exception {
        if (chatroom == null) {
            return new ResponseResult(1002,"Paramer chatroom is required");
        }

        String message = CommonUtil.checkFiled(chatroom,PATH,CheckMethod.GETLIST);
        if(null != message){
            return (WhiteListResult)GsonUtil.fromJson(message,WhiteListResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&chatroomId=").append(URLEncoder.encode(chatroom.getId().toString(), UTF8));

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/user/whitelist/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (WhiteListResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn)), WhiteListResult.class);
    }
}
