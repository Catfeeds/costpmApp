package com.fudan.cosmosapp.ui.classify.view;

import com.fudan.cosmosapp.base.BaseView;
import com.fudan.cosmosapp.bean.QuestionDetail;
import com.fudan.cosmosapp.ui.classify.presenter.QuestionPagePresenter;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public interface QuestionPageView extends BaseView<QuestionPagePresenter>{

    void questionDetailLoaded(QuestionDetail questionDetail);

    void handleError(Throwable throwable);

}
