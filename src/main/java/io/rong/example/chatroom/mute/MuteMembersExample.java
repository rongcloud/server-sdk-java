package io.rong.example.chatroom.mute;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.chatroom.mute.MuteMembers;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ListGagChatroomUserResult;
import io.rong.models.response.ResponseResult;

public class MuteMembersExample {
    /**
     * Replace with your App Key here
     * */
    private static final String appKey = "appKey";
    /**
     * Replace with your App Secret here
     * */
    private static final String appSecret = "appSecret";

    public static void main(String[] args) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, CenterEnum.BJ);

        MuteMembers muteMembers = rongCloud.chatroom.muteMembers;

        /**
         * 
         * Add muted chatroom members (In the app, if you want to prevent a user from sending messages in a chatroom, 
         * you can mute the user in the chatroom. Muted users can still receive and view messages in the chatroom but cannot send messages.)
         * Get the blocklist of a user
         */

        ChatroomMember[] members = {
                new ChatroomMember().setId("qawr34h"),new ChatroomMember().setId("qawr35h")
        };
        ChatroomModel chatroom = new ChatroomModel()
                .setId("hjhf07kk")
                .setMembers(members)
                .setMinute(5);
        ResponseResult result = muteMembers.add(chatroom);
        System.out.println("muteMembers.add:  " + result.toString());

        /**
         *
         * 
         * Query muted chatroom members
         */
        chatroom = new ChatroomModel()
                .setId("hjhf07kk");
        ListGagChatroomUserResult chatroomListGagUserResult = muteMembers.getList(chatroom);
        System.out.println("muteMembers.getList:  " + chatroomListGagUserResult.toString());

        /**
         *
         * 
         *
         * Unmute chatroom members
         */
        chatroom = new ChatroomModel()
                .setId("hjhf07kk")
                .setMembers(members);
        ResponseResult removeResult = muteMembers.remove(chatroom);
        System.out.println("muteMembers.remove:  " + removeResult.toString());


    }
}
