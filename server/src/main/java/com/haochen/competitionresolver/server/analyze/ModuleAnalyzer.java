package com.haochen.competitionresolver.server.analyze;

import com.haochen.competitionresolver.server.bean.Module;

/**
 * Created by Haochen on 2017/1/17.
 */
public class ModuleAnalyzer {
    public int[][][] analyze(Module module) {
        switch (module.getRule()) {
            case Module.SINGLE_KNOCKOUT:
                return singleKnockOutAnalyze(module);
            case Module.DOUBLE_KNOCKOUT:
                return doubleKnockOutAnalyze(module);
            case Module.SINGLE_ROUND_ROBIN:
                return singleRoundRobinAnalyze(module);
            case Module.DOUBLE_ROUND_ROBIN:
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
