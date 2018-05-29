package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class EnCompositionClassifyBean {
    /**
     {
     "chCClassificationList": [
     {
     "CHCompositionClassification": "风水类",
     "CHCompositionClassificationId": "1"
     }
     ],
     "error": "0",
     "reason": "success"
     }
     */

    private String reason;
    private int error;
    private List<EnCClassificationList> enCClassificationList;

    public static class EnCClassificationList {
        private String ENCompositionClassification;
        private String ENCompositionClassificationId;

        public String getENCompositionClassification() {
            return ENCompositionClassification;
        }

        public void setENCompositionClassification(String ENCompositionClassification) {
            this.ENCompositionClassification = ENCompositionClassification;
        }

        public String getENCompositionClassificationId() {
            return ENCompositionClassificationId;
        }

        public void setENCompositionClassificationId(String ENCompositionClassificationId) {
            this.ENCompositionClassificationId = ENCompositionClassificationId;
        }
    }

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

    public List<EnCClassificationList> getEnCClassificationLists() {
        return enCClassificationList;
    }

    public void setEnCClassificationLists(List<EnCClassificationList> enCClassificationLists) {
        this.enCClassificationList = enCClassificationLists;
    }
}
