package com.fudan.cosmosapp.adapter.item;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.adapter.IAdapterView;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.RadicalArray;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class CNRadicalListItem extends RelativeLayout implements IAdapterView<RadicalArray.RadicalList>{

    private final TextView tvDesWord;
    private final TextView tvResWord;

    public CNRadicalListItem(Context context) {
        super(context);
        View view =  View.inflate(CosmosApplication.getContext(), R.layout.item_cn_words,this);
        tvDesWord = (TextView) view.findViewById(R.id.tv_des_word);
        tvResWord = (TextView) view.findViewById(R.id.tv_res_word);

    }

    @Override
    public void bind(RadicalArray.RadicalList item, int position) {
        tvDesWord.setText("偏旁");
        tvResWord.setText(item.getRadical());
    }
}
