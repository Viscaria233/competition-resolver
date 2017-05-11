package com.haochen.competitionresolver.server.schedule;

import com.haochen.competitionresolver.server.bean.Competitor;
import com.haochen.competitionresolver.server.bean.Group;
import com.haochen.competitionresolver.server.bean.Match;
import com.haochen.competitionresolver.server.bean.Module;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import com.haochen.competitionresolver.server.Common;
import com.haochen.competitionresolver.server.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * SingleRoundRobinArranger Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 4, 2017</pre>
 */
public class SingleRoundRobinArrangerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: arrange(Module module)
     */
    @Test
    public void testArrangeModule() throws Exception {
//TODO: Test goes here...
        Competitor[][] competitors = Data.Arranger.RoundRobin.Single.competitors;
        List<Module> modules = Util.createModuleList(competitors);

        Class<?> clazz = SingleRoundRobinArranger.class;
        Method method = Common.findMethod(clazz, "arrange", Module.class);
        Util.invokeWithAllModules(method, clazz.newInstance(), modules);
        Assert.assertTrue(isFull(modules));
    }

    private boolean isFull(List<Module> modules) {
        for (Module module : modules) {
            for (Group group : module.getGroups()) {
                Map<Pair<Competitor, Competitor>, Integer> map = new HashMap<>();
                int competitorCount = group.getCompetitors().size();
                int matchCount = (competitorCount * (competitorCount - 1)) / 2;
                if (group.getMatches().size() != matchCount) {
                    return false;
                }
                for (Match match : group.getMatches()) {
                    Competitor k = match.getCompetitors()[0];
                    Competitor v = match.getCompetitors()[1];
                    if (!map.containsKey(new Pair<>(k, v))) {
                        map.put(new Pair<>(match.getCompetitors()[0], match.getCompetitors()[1]), 0);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Method: getMatches(Competitor[] competitors, int maxGame)
     */
    @Test
    public void testGetMatches() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: arrange(Group group)
     */
    @Test
    public void testArrangeGroup() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SingleRoundRobinArranger.getClass().getMethod("arrange", Group.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
