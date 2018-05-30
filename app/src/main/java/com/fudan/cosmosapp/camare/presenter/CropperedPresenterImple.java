package com.fudan.cosmosapp.camare.presenter;

import android.content.Context;

import com.fudan.cosmosapp.camare.model.CropperedModel;
import com.fudan.cosmosapp.camare.model.CropperedModelImple;
import com.fudan.cosmosapp.camare.view.CropperedView;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class CropperedPresenterImple implements CropperedPresenter {

    private Context context;

    private CropperedModel cropperedModel;

    private CropperedView cropperedView;

    public CropperedPresenterImple(Context context,CropperedView cropperedView){
        this.context = context;
        this.cropperedView = cropperedView;
        this.cropperedView.setPresenter(this);
        this.cropperedModel = new CropperedModelImple();
    }

    @Override
    public void uploadImage() {

    }
}
