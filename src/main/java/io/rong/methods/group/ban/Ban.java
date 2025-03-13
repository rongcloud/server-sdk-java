package io.rong.methods.group.ban;

import io.rong.RongCloud;
import io.rong.methods.group.ban.user.User;
import io.rong.methods.group.ban.whitelist.Whitelist;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.response.GroupBanResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 * Group Ban Service
 * Mute all members in a group. If certain users need to speak while the group is muted, add them to the group mute allowlist.
 * docs : https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
 *
 * */
public class Ban {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "group/ban";
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
    public Ban(String appKey, String appSecret, RongCloud rongCloud) {
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
     *
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
     * Retrieves the list of groups with banned users.
     *
     * @return ListGagGroupUserResult containing the list of groups with banned users.
     * @throws Exception if an error occurs during the process.
     **/
    public Result getList() throws Exception {
        StringBuilder sb = new StringBuilder();
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/group/ban/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (GroupBanResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn, rongCloud.getConfig())), GroupBanResult.class);
    }
    /**
     * Checks if users are banned in specified groups.
     *
     * @param groupIds The IDs of the groups to check for banned users. (Required)
     * @return ListGagGroupUserResult containing the list of banned users in the specified groups.
     * @throws Exception if an error occurs during the process.
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

        return (GroupBanResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn, rongCloud.getConfig())), GroupBanResult.class);
    }

    /**
     * Removes the global group mute
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
