package com.fudan.cosmosapp.ui.discover.model.imple;

import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.EnWordListArray;
import com.fudan.cosmosapp.httpClient.Api;
import com.fudan.cosmosapp.httpClient.CourseNetwork;
import com.fudan.cosmosapp.ui.discover.model.EnWordQueryModel;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class EnWordQueryModelImple implements EnWordQueryModel {
    @Override
    public Observable<EnWordListArray> getEnWordListArray() {

        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());
        return api.getEnWordListArray().compose(CourseNetwork.schedulersTransformer);
    }

    @Override
    public Observable<EnWordListArray> getEnWordListArrayByWord(String word) {

        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());
        return api.getEnWordListArrayByWord(word).compose(CourseNetwork.schedulersTransformer);
    }
}
