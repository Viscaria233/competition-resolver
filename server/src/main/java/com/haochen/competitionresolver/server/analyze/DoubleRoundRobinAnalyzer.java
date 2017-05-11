package com.haochen.competitionresolver.server.analyze;

import com.haochen.competitionresolver.server.bean.Competitor;
import com.haochen.competitionresolver.server.bean.Match;

import java.util.Map;

/**
 * Created by Haochen on 2017/2/1.
 */
public class DoubleRoundRobinAnalyzer extends SingleRoundRobinAnalyzer {
    public DoubleRoundRobinAnalyzer() {}

    @Override
    protected int[][][] getResultArray(int row, int col) {
        return new int[4][row][col];
    }

    @Override
    void winLoseGame(Report report, Match m, int[] points) {
        Competitor[] cs = m.getCompetitors();
        int home = m.getHomeCompetitor();
        boolean winAtHome = home == 0;
        winGame(cs[0], cs[1], winAtHome, points[0], report.result, report.map);
        winGame(cs[1], cs[0], !winAtHome, points[1], report.result, report.map);
    }

    private void winGame(Competitor self, Competitor foe, boolean winAtHome, int winCount, int[][][] result, Map<Competitor, Integer> map) {
        int selfIndex = map.get(self);
        int foeIndex = map.get(foe);
        int tableIndex = winAtHome ? 0 : 2;
        result[tableIndex][selfIndex][foeIndex] += winCount;
        result[tableIndex + 1][foeIndex][selfIndex] += winCount;
        result[0][selfIndex][columns.winGame] += winCount;
        result[0][foeIndex][columns.loseGame] += winCount;
    }
}
