package com.fudan.cosmosapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.app.Constant;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.base.BaseActivity;
import com.fudan.cosmosapp.utils.SPUtils;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);

        final EditText etTest = (EditText) findViewById(R.id.et_test);
        Button btnSure = (Button) findViewById(R.id.btn_sure);
        etTest.setText(Constant.BASEURL);

        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String host = etTest.getText().toString();
                Constant.BASEURL = host;
                etTest.setText(Constant.BASEURL);
                SPUtils.setSharedStringData(CosmosApplication.getContext(),Constant.IP,host);
            }
        });
    }
}
