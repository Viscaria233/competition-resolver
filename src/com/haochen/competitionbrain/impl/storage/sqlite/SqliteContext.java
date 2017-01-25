package com.haochen.competitionbrain.impl.storage.sqlite;

import com.haochen.competitionbrain.storage.DbContext;

/**
 * Created by Haochen on 2017/1/25.
 */
public class SqliteContext extends DbContext {
    private static final String url = "jdbc:sqlite:brain.db";
    private static DbContext instance;

    private SqliteContext() {}

    @Override
    protected void onCreate() {
        
    }

    public static DbContext getInstance() {
        if (instance == null) {
            instance = new SqliteContext();
        }
        return instance;
    }

    @Override
    protected String getDbUrl() {
        return url;
    }
}
