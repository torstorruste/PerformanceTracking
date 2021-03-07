package org.superhelt.performance.om;

public class Boss {

    private final int id;
    private final String name;

    public Boss(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
