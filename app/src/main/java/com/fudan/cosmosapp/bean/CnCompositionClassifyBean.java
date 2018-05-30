package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class CnCompositionClassifyBean {
    /**
     {{
     "reason": "success",
     "chCClassificationList": [
     {
     "CHCompositionClassification": "写人",
     "CHCompositionClassificationId": "1"
     },
     {
     "CHCompositionClassification": "叙事",
     "CHCompositionClassificationId": "2"
     },
     {
     "CHCompositionClassification": "写景",
     "CHCompositionClassificationId": "3"
     },
     {
     "CHCompositionClassification": "状物",
     "CHCompositionClassificationId": "4"
     },
     {
     "CHCompositionClassification": "书信",
     "CHCompositionClassificationId": "5"
     },
     {
     "CHCompositionClassification": "日记",
     "CHCompositionClassificationId": "6"
     }
     ],
     "error": "0"
     }
     */

    private String reason;
    private int error;
    private List<ChCClassificationList> chCClassificationList;

    public static class ChCClassificationList {
        private String CHCompositionClassification;
        private String CHCompositionClassificationId;

        public String getCHCompositionClassification() {
            return CHCompositionClassification;
        }

        public void setCHCompositionClassification(String CHCompositionClassification) {
            this.CHCompositionClassification = CHCompositionClassification;
        }

        public String getCHCompositionClassificationId() {
            return CHCompositionClassificationId;
        }

        public void setCHCompositionClassificationId(String CHCompositionClassificationId) {
            this.CHCompositionClassificationId = CHCompositionClassificationId;
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

    public List<ChCClassificationList> getChCClassificationList() {
        return chCClassificationList;
    }

    public void setChCClassificationList(List<ChCClassificationList> chCClassificationList) {
        this.chCClassificationList = chCClassificationList;
    }
}
