package test.com.haochen.competitionbrain.analyze;

import com.haochen.competitionbrain.analyze.SingleRoundRobinAnalyzer;
import com.haochen.competitionbrain.bean.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haochen on 2017/2/2.
 */
public class Common {
    static List<Module> createRoundRobinModuleList(Competitor[] competitors, int[][] matchResults, int[][][] gameResults) {
        List<Module> modules = new ArrayList<>();

        for (int i = 0; i < matchResults.length; ++i) {
            int matchIndex = 0;
            int gameIndex = 0;
            int[] matchResult = matchResults[i];
            Group group = new Group();
            for (int j = 0; matchIndex < matchResult.length; ++j) {
                for (int k = 0; k < competitors.length; ++k) {
                    int row = j % competitors.length;
                    int col = k % competitors.length;
                    if (row < col) {
                        Match match = createMatch(competitors, matchResult, matchIndex, row, col);
                        match.setHomeCompetitor(matchIndex >= matchResult.length ? 1 : 0);

                        int index = 0;
                        int[][] gameResult = gameResults[i];
                        for (int k1 = 0; k1 < match.finishedGameCount(); ++k1) {
                            Game game = createGame(competitors, gameResult[gameIndex], index, row, col);
                            match.getGames().add(game);
                            index += 2;
                        }
                        group.getMatches().add(match);
                    }
                    if (row != col) {
                        matchIndex += 2;
                        gameIndex++;
                    }
                }
            }
            Module module = new Module();
            module.getGroups().add(group);
            modules.add(module);
        }
        return modules;
    }

    static Match createMatch(Competitor[] competitors, int[] singleMatchResult, int matchIndex, int row, int col) {
        Match match = new Match(5);
        match.getCompetitors()[0] = competitors[row];
        match.getCompetitors()[1] = competitors[col];
        int m0 = singleMatchResult[matchIndex];
        int m1 = singleMatchResult[matchIndex + 1];
        match.getPoints()[0] = m0;
        match.getPoints()[1] = m1;
        match.setWinner(m0 > m1 ? 0 : 1);
        return match;
    }

    static Game createGame(Competitor[] competitors, int[] singleGameResult, int index, int row, int col) {
        Game game = new Game(11, 2);
        game.getCompetitors()[0] = competitors[row];
        game.getCompetitors()[1] = competitors[col];
        int p0 = singleGameResult[index];
        int p1 = singleGameResult[index + 1];
        game.getPoints()[0] = p0;
        game.getPoints()[1] = p1;
        game.setWinner(p0 > p1 ? 0 : 1);
        return game;
    }

    static Method findMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                return clazz.getDeclaredMethod(methodName, paramTypes);
            } catch (NoSuchMethodException e) {}
        }
        return null;
    }

    static int[][] invokeWithAllModules(String className, List<Module> modules, Competitor[] competitors, int invokeCount) {
        try {
            Class clazz = Class.forName(className);
            Method method= findMethod(clazz, "analyze", Module.class);
            if (method == null) {
                return null;
            }
            method.setAccessible(true);
            int[][] ranks = new int[invokeCount][];
            for (int i = 0; i < invokeCount; ++i) {
                int[][][] invokeResult = (int[][][]) method.invoke(Class.forName(className).newInstance(), modules.get(i));
                int[] rank = new int[competitors.length];
                for (int j = 0; j < rank.length; ++j) {
                    int[] array = invokeResult[0][j];
                    rank[j] = array[array.length - 1];
                }
                for (int[] array : invokeResult[0]) {
                    for (int j : array) {
                        System.out.printf("%4d", j);
                    }
                    System.out.println();
                }
                System.out.println();
                for (int[] array : invokeResult[1]) {
                    for (int j : array) {
                        System.out.printf("%4d", j);
                    }
                    System.out.println();
                }
                System.out.println("------------------------------------");
                ranks[i] = rank;
            }
            return ranks;
        } catch(IllegalAccessException | InvocationTargetException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}