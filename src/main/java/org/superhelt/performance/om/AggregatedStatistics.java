package org.superhelt.performance.om;

import java.util.List;

public class AggregatedStatistics {

    private final List<Encounter> encounters;
    private final List<PlayerStatistics> data;

    public AggregatedStatistics(List<Encounter> encounters, List<PlayerStatistics> data) {
        this.encounters = encounters;
        this.data = data;
    }

    public List<Encounter> getEncounters() {
        return encounters;
    }

    public List<PlayerStatistics> getData() {
        return data;
    }
}
