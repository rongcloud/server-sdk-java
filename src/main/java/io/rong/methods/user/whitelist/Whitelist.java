package io.rong.methods.user.whitelist;

import io.rong.RongCloud;
import io.rong.methods.BaseMethod;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.response.PWhiteListResult;
import io.rong.models.response.PagingQueryWhitelistResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.user.UserModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * User allowlist service
 * @author RongCloud
 */
public class Whitelist extends BaseMethod {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "user/whitelist";

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    @Override
    protected void initPath() {
        this.path = PATH;
    }

    public Whitelist(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;
        initPath();
    }

    /**
     * Add user to allowlist method
     *
     * @param user: User ID, whitelist (required)
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
     * Retrieves the whitelist for a specific user.
     *
     * @param user: User ID (required)
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
     * Paginates the user's whitelist.
     **/
    public PagingQueryWhitelistResult pagingQueryWhitelist(String userId, String pageToken, Integer size) throws Exception {
        String method = CheckMethod.PAGING_QUERY_WHITELIST;
        PagingQueryWhitelistResult result = checkParam("userId", userId, method, PagingQueryWhitelistResult.class);
        if (result != null) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        addFormParam(sb, "userId=", userId);
        addFormParam(sb, "&pageToken=", pageToken);
        addFormParam(sb, "&size=", size);
        String body = sb.toString();
        return doRequest("/user/whitelist/query.json", body, method, PagingQueryWhitelistResult.class);
    }

    /**
     * Method to remove a user from the allowlist
     *
     * @param user: User ID, whitelist (required)
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
     * Creates parameters for adding or removing a user from the allowlist
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
