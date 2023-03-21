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
 *  在线状态
 * docs: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
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
     * 检查用户在线状态方法
     * 请不要频繁循环调用此接口，而是选择合适的频率和时机调用，此接口设置了一定的频率限制。
     *
     * url /user/checkOnline
     * docs https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
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

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/user/checkOnline.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (CheckOnlineResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.CHECK,HttpUtil.returnResult(conn, rongCloud.getConfig())), CheckOnlineResult.class);
    }
}
