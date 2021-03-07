package org.superhelt.performance;

import org.superhelt.performance.measure.Measure;
import org.superhelt.performance.om.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsGenerator {

    public List<Statistics> generateStatistics(List<Encounter> encounters, List<Measure> measures) {
        List<Statistics> result = new ArrayList<>();

        for(Encounter encounter : encounters) {
            List<PlayerStatistics> playerStatistics = new ArrayList<>();

            for(Player player : encounter.getPlayers()) {
                Map<String, Integer> numbers = new HashMap<>();
                for(Measure measure : measures) {
                    if(measure.isRelevant(encounter.getBoss(), player)) {
                        int number = measure.calculate(encounter, player);
                        numbers.put(measure.getName(), number);
                    }
                }
                playerStatistics.add(new PlayerStatistics(player, numbers));
            }

            result.add(new Statistics(encounter, playerStatistics));
        }

        return result;
    }

    public AggregatedStatistics aggregate(List<Statistics> statistics) {
        Map<Player, PlayerStatistics> result = new HashMap<>();
        List<Encounter> encounters = new ArrayList<>();
        for(Statistics statistic : statistics) {
            encounters.add(statistic.getEncounter());
            for(PlayerStatistics data : statistic.getData()) {
                Player player = data.getPlayer();
                if(!result.containsKey(player)) {
                    result.put(player, data);
                } else {
                    result.put(player, data.merge(result.get(player)));
                }
            }
        }

        return new AggregatedStatistics(encounters, new ArrayList<>(result.values()));
    }
}
