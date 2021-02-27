package org.superhelt.performance.om;

public class Player {

    private final int id;
    private final String name;
    private final String playerClass;

    public Player(int id, String name, String playerClass) {
        this.id = id;
        this.name = name;
        this.playerClass = playerClass;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlayerClass() {
        return playerClass;
    }
}
