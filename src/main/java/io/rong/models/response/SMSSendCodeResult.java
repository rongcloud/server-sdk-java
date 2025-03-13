package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * SMSSendCodeResult is the successful response result.
 */
public class SMSSendCodeResult extends Result {
	// The unique identifier of the SMS verification code.
	String sessionId;

	public SMSSendCodeResult(Integer code, String sessionId, String errorMessage) {
		super(code, errorMessage);
		this.code = code;
		this.sessionId = sessionId;
		this.errorMessage = errorMessage;
	}

	/**
	 * Set the sessionId.
	 *
	 */	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	/**
	 * Get the sessionId.
	 *
	 * @return String
	 */
	public String getSessionId() {
		return sessionId;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, SMSSendCodeResult.class);
	}
}
