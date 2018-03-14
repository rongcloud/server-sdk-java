package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.Chatroom;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.*;
import java.io.Reader;

public class ChatroomExample {
       /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "appKey";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "appSecret";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        Chatroom chatroom = rongCloud.chatroom;

        Reader reader = null;
        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/chatroom/chatroom.html#create
         *
         * 创建聊天室
         *
         * */
        ChatroomModel[] chatrooms = {
                new ChatroomModel().setId("chatroomId1").setName("chatroomName1"),
                new ChatroomModel().setId("chatroomId2").setName("chatroomName2")
        };
        ResponseResult result = chatroom.create(chatrooms);

        System.out.println("create:  " + result.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/chatroom/chatroom.html#destory
         * 销毁聊天室
         *
         * */
        ChatroomModel chatroomModel = new ChatroomModel()
                .setId("d7ec7a8b8d8546c98b0973417209a548");

        ResponseResult chatroomDestroyResult = chatroom.destroy(chatroomModel);
        System.out.println("destroy:  " + chatroomDestroyResult.toString());


        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/chatroom/chatroom.html#getMembers
         * 查询聊天室成员demo
         *
         * */

        chatroomModel = new ChatroomModel()
                .setId("d7ec7a8b8d8546c98b0973417209a548")
                .setCount(500)
                .setOrder(1);

        ChatroomUserQueryResult chatroomQueryUserResult = chatroom.get(chatroomModel);
        System.out.println("queryUser:  " + chatroomQueryUserResult.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/chatroom/chatroom.html#isExist
         * 查询聊天室成员是否存在
         *
         * */
        ChatroomMember member = new ChatroomMember()
                .setId("137385")
                .setChatroomId("d7ec7a8b8d8546c98b0973417209a548")
                .setTime("8888");

        CheckChatRoomUserResult checkMemberResult = chatroom.isExist(member);
        System.out.println("checkChatroomUserResult:  " + checkMemberResult.isInChrm);


    }
}
