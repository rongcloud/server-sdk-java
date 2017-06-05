package io.rong.models;

import io.rong.util.GsonUtil;

/**
 * 敏感词、替换词信息
 */
public class Sensitivewords {
	// 查询类型
	String type;
	// 敏感词。
	String word;
	// 替换词。
	String replaceWord;
	
	public Sensitivewords(String type, String word, String replaceWord) {
		this.type = type;
		this.word = word;
		this.replaceWord = replaceWord;
	}
	
	/**
	 * 设置type
	 *
	 */	
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 获取type
	 *
	 * @return String
	 */
	public String getType() {
		return type;
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
	 * 设置replaceWord
	 *
	 */	
	public void setReplaceWord(String replaceWord) {
		this.replaceWord = replaceWord;
	}
	
	/**
	 * 获取replaceWord
	 *
	 * @return String
	 */
	public String getReplaceWord() {
		return replaceWord;
	}
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, Sensitivewords.class);
	}
}
