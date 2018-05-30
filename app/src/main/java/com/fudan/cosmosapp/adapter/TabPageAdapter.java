package com.fudan.cosmosapp.adapter;

/**
 * Created by yinxiaofei on 2017/7/2.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import java.util.List;

/**
 *
 * app导航内容区域适配器
 */
public class TabPageAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragments;
    public TabPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * 重写，不让Fragment销毁
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}