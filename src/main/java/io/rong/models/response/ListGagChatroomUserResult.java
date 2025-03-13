package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.util.GsonUtil;
import java.util.List;

/**
 * Result of listing muted users in a chatroom.
 */
public class ListGagChatroomUserResult extends Result{
	/**
	 * List of muted users in the chatroom.
	 *
	 */
	List<ChatroomMember> members;

	public ListGagChatroomUserResult(Integer code, String msg, List<ChatroomMember> members) {
		super(code, msg);
		this.members = members;
	}

	public ListGagChatroomUserResult(List<ChatroomMember> members) {
		this.members = members;
	}

	/**
	 * Sets the code.
	 *
	 */	
	@Override
	public void setCode(Integer code) {
		this.code = code;
	}
	
	/**
	 * Gets the code.
	 *
	 * @return Integer
	 */
	@Override
	public Integer getCode() {
		return code;
	}
	/**
	 * Gets the members.
	 *
	 * @return List
	 */
	public List<ChatroomMember> getMembers() {
		return this.members;
	}
	/**
	 * Set members
	 *
	 */
	public void setMembers(List<ChatroomMember> members) {
		this.members = members;
	}


	@Override
	public String toString() {
		return GsonUtil.toJson(this, ListGagChatroomUserResult.class);
	}
}
