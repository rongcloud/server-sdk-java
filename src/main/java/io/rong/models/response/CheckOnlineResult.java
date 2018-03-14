package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * checkOnlineUser返回结果
 */
public class CheckOnlineResult extends Result {

	// 在线状态，1为在线，0为不在线。
	String status;

	public CheckOnlineResult(Integer code, String status, String errorMessage) {
		super(code, errorMessage);
		this.code = code;
		this.status = status;
		this.msg = errorMessage;
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
		return GsonUtil.toJson(this, CheckOnlineResult.class);
	}
}
