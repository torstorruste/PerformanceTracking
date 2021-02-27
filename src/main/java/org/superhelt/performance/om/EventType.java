package org.superhelt.performance.om;

public enum EventType {
    APPLY_BUFF,
    REMOVE_BUFF,
    HEAL,
    DEATH;

    public static EventType fromJson(String json) {
        switch(json) {
            case "applybuff": return APPLY_BUFF;
            case "removebuff": return REMOVE_BUFF;
            case "heal": return HEAL;
            case "death": return DEATH;
            default: throw new IllegalArgumentException("Unknown event type "+json);
        }
    }
}
