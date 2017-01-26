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
    IndvCompetition getIndvCompetition(int id);
    TeamCompetition getTeamCompetition(int id);

    void save(Bean bean);
    void saveAthlete(Athlete athlete);
    void saveTeam(Team team);
    void saveCompetition(Competition competition);
    void saveIndvCompetition(IndvCompetition competition);
    void saveTeamCompetition(TeamCompetition competition);

    void remove(Bean bean);
    void removeAthlete(Athlete athlete);
    void removeTeam(Team team);
    void removeCompetition(Competition competition);
    void removeIndvCompetition(IndvCompetition competition);
    void removeTeamCompetition(TeamCompetition competition);

    Bean[] search(SearchTerm term);
    void commit(Match match);
    boolean exists(Bean bean);
}
