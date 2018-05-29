package com.fudan.cosmosapp.adapter.item;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.adapter.IAdapterView;
import com.fudan.cosmosapp.bean.EnWordListArray;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class EnWordQueryListItem extends RelativeLayout implements IAdapterView<EnWordListArray.EnglishDictionaryListBean>{

    private final TextView tvDesWord;
    private final TextView tvResWord;

    public EnWordQueryListItem(Context context) {
        super(context);
        View.inflate(context, R.layout.item_cn_words,this);

        tvDesWord = (TextView) findViewById(R.id.tv_des_word);
        tvResWord = (TextView) findViewById(R.id.tv_res_word);

    }

    @Override
    public void bind(EnWordListArray.EnglishDictionaryListBean item, int position) {
        tvDesWord.setText("英文单词");
        tvResWord.setText(item.getWord());
    }
}
