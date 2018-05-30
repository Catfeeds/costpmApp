package com.fudan.cosmosapp.adapter;

import android.content.Context;

import com.fudan.cosmosapp.adapter.item.CNWordListItem;
import com.fudan.cosmosapp.bean.KeyDetailArray;

import java.util.List;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class CNWordListAdapter extends ListAdapter<KeyDetailArray.KeyDetail, CNWordListItem>{

    public CNWordListAdapter(Context ctx, List<KeyDetailArray.KeyDetail> data) {
        super(ctx, data);
    }

    @Override
    protected CNWordListItem createView(Context context) {
        return new CNWordListItem(context);
    }
}
