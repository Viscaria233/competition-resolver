package com.haochen.competitionbrain.analyze;

import com.haochen.competitionbrain.bean.Competitor;
import com.haochen.competitionbrain.bean.Group;
import com.haochen.competitionbrain.bean.Match;
import com.haochen.competitionbrain.bean.Module;
import com.haochen.competitionbrain.rule.Rule;

import java.util.*;

/**
 * Created by Haochen on 2017/1/17.
 */
public class ModuleAnalyzer {
    public int[][][] analyze(Module module) {
        switch (module.getRule()) {
            case Rule.SINGLE_KNOCKOUT:
                return singleKnockOutAnalyze(module);
            case Rule.DOUBLE_KNOCKOUT:
                return doubleKnockOutAnalyze(module);
            case Rule.SINGLE_ROUND_ROBIN:
                return singleRoundRobinAnalyze(module);
            case Rule.DOUBLE_ROUND_ROBIN:
                return doubleRoundRobinAnalyze(module);
        }
        return new int[0][][];
    }

    public int[][][] singleKnockOutAnalyze(Module module) {
        int[][][] result = new int[0][][];
        return result;
    }

    public int[][][] doubleKnockOutAnalyze(Module module) {
        int[][][] result = new int[0][][];
        return result;
    }

    public int[][][] singleRoundRobinAnalyze(Module module) {
        List<Match> matches = new ArrayList<>();
        List<Competitor> temp = new ArrayList<>();
        for (Group g : module.getGroups()) {
            matches.addAll(g.getMatches());
            for (Match m : g.getMatches()) {
                temp.addAll(Arrays.asList(m.getCompetitors()));
            }
        }
        Competitor[] competitors = temp.stream().distinct().toArray(v -> new Competitor[1]);

        int row = competitors.length + 1;
        int col = competitors.length + 5;
        int[][][] result = new int[2][row][col];

        //put id into #0 Row and Column
        Map<Competitor, Integer> map = new HashMap<>();
        for (int i = 0; i < competitors.length; ++i) {
            Competitor c = competitors[i];
            int index = i + 1;
            result[0][0][index]
                    = result[0][index][0]
                    = result[1][0][index]
                    = result[1][index][0]
                    = c.getId();
            map.put(c, index);
        }

        //put points into array
        matches.forEach((m) -> {
            Competitor[] cs = m.getCompetitors();
            int[] points = m.getPoints();
            result[0][map.get(cs[0])][map.get(cs[1])]
                    = result[1][map.get(cs[1])][map.get(cs[0])]
                    = points[0];
            result[0][map.get(cs[1])][map.get(cs[0])]
                    = result[1][map.get(cs[0])][map.get(cs[1])]
                    = points[1];
        });

        //score, rate and rank calculating
        int scoreIndex = col - 4;
        int winIndex = col - 3;
        int loseIndex = col - 2;
        int rankIndex = col - 1;

        //score (+2 for win, +1 for lose, +0 for giving up) and rate (win/lose)
        for (int i = 1; i < row; ++i) {
            for (int j = 1; j < scoreIndex; ++j) {
                if (result[0][i][j] != -1) {//-1 means giving up
                    if (result[0][i][j] > result[1][i][j]) {//win
                        result[0][i][scoreIndex] += 2;
                        result[0][i][winIndex]++;
                    } else if (result[0][i][j] < result[1][i][j]) {//lose
                        result[0][i][scoreIndex] += 1;
                        result[0][i][loseIndex]++;
                    }
                }
            }
        }

        //rank
        int[] indexes = new int[row];
        int[] scores = new int[row];
        for (int i = 1; i < indexes.length; ++i) {
            indexes[i] = i;
            scores[i] = result[0][i][scoreIndex];
        }
        //sort
        for (int i = 0; i < indexes.length - 1; ++i) {
            for (int j = 0; j < indexes.length - i; ++j) {
                if (scores[j] < scores[j + 1]) {
                    int t = scores[j];
                    scores[j] = scores[j + i];
                    scores[j + 1] = t;

                    t = indexes[j];
                    indexes[j] = indexes[j + i];
                    indexes[j + 1] = t;
                }
            }
        }
        //rank calculating
        for (int i = 1; i < indexes.length; ++i) {
            if (i < indexes.length - 1 && scores[i] == scores[i + 1]) {
                result[0][indexes[i]][rankIndex] = -i;
            } else if (i > 1 && scores[i] == scores[i - 1]) {
                result[0][indexes[i]][rankIndex] = -i;
            } else {
                result[0][indexes[i]][rankIndex] = i;
            }
        }

        return result;
    }

    public int[][][] doubleRoundRobinAnalyze(Module module) {
        int[][][] result = new int[0][][];
        return result;
    }
}
