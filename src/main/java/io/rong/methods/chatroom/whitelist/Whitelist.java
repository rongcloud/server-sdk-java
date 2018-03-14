package io.rong.methods.chatroom.whitelist;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.models.CheckMethod;
import io.rong.models.response.ResponseResult;
import io.rong.models.CommonConstrants;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 *
 * 聊天室用户白名单服务
 * docs: "http://www.rongcloud.cn/docs/server.html#chatroom_user_whitelist"
 *
 * */
public class Whitelist {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "chatroom/whitelist";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Whitelist(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * 添加聊天室白名单成员方法
     *
     * @param  userId:聊天室中用户 Id，可提交多个，聊天室中白名单用户最多不超过 5 个。（必传）
     * @param  chatroomId:聊天室 Id。（必传）
     *
     * @return ResponseResult
     **/
    public ResponseResult add(String chatroomId, String[] userId) throws Exception {
        String message = CommonUtil.checkParam("id",chatroomId,PATH, CheckMethod.ADD);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }

        if (userId == null) {
            throw new ParamException(CommonConstrants.RCLOUD_PARAM_NULL,"/chatroom/user/whitelist/add", "Paramer 'userId' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));

        for (int i = 0 ; i< userId.length; i++) {
            String child  = userId[i];
            sb.append("&userId=").append(URLEncoder.encode(child, UTF8));
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/user/whitelist/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.ADD,HttpUtil.returnResult(conn)), ResponseResult.class);
    }
}
