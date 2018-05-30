package com.fudan.cosmosapp.adapter;

import android.content.Context;

import com.fudan.cosmosapp.adapter.item.CNPinYinListItem;
import com.fudan.cosmosapp.bean.PinYinArray;

import java.util.List;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class CNPinYinListAdapter extends ListAdapter<PinYinArray.PinyinList, CNPinYinListItem> {

    public CNPinYinListAdapter(Context ctx, List<PinYinArray.PinyinList> data) {
        super(ctx, data);
    }

    @Override
    protected CNPinYinListItem createView(Context context) {
        return new CNPinYinListItem(context);
    }
}
