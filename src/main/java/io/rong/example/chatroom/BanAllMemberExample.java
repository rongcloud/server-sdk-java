package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.ban.BanAllMember;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ChatroomBanListResult;
import io.rong.models.response.ListGagChatroomUserResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.StatusResult;

/**
 * 聊天室全体成员禁言
 * @author RongCloud
 */
public class BanAllMemberExample {

    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "appKey";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "appSecret";
    /**
     * 自定义api地址
     * */
    private static final String api = "http://api.rong-api.com";

    public static void main(String[] args) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        BanAllMember banAllMember = rongCloud.chatroom.banAllMember;

        /**
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 添加聊天室全体成员禁言
         * */
        ChatroomModel chatroom = new ChatroomModel();
        chatroom.setId("RC_Test_chatroom1");

        ResponseResult result = banAllMember.add(chatroom);
        System.out.println("addBanAllMember:  " + result.toString());

        /**
         * API 文档: https://docs.rongcloud.cn/v4/views/im/server/chatroom/ban.html#ban-add
         * 聊天室全体禁言状态检查
         * */
        StatusResult statusResult = banAllMember.check(chatroom);
        System.out.println("checkBanAllMember:  " + statusResult.toString());

        /**
         *
         * API 文档: https://docs.rongcloud.cn/v4/views/im/server/chatroom/ban.html#ban-query
         * 获取聊天室全体禁言列表
         */
        ChatroomBanListResult chatroomBanListResult = banAllMember.getList(10,1);
        System.out.println("listBanAllMember:  " + chatroomBanListResult.toString());

        /**
         *
         * API 文档: https://docs.rongcloud.cn/v4/views/im/server/chatroom/ban.html#ban-rollback
         * 删除聊天室全体禁言
         */
        ResponseResult removeResult = banAllMember.remove(chatroom);
        System.out.println("removeBanAllMember:  " + removeResult.toString());
    }
}
