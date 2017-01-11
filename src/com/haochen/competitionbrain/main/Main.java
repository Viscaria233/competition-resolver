package com.haochen.competitionbrain.main;

import com.haochen.competitionbrain.command.CommandHandler;
import com.haochen.competitionbrain.network.NetworkMonitor;
import com.haochen.competitionbrain.impl.network.socket.SocketMonitor;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Main {
    public static void main(String[] args) {
        CommandHandler handler = CommandHandler.getInstance();
        handler.start();

        NetworkMonitor monitor = new SocketMonitor();
        Thread monitorThread = new Thread(monitor::launch);
        monitorThread.start();

        commandLine(monitor, handler);
    }

    private static void commandLine(NetworkMonitor monitor, CommandHandler handler) {
        String ins;
        Scanner scanner = new Scanner(System.in);
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
                    continue;
                case "handler stop":
                    handler.setEnable(false);
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
            }
        } while (!ins.equals("exit"));
    }
}
