package org.superhelt.performance.om;

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

    @Override
    public String toString() {
        return name;
    }
}
