package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class EnComsitionDetailbean {

    /**
     {
     "chCompositionDetail": [
     {
     "chcompositionClassification": "风水类",
     "chcompositionClassificationId": "1",
     "chcompositionContext": "aaaaaa",
     "chcompositionId": "1",
     "chcompositionTitle": "哎呀"
     }
     ],
     "error": "0",
     "reason": "success"
     }
     */
    private List<EnCompositionDetail> enCompositionDetail;
    private int error;
    private String reason;

    public static class EnCompositionDetail {
        private String ENCompositionClassification;
        private String ENCompositionClassificationId;
        private String ENCompositionContext;
        private String ENCompositionId;
        private String ENCompositionTitle;

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

        public String getENCompositionContext() {
            return ENCompositionContext;
        }

        public void setENCompositionContext(String ENCompositionContext) {
            this.ENCompositionContext = ENCompositionContext;
        }

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

    public List<EnCompositionDetail> getEnCompositionDetail() {
        return enCompositionDetail;
    }

    public void setEnCompositionDetail(List<EnCompositionDetail> enCompositionDetail) {
        this.enCompositionDetail = enCompositionDetail;
    }
}
