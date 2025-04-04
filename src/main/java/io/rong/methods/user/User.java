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
 * User Service
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
     * Get Token method
     * url  "/user/getToken"
     *
     * @param user User information including id, name, portrait (required)
     * @return TokenResult
     **/
    public TokenResult register(UserModel user) throws Exception {
        // Fields to be validated
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
     * Refresh user information
     * url  "/user/refresh"
     *
     * @param user User information including id, name, and portrait (required)
     * @return ResponseResult
     **/
    public Result update(UserModel user) throws Exception {
        // Fields to be validated
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
     * Query user information method
     * url  "/user/info"
     *
     * @param user User information id (required)
     * @return UserResult
     * @throws Exception
     */
    public UserResult get(UserModel user) throws Exception {
        // Fields to be validated
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
     * Deactivate user
     * @param user
     * @return
     * @throws Exception
     */
    public ResponseResult deactivate(UserModel user) throws Exception {
        // Fields to be validated
        String message = CommonUtil.checkFiled(user, PATH, CheckMethod.GET);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(user.id, UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/deactivate.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GET, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }
    public UserDeactivateResult deactivateList() throws Exception {
        return deactivateList(1, 50);
    }

    /**
     * Query deactivated users
     * @param page The page number
     * @param pageSize The number of users per page
     * @return UserDeactivateResult
     * @throws Exception
     */
    public UserDeactivateResult deactivateList(int page, int pageSize) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("pageNo=").append(page);
        sb.append("&pageSize=").append(pageSize);
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/deactivate/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());
        return (UserDeactivateResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GET, HttpUtil.returnResult(conn, rongCloud.getConfig())), UserDeactivateResult.class);
    }

    /**
     * Activate a user
     * @param user The user model containing the user ID
     * @return ResponseResult
     * @throws Exception
     */
    public ResponseResult activate(UserModel user) throws Exception {
        // Fields to be validated
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
     * Reactivate a user ID
     * @param user The user model containing the user ID
     * @return ResponseResult
     * @throws Exception
     */
    public ResponseResult reactivate(UserModel user) throws Exception {
        // Fields to be validated
        String message = CommonUtil.checkFiled(user, PATH, CheckMethod.REACTIVATE);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        for (String userId : user.getIds()) {
            sb.append(",").append(URLEncoder.encode(userId, UTF8));
        }
        String body = sb.toString();
        if (body.indexOf(",") == 0) {
            body = "userId=" + body.substring(1);
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
          "/user/reactivate.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GET, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * Query the groups a user belongs to
     * url  "/user/group/query"
     *
     * @param user User information, id (required)
     * @return UserGroupQueryResult
     * @throws Exception
     */
    public UserGroupQueryResult getGroups(UserModel user) throws Exception {
        // Fields to be validated
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
     * Token expiration
     * url  "/user/refresh"
     *
     * @param user userId (required) time (required)
     * @return ResponseResult
     **/
    public Result expire(ExpireModel user) throws Exception {
        // Fields to validate
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