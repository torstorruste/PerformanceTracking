package org.superhelt.performance.data;

import org.superhelt.performance.om.Event;
import org.superhelt.performance.om.Report;
import org.superhelt.performance.eventprovider.EventProvider;

import java.util.List;

public interface DataClient {

    public Report getReport(String reportId);
    List<Event> getEvents(Report report, List<EventProvider> eventProviders);
}
