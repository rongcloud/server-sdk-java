package io.rong.methods.chatroom.whitelist;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.response.ChatroomWhitelistMsgResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 *
 * 聊天室消息白名单服务
 * docs: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
 *
 * */
public class Messages {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "chatroom/whitelist/message";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Messages(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }
    /**
     * 添加聊天室消息白名单成员方法
     *
     * @param  objectNames:消息类型列表
     *
     * @return ResponseResult
     **/
    public ResponseResult add(String[] objectNames) throws Exception {
        String errMsg = CommonUtil.checkParam("type",objectNames,PATH, CheckMethod.ADD);
        if(null != errMsg){
            return (ResponseResult)GsonUtil.fromJson(errMsg,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i< objectNames.length; i++) {
            String child  = objectNames[i];
            sb.append("&objectnames=").append(URLEncoder.encode(child, UTF8));
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/whitelist/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.ADD,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * 删除聊天室消息白名单方法
     *
     * @param  objectNames:消息类型列表
     *
     * @return ResponseResult
     **/
    public ResponseResult remove(String[] objectNames) throws Exception {
        String errMsg = CommonUtil.checkParam("type",objectNames,PATH, CheckMethod.REMOVE);
        if(null != errMsg){
            return (ResponseResult)GsonUtil.fromJson(errMsg,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i< objectNames.length; i++) {
            String child  = objectNames[i];
            sb.append("&objectnames=").append(URLEncoder.encode(child, UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/whitelist/delete.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, rongCloud.getConfig());

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn, rongCloud.getConfig())), ResponseResult.class);
    }

    /**
     * 获取聊天室消息类型白名单列表
     *
     *
     * @return ResponseResult
     **/
    public ChatroomWhitelistMsgResult getList() throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getConfig(), appKey, appSecret, "/chatroom/whitelist/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter("", conn, rongCloud.getConfig());
        return (ChatroomWhitelistMsgResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn, rongCloud.getConfig())), ChatroomWhitelistMsgResult.class);
    }
}

