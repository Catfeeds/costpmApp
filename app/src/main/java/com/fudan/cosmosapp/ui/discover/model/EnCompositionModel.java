package com.fudan.cosmosapp.ui.discover.model;

import com.fudan.cosmosapp.bean.EnCompositionTitleArray;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public interface EnCompositionModel {

    Observable<EnCompositionTitleArray> getEnCompositionTitleListByTitle(String title);

}
