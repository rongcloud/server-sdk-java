package io.rong.methods.push;

import java.net.HttpURLConnection;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.push.BroadcastModel;
import io.rong.models.push.PushModel;
import io.rong.models.response.PushResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

/**
 * 推送服务
 * <p>
 * docs https://www.rongcloud.cn/docs/push_service.html#broadcast
 * https://www.rongcloud.cn/docs/push_service.html#push
 */
public class Push {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "push";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }

    public Push(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    /**
     * 广播
     *
     * @param broadcast 广播数据
     * @return PushResult
     **/
    public PushResult send(BroadcastModel broadcast) throws Exception {
        // 需要校验的字段
        String message = CommonUtil.checkFiled(broadcast, PATH, CheckMethod.BROADCAST);
        if (null != message) {
            return (PushResult) GsonUtil.fromJson(message, PushResult.class);
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/push.json",
                "application/json");
        HttpUtil.setBodyParameter(broadcast.toString(), conn);

        return (PushResult) GsonUtil.fromJson(
                CommonUtil.getResponseByCode(PATH, CheckMethod.BROADCAST, HttpUtil.returnResult(conn)),
                PushResult.class);
    }

    /**
     * 推送
     *
     * @param push 推送数据
     * @return PushResult
     **/
    public PushResult send(PushModel push) throws Exception {
        // 需要校验的字段
        String message = CommonUtil.checkFiled(push, PATH, CheckMethod.PUSH);
        if (null != message) {
            return (PushResult) GsonUtil.fromJson(message, PushResult.class);
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/push.json",
                "application/json");

        HttpUtil.setBodyParameter(push.toString(), conn);

        return (PushResult) GsonUtil.fromJson(
                CommonUtil.getResponseByCode(PATH, CheckMethod.PUSH, HttpUtil.returnResult(conn)),
                PushResult.class);
    }

}