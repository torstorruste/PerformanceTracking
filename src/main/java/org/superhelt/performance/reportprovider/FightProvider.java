package org.superhelt.performance.reportprovider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.superhelt.performance.om.Fight;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FightProvider implements ValueProvider<List<Fight>> {

    @Override
    public String getQueryFragment() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\tfights(difficulty: 5) {\n");
        sb.append("\t\t\t\tencounterID,\n");
        sb.append("\t\t\t\tfightPercentage,\n");
        sb.append("\t\t\t\tstartTime,\n");
        sb.append("\t\t\t\tendTime,\n");
        sb.append("\t\t\t\tname,\n");
        sb.append("\t\t\t\tid,\n");
        sb.append("\t\t\t\tkill\n");
        sb.append("\t\t\t}\n");
        return sb.toString();
    }

    @Override
    public List<Fight> getValues(JsonObject report) {
        long startTimeLong = report.get("startTime").getAsLong();
        var startTime = LocalDateTime.ofEpochSecond(startTimeLong/1000, (int)(startTimeLong%1000)*1000000, ZoneOffset.ofHours(1));

        JsonArray fights = report.get("fights").getAsJsonArray();

        List<Fight> result = new ArrayList<>();
        for(int i=0;i<fights.size();i++) {
            result.add(parseFight(fights.get(i).getAsJsonObject(), startTime));
        }

        return result;
    }

    private Fight parseFight(JsonObject fight, LocalDateTime raidStart) {
        int id = fight.get("id").getAsInt();
        int encounterId = fight.get("encounterID").getAsInt();
        LocalDateTime startTime = raidStart.plus(fight.get("startTime").getAsLong(), ChronoUnit.MILLIS);
        LocalDateTime endTime = raidStart.plus(fight.get("endTime").getAsLong(), ChronoUnit.MILLIS);
        String name = fight.get("name").getAsString();
        double percentage = fight.get("fightPercentage").getAsDouble();
        boolean kill = fight.get("kill").getAsBoolean();

        return new Fight(id, encounterId, startTime, endTime, name, percentage, kill);
    }
}
