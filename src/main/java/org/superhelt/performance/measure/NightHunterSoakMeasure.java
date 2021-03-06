package org.superhelt.performance.measure;

import org.superhelt.performance.om.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NightHunterSoakMeasure implements Measure {
    @Override
    public String getName() {
        return "Night hunter soaks";
    }

    @Override
    public MeasureType getType() {
        return MeasureType.MECHANIC;
    }

    @Override
    public int calculate(Encounter encounter, Player player) {
        int numBuffs = (int)encounter.getEvents().stream()
                .filter(e->e.getEventType() == EventType.APPLY_DEBUFF)
                .filter(e->e.getTarget().equals(player))
                .filter(e->e.getAbility().equals(Abilities.NIGHT_HUNTER_DEBUFF))
                .count();

        List<Event> damageEvents = encounter.getEvents().stream()
                .filter(e->e.getEventType() == EventType.DAMAGE)
                .filter(e->e.getTarget().equals(player))
                .filter(e->e.getAbility().equals(Abilities.NIGHT_HUNTER_DAMAGE))
                .sorted(Comparator.comparing(Event::getTimestamp))
                .collect(Collectors.toList());

        List<EventGroup> group = EventGroup.groupByTime(damageEvents, 5);

        return numBuffs + group.size();
    }

    @Override
    public boolean isRelevant(Boss boss, Player player) {
        return boss.getId()==Bosses.SIRE;
    }
}
