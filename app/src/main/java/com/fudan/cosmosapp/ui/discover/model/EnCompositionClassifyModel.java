package com.fudan.cosmosapp.ui.discover.model;

import com.fudan.cosmosapp.bean.ChCompositionTitleArray;
import com.fudan.cosmosapp.bean.CnCompositionClassifyBean;
import com.fudan.cosmosapp.bean.EnCompositionClassifyBean;
import com.fudan.cosmosapp.bean.EnCompositionTitleArray;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public interface EnCompositionClassifyModel {

    Observable<EnCompositionClassifyBean> getEnCompositionClassify();

    Observable<EnCompositionTitleArray> getEnCompositionTitleListByClassifyId(String classificationId);

}
