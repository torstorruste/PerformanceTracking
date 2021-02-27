package org.superhelt.performance.eventprovider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.superhelt.performance.om.Event;
import org.superhelt.performance.om.EventType;
import org.superhelt.performance.om.Report;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DeathsProvider implements EventProvider {

    private static final Logger log = LoggerFactory.getLogger(DeathsProvider.class);

    @Override
    public String getQueryFragment(Report report) {
        var endTime = Duration.between(report.getStartTime(), report.getEndTime()).getSeconds()*1000;

        return String.format("Deaths: events(startTime: %d, endTime: %d, dataType: Deaths) {data}",
                0, endTime);
    }

    @Override
    public List<Event> getValues(Report report, JsonObject json) {
        log.debug("Preparing to fetch data for deaths");
        JsonArray data = json.get("Deaths").getAsJsonObject().get("data").getAsJsonArray();

        List<Event> result = new ArrayList<>();
        for(int i=0;i<data.size();i++) {
            result.add(parseEvent(data.get(i).getAsJsonObject(), report));
        }

        log.debug("Found {} events for deaths", result.size());
        return result;
    }

    private Event parseEvent(JsonObject event, Report report) {
        int fightId = event.get("fight").getAsInt();
        long diff = event.get("timestamp").getAsLong();
        LocalDateTime timestamp = report.getStartTime().plus(diff, ChronoUnit.MILLIS);
        int sourceId = event.get("sourceID").getAsInt();
        int targetId = event.get("targetID").getAsInt();
        EventType eventType = EventType.fromJson(event.get("type").getAsString());
        int abilityId = -1;
        if(event.get("killingAbilityGameID")!=null) {
            abilityId = event.get("killingAbilityGameID").getAsInt();
        }

        return new Event(fightId, timestamp, sourceId, targetId, abilityId, eventType);
    }
}
