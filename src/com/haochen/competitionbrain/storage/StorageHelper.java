package com.haochen.competitionbrain.storage;

import com.haochen.competitionbrain.bean.Bean;

/**
 * Created by Haochen on 2016/12/30.
 */
public interface StorageHelper {
    Bean get(int id);
    void save(Bean bean);
}
