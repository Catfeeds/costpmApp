package com.fudan.cosmosapp.ui.classify.presenter.imple;

import android.content.Context;

import com.fudan.cosmosapp.bean.Question;
import com.fudan.cosmosapp.bean.SearchQuestion;
import com.fudan.cosmosapp.httpClient.NetworkUtil;
import com.fudan.cosmosapp.ui.classify.model.QuestionModel;
import com.fudan.cosmosapp.ui.classify.model.imple.QuestionModelImple;
import com.fudan.cosmosapp.ui.classify.presenter.QuestionPresenter;
import com.fudan.cosmosapp.ui.classify.view.QuestionView;
import com.fudan.cosmosapp.utils.ToastUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class QuestionPresenterImple implements QuestionPresenter {

    private Context context;

    private QuestionModel questionModel;

    private QuestionView questionView;

    public QuestionPresenterImple(Context context,QuestionView questionView){
        this.context = context;
        this.questionView = questionView;
        this.questionView.setPresenter(this);
        this.questionModel = new QuestionModelImple();
    }

    @Override
    public void loadQuestions(String knowledgePointId) {

        if(!NetworkUtil.isNetworkAvailable(context)){
            ToastUtils.showToast(context,"网络无连接,请检查网络");
        }

        Observable<Question> observable = questionModel.getQuestions(knowledgePointId);
        observable.subscribe(new Observer<Question>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Question question) {
                        if(question.getError().equals("0")){

                            questionView.questionLoaded(question);
                        }else if(question.getError().equals("1")){
                            questionView.handleError(new Throwable(question.getReason()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        questionView.handleError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadSearchQuestion(String searchKey) {

        Observable<SearchQuestion> observable = questionModel.getSearchQuestion(searchKey);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchQuestion>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchQuestion searchQuestion) {
                        questionView.searchQuestion(searchQuestion);
                    }

                    @Override
                    public void onError(Throwable e) {
                        questionView.handleError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
