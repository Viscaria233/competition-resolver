package com.haochen.competitionbrain.network.socket;

import com.haochen.competitionbrain.bean.Bean;
import com.haochen.competitionbrain.network.NetworkIO;

import java.io.*;
import java.net.Socket;

/**
 * Created by Haochen on 2016/12/30.
 */
public class SocketIO implements NetworkIO {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketIO(Socket socket) {
        this.socket = socket;
        try {
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(int n) throws IOException {
        out.write(n);
    }

    @Override
    public void write(Bean bean) throws IOException {
        out.writeObject(bean);
    }

    @Override
    public void write(String str) throws IOException {
        out.writeUTF(str);
    }

    @Override
    public int readInt() throws IOException {
        return in.readInt();
    }

    @Override
    public Bean readBean() throws IOException, ClassNotFoundException {
        return (Bean) in.readObject();
    }

    @Override
    public String readString() throws IOException {
        return in.readUTF();
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
