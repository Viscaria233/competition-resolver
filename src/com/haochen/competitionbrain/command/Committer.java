package com.haochen.competitionbrain.command;

/**
 * Created by Haochen on 2017/1/7.
 */
public interface Committer {
    void handleResult(Result result);
}
