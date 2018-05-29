package com.fudan.cosmosapp.ui.discover.presenter;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public interface SearchKeyDetailPresenter {

    public static int LOADING_RADICAL = 1;
    public static int LOADING_PINYIN = 2;
    public static int LOADING_DETAIL = 3;
    public static int LOADING_KEYID_BY_RADICALID = 4;
    public static int LOADING_KEYID_BY_PINYINID = 5;

    void getSearchKeyDetail(String keyName);

    void getRadicalList();

    void getPinYinList();

    void getKeyIdByRadicalId(String radicalId);

    void getKeyIdByPinYinId(String pinyinId);

}
