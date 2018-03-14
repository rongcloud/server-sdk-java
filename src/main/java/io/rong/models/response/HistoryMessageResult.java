package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * historyMessage返回结果
 */
public class HistoryMessageResult extends Result {

	// 历史消息下载地址。
	String url;
	// 历史记录时间。（yyyymmddhh）
	String date;

	public HistoryMessageResult(Integer code, String url, String date, String errorMessage) {
		this.code = code;
		this.url = url;
		this.date = date;
		this.msg = errorMessage;
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

	@Override
	public String toString() {
		return GsonUtil.toJson(this, HistoryMessageResult.class);
	}
}
