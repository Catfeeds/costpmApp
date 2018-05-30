package com.fudan.cosmosapp.adapter.item;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.adapter.IAdapterView;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.KeyArray;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class CNWordByKeyIdListItem extends RelativeLayout implements IAdapterView<KeyArray.KeyList> {

    private final TextView tvDesWord;
    private final TextView tvResWord;

    public CNWordByKeyIdListItem(Context context) {
        super(context);
        View view = View.inflate(CosmosApplication.getContext(), R.layout.item_cn_words,this);
        tvDesWord = (TextView) view.findViewById(R.id.tv_des_word);
        tvResWord = (TextView) view.findViewById(R.id.tv_res_word);
    }

    @Override
    public void bind(KeyArray.KeyList item, int position) {
        tvDesWord.setText("汉字");

        if(item.getKey() == null||item.getKey().equals("")){
            tvResWord.setText(item.getKeyName());
        }else {
            tvResWord.setText(item.getKey());
        }
    }
}
