package io.rong.methods.chatroom.ban;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ListGagChatroomUserResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * Chatroom Global Mute Service
 *
 */
public class Ban {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "chatroom/global-gag";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Ban(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }
    /**
     * Add a user to the chatroom global mute list
     *
     * @param  chatroom : Id, minute. (Required)
     *
     * @return ResponseResult
     **/
    public ResponseResult add(ChatroomModel chatroom) throws Exception {
        String errMsg = CommonUtil.checkFiled(chatroom,PATH,CheckMethod.ADD);
        if(null != errMsg){
            return (ResponseResult)GsonUtil.fromJson(errMsg,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("minute=").append(URLEncoder.encode(chatroom.getMinute().toString(), UTF8));
        ChatroomMember[] members = chatroom.getMembers();
        for(ChatroomMember member : members){
            sb.append("&userId=").append(URLEncoder.encode(member.getId(), UTF8));
        }
        if(null != chatroom.getExtra() && chatroom.getExtra().length() != 0){
            sb.append("&extra=").append(URLEncoder.encode(chatroom.getExtra(), UTF8));
        }
        if(null != chatroom.getNeedNotify()){
            sb.append("&needNotify=").append(chatroom.getNeedNotify());
        }

        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/user/ban/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.ADD,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * Query the list of users globally muted in the chatroom
     *
     * @return ListGagChatroomUserResult
     **/
    public ListGagChatroomUserResult getList() throws Exception {
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/user/ban/query.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter("", conn, rongCloud.getConfig());
        return (ListGagChatroomUserResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn, rongCloud.getConfig())), ListGagChatroomUserResult.class);
    }

    /**
     * Remove global mute for users in the chatroom
     *
     * @param  chatroom: memberIds (required)
     *
     * @return ResponseResult
     **/
    public ResponseResult remove(ChatroomModel chatroom) throws Exception {
        String errMsg = CommonUtil.checkFiled(chatroom,PATH,CheckMethod.REMOVE);
        if(null != errMsg){
            return (ResponseResult)GsonUtil.fromJson(errMsg,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        ChatroomMember[] members = chatroom.getMembers();
        for(ChatroomMember member : members){
            sb.append("&userId=").append(URLEncoder.encode(member.getId(), UTF8));
        }
        if(null != chatroom.getExtra() && chatroom.getExtra().length() != 0){
            sb.append("&extra=").append(URLEncoder.encode(chatroom.getExtra(), UTF8));
        }
        if(null != chatroom.getNeedNotify()){
            sb.append("&needNotify=").append(chatroom.getNeedNotify());
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1);
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/user/ban/remove.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }
}


