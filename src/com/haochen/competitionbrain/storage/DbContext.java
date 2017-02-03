package com.haochen.competitionbrain.storage;

import java.sql.*;

/**
 * Created by Haochen on 2017/1/25.
 */
public abstract class DbContext {
    protected String dbUrl;
    protected Connection connection;
    protected Statement statement;

    protected DbContext() {
        init();
        try {
            onCreate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

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

    protected abstract void onCreate() throws SQLException;
    protected abstract String getDbUrl();

    public void setQueryTimeout(int second) {
        try {
            statement.setQueryTimeout(second);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    public int getQueryTimeout() {
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
        String replaced = replaceSql(sql, values);
        if (replaced == null) {
            return 0;
        }

        try {
            return statement.executeUpdate(replaced);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return 0;
    }

    public ResultSet query(String sql, Object... values) {
        String replaced = replaceSql(sql, values);
        if (replaced == null) {
            return null;
        }

        try {
            return statement.executeQuery(replaced);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return null;
    }

    public int count(String tableName) {
        String sql = "SELECT COUNT(*) FROM " + tableName;

        try {
            return statement.executeQuery(sql).getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return 0;
    }

    public boolean execute(String sql) {
        try {
            return statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int executeUpdate(String sql) {
        try {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ResultSet executeQuery(String sql) {
        try {
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void commit() {
        if (connection == null) {
             return;
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            statement = null;
            connection = null;
        }
    }

    private String replaceSql(String sql, Object... values) {
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
}
