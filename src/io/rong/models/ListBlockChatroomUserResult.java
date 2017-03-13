package io.rong.models;

import io.rong.util.GsonUtil;
import java.util.List;

/**
 * listBlockChatroomUser返回结果
 */
public class ListBlockChatroomUserResult {
	// 返回码，200 为正常。
	Integer code;
	// 被封禁用户列表。
	List<BlockChatRoomUser> users;
	// 错误信息。
	String errorMessage;
	
	public ListBlockChatroomUserResult(Integer code, List<BlockChatRoomUser> users, String errorMessage) {
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
		return GsonUtil.toJson(this, ListBlockChatroomUserResult.class);
	}
}
