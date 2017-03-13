package io.rong.models;

import io.rong.util.GsonUtil;
import java.util.List;

/**
 * queryBlockUser返回结果
 */
public class QueryBlockUserResult {
	// 返回码，200 为正常。
	Integer code;
	// 被封禁用户列表。
	List<BlockUsers> users;
	// 错误信息。
	String errorMessage;
	
	public QueryBlockUserResult(Integer code, List<BlockUsers> users, String errorMessage) {
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
	public void setUsers(List<BlockUsers> users) {
		this.users = users;
	}
	
	/**
	 * 获取users
	 *
	 * @return List<BlockUsers>
	 */
	public List<BlockUsers> getUsers() {
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
		return GsonUtil.toJson(this, QueryBlockUserResult.class);
	}
}
