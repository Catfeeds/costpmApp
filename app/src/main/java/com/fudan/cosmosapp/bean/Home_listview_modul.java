package com.fudan.cosmosapp.bean;

/**
 * Created by yinxiaofei on 2017/7/27.
 */

public class Home_listview_modul {
    private String  title=null;
    private String  content=null;
    int layoutType =0;

    public int getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
