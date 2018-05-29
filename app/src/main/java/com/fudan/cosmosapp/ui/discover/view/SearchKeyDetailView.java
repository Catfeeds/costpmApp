package com.fudan.cosmosapp.ui.discover.view;

import com.fudan.cosmosapp.base.BaseView;
import com.fudan.cosmosapp.bean.KeyArray;
import com.fudan.cosmosapp.bean.KeyDetailArray;
import com.fudan.cosmosapp.bean.PinYinArray;
import com.fudan.cosmosapp.bean.RadicalArray;
import com.fudan.cosmosapp.ui.discover.presenter.SearchKeyDetailPresenter;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public interface SearchKeyDetailView extends BaseView<SearchKeyDetailPresenter>{

    void handleError(Throwable throwable);

    void hideLoading();

    void showLoading();

    void emptyView(boolean visible);

    void keyDetailLoaded(KeyDetailArray keyDetailArray);

    void pinYinListLoaded(PinYinArray pinYinArray);

    void radicalListLoaded(RadicalArray radicalArray);

    void keyIdLoaded(KeyArray keyArray);

    void failedLoadingType(int type,String params);

}
