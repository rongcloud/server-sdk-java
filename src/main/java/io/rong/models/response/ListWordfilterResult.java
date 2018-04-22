package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.sensitiveword.SensitiveWordModel;
import io.rong.util.GsonUtil;

import java.util.List;

/**
 * listWordfilter返回结果
 */
public class ListWordfilterResult extends Result {
	// 敏感词内容。
	List<SensitiveWordModel> words;

	public ListWordfilterResult(Integer code, List<SensitiveWordModel> words, String errorMessage) {
		super(code, errorMessage);
		this.code = code;
		this.words = words;
		this.msg = errorMessage;
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
	public void setWords(List<SensitiveWordModel> words) {
		this.words = words;
	}
	
	/**
	 * 获取words
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
