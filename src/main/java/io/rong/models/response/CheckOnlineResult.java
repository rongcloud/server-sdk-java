package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * Result of checking online user status
 */
public class CheckOnlineResult extends Result {

	// User online status, 1 for online, 0 for offline.
	String status;

	public CheckOnlineResult(Integer code, String status, String errorMessage) {
		super(code, errorMessage);
		this.code = code;
		this.status = status;
		this.errorMessage = errorMessage;
	}
	/**
	 * Set the status
	 *
	 */	
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Get the status
	 *
	 * @return String
	 */
	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, CheckOnlineResult.class);
	}
}
