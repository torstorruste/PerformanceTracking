package org.superhelt.performance.measure;

import org.superhelt.performance.om.Boss;
import org.superhelt.performance.om.Encounter;
import org.superhelt.performance.om.Player;

public interface Measure {

    String getName();
    int calculate(Encounter encounter, Player player);
    boolean isRelevant(Boss boss, Player player);
}
