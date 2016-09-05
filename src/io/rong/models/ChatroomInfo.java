package io.rong.models;

import io.rong.util.GsonUtil;

/**
 * 聊天室信息。
 */
public class ChatRoomInfo {
	// 聊天室Id。
	String id;
	// 聊天室名称。
	String name;
	
	public ChatRoomInfo(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * 设置id
	 *
	 */	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 获取id
	 *
	 * @return String
	 */
	public String getId() {
		return id;
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
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatRoomInfo.class);
	}
}
