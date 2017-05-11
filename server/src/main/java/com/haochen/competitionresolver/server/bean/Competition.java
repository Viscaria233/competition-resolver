package com.haochen.competitionresolver.server.bean;

import com.haochen.competitionresolver.server.common.IFinish;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Competition extends Bean implements IFinish {
    public static final int INDIVIDUAL = 1;
    public static final int TEAM = 2;

    protected int type;
    protected List<Module> modules = new ArrayList<>();
    protected List<Competitor> competitors = new ArrayList<>();

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<Competitor> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(List<Competitor> competitors) {
        this.competitors = competitors;
    }

    @Override
    public boolean isFinish() {
        for (Module m : modules) {
            if (!m.isFinish()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Competition that = (Competition) o;

        if (type != that.type) return false;
        if (modules != null ? !modules.equals(that.modules) : that.modules != null) return false;
        return competitors != null ? competitors.equals(that.competitors) : that.competitors == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + type;
        result = 31 * result + (modules != null ? modules.hashCode() : 0);
        result = 31 * result + (competitors != null ? competitors.hashCode() : 0);
        return result;
    }
}
