package org.superhelt.performance.eventprovider;

import org.superhelt.performance.om.Abilities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EventProviders {

    public static List<EventProvider> deaths() {
        return Collections.singletonList(new DeathsProvider());
    }

    public static List<EventProvider> heals() {
        return Abilities.getHeals().stream().map(HealingProvider::new).collect(Collectors.toList());
    }

    public static List<EventProvider> defensives() {
        return Abilities.getDefensives().stream().map(BuffProvider::new).collect(Collectors.toList());
    }

    public static List<EventProvider> mechanics() {
        return Abilities.getMechanics().stream().map(DamageTakenProvider::new).collect(Collectors.toList());
    }
}
