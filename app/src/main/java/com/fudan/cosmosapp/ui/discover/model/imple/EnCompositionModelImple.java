package com.fudan.cosmosapp.ui.discover.model.imple;

import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.EnCompositionTitleArray;
import com.fudan.cosmosapp.httpClient.Api;
import com.fudan.cosmosapp.httpClient.CourseNetwork;
import com.fudan.cosmosapp.ui.discover.model.EnCompositionModel;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class EnCompositionModelImple implements EnCompositionModel {
    @Override
    public Observable<EnCompositionTitleArray> getEnCompositionTitleListByTitle(String title) {

        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());

        return api.getEnCompositionTitleListByTitle(title).compose(CourseNetwork.schedulersTransformer);
    }
}
