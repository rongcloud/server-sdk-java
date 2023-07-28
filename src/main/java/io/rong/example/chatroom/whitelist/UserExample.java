package io.rong.example.chatroom.whitelist;

import io.rong.RongCloud;
import io.rong.methods.chatroom.whitelist.Whitelist;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.WhiteListResult;

public class UserExample {
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

        Whitelist whitelist = rongCloud.chatroom.whiteList;

        /**
         * API: 文档 https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 添加聊天室用户白名单
         * */
        ChatroomMember[] members = {
                new ChatroomMember().setId("qawr34h"),new ChatroomMember().setId("qawr35h")
        };
        ChatroomModel chatroom = new ChatroomModel()
                .setId("d7ec7a8b8d8546c98b0973417209a548")
                .setMembers(members);
        ResponseResult addResult = whitelist.user.add(chatroom);
        System.out.println("add whitelist:  " + addResult.toString());

        /**
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 获取聊天室用户白名单
         * */

        WhiteListResult getResult = (WhiteListResult)whitelist.user.getList(chatroom);
        System.out.println("get whitelist:  " + getResult.toString());


        /**
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         * 删除聊天室用户白名单
         * */

        ResponseResult removeResult = whitelist.user.remove(chatroom);
        System.out.println("remove whitelist:  " + removeResult.toString());

       }
}
