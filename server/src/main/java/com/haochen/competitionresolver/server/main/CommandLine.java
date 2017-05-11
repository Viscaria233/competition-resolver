package com.haochen.competitionresolver.server.main;

import com.haochen.competitionresolver.server.command.CommandHandler;
import com.haochen.competitionresolver.server.impl.storage.sqlite.SqliteContext;
import com.haochen.competitionresolver.server.network.NetworkMonitor;
import com.haochen.competitionresolver.server.storage.DbContext;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Haochen on 2017/5/2.
 * TODO:
 */
public class CommandLine {
    private NetworkMonitor monitor;

    CommandLine(NetworkMonitor monitor) {
        this.monitor = monitor;
    }

    void launch() {
        String ins;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print(">>>>");
            ins = scanner.nextLine();
            String result = execute(ins);
            if (result != null) {
                System.out.println(result);
            }
        } while (!ins.equals("exit"));
        scanner.close();
    }

    String execute(String ins) {
        CommandHandler handler = CommandHandler.INSTANCE;
        switch (ins) {
            case Command.EXIT:
                return Message.EXIT;
            case Command.HANDLER_START:
                handler.setEnable(true);
                return Message.HANDLER_START;
            case Command.HANDLER_STOP:
                handler.setEnable(false);
                return Message.HANDLER_STOP;
            case Command.QUEUE_STATE:
                return Message.queueState(handler);
            case Command.QUEUE_CLEAR:
                handler.clear();
                return Message.QUEUE_CLEAR;
            case Command.MONITOR_STATE:
                return Message.monitorState(monitor);
        }
        if (ins.startsWith(Command.NEW)) {
            String type = ins.substring(4);
            try {
                switch (type) {
                    case Command.NEW_MANAGER:
                        monitor.newTerminal(NetworkMonitor.MANAGER);
                        break;
                    case Command.NEW_JUDGE:
                        monitor.newTerminal(NetworkMonitor.JUDGE);
                        break;
                    case Command.NEW_TEST:
                        monitor.newTerminal(NetworkMonitor.TEST);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ins.startsWith(Command.INSERT)
                || ins.startsWith(Command.DELETE)
                || ins.startsWith(Command.UPDATE)) {
            DbContext context = SqliteContext.getInstance();
            int count = context.executeUpdate(ins);
            return Message.dbChanged(count);
        } else if (ins.startsWith(Command.SELECT)) {
            DbContext context = SqliteContext.getInstance();
            ResultSet resultSet = context.executeQuery(ins);
            return Message.dbQuery(resultSet);
        }
        return null;
    }

    public static final class Command {
        public static final String EXIT = "exit";
        public static final String HANDLER_START = "handler start";
        public static final String HANDLER_STOP = "handler stop";
        public static final String QUEUE_STATE = "queue";
        public static final String QUEUE_CLEAR = "queue clear";
        public static final String MONITOR_STATE = "monitor";
        public static final String NEW = "new ";
        public static final String NEW_MANAGER = "manager";
        public static final String NEW_JUDGE = "judge";
        public static final String NEW_TEST = "test";
        public static final String INSERT = "insert ";
        public static final String DELETE = "delete ";
        public static final String UPDATE = "update ";
        public static final String SELECT = "select ";
    }

    public static final class Message {
        public static final String EXIT = "Exit";
        public static final String HANDLER_START = "CommandHandler is start";
        public static final String HANDLER_STOP = "CommandHandler is stop";
        public static final String QUEUE_CLEAR = "Command queue has cleaned";

        public static String dbChanged(int rowCount) {
            return rowCount + " row(s) changed.";
        }

        public static String dbQuery(ResultSet resultSet) {
            StringBuilder builder = new StringBuilder();
            try {
                while (resultSet.next()) {
                    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); ++i) {
                        builder.append(resultSet.getString(i)).append(", ");
                    }
                    builder.append('\n');
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return builder.toString();
        }

        public static String monitorState(NetworkMonitor monitor) {
            return monitor.properties();
        }

        public static String queueState(CommandHandler handler) {
            StringBuilder builder = new StringBuilder("[Command Queue] size: " + handler.size() + '\n');
            handler.stream().forEach(c ->
                    builder.append(c.getClass().getSimpleName()).append('\n'));
            return builder.toString();
        }
    }
}
