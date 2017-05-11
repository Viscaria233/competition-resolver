package com.haochen.competitionresolver.server.command;

import com.haochen.competitionresolver.server.bean.Bean;
import com.haochen.competitionresolver.server.impl.storage.test.TestStorageHelper;
import com.haochen.competitionresolver.server.storage.SearchTerm;
import com.haochen.competitionresolver.server.storage.StorageHelper;

/**
 * Created by Haochen on 2017/1/2.
 */
public class SearchCommand extends Command {
    private SearchTerm term;

    public SearchCommand(ResultHandler resultHandler, SearchTerm term) {
        super(resultHandler);
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
