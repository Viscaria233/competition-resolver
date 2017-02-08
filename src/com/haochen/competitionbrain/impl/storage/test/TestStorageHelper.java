package com.haochen.competitionbrain.impl.storage.test;

import com.haochen.competitionbrain.bean.*;
import com.haochen.competitionbrain.impl.storage.sqlite.SqliteContext;
import com.haochen.competitionbrain.storage.DbContext;
import com.haochen.competitionbrain.storage.SearchTerm;
import com.haochen.competitionbrain.storage.StorageHelper;

import java.io.*;
import java.util.*;

/**
 * Created by Haochen on 2017/1/9.
 */
public class TestStorageHelper implements StorageHelper {
    private static TestStorageHelper instance;

    private static final List<Bean> beans = new ArrayList<>();
    private static final File file = new File("bean_list");

    private TestStorageHelper() {
        init();
    }

    public static TestStorageHelper getInstance() {
        if (instance == null) {
            instance = new TestStorageHelper();
        }
        return instance;
    }

    private void init() {
        SqliteContext.getInstance();
        if (!file.exists()) {
            return;
        }
        ObjectInputStream ois = null;
        List<Bean> temp = new ArrayList<>();
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            Object obj = ois.readObject();
            if (temp.getClass().isInstance(obj)) {
                temp = temp.getClass().cast(obj);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        beans.clear();
        beans.addAll(temp);
    }

    @Override
    public Bean get(int id) {
        Class c = Object.class;
        Optional<Bean> op = beans.stream()
                .filter((b) -> c.isInstance(b) && b.getId() == id)
                .findFirst();
        return op.isPresent() ? op.get() : null;
    }

    @Override
    public Bean get(Class<? extends Bean> type, int id) {
        Optional<Bean> op = beans.stream()
                .filter((b) -> type.isInstance(b) && b.getId() == id)
                .findFirst();
        return op.isPresent() ? op.get() : null;
    }

    @Override
    public Athlete getAthlete(int id) {
        return (Athlete) get(Athlete.class, id);
    }

    @Override
    public Team getTeam(int id) {
        return (Team) get(Team.class, id);
    }

    @Override
    public Competition getCompetition(int id) {
        return (Competition) get(Competition.class, id);
    }

    @Override
    public void save(Bean bean) {
        beans.add(bean);
        DbContext dbContext = SqliteContext.getInstance();
        if (bean instanceof Athlete) {
            Athlete athlete = (Athlete) bean;
            dbContext.update("INSERT INTO athlete VALUES(?, ?, ?, ?, ?, ?)",
                    athlete.getId(), athlete.getName(), athlete.getAge(), athlete.getSex(),
                    athlete.getTel(), athlete.getScore());
        } else if (bean instanceof Team) {
            Team team = (Team) bean;
            dbContext.update("INSERT INTO team VALUES(?, ?, ?)",
                    team.getId(), team.getName(), team.getLeader().getId());
            for (Athlete athlete : team.getMembers()) {
                dbContext.update("INSERT INTO join_team VALUES(?, ?)",
                        athlete.getId(), team.getId());
            }
        } else if (bean instanceof Competition) {
            Competition competition = (Competition) bean;
            dbContext.update("INSERT INTO competition VALUES(?, ?, ?, ?)",
                    competition.getId(), competition.getName(), competition.getType(), null);
            for (Bean b : beans) {
                if (b instanceof Athlete) {
                    dbContext.update("INSERT INTO busy_athlete VALUES(?, ?, ?)",
                            b.getId(), competition.getId(), 0);
                }
            }
        }
    }

    @Override
    public void saveAthlete(Athlete athlete) {
        save(athlete);
    }

    @Override
    public void saveTeam(Team team) {
        save(team);
    }

    @Override
    public void saveCompetition(Competition competition) {
        save(competition);
    }

    @Override
    public void remove(int id) {
        Bean bean = get(id);
        if (bean != null) {
            beans.remove(bean);
        }
        removeFromDb(bean);
    }

    private void removeFromDb(Bean bean) {
        DbContext dbContext = SqliteContext.getInstance();
        if (bean instanceof Athlete) {
            dbContext.update("DELETE FROM athlete WHERE _id = ?", bean.getId());
            dbContext.update("DELETE FROM join_team WHERE athlete_id = ?", bean.getId());
            dbContext.update("DELETE FROM sign_up WHERE competitor_id = ?", bean.getId());
            dbContext.update("DELETE FROM busy_athlete WHERE athlete_id = ?", bean.getId());
            dbContext.update("DELETE FROM prize WHERE competitor_id = ?", bean.getId());
        } else if (bean instanceof Team) {
            dbContext.update("DELETE FROM team WHERE _id = ?", bean.getId());
            dbContext.update("DELETE FROM sign_up WHERE competitor_id = ?", bean.getId());
            dbContext.update("DELETE FROM prize WHERE competitor_id = ?", bean.getId());
            Team team = (Team) bean;
            for (Athlete athlete : team.getMembers()) {
                dbContext.update("DELETE FROM join_team WHERE athlete_id = ?", athlete.getId());
                dbContext.update("DELETE FROM busy_athlete WHERE athlete_id = ?", athlete.getId());
            }
        } else if (bean instanceof Competition) {
            dbContext.update("DELETE FROM competition WHERE _id = ?", bean.getId());
            dbContext.update("DELETE FROM sign_up WHERE competition_id = ?", bean.getId());
            dbContext.update("DELETE FROM busy_athlete WHERE competition_id = ?", bean.getId());
            dbContext.update("DELETE FROM prize WHERE competition_id = ?", bean.getId());
        }
    }

    @Override
    public void remove(Class<? extends Bean> type, int id) {
        Bean bean = get(type, id);
        if (bean != null) {
            beans.remove(bean);
            removeFromDb(bean);
        }
    }

    @Override
    public void removeAthlete(int id) {
        remove(Athlete.class, id);
    }

    @Override
    public void removeTeam(int id) {
        remove(Team.class, id);
    }

    @Override
    public void removeCompetition(int id) {
        remove(Competition.class, id);
    }

    @Override
    public Bean[] search(SearchTerm term) {
        return beans.stream()
                .filter(term::test)
                .toArray(Bean[]::new);
    }

    @Override
    public void commitResult(Match match) {
        Optional<Bean> op = beans.stream()
                .filter((b) -> b.equals(match))
                .findFirst();
        Bean bean = op.isPresent() ? op.get() : null;
        if (bean != null) {
            beans.remove(bean);
            beans.add(match);
        }
    }

    @Override
    public boolean exists(Bean bean) {
        return beans.stream()
                .filter((b) -> b.equals(bean))
                .count() > 0;
    }

    @Override
    public void commit() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(beans);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
