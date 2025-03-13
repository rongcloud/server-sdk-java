package io.rong.methods.user.remark;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.response.GetTagResult;
import io.rong.models.response.GetUserRemarksResult;
import io.rong.models.response.GroupBanWhitelistResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.user.GetTagModel;
import io.rong.models.user.TagModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import io.rong.util.JsonUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * User Remark Service
 */
public class Remark {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "user/remark";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public Remark(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud  = rongCloud;

    }

    /**
     * Add remarks for users in the application. If a user already has a remark, adding a new remark will overwrite the previous one.
     *
     * @param remarks User remark information
     * @return ResponseResult
     **/
    public ResponseResult set(String userId, String remarks) throws Exception {
        String message = CommonUtil.checkParam("id", userId, PATH, CheckMethod.SET);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("userId="+ userId);
        sb.append("&remarks="+ remarks);
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/remarks/set.json");

        HttpUtil.setBodyParameter(sb.toString(), conn, rongCloud.getConfig());
        return (ResponseResult) GsonUtil.fromJson(
                CommonUtil.getResponseByCode(PATH, CheckMethod.SET, HttpUtil.returnResult(conn, rongCloud.getConfig())),
                ResponseResult.class);
    }

    /**
     * Deletes the remarks for a user.
     *
     * @param targetId The ID of the user whose remarks are to be deleted.
     * @return ResponseResult
     **/
    public ResponseResult del(String userId, String targetId) throws Exception {
        String message = CommonUtil.checkParam("id", userId, PATH, CheckMethod.SET);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("userId="+ userId);
        sb.append("&targetId="+ targetId);
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/remarks/del.json");

        HttpUtil.setBodyParameter(sb.toString(), conn, rongCloud.getConfig());
        return (ResponseResult) GsonUtil.fromJson(
                CommonUtil.getResponseByCode(PATH, CheckMethod.DEL, HttpUtil.returnResult(conn, rongCloud.getConfig())),
                ResponseResult.class);
    }


    /**
     * Retrieves all remarks for a user.
     *
     * @param userId The ID of the user whose remarks are to be retrieved.
     * @return GetUserRemarksResult
     **/
    public GetUserRemarksResult get(String userId, int page, int size) throws Exception {
        String message = CommonUtil.checkParam("id",userId, PATH, CheckMethod.SET);
        if (null != message) {
            return (GetUserRemarksResult) GsonUtil.fromJson(message, GetUserRemarksResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("userId="+userId);
        sb.append("&page="+page);
        sb.append("&size="+size);


        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/remarks/get.json");

        HttpUtil.setBodyParameter(sb.toString(), conn, rongCloud.getConfig());
        return (GetUserRemarksResult) GsonUtil.fromJson(
                CommonUtil.getResponseByCode(PATH, CheckMethod.GET, HttpUtil.returnResult(conn, rongCloud.getConfig())),
                GetUserRemarksResult.class);
    }

    public GetUserRemarksResult get(String userId) throws Exception {
        return get(userId, 1, 50);
    }

    public GetUserRemarksResult get(String userId, int page) throws Exception {
        return get(userId, page ,50);
    }

}