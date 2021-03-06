package org.superhelt.performance.eventprovider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.superhelt.performance.om.Ability;
import org.superhelt.performance.om.warcraftlogs.WarcraftLogsEvent;
import org.superhelt.performance.om.warcraftlogs.Report;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HealingProvider implements EventProvider {

    private static final Logger log = LoggerFactory.getLogger(HealingProvider.class);
    private final Ability ability;


    public HealingProvider(Ability ability) {
        this.ability = ability;
    }

    @Override
    public String getQueryFragment(Report report) {
        var endTime = Duration.between(report.getStartTime(), report.getEndTime()).getSeconds()*1000;

        return String.format("%s: events(abilityID: %d, startTime: %d, endTime: %d, dataType: Healing) {data}",
                ability.getWarcraftlogsName(), ability.getId(), 0, endTime);
    }

    @Override
    public List<WarcraftLogsEvent> getValues(Report report, JsonObject json) {
        log.debug("Preparing to fetch data for heal {}", ability.getName());
        JsonArray data = json.get(ability.getWarcraftlogsName()).getAsJsonObject().get("data").getAsJsonArray();

        List<WarcraftLogsEvent> result = new ArrayList<>();
        for(int i=0;i<data.size();i++) {
            EventUtils.parseEvent(data.get(i).getAsJsonObject(), report, e-> ability.getId()).ifPresent(result::add);
        }

        log.debug("Found {} events for heal {}", result.size(), ability.getName());
        return result;
    }
}
