package com.haochen.competitionresolver.server.command;

import com.haochen.competitionresolver.server.bean.Match;
import com.haochen.competitionresolver.server.impl.storage.test.TestStorageHelper;
import com.haochen.competitionresolver.server.storage.StorageHelper;

/**
 * Created by Haochen on 2017/1/2.
 */
public class CommitCommand extends Command {
    private Match match;

    public CommitCommand(ResultHandler resultHandler, Match match) {
        super(resultHandler);
        this.match = match;
    }

    @Override
    public int type() {
        return COMMIT_RESULT;
    }

    @Override
    public Result execute() {
        StorageHelper helper = TestStorageHelper.getInstance();
        if (helper.exists(match)) {
            helper.commitResult(match);
            return new Result().setSuccess(true);
        } else {
            return new Result().setSuccess(false).setErrorMessage("Match not found");
        }
    }
}
