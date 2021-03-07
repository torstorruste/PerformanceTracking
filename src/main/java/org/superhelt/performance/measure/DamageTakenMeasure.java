package org.superhelt.performance.measure;

import org.superhelt.performance.om.Ability;
import org.superhelt.performance.om.Encounter;
import org.superhelt.performance.om.EventType;
import org.superhelt.performance.om.Player;

public class DamageTakenMeasure implements Measure {

    private final Ability ability;

    public DamageTakenMeasure(Ability ability) {
        this.ability = ability;
    }

    @Override
    public String getName() {
        return ability.getName();
    }

    @Override
    public int calculate(Encounter encounter, Player player) {
        return (int)encounter.getEvents().stream()
                .filter(e->e.getEventType()== EventType.DAMAGE)
                .filter(e->e.getAbility().equals(ability))
                .filter(e->e.getTarget().equals(player))
                .count();
    }
}
