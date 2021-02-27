package org.superhelt.performance.eventprovider;

import java.util.Arrays;
import java.util.List;

public class EventProviders {

    public static List<EventProvider> defensives() {
        return Arrays.asList(
                // Death Knights
                new BuffProvider("AntiMagicShell", 48707),

                // Demon Hunter

                // Druids
                new BuffProvider("Barkskin", 22812),
                new BuffProvider("SurvivalInstincts", 61336),

                // Hunter

                // Mage
                new BuffProvider("IceBarrier", 11426),

                // Monk

                // Paladin

                // Priest

                // Rogue

                // Shaman

                // Warlock

                // Warriors
                new BuffProvider("DieByTheSword", 118038),
                new BuffProvider("EnragedRegeneration", 184364),
                new BuffProvider("SpellReflection", 23920)
        );
    }
}
