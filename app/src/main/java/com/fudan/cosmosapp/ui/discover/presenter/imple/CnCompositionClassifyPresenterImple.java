package com.fudan.cosmosapp.ui.discover.presenter.imple;

import android.content.Context;
import android.util.Log;

import com.fudan.cosmosapp.bean.ChCompositionTitleArray;
import com.fudan.cosmosapp.bean.CnCompositionClassifyBean;
import com.fudan.cosmosapp.ui.discover.model.CnCompositionClassifyModel;
import com.fudan.cosmosapp.ui.discover.model.imple.CnCompositionClassifyModelImple;
import com.fudan.cosmosapp.ui.discover.presenter.CnCompositionClassifyPresenter;
import com.fudan.cosmosapp.ui.discover.view.CnCompositionClassifyView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class CnCompositionClassifyPresenterImple implements CnCompositionClassifyPresenter {

    private Context context;

    private CnCompositionClassifyModel cnCompositionClassifyModel;

    private CnCompositionClassifyView cnCompositionClassifyView;

    public CnCompositionClassifyPresenterImple(Context context, CnCompositionClassifyView cnCompositionClassifyView){
        this.context = context;
        this.cnCompositionClassifyView = cnCompositionClassifyView;
        this.cnCompositionClassifyView.setPresenter(this);
        this.cnCompositionClassifyModel = new CnCompositionClassifyModelImple();
    }

    @Override
    public void loadChCompositionClassify() {
        Observable<CnCompositionClassifyBean> observable = cnCompositionClassifyModel.getCnCompositionClassify();
        observable.subscribe(new Observer<CnCompositionClassifyBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("开始","加载分类");
            }

            @Override
            public void onNext(CnCompositionClassifyBean cnCompositionClassifyBean) {
                if(cnCompositionClassifyBean.getError() == 0){
                    cnCompositionClassifyView.hideLoading();
                    cnCompositionClassifyView.cnCompositionClassifyLoaded(cnCompositionClassifyBean);
                    Log.e("开始","分类加载成功");
                    cnCompositionClassifyView.emptyView(true);
                } else if(cnCompositionClassifyBean.getError() == 1){
                    cnCompositionClassifyView.emptyView(false);
                    cnCompositionClassifyView.hideLoading();
                    Log.e("开始","分类加载失败");
                    cnCompositionClassifyView.handleError(new Throwable(cnCompositionClassifyBean.getReason()));
                }
            }

            @Override
            public void onError(Throwable e) {
                cnCompositionClassifyView.emptyView(false);
                cnCompositionClassifyView.hideLoading();
                cnCompositionClassifyView.handleError(e);
                Log.e("开始","分类加载异常");

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void loadChCompositionListByClassifyId(String classifyId) {
        Observable<ChCompositionTitleArray> observable = cnCompositionClassifyModel.getChCompositionTitleListByClassifyId(classifyId);
        observable.subscribe(new Observer<ChCompositionTitleArray>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ChCompositionTitleArray chCompositionTitleArray) {
                if(chCompositionTitleArray.getError() == 0){
                    cnCompositionClassifyView.hideLoading();
                    cnCompositionClassifyView.cnCompositionListLoaded(chCompositionTitleArray);
                    cnCompositionClassifyView.emptyView(true);
                } else if(chCompositionTitleArray.getError() == 1){
                    cnCompositionClassifyView.emptyView(false);
                    cnCompositionClassifyView.hideLoading();
                    cnCompositionClassifyView.handleError(new Throwable(chCompositionTitleArray.getReason()));

                }
            }

            @Override
            public void onError(Throwable e) {
                cnCompositionClassifyView.emptyView(false);
                cnCompositionClassifyView.hideLoading();
                cnCompositionClassifyView.handleError(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
