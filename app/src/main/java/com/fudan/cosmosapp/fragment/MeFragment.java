package com.fudan.cosmosapp.fragment;

/**
 * Created by yinxiaofei on 2017/7/2.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.base.BaseFragment;
import com.fudan.cosmosapp.ui.TestActivity;
import com.fudan.cosmosapp.ui.me.ThemeActivity;

/**
 * Created by lt on 2015/12/1.
 */
public class MeFragment extends BaseFragment {

    private View   MePageView;
    private Button test;
    private LinearLayout llTheme;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MePageView= inflater.inflate(R.layout.main_me_fragment,container,false);

        llTheme = (LinearLayout) MePageView.findViewById(R.id.ll_theme);

        initView();

        return MePageView;
    }

    private void initView() {

        llTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CosmosApplication.getContext(),ThemeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                CosmosApplication.getContext().startActivity(intent);

            }
        });

        test = (Button) MePageView.findViewById(R.id.test);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CosmosApplication.getContext(),TestActivity.class));
            }
        });

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}