package com.fudan.cosmosapp.ui.classify.model;

import com.fudan.cosmosapp.bean.Grade;
import com.fudan.cosmosapp.bean.Subject;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public interface ClassifyModel {

    Observable<Grade> loadGrades();

    Observable<Subject> loadSubjects(int id);

}
