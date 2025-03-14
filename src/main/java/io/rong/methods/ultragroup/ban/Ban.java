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
 * Ultra group ban status
 * Global mute for ultra groups
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
     * Set ultra group ban status
     *
     * @param groupId: Ultra group ID
     * @param status: Ban status
     *
     * @return Result
     **/
    public Result set(String groupId, boolean status) throws Exception {
        return set(groupId, status, "");
    }
    public Result set(String groupId, boolean status, String busChannel) throws Exception {
        String message = CommonUtil.checkParam("id",groupId,PATH,CheckMethod.SET);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
        sb.append("&busChannel=").append(URLEncoder.encode(busChannel, UTF8));
        sb.append("&status=").append(URLEncoder.encode(String.valueOf(status), UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/globalbanned/set.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.SET,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }


/**
 * Query the mute status of an ultra group
 *
 * @param  groupId: The ID of the ultra group. (Required)
 *
 * @return boolean
 **/

    public Result check(String groupId) throws Exception {
        return check(groupId, "");
    }
    public Result check(String groupId, String busChannel) throws Exception {
        String message = CommonUtil.checkParam("id",groupId,PATH,CheckMethod.GET);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
        sb.append("&busChannel=").append(URLEncoder.encode(busChannel, UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/ultragroup/globalbanned/get.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (CheckStatusResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GET,HttpUtil.returnResult(conn, rongCloud.getConfig())), CheckStatusResult.class);
    }
}
