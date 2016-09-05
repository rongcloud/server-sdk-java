package io.rong.models;

import io.rong.util.GsonUtil;

/**
 * 聊天室信息。
 */
public class ChatRoom {
	// 聊天室 ID。
	String chrmId;
	// 聊天室名称。
	String name;
	// 聊天室创建时间。
	String time;
	
	public ChatRoom(String chrmId, String name, String time) {
		this.chrmId = chrmId;
		this.name = name;
		this.time = time;
	}
	
	/**
	 * 设置chrmId
	 *
	 */	
	public void setChrmId(String chrmId) {
		this.chrmId = chrmId;
	}
	
	/**
	 * 获取chrmId
	 *
	 * @return String
	 */
	public String getChrmId() {
		return chrmId;
	}
	
	/**
	 * 设置name
	 *
	 */	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取name
	 *
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置time
	 *
	 */	
	public void setTime(String time) {
		this.time = time;
	}
	
	/**
	 * 获取time
	 *
	 * @return String
	 */
	public String getTime() {
		return time;
	}
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatRoom.class);
	}
}
