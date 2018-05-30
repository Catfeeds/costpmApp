package com.fudan.cosmosapp.ui.classify.model.imple;

import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.Question;
import com.fudan.cosmosapp.bean.SearchQuestion;
import com.fudan.cosmosapp.httpClient.Api;
import com.fudan.cosmosapp.httpClient.CourseNetwork;
import com.fudan.cosmosapp.ui.classify.model.QuestionModel;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class QuestionModelImple implements QuestionModel {


    @Override
    public Observable<Question> getQuestions(String knowledgePointId) {

        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());

        return api.getQuestions(knowledgePointId).compose(CourseNetwork.schedulersTransformer);
    }

    @Override
    public Observable<SearchQuestion> getSearchQuestion(String searchKey) {

        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());

        return api.getSearchQuestion(searchKey).compose(CourseNetwork.schedulersTransformer);
    }
}
