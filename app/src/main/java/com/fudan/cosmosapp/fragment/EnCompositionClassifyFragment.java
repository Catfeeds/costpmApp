package com.fudan.cosmosapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.adapter.EnCompositionAdapter;
import com.fudan.cosmosapp.adapter.EnCompositionClassifyAdapter;
import com.fudan.cosmosapp.adapter.ListAdapter;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.EnCompositionClassifyBean;
import com.fudan.cosmosapp.bean.EnCompositionTitleArray;
import com.fudan.cosmosapp.ui.discover.presenter.EnCompositionClassifyPresenter;
import com.fudan.cosmosapp.ui.discover.presenter.imple.EnCompositionClassifyPresenterImple;
import com.fudan.cosmosapp.ui.discover.view.EnCompositionClassifyView;
import com.fudan.cosmosapp.utils.ToastUtils;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class EnCompositionClassifyFragment extends BaseWordExplainFragment implements EnCompositionClassifyView {

    private static final int MAX = 9;

    private RecyclerView recyclerView_cls;
    private RecyclerView recyclerView_list;
    private EnCompositionClassifyAdapter mClassifyAdapter;
    private EnCompositionAdapter mListAdapter;

    private EnCompositionClassifyPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classify,container,false);

        recyclerView_cls = (RecyclerView) view.findViewById(R.id.recycleView_classify);
        recyclerView_list = (RecyclerView) view.findViewById(R.id.recycleView_list);

        initView();

        new EnCompositionClassifyPresenterImple(CosmosApplication.getContext(),this).loadEnCompositionClassify();

        return view;
    }

    private void initView() {
        //分类列表
        mClassifyAdapter = new EnCompositionClassifyAdapter(CosmosApplication.getContext(),null);
        GridLayoutManager layoutManage = new GridLayoutManager(getContext(), 2);
        recyclerView_cls.setLayoutManager(layoutManage);
//        recyclerView_cls.setLayoutManager(new LinearLayoutManager(CosmosApplication.getContext()));
        recyclerView_cls.setAdapter(mClassifyAdapter);
        //标题列表
        mListAdapter = new EnCompositionAdapter(CosmosApplication.getContext(),null);
        recyclerView_list.setLayoutManager(new LinearLayoutManager(CosmosApplication.getContext()));
        recyclerView_list.setAdapter(mListAdapter);

        //选中类型 加载列表
        mClassifyAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                EnCompositionClassifyBean.EnCClassificationList item = mClassifyAdapter.getItem(position);
                mPresenter.loadEnCompositionListByClassifyId(item.getENCompositionClassificationId());
            }
        });

    }

    @Override
    public void setPresenter(EnCompositionClassifyPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void emptyView(boolean visible) {

    }

    @Override
    public void handleError(Throwable throwable) {
        ToastUtils.showToast(CosmosApplication.getContext(),throwable.getMessage());
    }

    @Override
    public void enCompositionClassifyLoaded(EnCompositionClassifyBean enCompositionClassifyBean) {
        if(enCompositionClassifyBean != null){
            mClassifyAdapter.setData(enCompositionClassifyBean.getEnCClassificationLists());
            mClassifyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void enCompositionListLoaded(EnCompositionTitleArray enCompositionTitleArray) {
        if(enCompositionTitleArray != null){
            mListAdapter.setData(enCompositionTitleArray.getEnCompositionList());
            mListAdapter.notifyDataSetChanged();
        }
    }
}
