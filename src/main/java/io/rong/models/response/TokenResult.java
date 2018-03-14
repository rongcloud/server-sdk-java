package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * getToken 返回结果
 */
public class TokenResult extends Result{
	// 用户 Token，可以保存应用内，长度在 256 字节以内.用户 Token，可以保存应用内，长度在 256 字节以内。
	String token;
	// 用户 Id，与输入的用户 Id 相同.
	String userId;

	public TokenResult(Integer code, String token, String userId, String errorMessage) {
		this.code = code;
		this.token = token;
		this.userId = userId;
		this.msg = errorMessage;
	}

	/**
	 * 设置token
	 *
	 */	
	public void setToken(String token) {
		this.token = token;
	}
	
	/**
	 * 获取token
	 *
	 * @return String
	 */
	public String getToken() {
		return token;
	}
	
	/**
	 * 设置userId
	 *
	 */	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取userId
	 *
	 * @return String
	 */
	public String getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, TokenResult.class);
	}
}
