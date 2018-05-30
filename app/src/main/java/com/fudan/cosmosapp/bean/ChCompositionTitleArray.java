package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class ChCompositionTitleArray {

    /**
     {
     "chCompositionList": [
     {
     "CHCompositionId": "1",
     "CHCompositionTitle": "哎呀"
     }
     ],
     "error": "0",
     "reason": "success"
     }
     */

    private int error;
    private String reason;
    private List<ChCompositionList> chCompositionList;

    public static class ChCompositionList {
        /**
         *  "CHCompositionId": "1",
         "CHCompositionTitle": "哎呀"
         */
        private String CHCompositionId;
        private String CHCompositionTitle;

        public String getCHCompositionId() {
            return CHCompositionId;
        }

        public void setCHCompositionId(String CHCompositionId) {
            this.CHCompositionId = CHCompositionId;
        }

        public String getCHCompositionTitle() {
            return CHCompositionTitle;
        }

        public void setCHCompositionTitle(String CHCompositionTitle) {
            this.CHCompositionTitle = CHCompositionTitle;
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

    public List<ChCompositionList> getChCompositionList() {
        return chCompositionList;
    }

    public void setChCompositionList(List<ChCompositionList> chCompositionList) {
        this.chCompositionList = chCompositionList;
    }
}
