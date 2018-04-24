package io.rong.methods.message.chatroom;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.response.ResponseResult;
import io.rong.models.message.ChatroomMessage;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 * 发送聊天室消息方法
 * docs : http://www.rongcloud.cn/docs/server.html#message_chatroom_publish
 * @author RongCloud
 *
 */
public class Chatroom {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "message/chatroom";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Chatroom(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }

    /**
     * 发送聊天室消息方法（一个用户向聊天室发送消息，单条消息最大 128k。每秒钟限 100 次。）
     *
     * @param  message:发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。融云消息类型在messages下，自定义消息继承BaseMessage即可（必传）
     *
     * @return ResponseResult
     * @throws Exception
     **/
    public ResponseResult send(ChatroomMessage message) throws Exception {

        String code = CommonUtil.checkFiled(message,PATH, CheckMethod.SEND);
        if(null != code){
            return (ResponseResult)GsonUtil.fromJson(code,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.getTargetId().toString(), UTF8));

        for (int i = 0 ; i< message.getTargetId().length; i++) {
            String child  = message.getTargetId()[i];
            sb.append("&toChatroomId=").append(URLEncoder.encode(child, UTF8));
        }

        sb.append("&objectName=").append(URLEncoder.encode(message.getContent().getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(message.getContent().toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/chatroom/publish.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISH,HttpUtil.returnResult(conn)), ResponseResult.class);
    }

    /**
     * 发送聊天室消广播消息方法（一个用户向聊天室发送消息，单条消息最大 128k。每秒钟限 100 次。）
     *
     * @param  message:发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。融云消息类型在messages下，自定义消息继承BaseMessage即可（必传）
     *
     * @return ResponseResult
     * @throws Exception
     **/
    public ResponseResult broadcast (ChatroomMessage message) throws Exception {

        String code = CommonUtil.checkFiled(message,PATH,CheckMethod.BROADCAST);
        if(null != code){
            return (ResponseResult)GsonUtil.fromJson(code,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId().toString(), UTF8));


        sb.append("&objectName=").append(URLEncoder.encode(message.getContent().getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(message.getContent().toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/chatroom/broadcast.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.BROADCAST,HttpUtil.returnResult(conn)), ResponseResult.class);
    }
}
