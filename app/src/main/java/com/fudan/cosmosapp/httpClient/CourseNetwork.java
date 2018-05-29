package com.fudan.cosmosapp.httpClient;


import android.content.Context;
import android.util.Log;

import com.fudan.cosmosapp.app.Constant;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ang on 2017/8/9.
 */

public class CourseNetwork {
    private static final long DEFAULT_TIMEOUT = 20;

    private static volatile CourseNetwork mInstance;
    private Api mApi;

    private Cache cache = null;
    private File httpCacheDirectory;

    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;

    private CourseNetwork() {

    }

    public static CourseNetwork getInstance() {
        if (mInstance == null) {
            synchronized (CourseNetwork.class) {
                if (mInstance == null) {
                    mInstance = new CourseNetwork();
                }
            }
        }
        return mInstance;
    }

    public Api getApi(Context context) {

        //缓存地址
        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(context.getCacheDir(), "app_cache");
        }

        //创建缓存
        try {
            //缓存目录 缓存文件大小10M
            cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
        } catch (Exception e) {
            Log.e("OKHttp", "Could not create http cache", e);
        }

        if (mApi == null) {
            synchronized (CourseNetwork.class) {
                if (mApi == null) {

                    //创建okhttp 便于打印log
                    //缓存
                    okHttpClient = new OkHttpClient.Builder()
                            .addNetworkInterceptor(
                                    new HttpLoggingInterceptor()
                                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                            )
                            //缓存
                            .cache(cache)
                            .retryOnConnectionFailure(true)//失败重连
                            .addInterceptor(new CacheInterceptor(context))
                            .addNetworkInterceptor(new CacheInterceptor(context))
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .build();

                    //使用自定义的mGsonConverterFactory
                    retrofit = new Retrofit.Builder()
                            .client(okHttpClient)
                            //使用自定义的mGsonConverterFactory
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .baseUrl(Constant.BASEURL)
                            .build();

                    mApi = retrofit.create(Api.class);
                }
            }
        }

        return mApi;

    }

    //处理线程调度的变换
    public static ObservableTransformer schedulersTransformer = new ObservableTransformer() {
        @Override
        public ObservableSource apply(Observable upstream) {
            return ((Observable) upstream).subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    //处理错误的变换
    private static class ErrorTransformer<T> implements ObservableTransformer{

        @Override
        public ObservableSource apply(Observable upstream) {
            //onErrorResumeNext当发生错误的时候，由另外一个Observable来代替当前的Observable并继续发射数据
            return (Observable<T>) upstream.map(new HandleFuc<T>()).onErrorResumeNext(new HttpResponseFunc<T>());
        }
    }


    public static class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
        @Override
        public Observable<T> apply(Throwable throwable) throws Exception {
            return Observable.error(ExceptionHandle.handleException(throwable));
        }
    }

    public static class HandleFuc<T> implements Function<BaseResponse<T>, T> {
        @Override
        public T apply(BaseResponse<T> response) throws Exception {
            //response中code码不会0 出现错误
            if (!response.isOk())
                throw new RuntimeException(response.getCode() + "" + response.getMsg() != null ? response.getMsg() : "");
            return response.getData();
        }
    }
}
