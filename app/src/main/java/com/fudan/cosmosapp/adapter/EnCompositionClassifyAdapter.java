package com.fudan.cosmosapp.adapter;

import android.content.Context;

import com.fudan.cosmosapp.adapter.item.CnCompositionClassifyBeanItem;
import com.fudan.cosmosapp.adapter.item.EnCompositionClassifyBeanItem;
import com.fudan.cosmosapp.bean.EnCompositionClassifyBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class EnCompositionClassifyAdapter extends ListAdapter<EnCompositionClassifyBean.EnCClassificationList,EnCompositionClassifyBeanItem>{

    public EnCompositionClassifyAdapter(Context ctx, List<EnCompositionClassifyBean.EnCClassificationList> data) {
        super(ctx, data);
    }

    @Override
    protected EnCompositionClassifyBeanItem createView(Context context) {
        return new EnCompositionClassifyBeanItem(context);
    }
}
