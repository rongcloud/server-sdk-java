package io.rong.models;

import io.rong.util.GsonUtil;

/**
 * Banned user information
 */
public class BlockUsers {
	// The ID of the banned user.
	String id;
	// The end time of the ban.
	String blockEndTime;

	public BlockUsers(String id, String blockEndTime) {
		this.id = id;
		this.blockEndTime = blockEndTime;
	}

	/**
	 * Get the user ID.
	 *
	 * @return String
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * Set the user ID.
	 *
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Set the ban end time.
	 *
	 */
	public void setBlockEndTime(String blockEndTime) {
		this.blockEndTime = blockEndTime;
	}
	
	/**
	 * Get the ban end time.
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
