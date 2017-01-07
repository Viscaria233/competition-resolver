package com.haochen.competitionbrain.bean;

import java.io.Serializable;

/**
 * Created by Haochen on 2016/12/29.
 */
public abstract class Bean implements Serializable {
    protected int id;

    public int getId() {
        return id;
    }
}
