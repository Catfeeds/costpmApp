package com.fudan.cosmosapp.ui.classify.model;

import com.fudan.cosmosapp.bean.Question;
import com.fudan.cosmosapp.bean.SearchQuestion;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public interface QuestionModel {

    Observable<Question> getQuestions(String knowledgePointId);

    Observable<SearchQuestion> getSearchQuestion(String searchKey);

}
