package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.gag.Gag;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ListGagChatroomUserResult;
import io.rong.models.response.ResponseResult;

/**
 * 聊天时禁言示例
 * @author RongCloud
 */
public class GagExample {
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
    private static final String api = "http://api.cn.ronghub.com";


    public static void main(String[] args) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        //自定义 api地址方式
        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        Gag gag = rongCloud.chatroom.gag;

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/chatroom/gag.html#add
         * 添加禁言聊天室成员方法想（在 App 中如果不让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，
         * 被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息.）获取某用户的黑名单列表方法（每秒钟限 100 次）
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
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/chatroom/gag.html#remove
         * 查询被禁言聊天室成员方法
         */
        chatroom = new ChatroomModel()
                .setId("hjhf07kk");
        ListGagChatroomUserResult chatroomListGagUserResult = gag.getList(chatroom);
        System.out.println("ListGagUser:  " + chatroomListGagUserResult.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/chatroom/gag.html#getList
         *
         * 移除禁言聊天室成员
         */
        chatroom = new ChatroomModel()
                .setId("hjhf07kk")
                .setMembers(members);
        ResponseResult removeResult = gag.remove(chatroom);
        System.out.println("rollbackGagUser:  " + result.toString());






    }
}
