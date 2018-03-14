package io.rong.models.response;

import io.rong.util.GsonUtil;

/**
 * 聊天室被禁言用户信息。
 * @author RongCloud
 */
public class GagChatRoomUser {
	// 解禁时间。
	String time;
	// 被封禁用户 Id。
	String userId;
	
	public GagChatRoomUser(String time, String userId) {
		this.time = time;
		this.userId = userId;
	}
	
	/**
	 * 设置time
	 *
	 */	
	public GagChatRoomUser setTime(String time) {
		this.time = time;
		return this;
	}
	
	/**
	 * 获取time
	 *
	 * @return String
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * 设置userId
	 *
	 */	
	public GagChatRoomUser setUserId(String userId) {
		this.userId = userId;
		return this;
	}
	
	/**
	 * 获取userId
	 *
	 * @return String
	 */
	public String getUserId() {
		return userId;
	}
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, GagChatRoomUser.class);
	}
}
