package com.haochen.competitionresolver.server.network;

import com.haochen.competitionresolver.server.common.IProperties;

import java.io.IOException;
import java.util.*;

/**
 * Created by Haochen on 2016/12/30.
 */
public abstract class NetworkMonitor implements IProperties {
    public static final int MANAGER = 1;
    public static final int JUDGE = 2;
    public static final int TEST = 3;

    private List<TerminalService> terminals = new ArrayList<>();
    private Map<Date, String> connectHistory = new HashMap<>();
    protected boolean enable = true;

    public NetworkMonitor() {
        prepare();
    }

    public final void launch() {
        while (enable) {
            TerminalService terminal = waitForTerminal();
            if (terminal != null) {
                terminals.add(terminal);
                connectHistory.put(new Date(), terminal.properties());
                handle(terminal);
                terminals.remove(terminal);
            }
        }
    }

    protected abstract void prepare();
    protected abstract TerminalService waitForTerminal();
    protected abstract void handle(TerminalService terminal);

    public void stop() {
        enable = false;
    }

    @Override
    public String properties() {
        final StringBuilder builder = new StringBuilder(
                "[Monitor]\nConnected Count: " + connectHistory.size());

        connectHistory.entrySet().stream()
                .sorted((a, b) -> (int) (a.getKey().getTime() - b.getKey().getTime()))
                .forEach((e) -> builder.append("\n[").append(e.getKey()).append("]").append(e.getValue()));

        builder.append("\nNow Connecting Count: ").append(terminals.size());
        return builder.toString();
    }

    public abstract void newTerminal(int type) throws IOException;
}
