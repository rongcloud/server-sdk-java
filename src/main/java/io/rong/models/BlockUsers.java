package io.rong.models;

import io.rong.util.GsonUtil;

/**
 * 封禁用户信息
 */
public class BlockUsers {
	// 被封禁用户 ID。
	String id;
	// 封禁结束时间。
	String blockEndTime;

	public BlockUsers(String id, String blockEndTime) {
		this.id = id;
		this.blockEndTime = blockEndTime;
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
	 * @param id 设置userId
	 *
	 */
	public void setId(String id) {
		this.id = id;
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
