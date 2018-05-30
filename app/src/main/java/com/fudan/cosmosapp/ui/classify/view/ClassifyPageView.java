package com.fudan.cosmosapp.ui.classify.view;

import com.fudan.cosmosapp.base.BaseView;
import com.fudan.cosmosapp.bean.BookVersion;
import com.fudan.cosmosapp.ui.classify.presenter.ClassifyPagePresenter;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public interface ClassifyPageView extends BaseView<ClassifyPagePresenter>{

    void VersionLoaded(BookVersion bookVersion);

    void handleError(Throwable throwable);

}
