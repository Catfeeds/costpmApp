package com.fudan.cosmosapp.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinxiaofei on 2017/8/9.
 */

public class questionListByESJson {

    @SerializedName("questionList")
    private List<QuestionListBean> questionList;

    public static questionListByESJson objectFromData(String str) {

        return new Gson().fromJson(str, questionListByESJson.class);
    }

    public static questionListByESJson objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), questionListByESJson.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<questionListByESJson> arrayquestionListByESJsonFromData(String str) {

        Type listType = new TypeToken<ArrayList<questionListByESJson>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<questionListByESJson> arrayquestionListByESJsonFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<questionListByESJson>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public List<QuestionListBean> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionListBean> questionList) {
        this.questionList = questionList;
    }

    public static class QuestionListBean {
        /**
         * ansContextHtml : A
         * questionId : 39
         * questionContext : 妈妈给小明买了37个本，现在只剩下13个，小明用了
         */

        @SerializedName("ansContextHtml")
        private String ansContextHtml;
        @SerializedName("questionId")
        private String questionId;
        @SerializedName("questionContext")
        private String questionContext;

        public static QuestionListBean objectFromData(String str) {

            return new Gson().fromJson(str, QuestionListBean.class);
        }

        public static QuestionListBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), QuestionListBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<QuestionListBean> arrayQuestionListBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<QuestionListBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<QuestionListBean> arrayQuestionListBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<QuestionListBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

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
