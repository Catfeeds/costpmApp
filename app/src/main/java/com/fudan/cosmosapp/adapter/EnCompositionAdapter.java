package com.fudan.cosmosapp.adapter;

import android.content.Context;

import com.fudan.cosmosapp.adapter.item.EnCompositionListItem;
import com.fudan.cosmosapp.bean.EnCompositionTitleArray;

import java.util.List;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class EnCompositionAdapter extends ListAdapter<EnCompositionTitleArray.EnCompositionList, EnCompositionListItem> {

    public EnCompositionAdapter(Context ctx, List<EnCompositionTitleArray.EnCompositionList> data) {
        super(ctx, data);
    }

    @Override
    protected EnCompositionListItem createView(Context context) {
        return new EnCompositionListItem(context);
    }
}
