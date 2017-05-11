package com.haochen.competitionresolver.server.command;

/**
 * Created by Haochen on 2017/1/1.
 */
public abstract class Command {
    public static final int ADD = 1;
    public static final int REMOVE = 2;
    public static final int EDIT = 3;
    public static final int SEARCH = 4;

    public static final int TEST = 5;

    public static final int COMMIT_RESULT = 6;

    protected ResultHandler resultHandler;

    public Command(ResultHandler resultHandler) {
        this.resultHandler = resultHandler;
    }

    public abstract int type();
    public abstract Result execute();

    public ResultHandler getResultHandler() {
        return resultHandler;
    }
}
