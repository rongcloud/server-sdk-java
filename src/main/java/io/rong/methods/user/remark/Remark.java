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
 * 用户备注服务
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
     * 为应用中的用户添加备注，如果某用户已经添加了备注，再次对用户添加备注时将覆盖之前设置的备注内容。
     *
     * @param remarks 用户备注信息
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
     * 删除用户备注
     *
     * @param targetId 删除用户备注
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
     * 查询用户所有备注功能
     *
     * @param userId
     * @return GetTagResult
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