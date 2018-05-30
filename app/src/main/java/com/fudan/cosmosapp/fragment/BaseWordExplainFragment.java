package com.fudan.cosmosapp.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public abstract class BaseWordExplainFragment extends Fragment{

    public Activity activity;
    public String text;

    public BaseWordExplainFragment(){
        activity = getActivity();
    }

    public void initData(){}

}
