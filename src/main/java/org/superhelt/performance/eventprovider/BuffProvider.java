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

public class BuffProvider implements EventProvider {

    private static final Logger log = LoggerFactory.getLogger(BuffProvider.class);

    private final String name;
    private final int abilityId;

    public BuffProvider(String name, int abilityId) {
        this.name = name;
        this.abilityId = abilityId;
    }

    @Override
    public String getQueryFragment(Report report) {
        var endTime = Duration.between(report.getStartTime(), report.getEndTime()).getSeconds()*1000;

        return String.format("%s: events(abilityID: %d, startTime: %d, endTime: %d, dataType: Buffs) {data}",
                name, abilityId, 0, endTime);
    }

    @Override
    public List<Event> getValues(Report report, JsonObject json) {
        log.debug("Preparing to fetch data for buff {}", name);
        JsonArray data = json.get(name).getAsJsonObject().get("data").getAsJsonArray();

        List<Event> result = new ArrayList<>();
        for(int i=0;i<data.size();i++) {
            EventUtils.parseEvent(data.get(i).getAsJsonObject(), report, e->abilityId).ifPresent(result::add);
        }

        log.debug("Found {} events for buff {}", result.size(), name);
        return result;
    }
}
