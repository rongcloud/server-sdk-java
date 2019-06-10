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
 * 用户标签服务 docs: "https://www.rongcloud.cn/docs/push_service.html#user_tag"
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
     * 为应用中的用户添加标签，如果某用户已经添加了标签，再次对用户添加标签时将覆盖之前设置的标签内容。
     *
     * @param tag 用户标签信息
     * @return ResponseResult
     **/
    public Result set(TagModel tag) throws Exception {
        String message = CommonUtil.checkFiled(tag, PATH, CheckMethod.SET_TAG);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret,
                "/user/tag/set.json", "application/json");

        HttpUtil.setBodyParameter(tag.toString(), conn);
        return (ResponseResult) GsonUtil.fromJson(
                CommonUtil.getResponseByCode(PATH, CheckMethod.SET_TAG, HttpUtil.returnResult(conn)),
                ResponseResult.class);
    }

    /**
     * 为应用中的用户添加标签，如果某用户已经添加了标签，再次对用户添加标签时将覆盖之前设置的标签内容。
     *
     * @param tags 多个用户标签信息
     * @return ResponseResult
     **/
    public Result batchSet(BatchTagModel batchTag) throws Exception {
        String message = CommonUtil.checkFiled(batchTag, PATH, CheckMethod.BATCH_SET_TAG);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret,
                "/user/tag/batch/set.json", "application/json");

        HttpUtil.setBodyParameter(batchTag.toString(), conn);
        return (ResponseResult) GsonUtil.fromJson(
                CommonUtil.getResponseByCode(PATH, CheckMethod.BATCH_SET_TAG, HttpUtil.returnResult(conn)),
                ResponseResult.class);
    }

    /**
     * 查询用户所有标签功能，支持批量查询每次最多查询 50 个用户。
     *
     * @param tags 多个用户ids
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

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret,
                "/user/tags/get.json", "application/x-www-form-urlencoded");

        HttpUtil.setBodyParameter(sb, conn);
        return (GetTagResult) GsonUtil.fromJson(
                CommonUtil.getResponseByCode(PATH, CheckMethod.GET_TAG, HttpUtil.returnResult(conn)),
                GetTagResult.class);
    }

}