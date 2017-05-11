package com.haochen.competitionresolver.server.storage;

import com.haochen.competitionresolver.server.bean.*;

/**
 * Created by Haochen on 2016/12/30.
 */
public interface StorageHelper {
    Bean get(int id);
    Bean get(Class<? extends Bean> type, int id);
    Athlete getAthlete(int id);
    Team getTeam(int id);
    Competition getCompetition(int id);

    void save(Bean bean);
    void saveAthlete(Athlete athlete);
    void saveTeam(Team team);
    void saveCompetition(Competition competition);

    void remove(int id);
    void remove(Class<? extends Bean> type, int id);
    void removeAthlete(int id);
    void removeTeam(int id);
    void removeCompetition(int id);

    Bean[] search(SearchTerm term);
    void commitResult(Match match);
    boolean exists(Bean bean);

    void commit();
}
