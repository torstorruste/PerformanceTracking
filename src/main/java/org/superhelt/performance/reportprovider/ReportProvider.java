package org.superhelt.performance.reportprovider;

import com.google.gson.JsonObject;
import org.superhelt.performance.om.warcraftlogs.ReportRanking;
import org.superhelt.performance.om.warcraftlogs.Fight;
import org.superhelt.performance.om.warcraftlogs.Report;
import org.superhelt.performance.om.warcraftlogs.ReportPlayer;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class ReportProvider implements ValueProvider<Report> {

    private final PlayerProvider playerProvider;
    private final FightProvider fightProvider;
    private final RankingProvider dpsRankingProvider;
    private final RankingProvider hpsRankingProvider;

    public ReportProvider(PlayerProvider playerProvider, FightProvider fightProvider, RankingProvider dpsRankingProvider, RankingProvider hpsRankingProvider) {
        this.playerProvider = playerProvider;
        this.fightProvider = fightProvider;
        this.dpsRankingProvider = dpsRankingProvider;
        this.hpsRankingProvider = hpsRankingProvider;
    }

    @Override
    public String getQueryFragment() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\tstartTime,\n");
        sb.append("\t\t\tendTime,\n");
        sb.append(playerProvider.getQueryFragment());
        sb.append(fightProvider.getQueryFragment());
        sb.append(dpsRankingProvider.getQueryFragment());
        sb.append(hpsRankingProvider.getQueryFragment());

        return sb.toString();
    }

    @Override
    public Report getValues(JsonObject report) {
        String code = report.get("code").getAsString();
        long startTimeLong = report.get("startTime").getAsLong();
        long endTimeLong = report.get("endTime").getAsLong();
        LocalDateTime startTime = LocalDateTime.ofEpochSecond(startTimeLong/1000, (int)(startTimeLong%1000)*1000000, ZoneOffset.ofHours(1));
        LocalDateTime endTime = LocalDateTime.ofEpochSecond(endTimeLong/1000, (int)(endTimeLong%1000)*1000000, ZoneOffset.ofHours(1));

        List<Fight> fights = fightProvider.getValues(report);
        List<ReportPlayer> players = playerProvider.getValues(report);
        List<ReportRanking> dpsRankings = dpsRankingProvider.getValues(report);
        List<ReportRanking> hpsRankings = hpsRankingProvider.getValues(report);

        return new Report(code, fights, players, dpsRankings, hpsRankings, startTime, endTime);
    }
}
