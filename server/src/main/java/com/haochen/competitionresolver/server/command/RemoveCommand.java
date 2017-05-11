package com.haochen.competitionresolver.server.command;

import com.haochen.competitionresolver.server.bean.Bean;
import com.haochen.competitionresolver.server.impl.storage.test.TestStorageHelper;
import com.haochen.competitionresolver.server.storage.StorageHelper;

/**
 * Created by Haochen on 2017/1/2.
 */
public class RemoveCommand extends Command {
    private Bean bean;

    public RemoveCommand(ResultHandler resultHandler, Bean bean) {
        super(resultHandler);
        this.bean = bean;
    }

    @Override
    public int type() {
        return REMOVE;
    }

    @Override
    public Result execute() {
        StorageHelper helper = TestStorageHelper.getInstance();
        if (helper.exists(bean)) {
            helper.remove(bean.getId());
            return new Result().setSuccess(true);
        } else {
            return new Result().setSuccess(false).setErrorMessage("Target not found");
        }
    }
}
