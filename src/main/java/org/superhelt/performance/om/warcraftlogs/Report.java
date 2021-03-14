package org.superhelt.performance.om.warcraftlogs;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Report {

    private final String code;
    private final List<Fight> fights;
    private final List<ReportPlayer> players;
    private final List<ReportRanking> dpsRankings;
    private final List<ReportRanking> hpsRankings;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Report(String code, List<Fight> fights, List<ReportPlayer> players, List<ReportRanking> dpsRankings, List<ReportRanking> hpsRankings, LocalDateTime startTime, LocalDateTime endTime) {
        this.code = code;
        this.fights = fights;
        this.players = players;
        this.dpsRankings = dpsRankings;
        this.hpsRankings = hpsRankings;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getCode() {
        return code;
    }

    public List<Fight> getFights() {
        return fights;
    }

    public List<ReportPlayer> getPlayers() {
        return players;
    }

    public List<ReportRanking> getDpsRankings() {
        return dpsRankings;
    }

    public List<ReportRanking> getDpsRankings(int fightId) {
        return dpsRankings.stream().filter(r->r.getFightId()==fightId).collect(Collectors.toList());
    }

    public List<ReportRanking> getHpsRankings() {
        return hpsRankings;
    }

    public List<ReportRanking> getHpsRankings(int fightId) {
        return hpsRankings.stream().filter(r->r.getFightId()==fightId).collect(Collectors.toList());
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
