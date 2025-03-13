package io.rong.example.chatroom.whitelist;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.chatroom.whitelist.Whitelist;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.WhiteListResult;

public class UserExample {
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

        Whitelist whitelist = rongCloud.chatroom.whiteList;

        /**
         * API: Documentation https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * Add users to the chatroom allowlist
         */
        ChatroomMember[] members = {
                new ChatroomMember().setId("qawr34h"),new ChatroomMember().setId("qawr35h")
        };
        ChatroomModel chatroom = new ChatroomModel()
                .setId("d7ec7a8b8d8546c98b0973417209a548")
                .setMembers(members);
        ResponseResult addResult = whitelist.user.add(chatroom);
        System.out.println("add whitelist:  " + addResult.toString());

        /**
         * 
         * Get the chatroom allowlist
         */

        WhiteListResult getResult = (WhiteListResult)whitelist.user.getList(chatroom);
        System.out.println("get whitelist:  " + getResult.toString());


        /**
        * 
        * Remove Chatroom Allowlist Users
        */

        ResponseResult removeResult = whitelist.user.remove(chatroom);
        System.out.println("remove whitelist:  " + removeResult.toString());

       }
}
