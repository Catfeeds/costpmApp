package com.fudan.cosmosapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.bean.NewsBean;

/**
 * Created by yinxiaofei on 2017/7/27.
 */

public class HomePage_ListAdapter extends BaseAdapter {
    public static final int TYPE_TITLE = 0;
    public static final int TYPE_COMPANY = 1;
    private Context context;

    private NewsBean newsBean;

//    private String[] comNames;
//    private String[] titleNames;
//    private String[] comIcons;

    public HomePage_ListAdapter(Context context, NewsBean newsBean) {
        super();
        this.context = context;
        this.newsBean = newsBean;
        //this.comIcons = comIcons;
    }

    public NewsBean getData(){
        return newsBean;
    }

    public void onDateChange(NewsBean newsBean) {
        if (newsBean == null) return;

        this.newsBean = newsBean;
        this.notifyDataSetChanged();
    }

    public void onDataAdd(NewsBean newsBean){
        if(newsBean == null) return;
        this.newsBean.getTitle().addAll(newsBean.getTitle());
        this.newsBean.getContent().addAll(newsBean.getContent());
        this.notifyDataSetChanged();
    }

    public void onDataClear(){
        if (this.newsBean != null){
            newsBean.getTitle().clear();
            newsBean.getContent().clear();
        }
    }

    @Override
    public int getCount() {
        if(newsBean == null) return 0;
        return newsBean.getTitle().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 4 == 0) {
            return TYPE_TITLE;
        } else {
            return TYPE_COMPANY;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        infoViewHolder titleHolder;
        newsViewHolder comHolder;
        switch (getItemViewType(position)) {
            case TYPE_TITLE:
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.main_home_listview_info, null);
                    titleHolder = new infoViewHolder();
                    titleHolder.title = (TextView) convertView.findViewById(R.id.tv_home_listview_info_title);
                    titleHolder.com = (TextView) convertView.findViewById(R.id.tv_home_listview_info_content);
                    titleHolder.icon = (ImageView) convertView.findViewById(R.id.iv_home_listview_infoimage);
                    convertView.setTag(titleHolder);
                } else {
                    titleHolder = (infoViewHolder) convertView.getTag();
                }
                if (position % 3 == 0) {
                    titleHolder.title.setText(newsBean.getTitle().get(position));
                    titleHolder.com.setText("常用推荐|你会喜欢的");
                    titleHolder.icon.setImageResource(R.drawable.myinfo03);
                } else if (position % 3 == 1) {
                    titleHolder.title.setText(newsBean.getTitle().get(position));
                    titleHolder.com.setText(newsBean.getContent().get(position));
                    titleHolder.icon.setImageResource(R.drawable.myinfo01);
                } else if (position % 8 == 0) {
                    titleHolder.title.setText(newsBean.getTitle().get(position));
                    titleHolder.com.setText(newsBean.getContent().get(position));
                    titleHolder.icon.setImageResource(R.drawable.myinfo02);
                }
                break;
            case TYPE_COMPANY:
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.main_home_listview_news, null);
                    comHolder = new newsViewHolder();
                    comHolder.com = (TextView) convertView.findViewById(R.id.tv_home_listview_news_content);
                    comHolder.title = (TextView) convertView.findViewById(R.id.tv_home_listview_news_title);
                    comHolder.icon = (ImageView) convertView.findViewById(R.id.iv_home_listview_newsImage);
                    convertView.setTag(comHolder);
                } else {
                    comHolder = (newsViewHolder) convertView.getTag();
                }
                comHolder.com.setText(newsBean.getContent().get(position));
                comHolder.title.setText(newsBean.getTitle().get(position));
                if (position % 3 == 0) {
                    comHolder.icon.setImageResource(R.drawable.my1);
                } else if (position % 3 == 1) {
                    comHolder.icon.setImageResource(R.drawable.my2);
                } else if (position % 3 == 2) {
                    comHolder.icon.setImageResource(R.drawable.my3);
                }

                break;
        }
        return convertView;
    }

    class infoViewHolder {
        TextView title;
        TextView com;
        ImageView icon;
    }

    class newsViewHolder {
        TextView com;
        TextView title;
        ImageView icon;
    }
}

