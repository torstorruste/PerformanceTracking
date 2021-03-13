package org.superhelt.performance.om;

public class ClassAbility extends Ability {

    private final PlayerClass playerClass;

    public ClassAbility(int id, String name, PlayerClass playerClass) {
        super(id, name);
        this.playerClass = playerClass;
    }

    @Override
    public boolean isRelevant(Boss boss, Player player) {
        return player.getPlayerClass()==playerClass;
    }

    public PlayerClass getPlayerClass() {
        return playerClass;
    }
}
