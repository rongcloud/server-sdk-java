package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * Successful result of getImageCode
 */
public class SMSImageCodeResult extends Result {
	// URL of the returned image verification code.
	String url;
	// ID of the returned image verification code.
	String verifyId;

	public SMSImageCodeResult(Integer code, String url, String verifyId, String errorMessage) {
		this.code = code;
		this.url = url;
		this.verifyId = verifyId;
		this.errorMessage = errorMessage;
	}

	/**
	 * Sets the URL
	 *
	 */	
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Gets the URL
	 *
	 * @return String
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Sets the verifyId
	 *
	 */	
	public void setVerifyId(String verifyId) {
		this.verifyId = verifyId;
	}
	
	/**
	 * Gets the verifyId
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
