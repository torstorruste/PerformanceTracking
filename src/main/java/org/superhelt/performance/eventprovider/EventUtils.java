package org.superhelt.performance.eventprovider;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.superhelt.performance.om.warcraftlogs.WarcraftLogsEvent;
import org.superhelt.performance.om.EventType;
import org.superhelt.performance.om.warcraftlogs.Report;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.function.Function;

public class EventUtils {

    private static final Logger log = LoggerFactory.getLogger(EventUtils.class);

    public static Optional<WarcraftLogsEvent> parseEvent(JsonObject event, Report report, Function<JsonObject, Integer> abilityIdParser) {
        try {
            if(event.get("fight")!=null) {
                int fightId = event.get("fight").getAsInt();
                long diff = event.get("timestamp").getAsLong();
                LocalDateTime timestamp = report.getStartTime().plus(diff, ChronoUnit.MILLIS);
                int sourceId = event.get("sourceID").getAsInt();
                int targetId = event.get("targetID").getAsInt();
                EventType eventType = EventType.fromJson(event.get("type").getAsString());
                int amount = getInt(event, "amount");
                int mitigated = getInt(event, "mitigated");
                int unmitigatedAmount = getInt(event, "unmitigatedAmount");
                int overkill = getInt(event, "overkill");

                int abilityId = abilityIdParser.apply(event);

                if(abilityId==0) {
                    int a = 5;
                }

                return Optional.of(new WarcraftLogsEvent(fightId, timestamp, sourceId, targetId, abilityId, eventType, amount, mitigated, unmitigatedAmount, overkill));
            } else {
                log.warn("Ignoring event {}: fight is null", event);
            }
        } catch(Exception e) {
            log.error("Unable to parse event {}: {}", event, e.getMessage());
        }
        return Optional.empty();
    }

    private static int getInt(JsonObject json, String column) {
        if(json.get(column)!=null) {
            return json.get(column).getAsInt();
        } else {
            return 0;
        }
    }
}
