package org.superhelt.performance.om;

public enum EventType {
    APPLY_BUFF,
    REFRESH_BUFF,
    REMOVE_BUFF,
    HEAL,
    DAMAGE,
    DEATH;

    public static EventType fromJson(String json) {
        switch(json) {
            case "applybuff": return APPLY_BUFF;
            case "removebuff": return REMOVE_BUFF;
            case "refreshbuff": return REFRESH_BUFF;
            case "heal": return HEAL;
            case "death": return DEATH;
            case "damage": return DAMAGE;
            default: throw new IllegalArgumentException("Unknown event type "+json);
        }
    }
}
