package com.fudan.cosmosapp.adapter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.bean.BookVersion;
import com.fudan.cosmosapp.bean.KnowledgePoint;
import com.fudan.cosmosapp.httpClient.Api;
import com.fudan.cosmosapp.httpClient.CourseNetwork;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ang on 2017/8/3.
 */

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.ViewHolder> {
    private static int mGradeId, mSubjectId;
    private List<BookVersion.TextbookVersionListBean> version;
    private static List<KnowledgePoint.KnowledgePointListBean> knowledgePoint;
    private MainPageItemListener listener;

    public MainPageAdapter(MainPageItemListener mainPageItemListener){
        listener = mainPageItemListener;
    }

    public interface MainPageItemListener{
        void onClick(String knowledgePointId);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject_version, parent, false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_page, parent, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // List 的第一行显示教材的版本
        if (getItemViewType(position) == 0){
            holder.rgVersion.removeAllViews();
            for (int i = 0; i < version.size(); i++){
                final RadioButton radioButton = new RadioButton(holder.mView.getContext());
                radioButton.setBackgroundResource(R.drawable.radio);
                radioButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
                radioButton.setGravity(Gravity.CENTER);
                radioButton.setText(version.get(i).getTextbookVersion());
                radioButton.setTextSize(16);
                radioButton.setTextColor(holder.mView.getContext().getResources().getColor(R.color.text_black));
                radioButton.setPadding(24,4,24,4);
                final int finalI = i;
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        notifyDataChanged(mGradeId, 2, finalI + 1);

                    }
                });
                LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT );
                radioButton.setLayoutParams( rlp );
                holder.rgVersion.addView(radioButton);
            }

        }
        // List 的其余部分显示知识点目录
        else {
            holder.mTextView.setText(knowledgePoint.get(position - 1).getKnowledgePoint());
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(knowledgePoint.get(position - 1).getKnowledgePointId());
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        if (knowledgePoint != null ){
            return knowledgePoint.size() + 1;
        }else {
            return 1;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 0;
        }else {
            return 1;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        RadioGroup rgVersion;
        CardView mCardView;
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            rgVersion = itemView.findViewById(R.id.rg_version);
            mTextView = itemView.findViewById(R.id.tv_item_main_page);
            mCardView = itemView.findViewById(R.id.cv_item_main_page);
        }
    }


    /**
     * 当有筛选条件变更时调用
     * @param gradeId
     * @param subjectId
     * @param bookVersionId
     */
    private void notifyDataChanged(int gradeId, int subjectId, int bookVersionId){
        Api api = CourseNetwork.getInstance().getApi(CosmosApplication.getContext());

        Observable<KnowledgePoint> pointObservable = api.getKnowledgePoint(
                String.valueOf(bookVersionId),
                String.valueOf(subjectId), String.valueOf(gradeId)).compose(CourseNetwork.schedulersTransformer);

        pointObservable.subscribe(new Observer<KnowledgePoint>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KnowledgePoint knowledgePoint) {
                        MainPageAdapter.knowledgePoint = knowledgePoint.getKnowledgePointList();
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 外部的 gradeId 或者 subjectId 发生变更时调用
     * @param gradeId
     * @param subjectId
     * @param version
     */
    public void notifyMainPageDataChanged(int gradeId, int subjectId, List<BookVersion.TextbookVersionListBean> version){
        mGradeId = gradeId;
        mSubjectId = subjectId;
        this.version = version;
        notifyDataChanged(gradeId, 2, 1);
    }
}
