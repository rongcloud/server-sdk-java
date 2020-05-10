package io.rong.methods.user.whitelist;

import io.rong.RongCloud;
import io.rong.models.response.PWhiteListResult;
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
 * 用户白名单服务
 * docs: "http://www.rongcloud.cn/docs/server.html#whitelist"
 *
 * @author RongCloud
 */
public class Whitelist {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "user/whitelist";
    private static final String CONTENTTYPE = "application/x-www-form-urlencoded";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public Whitelist(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;
    }

    /**
     * 添加用户到白名单方法（每秒钟限 100 次）
     *
     * @param user:用户 Id,whitelist（必传）
     * @return ResponseResult
     **/
    public Result add(UserModel user) throws Exception {
        String message = CommonUtil.checkFiled(user, PATH, CheckMethod.ADD);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        String body = createBodyContent(user);
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/whitelist/add.json");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.ADD, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * 获取某用户的白名单列表方法（每秒钟限 100 次）
     *
     * @param user:用户 Id。（必传）
     * @return QueryBlacklistUserResult
     **/
    public PWhiteListResult getList(UserModel user) throws Exception {
        String message = CommonUtil.checkFiled(user, PATH, CheckMethod.GETLIST);
        if (null != message) {
            return (PWhiteListResult) GsonUtil.fromJson(message, PWhiteListResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(user.getId(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1);
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/whitelist/query.json");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (PWhiteListResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GETLIST, HttpUtil.returnResult(conn, rongCloud.getConfig())), PWhiteListResult.class);
    }

    /**
     * 从白名单中移除用户方法（每秒钟限 100 次）
     *
     * @param user:用户 Id,whitelist（必传）
     * @return ResponseResult
     **/
    public Result remove(UserModel user) throws Exception {
        String message = CommonUtil.checkFiled(user, PATH, CheckMethod.REMOVE);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        String body = createBodyContent(user);
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/whitelist/remove.json");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.REMOVE, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * 创建添加、移除白名单的参数
     *
     * @param user
     * @return
     * @throws Exception
     */
    private String createBodyContent(UserModel user) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(user.getId(), UTF8));
        for (UserModel whiteUser : user.getWhitelist()) {
            sb.append("&whiteUserId=").append(URLEncoder.encode(whiteUser.getId(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1);
        }
        return body;
    }
}
