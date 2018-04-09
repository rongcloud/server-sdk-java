package io.rong.models.response;

import io.rong.models.Result;
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
	List<GagChatRoomUser> members;

	public ListGagChatroomUserResult(Integer code, String msg, List<GagChatRoomUser> members) {
		super(code, msg);
		this.members = members;
	}

	public ListGagChatroomUserResult(List<GagChatRoomUser> members) {
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
	public List<GagChatRoomUser> getMembers() {
		return this.members;
	}
	/**
	 * 设置members
	 *
	 */
	public void setMembers(List<GagChatRoomUser> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ListGagChatroomUserResult.class);
	}
}
