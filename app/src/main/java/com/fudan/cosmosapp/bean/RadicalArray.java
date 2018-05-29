package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class RadicalArray {

    /**
      {
     "error": "0",
     "radicalList": [
     {
     "radical": "王",
     "radicalId": "1"
     },
     {
     "radical": "火",
     "radicalId": "2"
     },
     {
     "radical": "月",
     "radicalId": "3"
     }
     ],
     "reason": "success"
     }
     */

    private int error;
    private String reason;
    private List<RadicalList> radicalList;

    public static class RadicalList {
        /**
        {
            "radical": "王",
            "radicalId": "1"
        }
        */
        private String radical;
        private String radicalId;

        public String getRadical() {
            return radical;
        }

        public void setRadical(String radical) {
            this.radical = radical;
        }

        public String getRadicalId() {
            return radicalId;
        }

        public void setRadicalId(String radicalId) {
            this.radicalId = radicalId;
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

    public List<RadicalList> getRadicalList() {
        return radicalList;
    }

    public void setRadicalList(List<RadicalList> radicalList) {
        this.radicalList = radicalList;
    }
}
