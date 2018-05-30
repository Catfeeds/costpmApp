package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Ang on 2017/8/9.
 */

public class Subject {

    /**
     * reason : success
     * subjectList : [{"subjectId":"2","subjectName":"数学"}]
     * error : 0
     */

    private String reason;
    private String error;
    private List<SubjectListBean> subjectList;

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

    public List<SubjectListBean> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<SubjectListBean> subjectList) {
        this.subjectList = subjectList;
    }

    public static class SubjectListBean {
        /**
         * subjectId : 2
         * subjectName : 数学
         */

        private String subjectId;
        private String subjectName;

        public String getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(String subjectId) {
            this.subjectId = subjectId;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }
    }
}
