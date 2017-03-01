package io.rong.models;

import io.rong.util.GsonUtil;

/**
 * 聊天室被封禁用户信息。
 */
public class BlockChatRoomUser {
	// 聊天室用户Id。
	String userId;
	// 加入聊天室时间。
	String time;
	
	public BlockChatRoomUser(String userId, String time) {
		this.userId = userId;
		this.time = time;
	}
	
	/**
	 * 设置userId
	 *
	 */	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取userId
	 *
	 * @return String
	 */
	public String getUserId() {
		return userId;
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
		return GsonUtil.toJson(this, BlockChatRoomUser.class);
	}
}
