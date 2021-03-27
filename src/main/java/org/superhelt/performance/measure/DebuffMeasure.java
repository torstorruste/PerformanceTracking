package org.superhelt.performance.measure;

import org.superhelt.performance.om.*;

public class DebuffMeasure implements Measure {

    private final Ability ability;

    public DebuffMeasure(Ability ability) {
        this.ability = ability;
    }

    @Override
    public String getName() {
        return "Possession";
    }

    @Override
    public MeasureType getType() {
        return MeasureType.MECHANIC;
    }

    @Override
    public int calculate(Encounter encounter, Player player) {
        return (int)encounter.getEvents().stream()
                .filter(e->e.getEventType() == EventType.APPLY_DEBUFF)
                .filter(e->e.getAbility().equals(ability))
                .filter(e->e.getTarget().equals(player))
                .count();
    }

    @Override
    public boolean isRelevant(Boss boss, Player player) {
        return ability.isRelevant(boss, player);
    }
}
