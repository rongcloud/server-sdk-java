package io.rong.models.response;

import io.rong.util.GsonUtil;

/**
 * 群组用户封禁信息。
 * @author RongCloud
 */
public class GagGroupUser {
	// 解禁时间。
	String time;
	// 群成员 Id。
	String userId;
	
	public GagGroupUser(String time, String userId) {
		this.time = time;
		this.userId = userId;
	}
	
	/**
	 * 设置time
	 *
	 */	
	public GagGroupUser setTime(String time) {
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
	public GagGroupUser setUserId(String userId) {
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
		return GsonUtil.toJson(this, GagGroupUser.class);
	}
}
