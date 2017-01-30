package com.haochen.competitionbrain.storage;

import com.haochen.competitionbrain.bean.*;

/**
 * Created by Haochen on 2016/12/30.
 */
public interface StorageHelper {
    Bean get(int id);
    Athlete getAthlete(int id);
    Team getTeam(int id);
    Competition getCompetition(int id);

    void save(Bean bean);
    void saveAthlete(Athlete athlete);
    void saveTeam(Team team);
    void saveCompetition(Competition competition);

    void remove(Bean bean);
    void removeAthlete(Athlete athlete);
    void removeTeam(Team team);
    void removeCompetition(Competition competition);

    Bean[] search(SearchTerm term);
    void commit(Match match);
    boolean exists(Bean bean);
}
