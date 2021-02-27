package org.superhelt.performance;

import org.superhelt.performance.data.CachedDataClient;
import org.superhelt.performance.data.QueryBuilder;
import org.superhelt.performance.data.WarcraftLogsClient;
import org.superhelt.performance.eventprovider.BuffProvider;

public class PerformanceTracker {

    public static void main(String[] args) {
        var client = new CachedDataClient(new WarcraftLogsClient(new QueryBuilder()));

        var report = client.getReport("MdPr1Y6VwHWLZ2AB");
        var events = client.getEvents(report,
                new BuffProvider("Barkskin", 22812),
                new BuffProvider("DieByTheSword", 118038),
                new BuffProvider("SpellReflection", 23920));

        System.out.println(report.getCode());
        System.out.println(events.size());
    }
}
