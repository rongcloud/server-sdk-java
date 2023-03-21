package io.rong.methods.user;

import com.google.gson.JsonParseException;
import io.rong.RongCloud;
import io.rong.methods.user.blacklist.Blacklist;
import io.rong.methods.user.block.Block;
import io.rong.methods.user.blockpushperiod.BlockPushPeriod;
import io.rong.methods.user.mute.MuteChatrooms;
import io.rong.methods.user.mute.MuteGroups;
import io.rong.methods.user.onlinestatus.OnlineStatus;
import io.rong.methods.user.remark.Remark;
import io.rong.methods.user.whitelist.Whitelist;
import io.rong.methods.user.chat.Ban;
import io.rong.models.*;
import io.rong.models.response.*;
import io.rong.models.user.ExpireModel;
import io.rong.models.user.UserModel;
import io.rong.methods.user.tag.Tag;
import io.rong.util.*;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSONException;


/**
 * 用户服务
 * docs : https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
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
    public Remark remark;
    public MuteChatrooms muteChatrooms;
    public MuteGroups muteGroups;
    public Ban ban;
    public BlockPushPeriod blockPushPeriod;
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
        this.ban = new Ban(appKey, appSecret, rongCloud);
        this.remark = new Remark(appKey, appSecret, rongCloud);
        this.blockPushPeriod = new BlockPushPeriod(appKey, appSecret, rongCloud);
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
        if (user.getPortrait() != null) {
            sb.append("&portraitUri=").append(URLEncoder.encode(user.portrait, UTF8));
        }


        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/user/getToken.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        TokenResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (TokenResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.REGISTER, response), TokenResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new TokenResult(500, "", user.id, "request:" + conn.getURL() +
                    " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

    /**
     * 刷新用户信息方法
     * url  "/user/refresh"
     * docs https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
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
     * docs https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
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

    /**
     * 用户注销
     * @param user
     * @return
     * @throws Exception
     */
    public ResponseResult abandon(UserModel user) throws Exception {
        //需要校验的字段
        String message = CommonUtil.checkFiled(user, PATH, CheckMethod.GET);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(user.id, UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/abandon.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GET, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }
    public UserAbandonResult abandonList() throws Exception {
        return abandonList(1, 50);
    }

    /**
     * 注销用户列表
     * @param page
     * @return
     * @throws Exception
     */
    public UserAbandonResult abandonList(int page, int pageSize) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("pageNo=").append(page);
        sb.append("&pageSize=").append(pageSize);
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/abandon/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        return (UserAbandonResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GET, HttpUtil.returnResult(conn, rongCloud.getConfig())), UserAbandonResult.class);
    }

    /**
     * 用户激活
     * @param user
     * @return
     * @throws Exception
     */
    public ResponseResult activate(UserModel user) throws Exception {
        //需要校验的字段
        String message = CommonUtil.checkFiled(user, PATH, CheckMethod.GET);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(user.id, UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/activate.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GET, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * 查询用户所在群组
     * url  "/user/group/query"
     * docs "https://docs.rongcloud.cn/v4/views/im/server/group/basic.html#query"
     *
     * @param user 用户信息 id (必传)
     * @return UserGroupQueryResult
     * @throws Exception
     */
    public UserGroupQueryResult getGroups(UserModel user) throws Exception {
        //需要校验的字段
        String message = CommonUtil.checkFiled(user, PATH, CheckMethod.GET_GROUPS);
        if (null != message) {
            return (UserGroupQueryResult) GsonUtil.fromJson(message, UserGroupQueryResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(user.id, UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/group/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        UserGroupQueryResult result = null;
        String response = "";
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (UserGroupQueryResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GET_GROUPS, response), UserGroupQueryResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new UserGroupQueryResult(500, "request:" + conn.getURL() +
                    " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

    /**
     * Token 失效
     * url  "/user/refresh"
     * docs "https://docs.rongcloud.cn/v4/5X/views/im/server/user/expire.html"
     *
     * @param user userId(必传) time(必传)
     * @return ResponseResult
     **/
    public Result expire(ExpireModel user) throws Exception {
        //需要校验的字段
        String message = CommonUtil.checkFiled(user, PATH, CheckMethod.EXPIRE);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        for (String userId : user.getUserId()) {
            sb.append("&userId=").append(URLEncoder.encode(userId, UTF8));
        }
        sb.append("&time=").append(URLEncoder.encode(user.getTime().toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1);
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/token/expire.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        String response = "";
        ResponseResult result;
        try {
            response = HttpUtil.returnResult(conn, rongCloud.getConfig());
            result = (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.EXPIRE, response), ResponseResult.class);
        } catch (JSONException | JsonParseException | IllegalStateException e) {
            rongCloud.getConfig().errorCounter.incrementAndGet();
            result = new ResponseResult(500, "request:" + conn.getURL() + " ,response:" + response + " ,JSONException:" + e.getMessage());
        }
        result.setReqBody(body);
        return result;
    }

}