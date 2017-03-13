package io.rong.models;

import io.rong.util.GsonUtil;

/**
 * historyMessage返回结果
 */
public class HistoryMessageResult {
	// 返回码，200 为正常。
	Integer code;
	// 历史消息下载地址。
	String url;
	// 历史记录时间。（yyyymmddhh）
	String date;
	// 错误信息。
	String errorMessage;
	
	public HistoryMessageResult(Integer code, String url, String date, String errorMessage) {
		this.code = code;
		this.url = url;
		this.date = date;
		this.errorMessage = errorMessage;
	}
	
	/**
	 * 设置code
	 *
	 */	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	/**
	 * 获取code
	 *
	 * @return Integer
	 */
	public Integer getCode() {
		return code;
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
	 * 设置date
	 *
	 */	
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * 获取date
	 *
	 * @return String
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * 设置errorMessage
	 *
	 */	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * 获取errorMessage
	 *
	 * @return String
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, HistoryMessageResult.class);
	}
}
