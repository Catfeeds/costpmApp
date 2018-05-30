package com.fudan.cosmosapp.adapter;

import android.content.Context;

import com.fudan.cosmosapp.adapter.item.EnWordQueryListItem;
import com.fudan.cosmosapp.bean.EnWordListArray;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class EnWordQueryAdapter extends ListAdapter<EnWordListArray.EnglishDictionaryListBean,EnWordQueryListItem>  {

    public EnWordQueryAdapter(Context ctx, List<EnWordListArray.EnglishDictionaryListBean> data) {
        super(ctx, data);
    }

    @Override
    protected EnWordQueryListItem createView(Context context) {
        return new EnWordQueryListItem(context);
    }
}
