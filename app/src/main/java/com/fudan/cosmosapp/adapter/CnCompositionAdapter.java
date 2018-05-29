package com.fudan.cosmosapp.adapter;

import android.content.Context;

import com.fudan.cosmosapp.adapter.item.CnCompositionListItem;
import com.fudan.cosmosapp.bean.ChCompositionTitleArray;

import java.util.List;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class CnCompositionAdapter extends ListAdapter<ChCompositionTitleArray.ChCompositionList, CnCompositionListItem> {

    public CnCompositionAdapter(Context ctx, List<ChCompositionTitleArray.ChCompositionList> data) {
        super(ctx, data);
    }

    @Override
    protected CnCompositionListItem createView(Context context) {
        return new CnCompositionListItem(context);
    }
}
