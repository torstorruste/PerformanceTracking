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

public class HealingProvider implements EventProvider {

    private static final Logger log = LoggerFactory.getLogger(HealingProvider.class);

    private final String name;
    private final int abilityId;

    public HealingProvider(String name, int abilityId) {
        this.name = name;
        this.abilityId = abilityId;
    }

    @Override
    public String getQueryFragment(Report report) {
        var endTime = Duration.between(report.getStartTime(), report.getEndTime()).getSeconds()*1000;

        return String.format("%s: events(abilityID: %d, startTime: %d, endTime: %d, dataType: Healing) {data}",
                name, abilityId, 0, endTime);
    }

    @Override
    public List<Event> getValues(Report report, JsonObject json) {
        log.debug("Preparing to fetch data for heal {}", name);
        JsonArray data = json.get(name).getAsJsonObject().get("data").getAsJsonArray();

        List<Event> result = new ArrayList<>();
        for(int i=0;i<data.size();i++) {
            result.add(parseEvent(data.get(i).getAsJsonObject(), report));
        }

        log.debug("Found {} events for heal {}", result.size(), name);
        return result;
    }

    private Event parseEvent(JsonObject event, Report report) {
        int fightId = event.get("fight").getAsInt();
        long diff = event.get("timestamp").getAsLong();
        LocalDateTime timestamp = report.getStartTime().plus(diff, ChronoUnit.MILLIS);
        int sourceId = event.get("sourceID").getAsInt();
        int targetId = event.get("targetID").getAsInt();
        EventType eventType = EventType.fromJson(event.get("type").getAsString());

        return new Event(fightId, timestamp, sourceId, targetId, abilityId, eventType);
    }
}
