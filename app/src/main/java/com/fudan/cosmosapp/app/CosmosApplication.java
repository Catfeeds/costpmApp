package com.fudan.cosmosapp.app;

import android.app.Application;
import android.content.Context;

import com.fudan.cosmosapp.utils.SPUtils;
import com.fudan.cosmosapp.utils.ThemeUtils;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class CosmosApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        int theme = SPUtils.getTheme(this);
        ThemeUtils.setsTheme(theme);
        ThemeUtils.onActivityCreateSetTheme(this);
    }

    public static Context getContext(){
        return context;
    }

}
