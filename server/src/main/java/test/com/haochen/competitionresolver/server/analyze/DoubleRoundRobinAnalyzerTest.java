package test.com.haochen.competitionresolver.server.analyze;

import com.haochen.competitionresolver.server.analyze.DoubleRoundRobinAnalyzer;
import com.haochen.competitionresolver.server.bean.Competitor;
import com.haochen.competitionresolver.server.bean.Module;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.com.haochen.competitionresolver.server.Data;

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
        Competitor[][] competitors = Data.Analyzer.RoundRobin.Double.competitors;
        int[][] matchResult = Data.Analyzer.RoundRobin.Double.matchResults;
        int[][][] gameResult = Data.Analyzer.RoundRobin.Double.gameResults;
        int[][] result = Data.Analyzer.RoundRobin.Double.results;

        List<Module> modules = Util.createRoundRobinModuleList(competitors, matchResult, gameResult);

        int[][] ranks = Util.invokeWithAllModules(DoubleRoundRobinAnalyzer.class.getName(), modules, competitors, result.length);
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
