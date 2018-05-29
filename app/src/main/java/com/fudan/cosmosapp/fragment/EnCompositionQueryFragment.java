package com.fudan.cosmosapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.adapter.EnCompositionAdapter;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.EnCompositionTitleArray;
import com.fudan.cosmosapp.ui.discover.presenter.EnCompositionPresenter;
import com.fudan.cosmosapp.ui.discover.presenter.imple.EnCompositionPresenterImple;
import com.fudan.cosmosapp.ui.discover.view.EnCompositionView;
import com.fudan.cosmosapp.utils.ToastUtils;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class EnCompositionQueryFragment extends BaseWordExplainFragment implements EnCompositionView {

    private ImageButton mChineseCompositionButton;
    private EditText edQuery;
    private EnCompositionAdapter mAdapter;

    private EnCompositionPresenter mPresenter;
    private ProgressBar progressBar;
    private TextView tvDes;
    private RecyclerView mCompositionListView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_composition_query,container,false);

        //获取listview并适配数据
        mCompositionListView = (RecyclerView) view.findViewById(R.id.recycleView);
        edQuery = (EditText) view.findViewById(R.id.ed_query);
        //获取文章搜索按钮
        mChineseCompositionButton = (ImageButton)view.findViewById(R.id.ch_composition_search_btn);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        tvDes = (TextView) view.findViewById(R.id.tv_des);

        mAdapter = new EnCompositionAdapter(CosmosApplication.getContext(),null);
        mCompositionListView.setLayoutManager(new LinearLayoutManager(CosmosApplication.getContext()));
        mCompositionListView.setAdapter(mAdapter);

        //设置搜索按钮点击事件
        mChineseCompositionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edQuery.getText().equals("")) {
                    ToastUtils.showToast(CosmosApplication.getContext(),"输入为空，请重输");
                    return;
                }

                String text = edQuery.getText().toString();
                mPresenter.loadEnCompositionTitleListByTitle(text);
            }
        });

        new EnCompositionPresenterImple(CosmosApplication.getContext(),this);

        return view;
    }

    @Override
    public void setPresenter(EnCompositionPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void handleError(Throwable throwable) {
        ToastUtils.showToast(CosmosApplication.getContext(),throwable.getMessage());
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void emptyView(boolean visible) {
        tvDes.setVisibility(visible?View.GONE:View.VISIBLE);
    }

    @Override
    public void enCompositionTitleListLoaded(EnCompositionTitleArray enCompositionTitleArray) {
        if(enCompositionTitleArray != null){
            mAdapter.setData(enCompositionTitleArray.getEnCompositionList());
            mAdapter.notifyDataSetChanged();
        }
    }
}
