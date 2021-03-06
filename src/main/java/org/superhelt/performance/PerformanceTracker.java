package org.superhelt.performance;

import org.superhelt.performance.data.CachedDataClient;
import org.superhelt.performance.data.QueryBuilder;
import org.superhelt.performance.data.WarcraftLogsClient;
import org.superhelt.performance.eventprovider.EventProvider;
import org.superhelt.performance.eventprovider.EventProviders;

import java.util.ArrayList;
import java.util.List;

public class PerformanceTracker {

    public static void main(String[] args) {
        var client = new CachedDataClient(new WarcraftLogsClient(new QueryBuilder()));

        for(String reportId : client.getReportIds(277050)) {

            var report = client.getReport(reportId);
            List<EventProvider> eventProviders = new ArrayList<>();
            eventProviders.addAll(EventProviders.defensives());
            eventProviders.addAll(EventProviders.heals());
            eventProviders.addAll(EventProviders.deaths());
            var events = client.getEvents(report, eventProviders);

            System.out.println(report.getCode());
            System.out.println(events.size());
        }
    }
}
