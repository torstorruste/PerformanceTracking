package org.superhelt.performance.valueprovider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.superhelt.performance.om.Event;
import org.superhelt.performance.om.EventType;
import org.superhelt.performance.om.Report;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BuffProvider extends EventProvider {

    private final int abilityId;
    private final Report report;

    public BuffProvider(String name, int abilityId, Report report) {
        super(name);
        this.abilityId = abilityId;
        this.report = report;
    }

    @Override
    public String getQueryFragment() {
        var endTime = Duration.between(report.getStartTime(), report.getEndTime()).getSeconds()*1000;

        return String.format("%s: events(abilityID: %d, startTime: %d, endTime: %d, dataType: Buffs) {data}",
                name, abilityId, 0, endTime);
    }

    @Override
    public List<Event> getValues(JsonObject report) {
        JsonArray data = report.get(name).getAsJsonObject().get("data").getAsJsonArray();

        List<Event> result = new ArrayList<>();
        for(int i=0;i<data.size();i++) {
            result.add(parseEvent(data.get(i).getAsJsonObject()));
        }

        return result;
    }

    private Event parseEvent(JsonObject event) {
        int fightId = event.get("fight").getAsInt();
        long diff = event.get("timestamp").getAsLong();
        LocalDateTime timestamp = report.getStartTime().plus(diff, ChronoUnit.MILLIS);
        int sourceId = event.get("sourceID").getAsInt();
        int targetId = event.get("targetID").getAsInt();
        EventType eventType = EventType.fromJson(event.get("type").getAsString());

        return new Event(fightId, timestamp, sourceId, targetId, abilityId, eventType);
    }
}
