package io.rong.example.chatroom;

import java.io.Reader;

import io.rong.RongCloud;
import io.rong.methods.chatroom.Chatroom;
import io.rong.methods.chatroom.entry.ChatroomEntry;
import io.rong.models.chatroom.ChatroomEntryModel;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ChatroomEntryListResult;
import io.rong.models.response.ResponseResult;
import java.util.HashMap;

/**
 * 聊天室设置属性，删除属性，查询属性 Demo
 * 
 * 应答码: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
 *
 */
public class EntryExample {
	/**
	 * 此处替换成您的appKey
	 */
	private static final String appKey = "appKey";
	/**
	 * 此处替换成您的appSecret
	 */
	private static final String appSecret = "appSecret";
	/**
	 * 自定义api地址
	 */
	private static final String api = "http://api.rong-api.com";

	public static void main(String[] args) throws Exception {
		RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
		ChatroomEntry entry = rongCloud.chatroom.entry;

		Chatroom chatroom = rongCloud.chatroom;

        Reader reader = null;
        /**
         * API 文档: https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
         *
         * 创建聊天室
         *
         * */
        ChatroomModel[] chatrooms = {
                new ChatroomModel().setId("chatroomId1").setName("chatroomName1"),
                new ChatroomModel().setId("chatroomId2").setName("chatroomName2")
        };
        ResponseResult createResult = chatroom.create(chatrooms);
        System.out.println("create chatroom result=" + createResult.toString());
		/**
		 * 聊天室属性设置，参考文档
		 * 
		 * https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
		 */
		ChatroomEntryModel model = new ChatroomEntryModel();
		model.setChatroomId("chatroomId1");
		model.setUserId("userId1");
		model.setKey("key1");
		model.setValue("value1");
		model.setAutoDelete(0);// 可选
		model.setObjectName("RC:TxtMsg");// 可选
		model.setContent("{\"key1\":\"value1\"}");// 可选

		ResponseResult result = entry.set(model);
		System.out.println("chatroomEntrySet Result:  " + result.toString());

		model = new ChatroomEntryModel();
		model.setChatroomId("chatroomId1");
		model.setAutoDelete(0);// 可选
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
		 * 聊天室属性删除，参考文档
		 * 
		 * https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
		 */
		ChatroomEntryModel modelRemove = new ChatroomEntryModel();
		modelRemove.setChatroomId("chatroomId1");
		modelRemove.setUserId("userId1");
		modelRemove.setKey("key1");
		modelRemove.setValue("value1");
		modelRemove.setObjectName("RC:TxtMsg");// 可选
		modelRemove.setContent("{\"key1\":\"value1\"}");// 可选

		ResponseResult removeResult = entry.remove(modelRemove);
		System.out.println("chatroomEntryRemove Result:  " + removeResult.toString());

		/**
		 * 聊天室属性查询，参考文档
		 * 
		 * https://doc.rongcloud.cn/imserver/server/v1/im-server-api-list-v1
		 */
		ChatroomEntryModel modelQuery = new ChatroomEntryModel();
		modelQuery.setChatroomId("chatroomId1");
		modelQuery.setKeys(new String[] { "key1", "key2" });

		ChatroomEntryListResult queryResult = entry.query(modelQuery);
		System.out.println("chatroomEntryQuery Result:  " + queryResult.toString());
	}
}
