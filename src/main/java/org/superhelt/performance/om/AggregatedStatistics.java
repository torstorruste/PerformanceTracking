package org.superhelt.performance.om;

import java.util.List;

public class AggregatedStatistics {

    private final List<PlayerStatistics> data;

    public AggregatedStatistics(List<PlayerStatistics> data) {
        this.data = data;
    }
}
