package org.superhelt.performance.eventprovider;

import org.superhelt.performance.om.Abilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EventProviders {

    public static List<EventProvider> deaths() {
        return Collections.singletonList(new DeathsProvider());
    }

    public static List<EventProvider> consumables() {
        return Abilities.getHeals().stream().map(CastProvider::new).collect(Collectors.toList());
    }

    public static List<EventProvider> defensives() {
        return Abilities.getDefensives().stream().map(BuffProvider::new).collect(Collectors.toList());
    }

    public static List<EventProvider> damageMechanics() {
        return Abilities.getDamageMechanics().stream().map(DamageTakenProvider::new).collect(Collectors.toList());
    }

    public static List<EventProvider> debuffMechanics() {
        return Abilities.getDebuffMechanics().stream().map(DebuffProvider::new).collect(Collectors.toList());
    }

    public static List<EventProvider> advancedMechanics() {
        List<EventProvider> result = new ArrayList<>();
        Abilities.getDamageTaken().stream().map(DamageTakenProvider::new).forEach(result::add);
        Abilities.getDebuffs().stream().map(DebuffProvider::new).forEach(result::add);
        return result;
    }

    public static List<EventProvider> all() {
        List<EventProvider> result = new ArrayList<>();
        result.addAll(EventProviders.defensives());
        result.addAll(EventProviders.consumables());
        result.addAll(EventProviders.deaths());
        result.addAll(EventProviders.damageMechanics());
        result.addAll(EventProviders.debuffMechanics());
        result.addAll(EventProviders.advancedMechanics());
        return result;
    }
}
