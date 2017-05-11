package com.haochen.competitionresolver.server.main;

import com.haochen.competitionresolver.server.impl.network.socket.SocketMonitor;

/**
 * Created by Haochen on 2017/5/2.
 * TODO:
 */
public class Main {
    public static void main(String[] args) {
        new Launcher().setMonitor(new SocketMonitor()).launch();
    }
}
