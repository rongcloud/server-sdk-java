package io.rong.models.sensitiveword;

import io.rong.util.GsonUtil;

/**
 * 敏感词、替换词信息
 */
public class SensitiveWordModel {
	/**
	 * 敏感词类型
	 */
	Integer type;
	/**
	 *敏感词
	 */
	String keyWord;
	/**
	 *替换词
	 */
	String replace;

	public SensitiveWordModel() {
	}

	public SensitiveWordModel(Integer type, String keyWord, String replace) {
		this.type = type;
		this.keyWord = keyWord;
		this.replace = replace;
	}

	/**
	 * 设置type
	 */
	public SensitiveWordModel setType(Integer type) {
		this.type = type;
		return this;
	}
	/**
	 * 获取type
	 *
	 * @return String
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * 获取word
	 *
	 * @return String
	 */
	public String getKeyWord() {
		return this.keyWord;
	}

	/**
	 * 设置word
	 *
	 */
	public SensitiveWordModel setKeyWord(String keyWord) {
		this.keyWord = keyWord;
		return this;
	}

	/**
	 * 获取replace
	 *
	 * @return String
	 */
	public String getReplace() {
		return this.replace;
	}

	/**
	 * 设置replace
	 *
	 */
	public SensitiveWordModel setReplace(String replace) {
		this.replace = replace;
		return this;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, SensitiveWordModel.class);
	}
}
