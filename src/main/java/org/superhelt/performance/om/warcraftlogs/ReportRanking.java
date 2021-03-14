package org.superhelt.performance.om.warcraftlogs;

import org.superhelt.performance.om.RankingType;

public class ReportRanking {

    private final int fightId;
    private final String playerName;
    private final RankingType rankingType;
    private final int rankPercent;

    public ReportRanking(int fightId, String playerName, RankingType rankingType, int rankPercent) {
        this.fightId = fightId;
        this.playerName = playerName;
        this.rankingType = rankingType;
        this.rankPercent = rankPercent;
    }

    public int getFightId() {
        return fightId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public RankingType getRankingType() {
        return rankingType;
    }

    public int getRankPercent() {
        return rankPercent;
    }
}
