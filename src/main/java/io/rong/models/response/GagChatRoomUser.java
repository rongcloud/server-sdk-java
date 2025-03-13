package io.rong.models.response;

import io.rong.util.GsonUtil;

/**
 * Information about a muted user in a chatroom.
 * @author RongCloud
 */
public class GagChatRoomUser {
	// The unmute time.
	String time;
	// The ID of the muted user.
	String userId;
	
	public GagChatRoomUser(String time, String userId) {
		this.time = time;
		this.userId = userId;
	}
	
	/**
	 * Set the unmute time.
	 *
	 */	
	public GagChatRoomUser setTime(String time) {
		this.time = time;
		return this;
	}
	
	/**
	 * Get the unmute time.
	 *
	 * @return String
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * Set the user ID.
	 *
	 */	
	public GagChatRoomUser setUserId(String userId) {
		this.userId = userId;
		return this;
	}
	
	/**
	 * Get the user ID.
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
