package io.rong.methods.chatroom.mute;

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
 * Member Mute
 * @author RongCloud
 */
public class MuteMembers {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "chatroom/mute/member";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public MuteMembers(String appKey, String appSecret,RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;

    }
    /**
     * Add muted chatroom members (If you don't want a user to send messages in a chatroom, you can mute them. Muted users can still receive and view messages but cannot send messages.)
     *
     * @param  chatroom: The chatroom information for muting, including chatroom ID (required), minute (required), and memberIds (required, supports up to 20 members)
     *
     * @return ResponseResult
     **/
    public ResponseResult add(ChatroomModel chatroom) throws Exception {
        String message = CommonUtil.checkFiled(chatroom,PATH, CheckMethod.ADD);
        if(null != message){
            return (ResponseResult) GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("chatroomId=").append(URLEncoder.encode(chatroom.getId(), UTF8));
        ChatroomMember[] members = chatroom.getMembers();
        for(ChatroomMember member : members){
            sb.append("&userId=").append(URLEncoder.encode(member.getId(), UTF8));
        }
        sb.append("&minute=").append(URLEncoder.encode(chatroom.getMinute().toString(), UTF8));
        if(null != chatroom.getExtra() && chatroom.getExtra().length() != 0){
            sb.append("&extra=").append(URLEncoder.encode(chatroom.getExtra(), UTF8));
        }
        if(null != chatroom.getNeedNotify()){
            sb.append("&needNotify=").append(chatroom.getNeedNotify());
        }
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/user/gag/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.ADD,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * Retrieve the list of muted members in a chatroom.
     *
     * @param  chatroom: Chatroom information, including the chatroom Id. (Required)
     *
     * @return ListGagChatroomUserResult
     **/
    public ListGagChatroomUserResult getList(ChatroomModel chatroom) throws Exception {
        String message = CommonUtil.checkFiled(chatroom,PATH,CheckMethod.GETLIST);
        if(null != message){
            return (ListGagChatroomUserResult)GsonUtil.fromJson(message,ListGagChatroomUserResult.class);
        }
        String body = "chatroomId="+URLEncoder.encode(chatroom.getId(), UTF8);
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/user/gag/list.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        return (ListGagChatroomUserResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn, rongCloud.getConfig())), ListGagChatroomUserResult.class);
    }

    /**
     * Remove muted members from a chatroom.
     *
     * @param  chatroom: Chatroom information, including the chatroom Id. (Required), and user Ids. (Required, supports up to 20 users)
     * @return ResponseResult
     **/
    public ResponseResult remove(ChatroomModel chatroom) throws Exception {
        String message = CommonUtil.checkFiled(chatroom,PATH,CheckMethod.REMOVE);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("chatroomId=").append(URLEncoder.encode(chatroom.getId(), UTF8));
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

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/user/gag/rollback.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

}
