package io.rong.methods.user.block;

import io.rong.RongCloud;
import io.rong.models.response.BlockUserResult;
import io.rong.models.Result;
import io.rong.models.CheckMethod;
import io.rong.models.response.ResponseResult;
import io.rong.models.user.UserModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * 用户封禁服务
 * docs: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
 */
public class Block {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "user/block";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public Block(String appKey, String appSecret, RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;

    }

    /**
     * 封禁用户方法
     *
     * @param user :用户信息 Id，minute（必传）
     * @return Result
     **/
    public Result add(UserModel user) throws Exception {

        String message = CommonUtil.checkFiled(user, PATH, CheckMethod.ADD);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(user.getId().toString(), UTF8));
        sb.append("&minute=").append(URLEncoder.encode(user.getMinute().toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/block.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.ADD, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * 解除用户封禁方法
     *
     * @param userId:用户 Id。（必传）
     * @return ResponseResult
     **/
    public ResponseResult remove(String userId) throws Exception {
        //参数校验
        String message = CommonUtil.checkParam("id", userId, PATH, CheckMethod.REMOVE);
        if (null != message) {
            return (ResponseResult) GsonUtil.fromJson(message, ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/unblock.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.REMOVE, HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * 获取被封禁用户方法
     *
     * @return QueryBlockUserResult
     **/
    public Result getList() throws Exception {
        return getList(null, null);
    }

    /**
     * 获取被封禁用户方法
     *
     * @param size:分页获取封禁用户列表时每页行数，不传时默认为 50 条。（非必传）
     * @param page:分页获取封禁用户列表时当前页数。（非必传）
     * @return QueryBlockUserResult
     **/
    public Result getList(Integer size, Integer page) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (size != null) {
            sb.append("size=").append(URLEncoder.encode(size.toString(), UTF8));
        }
        if (page != null) {
            sb.append("&page=").append(URLEncoder.encode(page.toString(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/block/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (BlockUserResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GETLIST, HttpUtil.returnResult(conn, rongCloud.getConfig())), BlockUserResult.class);
    }


}
