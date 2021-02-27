package org.superhelt.performance;

import org.superhelt.performance.data.CachedDataClient;
import org.superhelt.performance.data.QueryBuilder;
import org.superhelt.performance.data.WarcraftLogsClient;
import org.superhelt.performance.eventprovider.EventProviders;

public class PerformanceTracker {

    public static void main(String[] args) {
        var client = new CachedDataClient(new WarcraftLogsClient(new QueryBuilder()));

        var report = client.getReport("MdPr1Y6VwHWLZ2AB");
        var events = client.getEvents(report, EventProviders.defensives());

        System.out.println(report.getCode());
        System.out.println(events.size());
    }
}
