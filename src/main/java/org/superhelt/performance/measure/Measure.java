package org.superhelt.performance.measure;

import org.superhelt.performance.om.Boss;
import org.superhelt.performance.om.Encounter;
import org.superhelt.performance.om.Player;
import org.superhelt.performance.om.PlayerClass;

public interface Measure {

    String getName();
    MeasureType getType();
    Integer getBossId();
    PlayerClass getPlayerClass();
    int calculate(Encounter encounter, Player player);
    boolean isRelevant(Boss boss, Player player);
}
