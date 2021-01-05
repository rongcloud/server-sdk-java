package io.rong.methods.user;

import io.rong.RongCloud;
import io.rong.methods.user.blacklist.Blacklist;
import io.rong.methods.user.block.Block;
import io.rong.methods.user.mute.MuteChatrooms;
import io.rong.methods.user.mute.MuteGroups;
import io.rong.methods.user.onlinestatus.OnlineStatus;
import io.rong.methods.user.whitelist.Whitelist;
import io.rong.models.*;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.TokenResult;
import io.rong.models.response.UserResult;
import io.rong.models.user.UserModel;
import io.rong.methods.user.tag.Tag;
import io.rong.util.*;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonSyntaxException;


/**
 * 用户服务
 * docs : http://www.rongcloud.cn/docs/server.html#user
 **/
public class User {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "user";
    private String appKey;
    private String appSecret;
    public Block block;
    public Blacklist blackList;
    public Whitelist whiteList;
    public OnlineStatus onlineStatus;
    public Tag tag;
    public MuteChatrooms muteChatrooms;
    public MuteGroups muteGroups;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public User(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;
        this.block = new Block(appKey, appSecret, rongCloud);
        this.blackList = new Blacklist(appKey, appSecret, rongCloud);
        this.whiteList = new Whitelist(appKey, appSecret, rongCloud);
        this.onlineStatus = new OnlineStatus(appKey, appSecret, rongCloud);
        this.muteChatrooms = new MuteChatrooms(appKey, appSecret, rongCloud);
        this.muteGroups = new MuteGroups(appKey, appSecret, rongCloud);
        this.tag = new Tag(appKey, appSecret, rongCloud);
    }

    /**
     * 获取 Token 方法
     * url  "/user/getToken"
     * docs "http://rongcloud.cn/docs/server.html#getToken"
     *
     * @param user 用户信息 id,name,portrait(必传)
     * @return TokenResult
     **/
    public TokenResult register(UserModel user) throws Exception {
        //需要校验的字段
        String message = CommonUtil.checkFiled(user, PATH, CheckMethod.REGISTER);
        if (null != message) {
            return (TokenResult) GsonUtil.fromJson(message, TokenResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(user.id, UTF8));
        sb.append("&name=").append(URLEncoder.encode(user.name, UTF8));
        sb.append("&portraitUri=").append(URLEncoder.encode(user.portrait, UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/user/getToken.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        TokenResult result = null;
        try {
            result = (TokenResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.REGISTER, HttpUtil.returnResult(conn, rongCloud.getConfig())), TokenResult.class);
        } catch (JSONException | JsonSyntaxException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new TokenResult(500, "", user.id, "request:" + conn.getURL() + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

    /**
     * 刷新用户信息方法
     * url  "/user/refresh"
     * docs "http://www.rongcloud.cn/docs/server.html#user_refresh"
     *
     * @param user 用户信息 id name portrait(必传)
     * @return ResponseResult
     **/
    public Result update(UserModel user) throws Exception {
        //需要校验的字段
        String message = CommonUtil.checkFiled(user, PATH, CheckMethod.UPDATE);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(user.id.toString(), UTF8));

        if (user.name != null) {
            sb.append("&name=").append(URLEncoder.encode(user.name.toString(), UTF8));
        }

        if (user.portrait != null) {
            sb.append("&portraitUri=").append(URLEncoder.encode(user.portrait.toString(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/refresh.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.UPDATE, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * 查询用户信息方法
     * url  "/user/info"
     * docs "http://www.rongcloud.cn/docs/server.html#user_info"
     *
     * @param user 用户信息 id (必传)
     * @return UserResult
     * @throws Exception
     */
    public UserResult get(UserModel user) throws Exception {
        //需要校验的字段
        String message = CommonUtil.checkFiled(user, PATH, CheckMethod.GET);
        if (null != message) {
            return (UserResult) GsonUtil.fromJson(message, UserResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(user.id, UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/info.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (UserResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GET, HttpUtil.returnResult(conn, rongCloud.getConfig())), UserResult.class);
    }

}