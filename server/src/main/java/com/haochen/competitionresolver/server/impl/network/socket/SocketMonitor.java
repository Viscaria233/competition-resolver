package com.haochen.competitionresolver.server.impl.network.socket;

import com.haochen.competitionresolver.server.network.NetworkIO;
import com.haochen.competitionresolver.server.network.NetworkMonitor;
import com.haochen.competitionresolver.server.network.TerminalService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Haochen on 2016/12/30.
 */
public class SocketMonitor extends NetworkMonitor {
    private static final int END_WAITING = 100;

    private ServerSocket server;

    @Override
    protected void prepare() {
        for (int tempPort = 2333; this.server == null ; ++tempPort) {
            try {
                this.server = new ServerSocket(tempPort);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //这里用某种方式显示localPort
        int localPort = server.getLocalPort();
        System.out.println("Local Port: " + localPort);
    }

    @Override
    protected TerminalService waitForTerminal() {
        try {
            Socket socket = server.accept();

            NetworkIO io = new SocketIO(socket);
            int type = io.readInt();

            if (type != END_WAITING) {
                if (type == TEST) {
                    io.write(TEST);
                    io.flush();
                    io.close();
                } else {
                    System.out.println("accept: " + socket.getRemoteSocketAddress());
                    System.out.println("type: " + type);

                    if (type == MANAGER) {
                        return new SocketManager(socket);
                    } else if (type == JUDGE) {
                        return new SocketJudge(socket);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void handle(TerminalService terminal) {
        terminal.launch();
    }

    @Override
    public void stop() {
        try {
            Socket socket = new Socket("localhost", server.getLocalPort());
            enable = false;
            OutputStream os = socket.getOutputStream();
            os.write(END_WAITING);
            os.flush();
            socket.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String properties() {
        return super.properties() + "\nBy Socket\nLocal Port: " + server.getLocalPort();
    }

    @Override
    public void newTerminal(int type) throws IOException {
        if (type != END_WAITING) {
            Socket socket = new Socket("localhost", server.getLocalPort());
            NetworkIO io = new SocketIO(socket);
            switch (type) {
                case MANAGER:
                    io.write(MANAGER);
                    io.flush();
                    break;
                case JUDGE:
                    io.write(JUDGE);
                    io.flush();
                    break;
                case TEST:
                    io.write(TEST);
                    io.flush();
                    int result = io.readInt();
                    if (result == TEST) {
                        System.out.println("Test success");
                    }
                    break;
            }
            io.close();
        }
    }
}
