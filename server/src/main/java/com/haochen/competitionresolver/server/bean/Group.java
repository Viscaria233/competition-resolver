package com.haochen.competitionresolver.server.bean;

import com.haochen.competitionresolver.server.common.IFinish;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Group extends Bean implements IFinish {
    protected List<Match> matches = new ArrayList<>();
    protected int maxGame;
    protected List<Competitor> competitors = new ArrayList<>();

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public int getMaxGame() {
        return maxGame;
    }

    public void setMaxGame(int maxGame) {
        this.maxGame = maxGame;
    }

    public List<Competitor> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(List<Competitor> competitors) {
        this.competitors = competitors;
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
