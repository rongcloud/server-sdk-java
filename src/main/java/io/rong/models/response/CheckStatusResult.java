package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * CheckStatusResult
 */
public class CheckStatusResult extends Result {

	//禁言或其他状态
	String status;

	public CheckStatusResult(Integer code, String status, String errorMessage) {
		super(code, errorMessage);
		this.code = code;
		this.status = status;
		this.errorMessage = errorMessage;
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

	@Override
	public String toString() {
		return GsonUtil.toJson(this, CheckStatusResult.class);
	}
}
