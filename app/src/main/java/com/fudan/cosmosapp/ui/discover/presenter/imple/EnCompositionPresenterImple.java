package com.fudan.cosmosapp.ui.discover.presenter.imple;

import android.content.Context;

import com.fudan.cosmosapp.bean.EnCompositionTitleArray;
import com.fudan.cosmosapp.httpClient.NetworkUtil;
import com.fudan.cosmosapp.ui.discover.model.EnCompositionModel;
import com.fudan.cosmosapp.ui.discover.model.imple.EnCompositionModelImple;
import com.fudan.cosmosapp.ui.discover.presenter.EnCompositionPresenter;
import com.fudan.cosmosapp.ui.discover.view.EnCompositionView;
import com.fudan.cosmosapp.utils.ToastUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class EnCompositionPresenterImple implements EnCompositionPresenter {

    private Context context;

    private EnCompositionModel fb_compositionModel;

    private EnCompositionView fb_compositionView;

    public EnCompositionPresenterImple(Context context, EnCompositionView fb_compositionView){
        this.context = context;
        this.fb_compositionView = fb_compositionView;
        this.fb_compositionView.setPresenter(this);
        this.fb_compositionModel = new EnCompositionModelImple();
    }
    @Override
    public void loadEnCompositionTitleListByTitle(String title) {

        if(NetworkUtil.isNetworkAvailable(context)){
            ToastUtils.showToast(context,"网络无连接,请检查网络");
        }

        Observable<EnCompositionTitleArray> observable = fb_compositionModel.getEnCompositionTitleListByTitle(title);
        observable.subscribe(new Observer<EnCompositionTitleArray>() {
            @Override
            public void onSubscribe(Disposable d) {
                fb_compositionView.showLoading();
            }

            @Override
            public void onNext(EnCompositionTitleArray enCompositionTitleArray) {
                if(enCompositionTitleArray.getError() == 1){
                    fb_compositionView.hideLoading();
                    fb_compositionView.emptyView(false);
                }else if(enCompositionTitleArray.getError() == 0){
                    fb_compositionView.enCompositionTitleListLoaded(enCompositionTitleArray);
                    fb_compositionView.hideLoading();
                    fb_compositionView.emptyView(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                fb_compositionView.hideLoading();
                fb_compositionView.emptyView(false);
                fb_compositionView.handleError(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
