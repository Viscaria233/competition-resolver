package com.haochen.competitionbrain.network;

/**
 * Created by Haochen on 2016/12/30.
 */
public abstract class NetworkMonitor {
    public static final int MANAGER = 1;
    public static final int JUDGE = 2;

    public abstract void start();
}
