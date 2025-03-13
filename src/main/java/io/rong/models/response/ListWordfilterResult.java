package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.sensitiveword.SensitiveWordModel;
import io.rong.util.GsonUtil;

import java.util.List;

/**
 * Result of listWordfilter operation
 */
public class ListWordfilterResult extends Result {
	// Sensitive word content.
	List<SensitiveWordModel> words;

	public ListWordfilterResult(Integer code, List<SensitiveWordModel> words, String errorMessage) {
		super(code, errorMessage);
		this.code = code;
		this.words = words;
		this.errorMessage = errorMessage;
	}
	
	/**
	 * Set the code
	 *
	 */	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	/**
	 * Get the code
	 *
	 * @return Integer
	 */
	public Integer getCode() {
		return code;
	}
	
	/**
	 * Set the words
	 *
	 */	
	public void setWords(List<SensitiveWordModel> words) {
		this.words = words;
	}
	
	/**
	 * Get the words
	 *
	 * @return List
	 */
	public List<SensitiveWordModel> getWords() {
		return words;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ListWordfilterResult.class);
	}
}
