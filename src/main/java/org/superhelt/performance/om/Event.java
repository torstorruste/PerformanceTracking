package org.superhelt.performance.om;

import java.time.LocalDateTime;

public class Event {

    private final LocalDateTime timestamp;
    private final Player source;
    private final Ability ability;
    private final EventType eventType;

    public Event(LocalDateTime timestamp, Player source, Ability ability, EventType eventType) {
        this.timestamp = timestamp;
        this.source = source;
        this.ability = ability;
        this.eventType = eventType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Player getSource() {
        return source;
    }

    public Ability getAbility() {
        return ability;
    }

    public EventType getEventType() {
        return eventType;
    }
}
