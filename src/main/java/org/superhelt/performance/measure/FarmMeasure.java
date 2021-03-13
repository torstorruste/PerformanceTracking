package org.superhelt.performance.measure;

import org.superhelt.performance.om.Boss;
import org.superhelt.performance.om.Encounter;
import org.superhelt.performance.om.Player;
import org.superhelt.performance.om.PlayerClass;

public class FarmMeasure implements Measure {

    @Override
    public String getName() {
        return "Farm";
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
        return encounter.isProgress()?0:1;
    }

    @Override
    public boolean isRelevant(Boss boss, Player player) {
        return true;
    }
}
