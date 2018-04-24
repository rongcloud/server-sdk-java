package io.rong.models.sensitiveword;

import io.rong.util.GsonUtil;

/**
 * 敏感词、替换词信息
 */
public class SensitiveWordModel {
	/**
	 * 敏感词类型
	 */
	Integer type = 1;
	/**
	 *敏感词
	 */
	String keyword;
	/**
	 *替换词
	 */
	String replace;

	public SensitiveWordModel(Integer type, String keyword, String replace) {
		this.type = type;
		this.keyword = keyword;
		this.replace = replace;
	}

	public SensitiveWordModel() {
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
	 * 获取敏感词
	 *
	 * @return String
	 */
	public String getKeyword() {
		return this.keyword;
	}
	/**
	 * @param keyword 设置敏感词
	 *
	 */
	public SensitiveWordModel setKeyword(String keyword) {
		this.keyword = keyword;
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
