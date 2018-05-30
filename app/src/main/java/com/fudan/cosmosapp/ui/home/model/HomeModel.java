package com.fudan.cosmosapp.ui.home.model;

import com.fudan.cosmosapp.bean.NewsBean;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public interface HomeModel {

    Observable<NewsBean> getListData(int page);

}
