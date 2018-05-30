package com.fudan.cosmosapp.ui.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.adapter.CnCompositionPageAdapter;
import com.fudan.cosmosapp.base.BaseActivity;
import com.fudan.cosmosapp.fragment.BaseWordExplainFragment;
import com.fudan.cosmosapp.fragment.CnCompositionClassifyFragment;
import com.fudan.cosmosapp.fragment.CnCompositionQueryFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

public class FB_Composition_Activity extends BaseActivity {

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

        fragments.add(new CnCompositionQueryFragment());
        fragments.add(new CnCompositionClassifyFragment());

    }

    private void initView() {

        indicator = (TabPageIndicator) findViewById(R.id.indicator);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.setAdapter(new CnCompositionPageAdapter(fragments,getSupportFragmentManager()));
        indicator.setViewPager(viewPager);//绑定viewPager

    }
}
