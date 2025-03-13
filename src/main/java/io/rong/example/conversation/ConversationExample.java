package io.rong.example.conversation;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.conversation.Conversation;
import io.rong.models.conversation.ConversationModel;
import io.rong.models.conversation.ConversationSetTopModel;
import io.rong.models.response.ConversationNotificationResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CodeUtil.ConversationType;

/**
 *
 * Conversation Example
 * @author RongCloud
 *
 * @version 3.0.0
 */
public class ConversationExample {
    /**
     * Replace with your App Key
     * */
    private static final String appKey = "appKey";
    /**
     * Replace with your App Secret
     * */
    private static final String appSecret = "appSercet";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);
        // Custom API URL
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        Conversation Conversation = rongCloud.conversation;

        ConversationModel conversation = new ConversationModel()
                .setType(ConversationType.PRIVATE.getName())
                .setUserId("UgYzcDZSisNyYaZ83WXcEk11")
                .setTargetId("2iXiqVWUAWwaKA55FuZvY31");
        /**
         *
         * 
         * Set Conversation Do Not Disturb
         *
         */
        ResponseResult muteConversationResult = Conversation.mute(conversation);

        System.out.println("muteConversationResult:  " + muteConversationResult.toString());

        /**
         *
         * 
         * Disable Conversation Do Not Disturb
         *
         * */
        ResponseResult unMuteConversationResult = Conversation.unMute(conversation);

        System.out.println("unMuteConversationResult:  " + unMuteConversationResult.toString());
        /**
         *
         * 
         * Get Conversation Do Not Disturb Status
         *
         * */
        ConversationNotificationResult getMuteConversationResult = (ConversationNotificationResult)Conversation.get(conversation);

        System.out.println("getMuteConversationResult:  " + getMuteConversationResult.toString());



        /**
         * 
         * Set conversation top.
         */
        ConversationSetTopModel setTopModel = new ConversationSetTopModel()
                .setSetTop(true)
                .setConversationType(1)
                .setTargetId("tid")
                .setUserId("uid");
        ResponseResult result = Conversation.setTop(setTopModel);
        System.out.println("setTop:  " + result.toString());
    }
}
