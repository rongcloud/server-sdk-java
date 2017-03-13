package io.rong.models;

import io.rong.util.GsonUtil;

/**
 * queryBlacklistUser返回结果
 */
public class QueryBlacklistUserResult {
	// 返回码，200 为正常。
	Integer code;
	// 黑名单用户列表。
	String[] users;
	// 错误信息。
	String errorMessage;
	
	public QueryBlacklistUserResult(Integer code, String[] users, String errorMessage) {
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
	public void setUsers(String[] users) {
		this.users = users;
	}
	
	/**
	 * 获取users
	 *
	 * @return String[]
	 */
	public String[] getUsers() {
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
		return GsonUtil.toJson(this, QueryBlacklistUserResult.class);
	}
}
