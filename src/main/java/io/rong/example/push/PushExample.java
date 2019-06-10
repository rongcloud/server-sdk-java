package io.rong.example.push;

import io.rong.RongCloud;
import io.rong.models.push.*;
import io.rong.models.response.PushResult;

/**
 * Demo class
 *
 * @author RongCloud
 *
 */
public class PushExample {
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "appKey";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "appSecret";
    /**
     * 自定义api地址
     * */
    private static final String api = "http://api-cn.ronghub.com";


    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        /**
         *
         * API 文档:
         * https://www.rongcloud.cn/docs/push_service.html#broadcast
         *
         * 广播消息
         *
         **/
        BroadcastModel broadcast = new BroadcastModel();
        broadcast.setFromuserid("fromuserid");
        broadcast.setPlatform(new String[] {"ios", "android"});
        Audience audience = new Audience();
        audience.setUserid(new String[] { "userid1", "userid2" });
        broadcast.setAudience(audience);
        Message message = new Message();
        message.setContent("this is message");
        message.setObjectName("RC:TxtMsg");
        broadcast.setMessage(message);
        Notification notification = new Notification();
        notification.setAlert("this is broadcast");
        broadcast.setNotification(notification);
        PushResult result = rongCloud.push.send(broadcast);

        System.out.println("broadcast: " + result.toString());


        /**
         *
         * API 文档:
         * https://www.rongcloud.cn/docs/push_service.html#push
         *
         * 推送消息
         *
         **/
        PushModel pushmodel = new PushModel();
        pushmodel.setPlatform(new String[] {"ios", "android"});
        audience = new Audience();
        audience.setUserid(new String[] { "userid1", "userid2" });
        pushmodel.setAudience(audience);
        notification = new Notification();
        notification.setAlert("this is push");
        pushmodel.setNotification(notification);
        result = rongCloud.push.send(pushmodel);

        System.out.println("push: " + result.toString());
    }
}
