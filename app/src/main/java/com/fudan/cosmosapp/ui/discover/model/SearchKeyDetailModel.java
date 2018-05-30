package com.fudan.cosmosapp.ui.discover.model;

import com.fudan.cosmosapp.bean.KeyArray;
import com.fudan.cosmosapp.bean.KeyDetailArray;
import com.fudan.cosmosapp.bean.PinYinArray;
import com.fudan.cosmosapp.bean.RadicalArray;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public interface SearchKeyDetailModel {

    Observable<KeyDetailArray> getKeyDetailByKeyName(String keyName);

    Observable<RadicalArray> getRadicalList();

    Observable<PinYinArray> getPinYinList();

    Observable<KeyArray> getKeyIdByRadicalId(String radicalId);

    Observable<KeyArray> getKeyIdByPinYin(String pinyinId);

}
