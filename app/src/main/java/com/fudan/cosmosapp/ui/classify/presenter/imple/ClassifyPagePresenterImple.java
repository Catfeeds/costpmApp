package com.fudan.cosmosapp.ui.classify.presenter.imple;

import android.content.Context;

import com.fudan.cosmosapp.bean.BookVersion;
import com.fudan.cosmosapp.httpClient.NetworkUtil;
import com.fudan.cosmosapp.ui.classify.model.ClassifyPageModel;
import com.fudan.cosmosapp.ui.classify.model.imple.ClassifyPageModelImple;
import com.fudan.cosmosapp.ui.classify.presenter.ClassifyPagePresenter;
import com.fudan.cosmosapp.ui.classify.view.ClassifyPageView;
import com.fudan.cosmosapp.utils.ToastUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class ClassifyPagePresenterImple implements ClassifyPagePresenter {

    private Context context;

    private ClassifyPageModel classifyPageModel;

    private ClassifyPageView classifyPageView;

    public ClassifyPagePresenterImple(Context context,ClassifyPageView classifyPageView){
        this.context = context;
        this.classifyPageView = classifyPageView;
        this.classifyPageView.setPresenter(this);
        this.classifyPageModel = new ClassifyPageModelImple();
    }

    @Override
    public void loadVersion(String subjectId, String gradeId) {

        if(!NetworkUtil.isNetworkAvailable(context)){
            ToastUtils.showToast(context,"网络无连接,请检查网络");
        }

        Observable<BookVersion> observable = classifyPageModel.getVersions(subjectId,gradeId);
        observable.subscribe(new Observer<BookVersion>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BookVersion bookVersion) {
                        if(bookVersion.getError().equals("0")){

                            classifyPageView.VersionLoaded(bookVersion);
                        }else if(bookVersion.getError().equals("1")){

                            classifyPageView.handleError(new Throwable(bookVersion.getReason()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        classifyPageView.handleError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
