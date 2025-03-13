package io.rong.example.chatroom;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.chatroom.demotion.Demotion;
import io.rong.models.response.ChatroomDemotionMsgResult;
import io.rong.models.response.ResponseResult;

public class DemotionExample {
    /**
     * Replace with your appKey
     */
    private static final String appKey = "appKey";
    /**
     * Replace with your appSecret
     */
    private static final String appSecret = "appSecret";


    public static void main(String[] args) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);

        Demotion demotion = rongCloud.chatroom.demotion;

        /**
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * Add in-app chatroom message demotion
         */
        String[] messageType = {"RC:VcMsg", "RC:ImgTextMsg", "RC:ImgMsg"};
        ResponseResult addResult = demotion.add(messageType);
        System.out.println("add demotion:  " + addResult.toString());

        /**
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * Remove in-app chatroom message demotion
         */
        ResponseResult removeResult = demotion.remove(messageType);
        System.out.println("remove demotion:  " + removeResult.toString());


        /**
         * Retrieves the list of demotion messages and prints the result.
         */
        ChatroomDemotionMsgResult demotionMsgResult = demotion.getList();
        System.out.println("get demotion:  " + demotionMsgResult.toString());

    }
}
