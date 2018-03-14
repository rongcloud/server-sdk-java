package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;
import java.util.List;

/**
 * listBlockChatroomUser返回结果
 */
public class ListBlockChatroomUserResult extends Result{
	// 被封禁用户列表。
	List<BlockChatRoomUser> users;

	public ListBlockChatroomUserResult(Integer code, List<BlockChatRoomUser> users, String errorMessage) {
		this.code = code;
		this.users = users;
		this.msg = errorMessage;
	}

	/**
	 * 设置users
	 *
	 */	
	public void setUsers(List<BlockChatRoomUser> users) {
		this.users = users;
	}
	
	/**
	 * 获取users
	 *
	 * @return List<BlockChatRoomUser>
	 */
	public List<BlockChatRoomUser> getUsers() {
		return users;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ListBlockChatroomUserResult.class);
	}
}
