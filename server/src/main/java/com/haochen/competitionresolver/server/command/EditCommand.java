package com.haochen.competitionresolver.server.command;

import com.haochen.competitionresolver.server.bean.Bean;
import com.haochen.competitionresolver.server.impl.storage.test.TestStorageHelper;
import com.haochen.competitionresolver.server.storage.StorageHelper;

/**
 * Created by Haochen on 2017/1/2.
 */
public class EditCommand extends Command {
    private Bean before;
    private Bean after;

    public EditCommand(ResultHandler resultHandler, Bean before, Bean after) {
        super(resultHandler);
        this.before = before;
        this.after = after;
    }

    @Override
    public int type() {
        return EDIT;
    }

    @Override
    public Result execute() {
        StorageHelper helper = TestStorageHelper.getInstance();
        if (before.getId() == after.getId()) {
            if (helper.exists(before)) {
                if (!helper.exists(after)) {
                    helper.save(after);
                    return new Result().setSuccess(true);
                } else {
                    return new Result().setSuccess(false).setErrorMessage("Already exists");
                }
            } else {
                return new Result().setSuccess(false).setErrorMessage("Target not found");
            }
        } else {
            return new Result().setSuccess(false).setErrorMessage("ID can not be change");
        }
    }
}
