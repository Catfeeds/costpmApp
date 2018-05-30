package com.fudan.cosmosapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class NewsBean {

    private List<String> title;

    private List<String> content;

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
