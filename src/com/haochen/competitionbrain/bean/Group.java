package com.haochen.competitionbrain.bean;

import com.haochen.competitionbrain.common.IFinish;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Group extends Bean implements IFinish {
    protected List<Match> matches = new ArrayList<>();

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public boolean isFinish() {
        for (Match m : matches) {
            if (!m.isFinish()) {
                return false;
            }
        }
        return true;
    }
}
