package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 *  SMS verification code result.
 */
public class SMSVerifyCodeResult extends Result {
	// Indicates whether the verification is successful: true for success, false for failure.
	Boolean success;

	public SMSVerifyCodeResult(Integer code, Boolean success, String errorMessage) {
		super(code, errorMessage);
		this.code = code;
		this.success = success;
		this.errorMessage = errorMessage;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	/**
	 * Get the success status.
	 *
	 * @return Boolean
	 */
	public Boolean getSuccess() {
		return success;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, SMSVerifyCodeResult.class);
	}
}
