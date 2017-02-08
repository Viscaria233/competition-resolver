package com.haochen.competitionbrain.analyze;

import com.haochen.competitionbrain.bean.*;

import java.util.*;

/**
 * Created by Haochen on 2017/1/19.
 */
public class SingleRoundRobinAnalyzer {
    Columns columns = new Columns();

    public SingleRoundRobinAnalyzer() {}

    private ScoreGetter[] getScoreGetter() {
        final ScoreGetter SCORE = (r, i) -> r[0][i][columns.score];
        final ScoreGetter GAME_RATE = (r, i) -> r[0][i][columns.loseGame] == 0 ? Integer.MAX_VALUE
                : 1.0 * r[0][i][columns.winGame] / (r[0][i][columns.loseGame]);
        final ScoreGetter POINT_RATE = (r, i) -> r[0][i][columns.losePoint] == 0 ? Integer.MAX_VALUE
                : 1.0 * r[0][i][columns.winPoint] / (r[0][i][columns.losePoint]);
        return new ScoreGetter[]{SCORE, GAME_RATE, POINT_RATE};
    }

    private Comparator comparator = (r, i1, i2) -> {
        for (ScoreGetter getter : getScoreGetter()) {
            double s1 = getter.getScore(r, i1);
            double s2 = getter.getScore(r, i2);
            if (s1 != s2) {
                return s1 - s2;
            }
        }
        return 0;
    };

    class Columns {
        int score;
        int winGame;
        int loseGame;
        int winPoint;
        int losePoint;
        int rank;
    }

    class Report {
        int[][][] result;
        Competitor[] competitors;
        Map<Competitor, Integer> map;

    }

    interface ScoreGetter {
        double getScore(int[][][] result, int index);
    }

    interface Comparator {
        double compare(int[][][] result, int index1, int index2);
    }

    private Report report(Module module) {
        List<Match> matches = new ArrayList<>();
//        List<Competitor> competitors = new ArrayList<>();
        for (Group g : module.getGroups()) {
            matches.addAll(g.getMatches());
//            for (Match m : g.getMatches()) {
//                competitors.addAll(Arrays.asList(m.getCompetitors()));
//            }
        }
//        Competitor[] distinctCompetitors = competitors.stream().distinct().toArray(Competitor[]::new);
        List<Competitor> competitors = module.getCompetitors();
        Report report = createReport(competitors.toArray(new Competitor[competitors.size()]));

        matches.forEach((m) -> {
            int[] points = m.getPoints();
            matchScore(report, m, points);
            winLoseGame(report, m, points);
            winLosePoint(report, m);
        });
        return report;
    }

    private void winLosePoint(Report report, Match m) {
        for (Game g : m.getGames()) {
            Competitor[] cs = g.getCompetitors();
            int[] points = g.getPoints();
            winPoint(cs[0], cs[1], points[0], report.result, report.map);
            winPoint(cs[1], cs[0], points[1], report.result, report.map);
        }
    }

    void winLoseGame(Report report, Match m, int[] points) {
        Competitor[] cs = m.getCompetitors();
        winGame(cs[0], cs[1], points[0], report.result, report.map);
        winGame(cs[1], cs[0], points[1], report.result, report.map);
    }

    private void matchScore(Report report, Match m, int[] points) {
        if (points[0] == -1 || points[1] == -1) {
            winMatchByQuitingFoe(m.getWinner(), report.result, report.map);
        } else {
            winMatch(m.getWinner(), m.getLoser(), report.result, report.map);
        }
    }

    int[][][] analyze(Module module) {
        if (!module.isFinish()) {
            return null;
        }
        Report report = report(module);
        rank(report);
        return report.result;
    }

    private Report createReport(Competitor[] competitors) {
        int row = getResultRow(competitors);
        int col = getResultColumn(competitors);
        int[][][] result = getResultArray(row, col);
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

    private int getResultRow(Competitor[] competitors) {
        return competitors.length;
    }

    private int getResultColumn(Competitor[] competitors) {
        return competitors.length + 6;
    }

    protected int[][][] getResultArray(int row, int col) {
        return new int[2][row][col];
    }

    private boolean rank(Report report) {
        int[][][] result = report.result;
        Competitor[] competitors = report.competitors;
        //rank
        //sort desc by comparator.compare()
        int[] ranks = new int[competitors.length];
        int[] indexes = new int[competitors.length];
        for (int i = 0; i < indexes.length; ++i) {
            indexes[i] = i;
        }
//        int lastIndex = 0;
        for (int i = 0; i < competitors.length; ++i) {
            int max = i;
            for (int j = i + 1; j < competitors.length; ++j) {
                if (comparator.compare(result, indexes[j], indexes[max]) > 0) {
                    max = j;
                }
            }

            if (i > 0 && comparator.compare(result, indexes[max], indexes[i - 1]) == 0) {
                if (ranks[indexes[i - 1]] > 0) {
                    ranks[indexes[i - 1]] *= -1;
                }
                ranks[indexes[max]] = ranks[indexes[i - 1]];
            } else {
                ranks[indexes[max]] = i + 1;
            }

            if (max != i) {
                int t = indexes[max];
                indexes[max] = indexes[i];
                indexes[i] = t;
            }
//            lastIndex = i - 1;
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
        result[0][selfIndex][columns.winGame] += winCount;
        result[0][foeIndex][columns.loseGame] += winCount;
    }

    private void winPoint(Competitor self, Competitor foe, int winCount, int[][][] result, Map<Competitor, Integer> map) {
        result[0][map.get(self)][columns.winPoint] += winCount;
        result[0][map.get(foe)][columns.losePoint] += winCount;
    }
}
