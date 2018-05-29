package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class CnComsitionDetailbean {

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
    private int error;
    private String reason;
    private List<ChCompositionDetail> chCompositionDetail;

    public static class ChCompositionDetail {
        private String chcompositionClassification;
        private String chcompositionClassificationId;
        private String chcompositionContext;
        private String chcompositionId;
        private String chcompositionTitle;

        public String getChcompositionClassification() {
            return chcompositionClassification;
        }

        public void setChcompositionClassification(String chcompositionClassification) {
            this.chcompositionClassification = chcompositionClassification;
        }

        public String getChcompositionClassificationId() {
            return chcompositionClassificationId;
        }

        public void setChcompositionClassificationId(String chcompositionClassificationId) {
            this.chcompositionClassificationId = chcompositionClassificationId;
        }

        public String getChcompositionContext() {
            return chcompositionContext;
        }

        public void setChcompositionContext(String chcompositionContext) {
            this.chcompositionContext = chcompositionContext;
        }

        public String getChcompositionId() {
            return chcompositionId;
        }

        public void setChcompositionId(String chcompositionId) {
            this.chcompositionId = chcompositionId;
        }

        public String getChcompositionTitle() {
            return chcompositionTitle;
        }

        public void setChcompositionTitle(String chcompositionTitle) {
            this.chcompositionTitle = chcompositionTitle;
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

    public List<ChCompositionDetail> getChCompositionDetail() {
        return chCompositionDetail;
    }

    public void setChCompositionDetail(List<ChCompositionDetail> chCompositionDetail) {
        this.chCompositionDetail = chCompositionDetail;
    }
}
