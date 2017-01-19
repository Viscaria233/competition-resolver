package com.haochen.competitionbrain.bean;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Game extends Bean {
    protected Competitor[] competitors = new Competitor[2];
    protected Competitor winner;
    protected int[] points = new int[2];
    protected int maxPoint;
    protected int overtimePoint;

    public Competitor[] getCompetitors() {
        return competitors;
    }

    public void setCompetitors(Competitor[] competitors) {
        this.competitors = competitors;
    }

    public Competitor getWinner() {
        return winner;
    }

    public void setWinner(Competitor winner) {
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
}
