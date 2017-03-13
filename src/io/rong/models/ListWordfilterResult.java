package io.rong.models;

import io.rong.util.GsonUtil;

/**
 * listWordfilter返回结果
 */
public class ListWordfilterResult {
	// 返回码，200 为正常。
	Integer code;
	// 敏感词内容。
	String word;
	// 错误信息。
	String errorMessage;
	
	public ListWordfilterResult(Integer code, String word, String errorMessage) {
		this.code = code;
		this.word = word;
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
	 * 设置word
	 *
	 */	
	public void setWord(String word) {
		this.word = word;
	}
	
	/**
	 * 获取word
	 *
	 * @return String
	 */
	public String getWord() {
		return word;
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
		return GsonUtil.toJson(this, ListWordfilterResult.class);
	}
}
