package com.fudan.cosmosapp.bean;

/**
 * Created by Ang on 2017/8/11.
 */

public class QuestionDetail {

    /**
     * quesContextHtml :
     &lt;span class&gt;&lt;/span&gt;把45%化成分数是______，化成小数是______．

     * reason : success
     * ansContextHtml :
     45%=&lt;span  mathtag="math" &gt;&lt;table cellspacing="-1" cellpadding="-1" style="width:auto;display:inline-table;*display:inline;vertical-align:middle;margin:0;padding:0;text-align:center;line-height:normal;" style="display:inline-table;vertical-align:middle"&gt;&lt;tr&gt;&lt;td style="border-bottom:1px solid black"&gt;45&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;100&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/span&gt;=&lt;span  mathtag="math" &gt;&lt;table cellspacing="-1" cellpadding="-1" style="width:auto;display:inline-table;*display:inline;vertical-align:middle;margin:0;padding:0;text-align:center;line-height:normal;" style="display:inline-table;vertical-align:middle"&gt;&lt;tr&gt;&lt;td style="border-bottom:1px solid black"&gt;45÷5&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;100÷5&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/span&gt;=&lt;span  mathtag="math" &gt;&lt;table cellspacing="-1" cellpadding="-1" style="width:auto;display:inline-table;*display:inline;vertical-align:middle;margin:0;padding:0;text-align:center;line-height:normal;" style="display:inline-table;vertical-align:middle"&gt;&lt;tr&gt;&lt;td style="border-bottom:1px solid black"&gt;9&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;20&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/span&gt;=0.45，&lt;br&gt;故答案为：&lt;span  mathtag="math" &gt;&lt;table cellspacing="-1" cellpadding="-1" style="width:auto;display:inline-table;*display:inline;vertical-align:middle;margin:0;padding:0;text-align:center;line-height:normal;" style="display:inline-table;vertical-align:middle"&gt;&lt;tr&gt;&lt;td style="border-bottom:1px solid black"&gt;9&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;20&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/span&gt;、0.45．
     <hr>


     * error : 0
     * quesId : 17820
     */

    private String quesContextHtml;
    private String reason;
    private String ansContextHtml;
    private String error;
    private String quesId;

    public String getQuesContextHtml() {
        return quesContextHtml;
    }

    public void setQuesContextHtml(String quesContextHtml) {
        this.quesContextHtml = quesContextHtml;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAnsContextHtml() {
        return ansContextHtml;
    }

    public void setAnsContextHtml(String ansContextHtml) {
        this.ansContextHtml = ansContextHtml;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getQuesId() {
        return quesId;
    }

    public void setQuesId(String quesId) {
        this.quesId = quesId;
    }
}
