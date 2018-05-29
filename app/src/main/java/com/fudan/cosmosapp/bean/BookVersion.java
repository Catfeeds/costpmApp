package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Ang on 2017/8/9.
 */

public class BookVersion {

    /**
     * reason : success
     * textbookVersionList : [{"textbookVersion":"人教版","textbookVersionId":"1"},{"textbookVersion":"北师大版","textbookVersionId":"2"},{"textbookVersion":"冀教版","textbookVersionId":"3"},{"textbookVersion":"苏教版","textbookVersionId":"4"},{"textbookVersion":"西师版","textbookVersionId":"5"}]
     * error : 0
     */

    private String reason;
    private String error;
    private List<TextbookVersionListBean> textbookVersionList;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<TextbookVersionListBean> getTextbookVersionList() {
        return textbookVersionList;
    }

    public void setTextbookVersionList(List<TextbookVersionListBean> textbookVersionList) {
        this.textbookVersionList = textbookVersionList;
    }

    public static class TextbookVersionListBean {
        /**
         * textbookVersion : 人教版
         * textbookVersionId : 1
         */

        private String textbookVersion;
        private String textbookVersionId;

        public String getTextbookVersion() {
            return textbookVersion;
        }

        public void setTextbookVersion(String textbookVersion) {
            this.textbookVersion = textbookVersion;
        }

        public String getTextbookVersionId() {
            return textbookVersionId;
        }

        public void setTextbookVersionId(String textbookVersionId) {
            this.textbookVersionId = textbookVersionId;
        }
    }
}
