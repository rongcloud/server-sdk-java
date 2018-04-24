package io.rong.methods.message.system;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.message.BroadcastMessage;
import io.rong.models.message.MessageModel;
import io.rong.models.message.TemplateMessage;
import io.rong.models.response.ResponseResult;
import io.rong.models.Templates;
import io.rong.models.message.SystemMessage;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 发送系统消息方法
 *
 * docs : http://www.rongcloud.cn/docs/server.html#message_system_publish
 * @author RongCloud
 *
 */
public class MsgSystem {
    private static final String UTF8 = "UTF-8";
    private String appKey;
    private String appSecret;
    private static final String PATH = "message/system";
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public MsgSystem(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }

    /**
     * 发送系统消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM。
     * 每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。）
     *
     * @param message 消息体
     * @return ResponseResult
     * @throws Exception
     **/
    public ResponseResult send(MessageModel message) throws Exception {
        SystemMessage systemMessage = (SystemMessage)message;
        String code = CommonUtil.checkFiled(systemMessage,PATH,CheckMethod.PUBLISH);
        if(null != code){
            return (ResponseResult)GsonUtil.fromJson(code,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(systemMessage.getSenderId().toString(), UTF8));

        for (int i = 0 ; i< systemMessage.getTargetId().length; i++) {
            String child  = systemMessage.getTargetId()[i];
            sb.append("&toUserId=").append(URLEncoder.encode(child, UTF8));
        }

        sb.append("&objectName=").append(URLEncoder.encode(systemMessage.getContent().getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(systemMessage.getContent().toString(), UTF8));

        if (systemMessage.getPushContent() != null) {
            sb.append("&pushContent=").append(URLEncoder.encode(systemMessage.getPushContent().toString(), UTF8));
        }

        if (systemMessage.getPushData() != null) {
            sb.append("&pushData=").append(URLEncoder.encode(systemMessage.getPushData().toString(), UTF8));
        }

        if (systemMessage.getIsPersisted() != null) {
            sb.append("&isPersisted=").append(URLEncoder.encode(systemMessage.getIsPersisted().toString(), UTF8));
        }

        if (systemMessage.getPushData() != null) {
            sb.append("&isCounted=").append(URLEncoder.encode(systemMessage.getIsCounted().toString(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/system/publish.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISH,CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISH,HttpUtil.returnResult(conn))), ResponseResult.class);
    }

    /**
     * 发送系统模板消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM.每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。）
     *
     * @param  template:系统模版消息。
     *
     * @return ResponseResult
     * @throws Exception
     **/
    public ResponseResult sendTemplate(TemplateMessage template) throws Exception {

        String code = CommonUtil.checkFiled(template,PATH,CheckMethod.PUBLISHTEMPLATE);
        if(null != code){
            return (ResponseResult)GsonUtil.fromJson(code,ResponseResult.class);
        }
        Templates templateMessage = new Templates();

        ArrayList<String> toUserIds = new ArrayList<>();
        List<Map<String,String>> values = new ArrayList<>();
        List<String> push = new ArrayList<>();

        for(Map.Entry<String, TemplateMessage.Data> vo : template.getContent().entrySet()){
            toUserIds.add(vo.getKey());
            values.add(vo.getValue().getData());
            push.add(vo.getValue().getPush());
        }
        templateMessage.setFromUserId(template.getSenderId());
        templateMessage.setToUserId(toUserIds.toArray(new String[toUserIds.size()]));
        templateMessage.setObjectName(template.getObjectName());
        templateMessage.setContent(template.getTemplate().toString());
        templateMessage.setValues(values);
        templateMessage.setPushContent(push.toArray(new String[push.size()]));
        templateMessage.setPushData(template.getPushData());
        templateMessage.setContentAvailable(template.getContentAvailable());

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/system/publish_template.json", "application/json");
        HttpUtil.setBodyParameter(templateMessage.toString(), conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISHTEMPLATE,HttpUtil.returnResult(conn)), ResponseResult.class);
    }

    /**
     * 发送广播消息方法（发送消息给一个应用下的所有注册用户，如用户未在线会对满足条件（绑定手机终端）的用户发送 Push 信息，单条消息最大 128k，会话类型为 SYSTEM。每小时只能发送 2 次，每天最多发送 3 次。）
     * 该功能开发环境下可免费使用。生产环境下，您需要登录开发者后台，在“应用/IM 服务/高级功能设置”中开通公有云专业版后，在“广播消息和推送”中，开启后才能使用
     *
     * @param message 消息体
     *
     * @return ResponseResult
     * @throws Exception
     **/
    public ResponseResult broadcast(BroadcastMessage message) throws Exception {

        String errMsg = CommonUtil.checkFiled(message,PATH,CheckMethod.BROADCAST);
        if(null != errMsg){
            return (ResponseResult)GsonUtil.fromJson(errMsg,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId().toString(), UTF8));
        sb.append("&objectName=").append(URLEncoder.encode(message.getContent().getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(message.getContent().toString(), UTF8));

        if (message.getPushContent() != null) {
            sb.append("&pushContent=").append(URLEncoder.encode(message.getPushContent().toString(), UTF8));
        }

        if (message.getPushData() != null) {
            sb.append("&pushData=").append(URLEncoder.encode(message.getPushData().toString(), UTF8));
        }

        if (message.getOs() != null) {
            sb.append("&os=").append(URLEncoder.encode(message.getOs().toString(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/broadcast.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.BROADCAST,HttpUtil.returnResult(conn)), ResponseResult.class);
    }
}
