package io.rong.example.chatroom;

import io.rong.CenterEnum;
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

        Block block = rongCloud.chatroom.block;

        ChatroomMember[] members = {
                new ChatroomMember().setId("qawr34h"), new ChatroomMember().setId("qawr35h")
        };
        /**
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * Add banned chatroom members
         */


        ChatroomModel chatroom = new ChatroomModel()
                .setId("d7ec7a8b8d8546c98b0973417209a548")
                .setMembers(members)
                .setMinute(5);
        ResponseResult result = block.add(chatroom);
        System.out.println("addBlockUser:  " + result.toString());


        /**
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * Method to unban chatroom members
         */
        chatroom = new ChatroomModel()
                .setId("d7ec7a8b8d8546c98b0973417209a548")
                .setMembers(members);
        //ResponseResult removeResult = block.remove(chatroom);
        //System.out.println("removeResult:  " + removeResult.toString());

        /**
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * Method to query banned chatroom members
         */
        ListBlockChatroomUserResult getResult = block.getList("d7ec7a8b8d8546c98b0973417209a548");
        System.out.println("getListBlockUser:  " + getResult.toString());
    }

}
