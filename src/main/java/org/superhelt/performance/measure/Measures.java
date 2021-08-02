package org.superhelt.performance.measure;

import org.superhelt.performance.om.Abilities;

import java.util.*;
import java.util.stream.Collectors;

public class Measures {

    public static List<Measure> getDefensives() {
        return Abilities.getDefensives().stream().map(DefensiveMeasure::new).collect(Collectors.toList());
    }

    public static List<Measure> getHeals() {
        return Abilities.getHeals().stream().map(CastMeasure::new).collect(Collectors.toList());
    }

    public static List<Measure> getMechanics() {
        return Abilities.getDamageMechanics().stream().map(DamageTakenMeasure::new).collect(Collectors.toList());
    }

    public static List<Measure> getDebuffMechanics() {
        return Abilities.getDebuffMechanics().stream().map(DebuffMeasure::new).collect(Collectors.toList());
    }

    public static List<Measure> getAdvancedMechanics() {
        return Collections.emptyList();
    }

    public static List<Measure> getAll() {
        List<Measure> result = new ArrayList<>();

        result.addAll(getDefensives());
        result.addAll(getHeals());
        result.addAll(getMechanics());
        result.addAll(getDebuffMechanics());
        result.addAll(getAdvancedMechanics());
        result.add(new EarlyDeathMeasure());
        result.add(new ProgressMeasure());
        result.add(new FarmMeasure());

        return getUnique(result);
    }

    private static List<Measure> getUnique(List<Measure> measures) {
        Set<String> knownMeasures = new HashSet<>();
        List<Measure> result = new ArrayList<>();

        for(Measure measure : measures) {
            if(!knownMeasures.contains(measure.getName())) {
                result.add(measure);
                knownMeasures.add(measure.getName());
            }
        }

        return result;
    }
}
