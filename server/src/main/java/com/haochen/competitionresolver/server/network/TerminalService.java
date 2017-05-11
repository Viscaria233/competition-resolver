package com.haochen.competitionresolver.server.network;

import com.haochen.competitionresolver.server.common.IProperties;
import com.haochen.competitionresolver.server.command.Command;
import com.haochen.competitionresolver.server.command.CommandHandler;
import com.haochen.competitionresolver.server.command.ResultHandler;

import java.io.IOException;

/**
 * Created by Haochen on 2016/12/30.
 */
public abstract class TerminalService implements ResultHandler, IProperties {
    protected NetworkIO io;
    public final void launch() {
        new Thread(() -> {
            try {
                prepare();
                Command cmd = getCommand();
                if (cmd != null) {
                    synchronized (CommandHandler.class) {
                        CommandHandler handler = CommandHandler.INSTANCE;
                        handler.addCommand(cmd);
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
