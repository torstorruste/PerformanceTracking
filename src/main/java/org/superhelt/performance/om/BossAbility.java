package org.superhelt.performance.om;

public class BossAbility extends Ability {

    private final int bossId;

    public BossAbility(int id, String name, int bossId) {
        super(id, name);
        this.bossId = bossId;
    }

    @Override
    public boolean isRelevant(Boss boss, Player player) {
        return boss.getId()==bossId;
    }

    public int getBossId() {
        return bossId;
    }
}
