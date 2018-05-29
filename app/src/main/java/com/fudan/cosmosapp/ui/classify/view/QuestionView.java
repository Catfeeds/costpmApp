package com.fudan.cosmosapp.ui.classify.view;

import com.fudan.cosmosapp.base.BaseView;
import com.fudan.cosmosapp.bean.Question;
import com.fudan.cosmosapp.bean.SearchQuestion;
import com.fudan.cosmosapp.ui.classify.presenter.QuestionPresenter;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public interface QuestionView extends BaseView<QuestionPresenter> {

    void questionLoaded(Question question);

    void searchQuestion(SearchQuestion searchQuestion);

    void handleError(Throwable throwable);
}
