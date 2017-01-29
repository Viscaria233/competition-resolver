package com.haochen.competitionbrain.analyze;

import com.haochen.competitionbrain.bean.*;

import java.util.*;

/**
 * Created by Haochen on 2017/1/19.
 */
class SingleRoundRobinAnalyzer {
    private Columns columns = new Columns();

    private final ScoreGetter SCORE = (r, i) -> r[0][i][columns.score];
    private final ScoreGetter GAME_RATE = (r, i) -> 1.0 * r[0][i][columns.winGame] / (r[0][i][columns.loseGame + 1]);
    private final ScoreGetter POINT_RATE = (r, i) -> 1.0 * r[0][i][columns.winPoint] / (r[0][i][columns.losePoint + 1]);
    private ScoreGetter[] scoreGetters = {SCORE, GAME_RATE, POINT_RATE};

    private Comparator comparator = (r, i1, i2) -> {
        for (ScoreGetter getter : scoreGetters) {
            double s1 = getter.getScore(r, i1);
            double s2 = getter.getScore(r, i2);
            if (s1 != s2) {
                return s1 - s2;
            }
        }
        return 0;
    };

    private class Columns {
        int score;
        int winGame;
        int loseGame;
        int winPoint;
        int losePoint;
        int rank;
    }

    private class Report {
        int[][][] result;
        Competitor[] competitors;
        Map<Competitor, Integer> map;

    }

    private interface ScoreGetter {
        double getScore(int[][][] result, int index);
    }

    private interface Comparator {
        double compare(int[][][] result, int index1, int index2);
    }

    Report report(Module module) {
        List<Match> matches = new ArrayList<>();
        List<Competitor> temp = new ArrayList<>();
        for (Group g : module.getGroups()) {
            matches.addAll(g.getMatches());
            for (Match m : g.getMatches()) {
                temp.addAll(Arrays.asList(m.getCompetitors()));
            }
        }
        Competitor[] competitors = temp.stream().distinct().toArray(v -> new Competitor[1]);
        Report report = createReport(competitors);

        matches.forEach((m) -> {
            //put points
            Competitor[] cs = m.getCompetitors();
            int[] points = m.getPoints();
            winGame(cs[0], cs[1], points[0], report.result, report.map);
            winGame(cs[1], cs[0], points[1], report.result, report.map);

            //score and win/lose Game
            if (points[0] == -1 || points[1] == -1) {
                winMatchByQuitingFoe(m.getWinner(), report.result, report.map);
            } else {
                winMatch(m.getWinner(), m.getLoser(), report.result, report.map);
            }

            //win/lose Point
            for (Game g : m.getGames()) {
                Competitor[] gameCs = g.getCompetitors();
                int[] gamePoints = g.getPoints();
                winPoint(gameCs[0], gameCs[1], gamePoints[0], report.result, report.map);
                winPoint(gameCs[1], gameCs[0], gamePoints[1], report.result, report.map);
            }
        });
        return report;
    }

    int[][][] analyze(Module module) {
        Report report = report(module);
        boolean done = rank(report);
        return done ? report.result : null;
    }

    private Report createReport(Competitor[] competitors) {
        int row = competitors.length;
        int col = competitors.length + 6;
        int[][][] result = new int[2][row][col];
        columns.score = col - 6;
        columns.winGame = col - 5;
        columns.loseGame = col - 4;
        columns.winPoint = col - 3;
        columns.losePoint = col - 2;
        columns.rank = col - 1;
        Map<Competitor, Integer> map = new HashMap<>();
        for (int i = 0; i < competitors.length; ++i) {
            map.put(competitors[i], i);
        }

        Report report = new Report();
        report.result = result;
        report.competitors = competitors;
        report.map = map;
        return report;
    }

    private boolean rank(Report report) {
        int[][][] result = report.result;
        Competitor[] competitors = report.competitors;
        //rank
        //sort desc by comparator.compare()
        int[] ranks = new int[competitors.length];
        int lastIndex = 0;
        for (int i = 0; i < competitors.length - 1; ++i) {
            int max = 0;
            for (int j = i + 1; j < competitors.length - 1; ++j) {
                max = i;
                if (comparator.compare(result, max, j) > 0) {
                    max = j;
                }
            }
            if (i > 0 && comparator.compare(result, max, lastIndex) == 0) {
                if (ranks[lastIndex] > 0) {
                    ranks[lastIndex] *= -1;
                }
                ranks[max] = ranks[lastIndex];
            } else {
                ranks[max] = i + 1;
            }
            lastIndex = max;
        }
        //rank calculating
        for (int i = 0; i < ranks.length; ++i) {
            result[0][i][columns.rank] = ranks[i];
        }

        return hasResult(report.result, report.competitors);
    }

    private boolean hasResult(int[][][] result, Competitor[] competitors) {
        for (int i = 0; i < competitors.length; ++i) {
            if (result[0][i][columns.rank] <= 0) {
                return false;
            }
        }
        return true;
    }

    private void winMatchByQuitingFoe(Competitor winner, int[][][] result, Map<Competitor, Integer> map) {
        result[0][map.get(winner)][columns.score] += 2;
    }

    private void winMatch(Competitor winner, Competitor foe, int[][][] result, Map<Competitor, Integer> map) {
        result[0][map.get(winner)][columns.score] += 2;
        result[0][map.get(foe)][columns.score] += 1;
    }

    private void winGame(Competitor self, Competitor foe, int winCount, int[][][] result, Map<Competitor, Integer> map) {
        int selfIndex = map.get(self);
        int foeIndex = map.get(foe);
        result[0][selfIndex][foeIndex] += winCount;
        result[1][foeIndex][selfIndex] += winCount;
        result[0][selfIndex][columns.winGame]++;
        result[1][foeIndex][columns.loseGame]++;
    }

    private void winPoint(Competitor self, Competitor foe, int winCount, int[][][] result, Map<Competitor, Integer> map) {
        result[0][map.get(self)][columns.winPoint] += winCount;
        result[0][map.get(foe)][columns.losePoint] += winCount;
    }
}
