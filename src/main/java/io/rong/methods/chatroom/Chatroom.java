package io.rong.methods.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.ban.Ban;
import io.rong.methods.chatroom.ban.BanAllMember;
import io.rong.methods.chatroom.ban.BanAllMemberWhitelist;
import io.rong.methods.chatroom.distribute.Distribute;
import io.rong.methods.chatroom.entry.ChatroomEntry;
import io.rong.methods.chatroom.gag.Gag;
import io.rong.methods.chatroom.keepalive.Keepalive;
import io.rong.methods.chatroom.demotion.Demotion;
import io.rong.methods.chatroom.mute.MuteMembers;
import io.rong.methods.chatroom.whitelist.Whitelist;
import io.rong.methods.chatroom.block.Block;
import io.rong.models.*;
import io.rong.models.chatroom.*;
import io.rong.models.response.ChatroomUserQueryResult;
import io.rong.models.response.CheckChatRoomUserResult;
import io.rong.models.response.CheckChatRoomUsersResult;
import io.rong.models.response.ChatroomQueryResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * 聊天室服务
 * docs: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
 */
public class Chatroom {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "chatroom";
    private String appKey;
    private String appSecret;
    public Block block;
    public Gag gag;
    public MuteMembers muteMembers;
    public Ban ban;
    public BanAllMember banAllMember;
    public BanAllMemberWhitelist banAllMemberWhitelist;
    public Keepalive keepalive;
    public Demotion demotion;
    public Whitelist whiteList;
    public Distribute distribute;
    public ChatroomEntry entry;

    private RongCloud rongCloud;


    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
        gag.setRongCloud(rongCloud);
        keepalive.setRongCloud(rongCloud);
        demotion.setRongCloud(rongCloud);
        whiteList.setRongCloud(rongCloud);
        block.setRongCloud(rongCloud);
        demotion.setRongCloud(rongCloud);
        distribute.setRongCloud(rongCloud);
        ban.setRongCloud(rongCloud);
        banAllMember.setRongCloud(rongCloud);
        banAllMemberWhitelist.setRongCloud(rongCloud);
        entry.setRongCloud(rongCloud);
    }

    public Chatroom(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.gag = new Gag(appKey, appSecret);
        this.keepalive = new Keepalive(appKey, appSecret);
        this.demotion = new Demotion(appKey, appSecret);
        this.whiteList = new Whitelist(appKey, appSecret);
        this.block = new Block(appKey, appSecret);
        this.distribute = new Distribute(appKey, appSecret);
        this.ban = new Ban(appKey, appSecret);
        this.banAllMember = new BanAllMember(appKey, appSecret);
        this.banAllMemberWhitelist = new BanAllMemberWhitelist(appKey, appSecret);
        this.muteMembers = new MuteMembers(appKey, appSecret, rongCloud);
        this.entry = new ChatroomEntry(appKey, appSecret);

    }

    /**
     * 创建聊天室方法
     *
     * @param chatrooms:chatroom.id,name（必传）
     * @return ResponseResult
     **/
    @Deprecated
    public ResponseResult create(ChatroomModel[] chatrooms) throws Exception {
        if (chatrooms == null) {
            return new ResponseResult(1002, "Paramer 'chatrooms' is required");
        }
        for (ChatroomModel chatroom : chatrooms) {
            String message = CommonUtil.checkFiled(chatroom, PATH, CheckMethod.CREATE);
            if (null != message) {
                return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chatrooms.length; i++) {
            ChatroomModel chatroom = chatrooms[i];
            sb.append("&chatroom[" + chatroom.getId() + "]=").append(URLEncoder.encode(chatroom.getName(), UTF8));
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1);
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/create.json",
          "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(
          CommonUtil.getResponseByCode(PATH, CheckMethod.CREATE, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * 创建聊天室方法 V2
     *
     * @param chatroom
     * @return
     * @throws Exception
     */
    public ResponseResult createV2(ChatroomDataModel chatroom) throws Exception {
        if (chatroom == null) {
            return new ResponseResult(1002, "Paramer 'chatrooms' is required");
        }
        String message = CommonUtil.checkFiled(chatroom, PATH, CheckMethod.CREATEV2);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("chatroomId=").append(URLEncoder.encode(chatroom.getId().toString(), UTF8));
        if (null != chatroom.getDestroyType()) {
            sb.append("&destroyType=").append(URLEncoder.encode(chatroom.getDestroyType().toString(), UTF8));
        }
        if (null != chatroom.getDestroyTime()) {
            sb.append("&destroyTime=").append(URLEncoder.encode(chatroom.getDestroyTime().toString(), UTF8));
        }
        if (null != chatroom.getIsBan()) {
            sb.append("&isBan=").append(URLEncoder.encode(chatroom.getIsBan().toString(), UTF8));
        }
        if (null != chatroom.getEntryOwnerId()) {
            sb.append("&entryOwnerId=").append(URLEncoder.encode(chatroom.getEntryOwnerId().toString(), UTF8));
        }
        if (null != chatroom.getWhiteUserIds() && chatroom.getWhiteUserIds().length > 0) {
            for (String user : chatroom.getWhiteUserIds()) {
                sb.append("&whiteUserIds=").append(URLEncoder.encode(user, UTF8));
            }
        }
        if (null != chatroom.getEntryInfo() && chatroom.getEntryInfo().size() > 0) {
            sb.append("&entryInfo=").append(URLEncoder.encode(GsonUtil.toJson(chatroom.getEntryInfo()), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/create_new.json",
          "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        ResponseResult result = (ResponseResult) GsonUtil.fromJson(
          CommonUtil.getResponseByCode(PATH, CheckMethod.CREATEV2, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
        result.setReqBody(body);
        return result;
    }

    /**
     * 设置聊天室销毁类型
     *
     * @param destroyType: 设置销毁类型
     * @return ResponseResult
     **/
    public ResponseResult setDestroyType(ChatroomDestroyTypeModel destroyType) throws Exception {
        String message = CommonUtil.checkFiled(destroyType, PATH, CheckMethod.DESTORY);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("chatroomId=").append(URLEncoder.encode(destroyType.getId().toString(), UTF8));
        if (null != destroyType.getDestroyType()) {
            sb.append("&destroyType=").append(URLEncoder.encode(destroyType.getDestroyType().toString(), UTF8));
        }
        if (null != destroyType.getDestroyTime()) {
            sb.append("&destroyTime=").append(URLEncoder.encode(destroyType.getDestroyTime().toString(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/destroy/set.json",
          "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        ResponseResult result = (ResponseResult) GsonUtil.fromJson(
          CommonUtil.getResponseByCode(PATH, CheckMethod.DESTORY, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
        result.setReqBody(body);
        return result;
    }

    /**
     * 销毁聊天室方法e
     *
     * @param chatroom:要销毁的聊天室 Id。（必传）
     * @return ResponseResult
     **/
    public ResponseResult destroy(ChatroomModel chatroom) throws Exception {

        String message = CommonUtil.checkFiled(chatroom, PATH, CheckMethod.DESTORY);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        String body = "chatroomId=" + URLEncoder.encode(chatroom.getId(), UTF8);

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/destroy.json",
          "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(
          CommonUtil.getResponseByCode(PATH, CheckMethod.DESTORY, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }


    /**
     * 查询聊天室内用户方法
     *
     * @param chatroom:聊天室.id,count,order（必传）
     * @return ChatroomUserQueryResult
     **/
    public ChatroomUserQueryResult get(ChatroomModel chatroom) throws Exception {
        String message = CommonUtil.checkFiled(chatroom, PATH, CheckMethod.GET);
        if (null != message) {
            return (ChatroomUserQueryResult) GsonUtil.fromJson(message, ChatroomUserQueryResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&chatroomId=").append(URLEncoder.encode(chatroom.getId(), UTF8));
        sb.append("&count=").append(URLEncoder.encode(chatroom.getCount().toString(), UTF8));
        sb.append("&order=").append(URLEncoder.encode(chatroom.getOrder().toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1);
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/user/query.json",
          "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ChatroomUserQueryResult) GsonUtil.fromJson(
          CommonUtil.getResponseByCode(PATH, CheckMethod.GET, HttpUtil.returnResult(conn, rongCloud.getConfig())), ChatroomUserQueryResult.class);
    }


    /**
     * 查询用户是否存在聊天室
     *
     * @param chatroomModel:聊天室成员。（必传）
     * @return ResponseResult
     **/
    public CheckChatRoomUsersResult isExists(ChatroomModel chatroomModel) throws Exception {
        String message = CommonUtil.checkFiled(chatroomModel, PATH, CheckMethod.ISEXISTS);
        if (null != message) {
            return (CheckChatRoomUsersResult) GsonUtil.fromJson(message, CheckChatRoomUsersResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&chatroomId=").append(URLEncoder.encode(chatroomModel.getId(), UTF8));
        ChatroomMember[] members = chatroomModel.getMembers();
        for (ChatroomMember member : members) {
            sb.append("&userId=").append(URLEncoder.encode(member.getId(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/users/exist.json",
          "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (CheckChatRoomUsersResult) GsonUtil.fromJson(
          CommonUtil.getResponseByCode(PATH, CheckMethod.ISEXISTS, HttpUtil.returnResult(conn, rongCloud.getConfig())),
          CheckChatRoomUsersResult.class);
    }

    /**
     * 查询用户是否存在聊天室
     *
     * @param member:聊天室成员。（必传）
     * @return ResponseResult
     **/
    public CheckChatRoomUserResult isExist(ChatroomMember member) throws Exception {
        String message = CommonUtil.checkFiled(member, PATH, CheckMethod.ISEXIST);
        if (null != message) {
            return (CheckChatRoomUserResult) GsonUtil.fromJson(message, CheckChatRoomUserResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("chatroomId=").append(URLEncoder.encode(member.chatroomId, UTF8));
        sb.append("&userId=").append(URLEncoder.encode(member.id, UTF8));
        String body = sb.toString();
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/user/exist.json",
          "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (CheckChatRoomUserResult) GsonUtil.fromJson(
          CommonUtil.getResponseByCode(PATH, CheckMethod.ISEXIST, HttpUtil.returnResult(conn, rongCloud.getConfig())), CheckChatRoomUserResult.class);
    }


    public ChatroomQueryResult query(ChatroomModel chatroomModel) throws Exception {
        String message = CommonUtil.checkFiled(chatroomModel, PATH, CheckMethod.QUERY);
        if (null != message) {
            return (ChatroomQueryResult) GsonUtil.fromJson(message, ChatroomQueryResult.class);
        }
        String body = "chatroomId=" + URLEncoder.encode(chatroomModel.getId(), UTF8);
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/get.json",
          "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ChatroomQueryResult) GsonUtil.fromJson(
          CommonUtil.getResponseByCode(PATH, CheckMethod.QUERY, HttpUtil.returnResult(conn, rongCloud.getConfig())), ChatroomQueryResult.class);
    }

}