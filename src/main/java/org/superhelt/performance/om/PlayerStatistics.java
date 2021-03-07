package org.superhelt.performance.om;

import java.util.HashMap;
import java.util.Map;

public class PlayerStatistics {

    private final Player player;
    private final Map<String, Integer> data;

    public PlayerStatistics(Player player, Map<String, Integer> data) {
        this.player = player;
        this.data = data;
    }

    public Player getPlayer() {
        return player;
    }

    public Map<String, Integer> getData() {
        return data;
    }

    public PlayerStatistics merge(PlayerStatistics other) {
        Map<String, Integer> newData = new HashMap<>();
        for(Map.Entry<String, Integer> entry : data.entrySet()) {
            newData.put(entry.getKey(), entry.getValue());
        }
        for(Map.Entry<String, Integer> entry : other.getData().entrySet()) {
            if(newData.containsKey(entry.getKey())) {
                newData.put(entry.getKey(), entry.getValue()+newData.get(entry.getKey()));
            } else {
                newData.put(entry.getKey(), entry.getValue());
            }
        }

        return new PlayerStatistics(player, newData);
    }
}
