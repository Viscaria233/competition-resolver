package com.haochen.competitionbrain.main;

import com.haochen.competitionbrain.command.CommandHandler;
import com.haochen.competitionbrain.network.NetworkMonitor;
import com.haochen.competitionbrain.impl.network.socket.SocketMonitor;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Main {
    public static void main(String[] args) {
        CommandHandler handler = CommandHandler.getInstance();
        handler.start();

        NetworkMonitor monitor = new SocketMonitor();
        monitor.start();

        handler.interrupt();
    }
}
