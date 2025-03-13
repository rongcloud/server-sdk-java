package io.rong.models.response;

import io.rong.util.GsonUtil;

/**
 * Group user ban information.
 * @author RongCloud
 */
public class GagGroupUser {
	// Unban time.
	String time;
	// Group member ID.
	String id;

	public GagGroupUser(String time, String id) {
		this.time = time;
		this.id = id;
	}

	/**
	 * Set the unban time.
	 *
	 */	
	public GagGroupUser setTime(String time) {
		this.time = time;
		return this;
	}
	
	/**
	 * Get the unban time.
	 *
	 * @return String
	 */
	public String getTime() {
		return time;
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

	@Override
	public String toString() {
		return GsonUtil.toJson(this, GagGroupUser.class);
	}
}
