package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Ang on 2017/8/14.
 */

public class SearchQuestion {

    private List<QuestionListBean> questionList;

    public List<QuestionListBean> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionListBean> questionList) {
        this.questionList = questionList;
    }

    public static class QuestionListBean {
        /**
         * ansContextHtml :
         C,D,E
         <hr>


         * questionId : 6977
         * questionContext :
         <span class=""></span>下列图形中，沿虚线折叠后能围成正方体的有<br><div align="right">[ ]</div>
         <div class="option-item">
         <div>A、<img style="VERTICAL-ALIGN: middle" src="http://tikucommon-zs.oss-cn-beijing.aliyuncs.com/tiku/source/upimg/pic1/upload/papers/x02/20101109/201011091119285001083.gif"></div>
         </div>

         <div class="option-item">
         <div>B、<img style="VERTICAL-ALIGN: middle" src="http://tikucommon-zs.oss-cn-beijing.aliyuncs.com/tiku/source/upimg/pic1/upload/papers/x02/20101109/201011091119429841156.gif"></div>
         </div>

         <div class="option-item">
         <div>C、<img style="VERTICAL-ALIGN: middle" src="http://tikucommon-zs.oss-cn-beijing.aliyuncs.com/tiku/source/upimg/pic1/upload/papers/x02/20101109/201011091119514841141.gif"></div>
         </div>

         <div class="option-item">
         <div>D、<img style="VERTICAL-ALIGN: middle" src="http://tikucommon-zs.oss-cn-beijing.aliyuncs.com/tiku/source/upimg/pic1/upload/papers/x02/20101109/201011091120005461137.gif">E．<img style="VERTICAL-ALIGN: middle" src="http://tikucommon-zs.oss-cn-beijing.aliyuncs.com/tiku/source/upimg/pic1/upload/papers/x02/20101109/201011091120091091176.gif"></div>
         </div>

         */

        private String ansContextHtml;
        private String questionId;
        private String questionContext;

        public String getAnsContextHtml() {
            return ansContextHtml;
        }

        public void setAnsContextHtml(String ansContextHtml) {
            this.ansContextHtml = ansContextHtml;
        }

        public String getQuestionId() {
            return questionId;
        }

        public void setQuestionId(String questionId) {
            this.questionId = questionId;
        }

        public String getQuestionContext() {
            return questionContext;
        }

        public void setQuestionContext(String questionContext) {
            this.questionContext = questionContext;
        }
    }
}
