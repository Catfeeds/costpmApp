package com.fudan.cosmosapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.base.BaseFragment;
import com.fudan.cosmosapp.bean.QuestionDetail;
import com.fudan.cosmosapp.ui.classify.presenter.QuestionPagePresenter;
import com.fudan.cosmosapp.ui.classify.presenter.imple.QuestionPagePresenterImple;
import com.fudan.cosmosapp.ui.classify.view.QuestionPageView;
import com.fudan.cosmosapp.utils.ToastUtils;

/**
 * Created by Ang on 2017/8/10.
 */

public class QuestionPageFragment extends BaseFragment implements QuestionPageView {
    private WebView tvQuestion;
    private WebView tvAnswer;

    private String quesId = null;

    private QuestionPagePresenter mPresenter;

    private static final String QUES_ID = "ques-Id";

    public static QuestionPageFragment newInstance(String id) {
        Bundle args = new Bundle();
        QuestionPageFragment fragment = new QuestionPageFragment();
        args.putString(QUES_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new QuestionPagePresenterImple(getContext(),this);
        if (getArguments() != null){
            quesId = getArguments().getString(QUES_ID);
//            InitSolutionTask solutionTask = new InitSolutionTask();
//            solutionTask.execute(quesId);
            mPresenter.loadQuestionDetail(quesId);
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_page, container, false);

        tvQuestion = view.findViewById(R.id.tv_question);
        tvAnswer = view.findViewById(R.id.tv_answer);

        return view;
    }

    @Override
    public void setPresenter(QuestionPagePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void questionDetailLoaded(QuestionDetail questionDetail) {
        tvQuestion.loadDataWithBaseURL(null, String.valueOf(Html.fromHtml(questionDetail.getQuesContextHtml())),  "text/html", "utf-8", null);
        tvAnswer.loadDataWithBaseURL(null, String.valueOf(Html.fromHtml(questionDetail.getAnsContextHtml())), "text/html", "utf-8", null);

    }

    @Override
    public void handleError(Throwable throwable) {
        ToastUtils.showToast(CosmosApplication.getContext(),throwable.getMessage());
    }

//    private class InitSolutionTask extends AsyncTask<String, Void, QuestionDetail>{
//
//        @Override
//        protected QuestionDetail doInBackground(String... strings) {
//            Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());
//            Call<QuestionDetail> questionDetailCall = api.getQuestionDetail(strings[0]);
//            Response<QuestionDetail> bodyResponse = null;
//            try {
//                bodyResponse = questionDetailCall.execute();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            QuestionDetail questionDetail = bodyResponse.body();
//            return questionDetail;
//
//
//        }
//
//
//        @Override
//        protected void onPostExecute(QuestionDetail questionDetail) {
//
//            tvQuestion.loadDataWithBaseURL(null, String.valueOf(Html.fromHtml(questionDetail.getQuesContextHtml())),  "text/html", "utf-8", null);
//            tvAnswer.loadDataWithBaseURL(null, String.valueOf(Html.fromHtml(questionDetail.getAnsContextHtml())), "text/html", "utf-8", null);
//
////            tvQuestion.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置可滚动
////            tvQuestion.setText(Html.fromHtml(String.valueOf(Html.fromHtml(questionDetail.getQuesContextHtml(), imgGetter, null))));
////            tvAnswer.setText(Html.fromHtml(String.valueOf(Html.fromHtml(questionDetail.getAnsContextHtml(), imgGetter, null))));
//            Logger.i(questionDetail.getQuesId());
//
//
////            tvAnswer.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置可滚动
////            tvAnswer.setText(Html.fromHtml(questionDetail.getQuesContextHtml(), imgGetter, null));
//
//        }
//    }

//    private class InitSolutionTask extends AsyncTask<String, Void, String>{
//
//        @Override
//        protected String doInBackground(String... strings) {
//            Api api = CourseNetwork.getInstance().getApi();
//            Call<String> questionDetailCall = api.getQuestionDetailStr(strings[0]);
//            Response<String> bodyResponse = null;
//            try {
//                bodyResponse = questionDetailCall.execute();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return bodyResponse.body();
//
//
//        }
//
//
//        @Override
//        protected void onPostExecute(String questionDetail) {
//            try {
//                JSONObject body = new JSONObject(questionDetail);
//                String quesId = body.get("quesId").toString();
//                String quesContextHtml = body.get("quesContextHtml").toString();
//                String ansContextHtml = body.get("ansContextHtml").toString();
//
//                tvAnswer.loadDataWithBaseURL(null, ansContextHtml, "text/html", "utf-8", null);
//
////            tvQuestion.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置可滚动
//                tvQuestion.setText(Html.fromHtml(quesContextHtml, imgGetter, null));
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//
//        }
//    }

//
//    Html.ImageGetter imgGetter = new Html.ImageGetter() {
//        public Drawable getDrawable(String source) {
//            Log.i("RG", "source---?>>>" + source);
//            Drawable drawable = null;
//            URL url;
//            try {
//                url = new URL(source);
//                Log.i("RG", "url---?>>>" + url);
//                drawable = Drawable.createFromStream(url.openStream(), ""); // 获取网路图片
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
//                    drawable.getIntrinsicHeight());
//            Log.i("RG", "url---?>>>" + url);
//            return drawable;
//        }
//    };


}
