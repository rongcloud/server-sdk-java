package io.rong.models;

import io.rong.util.GsonUtil;

/**
 *  VerifyCode 返回结果
 */
public class SMSVerifyCodeResult {
	// 返回码，200 为正常。
	Integer code;
	// true 验证成功，false 验证失败。
	Boolean success;
	// 错误信息。
	String errorMessage;
	
	public SMSVerifyCodeResult(Integer code, Boolean success, String errorMessage) {
		this.code = code;
		this.success = success;
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
	 * 设置success
	 *
	 */	
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	/**
	 * 获取success
	 *
	 * @return Boolean
	 */
	public Boolean getSuccess() {
		return success;
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
		return GsonUtil.toJson(this, SMSVerifyCodeResult.class);
	}
}
