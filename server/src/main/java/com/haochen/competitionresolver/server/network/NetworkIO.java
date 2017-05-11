package com.haochen.competitionresolver.server.network;

import com.haochen.competitionresolver.server.common.IProperties;
import com.haochen.competitionresolver.server.bean.Bean;

import java.io.IOException;

/**
 * Created by Haochen on 2016/12/30.
 */
public interface NetworkIO extends IProperties {
    void write(int n) throws IOException;
    void write(String str) throws IOException;
    void write(Bean bean) throws IOException;

    int readInt() throws IOException;
    String readString() throws IOException;
    Bean readBean() throws IOException, ClassNotFoundException;

    void flush() throws IOException;
    void close() throws IOException;
}
