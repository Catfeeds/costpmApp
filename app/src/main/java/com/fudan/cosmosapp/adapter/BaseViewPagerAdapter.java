package com.fudan.cosmosapp.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public abstract class BaseViewPagerAdapter<T> extends PagerAdapter {

    public ArrayList<T> viewlist;
    public FragmentManager fragmentManager;

    public BaseViewPagerAdapter(ArrayList<T> viewlist, FragmentManager fragmentManager){
        this.viewlist = viewlist;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getCount() {
        return viewlist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //只需重写此方法
        return getView(container,position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    public abstract View getView(ViewGroup container, int position);
}
