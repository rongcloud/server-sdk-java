package io.rong.example.chatroom;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.chatroom.gag.Gag;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ListGagChatroomUserResult;
import io.rong.models.response.ResponseResult;

/**
 * Example of Chatroom Mute
 * @author RongCloud
 */
public class GagExample {
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

        Gag gag = rongCloud.chatroom.gag;

        /**
         * API Documentation: https://docs.rongcloud.io/platform-chat-api/im-server-api-list-v1
         * Add a muted user to the chatroom (When you want to prevent a user from sending messages in a chatroom, you can mute them.
         * The muted user can still receive and view messages in the chatroom but cannot send messages.)
         */

        ChatroomMember[] members = {
                new ChatroomMember().setId("qawr34h"),new ChatroomMember().setId("qawr35h")
        };
        ChatroomModel chatroom = new ChatroomModel()
                .setId("hjhf07kk")
                .setMembers(members)
                .setMinute(5);
        ResponseResult result = gag.add(chatroom);
        System.out.println("addGagUser:  " + result.toString());


/**
 * API Documentation: https://docs.rongcloud.io/platform-chat-api/im-server-api-list-v1
 * Method to query muted chatroom members
 */
chatroom = new ChatroomModel()
        .setId("hjhf07kk");
ListGagChatroomUserResult chatroomListGagUserResult = gag.getList(chatroom);
System.out.println("ListGagUser:  " + chatroomListGagUserResult.toString());

/**
 *
 * API Documentation: https://docs.rongcloud.io/platform-chat-api/im-server-api-list-v1
 *
 * Method to remove muted chatroom members
 */
chatroom = new ChatroomModel()
        .setId("hjhf07kk")
        .setMembers(members);
ResponseResult removeResult = gag.remove(chatroom);
System.out.println("rollbackGagUser:  " + result.toString());






    }
}
