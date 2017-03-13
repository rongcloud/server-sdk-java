package io.rong.models;

import io.rong.util.GsonUtil;
import java.util.List;

/**
 * listGagChatroomUser返回结果
 */
public class ListGagChatroomUserResult {
	// 返回码，200 为正常。
	Integer code;
	// 聊天室被禁言用户列表。
	List<GagChatRoomUser> users;
	// 错误信息。
	String errorMessage;
	
	public ListGagChatroomUserResult(Integer code, List<GagChatRoomUser> users, String errorMessage) {
		this.code = code;
		this.users = users;
		this.errorMessage = errorMessage;
	}
	
	/**
	 * 设置code
	 *
	 */	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	/**
	 * 获取code
	 *
	 * @return Integer
	 */
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
	
	/**
	 * 设置errorMessage
	 *
	 */	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * 获取errorMessage
	 *
	 * @return String
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, ListGagChatroomUserResult.class);
	}
}
