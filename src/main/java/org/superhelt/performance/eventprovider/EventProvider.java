package org.superhelt.performance.eventprovider;

import com.google.gson.JsonObject;
import org.superhelt.performance.om.warcraftlogs.WarcraftLogsEvent;
import org.superhelt.performance.om.warcraftlogs.Report;

import java.util.List;

public interface EventProvider {
    String getQueryFragment(Report report, int startTime);
    List<WarcraftLogsEvent> getValues(Report report, JsonObject json);
    Integer getNextTimestamp(JsonObject json);
}
