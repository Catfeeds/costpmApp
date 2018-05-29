package com.fudan.cosmosapp.camare.view;

import com.fudan.cosmosapp.base.BaseView;
import com.fudan.cosmosapp.camare.presenter.CropperedPresenter;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public interface CropperedView extends BaseView<CropperedPresenter>{

    void answeredByUploadImg();

    void hideLoading();

    void showLoading();

    void emptyView(boolean isVisible);

    void handleError(Throwable throwable);

}
