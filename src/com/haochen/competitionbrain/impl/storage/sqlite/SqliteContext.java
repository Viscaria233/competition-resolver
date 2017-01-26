package com.haochen.competitionbrain.impl.storage.sqlite;

import com.haochen.competitionbrain.storage.DbContext;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Haochen on 2017/1/25.
 */
public class SqliteContext extends DbContext {
    private static final String url = "jdbc:sqlite:brain.db";
    private static DbContext instance;

    private SqliteContext() {}

    public static DbContext getInstance() {
        if (instance == null) {
            instance = new SqliteContext();
        }
        return instance;
    }

    @Override
    protected void onCreate(Statement statement) throws SQLException {
        statement.setQueryTimeout(30);
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS athlete(" +
                "_id INTEGER PRIMARY KEY," +
                "name VARCHAR(20)," +
                "age UNSIGNED TINYINT," +
                "sex UNSIGNED TINYINT," +
                "score UNSIGNED SMALLINT," +
                "team_id INTEGER," +
                "tel VARCHAR(15))");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS team(" +
                "_id INTEGER PRIMARY KEY," +
                "name VARCHAR(20)," +
                "leader_id INTEGER)");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS indv_competition(" +
                "_id INTEGER PRIMARY KEY," +
                "name VARCHAR(20)," +
                "res_name VARCHAR(20))");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS team_competition(" +
                "_id INTEGER PRIMARY KEY," +
                "name VARCHAR(20)," +
                "res_name VARCHAR(20))");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS indv_sign_up(" +
                "competitor_id INTEGER," +
                "competition_id INTEGER," +
                "PRIMARY KEY(competitor_id, competition_id))");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS team_sign_up(" +
                "competitor_id INTEGER," +
                "competition_id INTEGER," +
                "PRIMARY KEY(competitor_id, competition_id))");
    }

    @Override
    protected String getDbUrl() {
        return url;
    }
}
