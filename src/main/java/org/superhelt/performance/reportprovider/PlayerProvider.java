package org.superhelt.performance.reportprovider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.superhelt.performance.om.PlayerClass;
import org.superhelt.performance.om.warcraftlogs.ReportPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayerProvider implements ValueProvider<List<ReportPlayer>> {
    @Override
    public String getQueryFragment() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\tmasterData {\n");
        sb.append("\t\t\t\tactors(type:\"Player\") {\n");
        sb.append("\t\t\t\t\tgameID,\n");
        sb.append("\t\t\t\t\tid,\n");
        sb.append("\t\t\t\t\tname,\n");
        sb.append("\t\t\t\t\ttype,\n");
        sb.append("\t\t\t\t\tsubType\n");
        sb.append("\t\t\t\t}\n");
        sb.append("\t\t\t}\n");
        return sb.toString();
    }

    @Override
    public List<ReportPlayer> getValues(JsonObject report) {
        JsonArray actors = report.get("masterData").getAsJsonObject().get("actors").getAsJsonArray();

        List<ReportPlayer> result = new ArrayList<>();
        for(int i=0;i<actors.size();i++) {
            result.add(parsePlayer(actors.get(i).getAsJsonObject()));
        }

        return result;
    }

    private ReportPlayer parsePlayer(JsonObject player) {
        int id = player.get("gameID").getAsInt();
        String name = player.get("name").getAsString();
        PlayerClass playerClass = PlayerClass.valueOf(player.get("subType").getAsString().toUpperCase());
        int reportId = player.get("id").getAsInt();

        return new ReportPlayer(id, name, playerClass, reportId);
    }
}
