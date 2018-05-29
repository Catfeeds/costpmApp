package com.fudan.cosmosapp.ui.discover.model;

import com.fudan.cosmosapp.bean.ChCompositionTitleArray;
import com.fudan.cosmosapp.bean.CnCompositionClassifyBean;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public interface CnCompositionClassifyModel {

    Observable<CnCompositionClassifyBean> getCnCompositionClassify();

    Observable<ChCompositionTitleArray> getChCompositionTitleListByClassifyId(String classificationId);

}
