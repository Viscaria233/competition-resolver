package com.haochen.competitionbrain.impl.storage.test;

import com.haochen.competitionbrain.bean.*;
import com.haochen.competitionbrain.storage.SearchTerm;
import com.haochen.competitionbrain.storage.StorageHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haochen on 2017/1/9.
 */
public class TestStorageHelper implements StorageHelper {
    private static TestStorageHelper instance;

    private static final List<Bean> beans = new ArrayList<>();

    private TestStorageHelper() {}

    public static TestStorageHelper getInstance() {
        if (instance == null) {
            instance = new TestStorageHelper();
        }
        return instance;
    }

    @Override
    public Bean get(int id) {
        Class c = Object.class;
        return beans.stream()
                .filter((b) -> c.isInstance(b) && b.getId() == id)
                .findFirst().get();
    }

    @Override
    public Athlete getAthlete(int id) {
        return (Athlete) get(id);
    }

    @Override
    public Team getTeam(int id) {
        return (Team) get(id);
    }

    @Override
    public Competition getCompetition(int id) {
        return (Competition) get(id);
    }

    @Override
    public IndvCompetition getIndvCompetition(int id) {
        return null;
    }

    @Override
    public TeamCompetition getTeamCompetition(int id) {
        return null;
    }

    @Override
    public void save(Bean bean) {
        beans.add(bean);
    }

    @Override
    public void saveAthlete(Athlete athlete) {

    }

    @Override
    public void saveTeam(Team team) {

    }

    @Override
    public void saveCompetition(Competition competition) {

    }

    @Override
    public void saveIndvCompetition(IndvCompetition competition) {

    }

    @Override
    public void saveTeamCompetition(TeamCompetition competition) {

    }

    @Override
    public void remove(Bean bean) {
        beans.remove(bean);
    }

    @Override
    public void removeAthlete(Athlete athlete) {

    }

    @Override
    public void removeTeam(Team team) {

    }

    @Override
    public void removeCompetition(Competition competition) {

    }

    @Override
    public void removeIndvCompetition(IndvCompetition competition) {

    }

    @Override
    public void removeTeamCompetition(TeamCompetition competition) {

    }

    @Override
    public Bean[] search(SearchTerm term) {
        return beans.stream()
                .filter(term::test)
                .toArray(Bean[]::new);
    }

    @Override
    public void commit(Match match) {
        Bean bean = beans.stream()
                .filter((b) -> b.equals(match))
                .findFirst().get();
        beans.remove(bean);
        beans.add(match);
    }

    @Override
    public boolean exists(Bean bean) {
        return beans.stream()
                .filter((b) -> b.equals(bean))
                .count() > 0;
    }
}
