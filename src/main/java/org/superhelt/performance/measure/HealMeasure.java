package org.superhelt.performance.measure;

import org.superhelt.performance.om.Ability;
import org.superhelt.performance.om.Encounter;
import org.superhelt.performance.om.EventType;
import org.superhelt.performance.om.Player;

public class HealMeasure implements Measure {

    private final Ability ability;

    public HealMeasure(Ability ability) {
        this.ability = ability;
    }

    @Override
    public String getName() {
        return ability.getName();
    }

    @Override
    public int calculate(Encounter encounter, Player player) {
        return (int)encounter.getEvents().stream()
                .filter(e->e.getEventType()== EventType.HEAL)
                .filter(e->e.getAbility().equals(ability))
                .filter(e->e.getSource().equals(player))
                .count();
    }
}