package com.haochen.competitionbrain.bean;

import com.haochen.competitionbrain.common.IFinish;
import com.haochen.competitionbrain.common.IWinnerLoser;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Match extends Bean implements IFinish, IWinnerLoser {
    protected Game[] games;
    protected Competitor[] competitors = new Competitor[2];
    protected int winner = -1;
    protected int[] points = new int[2];


    /**
     *
     * @param maxGames maxGames = 3 means Best of 5
     */
    public Match(int maxGames) {
        games = new Game[maxGames * 2 - 1];
    }

    public Game[] getGames() {
        return games;
    }

    public Competitor[] getCompetitors() {
        return competitors;
    }

    public int getMaxGame() {
        return games.length / 2 + 1;
    }

    public void setGames(Game[] games) {
        this.games = games;
    }

    public void setCompetitors(Competitor[] competitors) {
        this.competitors = competitors;
    }

    public int[] getPoints() {
        return points;
    }

    public void setPoints(int[] points) {
        this.points = points;
    }

    @Override
    public boolean isFinish() {
        for (Game g : games) {
            if (!g.isFinish()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Competitor getWinner() {
        if (winner == -1) {
            return null;
        }
        return competitors[winner];
    }

    @Override
    public Competitor getLoser() {
        if (winner == -1) {
            return null;
        }
        return competitors[winner == 0 ? 1 : 0];
    }

    @Override
    public void setWinner(int winner) {
        this.winner = winner;
    }
}
