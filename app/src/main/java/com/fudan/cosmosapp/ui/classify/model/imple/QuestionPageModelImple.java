package com.fudan.cosmosapp.ui.classify.model.imple;

import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.QuestionDetail;
import com.fudan.cosmosapp.httpClient.Api;
import com.fudan.cosmosapp.httpClient.CourseNetwork;
import com.fudan.cosmosapp.ui.classify.model.QuestionPageModel;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class QuestionPageModelImple implements QuestionPageModel {
    @Override
    public Observable<QuestionDetail> getQuestionDetail(String quesId) {

        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());
        return api.getQuestionDetail(quesId).compose(CourseNetwork.schedulersTransformer);
    }
}
