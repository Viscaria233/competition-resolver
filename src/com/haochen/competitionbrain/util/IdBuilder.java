package com.haochen.competitionbrain.util;

import com.haochen.competitionbrain.bean.*;
import com.haochen.competitionbrain.impl.storage.sqlite.SqliteContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Haochen on 2017/1/26.
 */
public class IdBuilder {
    public static int build(Class<? extends Bean> c) {
        String className = c.getSimpleName();
        String first = "" + className.charAt(0);
        String sub = className.substring(1);
        try {
            Method method = IdBuilder.class.getDeclaredMethod(first.toUpperCase() + sub + "Id");
            return (int) method.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int build(Bean bean) {
        return build(bean.getClass());
    }

    public static int athleteId() {
        return computeId(1 << 28, "athlete");
    }

    public static int teamId() {
        return computeId(2 << 28, "team");
    }

    public static int indvCompetitionId() {
        return computeId(3 << 28, "indv_competition");
    }

    public static int teamCompetitionId() {
        return computeId(4 << 28, "team_competition");
    }

    private static int computeId(int pre, String tableName) {
        int count = SqliteContext.getInstance().count(tableName);
        return pre | (count + 1);
    }
}
