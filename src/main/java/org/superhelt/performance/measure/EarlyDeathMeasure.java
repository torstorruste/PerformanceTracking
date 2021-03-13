package org.superhelt.performance.measure;

import org.superhelt.performance.om.*;

import java.util.Comparator;

public class EarlyDeathMeasure implements Measure {
    @Override
    public String getName() {
        return "Early deaths (first three)";
    }

    @Override
    public MeasureType getType() {
        return MeasureType.BASIC;
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
                .filter(e->e.getEventType()== EventType.DEATH)
                .sorted(Comparator.comparing(Event::getTimestamp))
                .limit(3)
                .filter(e->e.getTarget().equals(player))
                .count();
    }

    @Override
    public boolean isRelevant(Boss boss, Player player) {
        return true;
    }
}
