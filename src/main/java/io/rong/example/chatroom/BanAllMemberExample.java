package io.rong.example.chatroom;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.chatroom.ban.BanAllMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ChatroomBanListResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.StatusResult;

/**
 * Chatroom Mute All Members
 * @author RongCloud
 */
public class BanAllMemberExample {

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
        BanAllMember banAllMember = rongCloud.chatroom.banAllMember;

        /**
         * 
         * Mute all members in a chatroom
         * */
        ChatroomModel chatroom = new ChatroomModel();
        chatroom.setId("RC_Test_chatroom1");

        ResponseResult result = banAllMember.add(chatroom);
        System.out.println("addBanAllMember:  " + result.toString());

        /**
         * Check the mute status of all members in a chatroom
         * */
        StatusResult statusResult = banAllMember.check(chatroom);
        System.out.println("checkBanAllMember:  " + statusResult.toString());

        /**
         * Get the list of all muted members in the chatroom
         */
        ChatroomBanListResult chatroomBanListResult = banAllMember.getList(10,1);
        System.out.println("listBanAllMember:  " + chatroomBanListResult.toString());

        /**
         *
         * Remove the mute for all members in the chatroom
         */
        ResponseResult removeResult = banAllMember.remove(chatroom);
        System.out.println("removeBanAllMember:  " + removeResult.toString());
    }
}
