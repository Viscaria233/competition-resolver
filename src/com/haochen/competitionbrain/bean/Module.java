package com.haochen.competitionbrain.bean;

import java.util.List;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Module extends Bean {
    protected int rule;
    protected List<Group> groups;

    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
