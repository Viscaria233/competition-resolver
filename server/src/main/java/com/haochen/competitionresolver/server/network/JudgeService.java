package com.haochen.competitionresolver.server.network;

import java.io.IOException;

/**
 * Created by Haochen on 2016/12/30.
 */
public abstract class JudgeService extends TerminalService {
    @Override
    void prepare() throws IOException {
        if (io != null) {
            io.write("judge accepted");
            io.flush();
        }
    }

    @Override
    public String properties() {
        return super.properties() + "[Type: Judge]";
    }
}
