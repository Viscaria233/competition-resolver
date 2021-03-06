package com.haochen.competitionresolver.server.schedule;

import com.haochen.competitionresolver.server.bean.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haochen on 2017/2/3.
 */
public class SingleRoundRobinArranger {
    SingleRoundRobinArranger() {}

    void arrange(Module module) {
        module.getGroups().forEach(this::arrange);
    }

    private void arrange(Group group) {
        List<Competitor> competitorList = group.getCompetitors();
        int size = competitorList.size() % 2 == 0 ? competitorList.size() : competitorList.size() + 1;
        Competitor[] competitors = new Competitor[size];
        if (size > competitorList.size()) {
            competitors[size - 1] = new Bye();
        }
        for (int i = 0; i < competitorList.size(); ++i) {
            competitors[i] = competitorList.get(i);
        }

        List<Match> matches = getMatches(competitors, group.getMaxGame());
        group.getMatches().addAll(matches);
    }

    List<Match> getMatches(Competitor[] competitors, int maxGame) {
        List<Match> matches = new ArrayList<>();
        for (int i = 0; i < competitors.length - 1; ++i) {
            for (int head = 0, rear = competitors.length - 1; head < rear; ++head, --rear) {
                Competitor cHead = competitors[head];
                Competitor cRear = competitors[rear];
                if (cHead instanceof Bye || cRear instanceof Bye) {
                    continue;
                }

                Match match = new Match(maxGame);
                match.getCompetitors()[0] = cHead;
                match.getCompetitors()[1] = cRear;
                matches.add(match);
            }
            Competitor temp = competitors[competitors.length - 1];
            System.arraycopy(competitors, 1, competitors, 2, competitors.length - 1 - 1);
            competitors[1] = temp;
        }
        return matches;
    }
}
