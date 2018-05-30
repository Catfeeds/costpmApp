package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Ang on 2017/8/9.
 */

public class KnowledgePoint {

    /**
     * reason : success
     * knowledgePointList : [{"knowledgePointId":"157","knowledgePoint":"二 小数乘法"},{"knowledgePointId":"158","knowledgePoint":"三 小数除法"},{"knowledgePointId":"159","knowledgePoint":"四 可能性"},{"knowledgePointId":"160","knowledgePoint":"五 四则混合运算（二）"},{"knowledgePointId":"161","knowledgePoint":"六 多边形的面积"},{"knowledgePointId":"162","knowledgePoint":"一 方向与路线"},{"knowledgePointId":"163","knowledgePoint":"七 土地的面积"},{"knowledgePointId":"164","knowledgePoint":"八 方程"}]
     * error : 0
     */

    private String reason;
    private String error;
    private List<KnowledgePointListBean> knowledgePointList;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<KnowledgePointListBean> getKnowledgePointList() {
        return knowledgePointList;
    }

    public void setKnowledgePointList(List<KnowledgePointListBean> knowledgePointList) {
        this.knowledgePointList = knowledgePointList;
    }

    public static class KnowledgePointListBean {
        /**
         * knowledgePointId : 157
         * knowledgePoint : 二 小数乘法
         */

        private String knowledgePointId;
        private String knowledgePoint;

        public String getKnowledgePointId() {
            return knowledgePointId;
        }

        public void setKnowledgePointId(String knowledgePointId) {
            this.knowledgePointId = knowledgePointId;
        }

        public String getKnowledgePoint() {
            return knowledgePoint;
        }

        public void setKnowledgePoint(String knowledgePoint) {
            this.knowledgePoint = knowledgePoint;
        }
    }
}
