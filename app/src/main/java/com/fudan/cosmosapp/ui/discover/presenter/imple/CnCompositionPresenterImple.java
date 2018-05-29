package com.fudan.cosmosapp.ui.discover.presenter.imple;

import android.content.Context;

import com.fudan.cosmosapp.bean.ChCompositionTitleArray;
import com.fudan.cosmosapp.httpClient.NetworkUtil;
import com.fudan.cosmosapp.ui.discover.model.CnCompositionModel;
import com.fudan.cosmosapp.ui.discover.model.imple.CnCompositionModelImple;
import com.fudan.cosmosapp.ui.discover.presenter.CnCompositionPresenter;
import com.fudan.cosmosapp.ui.discover.view.CnCompositionView;
import com.fudan.cosmosapp.utils.ToastUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class CnCompositionPresenterImple implements CnCompositionPresenter {

    private Context context;

    private CnCompositionModel fb_compositionModel;

    private CnCompositionView fb_compositionView;

    public CnCompositionPresenterImple(Context context, CnCompositionView fb_compositionView){
        this.context = context;
        this.fb_compositionView = fb_compositionView;
        this.fb_compositionView.setPresenter(this);
        this.fb_compositionModel = new CnCompositionModelImple();
    }
    @Override
    public void loadChCompositionTitleListByTitle(String title) {

        if(!NetworkUtil.isNetworkAvailable(context)){
            ToastUtils.showToast(context,"网络无连接,请检查网络");
        }

        Observable<ChCompositionTitleArray> observable = fb_compositionModel.getEnCompositionTitleListByTitle(title);
        observable.subscribe(new Observer<ChCompositionTitleArray>() {
            @Override
            public void onSubscribe(Disposable d) {
                fb_compositionView.showLoading();
            }

            @Override
            public void onNext(ChCompositionTitleArray chCompositionTitleArray) {
                if(chCompositionTitleArray.getError() == 1){
                    fb_compositionView.hideLoading();
                    fb_compositionView.emptyView(false);
                }else if(chCompositionTitleArray.getError() == 0){
                    fb_compositionView.chCompositionTitleListLoaded(chCompositionTitleArray);
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
