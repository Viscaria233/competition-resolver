package com.haochen.competitionbrain.bean;

import java.util.*;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Game extends Bean {
    protected List<Point> points = new ArrayList<>();
    protected int maxPoint;
    protected int overtimePoint;

    public List<Point> getPoints() {
        return points;
    }

    public void addPoint(Competitor competitor, int point) {
        points.stream()
                .filter((c) -> c.competitor == competitor)
                .mapToInt((f) -> points.indexOf(f))
                .findFirst()
                .ifPresent(p -> addPoint(p, point));
    }

    public void addPoint(int index, int point) {
        if (points.size() > index) {
            int currentPoint = points.get(index).point;
            if (currentPoint + point <= maxPoint) {
                points.get(index).point += point;
            } else {
                points.get(index).point = maxPoint;
            }
        }
    }

    public void setMaxPoint(int maxPoint) {
        this.maxPoint = maxPoint;
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public int getOvertimePoint() {
        return overtimePoint;
    }

    public void setOvertimePoint(int overtimePoint) {
        this.overtimePoint = overtimePoint;
    }

    public static class Point {
        Competitor competitor;
        int point;
    }
}
