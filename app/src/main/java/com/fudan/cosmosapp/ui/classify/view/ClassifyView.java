package com.fudan.cosmosapp.ui.classify.view;

import com.fudan.cosmosapp.base.BaseView;
import com.fudan.cosmosapp.bean.Grade;
import com.fudan.cosmosapp.bean.Subject;
import com.fudan.cosmosapp.ui.classify.presenter.ClassifyPresenter;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public interface ClassifyView extends BaseView<ClassifyPresenter> {

    public static int FAILED_LOADING_GRADE = 1;
    public static int FAILED_LOADING_SUBJECT = 2;

    void emptyView(boolean visible);

    void handleError(Throwable throwable);

    void gradeLoaded(Grade grade);

    void subjectLoad(Subject subject);

    void hideProgress();

    void showProgress();

    void failedLoadingType(int type,int params);

}
