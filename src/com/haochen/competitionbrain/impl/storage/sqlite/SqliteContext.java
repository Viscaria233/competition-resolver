package com.haochen.competitionbrain.impl.storage.sqlite;

import com.haochen.competitionbrain.storage.DbContext;

import java.sql.SQLException;

/**
 * Created by Haochen on 2017/1/25.
 */
public class SqliteContext extends DbContext {
    private static final String url = "jdbc:sqlite:brain.db";
    private static DbContext instance;

    private SqliteContext() {}

    public static DbContext getInstance() {
        if (instance == null) {
            synchronized (SqliteContext.class) {
                if (instance == null) {
                    instance = new SqliteContext();
                }
            }
        }
        return instance;
    }

    @Override
    protected void onCreate() throws SQLException {
        setQueryTimeout(30);
        executeUpdate("CREATE TABLE IF NOT EXISTS athlete(" +
                "_id INTEGER PRIMARY KEY," +
                "name VARCHAR(20)," +
                "age UNSIGNED TINYINT," +
                "sex UNSIGNED TINYINT," +
                "score UNSIGNED SMALLINT," +
                "team_id INTEGER," +
                "tel VARCHAR(15))");
        executeUpdate("CREATE TABLE IF NOT EXISTS team(" +
                "_id INTEGER PRIMARY KEY," +
                "name VARCHAR(20)," +
                "leader_id INTEGER)");
        //type = 1(individual), 2(team)
        executeUpdate("CREATE TABLE IF NOT EXISTS competition(" +
                "_id INTEGER PRIMARY KEY," +
                "name VARCHAR(20)," +
                "type UNSIGNED TINYINT," +
                "res_name VARCHAR(20))");
        executeUpdate("CREATE TABLE IF NOT EXISTS sign_up(" +
                "competitor_id INTEGER," +
                "competition_id INTEGER," +
                "type UNSIGNED TINYINT," +
                "PRIMARY KEY(competitor_id, competition_id))");
    }

    @Override
    protected String getDbUrl() {
        return url;
    }
}
