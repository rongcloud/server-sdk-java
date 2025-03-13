package io.rong.example.user;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.user.mute.MuteChatrooms;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ListGagChatroomUserResult;
import io.rong.models.response.ResponseResult;
/**
 * Global chatroom mute example
 * @author RongCloud
 */
public class MuteChatroomsExample {
    /**
     * Replace with your appKey
     */
    private static final String appKey = "appKey";
    /**
     * Replace with your appSecret
     */
    private static final String appSecret = "appSecret";

    public static void main(String[] args) throws Exception {
        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        //Custom API endpoint
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);

        MuteChatrooms muteChatrooms = rongCloud.user.muteChatrooms;

        /**
         * 
         * Add global mute for chatroom
         */
        ChatroomMember[] members = {
                new ChatroomMember().setId("qawr34h"), new ChatroomMember().setId("qawr35h")
        };
        ChatroomModel chatroom = new ChatroomModel()
                .setMembers(members)
                .setMinute(5);
        ResponseResult result = muteChatrooms.add(chatroom);
        System.out.println("addGagUser:  " + result.toString());

        /**
         * 
         * Get global mute list for chatroom
         */

        ListGagChatroomUserResult chatroomListGagUserResult = muteChatrooms.getList();
        System.out.println("ListGagUser:  " + chatroomListGagUserResult.toString());

        /**
         *
         * 
         * Remove global mute for chatroom
         */
        chatroom = new ChatroomModel()
                .setMembers(members);
        ResponseResult removeResult = muteChatrooms.remove(chatroom);
        System.out.println("removeBanUser:  " + removeResult.toString());
    }
}
