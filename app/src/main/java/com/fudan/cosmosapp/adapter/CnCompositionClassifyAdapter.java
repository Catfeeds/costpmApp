package com.fudan.cosmosapp.adapter;

import android.content.Context;

import com.fudan.cosmosapp.adapter.item.CnCompositionClassifyBeanItem;
import com.fudan.cosmosapp.bean.CnCompositionClassifyBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class CnCompositionClassifyAdapter extends ListAdapter<CnCompositionClassifyBean.ChCClassificationList,CnCompositionClassifyBeanItem>{

    public CnCompositionClassifyAdapter(Context ctx, List<CnCompositionClassifyBean.ChCClassificationList> data) {
        super(ctx, data);
    }

    @Override
    protected CnCompositionClassifyBeanItem createView(Context context) {
        return new CnCompositionClassifyBeanItem(context);
    }
}
