package com.fudan.cosmosapp.ui.discover.model.imple;

import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.KeyArray;
import com.fudan.cosmosapp.bean.KeyDetailArray;
import com.fudan.cosmosapp.bean.PinYinArray;
import com.fudan.cosmosapp.bean.RadicalArray;
import com.fudan.cosmosapp.httpClient.Api;
import com.fudan.cosmosapp.httpClient.CourseNetwork;
import com.fudan.cosmosapp.ui.discover.model.SearchKeyDetailModel;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class SearchKeyDetailModelImple implements SearchKeyDetailModel {

    @Override
    public Observable<KeyDetailArray> getKeyDetailByKeyName(String keyName) {

        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());

        return api.getSearchKeyDetail(keyName).compose(CourseNetwork.schedulersTransformer);
    }

    @Override
    public Observable<RadicalArray> getRadicalList() {
        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());

        return api.getRadicalList().compose(CourseNetwork.schedulersTransformer);
    }

    @Override
    public Observable<PinYinArray> getPinYinList() {
        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());

        return api.getPinYinList().compose(CourseNetwork.schedulersTransformer);
    }

    @Override
    public Observable<KeyArray> getKeyIdByRadicalId(String radicalId) {
        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());

        return api.getKeyIdByRadicalId(radicalId).compose(CourseNetwork.schedulersTransformer);
    }

    @Override
    public Observable<KeyArray> getKeyIdByPinYin(String pinyinId) {
        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());

        return api.getKeyIdByPinYin(pinyinId).compose(CourseNetwork.schedulersTransformer);
    }

}
