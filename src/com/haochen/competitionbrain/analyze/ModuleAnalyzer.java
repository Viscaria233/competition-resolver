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
                return new SingleRoundRobinAnalyzer().analyze(module);
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

    public int[][][] doubleRoundRobinAnalyze(Module module) {
        int[][][] result = new int[0][][];
        return result;
    }
}
