package org.superhelt.performance.om.warcraftlogs;

import java.time.LocalDateTime;
import java.util.List;

public class Fight {
    private final int id;
    private final int encounterId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String name;
    private final double percentage;
    private final boolean kill;
    private final List<Integer> playerIds;

    public Fight(int id, int encounterId, LocalDateTime startTime, LocalDateTime endTime, String name, double percentage, boolean kill, List<Integer> playerIds) {
        this.id = id;
        this.encounterId = encounterId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.percentage = percentage;
        this.kill = kill;
        this.playerIds = playerIds;
    }

    public int getId() {
        return id;
    }

    public int getEncounterId() {
        return encounterId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getName() {
        return name;
    }

    public double getPercentage() {
        return percentage;
    }

    public boolean isKill() {
        return kill;
    }

    public List<Integer> getPlayerIds() {
        return playerIds;
    }
}
