package org.superhelt.performance.reportprovider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.superhelt.performance.om.warcraftlogs.ReportRanking;
import org.superhelt.performance.om.RankingType;

import java.util.ArrayList;
import java.util.List;

public class RankingProvider implements ValueProvider<List<ReportRanking>> {

    private final RankingType rankingType;

    public RankingProvider(RankingType rankingType) {
        this.rankingType = rankingType;
    }

    @Override
    public String getQueryFragment() {
        return String.format("%1$s: rankings(difficulty: 5, playerMetric: %1$s, timeframe: Historical)", rankingType.toString().toLowerCase());
    }

    @Override
    public List<ReportRanking> getValues(JsonObject report) {
        List<ReportRanking> result = new ArrayList<>();

        JsonArray data = report.get(rankingType.toString().toLowerCase()).getAsJsonObject().get("data").getAsJsonArray();
        for(int i=0;i<data.size();i++) {
            JsonObject ranking = data.get(i).getAsJsonObject();
            int fightId = ranking.get("fightID").getAsInt();

            result.addAll(getValues(fightId, ranking.get("roles").getAsJsonObject().get("tanks").getAsJsonObject().get("characters").getAsJsonArray()));
            result.addAll(getValues(fightId, ranking.get("roles").getAsJsonObject().get("healers").getAsJsonObject().get("characters").getAsJsonArray()));
            result.addAll(getValues(fightId, ranking.get("roles").getAsJsonObject().get("dps").getAsJsonObject().get("characters").getAsJsonArray()));
        }

        return result;
    }

    private List<ReportRanking> getValues(int fightId, JsonArray array) {
        List<ReportRanking> result = new ArrayList<>();

        for(int i=0;i<array.size();i++) {
            JsonObject jsonObject = array.get(i).getAsJsonObject();
            String playerName = jsonObject.get("name").getAsString();
            int rankPercent = jsonObject.get("rankPercent").getAsInt();

            result.add(new ReportRanking(fightId, playerName, rankingType, rankPercent));
        }

        return result;
    }
}
