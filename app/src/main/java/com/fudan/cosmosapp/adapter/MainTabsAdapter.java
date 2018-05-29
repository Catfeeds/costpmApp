package com.fudan.cosmosapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.util.SparseArray;

import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.Subject;
import com.fudan.cosmosapp.fragment.ClassifyFragment;
import com.fudan.cosmosapp.fragment.ClassifyPageFragment;
import com.fudan.cosmosapp.httpClient.Api;
import com.fudan.cosmosapp.httpClient.CourseNetwork;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ang on 2017/8/10.
 */
public class MainTabsAdapter extends FragmentStatePagerAdapter {

    private int mGradeId;
    private List<Subject.SubjectListBean> mSubjects;
    private DataChangeListener dataChangeListener;
    private ClassifyFragment mainFragment;

//    private Map<Integer,Fragment> mPages = new HashMap<>();
    /**
     * 比hashMap性能更优越
     */
    private SparseArray<Fragment> mPages = new SparseArray<>();

    public interface DataChangeListener {
        void DataChanged(int gradeId, int subjectId);
    }

    public MainTabsAdapter(int gradeId, List<Subject.SubjectListBean> subjects, FragmentManager fm, ClassifyFragment fragment) {
        super(fm);
        this.mSubjects = subjects;
        mGradeId = gradeId;
        mainFragment = fragment;

    }

    @Override
    public int getCount() {
        return mSubjects.size();
    }

    @Override
    public Fragment getItem(int i) {
        // TODO: 2017/8/7
//            return ClassifyPageFragment.newInstance(mGradeId, i + 1);
        if(mPages.get(i) != null){
            return mPages.get(i);
        }else {
            mPages.put(i,ClassifyPageFragment.newInstance(mGradeId, 2));
            return mPages.get(i);
        }

//            return ClassifyPageFragment.newInstance(9, 2);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mSubjects.get(position).getSubjectName();
    }

    /**
     * 年级改变时，调用此方法。
     * @param gradeId
     */
    public void notifyTabsDataChanged(final int gradeId) {
        mGradeId = gradeId;

        Log.e("gradeId",gradeId+"");

        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());
        final Observable<Subject> subjects = api.getSubjects(String.valueOf(gradeId)).compose(CourseNetwork.schedulersTransformer);
        subjects.subscribe(new Observer<Subject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Subject subject) {
//                        //当年级改变时 科目会改变 此时科目要清空
//                        mPages.clear();
                        mSubjects = subject.getSubjectList();
                        notifyDataSetChanged();

                        mainFragment.setTabSubjectMode(mSubjects.size());

                        dataChangeListener = (ClassifyPageFragment) getItem(0);
                        dataChangeListener.DataChanged(gradeId, 2);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
