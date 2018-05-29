package com.fudan.cosmosapp.ui.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.base.BaseActivity;
import com.fudan.cosmosapp.event.ThemeEvent;
import com.fudan.cosmosapp.utils.SPUtils;
import com.fudan.cosmosapp.utils.ThemeUtils;
import com.fudan.cosmosapp.utils.ToastUtils;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class ThemeActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llBlack;
    private LinearLayout llGreen;
    private LinearLayout llBlue;

    private ImageView whiteIndicator;
    private ImageView redIndicator;
    private ImageView blueIndicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_theme);

        llBlack = (LinearLayout) findViewById(R.id.ll_black);
        llGreen = (LinearLayout) findViewById(R.id.ll_green);
        llBlue = (LinearLayout) findViewById(R.id.ll_blue);

        whiteIndicator = (ImageView) findViewById(R.id.indicator_white);
        redIndicator = (ImageView) findViewById(R.id.indicator_red);
        blueIndicator = (ImageView) findViewById(R.id.indicator_blue);

        llBlack.setOnClickListener(this);
        llGreen.setOnClickListener(this);
        llBlue.setOnClickListener(this);

        initTheme();
    }

    private void initTheme() {
        int theme = SPUtils.getTheme(this);
        switch (theme) {
            case 1:
                whiteIndicator.setVisibility(View.VISIBLE);
                redIndicator.setVisibility(View.GONE);
                blueIndicator.setVisibility(View.GONE);

                break;
            case 2:
                whiteIndicator.setVisibility(View.GONE);
                redIndicator.setVisibility(View.VISIBLE);
                blueIndicator.setVisibility(View.GONE);

                break;
            case 3:
                whiteIndicator.setVisibility(View.GONE);
                redIndicator.setVisibility(View.GONE);
                blueIndicator.setVisibility(View.VISIBLE);

                break;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ll_green:
                setSpTheme(1);
                break;
            case R.id.ll_black:
                setSpTheme(2);
                break;
            case R.id.ll_blue:
                setSpTheme(3);
                break;
        }
    }

    public void setSpTheme(int theme) {

        if (theme == SPUtils.getTheme(this)) {
            ToastUtils.showToast(this, "正在使用该主题");
            return;
        }

        SPUtils.setTheme(this, theme);
        //发送消息 更新主题
        EventBus.getDefault().post(new ThemeEvent(theme));
        ThemeUtils.changeToTheme(this, theme);
    }
}
