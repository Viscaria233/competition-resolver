package com.haochen.competitionbrain.storage;

import com.haochen.competitionbrain.bean.Bean;
import com.haochen.competitionbrain.bean.Match;

/**
 * Created by Haochen on 2016/12/30.
 */
public interface StorageHelper {
    Bean get(Class<? extends Bean> c, int id);
    void save(Bean bean);
    void remove(Bean bean);
    Bean[] search(SearchTerm term);
    void commit(Match match);
    boolean exists(Bean bean);
}
