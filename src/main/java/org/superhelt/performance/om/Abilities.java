package org.superhelt.performance.om;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.superhelt.performance.om.Bosses.*;

public class Abilities {

    // Consumables
    public static final Ability HEALTHSTONE = new Ability(6262, "Healthstone");
    public static final Ability HEALING_POTION = new Ability(307192, "Healingpotion");
    public static final Ability SPECTRAL_INTELLECT = new Ability(307162, "Potion of Spectral Intellect");
    public static final Ability PHANTOM_FIRE = new Ability(307495, "Potion of Phantom Fire");
    public static final Ability SPECTRAL_STRENGTH = new Ability(307164, "Potion of Spectral Strength");
    public static final Ability SPECTRAL_AGILITY = new Ability(307159, "Potion of Spectral Agility");
    public static final Ability SACRIFICIAL_ANIMA = new Ability(322302, "Potion of Sacrificial Anima");
    public static final Ability SPIRITUAL_CLARITY = new Ability(307161, "Potion of Spiritual Clarity");

    // Defensives
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
    public static final Ability EXHILARATION = new ClassAbility(109304, "Exhilaration", PlayerClass.HUNTER);
    public static final Ability ASPECT_OF_THE_TURTLE = new ClassAbility(186265, "Aspect of the Turtle", PlayerClass.HUNTER);
    public static final Ability RESILIENCE_OF_THE_HUNTER = new ClassAbility(339461, "Resilience of the Hunter", PlayerClass.HUNTER);

    // Mage
    public static final Ability ICE_BARRIER = new ClassAbility(11426, "Ice Barrier", PlayerClass.MAGE);
    public static final Ability BLAZING_BARRIER = new ClassAbility(235313, "Blazing Barrier", PlayerClass.MAGE);
    public static final Ability ALTER_TIME = new ClassAbility(110909, "Alter Time", PlayerClass.MAGE);
    public static final Ability ICE_BLOCK = new ClassAbility(45438, "Ice Block", PlayerClass.MAGE);
    public static final Ability MIRROR_IMAGE = new ClassAbility(55342, "Mirror Image", PlayerClass.MAGE);

    // Monk
    public static final Ability TOUCH_OF_KARMA = new ClassAbility(122470, "Touch Of Karma", PlayerClass.MONK);
    public static final Ability FORTIFYING_BREW = new ClassAbility(243435, "Fortifying Brew", PlayerClass.MONK);
    public static final Ability DIFFUSE_MAGIC = new ClassAbility(122783, "Diffuse Magic", PlayerClass.MONK);
    public static final Ability DAMPEN_HARM = new ClassAbility(122278, "Dampen Harm", PlayerClass.MONK);

    // Paladin
    public static final Ability DIVINE_PROTECTION = new ClassAbility(498, "Divine Protection", PlayerClass.PALADIN);
    public static final Ability DIVINE_SHIELD = new ClassAbility(642, "Divine Shield", PlayerClass.PALADIN);

    // Priest
    public static final Ability FADE = new ClassAbility(586, "Fade", PlayerClass.PRIEST);
    public static final Ability DESPERATE_PRAYER = new ClassAbility(19236, "Desperate Prayer", PlayerClass.PRIEST);
    public static final Ability DISPERSION = new ClassAbility(47585, "Dispersion", PlayerClass.PRIEST);

    // Rogue

    // Shaman
    public static final Ability ASTRAL_SHIFT = new ClassAbility(108271, "Astral Shift", PlayerClass.SHAMAN);
    public static final Ability HARDEN_SKIN = new ClassAbility(118337, "Harden Skin", PlayerClass.SHAMAN);

    // Warlock
    public static final Ability UNENDING_RESOLVE = new ClassAbility(104773, "Unending Resolve", PlayerClass.WARLOCK);
    public static final Ability MORTAL_COIL = new ClassAbility(6789, "Mortal Coil", PlayerClass.WARLOCK);
    public static final Ability DARK_PACT = new ClassAbility(108416, "Dark Pact", PlayerClass.WARLOCK);

    // Warriors
    public static final Ability DIE_BY_THE_SWORD = new ClassAbility(118038, "Die By The Sword", PlayerClass.WARRIOR);
    public static final Ability ENRAGED_REGENERATION = new ClassAbility(184364, "Enraged Regeneration", PlayerClass.WARRIOR);
    public static final Ability SPELL_REFLECTION = new ClassAbility(23920, "Spell Reflection", PlayerClass.WARRIOR);

    // Shriekwing
    public static final Ability MURDER_PREY = new BossAbility(345425, "Murder Prey", SHRIEKWING);
    public static final Ability ECHOING_SONAR = new BossAbility(343022, "Echoing Sonar", SHRIEKWING);
    public static final Ability EARSPLITTING_SHRIEK = new BossAbility(336005, "Earsplitting Shriek", SHRIEKWING);
    public static final Ability BLIND_SWIPE = new BossAbility(343005, "Blind Swipe", SHRIEKWING);
    public static final Ability DESCENT = new BossAbility(342923, "Descent", SHRIEKWING);

    // Huntsman
    public static final Ability SPREADSHOT = new BossAbility(334404, "Spreadshot", HUNTSMAN);

    // Hungering Destroyer
    public static final Ability VOLATILE_EJECTION = new BossAbility(334228, "Volatile Ejection", HUNGERING);

    // Sun King
    public static final Ability SMOLDERING_REMNANT = new BossAbility(328579, "Smoldering Remnant", SUN_KING);
    public static final Ability FIERY_STRIKE = new BossAbility(326455, "Fiery Strike", SUN_KING);
    public static final Ability BLAZING_SURGE = new BossAbility(329518, "Blazing Surge", SUN_KING);
    public static final Ability SMOLDERING_PLUMAGE = new BossAbility(241254, "Smoldering Plumage", SUN_KING);

    // Artificer
    public static final Ability STASIS_TRAP = new BossAbility(326302, "Statis Trap", ARTIFICER);
    public static final Ability RIFT_BLAST = new BossAbility(329256, "Rift Blast", ARTIFICER);
    public static final Ability ANNIHILATE = new BossAbility(328789, "Annihilate", ARTIFICER);
    public static final Ability POSSESSION = new BossAbility(327414, "Possession", ARTIFICER);

    // Darkvein
    public static final Ability BOTTLED_ANIMA = new BossAbility(325769, "Bottled Anima", DARKVEIN);
    public static final Ability LINGERING_ANIMA = new BossAbility(325713, "Lingering Anima", DARKVEIN);

    // Council of Blood
    public static final Ability DARK_RECITAL = new BossAbility(334743, "Dark Recital", COUNCIL);
    public static final Ability EVASIVE_LUNGE = new BossAbility(327610, "Evasive Lunge", COUNCIL);
    // TODO: Death to Dancing fever (not jumping)
    // TODO: Damage to Dutiful Attendant

    // Sludgefist
    public static final Ability SHATTERING_CHAIN = new BossAbility(335295, "Shattering Chain", SLUDGEFIST);
    public static final Ability FALLING_RUBBLE = new BossAbility(332572, "Falling Rubble", SLUDGEFIST);
    public static final Ability FRACTURED_DEBRIS = new BossAbility(341307, "Fractured Debris", SLUDGEFIST);
    public static final Ability HEEDLESS_CHARGE = new BossAbility(339067, "Heedless Charge", SLUDGEFIST);
    public static final Ability DESTRUCTIVE_STOMP = new BossAbility(332318, "Destructive Stomp", SLUDGEFIST);
    public static final Ability STONEQUAKE = new BossAbility(335361, "Stonequake", SLUDGEFIST);
    public static final Ability SEISMIC_SHIFT = new BossAbility(341087, "Seismic Shift", SLUDGEFIST);

    // Stonelegion Generals
    public static final Ability CLUSTER_BOMBARDMENT = new BossAbility(336231, "Cluster Bombardment", GENERALS);
    public static final Ability SEISMIC_UPHEAVAL = new BossAbility(334500, "Seismic Upheaval", GENERALS);

    // Sire Denathrius
    public static final Ability CRESCENDO = new BossAbility(336162, "Crescendo", SIRE);
    public static final Ability MASSACRE = new BossAbility(330137, "Massacre", SIRE);
    public static final Ability SEARING_CENSURE = new BossAbility(341426, "Searing Censure", SIRE);
    public static final Ability WRACKING_PAIN = new BossAbility(329181, "Wracking Pain", SIRE);
    public static final Ability RANCOR = new BossAbility(335873, "Rancor", SIRE);
    public static final Ability RAVAGE = new BossAbility(327123, "Ravage", SIRE);
    public static final Ability NEUTRALIZE = new BossAbility(339113, "Neutralize", SIRE);
    // TODO: Impale
    // TODO: Hand of Destruction damage

    public static final Ability NIGHT_HUNTER_DEBUFF = new BossAbility(327796, "Night Hunter Debuff", SIRE);
    public static final Ability NIGHT_HUNTER_DAMAGE = new BossAbility(327810, "Night Hunter Damage", SIRE);
    public static final Ability INSATIABLE_HUNGER = new BossAbility(327823, "Insatiable Hunger", SIRE);
    public static final Ability FATAL_FINESSE = new BossAbility(332797, "Fatal Finesse", SIRE);

    public static List<Ability> getHeals() {
        return Arrays.asList(HEALTHSTONE, HEALING_POTION, SPECTRAL_INTELLECT, SPECTRAL_AGILITY,
                SPECTRAL_STRENGTH, PHANTOM_FIRE, SACRIFICIAL_ANIMA, SPIRITUAL_CLARITY,
                EXHILARATION, MORTAL_COIL);
    }

    public static List<Ability> getDefensives() {
        return Arrays.asList(ANTI_MAGIC_SHELL, ICEBOUND_FORTITUDE, LICHBORNE, FLESHCRAFT,
                BARKSKIN, SURVIVAL_INSTINCTS,
                SURVIVAL_OF_THE_FITTEST, ASPECT_OF_THE_TURTLE, RESILIENCE_OF_THE_HUNTER,
                ICE_BARRIER, BLAZING_BARRIER, ICE_BLOCK, ALTER_TIME, MIRROR_IMAGE,
                TOUCH_OF_KARMA, FORTIFYING_BREW, DIFFUSE_MAGIC, DAMPEN_HARM,
                DIVINE_PROTECTION, DIVINE_SHIELD,
                FADE, DESPERATE_PRAYER, DISPERSION,
                ASTRAL_SHIFT, HARDEN_SKIN,
                UNENDING_RESOLVE, DARK_PACT,
                DIE_BY_THE_SWORD, ENRAGED_REGENERATION, SPELL_REFLECTION);
    }

    public static List<Ability> getDamageMechanics() {
        return Arrays.asList(
                MURDER_PREY, ECHOING_SONAR, EARSPLITTING_SHRIEK, BLIND_SWIPE, DESCENT,
                SPREADSHOT,
                SMOLDERING_REMNANT,FIERY_STRIKE, BLAZING_SURGE, SMOLDERING_PLUMAGE,
                STASIS_TRAP, RIFT_BLAST, ANNIHILATE,
                BOTTLED_ANIMA, LINGERING_ANIMA,
                DARK_RECITAL, EVASIVE_LUNGE,
                SHATTERING_CHAIN, FALLING_RUBBLE, FRACTURED_DEBRIS, HEEDLESS_CHARGE, DESTRUCTIVE_STOMP, STONEQUAKE,
                CLUSTER_BOMBARDMENT, SEISMIC_UPHEAVAL,
                CRESCENDO, MASSACRE, SEARING_CENSURE, WRACKING_PAIN, RANCOR, RAVAGE, NEUTRALIZE
        );
    }

    public static List<Ability> getDebuffMechanics() {
        return Arrays.asList(
                POSSESSION
        );
    }

    public static List<Ability> getDebuffs() {
        return Arrays.asList(
                NIGHT_HUNTER_DEBUFF
        );
    }

    public static List<Ability> getDamageTaken() {
        return Arrays.asList(
                NIGHT_HUNTER_DAMAGE, INSATIABLE_HUNGER, FATAL_FINESSE, VOLATILE_EJECTION, SEISMIC_SHIFT
        );
    }

    public static Map<Integer, Ability> getAbilityMap() {
        Map<Integer, Ability> result = new HashMap<>();

        addAbilities(result, getHeals());
        addAbilities(result, getDefensives());
        addAbilities(result, getDamageMechanics());
        addAbilities(result, getDebuffMechanics());
        addAbilities(result, getDamageTaken());
        addAbilities(result, getDebuffs());

        return result;
    }

    private static void addAbilities(Map<Integer, Ability> result, List<Ability> abilities) {
        abilities.forEach(a->result.put(a.getId(), a));
    }
}
