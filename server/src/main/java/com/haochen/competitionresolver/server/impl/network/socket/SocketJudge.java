package com.haochen.competitionresolver.server.impl.network.socket;

import com.haochen.competitionresolver.server.command.Command;
import com.haochen.competitionresolver.server.command.Result;
import com.haochen.competitionresolver.server.command.TestCommand;
import com.haochen.competitionresolver.server.network.JudgeService;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Haochen on 2016/12/30.
 */
public class SocketJudge extends JudgeService {
    public SocketJudge(Socket socket) {
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
