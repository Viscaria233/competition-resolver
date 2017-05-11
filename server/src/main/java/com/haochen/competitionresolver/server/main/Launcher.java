package com.haochen.competitionresolver.server.main;

import com.haochen.competitionresolver.server.command.CommandHandler;
import com.haochen.competitionresolver.server.impl.storage.test.TestStorageHelper;
import com.haochen.competitionresolver.server.network.NetworkMonitor;
import com.haochen.competitionresolver.server.storage.StorageHelper;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Launcher {
    private NetworkMonitor monitor;
    private CommandLine commandLine;

    public Launcher() {}

    public Launcher(NetworkMonitor monitor) {
        this.monitor = monitor;
        this.commandLine = new CommandLine(monitor);
    }

    public Launcher setMonitor(NetworkMonitor monitor) {
        this.monitor = monitor;
        this.commandLine = new CommandLine(monitor);
        return this;
    }

    public String execute(String ins) {
        return commandLine.execute(ins);
    }

    public void launch() {
        StorageHelper storageHelper = TestStorageHelper.getInstance();

        CommandHandler handler = CommandHandler.INSTANCE;
        handler.launch();

        Thread monitorThread = new Thread(monitor::launch);
        monitorThread.start();

        commandLine.launch();

        storageHelper.commit();

        monitor.stop();
        handler.close();
    }
}
