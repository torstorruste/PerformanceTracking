package org.superhelt.performance.om;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Abilities {

    // Heals
    public static final Ability HEALTHSTONE = new Ability(6262, "Healthstone");
    public static final Ability HEALING_POTION = new Ability(307192, "Healingpotion");
    public static final Ability FLESHCRAFT = new Ability(324867, "Fleshcraft");

    // Death Knights
    public static final Ability ANTI_MAGIC_SHELL = new ClassAbility(48707, "Anti Magic Shell", PlayerClass.DEATHKNIGHT);
    public static final Ability ICEBOUND_FORTITUDE = new ClassAbility(48792, "Icebound Fortitude", PlayerClass.DEATHKNIGHT);
    public static final Ability LICHBORNE = new ClassAbility(49039, "Lichborne", PlayerClass.DEATHKNIGHT);

    // Demon Hunter

    // Druids
    public static final Ability BARKSKIN = new ClassAbility(22812, "Barkskin", PlayerClass.DRUID);
    public static final Ability SURVIVAL_INSTINCTS = new ClassAbility(61336, "Survival Instincts", PlayerClass.DRUID);

    // Hunter
    public static final Ability SURVIVAL_OF_THE_FITTEST = new ClassAbility(281195, "Survival Of The Fittest", PlayerClass.HUNTER);
    public static final Ability EXHILIRATION = new ClassAbility(109304, "Exhiliration", PlayerClass.HUNTER);
    public static final Ability ASPECT_OF_THE_TURTLE = new ClassAbility(186265, "Aspect of the Turtle", PlayerClass.HUNTER);

    // Mage
    public static final Ability ICE_BARRIER = new ClassAbility(11426, "Ice Barrier", PlayerClass.MAGE);
    public static final Ability BLAZING_BARRIER = new ClassAbility(235313, "Blazing Barrier", PlayerClass.MAGE);
    public static final Ability ALTER_TIME = new ClassAbility(110909, "Alter Time", PlayerClass.MAGE);
    public static final Ability ICE_BLOCK = new ClassAbility(45438, "Ice Block", PlayerClass.MAGE);

    // Monk
    public static final Ability TOUCH_OF_KARMA = new ClassAbility(122470, "Touch Of Karma", PlayerClass.MONK);
    public static final Ability FORTIFYING_BREW = new ClassAbility(243435, "Fortifying Brew", PlayerClass.MONK);
    public static final Ability DIFFUSE_MAGIC = new ClassAbility(122783, "Diffuse Magic", PlayerClass.MONK);

    // Paladin
    public static final Ability DIVINE_PROTECTION = new ClassAbility(498, "Divine Protection", PlayerClass.PALADIN);
    public static final Ability DIVINE_SHIELD = new ClassAbility(642, "Divine Shield", PlayerClass.PALADIN);

    // Priest
    public static final Ability FADE = new ClassAbility(586, "Fade", PlayerClass.PRIEST);
    public static final Ability DESPERATE_PRAYER = new ClassAbility(19236, "Desperate Prayer", PlayerClass.PRIEST);

    // Rogue

    // Shaman
    public static final Ability ASTRAL_SHIFT = new ClassAbility(108271, "Astral Shift", PlayerClass.SHAMAN);
    public static final Ability HARDEN_SKIN = new ClassAbility(118337, "Harden Skin", PlayerClass.SHAMAN);

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
    public static final Ability SPREADSHOT = new BossAbility(334404, "Spreadshot", 2418);

    // Hungering Destroyer (2383)

    // Sun King (2402)
    public static final Ability SMOLDERING_REMNANT = new BossAbility(328579, "Smoldering Remnant", 2402);
    public static final Ability FIERY_STRIKE = new BossAbility(326455, "Fiery Strike", 2402);
    public static final Ability BLAZING_SURGE = new BossAbility(329518, "Blazing Surge", 2402);
    public static final Ability SMOLDERING_PLUMAGE = new BossAbility(241254, "Smoldering Plumage", 2402);

    // Artificer (2405)
    public static final Ability STASIS_TRAP = new BossAbility(326302, "Statis Trap", 2405);
    public static final Ability RIFT_BLAST = new BossAbility(329256, "Rift Blast", 2405);
    public static final Ability ANNIHILATE = new BossAbility(328789, "Annihilate", 2405);
    // TODO: Possesion

    // Darkvein (2406)
    public static final Ability BOTTLED_ANIMA = new BossAbility(325769, "Bottled Anima", 2406);
    public static final Ability LINGERING_ANIMA = new BossAbility(325713, "Lingering Anima", 2406);

    // Council of Blood (2412)
    public static final Ability DARK_RECITAL = new BossAbility(334743, "Dark Recital", 2406);
    public static final Ability EVASIVE_LUNGE = new BossAbility(327610, "Evasive Lunge", 2406);
    // TODO: Death to Dancing fever (not jumping)
    // TODO: Damage to Dutiful Attendant

    // Sludgefist (2399)
    public static final Ability SHATTERING_CHAIN = new BossAbility(335295, "Shattering Chain", 2399);
    public static final Ability FALLING_RUBBLE = new BossAbility(332572, "Falling Rubble", 2399);
    public static final Ability FRACTURED_DEBRIS = new BossAbility(341307, "Fractured Debris", 2399);
    public static final Ability HEEDLESS_CHARGE = new BossAbility(339067, "Heedless Charge", 2399);
    public static final Ability DESTRUCTIVE_STOMP = new BossAbility(332318, "Destructive Stomp", 2399);
    public static final Ability STONEQUAKE = new BossAbility(335361, "Stonequake", 2399);
    // TODO: Seismic Shift splashes

    // Stonelegion Generals (2417)
    public static final Ability CLUSTER_BOMBARDMENT = new BossAbility(336231, "Cluster Bombardment", 2417);
    public static final Ability SEISMIC_UPHEAVAL = new BossAbility(334500, "Seismic Upheaval", 2417);

    // Sire Denathrius (2407)
    public static final Ability CRESCENDO = new BossAbility(336162, "Crescendo", 2407);
    public static final Ability MASSACRE = new BossAbility(330137, "Massacre", 2407);
    public static final Ability SEARING_CENSURE = new BossAbility(341426, "Searing Censure", 2407);
    public static final Ability WRACKING_PAIN = new BossAbility(329181, "Wracking Pain", 2407);
    public static final Ability RANCOR = new BossAbility(335873, "Rancor", 2407);
    public static final Ability RAVAGE = new BossAbility(327123, "Ravage", 2407);
    // TODO: Insatiable hunger and night hunter
    // TODO: Impale
    // TODO: Hand of Destruction damage
    // TODO: Orbs

    public static List<Ability> getHeals() {
        return Arrays.asList(HEALTHSTONE, HEALING_POTION, FLESHCRAFT);
    }

    public static List<Ability> getDefensives() {
        return Arrays.asList(ANTI_MAGIC_SHELL, ICEBOUND_FORTITUDE, LICHBORNE,
                BARKSKIN, SURVIVAL_INSTINCTS,
                SURVIVAL_OF_THE_FITTEST, EXHILIRATION, ASPECT_OF_THE_TURTLE,
                ICE_BARRIER, BLAZING_BARRIER, ICE_BLOCK, ALTER_TIME,
                TOUCH_OF_KARMA, FORTIFYING_BREW, DIFFUSE_MAGIC,
                DIVINE_PROTECTION, DIVINE_SHIELD,
                FADE, DESPERATE_PRAYER,
                ASTRAL_SHIFT, HARDEN_SKIN,
                UNENDING_RESOLVE,
                DIE_BY_THE_SWORD, ENRAGED_REGENERATION, SPELL_REFLECTION);
    }

    public static List<Ability> getMechanics() {
        return Arrays.asList(
                MURDER_PREY, ECHOING_SONAR, EARSPLITTING_SHRIEK, BLIND_SWIPE, DESCENT,
                SPREADSHOT,
                SMOLDERING_REMNANT,FIERY_STRIKE, BLAZING_SURGE, SMOLDERING_PLUMAGE,
                STASIS_TRAP, RIFT_BLAST, ANNIHILATE,
                BOTTLED_ANIMA, LINGERING_ANIMA,
                DARK_RECITAL, EVASIVE_LUNGE,
                SHATTERING_CHAIN, FALLING_RUBBLE, FRACTURED_DEBRIS, HEEDLESS_CHARGE, DESTRUCTIVE_STOMP, STONEQUAKE,
                CLUSTER_BOMBARDMENT, SEISMIC_UPHEAVAL,
                CRESCENDO, MASSACRE, SEARING_CENSURE, WRACKING_PAIN, RANCOR, RAVAGE
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
