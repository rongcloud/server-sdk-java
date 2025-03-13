package io.rong.example.chatroom;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.chatroom.ban.BanAllMemberWhitelist;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.GroupBanWhitelistResult;
import io.rong.models.response.ResponseResult;

/**
 * Chatroom Mute Exceptions
 * @author RongCloud
 */
public class BanAllMemberWhitelistExample {

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
        BanAllMemberWhitelist banAllMemberWhitelist = rongCloud.chatroom.banAllMemberWhitelist;

        /**
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * Add chatroom mute exceptions
         */
        ChatroomMember[] members = {
                new ChatroomMember().setId("qawr34h"),
                new ChatroomMember().setId("qawr35h")
        };
        ChatroomModel chatroom = new ChatroomModel()
                .setMembers(members)
                .setId("RC_Test_chatroom1");

        ResponseResult result = banAllMemberWhitelist.add(chatroom);
        System.out.println("addBanAllMemberWhitelist:  " + result.toString());

        /**
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * Get chatroom mute exceptions list
         */
        GroupBanWhitelistResult groupBanWhitelistResult = banAllMemberWhitelist.getList(chatroom.getId());
        System.out.println("listBanAllMemberWhitelist:  " + groupBanWhitelistResult.toString());

        /**
         *
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * Remove the Mute Exceptions for all members in the chatroom
         */
        ResponseResult removeResult = banAllMemberWhitelist.remove(chatroom);
        System.out.println("removeBanAllMemberWhitelist:  " + removeResult.toString());
    }
}
