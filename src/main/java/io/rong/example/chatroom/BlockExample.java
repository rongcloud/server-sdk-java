package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.block.Block;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ListBlockChatroomUserResult;
import io.rong.models.response.ResponseResult;

/**
 * @author RongCloud
 */
public class BlockExample {
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
        Block block = rongCloud.chatroom.block;

        ChatroomMember[] members = {
                new ChatroomMember().setId("qawr34h"),new ChatroomMember().setId("qawr35h")
        };
        /**
         *API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/chatroom/block.html#add
         *
         * 添加封禁聊天室成员方法
         */


        ChatroomModel chatroom = new ChatroomModel()
                .setId("IXQhMs3ny")
                .setMembers(members)
                .setMinute(5);
        ResponseResult result = block.add(chatroom);
        System.out.println("addBlockUser:  " + result.toString());


        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/chatroom/block.html#remove
         *
         * 移除封禁聊天室成员方法
         */
        chatroom = new ChatroomModel()
                .setId("IXQhMs3ny")
                .setMembers(members);
        ResponseResult removeResult = block.remove(chatroom);
        System.out.println("removeResult:  " + removeResult.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/chatroom/block.html#getList
         *
         * 查询被封禁聊天室成员方法
         */
        ListBlockChatroomUserResult getResult = block.getList("chatroomId1");
        System.out.println("getListBlockUser:  " + getResult.toString());
    }

}
