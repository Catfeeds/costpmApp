package com.fudan.cosmosapp.ui.classify.presenter.imple;

import android.content.Context;

import com.fudan.cosmosapp.bean.QuestionDetail;
import com.fudan.cosmosapp.httpClient.NetworkUtil;
import com.fudan.cosmosapp.ui.classify.model.QuestionPageModel;
import com.fudan.cosmosapp.ui.classify.model.imple.QuestionPageModelImple;
import com.fudan.cosmosapp.ui.classify.presenter.QuestionPagePresenter;
import com.fudan.cosmosapp.ui.classify.view.QuestionPageView;
import com.fudan.cosmosapp.utils.ToastUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class QuestionPagePresenterImple implements QuestionPagePresenter {

    private Context context;

    private QuestionPageModel questionPageModel;

    private QuestionPageView questionPageView;

    public QuestionPagePresenterImple(Context context,QuestionPageView questionPageView){
        this.context = context;
        this.questionPageView = questionPageView;
        this.questionPageView.setPresenter(this);
        this.questionPageModel = new QuestionPageModelImple();
    }

    @Override
    public void loadQuestionDetail(final String quesId) {

        if(!NetworkUtil.isNetworkAvailable(context)){
            ToastUtils.showToast(context,"网络无连接,请检查网络");
        }

        Observable<QuestionDetail> observable = questionPageModel.getQuestionDetail(quesId);
        observable.subscribe(new Observer<QuestionDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QuestionDetail questionDetail) {
                        if(questionDetail.getError().equals("0")){

                            questionPageView.questionDetailLoaded(questionDetail);
                        } else if(questionDetail.equals("1")){

                            questionPageView.handleError(new Throwable(questionDetail.getReason()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        questionPageView.handleError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
