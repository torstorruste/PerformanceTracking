package org.superhelt.performance.measure;

import org.superhelt.performance.om.*;

public class DefensiveMeasure implements Measure {

    private final Ability ability;

    public DefensiveMeasure(Ability ability) {
        this.ability = ability;
    }

    @Override
    public String getName() {
        return ability.getName();
    }

    @Override
    public MeasureType getType() {
        return MeasureType.DEFENSIVE;
    }

    @Override
    public Integer getBossId() {
        return null;
    }

    @Override
    public PlayerClass getPlayerClass() {
        if(ability instanceof ClassAbility) {
            return ((ClassAbility)ability).getPlayerClass();
        }
        return null;
    }

    @Override
    public int calculate(Encounter encounter, Player player) {
        return (int)encounter.getEvents().stream()
                .filter(e->e.getEventType()== EventType.APPLY_BUFF)
                .filter(e->e.getAbility().equals(ability))
                .filter(e->e.getSource()!=null && e.getSource().equals(player))
                .count();
    }

    @Override
    public boolean isRelevant(Boss boss, Player player) {
        return ability.isRelevant(boss, player);
    }
}
