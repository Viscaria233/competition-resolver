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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bean bean = (Bean) o;

        return id == bean.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
