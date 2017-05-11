package com.haochen.competitionresolver.server.command;

/**
 * Created by Haochen on 2017/1/2.
 */
public class Result {
    protected boolean success;
    protected String errorMessage;
    protected Object resultObject;

    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public Result setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public Result setResultObject(Object resultObject) {
        this.resultObject = resultObject;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Object getResultObject() {
        return resultObject;
    }
}
