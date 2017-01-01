package com.haochen.competitionbrain.network;

import java.io.IOException;

/**
 * Created by Haochen on 2016/12/30.
 */
public abstract class TerminalService {
    protected NetworkIO io;
    public final void launch() {
        new Thread(() -> {
            try {
                start();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    io.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    abstract void start() throws IOException;
    abstract int getMessage() throws IOException;
}
