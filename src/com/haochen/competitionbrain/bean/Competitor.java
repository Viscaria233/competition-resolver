package com.haochen.competitionbrain.bean;

/**
 * Created by Haochen on 2016/12/29.
 */
public abstract class Competitor extends Bean {
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
