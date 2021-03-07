package org.superhelt.performance.om;

import java.util.Map;

public class PlayerStatistics {

    private final Player player;
    private final Map<String, Integer> data;

    public PlayerStatistics(Player player, Map<String, Integer> data) {
        this.player = player;
        this.data = data;
    }
}
