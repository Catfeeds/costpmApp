package com.fudan.cosmosapp.ui.discover;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.base.BaseActivity;
import com.fudan.cosmosapp.bean.CnComsitionDetailbean;
import com.fudan.cosmosapp.bean.EnComsitionDetailbean;
import com.fudan.cosmosapp.httpClient.Api;
import com.fudan.cosmosapp.httpClient.CourseNetwork;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FB_CompositionDetail_Activity extends BaseActivity {

    private String chCompositionId;
    private String enCompositionId;
    private TextView tvDetail;
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chCompositionId = getIntent().getStringExtra("chCompositionId");
        enCompositionId = getIntent().getStringExtra("enCompositionId");

        setContentView(R.layout.fb_activity__composition_detail_);

        initView();

        initData();
    }

    private void initData() {


        if(chCompositionId == null || chCompositionId.equals("")){
            Api api = CourseNetwork.getInstance().getApi(this);
            Observable<EnComsitionDetailbean> observable =
                    api.getEnCompositionDetailById(enCompositionId).compose(CourseNetwork.schedulersTransformer);
            observable.subscribe(new Observer<EnComsitionDetailbean>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(EnComsitionDetailbean enComsitionDetailbean) {
                    if(enComsitionDetailbean.getError() == 1){

                        tvDetail.setText(enComsitionDetailbean.getReason());

                    }else if(enComsitionDetailbean.getError() == 0){
                        EnComsitionDetailbean.EnCompositionDetail detail = enComsitionDetailbean.getEnCompositionDetail().get(0);
                        if(detail == null){
                            Log.e("作文详情","null");
                        }else {

                            Log.e("题目",detail.getENCompositionTitle());
                        }
                        tvDetail.setText(detail.getENCompositionTitle());
                        webView.loadDataWithBaseURL(null,detail.getENCompositionContext(), "text/html", "utf-8", null);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    tvDetail.setText("加载失败");
                }

                @Override
                public void onComplete() {

                }
            });

        }else {
            Api api = CourseNetwork.getInstance().getApi(this);
            Observable<CnComsitionDetailbean> observable =
                    api.getChCompositionDetailById(chCompositionId).compose(CourseNetwork.schedulersTransformer);
            observable.subscribe(new Observer<CnComsitionDetailbean>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(CnComsitionDetailbean cnComsitionDetailbean) {
                    if(cnComsitionDetailbean.getError() == 1){

                        tvDetail.setText(cnComsitionDetailbean.getReason());

                    }else if(cnComsitionDetailbean.getError() == 0){
                        CnComsitionDetailbean.ChCompositionDetail detail = cnComsitionDetailbean.getChCompositionDetail().get(0);
                        tvDetail.setText(detail.getChcompositionTitle());
                        webView.loadDataWithBaseURL(null,detail.getChcompositionContext(), "text/html", "utf-8", null);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    tvDetail.setText("加载失败");
                }

                @Override
                public void onComplete() {

                }
            });

        }


    }

    private void initView() {
        tvDetail = (TextView) findViewById(R.id.tv_detail);
        webView = (WebView) findViewById(R.id.wv_detail);
    }


}
