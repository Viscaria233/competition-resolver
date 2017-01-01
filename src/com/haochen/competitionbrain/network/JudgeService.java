package com.haochen.competitionbrain.network;

import java.io.IOException;

/**
 * Created by Haochen on 2016/12/30.
 */
public abstract class JudgeService extends TerminalService {
    @Override
    void start() throws IOException {
        io.write("judge accepted");
        io.flush();
        while (true) {
            int msg = getMessage();
            switch (msg) {
                case 1:
                    return;
                case 2:
            }
        }
    }
}
