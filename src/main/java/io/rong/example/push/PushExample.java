package io.rong.example.push;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.messages.RcCmdMessage;
import io.rong.messages.TxtMessage;
import io.rong.models.message.BroadcastMessage;
import io.rong.models.push.*;
import io.rong.models.response.PushResult;
import io.rong.models.response.ResponseResult;

import static org.junit.Assert.assertEquals;

/**
 * Demo class
 *
 * @author RongCloud
 */
public class PushExample {
    /**
     * Replace with your App Key
     */
    private static final String appKey = "appKey";
    /**
     * Replace with your App Secret
     */
    private static final String appSecret = "appSecret";


    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);

        /**
         *
         * API Documentation:
         *
         * Broadcast Message
         *
         **/
        BroadcastModel broadcast = new BroadcastModel();
        broadcast.setFromuserid("fromuserid");
        broadcast.setPlatform(new String[]{"ios", "android"});
        Audience audience = new Audience();
        audience.setUserid(new String[]{"userid1", "userid2"});
        broadcast.setAudience(audience);
        Message message = new Message();
        message.setContent("this is message");
        message.setObjectName("RC:TxtMsg");
        message.setDisableUpdateLastMsg(true);
        broadcast.setMessage(message);
        Notification notification = new Notification();
        notification.setAlert("this is broadcast");
        broadcast.setNotification(notification);
        PushResult result = rongCloud.push.message(broadcast);

        System.out.println("broadcast: " + result.toString());

        /**
         *
         * API Documentation:
         *
         * Broadcast to online users
         *
         **/
        TxtMessage msg = new TxtMessage("this is message", "");
        BroadcastMessage obmessage = new BroadcastMessage()
                .setSenderId("OScHVP1tQ")
                .setObjectName("RC:TxtMsg")
                .setContent(msg);

        ResponseResult bresult = rongCloud.message.system.onlineBroadcast(obmessage);
        System.out.println("online broadcast: " + bresult.toString());

        /**
         *
         * API Documentation:
         * Recall broadcast message
         *
         */
        RcCmdMessage rcCmdMessage = new RcCmdMessage("BCVD-DV70-EKOC-7ES6");
        BroadcastMessage bmessage = new BroadcastMessage()
                .setSenderId("OScHVP1tQ")
                .setObjectName(rcCmdMessage.getType())
                .setContent(rcCmdMessage);
        bresult = rongCloud.message.system.broadcast(bmessage);
        System.out.println("recall broadcast:  " + bresult.toString());


        /**
         *
         * API Documentation:
         *
         * Push notification
         *
         **/
        PushModel pushmodel = new PushModel();
        pushmodel.setPlatform(new String[]{"ios", "android"});
        audience = new Audience();
        audience.setUserid(new String[]{"userid1", "userid2"});
        pushmodel.setAudience(audience);
        notification = new Notification();
        notification.setAlert("this is push");
        pushmodel.setNotification(notification);
        result = rongCloud.push.push(pushmodel);

        System.out.println("push: " + result.toString());
    }
}
