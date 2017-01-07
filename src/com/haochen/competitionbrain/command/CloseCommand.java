package com.haochen.competitionbrain.command;

/**
 * Created by Haochen on 2017/1/2.
 */
public class CloseCommand extends Command {
    public CloseCommand(Committer committer) {
        super(committer);
    }

    @Override
    public int type() {
        return CLOSE;
    }

    @Override
    public Result execute() {
        //Do nothing
        return null;
    }
}
