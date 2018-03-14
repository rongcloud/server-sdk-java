package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;
import java.util.List;

/**
 * listGagChatroomUser返回结果
 */
public class ListGagChatroomUserResult extends Result{
	// 聊天室被禁言用户列表。
	List<GagChatRoomUser> users;

	public ListGagChatroomUserResult(Integer code, List<GagChatRoomUser> users, String errorMessage) {
		this.code = code;
		this.users = users;
		this.msg = errorMessage;
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
	 * 设置users
	 *
	 */	
	public void setUsers(List<GagChatRoomUser> users) {
		this.users = users;
	}
	
	/**
	 * 获取users
	 *
	 * @return List<GagChatRoomUser>
	 */
	public List<GagChatRoomUser> getUsers() {
		return users;
	}


	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, ListGagChatroomUserResult.class);
	}
}
