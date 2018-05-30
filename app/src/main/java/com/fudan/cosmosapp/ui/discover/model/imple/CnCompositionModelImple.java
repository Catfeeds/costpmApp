package com.fudan.cosmosapp.ui.discover.model.imple;

import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.ChCompositionTitleArray;
import com.fudan.cosmosapp.httpClient.Api;
import com.fudan.cosmosapp.httpClient.CourseNetwork;
import com.fudan.cosmosapp.ui.discover.model.CnCompositionModel;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class CnCompositionModelImple implements CnCompositionModel {
    @Override
    public Observable<ChCompositionTitleArray> getEnCompositionTitleListByTitle(String title) {

        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());

        return api.getChCompositionTitleListByTitle(title).compose(CourseNetwork.schedulersTransformer);
    }
}
