package org.superhelt.performance.om;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.superhelt.performance.resources.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.List;

public class Encounter {

    private final Boss boss;
    private final List<Player> players;
    @JsonIgnore
    private final List<Event> events;
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    private final LocalDateTime startTime;
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    private final LocalDateTime endTime;
    private final boolean progress;

    public Encounter(Boss boss, List<Player> players, List<Event> events, LocalDateTime startTime, LocalDateTime endTime, boolean progress) {
        this.boss = boss;
        this.players = players;
        this.events = events;
        this.startTime = startTime;
        this.endTime = endTime;
        this.progress = progress;
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

    public boolean isProgress() {
        return progress;
    }
}
