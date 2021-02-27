package org.superhelt.performance.om;

import java.time.LocalDateTime;

public class Event {
    private final int fightId;
    private final LocalDateTime timestamp;
    private final int sourceId;
    private final int targetId;
    private final int abilityId;
    private final EventType type;

    public Event(int fightId, LocalDateTime timestamp, int sourceId, int targetId, int abilityId, EventType type) {
        this.fightId = fightId;
        this.timestamp = timestamp;
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.abilityId = abilityId;
        this.type = type;
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
}
