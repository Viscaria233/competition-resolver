package com.haochen.competitionresolver.server.impl.storage.sqlite;

import com.haochen.competitionresolver.server.storage.DbContext;

import java.sql.SQLException;

/**
 * Created by Haochen on 2017/1/25.
 */
public class SqliteContext extends DbContext {
    private static final String url = "jdbc:sqlite:brain.db";
    private static volatile DbContext instance;

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
                "tel VARCHAR(15)," +
                "score UNSIGNED SMALLINT)");
        executeUpdate("CREATE TABLE IF NOT EXISTS team(" +
                "_id INTEGER PRIMARY KEY," +
                "name VARCHAR(20)," +
                "leader_id INTEGER)");
        executeUpdate("CREATE TABLE IF NOT EXISTS join_team(" +
                "athlete_id INTEGER," +
                "team_id INTEGER," +
                "PRIMARY KEY(athlete_id, team_id))");
        //type = 1(individual), 2(team)
        executeUpdate("CREATE TABLE IF NOT EXISTS competition(" +
                "_id INTEGER PRIMARY KEY," +
                "name VARCHAR(20)," +
                "type UNSIGNED TINYINT," +
                "detail_filename VARCHAR(100))");
        executeUpdate("CREATE TABLE IF NOT EXISTS sign_up(" +
                "competitor_id INTEGER," +
                "competition_id INTEGER," +
                "type UNSIGNED TINYINT," +
                "PRIMARY KEY(competitor_id, competition_id))");
        executeUpdate("CREATE TABLE IF NOT EXISTS busy_athlete(" +
                "athlete_id INTEGER," +
                "competition_id INTEGER," +
                "busy TINYINT," +
                "PRIMARY KEY(athlete_id, competition_id))");
        executeUpdate("CREATE TABLE IF NOT EXISTS prize(" +
                "competitor_id INTEGER," +
                "competition_id INTEGER," +
                "name VARCHAR(20)," +
                "PRIMARY KEY(competitor_id, competition_id, name))");
    }

    @Override
    protected String getDbUrl() {
        return url;
    }
}
