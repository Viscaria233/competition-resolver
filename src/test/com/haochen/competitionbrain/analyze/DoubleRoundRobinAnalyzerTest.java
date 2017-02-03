package test.com.haochen.competitionbrain.analyze;

import com.haochen.competitionbrain.analyze.DoubleRoundRobinAnalyzer;
import com.haochen.competitionbrain.bean.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;

/**
 * DoubleRoundRobinAnalyzer Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 2, 2017</pre>
 */
public class DoubleRoundRobinAnalyzerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getResultArray(int row, int col)
     */
    @Test
    public void testGetResultArray() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: winLoseGame(Report report, Match m, int[] points)
     */
    @Test
    public void testWinLoseGame() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: analyze(Module module)
     */
    @Test
    public void testAnalyze() throws Exception {
//TODO: Test goes here...
        Competitor[][] competitors = Data.RoundRobin.Double.competitors;
        int[][] matchResult = Data.RoundRobin.Double.matchResults;
        int[][][] gameResult = Data.RoundRobin.Double.gameResults;
        int[][] result = Data.RoundRobin.Double.results;

        List<Module> modules = Common.createRoundRobinModuleList(competitors, matchResult, gameResult);

        int[][] ranks = Common.invokeWithAllModules(DoubleRoundRobinAnalyzer.class.getName(), modules, competitors, result.length);
        Assert.assertArrayEquals(ranks, result);
    }

    /**
     * Method: getScore(int[][][] results, int index)
     */
    @Test
    public void testGetScore() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: compare(int[][][] results, int index1, int index2)
     */
    @Test
    public void testCompare() throws Exception {
//TODO: Test goes here... 
    }


    /**
     * Method: winGame(Competitor self, Competitor foe, boolean winAtHome, int winCount, int[][][] results, Map<Competitor, Integer> map)
     */
    @Test
    public void testWinGame() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = DoubleRoundRobinAnalyzer.getClass().getMethod("winGame", Competitor.class, Competitor.class, boolean.class, int.class, int[][][].class, Map<Competitor,.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
