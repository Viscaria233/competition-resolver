package com.haochen.competitionbrain.storage;

import com.haochen.competitionbrain.bean.Bean;

/**
 * Created by Haochen on 2017/1/2.
 */
public class SearchTerm {
    public int id;
    public Class<? extends Bean> clazz;

    public boolean test(Bean bean) {
        return bean != null && clazz.isInstance(bean) && (
                bean.getId() == id
        );
    }
}
