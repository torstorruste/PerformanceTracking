package org.superhelt.performance.eventprovider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.superhelt.performance.om.warcraftlogs.WarcraftLogsEvent;
import org.superhelt.performance.om.warcraftlogs.Report;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DeathsProvider implements EventProvider {

    private static final Logger log = LoggerFactory.getLogger(DeathsProvider.class);

    @Override
    public String getQueryFragment(Report report) {
        var endTime = Duration.between(report.getStartTime(), report.getEndTime()).getSeconds()*1000;

        return String.format("Deaths: events(startTime: %d, endTime: %d, dataType: Deaths) {data}",
                0, endTime);
    }

    @Override
    public List<WarcraftLogsEvent> getValues(Report report, JsonObject json) {
        log.debug("Preparing to fetch data for deaths");
        JsonArray data = json.get("Deaths").getAsJsonObject().get("data").getAsJsonArray();

        List<WarcraftLogsEvent> result = new ArrayList<>();
        for(int i=0;i<data.size();i++) {
            EventUtils.parseEvent(data.get(i).getAsJsonObject(), report, (e)->e.get("killingAbilityGameID") != null?e.get("killingAbilityGameID").getAsInt():-1).ifPresent(result::add);
        }

        log.debug("Found {} events for deaths", result.size());
        return result;
    }
}
