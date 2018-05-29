package com.fudan.cosmosapp.ui.discover.presenter.imple;

import android.content.Context;

import com.fudan.cosmosapp.bean.KeyArray;
import com.fudan.cosmosapp.bean.KeyDetailArray;
import com.fudan.cosmosapp.bean.PinYinArray;
import com.fudan.cosmosapp.bean.RadicalArray;
import com.fudan.cosmosapp.ui.discover.model.SearchKeyDetailModel;
import com.fudan.cosmosapp.ui.discover.model.imple.SearchKeyDetailModelImple;
import com.fudan.cosmosapp.ui.discover.presenter.SearchKeyDetailPresenter;
import com.fudan.cosmosapp.ui.discover.view.SearchKeyDetailView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class SearchKeyDetailPresenterImple implements SearchKeyDetailPresenter {

    private Context context;

    private SearchKeyDetailModel searchKeyDetailModel;

    private SearchKeyDetailView searchKeyDetailView;

    public SearchKeyDetailPresenterImple(Context context,SearchKeyDetailView searchKeyDetailView){
        this.context = context;
        this.searchKeyDetailView = searchKeyDetailView;
        this.searchKeyDetailView.setPresenter(this);
        this.searchKeyDetailModel = new SearchKeyDetailModelImple();
    }

    @Override
    public void getSearchKeyDetail(final String keyName) {

        Observable<KeyDetailArray> observable = searchKeyDetailModel.getKeyDetailByKeyName(keyName);
        observable.subscribe(new Observer<KeyDetailArray>() {
            @Override
            public void onSubscribe(Disposable d) {
                searchKeyDetailView.showLoading();
            }

            @Override
            public void onNext(KeyDetailArray keyDetailArray) {
                if(keyDetailArray.getError() == 1){

                    searchKeyDetailView.hideLoading();
                    searchKeyDetailView.emptyView(false);
                    searchKeyDetailView.handleError(new Throwable(keyDetailArray.getReason()));
                    searchKeyDetailView.failedLoadingType(SearchKeyDetailPresenter.LOADING_DETAIL,keyName);

                } else if(keyDetailArray.getError() == 0){
                    searchKeyDetailView.keyDetailLoaded(keyDetailArray);
                    searchKeyDetailView.hideLoading();
                    searchKeyDetailView.emptyView(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                searchKeyDetailView.hideLoading();
                searchKeyDetailView.handleError(e);
                searchKeyDetailView.emptyView(false);
                searchKeyDetailView.failedLoadingType(SearchKeyDetailPresenter.LOADING_DETAIL,keyName);
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void getRadicalList() {
        Observable<RadicalArray> observable = searchKeyDetailModel.getRadicalList();
        observable.subscribe(new Observer<RadicalArray>() {
            @Override
            public void onSubscribe(Disposable d) {
                searchKeyDetailView.showLoading();
            }

            @Override
            public void onNext(RadicalArray radicalArray) {
                if(radicalArray.getError() == 1){

                    searchKeyDetailView.hideLoading();
                    searchKeyDetailView.emptyView(false);
                    searchKeyDetailView.handleError(new Throwable(radicalArray.getReason()));
                    searchKeyDetailView.failedLoadingType(SearchKeyDetailPresenter.LOADING_RADICAL,null);
                } else if(radicalArray.getError() == 0){

                    searchKeyDetailView.radicalListLoaded(radicalArray);
                    searchKeyDetailView.hideLoading();
                    searchKeyDetailView.emptyView(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                searchKeyDetailView.emptyView(false);
                searchKeyDetailView.handleError(e);
                searchKeyDetailView.hideLoading();
                searchKeyDetailView.failedLoadingType(SearchKeyDetailPresenter.LOADING_RADICAL,null);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getPinYinList() {
        Observable<PinYinArray> observable = searchKeyDetailModel.getPinYinList();
        observable.subscribe(new Observer<PinYinArray>() {
            @Override
            public void onSubscribe(Disposable d) {
                searchKeyDetailView.showLoading();
            }

            @Override
            public void onNext(PinYinArray pinYinArray) {
                if(pinYinArray.getError() == 1){

                    searchKeyDetailView.hideLoading();
                    searchKeyDetailView.emptyView(false);
                    searchKeyDetailView.handleError(new Throwable(pinYinArray.getReason()));
                    searchKeyDetailView.failedLoadingType(SearchKeyDetailPresenter.LOADING_PINYIN,null);

                } else if(pinYinArray.getError() == 0){
                    searchKeyDetailView.pinYinListLoaded(pinYinArray);
                    searchKeyDetailView.hideLoading();
                    searchKeyDetailView.emptyView(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                searchKeyDetailView.emptyView(false);
                searchKeyDetailView.handleError(e);
                searchKeyDetailView.hideLoading();
                searchKeyDetailView.failedLoadingType(SearchKeyDetailPresenter.LOADING_PINYIN,null);

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getKeyIdByRadicalId(final String radicalId) {
        Observable<KeyArray> observable = searchKeyDetailModel.getKeyIdByRadicalId(radicalId+"");
        observable.subscribe(new Observer<KeyArray>() {
            @Override
            public void onSubscribe(Disposable d) {
                searchKeyDetailView.showLoading();
            }

            @Override
            public void onNext(KeyArray keyArray) {
                if(keyArray.getError() == 1){

                    searchKeyDetailView.handleError(new Throwable(keyArray.getReason()));
                    searchKeyDetailView.emptyView(false);
                    searchKeyDetailView.hideLoading();
                    searchKeyDetailView.failedLoadingType(SearchKeyDetailPresenter.LOADING_KEYID_BY_RADICALID,radicalId);


                }else if(keyArray.getError() == 0){
                    searchKeyDetailView.hideLoading();
                    searchKeyDetailView.emptyView(true);
                    searchKeyDetailView.keyIdLoaded(keyArray);

                }
            }

            @Override
            public void onError(Throwable e) {
                searchKeyDetailView.emptyView(false);
                searchKeyDetailView.handleError(e);
                searchKeyDetailView.hideLoading();
                searchKeyDetailView.failedLoadingType(SearchKeyDetailPresenter.LOADING_KEYID_BY_RADICALID,radicalId);

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getKeyIdByPinYinId(final String pinyinId) {
        Observable<KeyArray> observable = searchKeyDetailModel.getKeyIdByPinYin(pinyinId+"");
        observable.subscribe(new Observer<KeyArray>() {
            @Override
            public void onSubscribe(Disposable d) {
                searchKeyDetailView.showLoading();
            }

            @Override
            public void onNext(KeyArray keyArray) {
                if(keyArray.getError() == 1){
                    searchKeyDetailView.handleError(new Throwable(keyArray.getReason()));
                    searchKeyDetailView.emptyView(false);
                    searchKeyDetailView.hideLoading();
                    searchKeyDetailView.failedLoadingType(SearchKeyDetailPresenter.LOADING_KEYID_BY_PINYINID,pinyinId);
                } else if(keyArray.getError() == 0){

                    searchKeyDetailView.hideLoading();
                    searchKeyDetailView.keyIdLoaded(keyArray);
                    searchKeyDetailView.emptyView(true);

                }
            }

            @Override
            public void onError(Throwable e) {
                searchKeyDetailView.emptyView(false);
                searchKeyDetailView.handleError(e);
                searchKeyDetailView.hideLoading();
                searchKeyDetailView.failedLoadingType(SearchKeyDetailPresenter.LOADING_KEYID_BY_PINYINID,pinyinId);

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
