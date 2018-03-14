package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.util.GsonUtil;

import java.util.List;

/**
 *  chatroomQuery 返回结果
 */
public class ChatroomQueryResult extends Result {
	List<ChatroomModel> chatRooms;
	public ChatroomQueryResult(Integer code, String errorMessage, List<ChatroomModel> chatRooms) {
		super(code, errorMessage);
		this.chatRooms = chatRooms;
	}
	/**
	 * 设置chatRooms
	 *
	 */
	public void setChatRooms(List<ChatroomModel> chatRooms) {
		this.chatRooms = chatRooms;
	}
	
	/**
	 * 获取chatRooms
	 *
	 * @return chatRooms
	 */
	public List<ChatroomModel> getChatRooms() {
		return chatRooms;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatroomQueryResult.class);
	}
}
