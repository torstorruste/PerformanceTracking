package org.superhelt.performance;

import org.superhelt.performance.data.CachedDataClient;
import org.superhelt.performance.data.QueryBuilder;
import org.superhelt.performance.data.WarcraftLogsClient;
import org.superhelt.performance.eventprovider.EventProvider;
import org.superhelt.performance.eventprovider.EventProviders;
import org.superhelt.performance.om.Boss;
import org.superhelt.performance.om.Encounter;
import org.superhelt.performance.om.Event;
import org.superhelt.performance.om.Player;
import org.superhelt.performance.om.warcraftlogs.Report;
import org.superhelt.performance.om.warcraftlogs.ReportPlayer;

import java.time.LocalDateTime;
import java.util.*;

public class PerformanceTracker {

    private static Map<Integer, Boss> knownBosses = new HashMap<>();
    private static Map<Integer, Player> knownPlayers = new HashMap<>();

    public static void main(String[] args) {
        var client = new CachedDataClient(new WarcraftLogsClient(new QueryBuilder()));

        List<Encounter> encounters = new ArrayList<>();
        for(String reportId : client.getReportIds(277050)) {

            var report = client.getReport(reportId);
            List<EventProvider> eventProviders = new ArrayList<>();
            eventProviders.addAll(EventProviders.defensives());
            eventProviders.addAll(EventProviders.heals());
            eventProviders.addAll(EventProviders.deaths());
            var events = client.getEvents(report, eventProviders);

            System.out.println(report.getCode());
            System.out.println(events.size());
            encounters.addAll(createEncounter(report, events));

        }

        encounters.sort(Comparator.comparing(Encounter::getStartTime));
        System.out.println(encounters.size());
    }

    private static List<Encounter> createEncounter(Report report, List<Event> events) {
        List<Encounter> result = new ArrayList<>();
        for(var fight : report.getFights()) {
            Boss boss = findBoss(fight.getEncounterId(), fight.getName());
            List<Player> players = getPlayers(fight.getPlayerIds(), report.getPlayers());
            LocalDateTime startTime = fight.getStartTime();
            LocalDateTime endTime = fight.getEndTime();

            result.add(new Encounter(boss, players, events, startTime, endTime));
        }
        return result;
    }

    private static List<Player> getPlayers(List<Integer> playerIds, List<ReportPlayer> players) {
        List<Player> result = new ArrayList<>();

        for(int id : playerIds) {
            var player = players.stream().filter(p->p.getReportId()==id).findFirst().get();

            if(!knownPlayers.containsKey(player.getId())) {
                knownPlayers.put(player.getId(), player);
            }
            result.add(knownPlayers.get(player.getId()));
        }

        return result;
    }

    private static Boss findBoss(int encounterId, String name) {
        if(!knownBosses.containsKey(encounterId)) {
            knownBosses.put(encounterId, new Boss(encounterId, name));
        }
        return knownBosses.get(encounterId);
    }
}
