package io.rong.models;

import io.rong.util.GsonUtil;

/**
 *  SMSSendCodeResult 成功返回结果
 */
public class SMSSendCodeResult {
	// 返回码，200 为正常。
	Integer code;
	// 短信验证码唯一标识。
	String sessionId;
	// 错误信息。
	String errorMessage;
	
	public SMSSendCodeResult(Integer code, String sessionId, String errorMessage) {
		this.code = code;
		this.sessionId = sessionId;
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
	 * 设置sessionId
	 *
	 */	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	/**
	 * 获取sessionId
	 *
	 * @return String
	 */
	public String getSessionId() {
		return sessionId;
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
		return GsonUtil.toJson(this, SMSSendCodeResult.class);
	}
}
