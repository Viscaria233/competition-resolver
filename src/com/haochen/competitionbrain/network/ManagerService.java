package com.haochen.competitionbrain.network;

import com.haochen.competitionbrain.command.Command;
import com.haochen.competitionbrain.command.Result;

import java.io.IOException;

/**
 * Created by Haochen on 2016/12/30.
 */
public abstract class ManagerService extends TerminalService {
    @Override
    void prepare() throws IOException {
        if (io != null) {
            io.write("manager accepted");
            io.flush();
        }
    }

    @Override
    public String properties() {
        return super.properties() + "[Type: Manager]";
    }
}
