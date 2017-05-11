package test.com.haochen.competitionresolver.server;

import java.lang.reflect.Method;

/**
 * Created by Haochen on 2017/2/4.
 */
public class Common {
    public static Method findMethod(Class<?> clazz, String methodName, Class<?>... paramTypes)
            throws NoSuchMethodException {
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Method method = clazz.getDeclaredMethod(methodName, paramTypes);
                method.setAccessible(true);
                return method;
            } catch (NoSuchMethodException e) {}
        }
        throw new NoSuchMethodException(methodName);
    }
}
