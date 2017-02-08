package com.haochen.competitionbrain.bean;

/**
 * Created by Haochen on 2016/12/29.
 */
public abstract class Competitor extends Bean {
    public Competitor() {}

    public Competitor(int id) {
        super(id);
    }

    public Competitor(int id, String name) {
        super(id, name);
    }
}
