package org.superhelt.performance.om;

import java.util.Arrays;
import java.util.List;

public class Abilities {

    // Heals
    public static final Ability HEALTHSTONE = new Ability(6262, "Healthstone");
    public static final Ability HEALING_POTION = new Ability(307192, "Healingpotion");

    // Death Knights
    public static final Ability ANTI_MAGIC_SHELL = new Ability(48707, "Anti Magic Shell");
    public static final Ability ICEBOUND_FORTITUDE = new Ability(48792, "Icebound Fortitude");

    // Demon Hunter

    // Druids
    public static final Ability BARKSKIN = new Ability(22812, "Barkskin");
    public static final Ability SURVIVAL_INSTINCTS = new Ability(61336, "Survival Instincts");

    // Hunter
    public static final Ability SURVIVAL_OF_THE_FITTEST = new Ability(281195, "Survival Of The Fittest");
    public static final Ability EXHILIRATION = new Ability(109304, "Exhiliration");

    // Mage
    public static final Ability ICE_BARRIER = new Ability(11426, "Ice Barrier");

    // Monk
    public static final Ability TOUCH_OF_KARMA = new Ability(122470, "Touch Of Karma");
    public static final Ability FORTIFYING_BREW = new Ability(243435, "Fortifying Brew");
    public static final Ability DIFFUSE_MAGIC = new Ability(122783, "Diffuse Magic");

    // Paladin
    public static final Ability DIVINE_PROTECTION = new Ability(498, "Divine Protection");

    // Priest
    public static final Ability FADE = new Ability(586, "Fade");
    public static final Ability DESPERATE_PRAYER = new Ability(19236, "Desperate Prayer");

    // Rogue

    // Shaman
    public static final Ability ASTRAL_SHIFT = new Ability(108271, "Astral Shift");

    // Warlock
    public static final Ability UNENDING_RESOLVE = new Ability(104773, "Unending Resolve");

    // Warriors
    public static final Ability DIE_BY_THE_SWORD = new Ability(118038, "Die By The Sword");
    public static final Ability ENRAGED_REGENERATION = new Ability(184364, "Enraged Regeneration");
    public static final Ability SPELL_REFLECTION = new Ability(23920, "Spell Reflection");

    // Shriekwing
    public static final Ability MURDER_PREY = new Ability(345425, "Murder Prey");
    public static final Ability ECHOING_SONAR = new Ability(343022, "Echoing Sonar");
    public static final Ability EARSPLITTING_SHRIEK = new Ability(336005, "Earsplitting Shriek");
    public static final Ability BLIND_SWIPE = new Ability(343005, "Blind Swipe");
    public static final Ability DESCENT = new Ability(342923, "Descent");

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
                MURDER_PREY, ECHOING_SONAR, EARSPLITTING_SHRIEK, BLIND_SWIPE, DESCENT
        );
    }
}
