package com.haochen.competitionresolver.server.command;

import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * Created by Haochen on 2017/1/7.
 */
public enum CommandHandler {
    INSTANCE;

    private final Queue<Command> queue = new LinkedList<>();
    private Handler handler;
    static {
        INSTANCE.handler = new Handler(INSTANCE.queue);
    }

    public void addCommand(Command command) {
        queue.offer(command);
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void clear() {
        queue.clear();
    }

    public Stream<Command> stream() {
        return queue.stream();
    }

    public void setEnable(boolean enable) {
        handler.enable = enable;
        if (enable) {
            synchronized (queue) {
                queue.notifyAll();
            }
        }
    }

    public boolean isEnable() {
        return handler.enable;
    }

    public void launch() {
        if (!handler.isAlive()) {
            handler.start();
        }
    }

    public void close() {
        queue.clear();
        handler.quit = true;
        synchronized (Handler.class) {
            Handler.class.notifyAll();
        }
    }

    private static class Handler extends Thread {
        private final SoftReference<Queue<Command>> queue;
        volatile boolean quit = false;
        boolean enable = true;

        Handler(Queue<Command> queue) {
            this.queue = new SoftReference<>(queue);
        }

        @Override
        public void run() {
            Queue<Command> q = queue.get();
            while (q != null) {
                while (!quit && (!enable || q.isEmpty())) {
                    synchronized (Handler.class) {
                        try {
                            Handler.class.wait();
                        } catch (InterruptedException ignored) {}
                    }
                }
                if (quit) {
                    return;
                }
                Command command = q.poll();
                if (command != null) {
                    Result result = command.execute();
                    command.resultHandler.handleResult(result);
                }
            }
        }
    }
}
