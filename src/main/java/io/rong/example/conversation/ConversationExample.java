package io.rong.example.conversation;

import io.rong.RongCloud;
import io.rong.methods.conversation.Conversation;
import io.rong.models.response.ConversationNotificationResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.conversation.ConversationModel;
import io.rong.util.CodeUtil;
import io.rong.util.CodeUtil.ConversationType;

/**
 *
 * 绘话示例
 * @author RongCloud
 *
 * @version 3.0.0
 */
public class ConversationExample {
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "appKey";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "appSercet";
    /**
     * 自定义api地址
     * */
    private static final String api = "http://api.rong-api.com";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        //自定义 api 地址方式
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        Conversation Conversation = rongCloud.conversation;

        ConversationModel conversation = new ConversationModel()
                .setType(ConversationType.PRIVATE.getName())
                .setUserId("UgYzcDZSisNyYaZ83WXcEk11")
                .setTargetId("2iXiqVWUAWwaKA55FuZvY31");
        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 设置消息免打扰
         *
         */
        ResponseResult muteConversationResult = Conversation.mute(conversation);

        System.out.println("muteConversationResult:  " + muteConversationResult.toString());

        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 解除消息免打扰
         *
         * */
        ResponseResult unMuteConversationResult = Conversation.unMute(conversation);

        System.out.println("unMuteConversationResult:  " + unMuteConversationResult.toString());
        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 获取消息免打扰
         *
         * */
        ConversationNotificationResult getMuteConversationResult = (ConversationNotificationResult)Conversation.get(conversation);

        System.out.println("getMuteConversationResult:  " + getMuteConversationResult.toString());
    }
}
