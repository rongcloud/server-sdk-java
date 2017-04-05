package io.rong.models;

import io.rong.util.GsonUtil;
import java.util.Map;
import java.util.List;

/**
 * listWordfilter返回结果
 */
public class ListWordfilterResult {
	// 返回码，200 为正常。
	Integer code;
	// 敏感词内容。
	List<Map<String, String>> words;
	// 错误信息。
	String errorMessage;
	
	public ListWordfilterResult(Integer code, List<Map<String, String>> words, String errorMessage) {
		this.code = code;
		this.words = words;
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
	 * 设置words
	 *
	 */	
	public void setWords(List<Map<String, String>> words) {
		this.words = words;
	}
	
	/**
	 * 获取words
	 *
	 * @return List<Map<String, String>>
	 */
	public List<Map<String, String>> getWords() {
		return words;
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
