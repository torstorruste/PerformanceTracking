package org.superhelt.performance.measure;

import org.superhelt.performance.om.*;

import java.util.stream.Collectors;

public class SeismicShiftSplashMeasure implements Measure {
    @Override
    public String getName() {
        return "Seismic Shift Splash";
    }

    @Override
    public MeasureType getType() {
        return MeasureType.MECHANIC;
    }

    @Override
    public int calculate(Encounter encounter, Player player) {
        var events = encounter.getEvents().stream()
                .filter(e->e.getEventType()==EventType.DAMAGE)
                .filter(e->e.getAbility()==Abilities.SEISMIC_SHIFT)
                .filter(e->e.getTarget().equals(player))
                .collect(Collectors.toList());

        var groups = EventGroup.groupByTime(events, 5);

        return (int) groups.stream().filter(g->g.size()>1).count();
    }

    @Override
    public boolean isRelevant(Boss boss, Player player) {
        return boss.getId()== Bosses.SLUDGEFIST;
    }
}
