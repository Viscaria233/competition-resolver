package test.com.haochen.competitionbrain.analyze;

import com.haochen.competitionbrain.analyze.SingleRoundRobinAnalyzer;
import com.haochen.competitionbrain.bean.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.com.haochen.competitionbrain.Data;

import java.util.List;

/**
 * SingleRoundRobinAnalyzer Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>一月 30, 2017</pre>
 */
public class SingleRoundRobinAnalyzerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: report(Module module)
     */
    @Test
    public void testReport() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: analyze(Module module)
     */
    @Test
    public void testAnalyze() throws Exception {
//TODO: Test goes here...
        Competitor[][] competitors = Data.Analyzer.RoundRobin.Single.competitors;
        int[][] matchResult = Data.Analyzer.RoundRobin.Single.matchResults;
        int[][][] gameResult = Data.Analyzer.RoundRobin.Single.gameResults;
        int[][] result = Data.Analyzer.RoundRobin.Single.results;

        List<Module> modules = Util.createRoundRobinModuleList(competitors, matchResult, gameResult);

        int[][] ranks = Util.invokeWithAllModules(SingleRoundRobinAnalyzer.class.getName(), modules, competitors, result.length);
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
     * Method: createReport(Competitor[] competitors)
     */
    @Test
    public void testCreateReport() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SingleRoundRobinAnalyzer.getClass().getMethod("createReport", Competitor[].class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: rank(Report report)
     */
    @Test
    public void testRank() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SingleRoundRobinAnalyzer.getClass().getMethod("rank", Report.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: hasResult(int[][][] results, Competitor[] competitors)
     */
    @Test
    public void testHasResult() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SingleRoundRobinAnalyzer.getClass().getMethod("hasResult", int[][][].class, Competitor[].class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: winMatchByQuitingFoe(Competitor winner, int[][][] results, Map<Competitor, Integer> map)
     */
    @Test
    public void testWinMatchByQuitingFoe() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SingleRoundRobinAnalyzer.getClass().getMethod("winMatchByQuitingFoe", Competitor.class, int[][][].class, Map<Competitor,.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: winMatch(Competitor winner, Competitor foe, int[][][] results, Map<Competitor, Integer> map)
     */
    @Test
    public void testWinMatch() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SingleRoundRobinAnalyzer.getClass().getMethod("winMatch", Competitor.class, Competitor.class, int[][][].class, Map<Competitor,.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: winGame(Competitor self, Competitor foe, int winCount, int[][][] results, Map<Competitor, Integer> map)
     */
    @Test
    public void testWinGame() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SingleRoundRobinAnalyzer.getClass().getMethod("winGame", Competitor.class, Competitor.class, int.class, int[][][].class, Map<Competitor,.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: winPoint(Competitor self, Competitor foe, int winCount, int[][][] results, Map<Competitor, Integer> map)
     */
    @Test
    public void testWinPoint() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SingleRoundRobinAnalyzer.getClass().getMethod("winPoint", Competitor.class, Competitor.class, int.class, int[][][].class, Map<Competitor,.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
