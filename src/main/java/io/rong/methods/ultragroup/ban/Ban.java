package io.rong.methods.ultragroup.ban;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.response.CheckStatusResult;
import io.rong.models.response.GroupBanResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * 超级群禁言状态
 * 超级群全局禁言
 * docs : https://www.rongcloud.cn/docs/server.html#group_ban
 *
 * */
public class Ban {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "ultragroup/ban";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;
    public User user;

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

    }
    /**
     * 设置超级群禁言状态
     *
     * @param groupId:超级群 ID
     * @param status:禁言状态
     *
     * @return Result
     **/
    public Result set(String groupId, boolean status) throws Exception {
        String message = CommonUtil.checkParam("id",groupId,PATH,CheckMethod.SET);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
        sb.append("&status=").append(URLEncoder.encode(String.valueOf(status), UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/globalbanned/set.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.SET,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * 查询超级群禁言状态
     *
     * @param  groupId:超级群Id。（必传）
     *
     * @return boolean
     **/
    public Result check(String groupId) throws Exception {
        String message = CommonUtil.checkParam("id",groupId,PATH,CheckMethod.GET);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/globalbanned/get.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (CheckStatusResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn, rongCloud.getConfig())), CheckStatusResult.class);
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
        String message = CommonUtil.checkParam("id",groupIds,PATH,CheckMethod.REMOVE);
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
