package com.fudan.cosmosapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.adapter.MainTabsAdapter;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.base.BaseFragment;
import com.fudan.cosmosapp.bean.Grade;
import com.fudan.cosmosapp.bean.Subject;
import com.fudan.cosmosapp.ui.classify.QuestionDetailActivity;
import com.fudan.cosmosapp.ui.classify.presenter.ClassifyPresenter;
import com.fudan.cosmosapp.ui.classify.presenter.imple.ClassifyPresenterImple;
import com.fudan.cosmosapp.ui.classify.view.ClassifyView;
import com.fudan.cosmosapp.utils.ToastUtils;

/**
 * Created by Ang on 2017/8/2.
 */

public class ClassifyFragment extends BaseFragment implements ClassifyView {


    private View mView;
    private Toolbar toolbar;
    private TabLayout tabSubject;
    private Spinner spinnerGrade;
    private ViewPager viewPager;

    private ProgressBar progressBar;
    private TextView tvDes;
    private Button btnRes;

    private ArrayAdapter<String> gradeAdapter;
    private MainTabsAdapter mainTabsAdapter;

    private int mGradeId = 9;

    private ClassifyPresenter mPresenter;

    private MyFailedListener mListener = new MyFailedListener();
    private int mType;
    private int mParams;

    public static Fragment newInstance() {
        return new ClassifyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main, container, false);

        toolbar = mView.findViewById(R.id.toolbar_fragment_main);
        tabSubject = mView.findViewById(R.id.tab_main_subject);
        spinnerGrade = mView.findViewById(R.id.spinner_fragment_main_grade);
        viewPager = mView.findViewById(R.id.vp_main);

        progressBar = (ProgressBar) mView.findViewById(R.id.progress_bar);
        tvDes = (TextView) mView.findViewById(R.id.tv_des);
        btnRes = (Button) mView.findViewById(R.id.btn_res);

        spinnerGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(mainTabsAdapter != null){
                    mainTabsAdapter.notifyTabsDataChanged(i + 1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        //重新加载
        btnRes.setOnClickListener(mListener);

        initView();

        return mView;
    }


    private void initView() {
        new ClassifyPresenterImple(getContext(), this).loadGrade();
        mPresenter.loadSubject(mGradeId);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    /**
     * 菜单
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_search) {
            // TODO: 2017/8/3 menu_search
            Intent intent = new Intent(getActivity(), QuestionDetailActivity.class);
            intent.putExtra("searchKey", "沿虚线折叠后能围成正方体的有");
            getActivity().startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 学科数过多则设置滚动
     *
     * @param subjectNum
     */
    public void setTabSubjectMode(int subjectNum) {

        if (subjectNum > 5) {
            tabSubject.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else {
            tabSubject.setTabMode(TabLayout.MODE_FIXED);
        }
    }

    @Override
    public void setPresenter(ClassifyPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void emptyView(boolean visible) {

    }

    @Override
    public void handleError(Throwable throwable) {
        ToastUtils.showToast(CosmosApplication.getContext(),throwable.getMessage());
    }

    @Override
    public void gradeLoaded(Grade grade) {

        String[] gradeString = new String[grade.getGradeList().size() - 1];
        for (int i = 1; i < grade.getGradeList().size(); i++) {
            gradeString[i - 1] = grade.getGradeList().get(i).getGradeName();
        }

        gradeAdapter = new ArrayAdapter<>(mView.getContext(), R.layout.row_spn, gradeString);
        gradeAdapter.setDropDownViewResource(R.layout.row_spn_dropdown);
        spinnerGrade.setAdapter(gradeAdapter);
        spinnerGrade.setSelection(mGradeId - 1);
    }

    @Override
    public void subjectLoad(Subject subject) {
        mainTabsAdapter = new MainTabsAdapter(mGradeId, subject.getSubjectList(), getChildFragmentManager(), ClassifyFragment.this);
        viewPager.setAdapter(mainTabsAdapter);
        tabSubject.setupWithViewPager(viewPager);
        setTabSubjectMode(subject.getSubjectList().size());

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        tvDes.setVisibility(View.GONE);
        btnRes.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        tvDes.setText("加载中..");
        tvDes.setVisibility(View.VISIBLE);
        btnRes.setVisibility(View.GONE);
    }

    @Override
    public void failedLoadingType(int type, int params) {
        btnRes.setVisibility(View.VISIBLE);
        tvDes.setVisibility(View.VISIBLE);
        btnRes.setText("重新加载");
        tvDes.setText("加载失败");

        mType = type;
        mParams = params;

    }

    public class MyFailedListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (mType){

                case ClassifyView.FAILED_LOADING_GRADE :
                    mPresenter.loadGrade();
                    mPresenter.loadSubject(mGradeId);
                    break;
                case ClassifyView.FAILED_LOADING_SUBJECT :
                    mPresenter.loadSubject(mParams);
                    break;


            }
        }
    }
}
