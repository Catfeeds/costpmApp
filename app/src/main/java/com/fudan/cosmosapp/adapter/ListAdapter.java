package com.fudan.cosmosapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25 0025.
 * <p>
 * T 数据模型的泛型
 */

public abstract class ListAdapter<T, V extends IAdapterView>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<T> mData;

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    //-1
    public ListAdapter(Context ctx, List<T> data) {
        this.mContext = ctx;
        this.mData = data;
    }

    protected abstract V createView(Context context);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //返回条目布局
        View itemView = (View) createView(mContext);

        final RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(itemView) {};
        //对条目的监听
        if (mOnItemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mOnItemClickListener.onItemClick(position);
                    }
                }
            });
        }

        //长按监听
        if (mOnItemLongClickListener != null) {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mOnItemLongClickListener.onItemClick(position);
                    }
                    /**
                     * false 消耗onitemclick 而true 不消耗onitemclick
                     */
                    return false;
                }
            });
        }

        return holder;
    }

    /**
     * 绑定viewholder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IAdapterView itemView = (V)holder.itemView;
        itemView.bind(getItem(position),position);
    }

    @Override
    public int getItemCount() {
        if(mData == null){
            return 0;
        }
        return mData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    public void setData(List<T> data){
        mData = data;
    }

    public void addData(List<T> data){
        if(mData == null){
            mData = data;
        }else {
            mData.addAll(data);
        }
    }

    public List<T> getData(){
        return mData;
    }

    public void clear(){
        if(mData != null){
            mData.clear();
        }
    }

    /**
     * 设置监听
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * 获取监听
     */
    public OnItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }

    /**
     * 设置监听
     */
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    /**
     * 获取监听
     */
    public OnItemLongClickListener getOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {

        void onItemClick(int position);
    }
}
