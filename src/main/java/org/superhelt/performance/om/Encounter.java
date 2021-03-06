package org.superhelt.performance.om;

import java.time.LocalDateTime;
import java.util.List;

public class Encounter {

    private final Boss boss;
    private final List<Player> players;
    private final List<Event> events;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Encounter(Boss boss, List<Player> players, List<Event> events, LocalDateTime startTime, LocalDateTime endTime) {
        this.boss = boss;
        this.players = players;
        this.events = events;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Boss getBoss() {
        return boss;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Event> getEvents() {
        return events;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
