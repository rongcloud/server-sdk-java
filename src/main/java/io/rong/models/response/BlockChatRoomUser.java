package io.rong.models.response;

import io.rong.util.GsonUtil;

/**
 * Information of a banned user in the chatroom.
 * @author RongCloud
 */
public class BlockChatRoomUser {
	// The user ID in the chatroom.
	String userId;
	// The time when the user joined the chatroom.
	String time;
	
	public BlockChatRoomUser(String userId, String time) {
		this.userId = userId;
		this.time = time;
	}
	
	/**
	 * Sets the userId.
	 *
	 */	
	public BlockChatRoomUser setUserId(String userId) {
		this.userId = userId;
		return this;
	}
	
	/**
	 * Gets the userId.
	 *
	 * @return String
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * Sets the time.
	 *
	 */	
	public BlockChatRoomUser setTime(String time) {
		this.time = time;
		return this;
	}
	
	/**
	 * Gets the time.
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
