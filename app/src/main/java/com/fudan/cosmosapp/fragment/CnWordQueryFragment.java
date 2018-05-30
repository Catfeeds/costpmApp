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
import com.fudan.cosmosapp.adapter.CNPinYinListAdapter;
import com.fudan.cosmosapp.adapter.CNRadicalListAdapter;
import com.fudan.cosmosapp.adapter.CNWordByKeyIdListAdapter;
import com.fudan.cosmosapp.adapter.CNWordListAdapter;
import com.fudan.cosmosapp.adapter.ListAdapter;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.KeyArray;
import com.fudan.cosmosapp.bean.KeyDetailArray;
import com.fudan.cosmosapp.bean.PinYinArray;
import com.fudan.cosmosapp.bean.RadicalArray;
import com.fudan.cosmosapp.ui.discover.FB_wordDetailActivity;
import com.fudan.cosmosapp.ui.discover.presenter.SearchKeyDetailPresenter;
import com.fudan.cosmosapp.ui.discover.presenter.imple.SearchKeyDetailPresenterImple;
import com.fudan.cosmosapp.ui.discover.view.SearchKeyDetailView;
import com.fudan.cosmosapp.utils.ToastUtils;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class CnWordQueryFragment extends BaseWordExplainFragment implements SearchKeyDetailView {

    private static final String WORD = "word";

    String word;
    private EditText edWord;
    private Button btnRadicalSearch;
    private Button btnPinYinSearch;
    private RecyclerView recyclerView;

    private SearchKeyDetailPresenter mPresenter;
    private RecyclerView recyclerViewCnWord;

    private CNPinYinListAdapter mCnPinYinListAdapter;
    private CNRadicalListAdapter mCnRadicalListAdapter;

    private CNWordListAdapter mCNWordListAdapter;
    private CNWordByKeyIdListAdapter mCnWordByIdListAdapter;
    private Button btnCnSearch;
    private ProgressBar progressBar;
    private TextView tvDes;
    private Button btnRes;

    private MyFailedListener mListener = new MyFailedListener();
    private int mFailedType;
    private String mParams;

    public static BaseWordExplainFragment newInstance(String text) {
        Bundle args = new Bundle();
        args.putString(WORD, text);
        CnWordQueryFragment fragment = new CnWordQueryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CnWordQueryFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            word = getArguments().getString(WORD, "");
        }

        new SearchKeyDetailPresenterImple(CosmosApplication.getContext(), this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cn, container, false);

        edWord = (EditText) view.findViewById(R.id.et_word);

        btnRadicalSearch = (Button) view.findViewById(R.id.btn_radical_search);
        btnPinYinSearch = (Button) view.findViewById(R.id.btn_pinyin_search);
        btnCnSearch = (Button) view.findViewById(R.id.btn_cn_search);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(CosmosApplication.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewCnWord = (RecyclerView) view.findViewById(R.id.recycleView_cn);
        recyclerViewCnWord.setLayoutManager(new LinearLayoutManager(CosmosApplication.getContext(), LinearLayoutManager.VERTICAL, false));

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        tvDes = (TextView) view.findViewById(R.id.tv_des);
        btnRes = (Button) view.findViewById(R.id.btn_res);

        initListener();

        if (word != null && !word.equals("")) {
            edWord.setText(word);
            mPresenter.getSearchKeyDetail(word);
        }

        return view;
    }

    private void initListener() {

        mCnPinYinListAdapter = new CNPinYinListAdapter(CosmosApplication.getContext(), null);
        mCnRadicalListAdapter = new CNRadicalListAdapter(CosmosApplication.getContext(), null);

        mCnWordByIdListAdapter = new CNWordByKeyIdListAdapter(CosmosApplication.getContext(), null);
        mCNWordListAdapter = new CNWordListAdapter(CosmosApplication.getContext(), null);

        //拼音查询
        mCnPinYinListAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                recyclerView.setVisibility(View.GONE);
                recyclerViewCnWord.setVisibility(View.VISIBLE);

                PinYinArray.PinyinList item = mCnPinYinListAdapter.getItem(position);
                if (item != null && item.getPinyinId() != null) {
                    //通过拼音加载keyid
                    mPresenter.getKeyIdByPinYinId(item.getPinyin());
                }

            }
        });
        //首部查询
        mCnRadicalListAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                recyclerView.setVisibility(View.GONE);
                recyclerViewCnWord.setVisibility(View.VISIBLE);

                RadicalArray.RadicalList item = mCnRadicalListAdapter.getItem(position);
                if (item != null && item.getRadicalId() != null) {
                    mPresenter.getKeyIdByRadicalId(item.getRadicalId());
                }

            }
        });

        //进入详情页面
        mCnWordByIdListAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                KeyArray.KeyList item = mCnWordByIdListAdapter.getItem(position);

                Intent intent = new Intent(CosmosApplication.getContext(), FB_wordDetailActivity.class);
                intent.putExtra("cn_keyId",item.getKeyId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                CosmosApplication.getContext().startActivity(intent);

            }
        });
        //进入详情页面
        mCNWordListAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                KeyDetailArray.KeyDetail item = mCNWordListAdapter.getItem(position);

                Intent intent = new Intent(CosmosApplication.getContext(), FB_wordDetailActivity.class);
                intent.putExtra("cn_keyId",item.getKeyId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                CosmosApplication.getContext().startActivity(intent);
            }
        });

        //查询拼音列表
        btnPinYinSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewCnWord.setVisibility(View.GONE);
                mPresenter.getPinYinList();
            }
        });
        //查询首部列表
        btnRadicalSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewCnWord.setVisibility(View.GONE);
                mPresenter.getRadicalList();
            }
        });
        //直接汉字查询
        btnCnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.GONE);

                if (edWord.getText().equals("")) {
                    ToastUtils.showToast(CosmosApplication.getContext(),"输入为空请重输");
                    return;
                }
                String key = edWord.getText().toString();
                mPresenter.getSearchKeyDetail(key);
            }
        });

        //重新加载
        btnRes.setOnClickListener(mListener);
    }


    @Override
    public void setPresenter(SearchKeyDetailPresenter presenter) {
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
        tvDes.setText("加载中..");
        btnRes.setVisibility(View.GONE);
    }

    @Override
    public void emptyView(boolean visible) {
        if(visible){
            tvDes.setVisibility(View.GONE);
            btnRes.setVisibility(View.GONE);
        }else {
            tvDes.setVisibility(View.VISIBLE);
            btnRes.setVisibility(View.VISIBLE);
            tvDes.setText("加载失败");
            btnRes.setText("重新加载");
        }
    }

    @Override
    public void keyDetailLoaded(KeyDetailArray keyDetailArray) {
        if (keyDetailArray != null) {
            mCNWordListAdapter.clear();
            mCnWordByIdListAdapter.clear();
            recyclerViewCnWord.setVisibility(View.VISIBLE);
            recyclerViewCnWord.setAdapter(mCNWordListAdapter);
            mCNWordListAdapter.setData(keyDetailArray.getKeyDetail());
            mCNWordListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void pinYinListLoaded(PinYinArray pinYinArray) {
        if (pinYinArray != null) {
            recyclerView.setVisibility(View.VISIBLE);
            mCnPinYinListAdapter.clear();
            recyclerView.setAdapter(mCnPinYinListAdapter);
            mCnPinYinListAdapter.setData(pinYinArray.getPinyinList());
            mCnPinYinListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void radicalListLoaded(RadicalArray radicalArray) {
        if (radicalArray != null) {
            recyclerView.setVisibility(View.VISIBLE);
            mCnRadicalListAdapter.clear();
            mCnRadicalListAdapter.setData(radicalArray.getRadicalList());
            recyclerView.setAdapter(mCnRadicalListAdapter);
            mCnRadicalListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void keyIdLoaded(KeyArray keyArray) {
        if (keyArray != null) {
            recyclerViewCnWord.setVisibility(View.VISIBLE);
            mCnWordByIdListAdapter.clear();
            mCNWordListAdapter.clear();
            recyclerViewCnWord.setAdapter(mCnWordByIdListAdapter);
            mCnWordByIdListAdapter.setData(keyArray.getKeyList());
            mCnWordByIdListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failedLoadingType(int type,String params) {
        mFailedType = type;
        mParams = params;
    }


    public class MyFailedListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (mFailedType){

                case SearchKeyDetailPresenter.LOADING_DETAIL:
                    mPresenter.getSearchKeyDetail(mParams);
                    break;
                case SearchKeyDetailPresenter.LOADING_RADICAL:
                    mPresenter.getRadicalList();
                    break;
                case SearchKeyDetailPresenter.LOADING_PINYIN:
                    mPresenter.getPinYinList();
                    break;
                case SearchKeyDetailPresenter.LOADING_KEYID_BY_PINYINID:
                    mPresenter.getKeyIdByPinYinId(mParams);
                    break;
                case SearchKeyDetailPresenter.LOADING_KEYID_BY_RADICALID:
                    mPresenter.getKeyIdByRadicalId(mParams);
                    break;

            }

        }
    }
}
