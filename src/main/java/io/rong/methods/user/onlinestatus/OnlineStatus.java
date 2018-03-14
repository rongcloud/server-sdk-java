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
 * 敏感词服务
 * docs: "http://www.rongcloud.cn/docs/server.html#sensitiveword"
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
    public OnlineStatus(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * 检查用户在线状态 方法（每秒钟限100次）
     * 请不要频繁循环调用此接口，而是选择合适的频率和时机调用，此接口设置了一定的频率限制。
     *
     * url /user/checkOnline
     * docs http://www.rongcloud.cn/docs/server.html#user_check_online
     *
     * @param  user:用户 id(必传)
     *
     * @return CheckOnlineResult
     **/
    public CheckOnlineResult check(UserModel user) throws Exception {
        //参数校验
        String message = CommonUtil.checkFiled(user,PATH, CheckMethod.CHECK);
        if(null != message){
            return (CheckOnlineResult)GsonUtil.fromJson(message,CheckOnlineResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(user.id.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret,
                "/user/checkOnline.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CheckOnlineResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.CHECK,HttpUtil.returnResult(conn)), CheckOnlineResult.class);
    }
}
