package com.fudan.cosmosapp.adapter;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public interface IAdapterView<T> {

    void bind(T item, int position);

}
