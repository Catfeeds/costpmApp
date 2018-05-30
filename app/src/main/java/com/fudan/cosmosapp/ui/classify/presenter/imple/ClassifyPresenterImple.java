package com.fudan.cosmosapp.ui.classify.presenter.imple;

import android.content.Context;

import com.fudan.cosmosapp.bean.Grade;
import com.fudan.cosmosapp.bean.Subject;
import com.fudan.cosmosapp.httpClient.NetworkUtil;
import com.fudan.cosmosapp.ui.classify.model.ClassifyModel;
import com.fudan.cosmosapp.ui.classify.model.imple.ClassifyModelImple;
import com.fudan.cosmosapp.ui.classify.presenter.ClassifyPresenter;
import com.fudan.cosmosapp.ui.classify.view.ClassifyView;
import com.fudan.cosmosapp.utils.ToastUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class ClassifyPresenterImple implements ClassifyPresenter {

    private Context context;

    private ClassifyView classifyView;

    private ClassifyModel classifyModel;


    public ClassifyPresenterImple(Context context,ClassifyView classifyView){
        this.context = context;
        this.classifyView = classifyView;
        this.classifyView.setPresenter(this);
        this.classifyModel = new ClassifyModelImple();
    }

    @Override
    public void loadGrade() {

        if(!NetworkUtil.isNetworkAvailable(context)){
            ToastUtils.showToast(context,"网络无连接,请检查网络");
        }

        Observable<Grade> observable = classifyModel.loadGrades();
        observable.subscribe(new Observer<Grade>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        classifyView.showProgress();
                    }

                    @Override
                    public void onNext(Grade grade) {
                        if(grade.getError().equals("1")){
                            classifyView.emptyView(false);
                            classifyView.hideProgress();
                            classifyView.handleError(new Throwable(grade.getReason()));
                            classifyView.failedLoadingType(ClassifyView.FAILED_LOADING_GRADE,-1);
                        } else if(grade.getError().equals("0")){
                            classifyView.emptyView(true);
                            classifyView.gradeLoaded(grade);
                            classifyView.hideProgress();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        classifyView.hideProgress();
                        classifyView.handleError(e);
                        classifyView.failedLoadingType(ClassifyView.FAILED_LOADING_GRADE,-1);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadSubject(final int id) {

        if(!NetworkUtil.isNetworkAvailable(context)){
            ToastUtils.showToast(context,"网络无连接,请检查网络");
        }

        Observable<Subject> observable = classifyModel.loadSubjects(id);
        observable.subscribe(new Observer<Subject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        classifyView.showProgress();
                    }

                    @Override
                    public void onNext(Subject subject) {
                        if(subject.getError().equals("1")){
                            classifyView.hideProgress();
                            classifyView.emptyView(false);
                            classifyView.handleError(new Throwable(subject.getReason()));
                            classifyView.failedLoadingType(ClassifyView.FAILED_LOADING_SUBJECT,id);
                        } else if(subject.getError().equals("0")){
                            classifyView.emptyView(true);
                            classifyView.hideProgress();
                            classifyView.subjectLoad(subject);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        classifyView.hideProgress();
                        classifyView.emptyView(false);
                        classifyView.handleError(e);
                        classifyView.failedLoadingType(ClassifyView.FAILED_LOADING_SUBJECT,id);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
