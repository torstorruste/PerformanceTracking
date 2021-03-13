package org.superhelt.performance.measure;

import org.superhelt.performance.om.*;

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
    public MeasureType getType() {
        return MeasureType.HEALING;
    }

    @Override
    public Integer getBossId() {
        return null;
    }

    @Override
    public PlayerClass getPlayerClass() {
        return null;
    }

    @Override
    public int calculate(Encounter encounter, Player player) {
        return (int)encounter.getEvents().stream()
                .filter(e->e.getEventType()== EventType.HEAL)
                .filter(e->e.getAbility().equals(ability))
                .filter(e->e.getSource().equals(player))
                .count();
    }

    @Override
    public boolean isRelevant(Boss boss, Player player) {
        return ability.isRelevant(boss, player);
    }
}
