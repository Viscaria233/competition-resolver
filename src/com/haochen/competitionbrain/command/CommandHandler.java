package com.haochen.competitionbrain.command;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Haochen on 2017/1/7.
 */
public class CommandHandler extends Thread {
    private static CommandHandler instance;
    private boolean enable;
    public static final Queue<Command> queue = new LinkedList<>();

    private CommandHandler() {}

    public static CommandHandler getInstance() {
        if (instance == null) {
            instance = new CommandHandler();
        }
        return instance;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
        if (enable) {
            synchronized (queue) {
                queue.notifyAll();
            }
        }
    }

    public boolean isEnable() {
        return enable;
    }

    @Override
    public void run() {
        synchronized (queue) {
            try {
                while (true) {
                    while (!enable || queue.isEmpty()) {
                        queue.wait();
                    }
                    Command command = queue.poll();
                    if (command != null) {
                        Result result = command.execute();
                        command.committer.handleResult(result);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void interrupt() {
        queue.clear();
        super.interrupt();
    }
}
