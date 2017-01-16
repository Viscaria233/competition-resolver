package com.haochen.competitionbrain.bean;

import java.util.*;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Game extends Bean {
    protected Point[] points = new Point[2];
    protected int maxPoint;
    protected int overtimePoint;

    public Point[] getPoints() {
        return points;
    }

    public void addPoint(Competitor competitor, int point) {
        if (competitor == points[0].competitor) {
            addPoint(0, point);
        } else if (competitor == points[1].competitor) {
            addPoint(1, point);
        }
    }

    public void addPoint(int index, int point) {
        if (index >= 0 && index < points.length) {
            int currentPoint = points[index].point;
            if (currentPoint + point <= maxPoint) {
                points[index].point += point;
            } else {
                points[index].point = maxPoint;
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
        public Competitor competitor;
        public int point;
    }
}
