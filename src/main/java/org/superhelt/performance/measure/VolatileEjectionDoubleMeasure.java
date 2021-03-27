package org.superhelt.performance.measure;

import org.superhelt.performance.om.*;

import java.util.stream.Collectors;

public class VolatileEjectionDoubleMeasure implements Measure {
    @Override
    public String getName() {
        return "Volatile Ejection double";
    }

    @Override
    public MeasureType getType() {
        return MeasureType.MECHANIC;
    }

    @Override
    public int calculate(Encounter encounter, Player player) {
        var events = encounter.getEvents().stream()
                .filter(e->e.getEventType()==EventType.DAMAGE)
                .filter(e->e.getAbility()==Abilities.VOLATILE_EJECTION)
                .filter(e->e.getTarget().equals(player))
                .collect(Collectors.toList());
        var groups = EventGroup.groupByTime(events, 40);
        return (int) groups.stream().filter(g->g.size()>1).count();
    }

    @Override
    public boolean isRelevant(Boss boss, Player player) {
        return boss.getId()==Bosses.HUNGERING;
    }
}
