package com.fudan.cosmosapp.ui.home.presenter.Imple;

import android.content.Context;

import com.fudan.cosmosapp.bean.NewsBean;
import com.fudan.cosmosapp.httpClient.NetworkUtil;
import com.fudan.cosmosapp.ui.home.model.HomeModel;
import com.fudan.cosmosapp.ui.home.model.Imple.HomeModelImple;
import com.fudan.cosmosapp.ui.home.presenter.HomePresenter;
import com.fudan.cosmosapp.ui.home.view.HomeView;
import com.fudan.cosmosapp.utils.ToastUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class HomePresenterImple implements HomePresenter {

    private Context mContext;

    private HomeView homeView;

    private HomeModel homeModel;

    public HomePresenterImple(Context context,HomeView view){
        this.mContext = context;
        this.homeView = view;
        this.homeView.setPresenter(this);

        this.homeModel = new HomeModelImple();
    }

    @Override
    public void loadList(int pageIndex) {

        if(!NetworkUtil.isNetworkAvailable(mContext)){
            ToastUtils.showToast(mContext,"网络无连接,请检查网络");
        }

        Observable<NewsBean> observable = homeModel.getListData(pageIndex);
        observable.subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        if(newsBean == null){
                            homeView.emptyView(false);
                            homeView.hideLoading();
                        }else {
                            homeView.listLoaded(newsBean);
                            homeView.hideLoading();
                            homeView.emptyView(true);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        homeView.handleError(e);
                        homeView.emptyView(false);
                        homeView.hideLoading();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void listRefresh() {

        if(!NetworkUtil.isNetworkAvailable(mContext)){
            ToastUtils.showToast(mContext,"网络无连接,请检查网络");
        }

        Observable<NewsBean> observable = homeModel.getListData(0);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        if(newsBean == null){
                            homeView.emptyView(false);
                            homeView.hideLoading();
                        }else {
                            homeView.listRefreshed(newsBean);
                            homeView.hideLoading();
                            homeView.emptyView(true);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        homeView.handleError(e);
                        homeView.emptyView(false);
                        homeView.hideLoading();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
