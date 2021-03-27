package org.superhelt.performance.measure;

import org.superhelt.performance.om.Abilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Measures {

    public static List<Measure> getDefensives() {
        return Abilities.getDefensives().stream().map(DefensiveMeasure::new).collect(Collectors.toList());
    }

    public static List<Measure> getHeals() {
        return Abilities.getHeals().stream().map(CastMeasure::new).collect(Collectors.toList());
    }

    public static List<Measure> getMechanics() {
        return Abilities.getMechanics().stream().map(DamageTakenMeasure::new).collect(Collectors.toList());
    }

    public static List<Measure> getAdvancedMechanics() {
        return Arrays.asList(
                new VolatileEjectionDoubleMeasure(),
                new SeismicShiftSplashMeasure(),
                new NightHunterSoakMeasure(),
                new NightHunterDoubleSoakMeasure(),
                new FatalFinesseSoakingMeasure()
        );
    }

    public static List<Measure> getAll() {
        List<Measure> result = new ArrayList<>();

        result.addAll(getDefensives());
        result.addAll(getHeals());
        result.addAll(getMechanics());
        result.addAll(getAdvancedMechanics());
        result.add(new EarlyDeathMeasure());
        result.add(new ProgressMeasure());
        result.add(new FarmMeasure());

        return result;
    }
}
