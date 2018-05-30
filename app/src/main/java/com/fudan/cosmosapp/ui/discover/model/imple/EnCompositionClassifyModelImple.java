package com.fudan.cosmosapp.ui.discover.model.imple;

import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.EnCompositionClassifyBean;
import com.fudan.cosmosapp.bean.EnCompositionTitleArray;
import com.fudan.cosmosapp.httpClient.Api;
import com.fudan.cosmosapp.httpClient.CourseNetwork;
import com.fudan.cosmosapp.ui.discover.model.EnCompositionClassifyModel;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class EnCompositionClassifyModelImple implements EnCompositionClassifyModel {

    @Override
    public Observable<EnCompositionClassifyBean> getEnCompositionClassify() {

        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());
        return api.getEnCompositionClassify().compose(CourseNetwork.schedulersTransformer);
    }

    @Override
    public Observable<EnCompositionTitleArray> getEnCompositionTitleListByClassifyId(String classificationId) {

        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());

        return api.getEnCompositionTitleListByClassifyId(classificationId).compose(CourseNetwork.schedulersTransformer);
    }
}
