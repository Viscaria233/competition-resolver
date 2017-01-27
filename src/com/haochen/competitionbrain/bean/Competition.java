package com.haochen.competitionbrain.bean;

import com.haochen.competitionbrain.common.IFinish;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Competition extends Bean implements IFinish {
    public static final int INDIVIDUAL = 1;
    public static final int TEAM = 2;

    protected String name;
    protected int type;
    protected List<Module> modules = new ArrayList<>();
    protected List<Competitor> competitors = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
