package com.haochen.competitionresolver.server.command;

/**
 * Created by Haochen on 2017/1/10.
 */
public class TestCommand extends Command {
    public TestCommand(ResultHandler resultHandler) {
        super(resultHandler);
    }

    @Override
    public int type() {
        return Command.TEST;
    }

    @Override
    public Result execute() {
        Result result = new Result();
        result.success = true;
        result.resultObject = "test command has successfully executed";
        return result;
    }
}
