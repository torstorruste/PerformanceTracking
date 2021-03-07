package org.superhelt.performance.reportprovider;

import com.google.gson.JsonObject;
import org.superhelt.performance.om.warcraftlogs.Fight;
import org.superhelt.performance.om.warcraftlogs.Report;
import org.superhelt.performance.om.warcraftlogs.ReportPlayer;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class ReportProvider implements ValueProvider<Report> {

    private final PlayerProvider playerProvider;
    private final FightProvider fightProvider;

    public ReportProvider(PlayerProvider playerProvider, FightProvider fightProvider) {
        this.playerProvider = playerProvider;
        this.fightProvider = fightProvider;
    }

    @Override
    public String getQueryFragment() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\tstartTime,\n");
        sb.append("\t\t\tendTime,\n");
        sb.append(playerProvider.getQueryFragment());
        sb.append(fightProvider.getQueryFragment());

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

        return new Report(code, fights, players, startTime, endTime);
    }
}
