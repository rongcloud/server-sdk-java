package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.util.GsonUtil;
import java.util.List;

/**
 * 聊天室禁言返回结果
 */
public class ListGagChatroomUserResult extends Result{
	/**
	 * 聊天室被禁言用户列表。
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
	 * 设置code
	 *
	 */	
	@Override
	public void setCode(Integer code) {
		this.code = code;
	}
	
	/**
	 * 获取code
	 *
	 * @return Integer
	 */
	@Override
	public Integer getCode() {
		return code;
	}
	/**
	 * 获取members
	 *
	 * @return List
	 */
	public List<ChatroomMember> getMembers() {
		return this.members;
	}
	/**
	 * 设置members
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
