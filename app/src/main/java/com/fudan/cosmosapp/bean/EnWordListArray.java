package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class EnWordListArray {


    /**
     * reason : success
     * error : 0
     * englishDictionaryList : [{"wordId":"1","word":"apple"},{"wordId":"2","word":"ant"}]
     */

    private String reason;
    private int error;
    private List<EnglishDictionaryListBean> englishDictionaryList;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<EnglishDictionaryListBean> getEnglishDictionaryList() {
        return englishDictionaryList;
    }

    public void setEnglishDictionaryList(List<EnglishDictionaryListBean> englishDictionaryList) {
        this.englishDictionaryList = englishDictionaryList;
    }

    public static class EnglishDictionaryListBean {
        /**
         * wordId : 1
         * word : apple
         */

        private String wordId;
        private String word;

        public String getWordId() {
            return wordId;
        }

        public void setWordId(String wordId) {
            this.wordId = wordId;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }
    }
}
