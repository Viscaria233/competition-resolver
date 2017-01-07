package com.haochen.competitionbrain.network;

import com.haochen.competitionbrain.command.Command;
import com.haochen.competitionbrain.command.Result;

import java.io.IOException;

/**
 * Created by Haochen on 2016/12/30.
 */
public abstract class ManagerService extends TerminalService {
    @Override
    void start() throws IOException {
        io.write("manager accepted");
        io.flush();
    }

    @Override
    Command getCommand() throws IOException {
        return null;
    }

    @Override
    public void handleResult(Result result) {

    }
}
