package io.rong.models.sensitiveword;

import io.rong.util.GsonUtil;

import java.util.List;

/**
 * Batch add sensitive words
 */
public class AddSensitiveWordsModel {

    /**
     * List of sensitive words
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


    public static class SensitiveWord {
        /**
         * Sensitive word, required, length no more than 32 characters
         */
        private String word;
        /**
         * Replacement word, optional, length no more than 32 characters
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
