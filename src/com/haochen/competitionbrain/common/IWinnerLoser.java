package com.haochen.competitionbrain.common;

import com.haochen.competitionbrain.bean.Competitor;

/**
 * Created by Haochen on 2017/1/26.
 */
public interface IWinnerLoser {
    Competitor getWinner();
    Competitor getLoser();
    void setWinner(int winner);
}
