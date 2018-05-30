package com.fudan.cosmosapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.adapter.CnCompositionAdapter;
import com.fudan.cosmosapp.adapter.CnCompositionClassifyAdapter;
import com.fudan.cosmosapp.adapter.ListAdapter;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.ChCompositionTitleArray;
import com.fudan.cosmosapp.bean.CnCompositionClassifyBean;
import com.fudan.cosmosapp.ui.discover.presenter.CnCompositionClassifyPresenter;
import com.fudan.cosmosapp.ui.discover.presenter.imple.CnCompositionClassifyPresenterImple;
import com.fudan.cosmosapp.ui.discover.view.CnCompositionClassifyView;
import com.fudan.cosmosapp.utils.ToastUtils;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class CnCompositionClassifyFragment extends BaseWordExplainFragment implements CnCompositionClassifyView {

    private RecyclerView recyclerView_cls;
    private RecyclerView recyclerView_list;
    private CnCompositionClassifyAdapter mClassifyAdapter;
    private CnCompositionAdapter mListAdapter;

    private CnCompositionClassifyPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classify,container,false);

        recyclerView_cls = (RecyclerView) view.findViewById(R.id.recycleView_classify);
        recyclerView_list = (RecyclerView) view.findViewById(R.id.recycleView_list);

        initView();

        new CnCompositionClassifyPresenterImple(CosmosApplication.getContext(),this).loadChCompositionClassify();

        return view;
    }

    private void initView() {
        //分类列表
        mClassifyAdapter = new CnCompositionClassifyAdapter(CosmosApplication.getContext(),null);
        GridLayoutManager layoutManage = new GridLayoutManager(getContext(), 2);//每行两列
        recyclerView_cls.setLayoutManager(layoutManage);
//        recyclerView_cls.setLayoutManager(new LinearLayoutManager(CosmosApplication.getContext()));
        recyclerView_cls.setAdapter(mClassifyAdapter);
        //标题列表
        mListAdapter = new CnCompositionAdapter(CosmosApplication.getContext(),null);
        recyclerView_list.setLayoutManager(new LinearLayoutManager(CosmosApplication.getContext()));
        recyclerView_list.setAdapter(mListAdapter);

        //选中类型 加载列表
        mClassifyAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CnCompositionClassifyBean.ChCClassificationList item = mClassifyAdapter.getItem(position);
                mPresenter.loadChCompositionListByClassifyId(item.getCHCompositionClassificationId());
            }
        });

    }

    @Override
    public void setPresenter(CnCompositionClassifyPresenter presenter) {
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
    public void cnCompositionClassifyLoaded(CnCompositionClassifyBean cnCompositionClassifyBean) {
        if(cnCompositionClassifyBean != null){
            Log.e("分类",cnCompositionClassifyBean.getChCClassificationList().get(0).getCHCompositionClassification());
            mClassifyAdapter.setData(cnCompositionClassifyBean.getChCClassificationList());
            mClassifyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void cnCompositionListLoaded(ChCompositionTitleArray chCompositionTitleArray) {
        if(chCompositionTitleArray != null){
            mListAdapter.setData(chCompositionTitleArray.getChCompositionList());
            mListAdapter.notifyDataSetChanged();
        }
    }
}
