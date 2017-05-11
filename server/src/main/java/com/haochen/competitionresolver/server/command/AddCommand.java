package com.haochen.competitionresolver.server.command;

import com.haochen.competitionresolver.server.bean.Bean;
import com.haochen.competitionresolver.server.impl.storage.test.TestStorageHelper;
import com.haochen.competitionresolver.server.storage.StorageHelper;

/**
 * Created by Haochen on 2017/1/2.
 */
public class AddCommand extends Command {
    private Bean bean;

    public AddCommand(ResultHandler resultHandler, Bean bean) {
        super(resultHandler);
        this.bean = bean;
    }

    @Override
    public int type() {
        return ADD;
    }

    @Override
    public Result execute() {
        StorageHelper helper = TestStorageHelper.getInstance();
        if (!helper.exists(bean)) {
            helper.save(bean);
            return new Result().setSuccess(true);
        } else {
            return new Result().setSuccess(false).setErrorMessage("Already exists");
        }
    }
}
