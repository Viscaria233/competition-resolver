package com.haochen.competitionbrain.network;

import com.haochen.competitionbrain.bean.Bean;

import java.io.IOException;

/**
 * Created by Haochen on 2016/12/30.
 */
public interface NetworkIO {
    void write(int n) throws IOException;
    void write(String str) throws IOException;
    void write(Bean bean) throws IOException;

    int readInt() throws IOException;
    String readString() throws IOException;
    Bean readBean() throws IOException, ClassNotFoundException;

    void flush() throws IOException;
    void close() throws IOException;
}
