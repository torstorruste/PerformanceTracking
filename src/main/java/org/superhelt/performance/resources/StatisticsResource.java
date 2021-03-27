package org.superhelt.performance.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.superhelt.performance.StatisticsGenerator;
import org.superhelt.performance.data.CachedDataClient;
import org.superhelt.performance.data.QueryBuilder;
import org.superhelt.performance.data.WarcraftLogsClient;
import org.superhelt.performance.eventprovider.EventProviders;
import org.superhelt.performance.measure.Measures;
import org.superhelt.performance.om.*;
import org.superhelt.performance.om.warcraftlogs.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class StatisticsResource {

    private static final Logger log = LoggerFactory.getLogger(StatisticsResource.class);

    private static final int guildId = 277050;
    private static final DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final Map<Integer, Boss> knownBosses = new HashMap<>();
    private static final Map<Integer, Player> knownPlayers = new HashMap<>();
    private static final Map<Integer, Ability> knownAbilities = Abilities.getAbilityMap();
    private static final CachedDataClient client = new CachedDataClient(new WarcraftLogsClient(new QueryBuilder()), Paths.get(""));

    private static List<Encounter> encounters = null;


    public StatisticsResource() {
    }

    @Path("refresh")
    @GET
    public Response refresh() {
        client.deleteReportOverview(guildId);
        encounters = getEncounters();
        return getStatus();
    }

    @Path("status")
    @GET
    public Response getStatus() {
        if(encounters==null) {
            encounters = getEncounters();
        }
        int numEncounters = encounters.size();
        LocalDateTime firstEncounter = encounters.get(0).getStartTime();
        LocalDateTime lastEncounter = encounters.get(encounters.size()-1).getStartTime();

        Status status = new Status(numEncounters, firstEncounter, lastEncounter);
        return Response.ok(status).build();
    }

    @Path("players")
    @GET
    public Response getPlayers() {
        if(encounters==null) {
            encounters = getEncounters();
        }
        List<Player> players = knownPlayers.values().stream()
                .sorted(Comparator.comparing(Player::getName))
                .collect(Collectors.toList());
        return Response.ok(players).build();
    }

    @Path("players/{playerId}/rankings")
    @GET
    public Response getRankings(@PathParam("playerId") int playerId) {
        return Response.ok(getRankingsFromEncounters(r->r.getPlayer().getId()==playerId)).build();
    }

    @Path("rankings")
    @GET
    public Response getRankings() {
        return Response.ok(getRankingsFromEncounters(r->true)).build();
    }

    @Path("bosses/{bossId}/rankings")
    @GET
    public Response getRankingsByBoss(@PathParam("bossId") int bossId) {
        return Response.ok(getRankingsFromEncounters(r -> r.getBoss().getId() == bossId)).build();
    }

    private List<Ranking> getRankingsFromEncounters(Predicate<Ranking> filter) {
        if(encounters==null) {
            encounters = getEncounters();
        }
        Stream<Ranking> dps = encounters.stream().flatMap(e->e.getDpsRankings().stream());
        Stream<Ranking> hps = encounters.stream().flatMap(e->e.getHpsRankings().stream());

        return Stream.concat(dps, hps).filter(filter).collect(Collectors.toList());
    }

    @Path("statistics")
    @GET
    public Response getStatistics(@QueryParam("bossId") Integer bossId, @QueryParam("encounterType") String encounterTypeString,
                                  @QueryParam("from") String dateString) {
        if(encounters==null) {
            encounters = getEncounters();
        }
        LocalDate startTime = dateString==null?null:LocalDate.parse(dateString, dt);

        EncounterType encounterType = parseEncounterType(encounterTypeString);

        AggregatedStatistics aggregated = getAggregatedStatistic(bossId, encounterType, startTime);
        return Response.ok(aggregated).build();
    }

    private EncounterType parseEncounterType(String encounterTypeString) {
        if(encounterTypeString==null) return EncounterType.BOTH;
        return EncounterType.valueOf(encounterTypeString.toUpperCase());
    }

    public AggregatedStatistics getAggregatedStatistic(Integer bossId, EncounterType encounterType, LocalDate startTime) {
        if(encounters==null) {
            encounters = getEncounters();
        }
        List<Encounter> encounters = this.encounters;
        if(bossId!=null) {
            encounters = encounters.stream().filter(e -> e.getBoss().getId()==bossId).collect(Collectors.toList());
        }
        if(encounterType!=EncounterType.BOTH) {
            encounters = encounters.stream().filter(e->e.getEncounterType()==encounterType).collect(Collectors.toList());
        }
        if(startTime != null) {
            encounters = encounters.stream().filter(e->e.getStartTime().isAfter(LocalDateTime.of(startTime, LocalTime.MIDNIGHT))).collect(Collectors.toList());
        }

        StatisticsGenerator statisticsGenerator = new StatisticsGenerator();
        List<Statistics> statistics = statisticsGenerator.generateStatistics(encounters, Measures.getAll());

        statistics = statistics.stream().map(s->new Statistics(null, s.getData())).collect(Collectors.toList());;

        return statisticsGenerator.aggregate(statistics);
    }

    private void addBoss(List<Boss> result, Collection<Boss> source, String name) {
        Optional<Boss> candidate = source.stream().filter(b -> b.getName().equals(name)).findFirst();

        candidate.ifPresent(result::add);
    }

    @Path("bosses")
    @GET
    public Response getBosses() {
        if(encounters==null) {
            encounters = getEncounters();
        }
        Collection<Boss> bosses = knownBosses.values();
        List<Boss> result = new ArrayList<>();
        addBoss(result, bosses, "Shriekwing");
        addBoss(result, bosses, "Huntsman Altimor");
        addBoss(result, bosses, "Hungering Destroyer");
        addBoss(result, bosses, "Sun King's Salvation");
        addBoss(result, bosses, "Artificer Xy'mox");
        addBoss(result, bosses, "Lady Inerva Darkvein");
        addBoss(result, bosses, "The Council of Blood");
        addBoss(result, bosses, "Sludgefist");
        addBoss(result, bosses, "Stone Legion Generals");
        addBoss(result, bosses, "Sire Denathrius");

        bosses.stream().filter(b->!result.contains(b))
                .forEach(result::add);

        return Response.ok(result).build();
    }

    @Path("bosses/{bossId}/statistics")
    @GET
    public Response getStatisticsByBoss(@PathParam("bossId") int bossId, @QueryParam("encounterType") String encounterTypeString,
                                        @QueryParam("from") String dateString) {
        return getStatistics(bossId, encounterTypeString, dateString);
    }

    @Path("bosses/{bossId}/encounters")
    @GET
    public Response getEncountersByBoss(@PathParam("bossId") int bossId) {
        List<Encounter> relevant = encounters.stream().filter(e->e.getBoss().getId()==bossId).collect(Collectors.toList());
        return Response.ok(relevant).build();
    }

    @Path("measures")
    @GET
    public Response getMeasures() {
        if(encounters==null) {
            encounters = getEncounters();
        }
        return Response.ok(Measures.getAll()).build();
    }

    private static List<Encounter> getEncounters() {
        Map<String, Report> reportMap = new HashMap<>();
        List<Fight> fights = new ArrayList<>();

        List<Encounter> encounters = new ArrayList<>();
        List<String> reportIds = client.getReportIds(guildId);

        log.info("fetching reports");
        for(String reportId : reportIds) {
            Report report = client.getReport(reportId);
            reportMap.put(report.getCode(), report);
            fights.addAll(report.getFights());
        }

        Map<Integer, LocalDateTime> firstKills = calculateFirstKills(fights);

        log.info("Fetching events");
        for(String reportId : reportIds) {
            Report report = reportMap.get(reportId);
            List<WarcraftLogsEvent> events = client.getEvents(report, EventProviders.all());

            encounters.addAll(createEncounter(report, events, firstKills));
        }

        log.info("Sorting encounters");
        encounters.sort(Comparator.comparing(Encounter::getStartTime));
        return encounters;
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
        for(Fight fight : report.getFights()) {
            Boss boss = findBoss(fight.getEncounterId(), fight.getName());
            List<Player> players = getPlayers(fight.getPlayerIds(), report.getPlayers());
            LocalDateTime startTime = fight.getStartTime();
            LocalDateTime endTime = fight.getEndTime();
            List<Event> events = translateEvents(warcraftLogsEvents, report.getPlayers(), fight);
            boolean progress = !firstKills.containsKey(boss.getId()) || !startTime.isAfter(firstKills.get(boss.getId()));
            EncounterType encounterType = progress ? EncounterType.PROGRESS : EncounterType.FARM;
            List<Ranking> dpsRankings = createRankings(report.getDpsRankings(fight.getId()), players, boss, startTime);
            List<Ranking> hpsRankings = createRankings(report.getHpsRankings(fight.getId()), players, boss, startTime);

            result.add(new Encounter(boss, players, events, startTime, endTime, encounterType, dpsRankings, hpsRankings));
        }
        return result;
    }

    private static List<Ranking> createRankings(List<ReportRanking> rankings, List<Player> players, Boss boss, LocalDateTime timestamp) {
        List<Ranking> result = new ArrayList<>();

        for(ReportRanking rank : rankings) {
            Optional<Player> player = players.stream().filter(p->p.getName().equals(rank.getPlayerName())).findFirst();

            if(player.isPresent()) {
                result.add(new Ranking(boss, player.get(), timestamp, rank.getRankingType(), rank.getRankPercent()));
            } else {
                // TODO: Figure out a better way to match player and ranking than by name
                log.error("Unable to find player with name {}", rank.getPlayerName());
            }
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

        if(ability==null) {
            log.warn("Unable to find ability with id {}", event.getAbilityId());
        }

        return new Event(timestamp, source, target, ability, eventType, amount, unmitigatedAmount);
    }

    private static List<Player> getPlayers(List<Integer> playerIds, List<ReportPlayer> players) {
        List<Player> result = new ArrayList<>();

        for(int id : playerIds) {
            Optional<ReportPlayer> optional = players.stream().filter(p->p.getReportId()==id).findFirst();

            if(optional.isPresent()) {
                ReportPlayer player = optional.get();
                if (!knownPlayers.containsKey(player.getId())) {
                    knownPlayers.put(player.getId(), new Player(player.getId(), player.getName(), player.getPlayerClass()));
                }
                result.add(knownPlayers.get(player.getId()));
            }
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
