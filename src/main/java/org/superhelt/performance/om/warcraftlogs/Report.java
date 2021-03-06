package org.superhelt.performance.om.warcraftlogs;

import java.time.LocalDateTime;
import java.util.List;

public class Report {

    private final String code;
    private final List<Fight> fights;
    private final List<ReportPlayer> players;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Report(String code, List<Fight> fights, List<ReportPlayer> players, LocalDateTime startTime, LocalDateTime endTime) {
        this.code = code;
        this.fights = fights;
        this.players = players;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
