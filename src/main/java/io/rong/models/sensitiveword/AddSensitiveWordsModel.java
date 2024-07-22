package io.rong.models.sensitiveword;

import io.rong.util.GsonUtil;

import java.util.List;

/**
 * 批量添加敏感词
 */
public class AddSensitiveWordsModel {

	/**
	 * 敏感词列表
	 */
	List<SensitiveWord> words;

	public List<SensitiveWord> getWords() {
		return words;
	}

	public AddSensitiveWordsModel setWords(List<SensitiveWord> words) {
		this.words = words;
		return this;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, AddSensitiveWordsModel.class);
	}


	public static class SensitiveWord{
		/**
		 * 敏感词， 必传，长度不大于 32 个字符
		 */
		private String word;
		/**
		 * 替换词， 非必传，长度不大于 32 个字符
		 */
		private String replaceWord;

		public String getWord() {
			return word;
		}

		public SensitiveWord setWord(String word) {
			this.word = word;
			return this;
		}

		public String getReplaceWord() {
			return replaceWord;
		}

		public SensitiveWord setReplaceWord(String replaceWord) {
			this.replaceWord = replaceWord;
			return this;
		}
	}
}
