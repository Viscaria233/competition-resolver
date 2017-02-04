package com.haochen.competitionbrain.schedule;

import com.haochen.competitionbrain.bean.Competitor;
import com.haochen.competitionbrain.bean.Match;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haochen on 2017/2/3.
 */
public class DoubleRoundRobinArranger extends SingleRoundRobinArranger {
    @Override
    List<Match> getMatches(Competitor[] competitors, int maxGame) {
        List<Match> matches1 = super.getMatches(competitors, maxGame);
        matches1.forEach((m) -> m.setHomeCompetitor(0));
        List<Match> matches2 = super.getMatches(competitors, maxGame);
        matches2.forEach((m) -> m.setHomeCompetitor(1));

        List<Match> result = new ArrayList<>();
        result.addAll(matches1);
        result.addAll(matches2);
        return result;
    }
}
