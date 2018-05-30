package com.fudan.cosmosapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.adapter.MainPageAdapter;
import com.fudan.cosmosapp.adapter.MainTabsAdapter;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.base.BaseFragment;
import com.fudan.cosmosapp.bean.BookVersion;
import com.fudan.cosmosapp.ui.classify.QuestionDetailActivity;
import com.fudan.cosmosapp.ui.classify.presenter.ClassifyPagePresenter;
import com.fudan.cosmosapp.ui.classify.presenter.imple.ClassifyPagePresenterImple;
import com.fudan.cosmosapp.ui.classify.view.ClassifyPageView;
import com.fudan.cosmosapp.utils.ToastUtils;

import java.util.List;

/**
 * Created by Ang on 2017/8/3.
 */

public class ClassifyPageFragment extends BaseFragment implements MainTabsAdapter.DataChangeListener, MainPageAdapter.MainPageItemListener, ClassifyPageView {
    private RecyclerView rvMainPage;
    private static MainPageAdapter rvAdapter;

    private static final String GRADE_ID = "grade-id";
    private static final String SUBJECT_ID = "subject-id";
    private int mGradeId, mSubjectId;

    private ClassifyPagePresenter mPresenter;

    public static Fragment newInstance(int gradeId, int subjectId) {
        Bundle args = new Bundle();
        args.putInt(GRADE_ID, gradeId);
        args.putInt(SUBJECT_ID, subjectId);
        ClassifyPageFragment fragment = new ClassifyPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mGradeId = getArguments().getInt(GRADE_ID);
            mSubjectId = getArguments().getInt(SUBJECT_ID);
        }

        Log.e("mGradeId", mGradeId + "");
        Log.e("mSubjectId", mSubjectId + "");

        //设置代理人
        new ClassifyPagePresenterImple(CosmosApplication.getContext(), this).loadVersion(mSubjectId + "", mGradeId + "");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);

        rvMainPage = view.findViewById(R.id.rv_main_page);
        rvMainPage.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvAdapter = new MainPageAdapter(this);

        return view;
    }

    @Override
    public void setPresenter(ClassifyPagePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void VersionLoaded(BookVersion bookVersion) {

        Log.e("6666666", "");

        List<BookVersion.TextbookVersionListBean> versionList = bookVersion.getTextbookVersionList();
        rvAdapter.notifyMainPageDataChanged(mGradeId, mSubjectId, versionList);
        rvMainPage.setAdapter(rvAdapter);
    }

    @Override
    public void handleError(Throwable throwable) {
        ToastUtils.showToast(CosmosApplication.getContext(),throwable.getMessage());
    }

    /**
     * 当需要更新 version 的数据时回调这个方法
     *
     * @param gradeId
     * @param subjectId
     */
    @Override
    public void DataChanged(final int gradeId, final int subjectId) {

        mGradeId = gradeId;
        mSubjectId = subjectId;

        if (mPresenter != null) {
            mPresenter.loadVersion(String.valueOf(subjectId), String.valueOf(gradeId));
        }
    }

    @Override
    public void onClick(String knowledgePointId) {
        Intent intent = new Intent(getActivity(), QuestionDetailActivity.class);
        intent.putExtra("knowledgePointId", knowledgePointId);
        getActivity().startActivity(intent);
    }

}
