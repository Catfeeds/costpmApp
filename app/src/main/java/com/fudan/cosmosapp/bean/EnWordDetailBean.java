package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class EnWordDetailBean {


    /**
     * reason : success
     * englishDictionaryDetail : [{"wordId":"1","englishPhonogram":"aipo","americanPhonogram":"aipo","word":"apple","wordTranslation":"苹果"},{"exampleSentencesId":"1","exampleSentences":"i have an apple","sentenceTranslation":"我有一个苹果"},{"exampleSentencesId":"2","exampleSentences":"you have an apple,too","sentenceTranslation":"你也有一个苹果"},{"nominal":"n","nominalId":"1"},{"nominal":"v","nominalId":"2"}]
     * error : 0
     */

    private String reason;
    private int error;
    private List<EnglishDictionaryDetailBean> englishDictionaryDetail;

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

    public List<EnglishDictionaryDetailBean> getEnglishDictionaryDetail() {
        return englishDictionaryDetail;
    }

    public void setEnglishDictionaryDetail(List<EnglishDictionaryDetailBean> englishDictionaryDetail) {
        this.englishDictionaryDetail = englishDictionaryDetail;
    }

    public static class EnglishDictionaryDetailBean {
        /**
         * wordId : 1
         * englishPhonogram : aipo
         * americanPhonogram : aipo
         * word : apple
         * wordTranslation : 苹果
         * exampleSentencesId : 1
         * exampleSentences : i have an apple
         * sentenceTranslation : 我有一个苹果
         * nominal : n
         * nominalId : 1
         */

        private String wordId;
        private String englishPhonogram;
        private String americanPhonogram;
        private String word;
        private String wordTranslation;
        private String exampleSentencesId;
        private String exampleSentences;
        private String sentenceTranslation;
        private String nominal;
        private String nominalId;

        public String getWordId() {
            return wordId;
        }

        public void setWordId(String wordId) {
            this.wordId = wordId;
        }

        public String getEnglishPhonogram() {
            return englishPhonogram;
        }

        public void setEnglishPhonogram(String englishPhonogram) {
            this.englishPhonogram = englishPhonogram;
        }

        public String getAmericanPhonogram() {
            return americanPhonogram;
        }

        public void setAmericanPhonogram(String americanPhonogram) {
            this.americanPhonogram = americanPhonogram;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getWordTranslation() {
            return wordTranslation;
        }

        public void setWordTranslation(String wordTranslation) {
            this.wordTranslation = wordTranslation;
        }

        public String getExampleSentencesId() {
            return exampleSentencesId;
        }

        public void setExampleSentencesId(String exampleSentencesId) {
            this.exampleSentencesId = exampleSentencesId;
        }

        public String getExampleSentences() {
            return exampleSentences;
        }

        public void setExampleSentences(String exampleSentences) {
            this.exampleSentences = exampleSentences;
        }

        public String getSentenceTranslation() {
            return sentenceTranslation;
        }

        public void setSentenceTranslation(String sentenceTranslation) {
            this.sentenceTranslation = sentenceTranslation;
        }

        public String getNominal() {
            return nominal;
        }

        public void setNominal(String nominal) {
            this.nominal = nominal;
        }

        public String getNominalId() {
            return nominalId;
        }

        public void setNominalId(String nominalId) {
            this.nominalId = nominalId;
        }
    }
}
