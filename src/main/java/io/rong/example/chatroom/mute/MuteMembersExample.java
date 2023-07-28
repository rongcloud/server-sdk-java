package io.rong.example.chatroom.mute;

import io.rong.RongCloud;
import io.rong.methods.chatroom.mute.MuteMembers;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ListGagChatroomUserResult;
import io.rong.models.response.ResponseResult;

public class MuteMembersExample {
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
        //自定义 api地址方式
        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        MuteMembers muteMembers = rongCloud.chatroom.muteMembers;

        /**
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 添加禁言聊天室成员方法想（在 App 中如果不让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，
         * 被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息.）获取某用户的黑名单列表方法
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
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 查询被禁言聊天室成员方法
         */
        chatroom = new ChatroomModel()
                .setId("hjhf07kk");
        ListGagChatroomUserResult chatroomListGagUserResult = muteMembers.getList(chatroom);
        System.out.println("muteMembers.getList:  " + chatroomListGagUserResult.toString());

        /**
         *
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * 移除禁言聊天室成员
         */
        chatroom = new ChatroomModel()
                .setId("hjhf07kk")
                .setMembers(members);
        ResponseResult removeResult = muteMembers.remove(chatroom);
        System.out.println("muteMembers.remove:  " + removeResult.toString());


    }
}
