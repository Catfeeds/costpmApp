package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class EnCompositionTitleArray {

    /**
     {
     "enCompositionList": [
     {
     "ENCompositionId": "1",
     "ENCompositionTitle": "哎呀"
     }
     ],
     "error": "0",
     "reason": "success"
     }
     */

    private int error;
    private String reason;
    private List<EnCompositionList> enCompositionList;

    public static class EnCompositionList {
        /**
         *  "CHCompositionId": "1",
         "CHCompositionTitle": "哎呀"
         */
        private String ENCompositionId;
        private String ENCompositionTitle;

        public String getENCompositionId() {
            return ENCompositionId;
        }

        public void setENCompositionId(String ENCompositionId) {
            this.ENCompositionId = ENCompositionId;
        }

        public String getENCompositionTitle() {
            return ENCompositionTitle;
        }

        public void setENCompositionTitle(String ENCompositionTitle) {
            this.ENCompositionTitle = ENCompositionTitle;
        }
    }


    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<EnCompositionList> getEnCompositionList() {
        return enCompositionList;
    }

    public void setEnCompositionList(List<EnCompositionList> enCompositionList) {
        this.enCompositionList = enCompositionList;
    }
}
