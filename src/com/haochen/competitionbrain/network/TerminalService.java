package com.haochen.competitionbrain.network;

import com.haochen.competitionbrain.command.Command;
import com.haochen.competitionbrain.command.CommandHandler;
import com.haochen.competitionbrain.command.Committer;
import com.haochen.competitionbrain.command.Result;

import java.io.IOException;

/**
 * Created by Haochen on 2016/12/30.
 */
public abstract class TerminalService implements Committer {
    protected NetworkIO io;
    public final void launch() {
        new Thread(() -> {
            try {
                start();
                while (true) {
                    Command cmd = getCommand();
                    if (cmd != null) {
                        if (cmd.type() == Command.CLOSE) {
                            break;
                        } else {
                            synchronized (CommandHandler.queue) {
                                CommandHandler.queue.offer(cmd);
                                CommandHandler.queue.notifyAll();
                            }
                        }
                    }
                }
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
    abstract Command getCommand() throws IOException;
}
