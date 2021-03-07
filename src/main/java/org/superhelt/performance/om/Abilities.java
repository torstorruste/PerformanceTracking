package org.superhelt.performance.om;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Abilities {

    // Heals
    public static final Ability HEALTHSTONE = new Ability(6262, "Healthstone");
    public static final Ability HEALING_POTION = new Ability(307192, "Healingpotion");

    // Death Knights
    public static final Ability ANTI_MAGIC_SHELL = new ClassAbility(48707, "Anti Magic Shell", PlayerClass.DEATHKNIGHT);
    public static final Ability ICEBOUND_FORTITUDE = new ClassAbility(48792, "Icebound Fortitude", PlayerClass.DEATHKNIGHT);

    // Demon Hunter

    // Druids
    public static final Ability BARKSKIN = new ClassAbility(22812, "Barkskin", PlayerClass.DRUID);
    public static final Ability SURVIVAL_INSTINCTS = new ClassAbility(61336, "Survival Instincts", PlayerClass.DRUID);

    // Hunter
    public static final Ability SURVIVAL_OF_THE_FITTEST = new ClassAbility(281195, "Survival Of The Fittest", PlayerClass.HUNTER);
    public static final Ability EXHILIRATION = new ClassAbility(109304, "Exhiliration", PlayerClass.HUNTER);

    // Mage
    public static final Ability ICE_BARRIER = new ClassAbility(11426, "Ice Barrier", PlayerClass.MAGE);

    // Monk
    public static final Ability TOUCH_OF_KARMA = new ClassAbility(122470, "Touch Of Karma", PlayerClass.MONK);
    public static final Ability FORTIFYING_BREW = new ClassAbility(243435, "Fortifying Brew", PlayerClass.MONK);
    public static final Ability DIFFUSE_MAGIC = new ClassAbility(122783, "Diffuse Magic", PlayerClass.MONK);

    // Paladin
    public static final Ability DIVINE_PROTECTION = new ClassAbility(498, "Divine Protection", PlayerClass.PALADIN);

    // Priest
    public static final Ability FADE = new ClassAbility(586, "Fade", PlayerClass.PRIEST);
    public static final Ability DESPERATE_PRAYER = new ClassAbility(19236, "Desperate Prayer", PlayerClass.PRIEST);

    // Rogue

    // Shaman
    public static final Ability ASTRAL_SHIFT = new ClassAbility(108271, "Astral Shift", PlayerClass.SHAMAN);

    // Warlock
    public static final Ability UNENDING_RESOLVE = new ClassAbility(104773, "Unending Resolve", PlayerClass.WARLOCK);

    // Warriors
    public static final Ability DIE_BY_THE_SWORD = new ClassAbility(118038, "Die By The Sword", PlayerClass.WARRIOR);
    public static final Ability ENRAGED_REGENERATION = new ClassAbility(184364, "Enraged Regeneration", PlayerClass.WARRIOR);
    public static final Ability SPELL_REFLECTION = new ClassAbility(23920, "Spell Reflection", PlayerClass.WARRIOR);

    // Shriekwing (2398)
    public static final Ability MURDER_PREY = new BossAbility(345425, "Murder Prey", 2398);
    public static final Ability ECHOING_SONAR = new BossAbility(343022, "Echoing Sonar", 2398);
    public static final Ability EARSPLITTING_SHRIEK = new BossAbility(336005, "Earsplitting Shriek", 2398);
    public static final Ability BLIND_SWIPE = new BossAbility(343005, "Blind Swipe", 2398);
    public static final Ability DESCENT = new BossAbility(342923, "Descent", 2398);

    // Huntsman (2418)

    // Hungering Destroyer (2383)

    // Sire Denathrius (2407)
    public static final Ability CRESCENDO = new BossAbility(336162, "Crescendo", 2407);


    public static List<Ability> getHeals() {
        return Arrays.asList(HEALTHSTONE, HEALING_POTION);
    }

    public static List<Ability> getDefensives() {
        return Arrays.asList(ANTI_MAGIC_SHELL, ICEBOUND_FORTITUDE,
                BARKSKIN, SURVIVAL_INSTINCTS,
                SURVIVAL_OF_THE_FITTEST, EXHILIRATION,
                ICE_BARRIER,
                TOUCH_OF_KARMA, FORTIFYING_BREW, DIFFUSE_MAGIC,
                DIVINE_PROTECTION,
                FADE, DESPERATE_PRAYER,
                ASTRAL_SHIFT,
                UNENDING_RESOLVE,
                DIE_BY_THE_SWORD, ENRAGED_REGENERATION, SPELL_REFLECTION);
    }

    public static List<Ability> getMechanics() {
        return Arrays.asList(
                MURDER_PREY, ECHOING_SONAR, EARSPLITTING_SHRIEK, BLIND_SWIPE, DESCENT,
                CRESCENDO
        );
    }

    public static Map<Integer, Ability> getAbilityMap() {
        Map<Integer, Ability> result = new HashMap<>();

        addAbilities(result, getHeals());
        addAbilities(result, getDefensives());
        addAbilities(result, getMechanics());

        return result;
    }

    private static void addAbilities(Map<Integer, Ability> result, List<Ability> abilities) {
        abilities.forEach(a->result.put(a.getId(), a));
    }
}
