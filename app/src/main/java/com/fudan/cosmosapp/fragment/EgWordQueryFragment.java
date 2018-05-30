package com.fudan.cosmosapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.adapter.EnWordQueryAdapter;
import com.fudan.cosmosapp.adapter.ListAdapter;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.EnWordListArray;
import com.fudan.cosmosapp.ui.discover.FB_wordDetailActivity;
import com.fudan.cosmosapp.ui.discover.presenter.EnWordQueryPresenter;
import com.fudan.cosmosapp.ui.discover.presenter.imple.EnWordQueryPresenterImple;
import com.fudan.cosmosapp.ui.discover.view.EnWordQueryView;
import com.fudan.cosmosapp.utils.ToastUtils;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class EgWordQueryFragment extends BaseWordExplainFragment implements EnWordQueryView {

    private static final String WORD = "word";
    private String word;
    private EditText edWord;
    private Button btnSearch;
    private RecyclerView recyclerView;
    private Button btnQuery;
    private Button btnRes;
    private ProgressBar progressBar;
    private TextView tvDes;

    private EnWordQueryPresenter mPresenter;
    private EnWordQueryAdapter mAdapter;

    private int failedType = EnWordQueryView.ENWORDLISTLOADED_FAILED;

    public EgWordQueryFragment(){
        super();
    }

    public static BaseWordExplainFragment newInstance(String text) {
        Bundle args = new Bundle();
        args.putString(WORD,text);
        EgWordQueryFragment fragment = new EgWordQueryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            word = getArguments().getString(WORD,"");
        }

        new EnWordQueryPresenterImple(CosmosApplication.getContext(),this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_eg,container,false);

        edWord = (EditText) view.findViewById(R.id.et_word);
        btnSearch = (Button) view.findViewById(R.id.btn_search);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        btnQuery = (Button) view.findViewById(R.id.btn_query);
        btnRes = (Button) view.findViewById(R.id.btn_res);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        tvDes = (TextView) view.findViewById(R.id.tv_des);

        if(word != null && !word.equals("")){
            edWord.setText(word);
        }

        initView();

        if (word != null && !word.equals("")) {
            edWord.setText(word);
            mPresenter.loadEnWordListByWord(word);
        }

        return view;
    }

    private void initView() {

        mAdapter = new EnWordQueryAdapter(CosmosApplication.getContext(),null);
        recyclerView.setLayoutManager(new LinearLayoutManager(CosmosApplication.getContext()));
        recyclerView.setAdapter(mAdapter);

        //跳转至详情界面
        mAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                EnWordListArray.EnglishDictionaryListBean item = mAdapter.getItem(position);

                Intent intent = new Intent(CosmosApplication.getContext(), FB_wordDetailActivity.class);
                intent.putExtra("en_keyId",item.getWordId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                CosmosApplication.getContext().startActivity(intent);
            }
        });

        //单词查询
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( edWord.getText().equals("")){
                    ToastUtils.showToast(CosmosApplication.getContext(),"输入为空，请重新输入");
                    return;
                }
                mPresenter.loadEnWordListByWord(edWord.getText().toString());
            }
        });

        //列表查询
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadEnWordList();
            }
        });

        //重新加载
        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (failedType){
                    case ENWORDLISTLOADED_FAILED :
                        mPresenter.loadEnWordList();
                        break;
                    case ENWORDLISTBYWORDLOADED_FAILED :
                        if( edWord.getText().equals("")){
                            ToastUtils.showToast(CosmosApplication.getContext(),"输入为空，请重新输入");
                            return;
                        }
                        mPresenter.loadEnWordListByWord(edWord.getText().toString());
                        break;
                }
            }
        });
    }

    @Override
    public void setPresenter(EnWordQueryPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void enWordListLoaded(EnWordListArray enWordListArray) {
        if(enWordListArray != null){
            mAdapter.clear();
            mAdapter.setData(enWordListArray.getEnglishDictionaryList());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void enWordListByWordLoaded(EnWordListArray enWordListArray) {
        if(enWordListArray != null){
            mAdapter.clear();
            mAdapter.setData(enWordListArray.getEnglishDictionaryList());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void enWordListLoadedFailed(int who) {
        failedType = who;
    }

    @Override
    public void handleError(Throwable throwable) {
        ToastUtils.showToast(CosmosApplication.getContext(),throwable.getMessage());
    }

    @Override
    public void emptyView(boolean isvisible) {
        if(isvisible){
            tvDes.setVisibility(View.GONE);
            btnRes.setVisibility(View.GONE);
        } else {
            tvDes.setVisibility(View.VISIBLE);
            btnRes.setVisibility(View.VISIBLE);
            tvDes.setText("加载失败");
            btnRes.setText("重新加载");
        }
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        tvDes.setVisibility(View.VISIBLE);
        tvDes.setText("加载中");
    }
}
