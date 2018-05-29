package com.fudan.cosmosapp.ui.discover;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.adapter.WordExplainPageAdapter;
import com.fudan.cosmosapp.base.BaseActivity;
import com.fudan.cosmosapp.fragment.BaseWordExplainFragment;
import com.fudan.cosmosapp.fragment.CnWordQueryFragment;
import com.fudan.cosmosapp.fragment.EgWordQueryFragment;
import com.fudan.cosmosapp.utils.TextUtils;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

public class FB_WordExplainActivity extends BaseActivity {

    private String word;
    private TabPageIndicator indicator;
    private ViewPager viewPager;

    private ArrayList<BaseWordExplainFragment> fragments;

    private int mCurrentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        word = getIntent().getStringExtra("hot_words");

        setContentView(R.layout.fb_activity__word_explain);

        initData();

        initView();
    }

    private void initData() {

        fragments = new ArrayList<>();

        int state = TextUtils.judgeTextIsChineseOREnglish(word);
        if( state == TextUtils.OTHER || state == TextUtils.EG){

            mCurrentPage = 0;
            fragments.add(EgWordQueryFragment.newInstance(word));
            fragments.add(new CnWordQueryFragment());
        } else if(state == TextUtils.CN){

            mCurrentPage = 1;
            fragments.add(new EgWordQueryFragment());
            fragments.add(CnWordQueryFragment.newInstance(word));
        }

    }

    private void initView() {

        indicator = (TabPageIndicator) findViewById(R.id.indicator);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.setAdapter(new WordExplainPageAdapter(fragments,getSupportFragmentManager()));
        indicator.setViewPager(viewPager);//绑定viewPager

        indicator.setCurrentItem(mCurrentPage);

    }

    //APP ID: 20170804000070647    密钥: 0fFzr4NEqNi1g6Ylbf4F
}
