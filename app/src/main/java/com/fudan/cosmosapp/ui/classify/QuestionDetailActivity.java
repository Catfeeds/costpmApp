package com.fudan.cosmosapp.ui.classify;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.base.BaseActivity;
import com.fudan.cosmosapp.bean.Question;
import com.fudan.cosmosapp.bean.SearchQuestion;
import com.fudan.cosmosapp.fragment.QuestionPageFragment;
import com.fudan.cosmosapp.ui.classify.presenter.QuestionPresenter;
import com.fudan.cosmosapp.ui.classify.presenter.imple.QuestionPresenterImple;
import com.fudan.cosmosapp.ui.classify.view.QuestionView;
import com.fudan.cosmosapp.utils.ToastUtils;

import java.util.List;

public class QuestionDetailActivity extends BaseActivity implements QuestionView {
    private Toolbar mToolbar;
    private TabLayout mTabQuestion;
    private ViewPager mVPQuestion;
    private questionTabsAdapter mTabsAdapter;

    private QuestionPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);

        new QuestionPresenterImple(this,this);

        Intent intent = getIntent();
        if (intent.getStringExtra("knowledgePointId") != null){
            String knowledgePointId = intent.getStringExtra("knowledgePointId");
//            InitQuestionTask questionTask = new InitQuestionTask();
//            questionTask.execute(knowledgePointId);
            mPresenter.loadQuestions(knowledgePointId);
        }else if (intent.getStringExtra("searchKey") != null){
            String searchKey = intent.getStringExtra("searchKey");
//            InitSearchQuestionTask task = new InitSearchQuestionTask();
//            task.execute(searchKey);
            mPresenter.loadSearchQuestion(searchKey);
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar_question_detail);
        mTabQuestion = (TabLayout) findViewById(R.id.tab_question_detail);
        mVPQuestion = (ViewPager) findViewById(R.id.vp_question_detail) ;

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }

    @Override
    public void setPresenter(QuestionPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void questionLoaded(Question question) {
        List<Question.QuestionListBean> questionList = question.getQuestionList();
        mTabsAdapter = new questionTabsAdapter(getSupportFragmentManager());
        mTabsAdapter.setQuestionList(questionList);
        mVPQuestion.setAdapter(mTabsAdapter);
        mTabQuestion.setupWithViewPager(mVPQuestion);
    }

    @Override
    public void searchQuestion(SearchQuestion searchQuestion) {
        List<SearchQuestion.QuestionListBean> questionList = searchQuestion.getQuestionList();
        mTabsAdapter = new questionTabsAdapter(getSupportFragmentManager());
        mTabsAdapter.setSearchQuestionList(questionList);
        mVPQuestion.setAdapter(mTabsAdapter);
        mTabQuestion.setupWithViewPager(mVPQuestion);
    }

    @Override
    public void handleError(Throwable throwable) {
        ToastUtils.showToast(CosmosApplication.getContext(),throwable.getMessage());
    }

//    private class InitQuestionTask extends AsyncTask<String, Void, List<Question.QuestionListBean>> {
//
//        @Override
//        protected List<Question.QuestionListBean> doInBackground(String... strings) {
//            Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());
//            Call<Question> questionCall = api.getQuestions(strings[0]);
//            Response<Question> bodyResponse = null;
//            try {
//                bodyResponse = questionCall.execute();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Question question = bodyResponse.body();
//
//            return question.getQuestionList();
//
//        }
//
//        @Override
//        protected void onPostExecute(List<Question.QuestionListBean> questionListBeen) {
//            mTabsAdapter = new questionTabsAdapter(getSupportFragmentManager());
//            mTabsAdapter.setQuestionList(questionListBeen);
//            mVPQuestion.setAdapter(mTabsAdapter);
//            mTabQuestion.setupWithViewPager(mVPQuestion);
//
//        }
//    }
//
//
//    private class InitSearchQuestionTask extends AsyncTask<String, Void, List<SearchQuestion.QuestionListBean>> {
//        @Override
//        protected List<SearchQuestion.QuestionListBean> doInBackground(String... strings) {
//            Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());
//            Call<SearchQuestion> questionCall = api.getSearchQuestion(strings[0]);
//            Response<SearchQuestion> bodyResponse = null;
//            try {
//                bodyResponse = questionCall.execute();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            SearchQuestion searchQuestion = bodyResponse.body();
//
//            return searchQuestion.getQuestionList();
//        }
//
//        protected void onPostExecute(List<SearchQuestion.QuestionListBean> questionListBeen) {
//            mTabsAdapter = new questionTabsAdapter(getSupportFragmentManager());
//            mTabsAdapter.setSearchQuestionList(questionListBeen);
//            mVPQuestion.setAdapter(mTabsAdapter);
//            mTabQuestion.setupWithViewPager(mVPQuestion);
//
//        }
//    }


    private class questionTabsAdapter extends FragmentStatePagerAdapter{
        private List<Question.QuestionListBean> questionList;
        private List<SearchQuestion.QuestionListBean> searchQuestionList;

        private int fragmentType;

        public questionTabsAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setQuestionList(List<Question.QuestionListBean> questionList){
            this.questionList = questionList;
            this.fragmentType = 0;
        }

        public void setSearchQuestionList(List<SearchQuestion.QuestionListBean> searchQuestionList){
            this.searchQuestionList = searchQuestionList;
            this.fragmentType = 1;
        }

        @Override
        public Fragment getItem(int position) {
            if (fragmentType == 0){
                return QuestionPageFragment.newInstance(questionList.get(position).getQuestionId() );
            }else {
                return QuestionPageFragment.newInstance(searchQuestionList.get(position).getQuestionId());
            }

        }

        @Override
        public int getCount() {
//            if (fragmentType == 0){
//                return questionList.size();
//            }else {
//                return searchQuestionList.size();
//            }
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return String.valueOf(position + 1);
        }
    }
}
