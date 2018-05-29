package com.fudan.cosmosapp.ui.classify.model.imple;

import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.Grade;
import com.fudan.cosmosapp.bean.Subject;
import com.fudan.cosmosapp.httpClient.Api;
import com.fudan.cosmosapp.httpClient.CourseNetwork;
import com.fudan.cosmosapp.ui.classify.model.ClassifyModel;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class ClassifyModelImple implements ClassifyModel{

    @Override
    public Observable<Grade> loadGrades() {

        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());
        return api.getGrades().compose(CourseNetwork.schedulersTransformer);
    }

    @Override
    public Observable<Subject> loadSubjects(int id) {
        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());
        return api.getSubjects(id+"").compose(CourseNetwork.schedulersTransformer);
    }

}

