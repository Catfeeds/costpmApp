package com.fudan.cosmosapp.ui.discover;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.base.BaseActivity;
import com.fudan.cosmosapp.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FB_WordLookUpActivity extends BaseActivity {

    private ImageButton mWordLookUpButton;
    private GridView mHotWordsGridView;

    private List<Map<String, Object>> mapList;
    private Map<String, Object> map;
    private String[] hotWords = {"outlook", "wonderful", "interesting", "beautiful", "belong", "achieve"};
    private EditText etWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fb_activity__word_look_up);

        initView();


    }

    private void initView() {

        etWord = (EditText) findViewById(R.id.et_word);

        //获取搜索按钮，设置点击事件
        mWordLookUpButton = (ImageButton) findViewById(R.id.word_search_btn);
        mWordLookUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startQuery();
            }
        });

        //获取gridview,适配数据
        String[] from = {"hot_words"};
        int[] to = {R.id.hot_words};
        mHotWordsGridView = (GridView) findViewById(R.id.hot_words_gridview);
        mHotWordsGridView.setAdapter(new SimpleAdapter(
                this, getData(), R.layout.fb_hotwords_gridview, from, to
        ));

        //设置item点击事件
        mHotWordsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap item = (HashMap) adapterView.getItemAtPosition(i);
                String word = String.valueOf(item.get("hot_words").toString());

                if(word != null){
                    etWord.setText(word);
                }

                mHotWordsGridView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startQuery();
                    }
                }, 200);

            }
        });
    }

    private void startQuery() {
        if(etWord.getText().equals("")){
            //提示
            ToastUtils.showToast(CosmosApplication.getContext(),"输入为空，请重输");
            return;
        }
        String word = etWord.getText().toString().trim();

        Intent mWordIntent = new Intent(FB_WordLookUpActivity.this, FB_WordExplainActivity.class);
        mWordIntent.putExtra("hot_words", word);
        startActivity(mWordIntent);
    }

    private List<Map<String, Object>> getData() {
        mapList = new ArrayList<>();
        for (int i = 0; i < hotWords.length; i++) {
            map = new HashMap<>();
            map.put("hot_words", hotWords[i]);
            mapList.add(map);
        }
        return mapList;
    }
}
