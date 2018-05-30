package com.fudan.cosmosapp.ui.discover.presenter.imple;

import android.content.Context;

import com.fudan.cosmosapp.bean.EnCompositionClassifyBean;
import com.fudan.cosmosapp.bean.EnCompositionTitleArray;
import com.fudan.cosmosapp.ui.discover.model.EnCompositionClassifyModel;
import com.fudan.cosmosapp.ui.discover.model.imple.EnCompositionClassifyModelImple;
import com.fudan.cosmosapp.ui.discover.presenter.EnCompositionClassifyPresenter;
import com.fudan.cosmosapp.ui.discover.view.EnCompositionClassifyView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class EnCompositionClassifyPresenterImple implements EnCompositionClassifyPresenter {

    private Context context;

    private EnCompositionClassifyModel enCompositionClassifyModel;

    private EnCompositionClassifyView enCompositionClassifyView;

    public EnCompositionClassifyPresenterImple(Context context, EnCompositionClassifyView cnCompositionClassifyView){
        this.context = context;
        this.enCompositionClassifyView = cnCompositionClassifyView;
        this.enCompositionClassifyView.setPresenter(this);
        this.enCompositionClassifyModel = new EnCompositionClassifyModelImple();
    }

    @Override
    public void loadEnCompositionClassify() {
        Observable<EnCompositionClassifyBean> observable = enCompositionClassifyModel.getEnCompositionClassify();
        observable.subscribe(new Observer<EnCompositionClassifyBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(EnCompositionClassifyBean enCompositionClassifyBean) {
                if(enCompositionClassifyBean.getError() == 0){
                    enCompositionClassifyView.hideLoading();
                    enCompositionClassifyView.enCompositionClassifyLoaded(enCompositionClassifyBean);
                    enCompositionClassifyView.emptyView(true);
                } else if(enCompositionClassifyBean.getError() == 1){
                    enCompositionClassifyView.emptyView(false);
                    enCompositionClassifyView.hideLoading();
                    enCompositionClassifyView.handleError(new Throwable(enCompositionClassifyBean.getReason()));
                }
            }

            @Override
            public void onError(Throwable e) {
                enCompositionClassifyView.emptyView(false);
                enCompositionClassifyView.hideLoading();
                enCompositionClassifyView.handleError(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void loadEnCompositionListByClassifyId(String classifyId) {
        Observable<EnCompositionTitleArray> observable = enCompositionClassifyModel.getEnCompositionTitleListByClassifyId(classifyId);
        observable.subscribe(new Observer<EnCompositionTitleArray>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(EnCompositionTitleArray enCompositionTitleArray) {
                if(enCompositionTitleArray.getError() == 0){
                    enCompositionClassifyView.hideLoading();
                    enCompositionClassifyView.enCompositionListLoaded(enCompositionTitleArray);
                    enCompositionClassifyView.emptyView(true);
                } else if(enCompositionTitleArray.getError() == 1){
                    enCompositionClassifyView.emptyView(false);
                    enCompositionClassifyView.hideLoading();
                    enCompositionClassifyView.handleError(new Throwable(enCompositionTitleArray.getReason()));

                }
            }

            @Override
            public void onError(Throwable e) {
                enCompositionClassifyView.emptyView(false);
                enCompositionClassifyView.hideLoading();
                enCompositionClassifyView.handleError(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
