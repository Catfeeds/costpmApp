package com.fudan.cosmosapp.ui.home.model.Imple;

import com.fudan.cosmosapp.bean.NewsBean;
import com.fudan.cosmosapp.fragment.HomeFragment;
import com.fudan.cosmosapp.httpClient.CourseNetwork;
import com.fudan.cosmosapp.ui.home.model.HomeModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class HomeModelImple implements HomeModel {

    @Override
    public Observable<NewsBean> getListData(int page) {

        //获取网络数据 没有api
//        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());
//        api.getGrades();
        page = page % 2;
        return Observable.just(getData(page)).compose(CourseNetwork.schedulersTransformer);
    }

    public NewsBean getData(int num){

        List<String> strings;
        List<String> contents;
        if(num == 0){
            strings = new ArrayList<String>(Arrays.asList(HomeFragment.titel));
            contents = new ArrayList<String>(Arrays.asList(HomeFragment.content));
        }else {
            strings = new ArrayList<String>(Arrays.asList(HomeFragment.titel));
            contents = new ArrayList<String>(Arrays.asList(HomeFragment.content));
        }
        NewsBean newsBean = new NewsBean();
        newsBean.setTitle(strings);
        newsBean.setContent(contents);

        return newsBean;
    }
}
