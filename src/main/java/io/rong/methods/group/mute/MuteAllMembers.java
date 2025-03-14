package io.rong.methods.group.mute;

import io.rong.RongCloud;
import io.rong.methods.group.ban.user.User;
import io.rong.methods.group.ban.whitelist.Whitelist;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.response.GroupMuteAllMembersCheckResult;
import io.rong.models.response.GroupMuteAllMembersListResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 * Group Mute Service
 * Mute all members in a group. If certain users need to speak while the group is muted, add them to the group mute allowlist.
 *
 * */
public class MuteAllMembers {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "group/mute/all";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;
    public User user;
    public Whitelist whitelist;

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public MuteAllMembers(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;
        this.user = new User(appKey,appSecret,rongCloud);
        this.whitelist = new Whitelist(appKey,appSecret,rongCloud);

    }
    /**
     * Add group mute method
     *
     * @param groupIds: Group ID
     * @return Result
     **/
    public Result add(String[] groupIds) throws Exception {
        String message = CommonUtil.checkParam("id",groupIds,PATH,CheckMethod.ADD);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        for(String groupId : groupIds){
            sb.append("&groupId=").append(URLEncoder.encode(groupId, UTF8));

        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/ban/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.ADD,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * Query muted groups method
     *
     * @return ListGagGroupUserResult
     **/
    public Result getList() throws Exception {
        StringBuilder sb = new StringBuilder();
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/ban/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (GroupMuteAllMembersListResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn, rongCloud.getConfig())), GroupMuteAllMembersListResult.class);
    }
    /**
     * Query muted groups method
     *
     * @param groupIds: Group IDs. (Required)
     *
     * @return ListGagGroupUserResult
     **/
    public Result check(String[] groupIds) throws Exception {
        String message = CommonUtil.checkParam("id",groupIds,PATH,CheckMethod.ADD);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        for(String groupId : groupIds){
            sb.append("&groupId=").append(URLEncoder.encode(groupId, UTF8));

        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/ban/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (GroupMuteAllMembersCheckResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn, rongCloud.getConfig())), GroupMuteAllMembersCheckResult.class);
    }

    /**
     * Remove global group mute method
     *
     * @param  groupIds: Group ID (required)
     *
     * @return ResponseResult
     **/
    public Result remove(String[] groupIds) throws Exception {
        String message = CommonUtil.checkParam("id",groupIds,PATH,CheckMethod.ADD);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        for(String groupId : groupIds){
            sb.append("&groupId=").append(URLEncoder.encode(groupId, UTF8));

        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/ban/rollback.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }
}