package com.haochen.competitionbrain.impl.network.socket;

import com.haochen.competitionbrain.command.Command;
import com.haochen.competitionbrain.command.Result;
import com.haochen.competitionbrain.command.TestCommand;
import com.haochen.competitionbrain.network.ManagerService;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Haochen on 2016/12/30.
 */
public class SocketManager extends ManagerService {
    public SocketManager(Socket socket) {
        this.io = new SocketIO(socket);
    }

    @Override
    protected Command getCommand() throws IOException {
        return new TestCommand(this);
    }

    @Override
    public String properties() {
        return super.properties() + "[Address: " + this.io.properties() + "]";
    }

    @Override
    public void handleResult(Result result) {
        System.out.println(result.isSuccess() ? result.getResultObject() : result.getErrorMessage());
    }
}
