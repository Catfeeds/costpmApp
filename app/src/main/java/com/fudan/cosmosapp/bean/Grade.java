package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by yinxiaofei on 2017/8/9.
 */

public class Grade {

    /**
     * reason : success
     * gradeList : [{"gradeName":"一年级","gradeId":"0"},{"gradeName":"一年级上册","gradeId":"1"},{"gradeName":"一年级下册","gradeId":"2"},{"gradeName":"二年级上册","gradeId":"3"},{"gradeName":"二年级下册","gradeId":"4"},{"gradeName":"三年级上册","gradeId":"5"},{"gradeName":"三年级下册","gradeId":"6"},{"gradeName":"四年级上册","gradeId":"7"},{"gradeName":"四年级下册","gradeId":"8"},{"gradeName":"五年级上册","gradeId":"9"},{"gradeName":"五年级下册","gradeId":"10"},{"gradeName":"六年级上册","gradeId":"11"},{"gradeName":"六年级下册","gradeId":"12"},{"gradeName":"七年级上册","gradeId":"13"},{"gradeName":"七年级下册","gradeId":"14"},{"gradeName":"八年级上册","gradeId":"15"},{"gradeName":"八年级下册","gradeId":"16"},{"gradeName":"九年级上册","gradeId":"17"},{"gradeName":"九年级下册","gradeId":"18"}]
     * error : 0
     */

    private String reason;
    private String error;
    private List<GradeListBean> gradeList;

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

    public List<GradeListBean> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<GradeListBean> gradeList) {
        this.gradeList = gradeList;
    }

    public static class GradeListBean {
        /**
         * gradeName : 一年级
         * gradeId : 0
         */

        private String gradeName;
        private int gradeId;

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public int getGradeId() {
            return gradeId;
        }

        public void setGradeId(int gradeId) {
            this.gradeId = gradeId;
        }
    }
}
