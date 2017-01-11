package com.haochen.competitionbrain.impl.storage.test;

import com.haochen.competitionbrain.bean.Bean;
import com.haochen.competitionbrain.bean.Match;
import com.haochen.competitionbrain.storage.SearchTerm;
import com.haochen.competitionbrain.storage.StorageHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haochen on 2017/1/9.
 */
public class TestStorageHelper implements StorageHelper {
    private static TestStorageHelper instance;

    private static final List<Bean> beans = new ArrayList<>();

    private TestStorageHelper() {}

    public static TestStorageHelper getInstance() {
        if (instance == null) {
            instance = new TestStorageHelper();
        }
        return instance;
    }

    @Override
    public Bean get(Class<? extends Bean> c, int id) {
        return beans.stream()
                .filter((b) -> c.isInstance(b) && b.getId() == id)
                .findFirst().get();
    }

    @Override
    public void save(Bean bean) {
        beans.add(bean);
    }

    @Override
    public void remove(Bean bean) {
        beans.remove(bean);
    }

    @Override
    public Bean[] search(SearchTerm term) {
        return beans.stream()
                .filter(term::test)
                .toArray(Bean[]::new);
    }

    @Override
    public void commit(Match match) {
        Bean bean = beans.stream()
                .filter((b) -> b.equals(match))
                .findFirst().get();
        beans.remove(bean);
        beans.add(match);
    }

    @Override
    public boolean exists(Bean bean) {
        return beans.stream()
                .filter((b) -> b.equals(bean))
                .count() > 0;
    }
}
