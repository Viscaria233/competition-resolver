package com.haochen.competitionbrain.network.socket;

import com.haochen.competitionbrain.network.NetworkMonitor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Haochen on 2016/12/30.
 */
public class SocketMonitor extends NetworkMonitor {
    private ServerSocket server;

    @Override
    public void start() {
        try {
            this.server = new ServerSocket(2333);
            int localPort = server.getLocalPort();

            /**
             * 这里以某种方式显示localPort
             */
            System.out.println(localPort);

            while (true) {
                Socket socket = server.accept();
                System.out.println("accept: " + socket.getRemoteSocketAddress());

                Reader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                int type = in.read();
                System.out.println("type: " + type);

                if (type == MANAGER) {
                    new SocketManager(socket).launch();
                } else if (type == JUDGE) {
                    new SocketJudge(socket).launch();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
