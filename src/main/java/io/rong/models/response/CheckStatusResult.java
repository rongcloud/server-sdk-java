package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * CheckStatusResult
 */
public class CheckStatusResult extends Result {

	// Mute or other statuses
	String status;

	public CheckStatusResult(Integer code, String status, String errorMessage) {
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
		return GsonUtil.toJson(this, CheckStatusResult.class);
	}
}
