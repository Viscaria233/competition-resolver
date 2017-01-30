package test.com.haochen.competitionbrain.analyze;

import com.haochen.competitionbrain.analyze.SingleRoundRobinAnalyzer;
import com.haochen.competitionbrain.bean.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        Module module = new Module();
        Group group = new Group();
        module.getGroups().add(group);
        Competitor[] competitors = {
                new Athlete(0),
                new Athlete(1),
                new Athlete(2),
                new Athlete(3),
                new Athlete(4),
        };
        int[] matchResult = {
                0, 3, 2, 3, 1, 3, 1, 3,
                3, 0, 0, 3, 3, 1, 1, 3,
                3, 2, 3, 0, 3, 1, 2, 3,
                3, 1, 1, 3, 1, 3, 3, 1,
                3, 1, 3, 1, 3, 2, 1, 3
        };
        int[][] gameResult = {
                {8, 11, 7, 11, 8, 11}, {11, 5, 11, 7, 9, 11, 6, 11, 11, 13}, {11, 9, 8, 11, 6, 11, 7, 11}, {9, 11, 9, 11, 11, 6, 7, 11},
                {11, 8, 11, 7, 11, 8}, {8, 11, 6, 11, 8, 11}, {11, 6, 11, 9, 9, 11, 11, 7}, {12, 10, 9, 11, 7, 11, 7, 11},
                {5, 11, 7, 11, 11, 9, 11, 6, 13, 11}, {11, 8, 11, 6, 11, 8}, {11, 5, 11, 8, 9, 11, 11, 8}, {12, 10, 9, 11, 9, 11, 11, 8, 7, 11},
                {9, 11, 11, 8, 11, 6, 11, 7}, {6, 11, 9, 11, 11, 9, 7, 11}, {5, 11, 8, 11, 11, 9, 8, 11}, {8, 11, 11, 4, 11, 9, 12, 10},
                {11, 9, 11, 9, 6, 11, 11, 7}, {10, 12, 11, 9, 11, 7, 11, 7}, {10, 12, 11, 9, 11, 9, 8, 11, 11, 7}, {11, 8, 4, 11, 9, 11, 10, 12},
        };
        int matchIndex = 0;
        int gameIndex = 0;
        for (int i = 0; i < competitors.length; ++i) {
            for (int j = 0; j < competitors.length; ++j) {
                if (i < j) {
                    Match match = new Match(5);
                    match.getCompetitors()[0] = competitors[i];
                    match.getCompetitors()[1] = competitors[j];
                    int m0 = matchResult[matchIndex];
                    int m1 = matchResult[matchIndex + 1];
                    match.getPoints()[0] = m0;
                    match.getPoints()[1] = m1;
                    match.setWinner(m0 > m1 ? 0 : 1);
                    int index = 0;
                    for (int k = 0; k < match.finishedGameCount(); ++k) {
                        Game game = new Game(11, 2);
                        game.getCompetitors()[0] = competitors[i];
                        game.getCompetitors()[1] = competitors[j];
                        int p0 = gameResult[gameIndex][index];
                        int p1 = gameResult[gameIndex][index + 1];
                        game.getPoints()[0] = p0;
                        game.getPoints()[1] = p1;
                        game.setWinner(p0 > p1 ? 0 : 1);
                        match.getGames().add(game);
                        index += 2;
                    }
                    group.getMatches().add(match);
                }
                if (i != j) {
                    matchIndex += 2;
                    gameIndex++;
                }
            }
        }
        try {
            Method method = SingleRoundRobinAnalyzer.class.getDeclaredMethod("analyze", Module.class);
            method.setAccessible(true);
            int[][][] result = (int[][][]) method.invoke(new SingleRoundRobinAnalyzer(), module);
            int[] rank = new int[competitors.length];
            for (int i = 0; i < rank.length; ++i) {
                int[] array = result[0][i];
                rank[i] = array[array.length - 1];
            }
            for (int[] array : result[0]) {
                for (int i : array) {
                    System.out.printf("%4d", i);
                }
                System.out.println();
            }
            System.out.println();
            for (int[] array : result[1]) {
                for (int i : array) {
                    System.out.printf("%4d", i);
                }
                System.out.println();
            }
            Assert.assertArrayEquals(rank, new int[]{5, 3, 1, 4, 2});
        } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method: getScore(int[][][] result, int index)
     */
    @Test
    public void testGetScore() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: compare(int[][][] result, int index1, int index2)
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
     * Method: hasResult(int[][][] result, Competitor[] competitors)
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
     * Method: winMatchByQuitingFoe(Competitor winner, int[][][] result, Map<Competitor, Integer> map)
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
     * Method: winMatch(Competitor winner, Competitor foe, int[][][] result, Map<Competitor, Integer> map)
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
     * Method: winGame(Competitor self, Competitor foe, int winCount, int[][][] result, Map<Competitor, Integer> map)
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
     * Method: winPoint(Competitor self, Competitor foe, int winCount, int[][][] result, Map<Competitor, Integer> map)
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
