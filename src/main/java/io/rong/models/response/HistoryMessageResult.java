package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

/**
 * History message result.
 */
public class HistoryMessageResult extends Result {

	// URL for downloading historical messages.
	String url;
	// Date of the historical records. (yyyymmddhh)
	String date;

	public HistoryMessageResult(Integer code, String url, String date, String errorMessage) {
		this.code = code;
		this.url = url;
		this.date = date;
		this.errorMessage = errorMessage;
	}
	/**
	 * Set the URL.
	 *
	 */	
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Get the URL.
	 *
	 * @return String
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Set the date.
	 *
	 */	
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Get the date.
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
