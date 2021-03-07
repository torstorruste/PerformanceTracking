package org.superhelt.performance;

import org.superhelt.performance.measure.Measure;
import org.superhelt.performance.om.Encounter;
import org.superhelt.performance.om.Player;
import org.superhelt.performance.om.PlayerStatistics;
import org.superhelt.performance.om.Statistics;

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
}
