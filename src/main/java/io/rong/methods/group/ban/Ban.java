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
 * 群组禁言服务
 * 设置某一群组全部成员禁言，如果在群组全部成员禁言状态下，需要某些用户可以发言时，可将此用户加入到群禁言用户白名单中。
 * docs : https://www.rongcloud.cn/docs/server.html#group_ban
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
     * 添加群禁言方法
     *
     * @param groupIds:群组 ID
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

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/group/ban/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.ADD,HttpUtil.returnResult(conn)), ResponseResult.class);
    }

    /**
     * 查询被禁言群方法
     *
     * @return ListGagGroupUserResult
     **/
    public Result getList() throws Exception {
        StringBuilder sb = new StringBuilder();
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/group/ban/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (GroupBanResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn)), GroupBanResult.class);
    }
    /**
     * 查询被禁言群方法
     *
     * @param  groupIds:群组Id。（必传）
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

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/group/ban/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (GroupBanResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn)), GroupBanResult.class);
    }

    /**
     * 移除全局群禁言方法
     *
     * @param  groupIds:群组 ID（必传）
     *
     * @return ResponseResult
     **/
    public Result remove(String[] groupIds) throws Exception {
        //参数校验
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

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/group/ban/rollback.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn)), ResponseResult.class);
    }
}
