package io.rong.models;

import io.rong.util.GsonUtil;

/**
 * checkOnlineUser返回结果
 */
public class CheckOnlineResult {
	// 返回码，200 为正常。
	Integer code;
	// 在线状态，1为在线，0为不在线。
	String status;
	// 错误信息。
	String errorMessage;
	
	public CheckOnlineResult(Integer code, String status, String errorMessage) {
		this.code = code;
		this.status = status;
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
	 * 设置status
	 *
	 */	
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 获取status
	 *
	 * @return String
	 */
	public String getStatus() {
		return status;
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
		return GsonUtil.toJson(this, CheckOnlineResult.class);
	}
}
