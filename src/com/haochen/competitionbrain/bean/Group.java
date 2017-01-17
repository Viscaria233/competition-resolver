package com.haochen.competitionbrain.bean;

import java.util.List;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Group extends Bean {
    protected List<Match> matches;

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
