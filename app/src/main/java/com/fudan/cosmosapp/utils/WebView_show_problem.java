package com.fudan.cosmosapp.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.base.BaseActivity;


/**
 * Created by yinxiaofei on 2017/7/30.
 */

public class WebView_show_problem extends BaseActivity {
    WebView webView =null;
    ProgressBar pb_webView=null;
    private String  searchString="沿虚线折叠后能围成正方体的有";

    private Intent  startIntent=null;
    private String  ansContextHtml;
    private String  questionId;
    private String  questionContext=null;
    @RequiresApi(api = 26)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);

        Bundle bundle=this.getIntent().getExtras();
        searchString= bundle.getString("solution");//startIntent.getStringExtra("solution");
        //Toast.makeText(this,searchString+"yinxiaofei",Toast.LENGTH_SHORT).show();


        webView = (WebView) findViewById(R.id.webView_show_news);
        pb_webView=(ProgressBar) findViewById(R.id.pb_webView);
        pb_webView.setVisibility(View.VISIBLE);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //webView.loadUrl("http://baidu.com");//http://1b7933z266.imwork.net:11951/nrms-web/searchQuestions/questionListByESJson?searchKey=沿虚线折叠后能围成正方体的有
        //questionContext = "<span class=\\\"\\\"></span>下列图形中，沿虚线折叠后能围成正方体的有<br><div align=\\\"right\\\">[ ]</div> <div class=\\\"option-item\\\">       <div>A、<img style=\\\"VERTICAL-ALIGN: middle\\\" src=\\\"http://tikucommon-zs.oss-cn-beijing.aliyuncs.com/tiku/source/upimg/pic1/upload/papers/x02/20101109/201011091119285001083.gif\\\"></div>    </div>    <div class=\\\"option-item\\\">        <div>B、<img style=\\\"VERTICAL-ALIGN: middle\\\" src=\\\"http://tikucommon-zs.oss-cn-beijing.aliyuncs.com/tiku/source/upimg/pic1/upload/papers/x02/20101109/201011091119429841156.gif\\\"></div>    </div>    <div class=\\\"option-item\\\">        <div>C、<img style=\\\"VERTICAL-ALIGN: middle\\\" src=\\\"http://tikucommon-zs.oss-cn-beijing.aliyuncs.com/tiku/source/upimg/pic1/upload/papers/x02/20101109/201011091119514841141.gif\\\"></div>    </div>    <div class=\\\"option-item\\\">        <div>D、<img style=\\\"VERTICAL-ALIGN: middle\\\" src=\\\"http://tikucommon-zs.oss-cn-beijing.aliyuncs.com/tiku/source/upimg/pic1/upload/papers/x02/20101109/201011091120005461137.gif\\\">E．<img style=\\\"VERTICAL-ALIGN: middle\\\" src=\\\"http://tikucommon-zs.oss-cn-beijing.aliyuncs.com/tiku/source/upimg/pic1/upload/papers/x02/20101109/201011091120091091176.gif\\\"></div>    </div>";

        webView.loadDataWithBaseURL(null,questionContext,"text/html", "utf-8", null);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成
                    pb_webView.setVisibility(View.GONE);

                } else {
                    // 加载中
                    pb_webView.setProgress(newProgress);
                }

            }
        });
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(webView.canGoBack())
            {
                webView.goBack();//返回上一页面
                return true;
            }
            else
            {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }



}
