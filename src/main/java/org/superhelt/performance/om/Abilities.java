package org.superhelt.performance.om;

import java.util.*;

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
    public static final Ability FEINT = new ClassAbility(1966, "Feint", PlayerClass.ROGUE);
    public static final Ability CLOAK_OF_SHADOWS = new ClassAbility(31224, "Cloak of Shadows", PlayerClass.ROGUE);
    public static final Ability EVASION = new ClassAbility(5277, "Evasion", PlayerClass.ROGUE);

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

    // Tarragrue
    public static final Ability REMNANT_SOULFORGE = new BossAbility(352392, "Remant soak", TARRAGRUE);
    public static final Ability REMNANT_UPPER_REACHES = new BossAbility(352384, "Remant soak", TARRAGRUE);
    public static final Ability REMNANT_MORT_REGAR = new BossAbility(352387, "Remant soak", TARRAGRUE);
    public static final Ability UNSHAKABLE_DREAD = new BossAbility(347286, "Unshakable Dread", TARRAGRUE);
    public static final Ability CHAINS_OF_ETERNITY = new BossAbility(347554, "Chains of Eternity", TARRAGRUE);
    public static final Ability HUNGERING_MIST = new BossAbility(347737, "Hungering Mist", TARRAGRUE);

    // Eye of the Jailor
    public static final Ability DRAGGING_CHAINS = new BossAbility(349979, "Dragging Chains", EYE);
    public static final Ability FRACTURED_SOUL = new BossAbility(353069, "Fractured Soul", EYE);
    public static final Ability JAILERS_MISERY = new BossAbility(350809, "Jailers Misery", EYE);
    public static final Ability ANNIHILATING_GLARE = new BossAbility(350763, "Annihilating Glare", EYE);

    // Nine
    public static final Ability ARADNES_FALLING_STRIKE = new BossAbility(350098, "Arandes Falling Strike", NINE);
    public static final Ability AGATHAS_ETERNAL_BLADE = new BossAbility(350031, "Agathas Eternal Blade", NINE);
    public static final Ability WINGS_OF_RAGE = new BossAbility(350374, "Wings of Rage", NINE);
    public static final Ability WINGS_OF_RAGE2 = new BossAbility(352757, "Wings of Rage", NINE);
    public static final Ability REVERBERATING_REFRAIN = new BossAbility(350462, "Reverberating Refrain", NINE);
    public static final Ability REVERBERATING_REFRAIN2 = new BossAbility(352753, "Reverberating Refrain", NINE);

    // Ner'zhul
    public static final Ability SUFFERING = new BossAbility(349890, "Suffering", NERZHUL);
    public static final Ability SORROWFUL_PROCESSION = new BossAbility(350388, "Sorrowful Procession", NERZHUL);
    public static final Ability SPITE = new BossAbility(354634, "Spite", NERZHUL);
    public static final Ability SPITE2 = new BossAbility(354479, "Spite", NERZHUL);
    public static final Ability SPITE3 = new BossAbility(354534, "Spite", NERZHUL);
    public static final Ability GRASP_OF_MALICE = new BossAbility(350076, "Grasp of Malice", NERZHUL);

    // Soulrender
    public static final Ability RENDERED_SOUL = new BossAbility(351229, "Rendered Soul", SOULRENDER);
    public static final Ability TORMENT = new BossAbility(350217, "Torment", SOULRENDER);

    // Painsmith
    public static final Ability SPIKED = new BossAbility(355526, "Spiked", PAINSMITH);
    public static final Ability SPIKED_BALL = new BossAbility(348363, "Spiked Ball", PAINSMITH);
    public static final Ability SHADOWSTEEL_EMBERS = new BossAbility(355535, "Shadowsteel Embers", PAINSMITH);
    public static final Ability SHADOWSTEEL_CHAINS = new BossAbility(355524, "Shadowsteel Chains", PAINSMITH);

    // Guardian
    public static final Ability DISINTEGRATION = new BossAbility(352833, "Disintegration", GUARDIAN);

    // Fatescribe
    public static final Ability FATE_FRAGMENT = new BossAbility(353162, "Fate Fragment", FATESCRIBE);
    public static final Ability DESPAIR = new BossAbility(357144, "Despair", FATESCRIBE);
    public static final Ability FATED_CONJUNCTION = new BossAbility(350355, "Fated Conjunction", FATESCRIBE);

    // Kel'Thuzad
    public static final Ability SHADOW_FISSURE = new BossAbility(355136, "Shadow Fissure", KELTHUZAD);
    public static final Ability FREEZING_BLAST = new BossAbility(352381, "Freezing Blast", KELTHUZAD);

    // Sylvanas
    public static final Ability DOMINATION_CHAINS = new BossAbility(352318, "Domination Chains", SYLVANAS);
    public static final Ability VEIL_OF_DARKNESS = new BossAbility(350777, "Veil of Darkness", SYLVANAS);
    public static final Ability DESECRATING_SHOT = new BossAbility(356377, "Desecrating Shot", SYLVANAS);
    public static final Ability RIVEN_DEBRIS = new BossAbility(353413, "Riven Debris", SYLVANAS);
    public static final Ability RIVE = new BossAbility(348145, "Rive", SYLVANAS);
    public static final Ability DEATH_KNIVES = new BossAbility(358433, "Death Knives", SYLVANAS);
    public static final Ability HAUNTING_WAVE = new BossAbility(351870, "Haunting Wave", SYLVANAS);


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
                FEINT, CLOAK_OF_SHADOWS, EVASION,
                ASTRAL_SHIFT, HARDEN_SKIN,
                UNENDING_RESOLVE, DARK_PACT,
                DIE_BY_THE_SWORD, ENRAGED_REGENERATION, SPELL_REFLECTION);
    }

    public static List<Ability> getDamageMechanics() {
        return Arrays.asList(
                HUNGERING_MIST,
                JAILERS_MISERY, ANNIHILATING_GLARE,
                ARADNES_FALLING_STRIKE, AGATHAS_ETERNAL_BLADE,
                GRASP_OF_MALICE,
                RENDERED_SOUL, TORMENT,
                SPIKED, SPIKED_BALL, SHADOWSTEEL_EMBERS,
                DISINTEGRATION,
                FATE_FRAGMENT, DESPAIR, FATED_CONJUNCTION,
                SHADOW_FISSURE, FREEZING_BLAST,
                DOMINATION_CHAINS, VEIL_OF_DARKNESS, DESECRATING_SHOT, RIVEN_DEBRIS, RIVE, DEATH_KNIVES, HAUNTING_WAVE
        );
    }

    public static List<Ability> getDebuffMechanics() {
        return Arrays.asList(
                REMNANT_MORT_REGAR, REMNANT_SOULFORGE, REMNANT_UPPER_REACHES, UNSHAKABLE_DREAD, CHAINS_OF_ETERNITY,
                DRAGGING_CHAINS, FRACTURED_SOUL,
                SUFFERING, SORROWFUL_PROCESSION, SPITE, SPITE2, SPITE3
        );
    }

    public static List<Ability> getDebuffs() {
        return Collections.emptyList();
    }

    public static List<Ability> getDamageTaken() {
        return Collections.emptyList();
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
