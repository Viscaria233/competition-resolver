package test.com.haochen.competitionbrain.schedule;

import com.haochen.competitionbrain.bean.*;
import com.haochen.competitionbrain.schedule.DoubleRoundRobinArranger;
import com.haochen.competitionbrain.schedule.SingleRoundRobinArranger;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.com.haochen.competitionbrain.Common;
import test.com.haochen.competitionbrain.Data;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DoubleRoundRobinArranger Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 4, 2017</pre>
 */
public class DoubleRoundRobinArrangerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getMatches(Competitor[] competitors, int maxGame)
     */
    @Test
    public void testGetMatches() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: arrange(Module module)
     */
    @Test
    public void testArrange() throws Exception {
//TODO: Test goes here...
        Competitor[][] competitors = Data.Arranger.RoundRobin.Double.competitors;
        List<Module> modules = Util.createModuleList(competitors);

        Class clazz = DoubleRoundRobinArranger.class;
        Method method = Common.findMethod(clazz, "arrange", Module.class);
        Util.invokeWithAllModules(method, clazz.newInstance(), modules);

        Assert.assertTrue(isFull(modules));
    }

    private boolean isFull(List<Module> modules) {
        for (Module module : modules) {
            for (Group group : module.getGroups()) {
                Map<Pair<Competitor, Competitor>, Integer> map = new HashMap<>();
                int competitorCount = group.getCompetitors().size();
                int matchCount = (competitorCount * (competitorCount - 1));
                if (group.getMatches().size() != matchCount) {
                    return false;
                }
                for (Match match : group.getMatches()) {
                    Competitor k = match.getCompetitors()[0];
                    Competitor v = match.getCompetitors()[1];
                    if (!map.containsKey(new Pair<>(k, v))) {
                        map.put(new Pair<>(match.getCompetitors()[0], match.getCompetitors()[1]), 0);
                    } else if (map.get(new Pair<>(k,v)) != 1) {
                        map.put(new Pair<>(match.getCompetitors()[0], match.getCompetitors()[1]), 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
