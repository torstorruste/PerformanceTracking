package org.superhelt.performance.eventprovider;

import java.util.Arrays;
import java.util.List;

public class EventProviders {

    public static List<EventProvider> heals() {
        return Arrays.asList(
                new HealingProvider("Healthstone", 6262),
                new HealingProvider("Healthpot", 307192)
        );
    }

    public static List<EventProvider> defensives() {
        return Arrays.asList(
                // Death Knights
                new BuffProvider("AntiMagicShell", 48707),
                new BuffProvider("IceboundFortitude", 48792),

                // Demon Hunter

                // Druids
                new BuffProvider("Barkskin", 22812),
                new BuffProvider("SurvivalInstincts", 61336),

                // Hunter
                new BuffProvider("SurvivalOfTheFittest", 281195),
                new BuffProvider("Exhiliration", 109304),

                // Mage
                new BuffProvider("IceBarrier", 11426),

                // Monk
                new BuffProvider("TouchOfKarma", 122470),
                new BuffProvider("FortifyingBrew", 243435),
                new BuffProvider("DiffuseMagic", 122783),

                // Paladin
                new BuffProvider("DivineProtection", 498),

                // Priest
                new BuffProvider("Fade", 586),
                new BuffProvider("DesperatePrayer", 19236),

                // Rogue

                // Shaman
                new BuffProvider("AstralShift", 108271),

                // Warlock
                new BuffProvider("UnendingResolve", 104773),

                // Warriors
                new BuffProvider("DieByTheSword", 118038),
                new BuffProvider("EnragedRegeneration", 184364),
                new BuffProvider("SpellReflection", 23920)
        );
    }
}
