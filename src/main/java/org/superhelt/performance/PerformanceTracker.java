package org.superhelt.performance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.superhelt.performance.data.CachedDataClient;
import org.superhelt.performance.data.QueryBuilder;
import org.superhelt.performance.data.WarcraftLogsClient;
import org.superhelt.performance.eventprovider.EventProvider;
import org.superhelt.performance.eventprovider.EventProviders;
import org.superhelt.performance.measure.EarlyDeathMeasure;
import org.superhelt.performance.measure.Measure;
import org.superhelt.performance.measure.Measures;
import org.superhelt.performance.om.*;
import org.superhelt.performance.om.warcraftlogs.Fight;
import org.superhelt.performance.om.warcraftlogs.WarcraftLogsEvent;
import org.superhelt.performance.om.warcraftlogs.Report;
import org.superhelt.performance.om.warcraftlogs.ReportPlayer;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class PerformanceTracker {

    private static final Logger log = LoggerFactory.getLogger(PerformanceTracker.class);

    private static Map<Integer, Boss> knownBosses = new HashMap<>();
    private static Map<Integer, Player> knownPlayers = new HashMap<>();
    private static Map<Integer, Ability> knownAbilities = Abilities.getAbilityMap();

    public static void main(String[] args) {
        var client = new CachedDataClient(new WarcraftLogsClient(new QueryBuilder()));

        Map<String, Report> reportMap = new HashMap<>();
        List<Fight> fights = new ArrayList<>();

        List<Encounter> encounters = new ArrayList<>();
        List<String> reportIds = client.getReportIds(277050);

        for(String reportId : reportIds) {
            var report = client.getReport(reportId);
            reportMap.put(report.getCode(), report);
            fights.addAll(report.getFights());
        }

        Map<Integer, LocalDateTime> firstKills = calculateFirstKills(fights);

        for(String reportId : reportIds) {
            Report report = reportMap.get(reportId);
            var events = client.getEvents(report, EventProviders.all());

            encounters.addAll(createEncounter(report, events, firstKills));
        }

        encounters.sort(Comparator.comparing(Encounter::getStartTime));

        StatisticsGenerator statisticsGenerator = new StatisticsGenerator();
        var statistics = statisticsGenerator.generateStatistics(encounters, Measures.getAll());
        var aggregated = statisticsGenerator.aggregate(statistics);

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer()).setPrettyPrinting().create();
        System.out.println(gson.toJson(aggregated));
    }

    private static Map<Integer, LocalDateTime> calculateFirstKills(List<Fight> fights) {
        fights.sort(Comparator.comparing(Fight::getStartTime));
        Map<Integer, LocalDateTime> firstKills = new HashMap<>();

        for(Fight fight : fights) {
            if(fight.isKill() && !firstKills.containsKey(fight.getEncounterId())) {
                log.info("Killed encounter {} first at {}", fight.getName(), fight.getStartTime());
                firstKills.put(fight.getEncounterId(), fight.getStartTime());
            }
        }
        return firstKills;
    }

    private static List<Encounter> createEncounter(Report report, List<WarcraftLogsEvent> warcraftLogsEvents, Map<Integer, LocalDateTime> firstKills) {
        List<Encounter> result = new ArrayList<>();
        for(var fight : report.getFights()) {
            Boss boss = findBoss(fight.getEncounterId(), fight.getName());
            List<Player> players = getPlayers(fight.getPlayerIds(), report.getPlayers());
            LocalDateTime startTime = fight.getStartTime();
            LocalDateTime endTime = fight.getEndTime();
            List<Event> events = translateEvents(warcraftLogsEvents, report.getPlayers(), fight);
            boolean progress = !firstKills.containsKey(boss.getId()) || !startTime.isAfter(firstKills.get(boss.getId()));

            result.add(new Encounter(boss, players, events, startTime, endTime, progress));
        }
        return result;
    }

    private static List<Event> translateEvents(List<WarcraftLogsEvent> warcraftLogsEvents, List<ReportPlayer> players, Fight fight) {
        return warcraftLogsEvents.stream()
                .filter(e->e.getFightId()==fight.getId())
                .map(e->createEvent(e, players))
                .collect(Collectors.toList());
    }

    private static Event createEvent(WarcraftLogsEvent event, List<ReportPlayer> players) {
        LocalDateTime timestamp = event.getTimestamp();
        Player source = players.stream().filter(p->p.getReportId()==event.getSourceId()).findFirst().orElse(null);
        Player target = players.stream().filter(p->p.getReportId()==event.getTargetId()).findFirst().orElse(null);
        Ability ability = knownAbilities.getOrDefault(event.getAbilityId(), null);
        EventType eventType = event.getType();
        int amount = event.getAmount();
        int unmitigatedAmount = event.getUnmitigatedAmount();

        return new Event(timestamp, source, target, ability, eventType, amount, unmitigatedAmount);
    }

    private static List<Player> getPlayers(List<Integer> playerIds, List<ReportPlayer> players) {
        List<Player> result = new ArrayList<>();

        for(int id : playerIds) {
            var player = players.stream().filter(p->p.getReportId()==id).findFirst().get();

            if(!knownPlayers.containsKey(player.getId())) {
                knownPlayers.put(player.getId(), new Player(player.getId(), player.getName(), player.getPlayerClass()));
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
