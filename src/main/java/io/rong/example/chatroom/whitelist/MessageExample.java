package io.rong.example.chatroom.whitelist;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.chatroom.whitelist.Whitelist;
import io.rong.models.response.ChatroomWhitelistMsgResult;
import io.rong.models.response.ResponseResult;

/**
 * @author RongCloud
 */
public class MessageExample {
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
        // Custom API endpoint
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, api);

        Whitelist whitelist = rongCloud.chatroom.whiteList;
        String[] messageType = {"RC:VcMsg", "RC:ImgTextMsg", "RC:ImgMsg"};

        /**
         * 
         * Add chatroom message allowlist
         */

        ResponseResult addResult = whitelist.message.add(messageType);
        System.out.println("add whitelist:  " + addResult.toString());
        /**
         * 
         * Get chatroom message allowlist
         */

        ChatroomWhitelistMsgResult getResult = whitelist.message.getList();
        System.out.println("get whitelist:  " + getResult.toString());

        /**
         * 
         * Delete chatroom message allowlist
         */
        ResponseResult removeResult = whitelist.message.remove(messageType);
        System.out.println("remove whitelist:  " + removeResult.toString());

       }
}
