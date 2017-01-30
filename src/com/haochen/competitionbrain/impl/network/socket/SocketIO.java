package com.haochen.competitionbrain.impl.network.socket;

import com.haochen.competitionbrain.bean.Bean;
import com.haochen.competitionbrain.common.Config;
import com.haochen.competitionbrain.network.NetworkIO;
import com.haochen.xmlbuilder.XmlReader;
import com.haochen.xmlbuilder.XmlWriter;
import com.haochen.xmlbuilder.util.XmlUtil;

import java.io.*;
import java.net.Socket;

/**
 * Created by Haochen on 2016/12/30.
 */
public class SocketIO implements NetworkIO {
    private Socket socket;
    private InputStream in;
    private OutputStream out;

    public SocketIO(Socket socket) {
        this.socket = socket;
        try {
            this.out = socket.getOutputStream();
            this.in = socket.getInputStream();
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
        out.write(XmlUtil.xmlString(bean).getBytes());
    }

    @Override
    public void write(String str) throws IOException {
        out.write(str.getBytes(Config.CHAR_SET));
    }

    @Override
    public int readInt() throws IOException {
        return in.read();
    }

    @Override
    public Bean readBean() throws IOException, ClassNotFoundException {
        byte[] buf = new byte[1024];
        StringBuilder builder = new StringBuilder();
        while (in.read(buf) != -1) {
            builder.append(new String(buf, Config.CHAR_SET));
        }
        return (Bean) XmlUtil.xmlObject(builder.toString());
    }

    @Override
    public String readString() throws IOException {
        byte[] buf = new byte[1024];
        StringBuilder builder = new StringBuilder();
        while (in.read(buf) != -1) {
            builder.append(new String(buf, Config.CHAR_SET));
        }
        return builder.toString();
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

    @Override
    public String properties() {
        return socket.getRemoteSocketAddress().toString();
    }
}
