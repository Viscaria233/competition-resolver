package com.haochen.competitionbrain.schedule;

import com.haochen.competitionbrain.bean.Competition;
import com.haochen.competitionbrain.bean.Module;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Schedule {
    public void arrange(Competition competition){
        for (Module module : competition.getModules()) {
            arrange(module);
        }
    }

    private void arrange(Module module) {
        switch (module.getRule()) {
            case Module.SINGLE_KNOCKOUT:
                singleKnockOutAnalyze(module);
                break;
            case Module.DOUBLE_KNOCKOUT:
                doubleKnockOutAnalyze(module);
                break;
            case Module.SINGLE_ROUND_ROBIN:
                singleRoundRobinAnalyze(module);
                break;
            case Module.DOUBLE_ROUND_ROBIN:
                doubleRoundRobinAnalyze(module);
                break;
        }
    }

    private void singleKnockOutAnalyze(Module module) {}

    private void doubleKnockOutAnalyze(Module module) {}

    private void singleRoundRobinAnalyze(Module module) {
        new SingleRoundRobinArranger().arrange(module);
    }

    private void doubleRoundRobinAnalyze(Module module) {
        new DoubleRoundRobinArranger().arrange(module);
    }
}
