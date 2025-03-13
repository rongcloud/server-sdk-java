package io.rong.methods.user.blockpushperiod;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.response.BlockPushPeriodResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.user.BlockPushPeriodModel;
import io.rong.models.user.UserModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 *
 * User Do Not Disturb Period
 * docs: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
 *
 * @author RongCloud
 * @version
 * */
public class BlockPushPeriod {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "user/blockpushperiod";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public BlockPushPeriod(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;

    }

    /**
     * Add User Do Not Disturb Period
     *
     * @param  user: User ID, startTime, period (required)
     * @return ResponseResult
     **/
    public Result add(BlockPushPeriodModel user) throws Exception {

        String message = CommonUtil.checkFiled(user,PATH,CheckMethod.ADD);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(user.getId(), UTF8));
        sb.append("&startTime=").append(URLEncoder.encode(user.getStartTime(), UTF8));
        sb.append("&period=").append(URLEncoder.encode(user.getPeriod().toString(), UTF8));
        sb.append("&level=").append(URLEncoder.encode(user.getLevel().toString(), UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/blockPushPeriod/set.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.ADD,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * Retrieves the Do Not Disturb period for a specific user
     *
     * @param  user: User ID (required)
     *
     **/
    public BlockPushPeriodResult getList(UserModel user) throws Exception {
        String message = CommonUtil.checkFiled(user,PATH,CheckMethod.GETLIST);
        if(null != message){
            return (BlockPushPeriodResult)GsonUtil.fromJson(message,BlockPushPeriodResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(user.getId(), UTF8));
        String body = sb.toString();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/blockPushPeriod/get.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (BlockPushPeriodResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn, rongCloud.getConfig())), BlockPushPeriodResult.class);
    }

    /**
     * Removes the Do Not Disturb period for a specific user
     *
     * @param  user: User ID (required)
     **/
    public Result remove(UserModel user) throws Exception {
        String message = CommonUtil.checkFiled(user,PATH,CheckMethod.REMOVE);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(user.getId(), UTF8));
        String body = sb.toString();


        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/blockPushPeriod/delete.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }
}
