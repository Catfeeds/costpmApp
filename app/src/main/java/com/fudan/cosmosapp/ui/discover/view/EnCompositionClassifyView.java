package com.fudan.cosmosapp.ui.discover.view;

import com.fudan.cosmosapp.base.BaseView;
import com.fudan.cosmosapp.bean.EnCompositionClassifyBean;
import com.fudan.cosmosapp.bean.EnCompositionTitleArray;
import com.fudan.cosmosapp.ui.discover.presenter.EnCompositionClassifyPresenter;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public interface EnCompositionClassifyView extends BaseView<EnCompositionClassifyPresenter> {

    void showLoading();

    void hideLoading();

    void emptyView(boolean visible);

    void handleError(Throwable throwable);

    void enCompositionClassifyLoaded(EnCompositionClassifyBean enCompositionClassifyBean);

    void enCompositionListLoaded(EnCompositionTitleArray enCompositionTitleArray);

}
