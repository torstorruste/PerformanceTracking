package org.superhelt.performance.om;

import java.util.List;

public class Statistics {

    private final Encounter encounter;
    private List<PlayerStatistics> data;

    public Statistics(Encounter encounter, List<PlayerStatistics> data) {
        this.encounter = encounter;
        this.data = data;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public List<PlayerStatistics> getData() {
        return data;
    }
}
