package com.haochen.competitionbrain.command;

import com.haochen.competitionbrain.bean.Bean;
import com.haochen.competitionbrain.impl.storage.test.TestStorageHelper;
import com.haochen.competitionbrain.storage.SearchTerm;
import com.haochen.competitionbrain.storage.StorageHelper;

/**
 * Created by Haochen on 2017/1/2.
 */
public class SearchCommand extends Command {
    private SearchTerm term;

    public SearchCommand(Committer committer, SearchTerm term) {
        super(committer);
        this.term = term;
    }

    @Override
    public int type() {
        return SEARCH;
    }

    @Override
    public Result execute() {
        StorageHelper helper = TestStorageHelper.getInstance();
        Bean[] beans = helper.search(term);
        if (beans != null && beans.length > 0) {
            return new Result().setSuccess(true).setResultObject(beans);
        } else {
            return new Result().setSuccess(false);
        }
    }
}
