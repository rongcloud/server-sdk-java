package io.rong.example.chatroom;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.chatroom.Chatroom;
import io.rong.models.chatroom.ChatroomDataModel;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.*;

public class ChatroomExample {
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

        Chatroom chatroom = rongCloud.chatroom;

        /**
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * Create a chatroom
         */
        ChatroomModel[] chatrooms = {
                new ChatroomModel().setId("chatroomId1").setName("chatroomName1"),
                new ChatroomModel().setId("chatroomId2").setName("chatroomName2")
        };
        ResponseResult result = chatroom.create(chatrooms);
        System.out.println("create:  " + result.toString());

        ChatroomDataModel chatroomDataModel = new ChatroomDataModel().setId("chatroomId3");
        ResponseResult result2 = chatroom.createV2(chatroomDataModel);
        System.out.println("createV2:  " + result2.toString());

        ChatroomQueryResult queryResult = chatroom.query(new ChatroomModel().setId("chatroomId3"));
        System.out.println("query:  " + queryResult.toString());

        /**
         *
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * Destroy a chatroom
         *
         * */
        ChatroomModel chatroomModel = new ChatroomModel()
                .setId("d7ec7a8b8d8546c98b0973417209a548");

        //ResponseResult chatroomDestroyResult = chatroom.destroy(chatroomModel);
        //System.out.println("destroy:  " + chatroomDestroyResult.toString());


        /**
         *
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * Query chatroom members demo
         *
         * */

        chatroomModel = new ChatroomModel()
                .setId("chatroomId1")
                .setCount(500)
                .setOrder(1);

        ChatroomUserQueryResult chatroomQueryUserResult = chatroom.get(chatroomModel);
        System.out.println("queryUser:  " + chatroomQueryUserResult.toString());

        /**
         *
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * Check if a chatroom member exists
         *
         * */
        ChatroomMember member = new ChatroomMember()
                .setId("76894")
                .setChatroomId("76891");

        CheckChatRoomUserResult checkMemberResult = chatroom.isExist(member);
        System.out.println("checkChatroomUserResult:  " + checkMemberResult.isInChrm);


        /**
         *
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * Batch check if chatroom members exist
         *
         * */
        ChatroomMember[] members = {
                new ChatroomMember().setId("qawr34h"),new ChatroomMember().setId("qawr35h")
        };

        ChatroomModel exists = new ChatroomModel()
                .setId("chrm01")
                .setMembers(members);
        CheckChatRoomUsersResult result1 = chatroom.isExists(exists);
        System.out.println("checkChatroomUsersResult:  " + result1);


    }
}
