package com.haochen.competitionbrain.bean;

import java.io.Serializable;

/**
 * Created by Haochen on 2016/12/29.
 */
public abstract class Bean implements Serializable {
    protected int id;
    protected String name;

    public Bean() {}

    public Bean(int id) {
        this.id = id;
    }

    public Bean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bean bean = (Bean) o;

        if (id != bean.id) return false;
        return name != null ? name.equals(bean.name) : bean.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
