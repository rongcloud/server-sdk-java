package io.rong.example.chatroom;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.chatroom.distribute.Distribute;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ResponseResult;

public class DistributeExample {
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
        // Custom API URL
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, api);

        Distribute distribute = rongCloud.chatroom.distribute;

        /**
         * API Documentation: https://docs.rongcloud.io/platform-chat-api/im-server-api-list-v1
         *
         * Stop Chatroom Message Distribution
         */
        ChatroomModel chatroomModel = new ChatroomModel()
                .setId("d7ec7a8b8d8546c98b0973417209a548");
        ResponseResult result = distribute.stop(chatroomModel);

        System.out.println("stopDistributionMessage:  " + result.toString());

        /**
         * API Documentation: https://docs.rongcloud.io/platform-chat-api/im-server-api-list-v1
         *
         * Resume Chatroom Message Distribution
         */
        ResponseResult resumeResult = distribute.resume(chatroomModel);
        System.out.println("resumeDistributionMessage:  " + resumeResult.toString());

    }
}
