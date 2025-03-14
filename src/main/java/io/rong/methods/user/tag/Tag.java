package io.rong.methods.user.tag;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.response.GetTagResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.user.BatchTagModel;
import io.rong.models.user.GetTagModel;
import io.rong.models.user.TagModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

/**
 * User Tag Service.
 */
public class Tag {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "user/tag";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public Tag(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud  = rongCloud;

    }

    /**
     * Add tags to users in the application. If a user already has tags, adding new tags will overwrite the previous ones.
     *
     * @param tag User tag information
     * @return ResponseResult
     **/
    public Result set(TagModel tag) throws Exception {
        String message = CommonUtil.checkFiled(tag, PATH, CheckMethod.SET_TAG);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/tag/set.json", "application/json");

        HttpUtil.setBodyParameter(tag.toString(), conn, rongCloud.getConfig());
        return (ResponseResult) GsonUtil.fromJson(
                CommonUtil.getResponseByCode(PATH, CheckMethod.SET_TAG, HttpUtil.returnResult(conn, rongCloud.getConfig())),
                ResponseResult.class);
    }

    /**
     * Add tags to users in the application. If a user already has tags, adding new tags will overwrite the previous ones.
     *
     * @param batchTag Multiple user tag information
     * @return ResponseResult
     **/
    public Result batchSet(BatchTagModel batchTag) throws Exception {
        String message = CommonUtil.checkFiled(batchTag, PATH, CheckMethod.BATCH_SET_TAG);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/tag/batch/set.json", "application/json");

        HttpUtil.setBodyParameter(batchTag.toString(), conn, rongCloud.getConfig());
        return (ResponseResult) GsonUtil.fromJson(
                CommonUtil.getResponseByCode(PATH, CheckMethod.BATCH_SET_TAG, HttpUtil.returnResult(conn, rongCloud.getConfig())),
                ResponseResult.class);
    }

    /**
     * Query all tags for users. Supports batch querying with a maximum of 50 users per query.
     *
     * @param getTag Multiple user IDs
     * @return GetTagResult
     **/
    public GetTagResult get(GetTagModel getTag) throws Exception {
        String message = CommonUtil.checkFiled(getTag, PATH, CheckMethod.GET_TAG);
        if (null != message) {
            return (GetTagResult) GsonUtil.fromJson(message, GetTagResult.class);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getTag.getUserIds().length; i++) {
            String userId = getTag.getUserIds()[i];
            if (null != userId) {
                sb.append("&userIds=").append(URLEncoder.encode(userId, UTF8));
            }
        }

        if (sb.indexOf("&") == 0) {
            sb.deleteCharAt(0);
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/tags/get.json", "application/x-www-form-urlencoded");

        HttpUtil.setBodyParameter(sb.toString(), conn, rongCloud.getConfig());
        return (GetTagResult) GsonUtil.fromJson(
                CommonUtil.getResponseByCode(PATH, CheckMethod.GET_TAG, HttpUtil.returnResult(conn, rongCloud.getConfig())),
                GetTagResult.class);
    }

}