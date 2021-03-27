package org.superhelt.performance.measure;

import org.superhelt.performance.om.*;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NightHunterDoubleSoakMeasure implements Measure {
    @Override
    public String getName() {
        return "Night hunter double soaks";
    }

    @Override
    public MeasureType getType() {
        return MeasureType.MECHANIC;
    }

    @Override
    public int calculate(Encounter encounter, Player player) {
        List<Event> damageEvents = encounter.getEvents().stream()
                .filter(e->e.getEventType() == EventType.DAMAGE)
                .filter(e->e.getTarget().equals(player))
                .filter(e->e.getAbility().equals(Abilities.NIGHT_HUNTER_DAMAGE))
                .sorted(Comparator.comparing(Event::getTimestamp))
                .collect(Collectors.toList());

        var groups = EventGroup.groupByTime(damageEvents, 5);

        return (int) groups.stream().filter(g->g.size()>1).count();
    }

    @Override
    public boolean isRelevant(Boss boss, Player player) {
        return boss.getId()==Bosses.SIRE;
    }
}
