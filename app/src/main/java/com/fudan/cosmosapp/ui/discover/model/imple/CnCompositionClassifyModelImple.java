package com.fudan.cosmosapp.ui.discover.model.imple;

import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.ChCompositionTitleArray;
import com.fudan.cosmosapp.bean.CnCompositionClassifyBean;
import com.fudan.cosmosapp.httpClient.Api;
import com.fudan.cosmosapp.httpClient.CourseNetwork;
import com.fudan.cosmosapp.ui.discover.model.CnCompositionClassifyModel;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class CnCompositionClassifyModelImple implements CnCompositionClassifyModel {

    @Override
    public Observable<CnCompositionClassifyBean> getCnCompositionClassify() {

        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());
        return api.getChCompositionClassify().compose(CourseNetwork.schedulersTransformer);
    }

    @Override
    public Observable<ChCompositionTitleArray> getChCompositionTitleListByClassifyId(String classificationId) {

        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());

        return api.getChCompositionTitleListByClassifyId(classificationId).compose(CourseNetwork.schedulersTransformer);
    }
}
