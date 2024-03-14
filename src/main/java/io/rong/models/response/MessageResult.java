package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * 系统消息返回结果
 */
public class MessageResult extends ResponseResult {

	// 消息ID
	String messageUID;
	// 发送消息时间戳
	Long sentTime;

	public MessageResult(Integer code, String msg) {
		super(code, msg);
		this.code = code;
		this.errorMessage = msg;
	}
	public MessageResult(Integer code, Long sentTime, String messageUID, String msg) {
		super(code, msg);
		this.code = code;
		this.messageUID = messageUID;
		this.sentTime = sentTime;
		this.errorMessage = msg;
	}
	/**
	 * 设置消息ID
	 *
	 */	
	public void setMessageUID(String url) {
		this.messageUID = messageUID;
	}
	
	/**
	 * 获取消息ID
	 *
	 * @return String
	 */
	public String getMessageUID() {
		return messageUID;
	}
	
	/**
	 * 设置发送消息时间戳
	 *
	 */	
	public void setDate(Long sentTime) {
		this.sentTime = sentTime;
	}
	
	/**
	 * 获取发送消息时间戳
	 *
	 * @return String
	 */
	public Long getDate() {
		return sentTime;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, MessageResult.class);
	}
}
