package org.superhelt.performance.om;

public enum EventType {
    APPLY_BUFF,
    REFRESH_BUFF,
    REMOVE_BUFF,
    REMOVE_BUFF_STACK,
    APPLY_DEBUFF,
    REFRESH_DEBUFF,
    REMOVE_DEBUFF,
    HEAL,
    DAMAGE,
    DEATH,
    CAST;

    public static EventType fromJson(String json) {
        switch(json) {
            case "applybuff": return APPLY_BUFF;
            case "removebuff": return REMOVE_BUFF;
            case "refreshbuff": return REFRESH_BUFF;
            case "applydebuff": return APPLY_DEBUFF;
            case "refreshdebuff": return REFRESH_DEBUFF;
            case "removedebuff": return REMOVE_DEBUFF;
            case "heal": return HEAL;
            case "death": return DEATH;
            case "damage": return DAMAGE;
            case "cast": return CAST;
            case "removebuffstack": return REMOVE_BUFF_STACK;
            default: throw new IllegalArgumentException("Unknown event type "+json);
        }
    }
}
