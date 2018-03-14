package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 *  getImageCode 成功返回结果
 */
public class SMSImageCodeResult extends Result {
	// 返回的图片验证码 URL 地址。
	String url;
	// 返回图片验证标识 Id。
	String verifyId;

	public SMSImageCodeResult(Integer code, String url, String verifyId, String errorMessage) {
		this.code = code;
		this.url = url;
		this.verifyId = verifyId;
		this.msg = errorMessage;
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

	@Override
	public String toString() {
		return GsonUtil.toJson(this, SMSImageCodeResult.class);
	}
}
