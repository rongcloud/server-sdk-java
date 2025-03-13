package io.rong.models.response;

import io.rong.util.GsonUtil;

/**
 * Group user information.
 * @author RongCloud
 */
public class GroupUser {
	// User ID.
	String id;
	
	public GroupUser(String id) {
		this.id = id;
	}
	
	/**
	 * Set the user ID.
	 *
	 */	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Get the user ID.
	 *
	 * @return String
	 */
	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, GroupUser.class);
	}
}
