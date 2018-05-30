package com.fudan.cosmosapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.utils.SPUtils;
import com.fudan.cosmosapp.utils.ThemeUtils;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int theme = SPUtils.getTheme(CosmosApplication.getContext());
        ThemeUtils.setsTheme(theme);
        ThemeUtils.onActivityCreateSetTheme(CosmosApplication.getContext());

    }
}
