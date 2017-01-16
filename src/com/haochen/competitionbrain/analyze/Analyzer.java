package com.haochen.competitionbrain.analyze;

import com.haochen.competitionbrain.bean.Competitor;
import com.haochen.competitionbrain.bean.Game;
import com.haochen.competitionbrain.bean.Match;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Haochen on 2017/1/14.
 */
public class Analyzer {
    public static Competitor getWinner(Game game) {
        Game.Point[] points = game.getPoints();
        if (points[0] != null && points[1] != null
            && points[0].competitor != null && points[1].competitor != null) {

            int maxPoint = game.getMaxPoint();
            Game.Point higher = points[0].point > points[1].point ? points[0] : points[1];
            Game.Point lower = points[0].point > points[1].point ? points[1] : points[0];

            if (lower.point == -1 && higher.point != -1) {
                return higher.competitor;
            } else if (higher.point >= maxPoint) {
                if (lower.point < maxPoint - 1) {
                    return higher.competitor;
                } else if (higher.point - lower.point >= game.getOvertimePoint()) {
                    return higher.competitor;
                }
            }
        }
        return null;
    }

    public static Competitor getWinner(Match match) {
        Game[] games = match.getGames();
        Competitor[] competitors = match.getCompetitors();
        int[] points = new int[2];

        for (Game g : games) {
            Competitor winner = getWinner(g);
            if (winner != null) {
                if (winner == competitors[0]) {
                    points[0]++;
                } else if (winner == competitors[1]) {
                    points[1]++;
                }
            }
        }

        int higher = points[0] > points[1] ? 0 : 1;
        if (higher >= match.getMaxGame()) {
            return competitors[higher];
        }
        return null;
    }
}
