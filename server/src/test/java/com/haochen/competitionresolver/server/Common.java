package com.haochen.competitionresolver.server;

import java.lang.reflect.Method;

/**
 * Created by Haochen on 2017/2/4.
 */
public class Common {
    public static Method findMethod(Class<?> clazz, String methodName, Class<?>... paramTypes)
            throws NoSuchMethodException {
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                return clazz.getDeclaredMethod(methodName, paramTypes);
            } catch (NoSuchMethodException e) {}
        }
        throw new NoSuchMethodException(methodName);
    }
}
