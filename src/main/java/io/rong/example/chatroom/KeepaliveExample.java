package io.rong.example.chatroom;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.chatroom.keepalive.Keepalive;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ChatroomKeepaliveResult;
import io.rong.models.response.ResponseResult;

/**
 *
 * Example for Chatroom Keepalive
 * @author RongCloud
 *
 * @version 3.0.0
 */
public class KeepaliveExample {
    /**
     * Replace with your App Key
     * */
    private static final String appKey = "appKey";
    /**
     * Replace with your App Secret
     * */
    private static final String appSecret = "appSecret";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);
        //Custom API URL
        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        Keepalive keepalive = rongCloud.chatroom.keepalive;

        /**
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * Add a keepalive chatroom
         *
         **/
        ChatroomModel chatroom = new ChatroomModel()
                .setId("d7ec7a8b8d8546c98b0973417209a548");
        ResponseResult addResult = keepalive.add(chatroom);
        System.out.println("add keepalive result"+addResult.toString());

        /**
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * Remove a keepalive chatroom
         *
         **/
        ResponseResult removeResult = keepalive.remove(chatroom);
        System.out.println("keepalive remove"+removeResult.toString());

        /**
         *
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * Retrieve Keepalive Chatroom
         *
         **/
        ChatroomKeepaliveResult result = keepalive.getList();

        System.out.println("keepalive getList"+result.toString());
    }

}
