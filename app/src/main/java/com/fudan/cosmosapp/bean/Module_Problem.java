package com.fudan.cosmosapp.bean;

/**
 * Created by yinxiaofei on 2017/7/7.
 */

public class Module_Problem {

    private String  Nmodule;
    private String  act;
    private String problem;

    public String getNmodule() {
        return Nmodule;
    }

    public String getAct() {
        return act;
    }

    public String getPeoblem() {
        return problem;
    }

    public void setNmodule(String nmodule) {
        Nmodule = nmodule;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public void setPeoblem(String peoblem) {
        this.problem = peoblem;
    }
}
