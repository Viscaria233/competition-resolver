package com.haochen.competitionbrain.bean;

import com.haochen.competitionbrain.common.IFinish;
import com.haochen.competitionbrain.common.IWinnerLoser;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Game extends Bean implements IFinish, IWinnerLoser {
    protected Competitor[] competitors = new Competitor[2];
    protected int winner = -1;
    protected int[] points = new int[2];
    protected int maxPoint;
    protected int overtimePoint;

    public Game() {}

    public Game(int maxPoint, int overtimePoint) {
        this.maxPoint = maxPoint;
        this.overtimePoint = overtimePoint;
    }

    public Competitor[] getCompetitors() {
        return competitors;
    }

    public void setCompetitors(Competitor[] competitors) {
        this.competitors = competitors;
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

    public int[] getPoints() {
        return points;
    }

    public void setPoints(int[] points) {
        this.points = points;
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(int maxPoint) {
        this.maxPoint = maxPoint;
    }

    public int getOvertimePoint() {
        return overtimePoint;
    }

    public void setOvertimePoint(int overtimePoint) {
        this.overtimePoint = overtimePoint;
    }

    @Override
    public boolean isFinish() {
        return winner != -1;
    }
}
