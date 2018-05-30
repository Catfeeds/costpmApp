package com.fudan.cosmosapp.ui.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.base.BaseActivity;
import com.fudan.cosmosapp.bean.EnWordDetailBean;
import com.fudan.cosmosapp.bean.KeyDetailArray;
import com.fudan.cosmosapp.httpClient.Api;
import com.fudan.cosmosapp.httpClient.CourseNetwork;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class FB_wordDetailActivity extends BaseActivity {


    private String cnKeyId;
    private String enKeyId;
    private TextView tvDetail;
    private WebView webView;
    private TextView tvEnDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cnKeyId = getIntent().getStringExtra("cn_keyId");
        enKeyId = getIntent().getStringExtra("en_keyId");

        setContentView(R.layout.activity_worddetail);

        tvDetail = (TextView) findViewById(R.id.tv_detail);
        webView = (WebView) findViewById(R.id.wv_detail);
        tvEnDetail = (TextView) findViewById(R.id.tv_enDetail);

        initView();
    }

    private void initView() {
        Api api = CourseNetwork.getInstance().getApi(this);
        if(cnKeyId != null){

            Observable<KeyDetailArray> keyDetailByKeyId =
                    api.getKeyDetailByKeyId(cnKeyId).compose(CourseNetwork.schedulersTransformer);
            keyDetailByKeyId.subscribe(new Observer<KeyDetailArray>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(KeyDetailArray keyDetailArray) {
                    KeyDetailArray.KeyDetail keyDetail = keyDetailArray.getKeyDetail().get(0);
                    if(keyDetailArray.getError() == 0){
                        tvDetail.setText(
                                "汉字:"+keyDetail.getKeyName()+
                                "   偏旁"+keyDetail.getRadical()+
                                "   拼音"+keyDetail.getPinyin()
                        );
                        webView.loadDataWithBaseURL(null,keyDetail.getDetailMeaning(), "text/html", "utf-8", null);
                    }else {
                        tvDetail.setText(keyDetailArray.getReason());
                    }
                }

                @Override
                public void onError(Throwable e) {
                    tvDetail.setText(e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });
        }else {
            Observable<EnWordDetailBean> observable = api.getEnWordDetailById(enKeyId).compose(CourseNetwork.schedulersTransformer);
            observable.subscribe(new Observer<EnWordDetailBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                    tvEnDetail.setVisibility(View.VISIBLE);
                    tvDetail.setVisibility(View.GONE);
                    webView.setVisibility(View.GONE);
                }

                @Override
                public void onNext(EnWordDetailBean enWordDetailBean) {
                    if(enWordDetailBean.getError() == 0){
                        dealWithWord(enWordDetailBean);
                    } else if (enWordDetailBean.getError() == 1){
                        tvEnDetail.setText("暂无数据");
                    }
                }

                @Override
                public void onError(Throwable e) {
                    tvEnDetail.setText("加载失败");
                }

                @Override
                public void onComplete() {

                }
            });


        }

    }

    private void dealWithWord(EnWordDetailBean enWordDetailBean) {

        for(EnWordDetailBean.EnglishDictionaryDetailBean bean:enWordDetailBean.getEnglishDictionaryDetail()){

            if(bean.getEnglishPhonogram() != null && !bean.getEnglishPhonogram().equals("")){
                tvEnDetail.append("英式发音:"+bean.getEnglishPhonogram()+"\n");
            }

            if(bean.getAmericanPhonogram() != null && !bean.getAmericanPhonogram().equals("")){
                tvEnDetail.append("美式发音:"+bean.getAmericanPhonogram()+"\n");
            }

            if(bean.getWord() != null && !bean.getWord().equals("")){
                tvEnDetail.append("单词:"+bean.getWord()+"\n");
            }

            if(bean.getWordTranslation() != null && !bean.getWordTranslation().equals("")){
                tvEnDetail.append("中文意义:"+bean.getWordTranslation()+"\n");
            }

            if(bean.getExampleSentences() != null && !bean.getExampleSentences().equals("")){
                tvEnDetail.append("例句:"+bean.getExampleSentences()+"\n");
            }

            if(bean.getSentenceTranslation() != null && !bean.getSentenceTranslation().equals("")){
                tvEnDetail.append("例句翻译:"+bean.getSentenceTranslation()+"\n");
            }

            if(bean.getNominal() != null && !bean.getNominal().equals("")){
                tvEnDetail.append("词性:"+bean.getNominal());
            }
        }

    }

    @Override
    protected void onDestroy() {

        webView.removeAllViews();
        webView.destroy();
        super.onDestroy();
    }
}
