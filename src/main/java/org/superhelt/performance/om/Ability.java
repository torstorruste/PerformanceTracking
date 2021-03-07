package org.superhelt.performance.om;

import java.util.Objects;

public class Ability {

    private final int id;
    private final String name;

    public Ability(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWarcraftlogsName() {
        return name.replace(" ", "");
    }

    public boolean isRelevant(Boss boss, Player player) {
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ability ability = (Ability) o;
        return id == ability.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
