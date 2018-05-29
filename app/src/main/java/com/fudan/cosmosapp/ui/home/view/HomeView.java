package com.fudan.cosmosapp.ui.home.view;

import com.fudan.cosmosapp.base.BaseView;
import com.fudan.cosmosapp.bean.NewsBean;
import com.fudan.cosmosapp.ui.home.presenter.HomePresenter;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public interface HomeView extends BaseView<HomePresenter>{

    void emptyView(boolean visible);

    void handleError(Throwable throwable);

    void showLoading();

    void hideLoading();

    void listLoaded(NewsBean newsBean);

    void listRefreshed(NewsBean newsBean);

}
