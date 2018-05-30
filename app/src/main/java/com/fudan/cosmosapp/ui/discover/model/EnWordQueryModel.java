package com.fudan.cosmosapp.ui.discover.model;

import com.fudan.cosmosapp.bean.EnWordListArray;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public interface EnWordQueryModel {

    Observable<EnWordListArray> getEnWordListArray();

    Observable<EnWordListArray> getEnWordListArrayByWord(String word);
}
