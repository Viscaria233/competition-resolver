package com.haochen.competitionbrain.analyze;

import com.haochen.competitionbrain.bean.*;
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

    private int[][][] singleKnockOutAnalyze(Module module) {
        int[][][] result = new int[0][][];
        return result;
    }

    private int[][][] doubleKnockOutAnalyze(Module module) {
        int[][][] result = new int[0][][];
        return result;
    }

    private int[][][] singleRoundRobinAnalyze(Module module) {
        return new SingleRoundRobinAnalyzer().analyze(module);
    }

    private int[][][] doubleRoundRobinAnalyze(Module module) {
        return new DoubleRoundRobinAnalyzer().analyze(module);
    }
}
