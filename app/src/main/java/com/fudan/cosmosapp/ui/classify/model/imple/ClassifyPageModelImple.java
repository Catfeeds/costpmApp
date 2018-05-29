package com.fudan.cosmosapp.ui.classify.model.imple;

import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.BookVersion;
import com.fudan.cosmosapp.httpClient.Api;
import com.fudan.cosmosapp.httpClient.CourseNetwork;
import com.fudan.cosmosapp.ui.classify.model.ClassifyPageModel;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class ClassifyPageModelImple implements ClassifyPageModel {
    @Override
    public Observable<BookVersion> getVersions(String subjectId, String gradeId) {

        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());

        return api.getVersions(subjectId,gradeId).compose(CourseNetwork.schedulersTransformer);
    }
}
