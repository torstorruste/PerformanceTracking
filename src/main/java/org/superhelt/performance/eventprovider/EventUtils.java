package org.superhelt.performance.eventprovider;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.superhelt.performance.om.Event;
import org.superhelt.performance.om.EventType;
import org.superhelt.performance.om.Report;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.function.Function;

public class EventUtils {

    private static final Logger log = LoggerFactory.getLogger(EventUtils.class);

    public static Optional<Event> parseEvent(JsonObject event, Report report, Function<JsonObject, Integer> abilityIdParser) {
        try {
            if(event.get("fight")!=null) {
                int fightId = event.get("fight").getAsInt();
                long diff = event.get("timestamp").getAsLong();
                LocalDateTime timestamp = report.getStartTime().plus(diff, ChronoUnit.MILLIS);
                int sourceId = event.get("sourceID").getAsInt();
                int targetId = event.get("targetID").getAsInt();
                EventType eventType = EventType.fromJson(event.get("type").getAsString());

                int abilityId = abilityIdParser.apply(event);

                return Optional.of(new Event(fightId, timestamp, sourceId, targetId, abilityId, eventType));
            } else {
                log.warn("Ignoring event {}: fight is null", event);
            }
        } catch(Exception e) {
            log.error("Unable to parse event {}: {}", event, e.getMessage());
        }
        return Optional.empty();
    }
}
