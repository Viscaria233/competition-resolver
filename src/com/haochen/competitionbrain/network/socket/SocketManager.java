package com.haochen.competitionbrain.network.socket;

import com.haochen.competitionbrain.network.ManagerService;

import java.net.Socket;

/**
 * Created by Haochen on 2016/12/30.
 */
public class SocketManager extends ManagerService {
    public SocketManager(Socket socket) {
        this.io = new SocketIO(socket);
    }
}
