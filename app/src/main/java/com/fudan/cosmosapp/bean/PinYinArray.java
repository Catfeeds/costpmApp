package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class PinYinArray {

    /**
     {
     "error": "0",
     "pinyinList": [
     {
     "pinyin": "1",
     "pinyinId": "chao"
     },
     {
     "pinyin": "2",
     "pinyinId": "zhao"
     }
     ],
     "reason": "success"
     }
     */

    private int error;
    private String reason;
    private List<PinyinList> pinyinList;

    public static class PinyinList {
        private String pinyin;
        private String pinyinId;

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

    public List<PinyinList> getPinyinList() {
        return pinyinList;
    }

    public void setPinyinList(List<PinyinList> pinyinList) {
        this.pinyinList = pinyinList;
    }
}
