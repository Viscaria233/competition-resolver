package test.com.haochen.competitionresolver.server.schedule;

import com.haochen.competitionresolver.server.bean.Competitor;
import com.haochen.competitionresolver.server.bean.Group;
import com.haochen.competitionresolver.server.bean.Module;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Haochen on 2017/2/4.
 */
public class Util {
    static List<Module> createModuleList(Competitor[][] array) {
        List<Module> modules = new ArrayList<>();
        for (Competitor[] c : array) {
            List<Competitor> competitors = Arrays.asList(c);
            Module module = new Module();
            module.setCompetitors(competitors);
            Group group = new Group();
            group.setMaxGame(5);
            group.setCompetitors(competitors);
            module.getGroups().add(group);

            modules.add(module);
        }
        return modules;
    }

    static void invokeWithAllModules(Method method, Object object, List<Module> modules) {
        method.setAccessible(true);
        try {
            for (Module module : modules) {
                method.invoke(object, module);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
