package org.superhelt.performance.om;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class EventGroup {
    private List<Event> events = new ArrayList<>();

    public EventGroup(Event event) {
        this.events.add(event);
    }

    public boolean shouldContain(Event candidate, int numSeconds) {
        for(Event event : events) {
            if(candidate.getTimestamp().minus(numSeconds, ChronoUnit.SECONDS).isBefore(event.getTimestamp())) {
                return true;
            }
        }
        return false;
    }

    public void add(Event event) {
        this.events.add(event);
    }

    public int size() {
        return events.size();
    }

    public static List<EventGroup> groupByTime(List<Event> events, int numSeconds) {
        List<EventGroup> result = new ArrayList<>();
        EventGroup currentGroup = null;
        for(Event current : events) {
            if (currentGroup == null || !currentGroup.shouldContain(current, numSeconds)) {
                currentGroup = new EventGroup(current);
                result.add(currentGroup);
            } else {
                currentGroup.add(current);
            }
        }

        return result;
    }
}
