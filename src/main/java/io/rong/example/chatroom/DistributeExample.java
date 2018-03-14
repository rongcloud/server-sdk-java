package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.distribute.Distribute;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ResponseResult;

public class DistributeExample {
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "appKey";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "appSecret";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        Distribute distribute = rongCloud.chatroom.distribute;

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/chatroom/distribute.html#stop
         *
         * 聊天室消息停止分发
         *
         */
        ChatroomModel chatroomModel = new ChatroomModel()
                .setId("d7ec7a8b8d8546c98b0973417209a548");
        ResponseResult result = distribute.stop(chatroomModel);

        System.out.println("stopDistributionMessage:  " + result.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/chatroom/distribute.html#resume
         *
         * 聊天室消息恢复分发方法（每秒钟限 100 次）
         */
        ResponseResult resumeResult = distribute.resume(chatroomModel);
        System.out.println("resumeDistributionMessage:  " + resumeResult.toString());

    }
}
