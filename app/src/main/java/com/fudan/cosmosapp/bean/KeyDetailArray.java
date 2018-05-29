package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class KeyDetailArray {

    /**
     * {
     "error": "0",
     "keyDetail": [
     {
     "detailMeaning": "dm",
     "keyId": "2",
     "keyName": "c",
     "meaning": "m",
     "radical": "月",
     "radicalId": "3",
     "rmStrokeMumber": "11",
     "strokeNumber": "12"
     },
     {
     "pinyin": "chao",
     "pinyinId": "1"
     },
     {
     "pinyin": "zhao",
     "pinyinId": "2"
     }
     ],
     "reason": "success"
     }
     */

    private int error;
    private String reason;
    private List<KeyDetail> keyDetail;

    public static class KeyDetail {

        private String detailMeaning;
        private String keyId;
        private String keyName;
        private String meaning;
        private String radical;
        private String radicalId;
        private String rmStrokeMumber;
        private String strokeNumber;
        private String pinyin;
        private String pinyinId;

        public String getDetailMeaning() {
            return detailMeaning;
        }

        public void setDetailMeaning(String detailMeaning) {
            this.detailMeaning = detailMeaning;
        }

        public String getKeyId() {
            return keyId;
        }

        public void setKeyId(String keyId) {
            this.keyId = keyId;
        }

        public String getKeyName() {
            return keyName;
        }

        public void setKeyName(String keyName) {
            this.keyName = keyName;
        }

        public String getMeaning() {
            return meaning;
        }

        public void setMeaning(String meaning) {
            this.meaning = meaning;
        }

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

        public String getRmStrokeMumber() {
            return rmStrokeMumber;
        }

        public void setRmStrokeMumber(String rmStrokeMumber) {
            this.rmStrokeMumber = rmStrokeMumber;
        }

        public String getStrokeNumber() {
            return strokeNumber;
        }

        public void setStrokeNumber(String strokeNumber) {
            this.strokeNumber = strokeNumber;
        }

        public String getPinyin() {
            return pinyin;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        public String getPinyinId() {
            return pinyinId;
        }

        public void setPinyinId(String pinyinId) {
            this.pinyinId = pinyinId;
        }

        @Override
        public String toString() {
            return "KeyDetail{" +
                    "detailMeaning='" + detailMeaning + '\'' +
                    ", keyId='" + keyId + '\'' +
                    ", keyName='" + keyName + '\'' +
                    ", meaning='" + meaning + '\'' +
                    ", radical='" + radical + '\'' +
                    ", radicalId='" + radicalId + '\'' +
                    ", rmStrokeMumber='" + rmStrokeMumber + '\'' +
                    ", strokeNumber='" + strokeNumber + '\'' +
                    ", pinyin='" + pinyin + '\'' +
                    ", pinyinId='" + pinyinId + '\'' +
                    '}';
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

    public List<KeyDetail> getKeyDetail() {
        return keyDetail;
    }

    public void setKeyDetail(List<KeyDetail> keyDetail) {
        this.keyDetail = keyDetail;
    }

    @Override
    public String toString() {
        return "KeyDetailArray{" +
                "error=" + error +
                ", reason='" + reason + '\'' +
                ", keyDetail=" + keyDetail +
                '}';
    }
}
