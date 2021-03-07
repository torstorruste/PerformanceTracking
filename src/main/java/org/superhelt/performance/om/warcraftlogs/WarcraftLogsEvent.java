package org.superhelt.performance.om.warcraftlogs;

import org.superhelt.performance.om.EventType;

import java.time.LocalDateTime;

public class WarcraftLogsEvent {
    private final int fightId;
    private final LocalDateTime timestamp;
    private final int sourceId;
    private final int targetId;
    private final int abilityId;
    private final EventType type;
    private final int amount;
    private final int mitigated;
    private final int unmitigatedAmount;
    private final int overkill;

    public WarcraftLogsEvent(int fightId, LocalDateTime timestamp, int sourceId, int targetId, int abilityId, EventType type, int amount, int mitigated, int unmitigatedAmount, int overkill) {
        this.fightId = fightId;
        this.timestamp = timestamp;
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.abilityId = abilityId;
        this.type = type;
        this.amount = amount;
        this.mitigated = mitigated;
        this.unmitigatedAmount = unmitigatedAmount;
        this.overkill = overkill;
    }

    public int getFightId() {
        return fightId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getSourceId() {
        return sourceId;
    }

    public int getTargetId() {
        return targetId;
    }

    public int getAbilityId() {
        return abilityId;
    }

    public EventType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public int getMitigated() {
        return mitigated;
    }

    public int getUnmitigatedAmount() {
        return unmitigatedAmount;
    }

    public int getOverkill() {
        return overkill;
    }
}
