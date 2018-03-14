package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 *  SMSSendCodeResult 成功返回结果
 */
public class SMSSendCodeResult extends Result {
	// 短信验证码唯一标识。
	String sessionId;

	public SMSSendCodeResult(Integer code, String sessionId, String errorMessage) {
		super(code, errorMessage);
		this.code = code;
		this.sessionId = sessionId;
		this.msg = errorMessage;
	}

	/**
	 * 设置sessionId
	 *
	 */	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	/**
	 * 获取sessionId
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
