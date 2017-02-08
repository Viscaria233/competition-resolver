package com.haochen.competitionbrain.main;

import com.haochen.competitionbrain.command.Command;
import com.haochen.competitionbrain.command.CommandHandler;
import com.haochen.competitionbrain.impl.storage.sqlite.SqliteContext;
import com.haochen.competitionbrain.impl.storage.test.TestStorageHelper;
import com.haochen.competitionbrain.network.NetworkMonitor;
import com.haochen.competitionbrain.impl.network.socket.SocketMonitor;
import com.haochen.competitionbrain.storage.DbContext;
import com.haochen.competitionbrain.storage.StorageHelper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Main {
    public static void main(String[] args) {
        StorageHelper storageHelper = TestStorageHelper.getInstance();

        CommandHandler handler = CommandHandler.getInstance();
        handler.start();

        NetworkMonitor monitor = new SocketMonitor();
        Thread monitorThread = new Thread(monitor::launch);
        monitorThread.start();

        commandLine(monitor);
        storageHelper.commit();
    }

    private static void commandLine(NetworkMonitor monitor) {
        String ins;
        Scanner scanner = new Scanner(System.in);
        CommandHandler handler = CommandHandler.getInstance();
        do {
            System.out.print(">>>>");
            ins = scanner.nextLine();
            switch (ins) {
                case "exit":
                    monitor.stop();
                    handler.setEnable(true);
                    while (!CommandHandler.queue.isEmpty());
                    handler.setEnable(false);
                    handler.interrupt();
                    continue;
                case "handler start":
                    handler.setEnable(true);
                    System.out.println("CommandHandler is start");
                    continue;
                case "handler stop":
                    handler.setEnable(false);
                    System.out.println("CommandHandler is stop");
                    continue;
                case "queue":
                    System.out.println("[Command Queue] size: " + CommandHandler.queue.size());
                    for (Command c : CommandHandler.queue) {
                        System.out.println(c.getClass().getSimpleName());
                    }
                    continue;
                case "queue clear":
                    CommandHandler.queue.clear();
                    System.out.println("Command queue has cleaned");
                    continue;
                case "monitor":
                    System.out.println(monitor.properties());
                    continue;
            }
            if (ins.startsWith("new ")) {
                String type = ins.substring(4);
                try {
                    switch (type) {
                        case "manager":
                            monitor.newTerminal(NetworkMonitor.MANAGER);
                            break;
                        case "judge":
                            monitor.newTerminal(NetworkMonitor.JUDGE);
                            break;
                        case "test":
                            monitor.newTerminal(NetworkMonitor.TEST);
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (ins.startsWith("insert ")) {
                DbContext context = SqliteContext.getInstance();
                int count = context.executeUpdate(ins);
                System.out.println(count + " row(s) changed.");
            } else if (ins.startsWith("select ")) {
                DbContext context = SqliteContext.getInstance();
                ResultSet resultSet = context.executeQuery(ins);
                try {
                    while (resultSet.next()) {
                        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); ++i) {
                            System.out.print(resultSet.getString(i) + ", ");
                        }
                        System.out.println();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (ins.startsWith("delete ")) {
                DbContext context = SqliteContext.getInstance();
                int count = context.executeUpdate(ins);
                System.out.println(count + " row(s) changed.");
            }
        } while (!ins.equals("exit"));
    }
}
