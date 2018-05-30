package com.fudan.cosmosapp.ui.discover.view;

import com.fudan.cosmosapp.base.BaseView;
import com.fudan.cosmosapp.bean.ChCompositionTitleArray;
import com.fudan.cosmosapp.bean.CnCompositionClassifyBean;
import com.fudan.cosmosapp.ui.discover.presenter.CnCompositionClassifyPresenter;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public interface CnCompositionClassifyView extends BaseView<CnCompositionClassifyPresenter> {

    void showLoading();

    void hideLoading();

    void emptyView(boolean visible);

    void handleError(Throwable throwable);

    void cnCompositionClassifyLoaded(CnCompositionClassifyBean cnCompositionClassifyBean);

    void cnCompositionListLoaded(ChCompositionTitleArray chCompositionTitleArray);

}
