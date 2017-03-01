package io.rong.models;

import io.rong.util.GsonUtil;

/**
 * 封禁用户信息
 */
public class BlockUsers {
	// 被封禁用户 ID。
	String userId;
	// 封禁结束时间。
	String blockEndTime;
	
	public BlockUsers(String userId, String blockEndTime) {
		this.userId = userId;
		this.blockEndTime = blockEndTime;
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
	 * 设置blockEndTime
	 *
	 */	
	public void setBlockEndTime(String blockEndTime) {
		this.blockEndTime = blockEndTime;
	}
	
	/**
	 * 获取blockEndTime
	 *
	 * @return String
	 */
	public String getBlockEndTime() {
		return blockEndTime;
	}
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, BlockUsers.class);
	}
}
