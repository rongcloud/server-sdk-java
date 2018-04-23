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
	String id;

	public GagGroupUser(String time, String id) {
		this.time = time;
		this.id = id;
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
	 * 获取userId
	 *
	 * @return String
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置userId
	 *
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, GagGroupUser.class);
	}
}
