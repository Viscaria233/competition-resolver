package com.haochen.competitionbrain.command;

import com.haochen.competitionbrain.bean.Bean;
import com.haochen.competitionbrain.storage.StorageHelper;

/**
 * Created by Haochen on 2017/1/2.
 */
public class RemoveCommand extends Command {
    private Bean bean;

    public RemoveCommand(Committer committer, Bean bean) {
        super(committer);
        this.bean = bean;
    }

    @Override
    public int type() {
        return REMOVE;
    }

    @Override
    public Result execute() {
        StorageHelper helper = null;
        if (helper.exists(bean)) {
            helper.remove(bean);
            return new Result().setSuccess(true);
        } else {
            return new Result().setSuccess(false).setErrorMessage("Target not found");
        }
    }
}
