package com.fudan.cosmosapp.adapter.item;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.adapter.IAdapterView;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.EnCompositionTitleArray;
import com.fudan.cosmosapp.ui.discover.FB_CompositionDetail_Activity;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class EnCompositionListItem extends RelativeLayout implements IAdapterView<EnCompositionTitleArray.EnCompositionList> {

    private final TextView tvTitle;
    private final Button btnEnter;
    private Context context;


    public EnCompositionListItem(Context context) {
        super(context);
        this.context = context;

        View view = View.inflate(CosmosApplication.getContext(), R.layout.item_cn_composition,this);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        btnEnter = (Button) view.findViewById(R.id.btn_enter);

    }

    @Override
    public void bind(final EnCompositionTitleArray.EnCompositionList item, int position) {
        tvTitle.setText(item.getENCompositionTitle());

        btnEnter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,FB_CompositionDetail_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("enCompositionId",item.getENCompositionId());
                context.startActivity(intent);
            }
        });


    }
}
