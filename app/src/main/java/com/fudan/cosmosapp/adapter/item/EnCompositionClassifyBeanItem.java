package com.fudan.cosmosapp.adapter.item;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.adapter.IAdapterView;
import com.fudan.cosmosapp.bean.EnCompositionClassifyBean;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class EnCompositionClassifyBeanItem extends RelativeLayout implements IAdapterView<EnCompositionClassifyBean.EnCClassificationList> {

    private final TextView tvClassify;

    public EnCompositionClassifyBeanItem(Context context) {
        super(context);
        View view = View.inflate(context, R.layout.item_composition_classify,this);
        tvClassify = (TextView) view.findViewById(R.id.tv_classify);
    }

    @Override
    public void bind(EnCompositionClassifyBean.EnCClassificationList item, int position) {
        tvClassify.setText(item.getENCompositionClassification());
        tvClassify.setTextColor(Color.BLACK);
    }
}
