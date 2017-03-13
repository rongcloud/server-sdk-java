package io.rong.models;

import io.rong.util.GsonUtil;
import java.util.List;

/**
 *  chatroomUserQuery 返回结果
 */
public class ChatroomUserQueryResult {
	// 返回码，200 为正常。
	Integer code;
	// 聊天室中用户数。
	Integer total;
	// 聊天室成员列表。
	List<ChatRoomUser> users;
	// 错误信息。
	String errorMessage;
	
	public ChatroomUserQueryResult(Integer code, Integer total, List<ChatRoomUser> users, String errorMessage) {
		this.code = code;
		this.total = total;
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
	 * 设置total
	 *
	 */	
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	/**
	 * 获取total
	 *
	 * @return Integer
	 */
	public Integer getTotal() {
		return total;
	}
	
	/**
	 * 设置users
	 *
	 */	
	public void setUsers(List<ChatRoomUser> users) {
		this.users = users;
	}
	
	/**
	 * 获取users
	 *
	 * @return List<ChatRoomUser>
	 */
	public List<ChatRoomUser> getUsers() {
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
		return GsonUtil.toJson(this, ChatroomUserQueryResult.class);
	}
}
