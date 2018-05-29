package com.fudan.cosmosapp.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import com.fudan.cosmosapp.fragment.BaseWordExplainFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class WordExplainPageAdapter extends BaseViewPagerAdapter<BaseWordExplainFragment> {

    private String[] mTabName = {"英语词典查询","汉语词典查询"};

    public WordExplainPageAdapter(ArrayList<BaseWordExplainFragment> viewlist, FragmentManager fragmentManager) {
        super(viewlist, fragmentManager);
    }

    @Override
    public int getCount() {
        return mTabName.length;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        BaseWordExplainFragment fragment = viewlist.get(position);

        // 如果fragment还没有added
        if(!fragment.isAdded()){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(fragment,fragment.getClass().getSimpleName());
            ft.commit();
            /**
             * 在用FragmentTransaction.commit()方法提交FragmentTransaction对象后
             * 会在进程的主线程中，用异步的方式来执行。
             * 如果想要立即执行这个等待中的操作，就要调用这个方法（只能在主线程中调用）。
             * 要注意的是，所有的回调和相关的行为都会在这个调用中被执行完成
             * 因此要仔细确认这个方法的调用位置。
             */

            fragmentManager.executePendingTransactions();
        }

        if(fragment.getView().getParent() == null){
            container.addView(fragment.getView());
        }

        return fragment.getView();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabName[position];
    }
}
