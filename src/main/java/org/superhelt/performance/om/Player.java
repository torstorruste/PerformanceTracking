package org.superhelt.performance.om;

import java.util.Objects;

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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Player player = (Player) o;
        return id == player.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
