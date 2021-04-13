package org.superhelt.performance.om;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.superhelt.performance.resources.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class Status {

    private final int numEncounters;
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    private final LocalDateTime firstEncounter;
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    private final LocalDateTime lastEncounter;

    public Status(int numEncounters, LocalDateTime firstEncounter, LocalDateTime lastEncounter) {
        this.numEncounters = numEncounters;
        this.firstEncounter = firstEncounter;
        this.lastEncounter = lastEncounter;
    }

    public int getNumEncounters() {
        return numEncounters;
    }

    public LocalDateTime getFirstEncounter() {
        return firstEncounter;
    }

    public LocalDateTime getLastEncounter() {
        return lastEncounter;
    }
}
