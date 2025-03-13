package io.rong.example.chatroom;

import io.rong.CenterEnum;
import io.rong.RongCloud;
import io.rong.methods.chatroom.Chatroom;
import io.rong.methods.chatroom.entry.ChatroomEntry;
import io.rong.models.chatroom.ChatroomEntryModel;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ChatroomEntryListResult;
import io.rong.models.response.ResponseResult;

import java.io.Reader;
import java.util.HashMap;

/**
 * Demo for setting, deleting, and querying chatroom attributes
 *
 * Response codes: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
 *
 */
public class EntryExample {
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
        ChatroomEntry entry = rongCloud.chatroom.entry;

        Chatroom chatroom = rongCloud.chatroom;

        Reader reader = null;
        /**
         * API Documentation: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * Create chatroom
         *
         * */
        ChatroomModel[] chatrooms = {
                new ChatroomModel().setId("chatroomId1").setName("chatroomName1"),
                new ChatroomModel().setId("chatroomId2").setName("chatroomName2")
        };
        ResponseResult createResult = chatroom.create(chatrooms);
        System.out.println("create chatroom result=" + createResult.toString());
        /**
         * Chatroom attribute settings, refer to the documentation
         *
         * https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         */
        ChatroomEntryModel model = new ChatroomEntryModel();
        model.setChatroomId("chatroomId1");
        model.setUserId("userId1");
        model.setKey("key1");
        model.setValue("value1");
        model.setAutoDelete(0);
        model.setObjectName("RC:TxtMsg");
        model.setContent("{\"key1\":\"value1\"}");

        ResponseResult result = entry.set(model);
        System.out.println("chatroomEntrySet Result:  " + result.toString());

        model = new ChatroomEntryModel();
        model.setChatroomId("chatroomId1");
        model.setAutoDelete(0);
        model.setEntryOwnerId("userId1");
        HashMap<String, String> kv = new HashMap<String, String>();
        kv.put("key1", "1");
        kv.put("key2", "2");
        kv.put("key3", "3");
        kv.put("key4", "4");
        model.setEntryInfo(kv);
        result = entry.batchSet(model);
        System.out.println("chatroomEntryBatchSet Result:  " + result.toString());


        /**
         * Chatroom attribute deletion, refer to the documentation
         *
         * https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         */
        ChatroomEntryModel modelRemove = new ChatroomEntryModel();
        modelRemove.setChatroomId("chatroomId1");
        modelRemove.setUserId("userId1");
        modelRemove.setKey("key1");
        modelRemove.setValue("value1");
        modelRemove.setObjectName("RC:TxtMsg");
        modelRemove.setContent("{\"key1\":\"value1\"}");

        ResponseResult removeResult = entry.remove(modelRemove);
        System.out.println("chatroomEntryRemove Result:  " + removeResult.toString());

        /**
         * Chatroom attribute query, refer to the documentation
         *
         * https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         */
        ChatroomEntryModel modelQuery = new ChatroomEntryModel();
        modelQuery.setChatroomId("chatroomId1");
        modelQuery.setKeys(new String[]{"key1", "key2"});

        ChatroomEntryListResult queryResult = entry.query(modelQuery);
        System.out.println("chatroomEntryQuery Result:  " + queryResult.toString());
    }
}
