package org.superhelt.performance.measure;

import org.superhelt.performance.om.Encounter;
import org.superhelt.performance.om.Event;
import org.superhelt.performance.om.EventType;
import org.superhelt.performance.om.Player;

import java.util.Comparator;

public class EarlyDeathMeasure implements Measure {
    @Override
    public String getName() {
        return "Early deaths (first three)";
    }

    @Override
    public int calculate(Encounter encounter, Player player) {
        return (int)encounter.getEvents().stream()
                .filter(e->e.getEventType()== EventType.DEATH)
                .sorted(Comparator.comparing(Event::getTimestamp))
                .limit(3)
                .filter(e->e.getTarget().equals(player))
                .count();
    }
}
