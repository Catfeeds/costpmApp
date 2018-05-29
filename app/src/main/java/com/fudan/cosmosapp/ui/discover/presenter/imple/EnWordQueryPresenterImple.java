package com.fudan.cosmosapp.ui.discover.presenter.imple;

import android.content.Context;

import com.fudan.cosmosapp.bean.EnWordListArray;
import com.fudan.cosmosapp.ui.discover.model.EnWordQueryModel;
import com.fudan.cosmosapp.ui.discover.model.imple.EnWordQueryModelImple;
import com.fudan.cosmosapp.ui.discover.presenter.EnWordQueryPresenter;
import com.fudan.cosmosapp.ui.discover.view.EnWordQueryView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class EnWordQueryPresenterImple implements EnWordQueryPresenter {

    private Context context;

    private EnWordQueryModel enWordQueryModel;

    private EnWordQueryView enWordQueryView;

    public EnWordQueryPresenterImple(Context context, EnWordQueryView enWordQueryView){
        this.context = context;
        this.enWordQueryView = enWordQueryView;
        this.enWordQueryView.setPresenter(this);
        this.enWordQueryModel = new EnWordQueryModelImple();
    }

    @Override
    public void loadEnWordList() {
        Observable<EnWordListArray> observable = enWordQueryModel.getEnWordListArray();
        observable.subscribe(new Observer<EnWordListArray>() {
            @Override
            public void onSubscribe(Disposable d) {
                enWordQueryView.showLoading();
            }

            @Override
            public void onNext(EnWordListArray enWordListArray) {
                if(enWordListArray.getError() == 0){
                    enWordQueryView.enWordListLoaded(enWordListArray);
                    enWordQueryView.hideLoading();
                    enWordQueryView.emptyView(true);
                } else if(enWordListArray.getError() == 1){
                    enWordQueryView.emptyView(false);
                    enWordQueryView.handleError(new Throwable(enWordListArray.getReason()));
                    enWordQueryView.hideLoading();
                }
            }

            @Override
            public void onError(Throwable e) {
                enWordQueryView.handleError(e);
                enWordQueryView.hideLoading();
                enWordQueryView.emptyView(false);
                enWordQueryView.enWordListLoadedFailed(EnWordQueryView.ENWORDLISTLOADED_FAILED);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void loadEnWordListByWord(String word) {
        Observable<EnWordListArray> observable = enWordQueryModel.getEnWordListArrayByWord(word);
        observable.subscribe(new Observer<EnWordListArray>() {
            @Override
            public void onSubscribe(Disposable d) {
                enWordQueryView.showLoading();
            }

            @Override
            public void onNext(EnWordListArray enWordListArray) {
                if(enWordListArray.getError() == 0){
                    enWordQueryView.hideLoading();
                    enWordQueryView.enWordListByWordLoaded(enWordListArray);
                    enWordQueryView.emptyView(true);
                }else if(enWordListArray.getError() == 1){
                    enWordQueryView.hideLoading();
                    enWordQueryView.emptyView(false);
                    enWordQueryView.handleError(new Throwable(enWordListArray.getReason()));
                }
            }

            @Override
            public void onError(Throwable e) {
                enWordQueryView.handleError(e);
                enWordQueryView.hideLoading();
                enWordQueryView.emptyView(false);
                enWordQueryView.enWordListLoadedFailed(EnWordQueryView.ENWORDLISTBYWORDLOADED_FAILED);

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
