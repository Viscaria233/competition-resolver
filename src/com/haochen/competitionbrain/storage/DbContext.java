package com.haochen.competitionbrain.storage;

import java.sql.*;

/**
 * Created by Haochen on 2017/1/25.
 */
public abstract class DbContext {
    protected String dbUrl;
    protected Connection connection;
    protected Statement statement;

    public DbContext() {
        init();
        onCreate();
    }

    protected abstract void onCreate();

    public void setQueryTimeout(int second) {
        if (!isReady()) {
            return;
        }
        try {
            statement.setQueryTimeout(second);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    public int getQueryTimeout() {
        if (!isReady()) {
            return 0;
        }
        int result = 0;
        try {
            result = statement.getQueryTimeout();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return result;
    }

    public int update(String sql) {
        if (!isReady()) {
            return 0;
        }
        int result = 0;
        try {
            result = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return result;
    }

    public ResultSet query(String sql) {
        if (!isReady()) {
            return null;
        }
        ResultSet result = null;
        try {
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return result;
    }

    public void close() {
        if (!isReady()) {
            return;
        }
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            statement = null;
            connection = null;
        }
    }

    protected abstract String getDbUrl();

    private void init() {
        dbUrl = getDbUrl();
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(dbUrl);
            }
            if (statement == null) {
                statement = connection.createStatement();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    protected boolean isReady() {
        return connection != null && statement != null;
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
