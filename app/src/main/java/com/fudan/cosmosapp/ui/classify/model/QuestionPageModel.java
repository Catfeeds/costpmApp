package com.fudan.cosmosapp.ui.classify.model;

import com.fudan.cosmosapp.bean.QuestionDetail;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public interface QuestionPageModel {

    Observable<QuestionDetail> getQuestionDetail(String quesId);


}
