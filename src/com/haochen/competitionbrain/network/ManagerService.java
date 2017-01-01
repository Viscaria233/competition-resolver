package com.haochen.competitionbrain.network;

import java.io.IOException;

/**
 * Created by Haochen on 2016/12/30.
 */
public abstract class ManagerService extends TerminalService {
    public static final int CLOSE = 1;

    @Override
    void start() throws IOException {
        io.write("manager accepted");
        io.flush();
        while (true) {
            int msg = getMessage();
            switch (msg) {
                case CLOSE:
                    return;
                case 2:
            }
        }
    }
}
