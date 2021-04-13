package org.superhelt.performance.measure;

import org.superhelt.performance.om.*;

import java.util.List;
import java.util.stream.Collectors;

public class FatalFinesseSoakingMeasure implements Measure {
    @Override
    public String getName() {
        return "Fatal Finesse soaking";
    }

    @Override
    public MeasureType getType() {
        return MeasureType.MECHANIC;
    }

    @Override
    public int calculate(Encounter encounter, Player player) {
        List<Event> events = encounter.getEvents().stream()
                .filter(e->e.getEventType()== EventType.DAMAGE)
                .filter(e->e.getAbility()==Abilities.FATAL_FINESSE)
                .filter(e->e.getTarget().equals(player))
                .filter(e->e.getAmount()<5000)
                .collect(Collectors.toList());

        return events.size();
    }

    @Override
    public boolean isRelevant(Boss boss, Player player) {
        return boss.getId()== Bosses.SIRE;
    }
}
