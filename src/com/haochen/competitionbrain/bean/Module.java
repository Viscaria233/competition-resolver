package com.haochen.competitionbrain.bean;

import com.haochen.competitionbrain.common.IFinish;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Module extends Bean implements IFinish {
    public static final int SINGLE_KNOCKOUT = 1;
    public static final int DOUBLE_KNOCKOUT = 1 << 1;
    public static final int SINGLE_ROUND_ROBIN = 1 << 2;
    public static final int DOUBLE_ROUND_ROBIN = 1 << 3;

    protected int rule;
    protected List<Group> groups = new ArrayList<>();
    protected List<Competitor> competitors = new ArrayList<>();

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

    public List<Competitor> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(List<Competitor> competitors) {
        this.competitors = competitors;
    }

    @Override
    public boolean isFinish() {
        for (Group g : groups) {
            if (!g.isFinish()) {
                return false;
            }
        }
        return true;
    }
}
