package org.superhelt.performance.data;

import org.superhelt.performance.om.warcraftlogs.WarcraftLogsEvent;
import org.superhelt.performance.om.warcraftlogs.Report;
import org.superhelt.performance.eventprovider.EventProvider;

import java.util.List;

public interface DataClient {

    List<String> getReportIds(int guildId);
    Report getReport(String reportId);
    List<WarcraftLogsEvent> getEvents(Report report, List<EventProvider> eventProviders);
}
