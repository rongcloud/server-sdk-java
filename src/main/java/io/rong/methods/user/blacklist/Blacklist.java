package io.rong.methods.user.blacklist;

import io.rong.RongCloud;
import io.rong.methods.BaseMethod;
import io.rong.models.response.BlackListResult;
import io.rong.models.response.PagingQueryBlacklistResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.Result;
import io.rong.models.CheckMethod;
import io.rong.models.user.UserModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 *
 * 用户黑名单服务
 * docs: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
 *
 * @author RongCloud
 * @version
 * */
public class Blacklist extends BaseMethod {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "user/blacklist";

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    @Override
    protected void initPath() {
        super.path = PATH;
    }

    public Blacklist(String appKey, String appSecret,RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;
        initPath();
    }

    /**
     * 添加用户到黑名单方法
     *
     * @param  user:用户 Id,blacklist（必传）
     *
     * @return ResponseResult
     **/
    public Result add(UserModel user) throws Exception {

        String message = CommonUtil.checkFiled(user,PATH,CheckMethod.ADD);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(user.getId().toString(), UTF8));
        for(UserModel blackUser:user.getBlacklist()){
            sb.append("&blackUserId=").append(URLEncoder.encode(blackUser.getId().toString(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/blacklist/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.ADD,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * 获取某用户的黑名单列表方法
     *
     * @param  user:用户 Id。（必传）
     *
     * @return QueryBlacklistUserResult
     **/
    public BlackListResult getList(UserModel user) throws Exception {
        String message = CommonUtil.checkFiled(user,PATH,CheckMethod.GETLIST);
        if(null != message){
            return (BlackListResult)GsonUtil.fromJson(message,BlackListResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(user.getId().toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/blacklist/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (BlackListResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn, rongCloud.getConfig())), BlackListResult.class);
    }

    public PagingQueryBlacklistResult pagingQueryBlacklist(String userId, String pageToken, Integer size) throws Exception {
        String method = CheckMethod.PAGING_QUERY_BLACKLIST;
        PagingQueryBlacklistResult result = checkParam("userId", userId, method, PagingQueryBlacklistResult.class);
        if (result != null) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", userId);
        addFormParam(sb, "&pageToken=", pageToken);
        addFormParam(sb, "&size=", size);
        String body = sb.toString();
        return doRequest("/user/blacklist/query.json", body, method, PagingQueryBlacklistResult.class);
    }

    /**
     * 从黑名单中移除用户方法
     *
     * @param  user:用户 Id,blacklist（必传）
     *
     * @return ResponseResult
     **/
    public Result remove(UserModel user) throws Exception {
        String message = CommonUtil.checkFiled(user,PATH,CheckMethod.REMOVE);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(user.getId().toString(), UTF8));
        for(UserModel blackUser:user.getBlacklist()){
            sb.append("&blackUserId=").append(URLEncoder.encode(blackUser.getId().toString(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/blacklist/remove.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }
}
