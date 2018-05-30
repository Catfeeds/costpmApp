package com.fudan.cosmosapp.httpClient;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class CacheInterceptor implements Interceptor {

    private Context context;

    CacheInterceptor(Context context){
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        if(NetworkUtil.isNetworkAvailable(context)){
            Response response = chain.proceed(request);
            //打印何种cacheControl方式
            String cacheControl = request.cacheControl().toString();
            Log.e("CacheInterceptor", "6s load cahe" + cacheControl);

            int maxAge = 60*60;//读取缓存时间

            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control","public, max-age=" + maxAge)
                    .build();
        }else {

            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();

            Response response = chain.proceed(request);
            //设置缓存时间3天
            int maxStale = 60 * 60 * 24 * 3;
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }

    }
}
