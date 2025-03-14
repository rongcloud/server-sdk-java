package io.rong.example.chatroom;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.chatroom.ban.Ban;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ListGagChatroomUserResult;
import io.rong.models.response.ResponseResult;

/**
 * Chatroom Global Ban Example
 * @author RongCloud
 */
public class BanExample {
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
        // Custom API endpoint
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, api);

        Ban ban = rongCloud.chatroom.ban;

        /**
         * 
         * Add global chatroom mute
         */
        ChatroomMember[] members = {
                new ChatroomMember().setId("qawr34h"),new ChatroomMember().setId("qawr35h")
        };
        ChatroomModel chatroom = new ChatroomModel()
                .setMembers(members)
                .setMinute(5);
        ResponseResult result = ban.add(chatroom);
        System.out.println("addGagUser:  " + result.toString());

        /**
         * 
         * Get global chatroom mute list
         */

        ListGagChatroomUserResult chatroomListGagUserResult = ban.getList();
        System.out.println("ListGagUser:  " + chatroomListGagUserResult.toString());

        /**
         *
         * 
         * Remove global mute in chatroom
         */
        chatroom = new ChatroomModel()
                .setMembers(members);
        ResponseResult removeResult = ban.remove(chatroom);
        System.out.println("removeBanUser:  " + removeResult.toString());
    }
}
