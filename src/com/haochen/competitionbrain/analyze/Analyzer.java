package com.haochen.competitionbrain.analyze;

import com.haochen.competitionbrain.bean.Competitor;
import com.haochen.competitionbrain.bean.Game;
import com.haochen.competitionbrain.bean.Match;

import java.util.List;
import java.util.Map;

/**
 * Created by Haochen on 2017/1/14.
 */
public class Analyzer {
    public static Competitor getWinner(Game game) {
        if (game.getPoints().size() >= 2) {
            for (Game.Point p : game.getPoints()) {

            }
        }
        return null;
    }

    public static List<Competitor> getWinner(Match match) {
        return null;
    }
}
