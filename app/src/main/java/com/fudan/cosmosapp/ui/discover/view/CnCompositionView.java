package com.fudan.cosmosapp.ui.discover.view;

import com.fudan.cosmosapp.base.BaseView;
import com.fudan.cosmosapp.bean.ChCompositionTitleArray;
import com.fudan.cosmosapp.ui.discover.presenter.CnCompositionPresenter;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public interface CnCompositionView extends BaseView<CnCompositionPresenter>{

    void handleError(Throwable throwable);

    void hideLoading();

    void showLoading();

    void emptyView(boolean visible);

    void chCompositionTitleListLoaded(ChCompositionTitleArray chCompositionTitleArray);

}
