package com.fudan.cosmosapp.fragment;

/**
 * Created by yinxiaofei on 2017/7/2.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.base.BaseFragment;
import com.fudan.cosmosapp.ui.discover.FB_Composition_Activity;
import com.fudan.cosmosapp.ui.discover.FB_EnglishComposition_Activity;
import com.fudan.cosmosapp.ui.discover.FB_WordLookUpActivity;

/**
 * Created by lt on 2015/12/1.
 */
public class DiscoverFragment extends BaseFragment {

    private View discoverpage;

    private LinearLayout mLayoutChineseComposition;
    private LinearLayout mLayoutWordSearch;
    private LinearLayout mLayoutEnglishComposition;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        discoverpage = inflater.inflate(R.layout.fb_discover_fragment, container, false);

        //以下为所有LinearLayout和他们的点击事件
        mLayoutChineseComposition = (LinearLayout) discoverpage.findViewById(R.id.ll_chinese_composition_discover);
        mLayoutWordSearch = (LinearLayout) discoverpage.findViewById(R.id.ll_word_lookup_discover);
        mLayoutEnglishComposition = (LinearLayout) discoverpage.findViewById(R.id.ll_english_composition_discover);

        //中文作文 点击事件
        mLayoutChineseComposition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FB_Composition_Activity.class);
                startActivity(intent);
            }
        });

        //英语作文 点击事件
        mLayoutEnglishComposition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getActivity(), FB_EnglishComposition_Activity.class);
                startActivity(mIntent);
            }
        });

        //单词搜索 点击事件
        mLayoutWordSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mWordLookUpIntent = new Intent(getActivity(), FB_WordLookUpActivity.class);
                startActivity(mWordLookUpIntent);
            }
        });

        return discoverpage;
    }
}