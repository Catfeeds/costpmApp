package com.fudan.cosmosapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fudan.cosmosapp.event.ThemeEvent;
import com.fudan.cosmosapp.utils.SPUtils;
import com.fudan.cosmosapp.utils.ThemeUtils;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置主题
        int theme = SPUtils.getTheme(this);
        ThemeUtils.setsTheme(theme);
        ThemeUtils.onActivityCreateSetTheme(this);

        Log.e(this.getClass().getSimpleName(),"onCreate");

        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 接受消息更换主题
     * @param theme
     */
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onTheme(ThemeEvent theme) {
        ThemeUtils.changeToTheme(this,theme.getTheme());
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        Log.e(this.getClass().getSimpleName(),"onDestroy");
        super.onDestroy();
    }
}
