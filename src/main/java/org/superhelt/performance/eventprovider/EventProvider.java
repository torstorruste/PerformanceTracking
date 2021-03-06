package org.superhelt.performance.eventprovider;

import com.google.gson.JsonObject;
import org.superhelt.performance.om.Event;
import org.superhelt.performance.om.warcraftlogs.Report;

import java.util.List;

public interface EventProvider {
    String getQueryFragment(Report report);
    List<Event> getValues(Report report, JsonObject json);
}
