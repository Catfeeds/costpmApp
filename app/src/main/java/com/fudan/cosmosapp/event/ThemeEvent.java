package com.fudan.cosmosapp.event;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class ThemeEvent {

    private int theme;

    public ThemeEvent(int theme){
        this.theme = theme;
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }
}
