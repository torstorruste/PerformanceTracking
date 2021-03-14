package org.superhelt.performance.om;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.superhelt.performance.resources.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class Ranking {

    private final Boss boss;
    private final Player player;
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    private final LocalDateTime timestamp;
    private final RankingType rankingType;
    private final int rank;

    public Ranking(Boss boss, Player player, LocalDateTime timestamp, RankingType rankingType, int rank) {
        this.boss = boss;
        this.player = player;
        this.timestamp = timestamp;
        this.rankingType = rankingType;
        this.rank = rank;
    }

    public Boss getBoss() {
        return boss;
    }

    public Player getPlayer() {
        return player;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public RankingType getRankingType() {
        return rankingType;
    }

    public int getRank() {
        return rank;
    }
}
