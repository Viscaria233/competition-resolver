package com.haochen.competitionbrain.impl.network.socket;

import com.haochen.competitionbrain.network.JudgeService;

import java.net.Socket;

/**
 * Created by Haochen on 2016/12/30.
 */
public class SocketJudge extends JudgeService {
    public SocketJudge(Socket socket) {
        this.io = new SocketIO(socket);
    }
}
