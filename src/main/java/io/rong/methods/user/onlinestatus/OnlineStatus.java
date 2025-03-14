package io.rong.methods.user.onlinestatus;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.response.CheckOnlineResult;
import io.rong.models.user.UserModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 *
 *  User Online Status
 *
 * @version
 * */
public class OnlineStatus {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "user/online-status";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public OnlineStatus(String appKey, String appSecret,RongCloud rongCloud) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.rongCloud = rongCloud;

    }
    /**
     * Check User Online Status
     * Avoid frequent or looped calls to this API. Instead, choose an appropriate frequency and timing for calls, as this API has rate limits in place.
     *
     * url /user/checkOnline
     *
     * @param  user: User ID (required)
     *
     * @return CheckOnlineResult
     **/
    public CheckOnlineResult check(UserModel user) throws Exception {
        // Parameter validation
        String message = CommonUtil.checkFiled(user, PATH, CheckMethod.CHECK);
        if (null != message) {
            return (CheckOnlineResult) GsonUtil.fromJson(message, CheckOnlineResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(user.id.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/checkOnline.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (CheckOnlineResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.CHECK,HttpUtil.returnResult(conn, rongCloud.getConfig())), CheckOnlineResult.class);
    }
}
