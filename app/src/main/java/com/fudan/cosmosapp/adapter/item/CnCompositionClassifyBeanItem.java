package com.fudan.cosmosapp.adapter.item;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.adapter.IAdapterView;
import com.fudan.cosmosapp.bean.CnCompositionClassifyBean;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class CnCompositionClassifyBeanItem extends RelativeLayout implements IAdapterView<CnCompositionClassifyBean.ChCClassificationList> {

    private final TextView tvClassify;

    public CnCompositionClassifyBeanItem(Context context) {
        super(context);
        View view = View.inflate(context, R.layout.item_composition_classify,this);
        tvClassify = (TextView) view.findViewById(R.id.tv_classify);
    }

    @Override
    public void bind(CnCompositionClassifyBean.ChCClassificationList item, int position) {
        tvClassify.setText(item.getCHCompositionClassification());
        tvClassify.setTextColor(Color.BLACK);
    }
}
