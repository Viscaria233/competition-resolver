package com.haochen.competitionbrain.command;

import com.haochen.competitionbrain.bean.Bean;
import com.haochen.competitionbrain.storage.StorageHelper;

/**
 * Created by Haochen on 2017/1/2.
 */
public class AddCommand extends Command {
    private Bean bean;

    public AddCommand(Committer committer, Bean bean) {
        super(committer);
        this.bean = bean;
    }

    @Override
    public int type() {
        return ADD;
    }

    @Override
    public Result execute() {
        StorageHelper helper = null;
        if (!helper.exists(bean)) {
            helper.save(bean);
            return new Result().setSuccess(true);
        } else {
            return new Result().setSuccess(false).setErrorMessage("Already exists");
        }
    }
}
