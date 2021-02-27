package org.superhelt.performance.valueprovider;

public class FightValueProvider implements ValueProvider {
    public String getQueryFragment() {
        StringBuilder sb = new StringBuilder();
        sb.append("      fights(difficulty: 5) {\n");
        sb.append("        encounterID,\n");
        sb.append("        fightPercentage,\n");
        sb.append("        startTime,\n");
        sb.append("        endTime,\n");
        sb.append("        name,\n");
        sb.append("        id,\n");
        sb.append("        kill\n");
        sb.append("      }\n");
        return sb.toString();
    }
}
