package com.haochen.competitionbrain.analyze;

import com.haochen.competitionbrain.bean.*;

import java.util.*;

/**
 * Created by Haochen on 2017/1/19.
 */
class SingleRoundRobinAnalyzer {
    private int[][][] result;
    private Competitor[] competitors;
    private Map<Competitor, Integer> map = new HashMap<>();
    private Columns columns = new Columns();

    private class Columns {
        int score;
        int winGame;
        int loseGame;
        int winPoint;
        int losePoint;
        int rank;
    }

    int[][][] analyze(Module module) {
        List<Match> matches = new ArrayList<>();
        List<Competitor> temp = new ArrayList<>();
        for (Group g : module.getGroups()) {
            matches.addAll(g.getMatches());
            for (Match m : g.getMatches()) {
                temp.addAll(Arrays.asList(m.getCompetitors()));
            }
        }
        competitors = temp.stream().distinct().toArray(v -> new Competitor[1]);
        init();

        matches.forEach((m) -> {
            //put points
            Competitor[] cs = m.getCompetitors();
            int[] points = m.getPoints();
            winGame(cs[0], cs[1], points[0]);
            winGame(cs[1], cs[0], points[1]);

            //score and win/lose Game
            if (points[0] * points[1] < 0) {
                winMatchByQuit(m.getWinner());
            } else {
                winMatch(m.getWinner(), m.getLoser());
            }

            //win/lose Point
            for (Game g : m.getGames()) {
                Competitor[] gameCs = g.getCompetitors();
                int[] gamePoints = g.getPoints();
                winPoint(gameCs[0], gameCs[1], gamePoints[0]);
                winPoint(gameCs[1], gameCs[0], gamePoints[1]);
            }
        });

        boolean done = rank();
        return done ? result : null;
    }

    private void init() {
        int col = competitors.length + 6;
        int row = competitors.length;
        result = new int[2][row][col];

        for (int i = 0; i < competitors.length; ++i) {
            map.put(competitors[i], i);
        }

        columns.score = col - 6;
        columns.winGame = col - 5;
        columns.loseGame = col - 4;
        columns.winPoint = col - 3;
        columns.losePoint = col - 2;
        columns.rank = col - 1;
    }

    private boolean rank() {
        //rank
        int[] indexes = new int[competitors.length];
        int[] scores = new int[competitors.length];
        for (int i = 0; i < indexes.length; ++i) {
            indexes[i] = i;
            scores[i] = result[0][i][columns.score];
        }
        //sort desc
        for (int i = 0; i < indexes.length - 1; ++i) {
            for (int j = 0; j < indexes.length - i; ++j) {
                if (scores[j] < scores[j + 1]) {
                    swap(scores, j, j + 1);
                    swap(indexes, j, j + 1);
                }
            }
        }
        //rank calculating
        result[0][indexes[0]][columns.rank] = 1;
        int rank = 1;
        int sameRankCount = 1;
        for (int i = 1; i < indexes.length; ++i) {
            if (scores[i] == scores[i - 1]) {
                result[0][indexes[i]][columns.rank] = -rank;
                result[0][indexes[i - 1]][columns.rank] *= -1;
                sameRankCount++;
            } else {
                rank += sameRankCount;
                sameRankCount = 0;
                result[0][indexes[i]][columns.rank] = rank;
            }
        }

        if (noResult()) {
            return false;
        }
        return true;
    }

    private boolean noResult() {
        int rank = result[0][0][columns.rank];
        if (rank > 0) {
            return false;
        }
        for (int i = 0; i < competitors.length; ++i) {
            if (result[0][i][columns.rank] != rank) {
                return false;
            }
        }
        return true;
    }

    private void swap(int[] array, int id1, int id2) {
        int t = array[id1];
        array[id1] = array[id2];
        array[id2] = t;
    }

    private void winMatchByQuit(Competitor winner) {
        result[0][map.get(winner)][columns.score] += 2;
    }

    private void winMatch(Competitor winner, Competitor foe) {
        result[0][map.get(winner)][columns.score] += 2;
        result[0][map.get(foe)][columns.score] += 1;
    }

    private void winGame(Competitor self, Competitor foe, int winCount) {
        int selfIndex = map.get(self);
        int foeIndex = map.get(foe);
        result[0][selfIndex][foeIndex] += winCount;
        result[1][foeIndex][selfIndex] += winCount;
        result[0][selfIndex][columns.winGame]++;
        result[1][foeIndex][columns.loseGame]++;
    }

    private void winPoint(Competitor self, Competitor foe, int winCount) {
        result[0][map.get(self)][columns.winPoint] += winCount;
        result[0][map.get(foe)][columns.losePoint] += winCount;
    }
}
