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
        try {
            onCreate(getStatement());
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    protected abstract void onCreate(Statement statement) throws SQLException;

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

    public int update(String sql, Object... values) {
        if (!isReady()) {
            return 0;
        }

        String replaced = replaceSql(sql, values);
        if (replaced == null) {
            return 0;
        }

        int result = 0;
        try {
            result = statement.executeUpdate(replaced);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return result;
    }

    public ResultSet query(String sql, Object... values) {
        if (!isReady()) {
            return null;
        }

        String replaced = replaceSql(sql, values);
        if (replaced == null) {
            return null;
        }

        ResultSet result = null;
        try {
            result = statement.executeQuery(replaced);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return result;
    }

    public int count(String tableName) {
        if (!isReady()) {
            return 0;
        }

        String sql = "SELECT COUNT(*) FROM " + tableName;

        ResultSet result = null;
        try {
            result = statement.executeQuery(sql);
            return result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return 0;
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

    protected String replaceSql(String sql, Object... values) {
        int index = 0;
        while (sql.contains("?")) {
            if (index >= values.length) {
                return null;
            }
            sql = sql.replaceFirst("(?)", values[index].toString());
            index++;
        }
        return sql;
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
