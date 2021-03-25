package org.superhelt.performance.measure;

import org.superhelt.performance.om.*;

public class FarmMeasure implements Measure {

    @Override
    public String getName() {
        return "Farm";
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
        return encounter.getEncounterType()==EncounterType.FARM?1:0;
    }

    @Override
    public boolean isRelevant(Boss boss, Player player) {
        return true;
    }
}
