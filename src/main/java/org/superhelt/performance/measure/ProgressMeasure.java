package org.superhelt.performance.measure;

import org.superhelt.performance.om.*;

public class ProgressMeasure implements Measure {
    @Override
    public String getName() {
        return "Progress";
    }

    @Override
    public MeasureType getType() {
        return MeasureType.ENCOUNTER;
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
        return encounter.getEncounterType()==EncounterType.PROGRESS?1:0;
    }

    @Override
    public boolean isRelevant(Boss boss, Player player) {
        return true;
    }
}
