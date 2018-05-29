package com.fudan.cosmosapp.adapter;

import android.content.Context;

import com.fudan.cosmosapp.adapter.item.CNRadicalListItem;
import com.fudan.cosmosapp.bean.RadicalArray;

import java.util.List;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class CNRadicalListAdapter extends ListAdapter<RadicalArray.RadicalList,CNRadicalListItem>{

    public CNRadicalListAdapter(Context ctx, List<RadicalArray.RadicalList> data) {
        super(ctx, data);
    }

    @Override
    protected CNRadicalListItem createView(Context context) {
        return new CNRadicalListItem(context);
    }
}
