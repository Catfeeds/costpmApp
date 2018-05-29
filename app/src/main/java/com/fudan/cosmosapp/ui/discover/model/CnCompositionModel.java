package com.fudan.cosmosapp.ui.discover.model;

import com.fudan.cosmosapp.bean.ChCompositionTitleArray;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public interface CnCompositionModel {

    Observable<ChCompositionTitleArray> getEnCompositionTitleListByTitle(String title);

}
