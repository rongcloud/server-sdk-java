package io.rong.models;

import io.rong.util.GsonUtil;

/**
 *  getImageCode 成功返回结果
 */
public class SMSImageCodeResult {
	// 返回码，200 为正常。
	Integer code;
	// 返回的图片验证码 URL 地址。
	String url;
	// 返回图片验证标识 Id。
	String verifyId;
	// 错误信息。
	String errorMessage;
	
	public SMSImageCodeResult(Integer code, String url, String verifyId, String errorMessage) {
		this.code = code;
		this.url = url;
		this.verifyId = verifyId;
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
	 * 设置url
	 *
	 */	
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 获取url
	 *
	 * @return String
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * 设置verifyId
	 *
	 */	
	public void setVerifyId(String verifyId) {
		this.verifyId = verifyId;
	}
	
	/**
	 * 获取verifyId
	 *
	 * @return String
	 */
	public String getVerifyId() {
		return verifyId;
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
		return GsonUtil.toJson(this, SMSImageCodeResult.class);
	}
}
