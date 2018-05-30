package com.fudan.cosmosapp.ui.discover.view;

import com.fudan.cosmosapp.base.BaseView;
import com.fudan.cosmosapp.bean.EnWordListArray;
import com.fudan.cosmosapp.ui.discover.presenter.EnWordQueryPresenter;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public interface EnWordQueryView extends BaseView<EnWordQueryPresenter>{

    int ENWORDLISTLOADED_FAILED = 1;
    int ENWORDLISTBYWORDLOADED_FAILED = 2;


    void enWordListLoaded(EnWordListArray enWordListArray);

    void enWordListByWordLoaded(EnWordListArray enWordListArray);

    void enWordListLoadedFailed(int who);

    void handleError(Throwable throwable);

    void emptyView(boolean isvisible);

    void hideLoading();

    void showLoading();

}
