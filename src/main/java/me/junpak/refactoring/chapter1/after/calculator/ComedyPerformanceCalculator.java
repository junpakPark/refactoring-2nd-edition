package me.junpak.refactoring.chapter1.after.calculator;

import me.junpak.refactoring.chapter1.after.PerformanceCalculator;
import me.junpak.refactoring.chapter1.data.Performance;
import me.junpak.refactoring.chapter1.data.Play;

public class ComedyPerformanceCalculator extends PerformanceCalculator {

    public ComedyPerformanceCalculator(final Performance aPerformance, final Play aPlay) {
        super(aPerformance, aPlay);
    }

}
