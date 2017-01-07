package com.haochen.competitionbrain.command;

import com.haochen.competitionbrain.bean.Match;
import com.haochen.competitionbrain.storage.StorageHelper;

/**
 * Created by Haochen on 2017/1/2.
 */
public class CommitCommand extends Command {
    private Match match;

    public CommitCommand(Committer committer, Match match) {
        super(committer);
        this.match = match;
    }

    @Override
    public int type() {
        return COMMIT_RESULT;
    }

    @Override
    public Result execute() {
        StorageHelper helper = null;
        if (helper.exists(match)) {
            helper.commit(match);
            return new Result().setSuccess(true);
        } else {
            return new Result().setSuccess(false).setErrorMessage("Match not found");
        }
    }
}
