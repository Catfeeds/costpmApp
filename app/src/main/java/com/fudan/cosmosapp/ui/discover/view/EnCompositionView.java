package com.fudan.cosmosapp.ui.discover.view;

import com.fudan.cosmosapp.base.BaseView;
import com.fudan.cosmosapp.bean.EnCompositionTitleArray;
import com.fudan.cosmosapp.ui.discover.presenter.EnCompositionPresenter;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public interface EnCompositionView extends BaseView<EnCompositionPresenter>{

    void handleError(Throwable throwable);

    void hideLoading();

    void showLoading();

    void emptyView(boolean visible);

    void enCompositionTitleListLoaded(EnCompositionTitleArray enCompositionTitleArray);

}
