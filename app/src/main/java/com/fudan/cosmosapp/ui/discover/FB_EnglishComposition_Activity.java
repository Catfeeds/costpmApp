package com.fudan.cosmosapp.ui.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.adapter.EnCompositionPageAdapter;
import com.fudan.cosmosapp.base.BaseActivity;
import com.fudan.cosmosapp.fragment.BaseWordExplainFragment;
import com.fudan.cosmosapp.fragment.EnCompositionClassifyFragment;
import com.fudan.cosmosapp.fragment.EnCompositionQueryFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

public class FB_EnglishComposition_Activity extends BaseActivity {
    private TabPageIndicator indicator;
    private ViewPager viewPager;

    private ArrayList<BaseWordExplainFragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fb_activity__composition_);

        initData();

        initView();

    }

    private void initData() {

        fragments = new ArrayList<>();

        fragments.add(new EnCompositionQueryFragment());
        fragments.add(new EnCompositionClassifyFragment());

    }

    private void initView() {

        indicator = (TabPageIndicator) findViewById(R.id.indicator);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.setAdapter(new EnCompositionPageAdapter(fragments,getSupportFragmentManager()));
        indicator.setViewPager(viewPager);//绑定viewPager

    }
}
