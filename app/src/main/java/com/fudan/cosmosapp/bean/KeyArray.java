package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class KeyArray {

    /**
     {
     "error": "0",
     "keyList": [
     {
     "key": "c",
     "keyId": "2"
     "keyName"
     }
     ],
     "reason": "success"
     }
     */

    private int error;
    private String reason;
    private List<KeyList> keyList;

    public static class KeyList {

        private String keyName;
        private String keyId;
        private String key;

        public String getKeyName() {
            return keyName;
        }

        public void setKeyName(String key) {
            this.keyName = key;
        }

        public String getKeyId() {
            return keyId;
        }

        public void setKeyId(String keyId) {
            this.keyId = keyId;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
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

    public List<KeyList> getKeyList() {
        return keyList;
    }

    public void setKeyList(List<KeyList> keyList) {
        this.keyList = keyList;
    }
}
