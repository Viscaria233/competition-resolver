package com.haochen.competitionresolver.server.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Team extends Competitor {
    protected Athlete leader;
    protected List<Athlete> members = new ArrayList<>();

    public Athlete getLeader() {
        return leader;
    }

    public void setLeader(Athlete leader) {
        this.leader = leader;
    }

    public List<Athlete> getMembers() {
        return members;
    }

    public void setMembers(List<Athlete> members) {
        this.members = members;
    }
}
