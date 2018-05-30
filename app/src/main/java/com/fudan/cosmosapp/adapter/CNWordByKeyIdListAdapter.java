package com.fudan.cosmosapp.adapter;

import android.content.Context;

import com.fudan.cosmosapp.adapter.item.CNWordByKeyIdListItem;
import com.fudan.cosmosapp.bean.KeyArray;

import java.util.List;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class CNWordByKeyIdListAdapter extends ListAdapter<KeyArray.KeyList, CNWordByKeyIdListItem>{

    public CNWordByKeyIdListAdapter(Context ctx, List<KeyArray.KeyList> data) {
        super(ctx, data);
    }

    @Override
    protected CNWordByKeyIdListItem createView(Context context) {
        return new CNWordByKeyIdListItem(context);
    }
}
