package io.rong.methods.chatroom.demotion;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.response.ChatroomDemotionMsgResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 *
 * Chatroom priority service
 *
 * */
public class Demotion {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "chatroom/demotion";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Demotion(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * Add in-app chatroom demotion messages
     *
     * @param  objectName: Message type, up to 5 types can be submitted at a time, with a maximum of 20 types allowed. (Required)
     *
     * @return ResponseResult
     **/
    public ResponseResult add(String[] objectName) throws Exception {
        String message = CommonUtil.checkParam("type",objectName,PATH,CheckMethod.ADD);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i< objectName.length; i++) {
            String child  = objectName[i];
            sb.append("&objectName=").append(URLEncoder.encode(child, UTF8));
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/chatroom/message/priority/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.ADD,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * Remove downgraded messages from in-app chatrooms
     *
     * @param  objectNames: List of message types to be destroyed. (Required)
     *
     * @return ResponseResult
     **/
    public ResponseResult remove(String[] objectNames) throws Exception {
        String message = CommonUtil.checkParam("type",objectNames,PATH,CheckMethod.REMOVE);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i< objectNames.length; i++) {
            String child  = objectNames[i];
            sb.append("&objectName=").append(URLEncoder.encode(child, UTF8));
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/chatroom/message/priority/remove.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }
    /**
     * Get downgraded messages from in-app chatrooms
     *
     *
     * @return ResponseResult
     **/
    public ChatroomDemotionMsgResult getList() throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret,
                "/chatroom/message/priority/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter("", conn, rongCloud.getConfig());
        return (ChatroomDemotionMsgResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn, rongCloud.getConfig())), ChatroomDemotionMsgResult.class);
    }
}
