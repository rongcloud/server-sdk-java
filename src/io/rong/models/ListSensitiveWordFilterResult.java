package io.rong.models;

import io.rong.util.GsonUtil;

import java.util.List;

/**
 * listWordfilter返回结果
 */
public class ListSensitiveWordFilterResult {
    // 返回码，200 为正常。
    Integer code;
    // 敏感词内容。
    List<Words> words;

    // 错误信息。
    String errorMessage;

    public ListSensitiveWordFilterResult(Integer code, List<Words> words, String errorMessage) {
        this.code = code;
        this.words = words;
        this.errorMessage = errorMessage;
    }

    /**
     * 设置code
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
     */
    public void setWords(List<Words> words) {
        this.words = words;
    }

    /**
     * 获取words
     *
     * @return String
     */
    public List<Words> getWords() {
        return words;
    }

    /**
     * 设置errorMessage
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
        return GsonUtil.toJson(this, ListSensitiveWordFilterResult.class);
    }


    class Words {
        // 查询敏感词的类型，0 为查询替换敏感词，1 为查询屏蔽敏感词，2 为查询全部敏感词
        String type;
        // 	敏感词内容。
        String word;
        //替换的敏感词内容。
        String replaceWord;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getReplaceWord() {
            return replaceWord;
        }

        public void setReplaceWord(String replaceWord) {
            this.replaceWord = replaceWord;
        }

        @Override
        public String toString() {
            return GsonUtil.toJson(this, Words.class);
        }
    }
}
