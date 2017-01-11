package com.haochen.competitionbrain.network;

import com.haochen.competitionbrain.common.IProperties;
import com.haochen.competitionbrain.command.Command;
import com.haochen.competitionbrain.command.CommandHandler;
import com.haochen.competitionbrain.command.Committer;

import java.io.IOException;

/**
 * Created by Haochen on 2016/12/30.
 */
public abstract class TerminalService implements Committer, IProperties {
    protected NetworkIO io;
    public final void launch() {
        new Thread(() -> {
            try {
                prepare();
                Command cmd = getCommand();
                if (cmd != null) {
                    synchronized (CommandHandler.queue) {
                        CommandHandler.queue.offer(cmd);
                        CommandHandler.queue.notifyAll();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (io != null) {
                    try {
                        io.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    abstract void prepare() throws IOException;
    protected abstract Command getCommand() throws IOException;

    @Override
    public String properties() {
        return "[Terminal]";
    }
}
