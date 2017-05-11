package com.haochen.competitionresolver.server.common;

import com.haochen.competitionresolver.server.bean.Competitor;

/**
 * Created by Haochen on 2017/1/26.
 */
public interface IWinnerLoser {
    Competitor getWinner();
    Competitor getLoser();
    void setWinner(int winner);
}
