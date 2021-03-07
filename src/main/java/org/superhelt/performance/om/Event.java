package org.superhelt.performance.om;

import java.time.LocalDateTime;

public class Event {

    private final LocalDateTime timestamp;
    private final Player source;
    private final Player target;
    private final Ability ability;
    private final EventType eventType;
    private final int amount;
    private final int unmitigatedAmount;

    public Event(LocalDateTime timestamp, Player source, Player target, Ability ability, EventType eventType, int amount, int unmitigatedAmount) {
        this.timestamp = timestamp;
        this.source = source;
        this.target = target;
        this.ability = ability;
        this.eventType = eventType;
        this.amount = amount;
        this.unmitigatedAmount = unmitigatedAmount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Player getSource() {
        return source;
    }

    public Player getTarget() {
        return target;
    }

    public Ability getAbility() {
        return ability;
    }

    public EventType getEventType() {
        return eventType;
    }

    public int getAmount() {
        return amount;
    }

    public int getUnmitigatedAmount() {
        return unmitigatedAmount;
    }
}
