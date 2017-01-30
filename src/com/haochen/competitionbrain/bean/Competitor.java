package com.haochen.competitionbrain.bean;

/**
 * Created by Haochen on 2016/12/29.
 */
public abstract class Competitor extends Bean {
    protected String name;

    public Competitor() {}

    public Competitor(int id) {
        super(id);
    }

    public Competitor(int id, String name) {
        super(id);
        this.name = name;
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
        if (!super.equals(o)) return false;

        Competitor that = (Competitor) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
